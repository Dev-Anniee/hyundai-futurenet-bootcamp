package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Dept;
import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

/*
1. DB연결
2. CRUD 함수 구현

전체조회 : select deptno , dname , loc from dept
조건조회 : select deptno , dname , loc from dept where deptno=?
삽입    : insert into dept(deptno,dname,loc) values(?,?,?)
삭제    : delete from dept where deptno=?
수정    : update dept set dname=? , loc=? where deptno=?
추가)
LIKE 검색
등등 ...  '%검색어%'
*/

public class DeptDao {
	public List<Dept> getDeptAllList(){
		
		List<Dept> deptList = new ArrayList<Dept>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = ConnectionHelper.getConnection(DBType.ORACLE);
			  String sql="select deptno , dname , loc from dept";
			  
			  pstmt = conn.prepareStatement(sql);
			  rs = pstmt.executeQuery();
			  
			  while(rs.next()) {
				  //한건의 Dept List Add
				  Dept dept = new Dept();
				  dept.setDeptno(rs.getInt("deptno"));
				  dept.setDname(rs.getString("dname"));
				  dept.setLoc(rs.getString("loc"));
				  //List추가
				  deptList.add(dept);
			  }
			  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return deptList;
	}
	
	public Dept getDeptListByDeptno(int deptno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Dept dept=null;
		try {
			  conn = ConnectionHelper.getConnection(DBType.ORACLE);
			  String sql="select deptno , dname , loc from dept where deptno=?";
			  
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setInt(1, deptno);
			  rs = pstmt.executeQuery();
			  
			  while(rs.next()) {
				  //한건의 Dept List Add
				  dept = new Dept();
				  dept.setDeptno(rs.getInt("deptno"));
				  dept.setDname(rs.getString("dname"));
				  dept.setLoc(rs.getString("loc"));
			  }
			  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return dept;
	}
	
	public int insertDept(Dept dept) { // valeus(?,?,?)
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount =0;
		
		try {
			   conn = ConnectionHelper.getConnection(DBType.ORACLE);
			   String sql="insert into dept(deptno,dname,loc) values(?,?,?)";
			   
			   pstmt = conn.prepareStatement(sql);
			   
			   pstmt.setInt(1, dept.getDeptno());
			   pstmt.setString(2, dept.getDname());
			   pstmt.setString(3, dept.getLoc());
			   
			   rowcount = pstmt.executeUpdate();
			   
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		
		return rowcount;
	}
	
	//update dept set dname=? , loc=? where deptno=?
	public int updateDept(Dept dept) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount =0;
		
		try {
			   conn = ConnectionHelper.getConnection(DBType.ORACLE);
			   String sql="update dept set dname=? , loc=? where deptno=?";
			   
			   pstmt = conn.prepareStatement(sql);
			   
			   
			   pstmt.setString(1, dept.getDname());
			   pstmt.setString(2, dept.getLoc());
			   pstmt.setInt(3, dept.getDeptno());
			   
			   rowcount = pstmt.executeUpdate();
			   
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		
		return rowcount;
	}
	
	//delete from dept where deptno=?
	public int deleteDept(int deptno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount =0;
		
		try {
			   conn = ConnectionHelper.getConnection(DBType.ORACLE);
			   String sql="delete from dept where deptno=?";
			   
			   pstmt = conn.prepareStatement(sql);
			   
			   pstmt.setInt(1, deptno);
			   
			   rowcount = pstmt.executeUpdate();
			   
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		
		return rowcount;
	}
}