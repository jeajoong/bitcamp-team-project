package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Announce;

public interface AnnounceDao {
  int insert(Announce announce);
  List<Announce> findAll();
  Announce findByNo(int no);
  int increaseCount(int no);
  int update(Announce announce);
  int delete(int no);
  int countAll();
}






