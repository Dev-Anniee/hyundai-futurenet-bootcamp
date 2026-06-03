<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = request.getParameter("id");
	if(id.equals("hong")){
%>
	<%=id%><img src="images/4.jpg" style="width:100px;height:100px">
<%		
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
	<!--
		request.getParameter("userid") > EL param
		request.getParameter 동일한 역할 >>   param.userid
	  -->
	  <c:if test="${param.id == 'hong'}">
	  	  ${param.id}<img src="images/4.jpg" style="width:100px;height:100px">
	  </c:if>
	  <!--  
	  http://localhost:8080/WEBJSP_EL_JSTL/Ex03_EL_JSTL.jsp?id=hong&age=25
	  -->
	  <c:if test="${param.age > 20}" var="result">
	  	param.value : ${param.age}<br>
	  </c:if>
	 ${result}<br>
</body>
</html>






