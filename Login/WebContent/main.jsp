<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3><%=session.getAttribute("id") %>님 환영합니다</h3> <br>

<input type="button" value="회원수정"
onclick="location.href='userUpdateForm.do?id=<%=session.getAttribute("id") %>'">

<input type="button" value="모든회원정보보기" onclick="location.href='userList.do'">

</body>
</html>