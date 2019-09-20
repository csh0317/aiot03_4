<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet">

</head>
<body>
	<center>
	<form action="InsertServlet" class='box'>
		<h1>New User</h1>
			<input type="text" name="name" placeholder="Username"><br>
			<input type="password" name="password" placeholder="Password"><br>
			<input type="password" name="password2" placeholder="Enter password again"><br>
			<input type="submit" name="" value="Send"><br>
			<a href='index.jsp'>我已經有帳號了</a>
	</form>
	</center>
</body>
</html>