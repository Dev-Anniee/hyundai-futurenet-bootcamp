<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>게시판 2</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="common/header.jsp" />

<div class="container my-5">
  <h2>게시판 2</h2>

  <div class="list-group mt-3">
    <a href="#" class="list-group-item list-group-item-action">자유게시판 글입니다.</a>
    <a href="#" class="list-group-item list-group-item-action">두 번째 게시글입니다.</a>
    <a href="#" class="list-group-item list-group-item-action">Bootstrap list-group 예제입니다.</a>
  </div>

  <button class="btn btn-success mt-3">글쓰기</button>
</div>

<jsp:include page="common/footer.jsp" />

</body>
</html>