<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ideas</title>
</head>
<body>
	<h1>Welcome, <c:out value="${user.name}" /></h1>
	<a href="/logout">Logout</a>
	
	<table>
		<thead>
			<tr>
				<th>Idea</th>
				<th>Created By:</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ideas}" var="idea">
			<tr>
				<td><a href="/ideas/${idea.id}"><c:out value="${idea.content}"/></a></td>
				<td>${idea.user.name}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/ideas/new">Create an Idea</a>
</body>
</html>
