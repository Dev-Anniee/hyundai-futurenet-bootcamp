<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>
</head>
<body>

<h2>글쓰기</h2>

<form method="post" action="${pageContext.request.contextPath}/board/write">
    제목 <br>
    <input type="text" name="title"><br><br>

    작성자 <br>
    <input type="text" name="writer"><br><br>

    내용 <br>
    <textarea name="content" rows="10" cols="60"></textarea><br><br>

    <button type="submit">등록</button>
    <a href="${pageContext.request.contextPath}/board/list">목록</a>
</form>

</body>
</html>