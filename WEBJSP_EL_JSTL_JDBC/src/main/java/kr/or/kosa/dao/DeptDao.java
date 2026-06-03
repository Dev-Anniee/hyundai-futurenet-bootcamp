package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Dept;
import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class DeptDao {
    public List<Dept> getDeptListByDeptNo(int deptno) {
        List<Dept> deptList = new ArrayList<>();
        String sql = "select deptno, dname, loc from dept where deptno = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionHelper.getConnection(DBType.ORACLE);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deptno);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                deptList.add(new Dept(
                    rs.getInt("deptno"),
                    rs.getString("dname"),
                    rs.getString("loc")
                ));
            }
        } catch (Exception e) {
            System.out.println("getDeptListByDeptNo error: " + e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return deptList;
    }

    public Dept getDeptByDeptNo(int deptno) {
        List<Dept> deptList = getDeptListByDeptNo(deptno);
        return deptList.isEmpty() ? null : deptList.get(0);
    }
}
