package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.domain.TermsAgree;
import com.eomcs.lms.service.KakaoService;
import com.eomcs.lms.service.MemberService;


@Service
public class KakaoServiceImpl implements KakaoService {
  
  final static Logger logger = LogManager.getLogger(KakaoServiceImpl.class);
  
  MemberService memberService;
  
  public KakaoServiceImpl(MemberService memberService) {
    this.memberService = memberService;
  }
  
  public String requestKakaoAccessToken(HttpSession session, String code) throws Exception {
    logger.info("token");
    
    RestTemplate template = new RestTemplate();
    String kakaoUrl = "https://kauth.kakao.com/oauth/token";
    MultiValueMap<String,String> paramMap = new LinkedMultiValueMap<String,String>();
    paramMap.add("grant_type", "authorization_code");
    paramMap.add("client_id", "43ee012e7eba5344ec3ae793e76332f5");
    paramMap.add("redirect_uri", "http://localhost:8080/bitcamp-team-project/app/auth/kakaoAccessToken");
    paramMap.add("code", code);
    paramMap.add("client_secret", "UlIkhLNaHbdIqlFRgF8LoaXg5N6lYFhz");
    
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(paramMap, headers);
    
    String rawJsonString = 
        template.postForObject(kakaoUrl, request, String.class);
    
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
    
    String kakaoAccessToken = (String) jsonObject.get("access_token");
    
    logger.debug("kakaoAccessToken / accessToken : "+ kakaoAccessToken);
    
    session.setAttribute("kakaoAccessToken", kakaoAccessToken);
    
    return kakaoAccessToken;
    
  }
  
  @SuppressWarnings("unchecked")
  public String kakaoUserDataLoadAndCheck(String accessToken, HttpSession session) throws Exception {
    String kakaoUrl = 
        "https://kapi.kakao.com/v2/user/me";
    RestTemplate template = new RestTemplate();
    
    MultiValueMap<String,String> paramMap = new LinkedMultiValueMap<String,String>();
    paramMap.add("property_keys", "[\"kakao_account.email\", \"properties.nickname\"]");
    
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + accessToken);
    HttpEntity<Object> request = new HttpEntity<>(paramMap, headers);
    String rawJsonString = template.postForObject(kakaoUrl, request, String.class);
    
    logger.debug("kakaoAccessToken:check / rawJson! : " + rawJsonString);

    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);

    logger.debug("kakaoUserDataLoadAndCheck / raw json : " + jsonObject);
    
    HashMap<String,Object> response = (HashMap<String,Object>) jsonObject.get("kakao_account");

    String email = (String) response.get("email");
    logger.info("email >> " + email);
    
    if (email == null || email.length() == 0) {
      session.setAttribute("login_type", "noEmail");
      return "redirect:kakaoLoginFail";
    }
    
    Member member = memberService.get(email);
    
    // 해당 이메일로 가입된 유저가 없으면 신규가입으로 보낸다.
    if (member == null) {
      return "redirect:kakaoJoin";
    }
    
    // 해당 이메일로 가입되어있지만, 로그인 타입이 네이버가 아니라면, alert창을 띄우게 한다.
    if (!member.getLoginType().equalsIgnoreCase("kakao")) {
      session.setAttribute("login_type", member.getLoginType());
      return "redirect:kakaoLoginFail";
    }
    
    // 해당 이메일로 가입되어있고, 로그인 타입이 네이버라면 자동로그인 처리한 후 메인으로 보낸다.
    session.setAttribute("loginUser", member);

    return "redirect:loginSuccess";
}
  
  @SuppressWarnings("unchecked")
  public String kakaoUserDataLoadAndSave(
      String accessToken, 
      HttpSession session, 
      TermsAgree termsAgree) throws Exception {
    String kakaoUrl = 
        "https://kapi.kakao.com/v2/user/me";
    RestTemplate template = new RestTemplate();
    MultiValueMap<String,String> paramMap = new LinkedMultiValueMap<String,String>();
    paramMap.add("property_keys", "[\"kakao_account.email\", \"properties.nickname\"]");
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + accessToken);
    HttpEntity<Object> request = new HttpEntity<>(paramMap, headers);
    String rawJsonString = template.postForObject(kakaoUrl, request, String.class);
    
    logger.debug("kakaoAccessToken:check / rawJson! : " + rawJsonString);

    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);

    logger.debug("kakaoUserDataLoadAndCheck / raw json : " + jsonObject);
    
    HashMap<String,Object> response = (HashMap<String,Object>) jsonObject.get("kakao_account");
    HashMap<String,Object> properties = (HashMap<String,Object>) jsonObject.get("properties");
    String nickname = (String) properties.get("nickname");
    String email = (String) response.get("email");

    Member member = new Member();
    member.setId("kakao-" + UUID.randomUUID().toString());
    member.setName(nickname);
    member.setEmail(email);
    member.setPassword(UUID.randomUUID().toString());
    member.setLoginType("kakao");
    
    memberService.add(member, termsAgree);
    
    // 회원가입 후 자동로그인처리
    Member newMember = memberService.get(member.getNo());
    
    logger.debug(newMember);
      
    if (newMember == null) {
      return "fail";
    }
    
    session.setAttribute("loginUser", newMember);
    return "success";
}
}







