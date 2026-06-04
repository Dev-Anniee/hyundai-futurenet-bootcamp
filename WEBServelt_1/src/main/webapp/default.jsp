<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <h3>요청 처리하기</h3>
    <%= request.getContextPath() %><br> <!-- localhost:8080/Web/simple -->
    ${pageContext.request.contextPath}  <!-- /Web  -->
    <hr>
    <a href="<%=request.getContextPath()%>/simple">simple 요청(GET)</a><br>
    <a href="<%=request.getContextPath()%>/simple?type=date">simple 날짜 요청(GET)</a><br>
    <a href="<%=request.getContextPath()%>/simple?type=abcd">simple 비정상 요청(GET)</a><br>

    <hr>
    <a href="${pageContext.request.contextPath}/Board?cmd=boardlist">게시판 목록보기</a><br>
    <a href="${pageContext.request.contextPath}/Board?cmd=boardwrite">게시판 글쓰기</a><br>
    <a href="${pageContext.request.contextPath}/Board">cmd null error 페이지</a><br>
    <a href="${pageContext.request.contextPath}/Board?cmd=login">보안페이지 view</a><br>
</body>
</html>
