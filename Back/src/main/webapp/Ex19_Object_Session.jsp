<%@ page import="kr.or.kosa.Emp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  Emp emp = (Emp)session.getAttribute("empobj");
%>
emp 정보 출력 : <%=emp.toString()%>
</body>
</html>
