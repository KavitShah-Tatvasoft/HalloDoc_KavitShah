<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/patient-login-forgetpassword.css" />">
<script src="<c:url value="/resources/js/login.js" />"></script>
<title>Patient Login</title>
</head>
<body class="relative-position" onload="showToast(${showalert})">
	<div class="moon-flex-1">

		<button type="button" onclick="changeThemeDarkBright(this)"
			class="theme-btn" id="moon-btn"></button>
		<label for="moon-btn"> <img
			src="<c:url value="/resources/images/moon.svg" />" alt="moon"
			class="moon" id="moon-id">
		</label>

		<button type="button" onclick="changeThemeDarkBright(this)"
			class="theme-btn" id="sun-btn"></button>
		<label for="sun-btn"> <img
			src="<c:url value="/resources/images/sun-blue.svg" />" alt="sun"
			class="sun" id="sun-id">
		</label>
	</div>

	<div class="container d-flex flex-column align-items-center">
		<img src="<c:url value="/resources/images/doc-img.jpg" />"
			alt="Doctor-Image" class="back-img-patient">

		<div>
			<img src="<c:url value='/resources/images/logo-halloDoc.png'/>"
				alt="halloDoc logo" class="main-logo">
		</div>

		<div class="relative-position add-margin-top">

			<a type="button" onclick="history.go(-1)">
				<div class="back-btn-top ">
					<img src="<c:url value="/resources/images/chevron-left.svg" />"
						alt=""> Back
				</div>
			</a>

			<h2 class="Login-text-patient mb-4" id="Login-text-id">Login To
				Your Account</h2>

			<form action="loginValidator" method="post">

				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1" id="floatingInput"
						placeholder="Username" name="username"> <label
						for="floatingInput" class="username-clr">Username</label> <img
						src="<c:url value="/resources/images/person-circle.svg" />"
						alt="user-png" class="user-btn-img">
				</div>

				<div class="form-floating mb-3 inp">
					<input type="password" class="form-control input-1"
						id="floatingPassword" name="password" placeholder="Password">
					<label for="floatingPassword">Password</label> <a href="#"
						onclick="showPass()"><img
						src="<c:url value="/resources/images/password-eye.svg" />"
						alt="view pass" class="open-eye" id="open-eye-id"></a> <a
						href="#" onclick="hidePass()"><img
						src="<c:url value="/resources/images/password-eye-slash.svg" />"
						alt="hide pass" class="close-eye" id="close-eye-id"></a>
				</div>

				<div style="color: red; margin-bottom: 15px">${Error}</div>

				<!--                 <a href="patient-dashboard.html"> -->
				<!--                     <div class="form-floating"> -->
				<!--                         <div class="submit-btn" type="submit" role="button">Log In</div> -->
				<!--                     </div> -->
				<!--                 </a> -->

				<button class="submit-btn">Log In</button>
			</form>
		</div>

		<p class="mt-3">
			<a href="<c:url value="/forgetPasswordPatient" />"
				class="forget-pass">Forget Password?</a>
		</p>


		<footer class="bottom-txt-patient">
			<a href="#" class="bottom-txt-1" id="term">Terms of Condition</a> <span
				class="bottom-txt-1" id="of">|</span> <a href="#"
				class="bottom-txt-1" id="privacy">Privacy Policy</a>
		</footer>

	</div>

	<div class="toaster">
		<div class="toaster-content">
				<c:set var="status" value="${showAlertTypeJsp}" />
				
				<c:choose>
					<c:when test="${status == 'faliure'}">
						<i class="uil uil-exclamation toaster-check"></i>
					</c:when>
					<c:otherwise>
						<i class="uil uil-check toaster-check"></i>
					</c:otherwise>
				</c:choose>
				
				
			
			<div class="message">

				<c:choose>
					<c:when test="${status == 'faliure'}">
						<span class="message-text text-1">Faliure</span>
					</c:when>
					<c:when test="${status == 'success'}">
						<span class="message-text text-1">Success</span>
					</c:when>
					<c:otherwise>
						<span class="message-text text-1">Message</span>
					</c:otherwise>
				</c:choose>

				<span class="message-text text-2"> ${msg}</span>
			</div>
		</div>
		<i class="uil uil-multiply toaster-close"></i>
		<div class="progress"></div>
	</div>

	<script src="<c:url value="/resources/js/login.js" />"></script>
	<script src="<c:url value='/resources/js/toasters.js' />"></script>
	<script src="<c:url value="/resources/js/darktheme.js" />"></script>
</body>
</html>