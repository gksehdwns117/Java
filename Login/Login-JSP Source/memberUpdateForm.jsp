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
		Member member = (Member) request.getAttribute("member");
	%>
	<form action="memberUpdate.do" method="post">
		아이디 : <input type="text" name="id" value="<%=member.getId()%>"
			readonly="readonly"><br> 비 번 : <input type="password"
			name="pw"><br> 이 름 : <input type="text" name="name"
			value="<%=member.getName()%>"><br> 이메일 : <input
			type="text" name="email" value="<%=member.getEmail()%>"><br>
		<input type="submit" value="수정하기"> <input type="reset"
			value="다시하기">
	</form>




</body>
</html>