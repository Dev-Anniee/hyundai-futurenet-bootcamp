<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memo Insert</title>
</head>
<body>
<jsp:include page="/header.jsp" />
<h2>메모 등록</h2>

<form action="${pageContext.request.contextPath}/memoinsert.do" method="post">
    <p>ID: <input type="text" name="id" required></p>
    <p>Email: <input type="email" name="email" required></p>
    <p>Content: <textarea name="content" rows="5" cols="40" required></textarea></p>
    <button type="submit">등록</button>
</form>

<p><a href="${pageContext.request.contextPath}/memolist.do">목록</a></p>
</body>
</html>