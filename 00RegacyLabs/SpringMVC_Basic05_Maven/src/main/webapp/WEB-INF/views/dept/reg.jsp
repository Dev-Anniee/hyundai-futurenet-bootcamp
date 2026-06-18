<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DEPT 등록</title>
<link href="${pageContext.request.contextPath}/dept/dept.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<div id="main">
		<div class="top-wrapper clear dept-wrapper">
			<div id="content" class="dept-content">
				<h2>DEPT 등록</h2>
				<form class="dept-form" action="${pageContext.request.contextPath}/dept/reg.do" method="post">
					<p>
						<label>DEPTNO</label>
						<input type="number" name="deptno" required>
					</p>
					<p>
						<label>DNAME</label>
						<input type="text" name="dname" maxlength="14">
					</p>
					<p>
						<label>LOC</label>
						<input type="text" name="loc" maxlength="13">
					</p>
					<div class="dept-command">
						<button class="dept-insert-button" type="submit">등록</button>
						<a class="dept-insert-button" href="${pageContext.request.contextPath}/dept/list.do">목록</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
</body>
</html>
