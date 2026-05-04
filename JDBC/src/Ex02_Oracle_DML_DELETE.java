
/*
 1. select > 결과집합 (resultSet) >read
 2. DML (insert,update, delete)
 2.1 결과 집합이 없어요 > resultSet(x)
 2.2 반영된 결과 수 (반영된 행의 수) update (5) > 5, update (0) > 0
 2.3 return 되는 값이 0보다 크면 된다
 
 TodayPoint
 1. 툴(sqlplus, Developer) : insert,update, delete 반드시 commit 아니면 rollback 강제
 2. JDBC API : insert, update, delete JAVA 코드로 작업 > default > auto commit > 실반영
 3. JDBC API : auto commit > 개발자 강제 코드 제어 > false(옵션 기능 사용하려면) > 반드시 commit 아니면 rollback 강제
 
begin
 	A 계좌 : update
	B 계좌 : update
end
하나의 논리적인 작업 단위 (transaction)
성공 or 실패

JAVA : JDBC API
auto commit > false
반드시 commit 아니면 rollback 구현

실습 테이블 (제약) drop
@C:\Edu\Database\Data\Data.sql
 */

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex02_Oracle_DML_DELETE {

	public static void main(String[] args) throws SQLException{
		//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","KOSA","1004");
		
		Connection conn =  null ;
		Statement stmt = null;
		
		try {
			  conn =  ConnectionHelper.getConnection(DBType.ORACLE);
			  //명령객체 생성
			  stmt = conn.createStatement();
			  
			  //parameter 받기
			  int deptno=0;
			  Scanner sc = new Scanner(System.in);
			  System.out.println("부서번호입력");
			  deptno = Integer.parseInt(sc.nextLine());
			  
			  //명령
			  String sql="delete from emp where deptno="+deptno;
			  //사실
			  //preparedStatement 
			  //"delete from emp where deptno=?";
			  
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
