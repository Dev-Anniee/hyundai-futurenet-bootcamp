<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--
Server (웹 서버(WAS)
1. server memory : session객체 (사이트 접속하는 [사용자]마다 [고유]하게 부여하는 메모리(객체)) > 서버 리부팅,개발코드변경 > 소멸 > 생성
2. server memory : application객체 (사이트 접속하는 [모든 사용자]에게 부여하는 메모리(객체)) > 서버 리부팅,개발코드변경 > 소멸 > 생성

session 객체 : 서버(Web)에 접속한 사용자 마다 고유하게 부여되는 객체
고유성 보장 (key 값 ) >> 각각의 session객체마다 이름표 > key값 > 클라이언트에도 전달
활용) 클라이언트마다 고유하게 부여 (session 객체)
>> 로그인ID , 장바구니(사용자마다 다른) , 사용자의 정보 (각각 다른 정보)  , 사용자마다 접속한 시간, 마지막 접속 시간

1. http://192.168.0.29:8090/WebJSP/_Session.jsp 서버에 요청 보내기
2. sessionID (key) 생성 ->  session -> sessionID response 해요(Cookie)
2.1  JSESSIONID : 43AE93C6D0A5CD99BBCF73149DC1FB14
3. 동기화 (너랑 나랑 친구다)

ex) 접속자 1000명이 Web Server에는 key 1000개 만들어지고 key이름표로 달고있는 session객체도 1000개 생성


ex) Client  로그 아웃 버튼을 클릭 >> 서버는 어떤한 작업을 수행 할까요 >> 로그아웃 판단 >> key 가지고 와서
>> key (이름표) >> 객체를 찿아서 >> abandon (소멸) >> invalidate
-->

<%
    Date time = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>session 객체 정보</h3>
    session 객체 식별값 : <%=session.getId()%>
    <hr>
    <%
        time.setTime(session.getCreationTime());
    %>
    [session 생성된 시간] : <%= formatter.format(time) %>
    <hr>
    <%
        time.setTime(session.getLastAccessedTime());
    %>
    [session 마지막 접속 시간 client] : <%= formatter.format(time) %>
</body>
</html>
