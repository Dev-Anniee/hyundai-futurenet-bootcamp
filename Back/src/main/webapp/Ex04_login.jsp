<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    /*
    1. 한글처리
    2. 데이터 받기
    3. 로직처리 (비지니스) > DB작업(CRUD)

    내장객체(WAS 톰켓)
    1. request (클라이언트 정보를 담고 있는 객체 : 요청 페이지당 한개 (웹서버 생성))
    2. response (웹서버 ... 응답객체)
    3. session (클라이언트 마다 고유하게 생성되는 고유객체 : 접속자 100명 100개 객체 생성)
    4. application (전역자원 : 모든 접속자(session) 공유하는 객체)
    5. out (출력객체 out.print)

    5개의 객체는 JSP new 없이 그냥 사용 (미리 생성되어 있느니까)
    */
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");

	/*
		model1 .... MVC 흉내
		JSP 요청...
		>Model (DTO ,DAO)
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = new MemberDto();

		memberDto.setId(id);

		int result = memberDao.insertData(memberDto);

		클라이언트에게 가입성공 메시지 전달


	*/
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%=id %>
</body>
</html>