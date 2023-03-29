<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User Page</title>
<style>
	.error {
		color: red;
	}
</style>
</head>
<body>
<h1 class="error">Add User</h1>
<form:form action="doAddUser" method="post" modelAttribute="user">
	
	Name (*) : <form:input path="name"/>
		   <form:errors path="name"/>
		   <br>
		   
	Email (*) : <form:input path="email"/>
				  <form:errors path="email"/>
				  <br>
		   
	<input type="submit" value="Add User"/>
</form:form>
</body>
</html>