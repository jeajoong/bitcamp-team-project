package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.eomcs.lms.dao.AnnounceDao;
import com.eomcs.lms.dao.AnnounceFileDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Announce;
import com.eomcs.lms.domain.AnnounceFile;
import com.eomcs.lms.service.AnnounceService;

// 스프링 IoC 컨테이너가 관리하는 객체 중에서 
// 비즈니스 로직을 담당하는 객체는 
// 특별히 그 역할을 표시하기 위해 @Component 대신에 @Service 애노테이션을 붙인다.
// 이렇게 애노테이션으로 구분해두면 나중에 애노테이션으로 객체를 찾을 수 있다.
@Service
public class AnnounceServiceImpl implements AnnounceService {

  AnnounceDao announceDao;
  MemberDao memberDao;
  AnnounceFileDao fileDao;

  public AnnounceServiceImpl(
      AnnounceDao announceDao, 
      MemberDao memberDao,
      AnnounceFileDao fileDao) {
    this.announceDao = announceDao;
    this.memberDao = memberDao;
    this.fileDao = fileDao;
  }

  // 비지니스 객체에서 메서드 이름은 가능한 업무 용어를 사용한다.
  @Override
  public List<Announce> list(int pageNo, int pageSize, int memberNo) {

    if (memberNo <= 0) {
      HashMap<String,Object> params = new HashMap<>();
      params.put("size", pageSize);
      params.put("rowNo", (pageNo - 1) * pageSize);
      
      return announceDao.findAll(params);
      

    } else {
      HashMap<String,Object> params = new HashMap<>();

      if (memberNo > 0) {
        params.put("memberNo", memberNo);
      } else {
        params.put("memberNo", null);
      }
      params.put("size", pageSize);
      params.put("rowNo", (pageNo - 1) * pageSize);
      return announceDao.findAll(params);
    }
  }

  
  @Override
  public Announce get(int no) {

    Announce announce = announceDao.findByNoWithFile(no);
    if (announce != null) {
      announceDao.increaseCount(no);
    }
    return announce;
  }
  
  @Override
  public int add(Announce announce) {
    
    int count = announceDao.insert(announce);
    
    List<AnnounceFile> files = announce.getFiles();
    for (AnnounceFile f : files) {
      f.setAnnounceNo(announce.getNo());
    }

    fileDao.insert(announce.getFiles());

    return count;
  }


  @Override
  public Announce getUpdate(int no) {
    
    Announce announce = announceDao.detailUpdate(no);
    
    return announce;
  }
  
  
  @Override
  public int update(Announce announce) {
    return announceDao.update(announce);
  }

  @Override
  public int delete(int no) {
    fileDao.deleteByAnnounceNo(no);
    return announceDao.delete(no);
  }

  @Override
  public int size() {
    return announceDao.countAll();
  }
}






