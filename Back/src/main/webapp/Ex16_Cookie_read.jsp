<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	Cookie 는 도메인 별로 (domain) 
-->
<%
	Cookie[] cs = request.getCookies();
	//현재 도메인에 해당하는 모든 쿠키정보를 가지고 와서
	if(cs != null || cs.length > 0){
		for(Cookie c : cs){
			out.print(c.getName() + "<br>");
			out.print(c.getValue() + "<br>");
			out.print(c.getMaxAge() + "<br>"); // -1 (session )
			out.print(c.getName() + "<br>");
		
		}
	}

%>
</body>
</html>