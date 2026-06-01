<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/error/commonError.jsp" %>    
<!--
서버쪽에서 예외가 발생 .... 코드를 클라이언트 ... 발생된 코드 (x)
1. 문제 발생시 대체 페이지를 만들어서 그 페이지 서비스
2. 전역 (web.xml)
3. 각각의 페이지 별로 예외서비스를 제공
  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int data = 0/0;
%>
</body>
</html>