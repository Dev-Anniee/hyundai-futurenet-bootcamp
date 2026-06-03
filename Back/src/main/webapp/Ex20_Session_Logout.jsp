<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		session.invalidate();  //네이버 다음 로그아웃 버튼 클릭
	%>
	<script type="text/javascript">
		location.href="Ex20_Session_Login.jsp";
	</script>
</body>
</html>