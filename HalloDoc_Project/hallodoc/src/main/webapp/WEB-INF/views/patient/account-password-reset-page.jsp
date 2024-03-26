
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/css/patient-login-forgetpassword.css" /> ">
<title>Reset Password</title>
</head>
<body>
<body class="relative-position">


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
			<img src="<c:url value="/resources/images/logo-halloDoc.png" />"
				alt="halloDoc logo" class="main-logo">
		</div>

		<div class="relative-position add-margin-top">

			<h2 class="Login-text-patient mb-4" id="Login-text-id">Reset
				Your Account Password</h2>

			<form action="${pageContext.request.contextPath}/createPatientValidator" method="post">

				<div class="form-floating mb-3 inp">
					<input type="Email" class="form-control input-1" id="floatingInput"
						placeholder="Email" name="email" value="${email}" readonly="readonly"> <label
						for="floatingInput" class="username-clr" >Email</label> <img
						src="<c:url value="/resources/images/person-circle.svg" />"
						alt="user-png" class="user-btn-img">
					
				</div>
				
				<input type="text" value="${token }", name="token">

				<div class="form-floating mb-3 inp">
					<input type="password" class="form-control input-1"
						id="floatingPassword-pass1" onblur="validatePassword()" placeholder="Password" name="password">
					<label for="floatingPassword-pass1">Password</label> <a href="#"
						onclick="showPass()"><img
						src="<c:url value="/resources/images/password-eye.svg" />"
						alt="view pass" class="open-eye" id="open-eye-id"></a> <a
						href="#" onclick="hidePass()"><img
						src="<c:url value="/resources/images/password-eye-slash.svg" />"
						alt="hide pass" class="close-eye" id="close-eye-id"></a>
						<span id="passError1"></span>
				</div>

				<div class="form-floating mb-3 inp">
					<input type="password" class="form-control input-1" onblur="validateConfirmPassword()"
						id="floatingPassword-pass2" placeholder="Confirmation Password" name="confPassword">
					<label for="floatingPassword-pass2">Confirmation Password</label> <a
						href="#" onclick="showPassword()"><img
						src="<c:url value="/resources/images/password-eye.svg" />"
						alt="view pass" class="open-eye" id="open-eye-id-1"></a> <a
						href="#" onclick="hidePassword()"><img
						src="<c:url value="/resources/images/password-eye-slash.svg" />"
						alt="hide pass" class="close-eye" id="close-eye-id-1"></a>
						<div style="color:red;margin-top:8px">${PasswordMatch}</div>
						<span id="passError"></span>
				</div>

<!-- 				<a href="patient-login.html"> -->
<!-- 					<div class="form-floating"> -->
<!-- 						<div class="submit-btn">Create</div> -->
<!-- 					</div> -->
<!-- 				</a> -->

					<button class="submit-btn" type="submit">Create</button>
			</form>
		</div>


		<footer class="bottom-txt-patient">
			<a href="#" class="bottom-txt-1" id="term">Terms of Condition</a> <span
				class="bottom-txt-1" id="of">|</span> <a href="#"
				class="bottom-txt-1" id="privacy">Privacy Policy</a>
		</footer>

	</div>


	<script src=" <c:url value="/resources/js/patient-login.js" />"></script>
<%-- 	<script src="<c:url value="/resources/js/login.js" /> "></script> --%>
	<script src='<c:url value="/resources/js/darktheme.js" /> '></script>
</body>


</html>