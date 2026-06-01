<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page buffer="8kb" autoFlush="true" %>    
<%
	/* 
		JAVA : IO > buffer > default 8kb > 비워지는 시점 : 1. 8kbyte(채워지면)  2. 강제 출발(flush (close()))

		기본적으로 JSP 페이지도
		설정을 강제하지 않으면
		buffer="8kbyte" autoFlush="true"
		버퍼가 꽉 차면 자동으로 브라우저에 보내라
	*/

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%
			for(int i = 0 ; i < 10 ; i++){
				out.print("<b>" + i + "<b><br>");
			}
		%>
</body>
</html>