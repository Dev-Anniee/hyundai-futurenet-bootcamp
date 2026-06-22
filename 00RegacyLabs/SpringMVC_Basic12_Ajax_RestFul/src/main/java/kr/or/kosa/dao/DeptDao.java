package kr.or.kosa.dao;

import java.util.List;
import kr.or.kosa.dto.Dept;

public interface DeptDao {
  int insert(Dept dept);
  List<Dept> select();
  Dept selectByDeptno(int deptno);
  int update(Dept dept);
  int delete(int deptno);
}
