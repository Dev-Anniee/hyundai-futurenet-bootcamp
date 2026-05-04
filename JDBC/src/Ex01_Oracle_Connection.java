/*

JDBC

1. Java 언어(APP)를 통해서 Oracle(소프트웨어) 연결해서 CRUD작업
2. Java App : Oracle , My-sql , MS-sql 등등 연결하고 작업(CRUD)
		2.1 각각의 제품에 맞는 드라이버를 가지고 있어야 합니다
		CASE 1: 삼성 노트북 >> HP 프린터 연결 >> HP프린터 사이트에서 드라이버 다운 >> 삼성 설치 
		CASE 2: HP프린터 제조 회사는 ... 삼성, LG 회사마다 적용할 수 있는 드라이버를 별도 제작

각 언어에 맞는 드라이버를 다운로드 해서 제품에 맞게 설치 .... 접속 ...
Oracle (https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html)
Mysql (https://dev.mysql.com/downloads/connector/j/)

3. 드라이버를 참조 (현재 프로젝트에서 사용) >> Java Project -> 속성 -> build path ->
jar 파일 추가 
	3.1 드라이버 사용 준비 완료 >> 메모리에 load 사용 ....

3.2 Class.forName("oracle.jdbc.OracleDriver")..... new 객체 생성 ....

4. JAVA CODE (CRUD) >> JDBC API 제공 받는다
4.1 import java.sql.* >> interface , class 제공

4.2 개발자는 interface 를 통해서 표준화된 DB 작업 수행
	POINT) why interface형태로 제공 >> JDBC API >> Oracle, Mysql , ....)

	//OracleConnection >> Connection 구현 >> Connection conn = new OracleConnection : 다형성 보장
	//MysqlConnection >> Connection 구현 >> Connection conn = new MysqlConnection : 다형성 보장

>>다형성을 구현 (인터페이스 활용)
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement 등등 

5. 작업순서
	5.1 DB연결 -> 명령생성 -> 명령실행(select<>, update, delete) -> 처리 -> 자원해제
 	자원해제는 필수 왜? 가비지 컬렉터가 자동 처리 불가능

5.1 명령 : DDL  (create , alter , drop)
		  CRUD (insert , select , update , delete)

실행 : 쿼리문을 DB서버에게 전달 
처리 : 결과를 받아서 화면 출력 , 또는 다른 프로그램에 전달 등등
자원해제 : 연결해제 

*/
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ex01_Oracle_Connection {
	public static void main(String[] args) throws Exception{
		//DB 연결
		//제품에 맞는 드라이버 메모리에 로딩
		Class.forName("oracle.jdbc.OracleDriver");
		// new  연산자 없이 독자적인 메모리 생성 가능
		//연결 문자열 (데이터베이스 연결을 위한 최소한의 정보) :IP, PORT, 계정, 비밀번호까지
		
		//오라클 버전 11c
		//DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KOSA","1004");
		// 주의사항 : @localhost:1521/XEPDB1 - :은 SID를, /는 서비스 이름 -> /XEPDB1
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","KOSA","1004"); //정보를 가지고 오라클 서버로 접속을 시도
		//OracleConnection 객체가 Conncection을 구현하고 있다
		//MysqlConnection 객체가 Conncection을 구현하고 있다
		// 다형성의 원리 
		System.out.println("DB연결: "+ conn.isClosed()); // 아니요 닫혀있지 않아요~! false 반환
		
		//연결 성공
		
		//명령 객체 얻어오기
		Statement stmt = conn.createStatement();
		
		//명령 - DDL, DML
		String sql = "select empno, ename, sal from emp";
		
		//명령 실행
		//실행 결과 집합 (Result set) : DB 서버
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs를 통해서 DB 서버 메모리 > row 단위 > Data Read
		while(rs.next()) { //하나씩 접근
			System.out.println(rs.getInt("empno")+"/"+rs.getString("ename")+"/"+rs.getInt(3));
		}
		//자원 해제
		rs.close();
		stmt.close();
		conn.close();
		System.out.println("DB연결 : " + conn.isClosed()); //true
	}
}
