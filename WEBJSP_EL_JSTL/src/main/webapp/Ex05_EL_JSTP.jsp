<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="i" begin="0" end="10">
		<c:set var="sum" value="${sum+i}" />
		${i}<br>
	</c:forEach>
	sum누적: ${sum}<br>
	
	<ul>
		<c:forEach var="i" begin="1"  end="9">
			<li>3*${i}=${3*i}</li>
		</c:forEach>
	</ul>
	
	<h3>foreach choose</h3>
	<c:forEach var="i" begin="2"  end="9">
		<c:forEach var="j" begin="0" end="9">
			<c:choose>
				<c:when test="${j == 0}">
						${i}단<br>
				</c:when>
				<c:otherwise>
					    ${i}*${j}=${i*j}&nbsp;&nbsp;&nbsp;
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<br>
	</c:forEach>
	<%
		//JAVA 제공하느 API 는 EL & JSTL 직접 사용 안되요 
		//***담아서 .... EL&JSTL 출력
		int[] arr = new int[]{10,20,30,40,50};
		for(int i : arr){
			out.print("출력값 <b><i>" +  i + "</i></b><br>");
		}
	
		//KEY POINT 객체 직접 접근 불가 (EL ...)
		//담아라 
		request.setAttribute("iarr", arr);
	%>
	<c:forEach var="arr" items="${iarr}">
		배열값: ${arr}<br>
	</c:forEach>
	<h3>이쁘지 않아요 쓰지 마세요</h3>
	<c:set var="array" value="<%=arr%>">
	
	</c:set>
</body>
</html>