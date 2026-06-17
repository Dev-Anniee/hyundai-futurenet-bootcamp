<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  
		서버에 전송 데이터 : text
		서버에 : 텍스트 + 파일 동시에 전송 가능 enctype="multipart/form-data"
	-->
	<form method="post" enctype="multipart/form-data" >
		이름:<input type="text" name="name"><br>
		나이:<input type="text" name="age"><br>
		사진:<input type="file" name="file"><br>
		<input type="submit" value="파일 업로드">
	</form>
</body>
</html>