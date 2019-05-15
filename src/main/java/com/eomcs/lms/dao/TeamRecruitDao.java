package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Team;
import com.eomcs.lms.domain.TeamRecruit;

public interface TeamRecruitDao {
  int insert(TeamRecruit teamRecruit);

  int insert2(Team team);
 
  List<TeamRecruit> findAll(Map<String, Object> params);
  
  List<TeamRecruit> findAll2();
  
  List<TeamRecruit> findByKeyword(String keyword);
  
  TeamRecruit findByNo(int no);
  
  List<Team> findByNo2(int no);
  
  int increaseCount(int no);
  
  int update(TeamRecruit teamRecruit);
  
  int delete(int no);

//  int countAll();
}


