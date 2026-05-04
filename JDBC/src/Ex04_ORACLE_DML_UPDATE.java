/*
  update emp set sal=0 where deptno=20
*/

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex04_ORACLE_DML_UPDATE {

	public static void main(String[] args) throws SQLException{
		Connection conn =  null ;
		Statement stmt = null;
		
		try {
			  conn =  ConnectionHelper.getConnection(DBType.ORACLE);
			  //명령객체 생성
			  stmt = conn.createStatement();
			  
			  //명령
			  String sql="update emp set sal=0 where deptno=20";
			  
			  int resultRow = stmt.executeUpdate(sql); //executeUpdate > update , delete , insert
			  //실반영 (JDBC API) commit ...
			  if(resultRow > 0) {
				  System.out.println("반영된 행의 수 : " + resultRow);
			  }else {
				  System.out.println("반영된 행이 없습니다");
			  }
			  
		} catch (Exception e) {
			//stmt.executeUpdate(sql) 문제 생기면
			System.out.println("SQL 예외발생 : " + e.getMessage());
		}finally {
			ConnectionHelper.close(stmt);
			ConnectionHelper.close(conn);
		}
	}

}
