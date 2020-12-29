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
ArrayList<Member> memberlist = (ArrayList<Member>)request.getAttribute("memberlist");
%>

<table>
	<tr>
		<th>아이디</th>
		<th>이   름</th>
		<th>이메일</th>
	</tr>
	<% 
	for(Member m : memberList){
	%>

	<tr>
		<td><%=m.getId() %></td>
		<td><%=m.getName() %></td>
		<td><%=m.getEmail() %></td>
	</tr>
	
	<%
	}
	%>

</table>
</body>
</html>