<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String id = request.getParameter("txtuserid");
    String pwd = request.getParameter("txtpwd");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
서버확인<br>
당신이 입력한 ID : <%=id %><br>
당싱이 입력한 PWD: <%=pwd %><br>
</body>
</html>