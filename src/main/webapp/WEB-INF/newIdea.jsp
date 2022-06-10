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
	<h1>Create a New Idea</h1>
	<form:form action="/ideas/new" method="post" modelAttribute="idea">
		<form:label path="content">Content:</form:label>
		<form:errors path="content"/>
		<form:input path="content"/>
		<form:hidden path = "user" value="${user.id}"/>
		<form:errors path="user"/>  
		<input type="submit" value="Submit"/>
	</form:form>
	
</body>
</html>