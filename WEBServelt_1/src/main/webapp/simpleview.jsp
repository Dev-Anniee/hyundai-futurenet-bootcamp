<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3> jsp 파일은 servlet이 forward한 자원을 사용(request)할 수 있다</h3>
    결과 출력 : <%=request.getAttribute("result")%>
    <hr>
    EL ${requestScope.result}<br>
</body>
</html>
