<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
</head>
<body>

<h2>게시판 목록</h2>

<a href="${pageContext.request.contextPath}/board/write">글쓰기</a>

<table border="1" width="600">
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
    </tr>

    <c:forEach var="board" items="${boardList}">
        <tr>
            <td>${board.no}</td>
            <td>
                <a href="${pageContext.request.contextPath}/board/detail?no=${board.no}">
                    ${board.title}
                </a>
            </td>
            <td>${board.writer}</td>
        </tr>
    </c:forEach>

    <c:if test="${empty boardList}">
        <tr>
            <td colspan="3">등록된 게시글이 없습니다.</td>
        </tr>
    </c:if>
</table>

</body>
</html>