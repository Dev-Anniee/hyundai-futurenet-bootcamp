<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memo List</title>
</head>
<body>
<h2>Memo List</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Content</th>
    </tr>
    <c:forEach var="memo" items="${requestScope.memoList}">
        <tr>
            <td>${memo.id}</td>
            <td>${memo.email}</td>
            <td>${memo.content}</td>
        </tr>
    </c:forEach>
</table>

<p>
    <a href="${pageContext.request.contextPath}/memo.html">글쓰기</a>
</p>
</body>
</html>
