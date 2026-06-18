<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DEPT 수정</title>
<link href="${pageContext.request.contextPath}/dept/dept.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<div id="main">
		<div class="top-wrapper clear dept-wrapper">
			<div id="content" class="dept-content">
				<h2>DEPT 수정</h2>
				<form class="dept-form" action="${pageContext.request.contextPath}/dept/edit.do" method="post">
					<p>
						<label>DEPTNO</label>
						<input type="number" name="deptno" value="${dept.deptno}" readonly>
					</p>
					<p>
						<label>DNAME</label>
						<input type="text" name="dname" value="${dept.dname}" maxlength="14">
					</p>
					<p>
						<label>LOC</label>
						<input type="text" name="loc" value="${dept.loc}" maxlength="13">
					</p>
					<div class="dept-command">
						<button class="dept-insert-button" type="submit">수정</button>
						<a class="dept-insert-button" href="${pageContext.request.contextPath}/dept/list.do">목록</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
</body>
</html>
