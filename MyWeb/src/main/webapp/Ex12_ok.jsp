<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//back 코드 작업 가능 (java 파일 처럼)
	//MS : ASP(vsscript) : ASPX (C#)
	/*
		M : Model (DTO , DAO ,SERVICE) : POJO(순수한 자바객체)
	    V : (view 화면) : JSP(MPA) > login.jsp , loginok.jsp , board.jsp (2차)
	    
	    질문) 모든 처리 jsp 하나요  > 아니요 > 예전에는 jsp(화면 + DB연결 + 로직 + ...) > 무식한 놈
        jsp > service 에서 객체 받아서 for , if 가지고 화면 출력하는 용도
        
	    
	    3차: react ,vue(SPA) > backend (view (x)) : 데이터 서버(REST API)
	    C : 클라이언 요청을 받고 처리 하고 응답 
	      Controller (java 만든 웹전용 파일 : servlet > Http(request, response 객체 ))
	
	    질문: jsp  , servlet  
	    java > 웹 > request ,response > servlet > 웹 > 문제 ( UI 더러워요 ) 
	    
	    UI 전문적인 무었인가 필요 (html + server) > JSP > (UI 처리) > java 어려워요
	    
	    MVC : 잘 하는 것만 해 
	  
	*/
	String id = request.getParameter("userid"); // name = "userid"
	String pwd = request.getParameter("userpwd"); // name = "userpwd"
	/* 	나중에 컨트롤러 기능*/	

	//필요하다면 DB 연결 -> select count ...
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>당신이 입력한 데이터</h3>
	ID: <%=id %><br>
	PWD: <%=pwd %>
</body>
</html>