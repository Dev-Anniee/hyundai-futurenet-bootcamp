<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 쓰기</title>
    <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
    <link rel="Stylesheet" href="../style/default.css" />
    <script type="text/javascript">
        function check() {
            if (!bbs.subject.value) { alert("제목을 입력하세요"); bbs.subject.focus(); return false; }
            if (!bbs.writer.value) { alert("이름을 입력하세요"); bbs.writer.focus(); return false; }
            if (!bbs.pwd.value) { alert("비밀번호를 입력하세요"); bbs.pwd.focus(); return false; }
            document.bbs.submit();
        }
    </script>
</head>
<body>
<% pageContext.include("/include/header.jsp"); %>
<div id="pageContainer">
    <div style="padding-top: 25px; text-align: center">
        <form name="bbs" action="board_writeok.jsp" method="POST">
            <table width="95%" border="2" align="center">
                <tr><td width="20%" align="center">제목</td><td width="80%" align="left"><input type="text" name="subject" size="40"></td></tr>
                <tr><td width="20%" align="center">글쓴이</td><td width="80%" align="left"><input type="text" name="writer" size="40"></td></tr>
                <tr><td width="20%" align="center">이메일</td><td width="80%" align="left"><input type="text" name="email" size="40"></td></tr>
                <tr><td width="20%" align="center">홈페이지</td><td width="80%" align="left"><input type="text" name="homepage" size="40" value="http://"></td></tr>
                <tr><td width="20%" align="center">글내용</td><td width="80%" align="left"><textarea rows="10" cols="60" name="content" class="ckeditor"></textarea></td></tr>
                <tr><td width="20%" align="center">비밀번호</td><td width="80%" align="left"><input type="password" name="pwd" size="20"></td></tr>
                <tr><td width="20%" align="center">첨부파일</td><td width="80%" align="left"><input type="file" name="filename"></td></tr>
                <tr><td colspan="2" align="center"><input type="button" value="글쓰기" onclick="check();" /> <input type="reset" value="다시쓰기" /></td></tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
