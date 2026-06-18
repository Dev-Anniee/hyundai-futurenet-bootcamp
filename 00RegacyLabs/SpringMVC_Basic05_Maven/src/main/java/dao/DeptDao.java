 package dao;

import java.sql.SQLException;
import java.util.List;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import vo.Dept;

@Mapper
public interface DeptDao {
	public List<Dept> getDepts() throws ClassNotFoundException, SQLException;
	public Dept getDept(int deptno) throws ClassNotFoundException, SQLException;
	public int insert(Dept dept) throws ClassNotFoundException, SQLException;
	public int update(Dept dept) throws ClassNotFoundException, SQLException;
	public int delete(int deptno) throws ClassNotFoundException, SQLException;
	public List<Dept> selectSearch(Map<String, Object> map) throws ClassNotFoundException, SQLException;
}
