import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex08_MariaDB_Connection {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = ConnectionHelper.getConnection(DBType.MARIADB); //연결 정보만 바꾸면 된다
		String sql = "SELECT E.EMPNO, E.ENAME, D.DNAME FROM EMP E JOIN DEPT D ON E.DEPTNO = D.DEPTNO";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while(rs.next()) {
		    System.out.println(rs.getInt("EMPNO") + "\t" + rs.getString("ENAME") + "\t" + rs.getString("DNAME"));
		}

	}

}
