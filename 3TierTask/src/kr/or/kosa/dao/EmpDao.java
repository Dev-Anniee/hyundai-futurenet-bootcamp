package kr.or.kosa.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import kr.or.kosa.dto.Emp;
import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class EmpDao {
    public List<Emp> getEmpAllList() {
        List<Emp> EmpList = new ArrayList<Emp>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionHelper.getConnection(DBType.ORACLE);
            String sql = "SELECT e.empno, e.ename, e.deptno, d.dname FROM emp e JOIN dept d ON e.deptno = d.deptno";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Emp emp = new Emp();
                emp.setEmpno(rs.getInt(1));
                emp.setEname(rs.getString(2));
                emp.setDeptno(rs.getInt(3));
                emp.setDname(rs.getString(4));
                EmpList.add(emp);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return EmpList;
    }

    public Emp getEmpByEmpno(int empno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Emp Emp = null;
        try{
            conn = ConnectionHelper.getConnection(DBType.ORACLE);
            String sql = "SELECT e.empno, e.ename, e.deptno, d.dname " +
                "FROM emp e JOIN dept d ON e.deptno = d.deptno " +
                "WHERE e.empno = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empno);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Emp = new Emp();
                Emp.setEmpno(rs.getInt(1));
                Emp.setEname(rs.getString(2));
                Emp.setDeptno(rs.getInt(3));
                Emp.setDname(rs.getString(4));
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return Emp;
    }

    public int insertEmp(Emp emp){
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowcnt =0;

        try{
            conn = ConnectionHelper.getConnection(DBType.ORACLE);
            String sql = "insert into emp(empno, ename, deptno) values(?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, emp.getEmpno());
            pstmt.setString(2, emp.getEname());
            pstmt.setInt(3, emp.getDeptno());

            rowcnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return rowcnt;
    }
    public int updateEmp(Emp emp){
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowcnt =0;

        try{
            conn = ConnectionHelper.getConnection(DBType.ORACLE);
            String sql = "update emp set ename=?, deptno=? where empno=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getEname());
            pstmt.setInt(2, emp.getDeptno());
            pstmt.setInt(3, emp.getEmpno());
            rowcnt = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return rowcnt;
    }

    public int deleteEmp(int empno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowcnt =0;

        try{
            conn = ConnectionHelper.getConnection(DBType.ORACLE);
            String sql = "delete from emp where empno=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empno); //첫 번째 물음표에 empno를 집어 넣는다
            rowcnt = pstmt.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return rowcnt;
    }

    public List<Emp> getEmpListByLikeEname(String ename) {
        List<Emp> EmpList = new ArrayList<>();
         Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;

         try{
             conn = ConnectionHelper.getConnection(DBType.ORACLE);
             String sql = "SELECT e.empno, e.ename, e.deptno, d.dname " +
                 "FROM emp e JOIN dept d ON e.deptno = d.deptno " +
                 "WHERE e.ename LIKE ?";
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, "%"+ename+"%");
             rs = pstmt.executeQuery();

             while (rs.next()) {
                 Emp Emp = new Emp();
                 Emp.setEmpno(rs.getInt("empno"));
                 Emp.setEname(rs.getString("ename"));
                 Emp.setDeptno(rs.getInt("deptno"));
                 Emp.setDname(rs.getString("dname"));
                 EmpList.add(Emp);
             }
         }catch (Exception e) {
             System.out.println(e.getMessage());
         }finally {
             ConnectionHelper.close(rs);
             ConnectionHelper.close(pstmt);
             ConnectionHelper.close(conn);
         }
         return EmpList;
    }
    public List<Emp> getEmpListByRegexp(String pattern) {
        List<Emp> EmpList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionHelper.getConnection(DBType.ORACLE);
            String sql = "SELECT e.empno, e.ename, e.deptno, d.dname " +
                "FROM emp e JOIN dept d ON e.deptno = d.deptno " +
                "WHERE REGEXP_LIKE(e.ename, ?, 'i')";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pattern);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Emp emp = new Emp();
                emp.setEmpno(rs.getInt("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setDeptno(rs.getInt("deptno"));
                emp.setDname(rs.getString("dname"));
                EmpList.add(emp);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return EmpList;
    }
}
