<%@page import="java.util.ArrayList"%>
<%@page import="Model.User"%>
<%@page import="java.util.List"%>
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
	ArrayList<User> userlist = (ArrayList<User>)request.getAttribute("userlist");
	%>

<table border="1">
	<tr>
		<th>아이디</th>
		<th>이   름</th>
		<th>이메일</th>
	</tr>
	<% 
	for(User u : userlist){
	%>

	<tr>
		<td><%=u.getId() %></td>
		<td><%=u.getName() %></td>
		<td><%=u.getEmail() %></td>
	</tr>
	<%
	}
	%>

</table>
</body>
</html>