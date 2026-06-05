<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
    <div class="title">
        <a href="<%= request.getContextPath() %>/">DEMO WEBSITE</a>
    </div>
    <div class="links">
        <a href="#"><%= request.getContextPath() %></a>
        <a href="#">추후 등록</a>
        <a href="#">추후 로그아웃</a>
    </div>
</div>
<div id="menu">
    <div>
        <ul>
            <li><a href="<%= request.getContextPath() %>/board/board_list.jsp">BOARD LIST</a></li>
            <li><a href="<%= request.getContextPath() %>/board/board_write.jsp">BOARD WRITE</a></li>
        </ul>
    </div>
</div>
<div style="text-align:right;margin-top:1px;border:solid 1px;padding:5px">
    [ TOTAL : 명 ]
    [ CURRENT : 명 ]
</div>
