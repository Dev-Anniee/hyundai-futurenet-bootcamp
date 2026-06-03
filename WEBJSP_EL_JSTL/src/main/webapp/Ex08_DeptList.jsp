<%@page import="kr.or.kosa.dto.Dept"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosa.dao.DeptDao"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//servlet 에서 view에게 데이터가 담겨있는 객체를 전달 
	
	DeptDao dao = new DeptDao();
	List<Dept> deptlist = dao.getAllDeptList();
	//deptlist java 객체 EL 접근 없다
	//담아라
	//session , request
	
	//request.setAttribute("list", deptlist);
	request.setAttribute("list", null);
%>
<html>
<head>
    <title>게시판 목록</title>
    <style>
        table { border-collapse: collapse; width: 600px; margin: 20px auto; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background: #f2f2f2; }
    </style>
</head>
<body>
<h2 style="text-align:center;">부서 목록</h2>
<table>
    <thead>
        <tr>
            <th>부서번호</th>
            <th>부서이름</th>
            <th>부서위치</th>
        </tr>
    </thead>
    <tbody>
       	 <c:forEach var="dept" items="${requestScope.list}">
       	 	  <tr>
       	 	  		<td>
       	 	  			<a href="detail.jsp?deptno=${dept.deptno}">${dept.deptno}</a>
       	 	  		</td>
       	 	  		<td>${dept.dname}</td>
       	 	  		<td>${dept.loc}</td>
       	 	  </tr>
       	 </c:forEach>
       	 <!-- 게시글이 없거나 ... 부서목록이 하나도 없어요 -->
       	 <c:if test="${empty requestScope.list}">
       	 	<tr>
       	 		<td colspan="3">등록된 부서가 없습니다</td>
       	 	</tr>
       	 </c:if>
    </tbody>
</table>
</body>
</html>

<%--
확장자가  DeptList.html

<tr th:each="dept : ${list}">
    <td>
        <a th:href="@{/dept/detail(deptno=${dept.deptno})}"
           th:text="${dept.deptno}">
        </a>
    </td>

    <td th:text="${dept.dname}"></td>

    <td th:text="${dept.loc}"></td>
</tr>

<tr th:if="${#lists.isEmpty(list)}">
    <td colspan="3">
        등록된 부서가 없습니다.
    </td>
</tr>


--%>