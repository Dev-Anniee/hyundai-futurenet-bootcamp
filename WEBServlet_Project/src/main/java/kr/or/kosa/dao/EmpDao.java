package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Emp;
import kr.or.kosa.utils.ConnectionPoolHelper;

public class EmpDao {
	
	 // 부서번호 목록 가져오기
	public List<Integer> getDeptNoList() {
	    List<Integer> list = new ArrayList<>();
	    String sql = "SELECT DISTINCT deptno FROM emp ORDER BY deptno";
	    try (Connection conn = ConnectionPoolHelper.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        while (rs.next()) {
	            list.add(rs.getInt("deptno"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

    // 부서번호로 사원 조회
    public List<Emp> getEmpListByDeptNo(int deptno) {
        List<Emp> empList = new ArrayList<>();
        String sql = "SELECT e.empno, e.ename, e.sal, e.hiredate, e.deptno, d.dname " +
                "FROM emp e JOIN dept d ON e.deptno = d.deptno " +
                "WHERE e.deptno=?";

        try (Connection conn = ConnectionPoolHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, deptno);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Emp emp = new Emp();
                    emp.setEmpno(rs.getInt("empno"));
                    emp.setEname(rs.getString("ename"));
                    emp.setSal(rs.getInt("sal"));
                    emp.setHiredate(rs.getDate("hiredate"));
                    emp.setDeptno(rs.getInt("deptno"));
                    emp.setDname(rs.getString("dname")); 
                    empList.add(emp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }
    
    public int insertEmp(Emp emp) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionPoolHelper.getConnection();
            String sql = "INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, emp.getEmpno());
            pstmt.setString(2, emp.getEname());
            pstmt.setString(3, emp.getJob());
            pstmt.setInt(4, emp.getMgr());
            pstmt.setDate(5, new java.sql.Date(emp.getHiredate().getTime()));
            pstmt.setInt(6, emp.getSal());
            pstmt.setInt(7, emp.getComm()); 
            pstmt.setInt(8, emp.getDeptno());

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPoolHelper.close(pstmt);
            ConnectionPoolHelper.close(conn);
        }
        return result;
    }

    public List<Emp> getEmpAllList() {
        List<Emp> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPoolHelper.getConnection();
            String sql = "SELECT e.empno, e.ename, e.job, e.mgr, e.hiredate, e.sal, e.comm, e.deptno, d.dname " +
                         "FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno " +
                         "ORDER BY e.empno";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Emp emp = new Emp(
                    rs.getInt("empno"),
                    rs.getString("ename"),
                    rs.getString("job"),
                    rs.getInt("mgr"),
                    rs.getDate("hiredate"),
                    rs.getInt("comm"),
                    rs.getInt("deptno"),
                    rs.getInt("sal"),
                    rs.getString("dname")
                );
                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPoolHelper.close(rs);
            ConnectionPoolHelper.close(pstmt);
            ConnectionPoolHelper.close(conn);
        }
        return list;
    }

    // 사번 중복 체크
    public boolean isEmpnoDuplicate(int empno) {
        String sql = "SELECT COUNT(*) cnt FROM emp WHERE empno=?";
        try (Connection conn = ConnectionPoolHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cnt") > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Integer> getMgrList() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT mgr FROM emp WHERE mgr IS NOT NULL ORDER BY mgr";
        try (Connection conn = ConnectionPoolHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getInt("mgr"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    


}
