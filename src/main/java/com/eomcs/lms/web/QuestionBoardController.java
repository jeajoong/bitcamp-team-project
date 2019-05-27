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
import org.springframework.web.bind.annotation.RequestParam;
import com.eomcs.lms.domain.AnswerBoard;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.domain.QuestionBoard;
import com.eomcs.lms.domain.TeamRecruit;
import com.eomcs.lms.service.AnswerBoardService;
import com.eomcs.lms.service.QuestionBoardService;

@Controller
@RequestMapping("/question")
public class QuestionBoardController {

  private static final Logger logger = LogManager.getLogger(QuestionBoardController.class);

  @Autowired
  AnswerBoardService answerBoardService;
  @Autowired
  QuestionBoardService questionBoardService;
  @Autowired
  ServletContext servletContext;

  @GetMapping
  public String list(@RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "3") int pageSize, Model model, HttpSession session) {

    if (pageSize < 3 || pageSize > 8)
      pageSize = 3;

    // int rowCount = recruitBoardService.size();
    int rowCount = 1;
    int totalPage = rowCount / pageSize;
    if (rowCount % pageSize > 0)
      totalPage++;

    if (pageNo > totalPage)
      pageNo = totalPage;
    if (pageNo < 1)
      pageNo = 1;

      Member member = (Member) session.getAttribute("loginUser");
        
    List<QuestionBoard> question = questionBoardService.list(pageNo, pageSize);
    List<AnswerBoard> answer = answerBoardService.list();
   
    model.addAttribute("question", question);
    model.addAttribute("answer", answer);
    model.addAttribute("aa", member);
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("totalPage", totalPage);

    return "question/list";
  }
  
  @GetMapping("form")
  public void form() {
  }
  
  @PostMapping("add")
  public String add(QuestionBoard questionBoard, HttpSession session) {
    
    Member member = (Member) session.getAttribute("loginUser");
    
    questionBoard.setMember(member);
    questionBoard.setMemberNo(member.getNo());

    System.out.println(questionBoard);
    
    questionBoardService.add(questionBoard);
    return "redirect:.";
  }
  
  
  @PostMapping("add2")
  public String add(AnswerBoard answerBoard, HttpSession session ,QuestionBoard question) {
    
    Member member = (Member) session.getAttribute("loginUser");
    
    question.setQuestionStatus(true);
    questionBoardService.update22(question);

    answerBoardService.add(answerBoard);
    
    return "redirect:.";
  }
  
  @PostMapping("update2")
  public String update(AnswerBoard answerBoard) {
    
      System.out.println(answerBoard.toString());
    if (answerBoardService.update(answerBoard) == 0) {
      throw new RuntimeException("해당 번호의 게시물이 없습니다.");
    }
    return "redirect:./";
  }
  @GetMapping("delete/{no}") 
  public String delete(@PathVariable int no) {
     answerBoardService.deleteq(no);
    if (questionBoardService.delete(no) == 0) throw new RuntimeException("해당 번호의 게시물이 없습니다.");
    return "redirect:../";
  }
  
  @GetMapping("delete2/{no}") 
  public String delete2(@PathVariable int no) {
    if (answerBoardService.delete(no) == 0) throw new RuntimeException("해당 번호의 게시물이 없습니다.");
    return "redirect:../";
  }
  
  @GetMapping("{no}") 
  public String detail(@PathVariable int no, Model model ) { 
    QuestionBoard  question = questionBoardService.get(no); 
    AnswerBoard  answer = answerBoardService.get(no); 
    model.addAttribute("question", question);
    model.addAttribute("answer", answer);
    
    return "question/detail"; 
  }
  
  @GetMapping("mylist/{no}") 
  public String mylist(@PathVariable int no, Model model ) { 
    List<QuestionBoard> question = questionBoardService.get2(no); 
    model.addAttribute("question", question);
    return "question/mylist"; 
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

