<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Paging Page</title>
<style type="text/css">
.paging a {
	border: 0.5px #efefef;
 	border-radius: 20px;
 	background-color: #efefef;
 	color: #212529;
 	padding: 0.2em 0.4em;
 	transition: 0.4s;
}
.paging a:hover {
	border: 0.5px #212529;
	background-color: #212529;
	color: #fff;
	transition: 0.4s;
}
</style>
</head>
<body>
	<span class="paging"> 
		<span class="total-items"> Total Items: ${totalItems} </span> 
		<br><br>
		<c:choose>
			<c:when test="${currentPage>1 }">
				<a href="${pageContext.request.contextPath}/${path}/1">First</a>
				<a
					href="${pageContext.request.contextPath}/${path}/${currentPage-1}">Previous</a>
			</c:when>
			<c:otherwise>
				<button><span>First</span></button>
				<button><span>Previous</span></button>
			</c:otherwise>
		</c:choose> 
		<c:choose>
			<c:when test="${currentPage<totalPage}">
				<a href="${pageContext.request.contextPath }/${path}/${currentPage +1}">Next</a>
				<a href="${pageContext.request.contextPath }/${path}/${totalPage}">Last</a>
			</c:when>
			<c:otherwise>
				<button><span>Next</span></button>
				<button><span>Last</span></button>
			</c:otherwise>
		</c:choose>
	</span>

</body>
</html>