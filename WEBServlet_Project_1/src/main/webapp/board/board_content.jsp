<%@page import="kr.or.bit.dto.Reply"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.bit.dto.Board"%>
<%@page import="kr.or.bit.service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
    <link rel="Stylesheet" href="<%=request.getContextPath()%>/style/default.css" />
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String idx = request.getParameter("idx");
    if (idx == null || idx.trim().equals("")) {
        response.sendRedirect("board_list.jsp");
        return;
    }
    idx = idx.trim();
    String cpage = request.getParameter("cp");
    String pagesize = request.getParameter("ps");
    if (cpage == null || cpage.trim().equals("")) cpage = "1";
    if (pagesize == null || pagesize.trim().equals("")) pagesize = "5";

    BoardService service = BoardService.getInBoardService();
    boolean isread = service.addReadNum(idx);
    if (isread) System.out.println("조회수 증가 : " + isread);
    Board board = service.content(Integer.parseInt(idx));
    pageContext.include("/include/header.jsp");
%>
<div id="pageContainer">
    <div style="padding-top: 30px; text-align: center">
        <center>
            <b>게시글 내용</b>
            <table width="80%" border="1">
                <tr>
                    <td width="20%" align="center"><b>글번호</b></td>
                    <td width="30%"><%=idx%></td>
                    <td width="20%" align="center"><b>작성일</b></td>
                    <td><%=board.getWritedate()%></td>
                </tr>
                <tr>
                    <td width="20%" align="center"><b>글쓴이</b></td>
                    <td width="30%"><%=board.getWriter()%></td>
                    <td width="20%" align="center"><b>조회수</b></td>
                    <td><%=board.getReadnum()%></td>
                </tr>
                <tr>
                    <td width="20%" align="center"><b>홈페이지</b></td>
                    <td><%=board.getHomepage()%></td>
                    <td width="20%" align="center"><b>첨부파일</b></td>
                    <td><%=board.getFilename()%></td>
                </tr>
                <tr>
                    <td width="20%" align="center"><b>제목</b></td>
                    <td colspan="3"><%=board.getSubject()%></td>
                </tr>
                <tr height="100">
                    <td width="20%" align="center"><b>글내용</b></td>
                    <td colspan="3">
<%
    String content = board.getContent();
    if (content != null) content = content.replace("
", "<br>");
    out.print(content);
%>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="board_list.jsp?cp=<%=cpage%>&ps=<%=pagesize%>">목록가기</a> |
                        <a href="board_edit.jsp?idx=<%=idx%>&cp=<%=cpage%>&ps=<%=pagesize%>">편집</a> |
                        <a href="board_delete.jsp?idx=<%=idx%>&cp=<%=cpage%>&ps=<%=pagesize%>">삭제</a> |
                        <a href="board_rewrite.jsp?idx=<%=idx%>&cp=<%=cpage%>&ps=<%=pagesize%>&subject=<%=board.getSubject()%>">답글</a>
                    </td>
                </tr>
            </table>
            <form name="reply" action="board_replyok.jsp" method="POST">
                <input type="hidden" name="idx" value="<%=idx%>">
                <input type="hidden" name="userid" value="">
                <table width="80%" border="1">
                    <tr><th colspan="2">댓글 쓰기</th></tr>
                    <tr>
                        <td align="left">
                            작성자: <input type="text" name="reply_writer"><br />
                            내용: <textarea name="reply_content" rows="2" cols="50"></textarea>
                        </td>
                        <td align="left">
                            비밀번호: <input type="password" name="reply_pwd" size="4">
                            <input type="button" value="등록" onclick="reply_check()">
                        </td>
                    </tr>
                </table>
            </form>
            <script type="text/javascript">
                function reply_check() {
                    var frm = document.reply;
                    if (frm.reply_writer.value == "" || frm.reply_content.value == "" || frm.reply_pwd.value == "") {
                        alert("댓글 내용, 작성자, 비밀번호를 모두 입력해야 합니다");
                        return false;
                    }
                    frm.submit();
                }
                function reply_del(frm) {
                    if (frm.delPwd.value == "") {
                        alert("비밀번호를 입력하세요");
                        frm.delPwd.focus();
                        return false;
                    }
                    frm.submit();
                }
            </script>
            <br>
<%
    List<Reply> replylist = service.replyList(idx);
    if (replylist != null && replylist.size() > 0) {
%>
            <table width="80%" border="1">
                <tr><th colspan="2">댓글 목록</th></tr>
<%
        for (Reply reply : replylist) {
%>
                <tr align="left">
                    <td width="80%">
                        [<%=reply.getWriter()%>] : <%=reply.getContent()%>
                        <br> 작성일: <%=reply.getWritedate().toString()%>
                    </td>
                    <td width="20%">
                        <form action="boardreply_deleteOk.jsp" method="POST" name="replyDel">
                            <input type="hidden" name="no" value="<%=reply.getNo()%>">
                            <input type="hidden" name="idx" value="<%=idx%>">
                            password : <input type="password" name="delPwd" size="4">
                            <input type="button" value="삭제" onclick="reply_del(this.form)">
                        </form>
                    </td>
                </tr>
<%
        }
%>
            </table>
<%
    }
%>
        </center>
    </div>
</div>
</body>
</html>
