<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="kr.or.kosa.Emp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	Emp emp = new Emp(2000,"kosauser");
	
	//emp 객체를 다른 페이지도 공유
	//담는다 (session (모든 페이지) , request(현재 + include , forward 페이지))
	request.setAttribute("e",emp);
	
	//java 가 제공하는 객체도 담을 수 있나요
	Map<String,String> hp =  new HashMap<>();
	hp.put("data", "1004");
	
	//request.setAttribute("hpdata", hp);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>기존방식 출력</h3>
	<%=emp%><br>
	<%=emp.getEmpno()%><br>
	<%=emp.getEname()%><br>
	
	<h3>EL : POINT (자바객체에 대해서 직접 접근 불가능 : 담는다(request , session))</h3>
	${emp}<br>
	<h3>해결사(담아라)</h3>
	1.객체 접근 (불가) : request , session 담아서 사용 :GOOD<br>
	EL : ${requestScope.e}<br>
	EL : ${e.empno}<br> <!-- Emp 객체 getter 구현되어 있다면 필드명으로만 getter 자동 호출 -->
	EL : ${requestScope.e.empno}<br>
	
	<h3>JSTL</h3>
	<c:set var="m" value="<%=emp%>" />
	EL을 통해서 출력 : ${m}<br>
	EL 권장하지 않아요 : ${m.getEmpno()}<br>
	
	EL: ${m.empno}<br>
	EL: ${m.ename}<br>
	
	<hr>
	<h3>JSTL script 변수,제어문 가능</h3>
	<c:set var="job" value="농구선수" scope="request" />
	당신의 직업은 : ${job}<br>
	
	<hr>
	<c:set var="vhp" value="<%=hp%>" />
	hp객체 : ${vhp}<br>
	hp객체 : ${vhp.data}<br>
</body>
</html>