<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>게시판 1</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="common/header.jsp" />

<div class="container my-5">
  <h2>게시판 1</h2>

  <table class="table table-hover mt-3">
    <thead class="table-dark">
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>게시판 1 첫 번째 글</td>
        <td>홍길동</td>
        <td>2026-06-01</td>
      </tr>
      <tr>
        <td>2</td>
        <td>Bootstrap 게시판 예제</td>
        <td>김철수</td>
        <td>2026-06-01</td>
      </tr>
    </tbody>
  </table>

  <button class="btn btn-primary">글쓰기</button>
</div>

<jsp:include page="common/footer.jsp" />

</body>
</html>