<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DEPT 상세</title>
<link href="${pageContext.request.contextPath}/dept/dept.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<div id="main">
		<div class="top-wrapper clear dept-wrapper">
			<div id="content" class="dept-content">
				<h2>DEPT 상세</h2>
				<table class="dept-detail-table">
					<tr>
						<th>DEPTNO</th>
						<td>${dept.deptno}</td>
					</tr>
					<tr>
						<th>DNAME</th>
						<td>${dept.dname}</td>
					</tr>
					<tr>
						<th>LOC</th>
						<td>${dept.loc}</td>
					</tr>
				</table>
				<p class="dept-command">
					<a class="dept-insert-button" href="${pageContext.request.contextPath}/dept/edit.do?deptno=${dept.deptno}">수정</a>
					<a class="dept-insert-button" href="${pageContext.request.contextPath}/dept/list.do">목록</a>
				</p>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
</body>
</html>
