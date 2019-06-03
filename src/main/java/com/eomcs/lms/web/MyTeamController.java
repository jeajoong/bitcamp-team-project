package com.eomcs.lms.web;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eomcs.lms.domain.AnswerBoard;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.domain.QuestionBoard;
import com.eomcs.lms.domain.Team;
import com.eomcs.lms.service.AnswerBoardService;
import com.eomcs.lms.service.MyTeamService;
import com.eomcs.lms.service.QuestionBoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/myteam")
public class MyTeamController {

  private static final Logger logger = LogManager.getLogger(MyTeamController.class);

  @Autowired
  AnswerBoardService answerBoardService;
  @Autowired
  QuestionBoardService questionBoardService;
  @Autowired
  MyTeamService myTeamService;
  @Autowired
  ServletContext servletContext;

 
  @GetMapping
  public String list(Model model, HttpSession session) throws JsonProcessingException  {
	  
	  ObjectMapper om = new ObjectMapper();
	  Member member = (Member)session.getAttribute("loginUser");
	
	  int a =	member.getNo();

	  List<Team> team = myTeamService.teamList(a);  

	  
	  String json = om.writeValueAsString(team);
	  System.out.println(json);
	  session.setAttribute("member",member);
	  model.addAttribute("team",team);
	  model.addAttribute("json",json);
	  
	  
    return "myteam/list";
  }
  
  /* 질문 게시글 폼 */ 
  @GetMapping("form")
  public void form() {
  }

  /* 질문 게시글 작성 */
  @PostMapping("add")
  public String add(QuestionBoard questionBoard, HttpSession session) {
	  
    Member member = (Member) session.getAttribute("loginUser");
    
    questionBoard.setMember(member);
    questionBoard.setMemberNo(member.getNo());
    
    
    
    questionBoardService.add(questionBoard);
    return "redirect:.";
  }
  
  
  /* 답변 하기 */
  @PostMapping("add2")
  public String add(AnswerBoard answerBoard, QuestionBoard question) {
    
    question.setQuestionStatus(true);
    questionBoardService.update22(question);

    answerBoardService.add(answerBoard);
    
    return "redirect:.";
  }
  
  /* 답변 수정하기 */
  @PostMapping("update2")
  public String update(AnswerBoard answerBoard) {
    
    if (answerBoardService.update(answerBoard) == 0) {
      throw new RuntimeException("해당 번호의 게시물이 없습니다.");
    }
    return "redirect:./";
  }

  /* 질문 게시글 삭제 */
  @GetMapping("delete/{no}") 
  public String delete(@PathVariable int no) {
     answerBoardService.deleteq(no);
    if (questionBoardService.delete(no) == 0) throw new RuntimeException("해당 번호의 게시물이 없습니다.");
    return "redirect:../";
  }
  
  /* 답글 삭제 */
  @GetMapping("delete2/{no}") 
  public String delete2(@PathVariable int no, QuestionBoard question) {
    answerBoardService.get2(no).getQuestionNo();
    QuestionBoard question1 =  questionBoardService.get(answerBoardService.get2(no).getQuestionNo());
    question1.setQuestionStatus(false);
    questionBoardService.update(question1);
    if (answerBoardService.delete(no) == 0) throw new RuntimeException("해당 번호의 게시물이 없습니다.");
    return "redirect:../";
  }
  
  /* 질문 게시글 상세보기 */
  @GetMapping("{no}") 
  public String detail(@PathVariable int no, Model model ) { 
    QuestionBoard  question = questionBoardService.get(no); 
    AnswerBoard  answer = answerBoardService.get(no); 
    System.out.println(question.toString());
    model.addAttribute("question", question);
    model.addAttribute("answer", answer);
    
    return "question/detail"; 
  }

  
  /* 내가 등록한 글만 보기 */
  @GetMapping("mylist/{no}") 
  public String mylist(@PathVariable int no, Model model ) { 
    List<QuestionBoard> question = questionBoardService.get2(no); 
    model.addAttribute("question", question);
    return "question/mylist"; 
  }
  

  /* 내가 등록한 글만 보기 */
  @GetMapping("answerlist") 
  public String answerlist(Model model ) { 
    List<QuestionBoard> question = questionBoardService.list(0, 0);
    model.addAttribute("question", question);
    return "question/answerlist"; 
  }
  
      
  @GetMapping("update/{no}")
  public String detailUpdate(@PathVariable int no, Model model) {
    QuestionBoard question = questionBoardService.getUpdate(no);
    model.addAttribute("question", question);
    return "question/update";
  }   


  @PostMapping("update")
  public String update(QuestionBoard question) {
    String a = String.valueOf(question.getQuestionNo());
   
    if (questionBoardService.update(question) == 0) {
      throw new RuntimeException("해당 번호의 게시물이 없습니다.");
    }
    return "redirect:../question/" + String.valueOf(question.getQuestionNo()); 
  }

}
