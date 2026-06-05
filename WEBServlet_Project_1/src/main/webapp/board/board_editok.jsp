<%@page import="kr.or.bit.service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    String idx = request.getParameter("idx");
    if (idx == null || idx.trim().equals("")) {
        out.print("<script>alert('글번호 입력 오류'); location.href='board_list.jsp';</script>");
        return;
    }
    BoardService service = BoardService.getInBoardService();
    int result = service.board_Edit(request);
    String msg;
    String url;
    if (result > 0) { msg = "수정 성공"; url = "board_list.jsp"; }
    else { msg = "수정 실패"; url = "board_edit.jsp?idx=" + idx; }
    request.setAttribute("board_msg", msg);
    request.setAttribute("board_url", url);
%>
<jsp:forward page="redirect.jsp"></jsp:forward>
