<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String idx = request.getParameter("idx");
    if (idx == null || idx.trim().equals("")) {
%>
<script>alert("글번호가 넘어오지 않았습니다"); history.back();</script>
<% return; } %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 삭제</title>
    <link rel="Stylesheet" href="<%=request.getContextPath()%>/style/default.css" />
    <script type="text/javascript">
        function delCheck() {
            if (del.pwd.value == "") { alert("비밀번호를 입력해야 합니다"); del.pwd.focus(); return false; }
            if (del.pwd.value.length > 8) { alert("비밀번호는 8자리 이내입니다"); del.pwd.select(); return false; }
            document.del.submit();
        }
    </script>
</head>
<body>
<% pageContext.include("/include/header.jsp"); %>
<div id="pageContainer"><div style="padding-top: 25px; text-align: center">
    <form name="del" method="POST" action="board_deleteok.jsp"><center>
        비밀번호 : <input type="password" name="pwd">
        <input type="hidden" name="idx" value="<%=idx %>">
        <hr width="500" color="gold">
        <input type="button" value="삭제" onclick="delCheck();">
        <input type="reset" value="다시">
    </center></form>
</div></div>
</body>
</html>
