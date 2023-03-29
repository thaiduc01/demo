<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update User Page</title>
<style>
	.readonly{
		background-color: grey;
	}
</style>
</head>
<body>
<h1>Update User</h1>
<form:form action="doUpdateUser" method="post" modelAttribute="user">
	Id : <form:input path="id" readonly="true" cssClass="readonly"/><br>
	
	Name (*) : <form:input path="name"/>
		   <form:errors path="name" />
		   <br>
		   
	Email (*) : <form:input path="email"/>
				  <form:errors path="email" cssClass="error"/>
				  <br>
		   
	<input type="submit" value="Update Book"/>
</form:form>
</body>
</html>