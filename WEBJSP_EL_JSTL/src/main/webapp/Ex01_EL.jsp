<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   /* 

		Expression Language의 약자 (EL)
		JSP 2.0에서 새롭게 추가된 스크립트 언어
		기존의 Script tag의 표현식(정보: 스크립트 릿) tag에서 업그레이드된 버전 ( ${ 정보 } )
		
		[ 주요 특징 ]
		1) JSP 속성영역 (request,  response, session, application) 저장된 속성 객체의 property를 출력한다 
		2) 리터럴 데이터, 다양한 연산결과 출력이 가능하다 
		3) JSTL과 연동이 가능하다  (EL 출력을 담당하는  script > 변수 , 제어문 ,논리 제어 방법이 없어요)
		
		EL(출력 표현식) : JSP 페이지에서 사용되는 출력 전용 스크립트 언어 (화면...) 
		POINT) **********[서버]에서 해석되는 스크립트언어*************
		이유 : 스파케티 코드 % + UI(정적) > 유지보수 어려움 > 코드의 가독성(해석)
		
		문제) EL & JSTL 만들어서 html 과 잘 놀고 ... JSP 사용가능 
		
		EL : tomcat 서버가 내장하고 있는 자원 (별도의 참조 필요 없다)
		JSTL : 다운로드 해서 (빌드 path....)  WEB-INF > lib > 복사 붙여넣기 > jar 파일
		
		
		EL 객체를 가지고 있다
		1. param       -> requset.getParameter
		2. paramValues
		3. requestScope  -> request
		4. sessionScope  -> session
		5. applicationScope 
	*/
%>  
<%
	//EL 출력 전담하는 script ....  java객체 생성 ...(x)
	
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("userid");
	
	//값을 모든 페이지 공유
	//session  변수
	session.setAttribute("userid", "king");
	
	//현재 ... include , forward 페이지만 고유
	//request  변수
	request.setAttribute("name", "hong");
	request.setAttribute("hobby", "java");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>기존 JSP 출력</h3>
	<b><%=id%></b><br>
	<b><%=request.getAttribute("name")%></b><br>
	<b><%=request.getAttribute("hobby")%></b><br>
	<b><%=session.getAttribute("userid")%></b><br>
	<h3>기존 출력이 아니고 EL 출력</h3>
	<%=100+200+300%>
	EL:${100+200+300}<br>
	EL:${1==1}<br>
	EL:${false}<br>
	EL:${!false}<br>
	EL:${empty x}<br>
	<hr>
	${requestScope.name}<br>
	${sessionScope.userid}<br>
	${hobby}<br>
</body>
</html>





