<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>DEPT</title>
	<link href="${pageContext.request.contextPath}/dept/dept.css" type="text/css" rel="stylesheet" />
</head>
<body>
<jsp:include page="/WEB-INF/views/inc/header.jsp" />
<div id="main">
	<div class="top-wrapper clear dept-wrapper">
		<div id="content" class="dept-content">
			<h2>DEPT LIST</h2>

			<form action="${pageContext.request.contextPath}/dept/list.do" method="get">
				<select name="column">
					<option value="dname">부서명</option>
					<option value="loc">지역</option>
					<option value="deptno">부서번호</option>
				</select>

				<input type="text" name="search" placeholder="검색어 입력">

				부서번호:
				<input type="text" name="deptnoList" placeholder="예: 10,20,30">

				<input type="submit" value="검색">
			</form>

			<p class="dept-command">
				<a class="dept-insert-button" href="${pageContext.request.contextPath}/dept/reg.do">부서 등록</a>
			</p>

			<table class="dept-table">
				<thead>
				<tr>
					<th>DEPTNO</th>
					<th>DNAME</th>
					<th>LOC</th>
					<th>관리</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="dept" items="${list}">
					<tr>
						<td>${dept.deptno}</td>
						<td><a href="${pageContext.request.contextPath}/dept/detail.do?deptno=${dept.deptno}">${dept.dname}</a></td>
						<td>${dept.loc}</td>
						<td>
							<a href="${pageContext.request.contextPath}/dept/edit.do?deptno=${dept.deptno}">수정</a>
							<a href="${pageContext.request.contextPath}/dept/del.do?deptno=${dept.deptno}">삭제</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
</body>
</html>