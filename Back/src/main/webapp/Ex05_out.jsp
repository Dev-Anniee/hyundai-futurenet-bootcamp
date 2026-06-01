<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    /*
        servlet 형
        java 코드 UI
        out.print("<html>")

        jsp 동생
        기본 : html 가지고 있다
        out 객체 활용도 가능


    */

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>스파케티 코드 (서버 출력 +  정적 html 혼합)</h3>
<%
    boolean b = true;
    if(10 > 5){
%>
IF(true)<font color="red"><%=b%></font>
<%
}else{
    b = false;
%>
IF(false)<font color="blue"><%=b%></font>
<%
    }
%>
    <h3> JSP out 객체 (sevelet 시절에 사용했던)</h3>
    <%
        boolean b2 = true;
        if(10 > 5){
          out.print("IF(true)<font color='red'>"+b2+"</font>");
        }else {
          b2 = false;
            out.print("IF(true)<font color='blue'>"+b2+"</font>");
        }
    %>
</body>
</html>






