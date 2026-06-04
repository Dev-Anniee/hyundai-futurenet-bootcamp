<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memo Update</title>
</head>
<body>
<jsp:include page="/header.jsp" />
<h2>메모 수정</h2>

<form action="${pageContext.request.contextPath}/memoupdateok.do" method="post">
    <p>ID: <input type="text" name="id" value="${memoupdate.id}" readonly></p>
    <p>Email: <input type="email" name="email" value="${memoupdate.email}" required></p>
    <p>Content: <textarea name="content" rows="5" cols="40" required>${memoupdate.content}</textarea></p>
    <button type="submit">수정</button>
</form>

<p><a href="${pageContext.request.contextPath}/memolist.do">목록</a></p>
</body>
</html>