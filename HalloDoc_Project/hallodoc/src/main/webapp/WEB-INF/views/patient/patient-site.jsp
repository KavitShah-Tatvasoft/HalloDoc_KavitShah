<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/css/patient-site.css" />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">
<title>Patient-Site</title>
</head>
<body>
	<div class="main-body">
		<img src="<c:url value="/resources/images/doc-img.jpg" />" alt="background"
			class="background-img">
		<div class="absolute-pos">
			<button type="button" class="theme-btn" id="moon-btn"
				onclick="changeThemeDarkBright(this)"></button>
			<label for="moon-btn"> <img
				src="<c:url value="/resources/images/moon-blue.svg" />" alt="moon" class="moon"
				id="moon-id">
			</label>

			<button type="button" class="theme-btn" id="sun-btn"
				onclick="changeThemeDarkBright(this)"></button>
			<label for="sun-btn"> <img
				src="<c:url value="/resources/images/sun-blue.svg" />" alt="sun" class="sun"
				id="sun-id">
			</label>
		</div>

		<div class="select-flex">
			<img src="<c:url value="/resources/images/logo-halloDoc.png" />" alt="HalloDoc">

			<a href="< c:url value='/patient_submit_request' />" type="button"
				class="div-width blue-clr"> 
				Submit a Request 
			</a> 
			
			<a href="< c:url value='/patient_login' />" class="div-width indigo-clr"
				type="button"> 
				Registered Patients 
			</a>
		</div>

		<footer>
			<a href="#">Terms of Condition</a> &nbsp;|&nbsp;<a href="#">
				Privacy Policy </a>
		</footer>
	</div>

	<script src="<c:url value="/resources/js/darktheme.js" />"></script>
</body>
</html>