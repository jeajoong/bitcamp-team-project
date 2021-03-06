package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.domain.Team;
import com.eomcs.lms.domain.TeamMember;
import com.eomcs.lms.domain.TeamRecruit;

public interface TeamRecruitBoardService {
  List<TeamRecruit> list();

  List<TeamRecruit> list2();
  
  List<Member> list3();

  List<TeamRecruit> boardSearch(String keyword);

  int add(TeamRecruit teamRecruit);
  
  int add2(Team team);
  
  TeamRecruit get(int no);
  
  List<Team> get2(int no);
  
  List<TeamMember> get3(int no);
  
  TeamRecruit getUpdate(int no);

  int update(TeamRecruit teamRecruit);

  int delete(int no);

   int size();
   
   int add3(int no, int mno);
}
