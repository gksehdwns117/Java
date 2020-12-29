<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 로그인창 </title>
</head>
<body>
	<form action="login.do">
	아이디 : <input type="text" name="id"><br>
	비   번 &nbsp;&nbsp;: <input type="password" name="pw"><br>
	<input type="submit" value="로그인">
	</form>
	
	<input type="button" value="회원가입"
	onclick="location.href='joinForm.do'">
</body>
</html>