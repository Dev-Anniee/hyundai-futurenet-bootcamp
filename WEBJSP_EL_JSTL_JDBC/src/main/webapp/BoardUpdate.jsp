<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
</head>
<body>

<h2>게시글 수정</h2>

<form method="post" action="${pageContext.request.contextPath}/board/update">
    <input type="hidden" name="no" value="${board.no}">

    제목 <br>
    <input type="text" name="title" value="${board.title}"><br><br>

    작성자 <br>
    <input type="text" name="writer" value="${board.writer}"><br><br>

    내용 <br>
    <textarea name="content" rows="10" cols="60">${board.content}</textarea><br><br>

    <button type="submit">수정</button>
    <a href="${pageContext.request.contextPath}/board/detail?no=${board.no}">취소</a>
</form>

</body>
</html>