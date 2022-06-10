<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First JSP</title>
</head>
<body>
	<h1>${idea.content}</h1>
	<h3>Created by: ${idea.user.name}</h3>
	<c:if test="${user.id == idea.user.id}">
		<a href="/ideas/${idea.id}/edit">Edit</a>
	</c:if>
	<a href="/ideas">Home</a>	
</body>
</html>