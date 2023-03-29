<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<%@ page isELIgnored="false" %>
				<!DOCTYPE html>
				<html lang="en">

				<head>
					<meta charset="UTF-8">
					<meta http-equiv="X-UA-Compatible" content="IE=edge">
					<meta name="viewport" content="width=device-width, initial-scale=1.0">
					<title>List Page</title>
					<style>
						table,
						th,
						td {
							border: 1px solid black;

						}

						.paging-area {
							margin-top: 10px;
						}
					</style>
				</head>

				<body>
					<a href="#">Add Category</a><br>
					<a href="${pageContext.request.contextPath}/addUser">Add User</a>

					<form action="doSearch" method="get">
						<input name="keyword" placeholder="name, email" /><input type="submit" value="search" />
					</form><br>

					<table width="100%">
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Email</th>
							<th></th>
						</tr>
						<c:forEach items="${listUser}" var="item">
							<tr>
								<td>${item.id }</td>
								<td>${item.name }</td>
								<td>${item.email }</td>
								<td><a href="update?id=${item.id }">Update</a>
									<a href="${pageContext.request.contextPath }/delete?id=${item.id }">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="paging-area">
						<c:import url="paging.jsp"></c:import>
					</div>
				</body>

				</html>