<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memo List</title>
</head>
<body>
<jsp:include page="/header.jsp" />
<h2>메모 목록</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Content</th>
    </tr>
    <c:if test="${empty memoList}">
        <tr>
            <td colspan="3">등록된 메모가 없습니다.</td>
        </tr>
    </c:if>
    <c:forEach var="memo" items="${memoList}">
        <tr>
            <td><a href="${pageContext.request.contextPath}/memodetail.do?id=${memo.id}">${memo.id}</a></td>
            <td>${memo.email}</td>
            <td>${memo.content}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>