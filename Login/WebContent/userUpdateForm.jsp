<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Model.User user = (Model.User) request.getAttribute("user");
	%>
	<form action="userUpdate.do" method="post">
		아이디 : <input type="text" name="id" value="<%=user.getId()%>" readonly="readonly"><br>
		비 번 : <input type="password" name="pw"><br> 
		이 름 : <input type="text" name="name" value="<%=user.getName()%>"><br>
		이메일 : <input type="text" name="email" value="<%=user.getEmail()%>"><br>
		<input type="submit" value="수정하기"> <input type="reset" value="다시하기">
	</form>
</body>
</html>