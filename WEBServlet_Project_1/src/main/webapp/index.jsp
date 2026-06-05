<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 메인</title>
    <link rel="Stylesheet" href="style/default.css">
</head>
<body>
<%
    pageContext.include("include/header.jsp");
%>
<div id="pageContainer">
    <h3>UI(CSS 공통 페이지 적용 확인)</h3>
    <h3>DB 연결 정보 확인</h3>
<%
    Connection conn = null;
    Context context = new InitialContext();
    DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
    conn = ds.getConnection();
    out.print("DB 연결 여부 : " + conn.isClosed() + "<br>");
    conn.close();
    out.print("DB 연결 여부 : " + conn.isClosed() + "<br>");
%>
</div>
</body>
</html>
