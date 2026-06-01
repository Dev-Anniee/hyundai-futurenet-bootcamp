<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!--
공통 페이지

JSP:include  현재 페이지에서 공통의 다른 페이지를 불러와서 사용

-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<b>SITE MAIN</b>
<hr>
<jsp:include page="/common/sub.jsp" />
<hr>
<b>SITE BOTTOM</b>
</body>
</html>