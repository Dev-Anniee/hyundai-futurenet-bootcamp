<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memo Detail</title>
</head>
<body>
<jsp:include page="/header.jsp" />
<h2>메모 상세</h2>

<p>ID: ${memodetail.id}</p>
<p>Email: ${memodetail.email}</p>
<p>Content: ${memodetail.content}</p>

<p>
    <a href="${pageContext.request.contextPath}/memoupdate.do?id=${memodetail.id}">수정</a>
    <a href="${pageContext.request.contextPath}/memodelete.do?id=${memodetail.id}">삭제</a>
    <a href="${pageContext.request.contextPath}/memolist.do">목록</a>
</p>
</body>
</html>