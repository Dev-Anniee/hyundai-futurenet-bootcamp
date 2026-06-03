<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
</head>
<body>

<h2>게시글 상세</h2>

<p>번호 : ${board.no}</p>
<p>제목 : ${board.title}</p>
<p>작성자 : ${board.writer}</p>
<p>내용</p>
<textarea rows="10" cols="60" readonly>${board.content}</textarea>

<br><br>

<a href="${pageContext.request.contextPath}/board/list">목록</a>
<a href="${pageContext.request.contextPath}/board/update?no=${board.no}">수정</a>
<a href="${pageContext.request.contextPath}/board/delete?no=${board.no}"
   onclick="return confirm('삭제하시겠습니까?')">삭제</a>

</body>
</html>