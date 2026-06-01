<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>게시판 3</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="common/header.jsp" />

<div class="container my-5">
  <h2>게시판 3</h2>

  <div class="row mt-3">
    <div class="col-md-4">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">공지사항 1</h5>
          <p class="card-text">게시판 3의 카드형 글입니다.</p>
          <a href="#" class="btn btn-outline-primary">상세보기</a>
        </div>
      </div>
    </div>
  </div>

  <button class="btn btn-warning mt-3">글쓰기</button>
</div>

<jsp:include page="common/footer.jsp" />

</body>
</html>