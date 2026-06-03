<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//MVC (servlet 요청 받아서 처리)
	
	//지금 jsp
	
	/*
		1. 한글 처리 
		2. 데이터 받기
		3. 업무처리(로직 : 요구사항)
		3.1 DB연결 ... select .... 결과 인증처리
	
	*/
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String pwd = request.getParameter("pwd");
	
	boolean success = false;
	if(uid.equals(pwd)){ //uid = pwd 같다면 회원 인정
		//로그인 성공
		//사용자가 원하는 페이지 제공
		//Member.jsp (회원만 : 인증된 사용자)
		session.setAttribute("memberid", uid);
		//다른 페이지 : session.getAttribute("memberid") > null 이면 ... admin 이면 관리자
		success= true;
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(success == true){
			//로그인 성공
			out.print("<h3>로그인 성공</h3>");
			String user = (String)session.getAttribute("memberid");
			out.print(user + "님 로그인 되었습니다<br>");
			out.print("<a href='Ex20_Session_Member.jsp'>회원전용페이지</a>");
		}else{
	%>
		  <!--로그인 실패 -->
		  <script type="text/javascript">
		  	alert("다시 로그인해");
		  	location.href="Ex20_Session_Login.jsp";
		  	//window.history(-1)
		  </script>
	<%		
		}
	
	%>
</body>
</html>