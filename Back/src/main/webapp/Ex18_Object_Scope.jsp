<%@ page import="kr.or.kosa.Emp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
Tomcat제공(WAS) : 기본적인 내장 객체
1. request  (요청: 클라이언트 정보)
2. response (응답: 특정 페이지 이동 , 정보 출력 , 쿠키 쓰기)
3. application(전역객체: web.xml 자원 접근 read , 전역변수)
4. session(고유객체(접속한 사용자에게 부여되는): 고유한 변수)
5. out (출력)
....

*session객체 (활용도 ^^)
[내부에 session변수 생성]
scope : a.jsp(session.setAtt.."member" 변수 생성)
b.jsp(session.getAtt.. "member" 사용가능)
session 변수는 (**현재 Webapp  폴더 안에 있는 **모든 페이지에서** 사용 가능**)
Life-cycle : 1. session변수는 session객체와 동일
2. session 소멸 : 서버 리부팅 , Tomcat 재실행 , 로그아웃 session.invaildate()

*application 객체
[내부에 application변수 생성 : 전역변수 >>모든 session (접속한 모든 사용자) 공유
scope :   (현재 Webapp  폴더 안에 있는 모든 페이지에서 사용 가능) >> java >> static
Life-cycle : 소멸 (서버 리부팅)   , Tomcat 재실행

*request 객체
[내부에 변수 생성] : request.setAttribute("type","A"); *****~~~~~
scope: 요청 페이지(login.jsp >> 요청당 request 생성 >> scope >> page
예외적으로 ^^^^^^^^^
login.jsp >> ok.jsp(include or forward) request 객체는 ok.jsp 공유된다
-->
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Emp emp = new Emp();
        emp.setEmpno(2000);
        emp.setEname("홍길동");

        // emp 객체는 현재 페이지(scope)에서만 사용 가능
        // 모든 페이지 emp 객체를 사용하고 싶다
        session.setAttribute("empobj", emp);
        //webapp 폴더 안에 있는 모든 페이지 (jsp) 사용 가능

        //포인트 : include, forward - request 객체 공유
        //a.jsp (b.jsp include) c.jsp (d.jsp forward)
        //requset 객체 하나      //requst 객체 하나

        request.setAttribute("who","king");
        // 원칙 : Ex18_Object_Scope.jsp만 request.setAttribue("who", "king"); 사용 가능
        //forward ... Ex19_board.jsp >> request.getAttribute("who") >> "king" 사용 가능
    %>
    <%=emp.getEname()%><hr>
    <%=request.getAttribute("who")%><hr>
    <a href = "Ex19_Object_Session.jsp">Ex19</a>
</body>
</html>
