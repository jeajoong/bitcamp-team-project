package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.domain.Team;
import com.eomcs.lms.domain.TeamAges;
import com.eomcs.lms.domain.TeamLevel;
import com.eomcs.lms.domain.TeamType;
import com.eomcs.lms.domain.TeamTypeSports;

public interface TeamDao {
  int insert(Team team);
  Team findByNo(int no);
  List<Team> findAllTeam();
  
  List<TeamType> findTeamType();
  List<TeamAges> findTeamAge();
  List<TeamTypeSports> findSportsType();
  List<TeamLevel> findTeamLevel();
  
  List<Member> findAllMember(Map<String,Object> params);
  int update(Team teamO);
  int delete(int no);
  int countAll();

// 더 추가해야 할거 추가하기

}






