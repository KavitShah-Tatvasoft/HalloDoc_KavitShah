<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="no-login-navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<c:url value='/resources/css/loader.css' />">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/css/create-patient-request.css" />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="<c:url value="/resources/css/footer.css" />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<title>Patient Request</title>
</head>
<body onload="hideLoader()">

	<div class="loader-container">
		<div class="loader"></div>
	</div>

	<div
		class="container-fluid patient-form footer-container shadow p-3 bg-white rounded relative-position extra-margin">

		<!--         <a href="patient_submit_request.html"> -->
		<div class="back-btn-top " type="button" onclick="history.go(-1)">
			<img src="<c:url value="/resources/images/chevron-left.svg" />"
				alt=""> Back
		</div>
		<!--         </a> -->

		<form action="addPatientRequest" method="post" id="patientRequestForm"
			enctype="multipart/form-data">
			<div class="row">
				<!--Row 1-->

				<div class="col-12 mb-3">
					<!--Patient Information col-->
					<span class="patient-text">Patient Information</span>
				</div>



				<div class="col-12 inc-height">
					<!--System Information col-->
					<div class="form-floating mb-4 ">
						<textarea name="symptoms"
							class="form-control input-1 inc-inp-height" id="floatingInput-1"
							placeholder="Enter Brief Details Of Symptoms(Optional)"></textarea>
						<label for="floatingInput-1" class="username-clr">Enter
							Brief Details Of Symptoms <span class="optional-toggle">(Optional)</span>
						</label>
					</div>
				</div>
				<div class="col-12 col-md-6">
					<!--First Name col-->
					<div class="form-floating inp">
						<input type="text" class="form-control input-2"
							id="floatingInput-2" placeholder="First Name" name="firstName"
							autocomplete="off"> <label for="floatingInput-2">First
							Name</label> <span id="firstNameError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1"
							id="floatingInput-3" placeholder="Last Name" name="lastName"
							autocomplete="off"> <label for="floatingInput-3">Last
							Name</label> <span id="lastNameError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp custom-date-input">
						<!--Date Picker col-->
						<input type="date" class="form-control input-1"
							id="floatingInput-4" placeholder="Date Of Birth"
							autocomplete="off" name="dob"> <label
							for="floatingInput-4">Date of Birth</label> <img
							src="<c:url value="/resources/images/calendar4-week.svg" />"
							alt="" class="custom-date-icon"> <span id="dobError"
							class="error-class-span"></span>
					</div>
				</div>
			</div>

			<div class="row">
				<!--Row 2-->

				<div class="col-12 mb-2">
					<!--Patient Contact Information col-->
					<span class="patient-text">Patient Contact Information</span>
				</div>

				<div class="col-12 col-md-6">
					<!--Email col-->
					<div class="form-floating inp">
						<input type="email" name="email" class="form-control input-2"
							id="emailField" placeholder="Email" autocomplete="off"
							onblur="validateEmail()"> <label for="emailField">Email</label>
						<span id="emailErrorField" style="color: red; padding-left: 5px;"></span>
					</div>

					<input type="hidden" name="isExsistingPatient" value="false"
						id="isExsistingPatient" />

				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating inp phonecolheight phonecol-height-1">
						<input type="tel" name="mobileNumber"
							class="form-control phoneflags" id="phone" />
					</div>
					<span id="mobileNumberError" class="error-class-span"></span>
				</div>

			</div>



			<div class="row" id="passwordRowId">
				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<!-- 						<input type="password" class="form-control input-1" -->
						<!-- 							id="floatingPassword-pass1" onblur="validatePassword()" -->
						<!-- 							placeholder="Password" name="password"> <label -->
						<!-- 							for="floatingPassword-pass1">Password</label> <a href="#" -->
						<!-- 							onclick="showPass()"><img -->
						<%-- 							src="<c:url value="/resources/images/password-eye.svg" />" --%>
						<!-- 							alt="view pass" class="open-eye" id="open-eye-id"></a> <a -->
						<!-- 							href="#" onclick="hidePass()"><img -->
						<%-- 							src="<c:url value="/resources/images/password-eye-slash.svg" />" --%>
						<!-- 							alt="hide pass" class="close-eye" id="close-eye-id"></a> <span -->
						<!-- 							id="passError1" class="error-class-span"></span> -->
						<input type="password" class="form-control input-1"
							id="floatingPassword-pass1"
							placeholder="Password" name="password"> <label
							for="floatingPassword-pass1">Password</label> <a href="#"
							onclick="showPass()"><img
							src="<c:url value="/resources/images/password-eye.svg" />"
							alt="view pass" class="open-eye" id="open-eye-id"></a> <a
							href="#" onclick="hidePass()"><img
							src="<c:url value="/resources/images/password-eye-slash.svg" />"
							alt="hide pass" class="close-eye" id="close-eye-id"></a> <span
							id="passError1" class="error-class-span visible"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<!-- 						<input type="password" onblur="validateConfirmPassword()" -->
						<!-- 							class="form-control input-1" id="floatingPassword-pass2" -->
						<!-- 							placeholder="Confirm Password" name="confirmPassword"> <label -->
						<!-- 							for="floatingPassword-pass2">Confirm Password</label> <a href="#" -->
						<!-- 							onclick="showPass1()"><img -->
						<%-- 							src="<c:url value="/resources/images/password-eye.svg" />" --%>
						<!-- 							alt="view pass" class="open-eye" id="open-eye-id-1"></a> <a -->
						<!-- 							href="#" onclick="hidePass1()"><img -->
						<%-- 							src="<c:url value="/resources/images/password-eye-slash.svg" />" --%>
						<!-- 							alt="hide pass" class="close-eye" id="close-eye-id-1"></a> <span -->
						<!-- 							id="passError" class="error-class-span visible"></span> -->

						<input type="password" class="form-control input-1"
							id="floatingPassword-pass2" placeholder="Confirm Password"
							name="confirmPassword"> <label
							for="floatingPassword-pass2">Confirm Password</label> <a href="#"
							onclick="showPass1()"><img
							src="<c:url value="/resources/images/password-eye.svg" />"
							alt="view pass" class="open-eye" id="open-eye-id-1"></a> <a
							href="#" onclick="hidePass1()"><img
							src="<c:url value="/resources/images/password-eye-slash.svg" />"
							alt="hide pass" class="close-eye" id="close-eye-id-1"></a> <span
							id="passError" class="error-class-span visible"></span>

					</div>
				</div>


			</div>

			<div class="row">
				<!--Row 3-->

				<div class="col-12 mb-3 add-extra-margin">
					<!--Patient Location col-->
					<span class="patient-text">Patient Location</span>
				</div>

				<div class="col-12 col-md-6">
					<!--Street col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="street" class="form-control input-2"
							id="floatingInput-7" placeholder="Street" autocomplete="off">
						<label for="floatingInput-7">Street</label> <span id="streetError"
							class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--City col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="city" class="form-control input-2"
							id="floatingInput-8" placeholder="City" autocomplete="off">
						<label for="floatingInput-8">City</label> <span id="cityError"
							class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--State col-->
					<div class="form-floating inp">
						<input type="text" name="state" class="form-control input-2"
							id="state" placeholder="State" onblur="validatePatientState()"
							autocomplete="off"> <label for="state">State</label> <span
							id="stateErrorField"></span> <span id="stateError"
							class="error-class-span"></span>
					</div>

				</div>

				<div class="col-12 col-md-6">
					<!--Zip Code col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="zipcode" class="form-control input-2"
							id="floatingInput-10" placeholder="Zip Code" autocomplete="off">
						<label for="floatingInput-10">Zip Code</label> <span id="zipError"
							class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Room #/ Suite(Optional) col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="room" class="form-control input-2"
							id="floatingInput-11" placeholder="Room #/ Suite(Optional)"
							autocomplete="off"> <label for="floatingInput-11">Room
							#/ Suite(Optional)</label>

					</div>
				</div>

			</div>



			<div class="row">
				<!--Row 4-->
				<div class="col-12 mb-3">
					<!--Patient Contact Information col-->
					<span class="patient-text">(Optional) Upload Photo or
						Document</span>
				</div>

				<div class="col-12">
					<div class="input-group mb-3">
						<input type="file" name="document" class="form-control"
							id="inputGroupFile02"> <label
							class="input-group-text file-upload-btn" for="inputGroupFile02"><img
							src="<c:url value="/resources/images/cloud-arrow-up-white.svg" />"
							alt=""><span class="upload-txt">Upload</span></label> <span
							id="fileuploadError"></span>
					</div>
				</div>

			</div>

			<div class="bottom-btns mt-3">
				<button type="submit" id="submit-btn-id"
					class="bottom-btns-submit shrink-btns">Submit</button>
				<button type="reset" class="bottom-btns-cancel shrink-btns">Cancel</button>
			</div>
		</form>

	</div>

	<%@include file="footer-black.jsp"%>
	<script src="<c:url value='/resources/js/loader.js' />"></script>
	<script>
		const phoneInputField = document.querySelector("#phone");
		const phoneInput = window
				.intlTelInput(
						phoneInputField,
						{
							utilsScript : "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
						});
	</script>

	<script type="text/javascript">
		
	</script>

	<script src="<c:url value="/resources/js/darktheme.js" />"></script>
	<script src="<c:url value="/resources/js/create-patient-request.js" />"></script>

</body>

</html>