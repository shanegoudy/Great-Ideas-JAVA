<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First JSP</title>
</head>
<body>
	<h1>Edit ${idea.content}</h1>
	<form:form action="/ideas/${idea.id}/edit" method="post" modelAttribute="idea">
		<input type="hidden" name="_method" value="put">
		<form:label path="content">Content:</form:label>
		<form:errors path="content"/>
		<form:input path="content"/>
		<form:hidden path = "user" value="${user.id}"/>
		<form:errors path="user"/>  
		<input type="submit" value="Update"/>
	</form:form>
	<form action="/ideas/${idea.id}/delete" method="post">
    	<input type="hidden" name="_method" value="delete">
    	<input type="submit" value="Delete">
	</form>
</body>
</html>