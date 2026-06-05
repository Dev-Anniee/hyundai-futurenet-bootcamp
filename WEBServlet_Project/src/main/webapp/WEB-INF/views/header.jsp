<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
.navbar {
	background-color: #c9c9ff !important; /* 파스텔 라벤더 */
}

.navbar-brand {
	font-weight: bold;
	color: #333 !important;
}

.navbar-nav .nav-link {
	color: #333 !important;
	margin-left: 10px;
	transition: background-color 0.3s ease, color 0.3s ease;
	border-radius: 8px;
	padding: 6px 12px;
}

.navbar-nav .nav-link:hover {
	background-color: #e8e8ff;
	color: #000 !important;
}
</style>

<!-- 상단 네비게이션 바 -->
<nav class="navbar navbar-expand-lg shadow-sm">
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp">사원 관리 시스템</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a class="nav-link"
					href="InsertEmpForm.do">사원 입력</a></li>
				<li class="nav-item"><a class="nav-link"
					href="DeptnoListForm.do">부서별 사원
						조회</a></li>
			</ul>
		</div>
	</div>
</nav>
