package kr.or.kosa.service;

import java.util.List;
import kr.or.kosa.dao.DeptDao;
import kr.or.kosa.dto.Dept;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
  private SqlSession sqlsession;

  @Autowired
  public void setSqlsession(SqlSession sqlsession) {
    this.sqlsession = sqlsession;
  }

  public List<Dept> selectAllDeptList(){
    DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
    List<Dept> list = deptDao.select();
    return list;
  }

  public Dept selectByDeptno(int deptno){
    DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
    Dept dept = deptDao.selectByDeptno(deptno);
    return dept;
  }

  public int insert(Dept dept){
    DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
    return deptDao.insert(dept);
  }

  public int update(Dept dept){
    DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
    return deptDao.update(dept);
  }

  public int delete(int deptno){
    DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
    return deptDao.delete(deptno);
  }
}
