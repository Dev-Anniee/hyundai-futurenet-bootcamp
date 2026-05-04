import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

/*

update emp set ename=? , sal=?, job=? , deptno=?
where empno=?

값은 알아서 설정

*/

public class Ex06_Oracle_PrepareStatement {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection(DBType.ORACLE);
			String sql = "update emp set ename=? , sal=?, job=? , deptno=? where empno=?";
			
			pstmt = conn.prepareStatement(sql); // 객체를 얻기 전에 미리 쿼리를 보내서 컴파일 정보를 전달
	
			//parameter 설정
			pstmt.setString(1, "아무개");
			pstmt.setInt(2, 5555);
			pstmt.setString(3, "IT");
			pstmt.setInt(4, 30);
			pstmt.setInt(5, 7369);
			
			int row = pstmt.executeUpdate(); //parameter 가지고 서버 DB
			if(row>0)
				System.out.println(row+"건 반영");
			else {
				System.out.println(row+"건 반영 건 없어요");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}

	}
}
