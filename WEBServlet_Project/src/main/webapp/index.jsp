<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 관리 시스템</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" 
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #fdfdfd;
            font-family: 'Segoe UI', sans-serif;
        }
        .hero-section {
            margin-top: 80px;
            text-align: center;
        }
        .hero-title {
            font-size: 2.5rem;
            font-weight: bold;
            color: #5d5c61;
        }
        .hero-subtitle {
            color: #666;
            margin-bottom: 40px;
        }
        .card-option {
            border-radius: 20px;
            padding: 30px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .card-option:hover {
            transform: translateY(-8px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }
        .btn-custom {
            border: none;
            font-weight: 600;
            padding: 12px 20px;
            border-radius: 12px;
        }
        .btn-insert {
            background-color: #a8d8ea;
            color: #333;
        }
        .btn-insert:hover {
            background-color: #91c9db;
        }
        .btn-select {
            background-color: #b8e0d2;
            color: #333;
        }
        .btn-select:hover {
            background-color: #9fcfbf;
        }
    </style>
</head>
<body>
    <!-- header include -->
    <jsp:include page="/WEB-INF/views/header.jsp"/>

    <div class="container hero-section">
        <h1 class="hero-title">사원 관리 메인 페이지</h1>
        <p class="hero-subtitle">원하는 작업을 선택하세요</p>

        <div class="row justify-content-center g-4">
            <div class="col-md-4">
                <div class="card card-option text-center shadow-sm">
                    <h4 class="mb-3">사원 등록</h4>
                    <p class="text-muted">새로운 사원 정보를 등록합니다.</p>
                    <a href="InsertEmpForm.do" class="btn btn-custom btn-insert">사원 입력</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-option text-center shadow-sm">
                    <h4 class="mb-3">부서별 조회</h4>
                    <p class="text-muted">부서별 사원 목록을 조회합니다.</p>
                    <a href="DeptnoListForm.do" class="btn btn-custom btn-select">부서별 사원 조회</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
