<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="no-login-navbar.jsp"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Authorization Error</title>
</head>
<body>
	<div class="container"
		style="width: 60%; text-align: center; margin-top: 25vh;">
		<h2>${errorMessage}</h2>
	</div>
</body>
</html>