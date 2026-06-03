<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//?id=hong&pwd=1004&hobby=a&hobby=b&hobby=c
	
    //String[] arr =request.getParameterValues("hobby");


    //request.getParameterValues("hobby"); 같은놈  paramValues
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h3>다중값 처리</h3>
		<c:choose>
			<c:when test="${not empty paramValues.hobby}">
				당신의 선택은
				<!-- Array.foreach(callback(index ,value , array)) -->
				<ul>
					<c:forEach var="hobby" items="${paramValues.hobby}" varStatus="status">
						<li>${hobby} - ${status.index} - ${status.count}</li>
					</c:forEach>
				</ul>
			</c:when>
		</c:choose>
</body>
</html>