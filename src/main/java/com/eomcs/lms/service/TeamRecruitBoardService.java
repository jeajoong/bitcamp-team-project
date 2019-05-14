package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.TeamRecruit;

public interface TeamRecruitBoardService {
  List<TeamRecruit> list(int pageNo, int pageSize);

  List<TeamRecruit> list2();

  List<TeamRecruit> boardSearch(String keyword);

  int add(TeamRecruit teamRecruit);

  TeamRecruit get(int no);

  int update(TeamRecruit teamRecruit);

  int delete(int no);

  // int size();
}
