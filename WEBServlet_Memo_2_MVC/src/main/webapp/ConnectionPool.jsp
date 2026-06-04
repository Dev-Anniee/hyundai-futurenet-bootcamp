<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	DBCP 커넥션 풀 (미리 연결 생성 놓고 ..사용하고 반환 )
	커넥션 풀 종류 (Hikari CP , 연습 : Tomcat 제공 Pool (tomcat-dbcp.jar))
	
	context.xml 설정 가져와서 사용
	1.Tomcat 가지고 있는 context.xml 설정
	2.프로젝트 단위 설정 context.xml > META-INF > context.xml
	
	//JNDI (java Naming and Directory Interface) : 원도우 탐색기 검색 .... 찾는다
	
	name="jdbc/oracle" 가지는 Pool 상점 찿기
*/

    Context context = new InitialContext();
	//현재 프로젝트 문서 전체에서 이름으로 검색
	
	DataSource ds =  (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	// java:comp/env/ 이미 정의된 약속된 표현 + 내가만든 이름  
	
	//웹서버 메모리에 튜브가게 오픈 > 가게찾기 > DB연결 자원(타입 :DataSource ) > 빌려서 사용
	Connection conn = null;
	
	//빌려주세요
	conn = ds.getConnection();// 튜브 빌려서 
	
	out.print("DB연결 여부 확인(false) : " + conn.isClosed() + "<br>");
	
	//놀고
	//반드시 반환 
	conn.close(); //자원해제가 아니고 반환 (pool);
	
	out.print("반환 확인(false) : " + conn.isClosed() + "<br>");
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>