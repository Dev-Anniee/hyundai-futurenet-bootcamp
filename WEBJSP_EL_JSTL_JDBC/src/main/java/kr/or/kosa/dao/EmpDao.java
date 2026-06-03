package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.EmpDto;
import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class EmpDao {
    public List<EmpDto> getEmpList() {
        List<EmpDto> empList = new ArrayList<>();
        String sql = "select e.empno, e.ename, e.job, nvl(e.mgr, 0) mgr, " +
            "to_char(e.hiredate, 'YYYY-MM-DD') hiredate, e.sal, nvl(e.comm, 0) comm, " +
            "e.deptno, d.dname " +
            "from emp e left join dept d on e.deptno = d.deptno " +
            "order by e.empno";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionHelper.getConnection(DBType.ORACLE);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empList.add(mapEmp(rs));
            }
        } catch (Exception e) {
            System.out.println("getEmpList error: " + e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return empList;
    }

    public List<EmpDto> getEmpListByDeptNo(int deptno) {
        List<EmpDto> empList = new ArrayList<>();
        String sql = "select e.empno, e.ename, e.job, nvl(e.mgr, 0) mgr, " +
            "to_char(e.hiredate, 'YYYY-MM-DD') hiredate, e.sal, nvl(e.comm, 0) comm, " +
            "e.deptno, d.dname " +
            "from emp e left join dept d on e.deptno = d.deptno " +
            "where e.deptno = ? " +
            "order by e.empno";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionHelper.getConnection(DBType.ORACLE);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deptno);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empList.add(mapEmp(rs));
            }
        } catch (Exception e) {
            System.out.println("getEmpListByDeptNo error: " + e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return empList;
    }

    private EmpDto mapEmp(ResultSet rs) throws Exception {
        EmpDto emp = new EmpDto();
        emp.setEmpno(rs.getInt("empno"));
        emp.setEname(rs.getString("ename"));
        emp.setJob(rs.getString("job"));
        emp.setMgr(rs.getInt("mgr"));
        emp.setHiredate(rs.getString("hiredate"));
        emp.setSal(rs.getInt("sal"));
        emp.setComm(rs.getInt("comm"));
        emp.setDeptno(rs.getInt("deptno"));
        emp.setDname(rs.getString("dname"));
        return emp;
    }
}
