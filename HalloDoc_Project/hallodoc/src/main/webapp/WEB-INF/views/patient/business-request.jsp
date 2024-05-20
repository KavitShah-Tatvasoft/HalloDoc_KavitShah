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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/create-patient-request.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/FamilyFriend-request.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;400&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<title>Business Request</title>
</head>
<body onload="hideLoader()">
	<div class="loader-container">
		<div class="loader"></div>
	</div>	
	<div
		class="container-fluid footer-container patient-form shadow p-3 bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " type="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>
		<form action="addBusinessRequest" method="post" id="business-form-id">
			<div class="row">
				<!--Row 1-->

				<div class="col-12 mb-3">
					<!--Patient Information col-->
					<span class="patient-text">Business Information</span>
				</div>

				<div class="col-12 col-md-6">
					<!--First Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="reqFirstName"
							class="form-control input-2" id="reqFirstName"
							placeholder="First Name" autocomplete="off"> <label
							for="reqFirstName">Your First Name</label>
						<span id="reqFirstNameError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="reqLastName" class="form-control input-1"
							id="reqLastName" placeholder="Last Name" autocomplete="off">
						<label for="reqLastName">Your Last Name</label>
						<span id="reqLastNameError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 inp phonecolheight">
						<input type="tel" name="reqMobileNumber"
							class="form-control phoneflags phone" />
						<span id="reqMobileNumberError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Email col-->
					<div class="form-floating mb-3 inp business-req-only">
						<input type="email" name="reqEmail" class="form-control input-2"
							id="reqEmail" placeholder="Email" autocomplete="off">
						<label for="reqEmail">Your Email</label>
						<span id="reqEmailError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Relation with patient col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="reqProperty" class="form-control input-2"
							id="reqProperty" placeholder="Relation With Patient"
							autocomplete="off"> <label for="reqProperty">Business/Property
							Name </label>
							<span id="reqPropertyError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Case Number col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="reqCaseNumber"
							class="form-control input-2" id="floatingInput-2"
							placeholder="First Name" autocomplete="off"> <label
							for="floatingInput-2">Case Number (Optional)</label>
					</div>
				</div>


			</div>

			<div class="row">
				<!--Row 2-->

				<div class="col-12 mb-3">
					<!--Patient Information col-->
					<span class="patient-text">Patient Information</span>
				</div>

				<div class="col-12 inc-height">
					<!--System Information col-->
					<div class="form-floating mb-4 ">
						<textarea name="symptoms"
							class="form-control input-1 inc-inp-height" id="symptoms"
							placeholder="Enter Brief Details Of Symptoms(Optional)"></textarea>
						<label for="symptoms" class="username-clr">Enter
							Brief Details Of Symptoms <span class="optional-toggle">(Optional)</span>
						</label>
						
					</div>
				</div>
				<div class="col-12 col-md-6">
					<!--First Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="ptFirstName" class="form-control input-2"
							id="ptFirstName" placeholder="First Name" autocomplete="off">
						<label for="ptFirstName">First Name</label>
						<span id="ptFirstNameError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="ptLastName" class="form-control input-1"
							id="ptLastName" placeholder="Last Name" autocomplete="off">
						<label for="ptLastName">Last Name</label>
						
						<span id="ptLastNameError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp custom-date-input">
						<!--Date Picker col-->
						<input type="date" name="ptDob" class="form-control input-1"
							id="ptDob" placeholder="Date Of Birth"
							autocomplete="off"> <label for="ptDob">Date
							of Birth</label> <img
							src="<c:url value='/resources/images/calendar4-week.svg' />"
							alt="" class="custom-date-icon">
							<span id="ptDobError" class="error-class-span"></span>
					</div>
				</div>

			</div>

			<div class="row">
				<!--Row 3-->

				<div class="col-12 mb-3 ">
					<!--Patient Contact Information col-->
					<span class="patient-text">Patient Contact Information</span>
				</div>

				<div class="col-12 col-md-6">
					<!--Email col-->
					<div class="form-floating mb-3 inp">
						<input type="email" name="ptEmail" class="form-control input-2"
							id="ptEmail" placeholder="Email" autocomplete="off">
						<label for="ptEmail">Email</label>
						<span id="ptEmailError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 inp phonecolheight">
						<input type="tel" name="ptMobileNumber"
							class="form-control phoneflags phone" />
						<span id="ptMobileNumberError" class="error-class-span"></span>
					</div>
				</div>

			</div>



			<div class="row">
				<!--Row 4-->

				<div class="col-12 mb-3 add-extra-margin">
					<!--Patient Location col-->
					<span class="patient-text">Patient Location</span>
				</div>

				<div class="col-12 col-md-6">
					<!--Street col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="ptStreet" class="form-control input-2"
							id="ptStreet" placeholder="Street" autocomplete="off">
						<label for="ptStreet">Street</label>
						<span id="ptStreetError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--City col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="ptCity" class="form-control input-2"
							id="ptCity" placeholder="City" autocomplete="off">
						<label for="ptCity">City</label>
						<span id="ptCityError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--State col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="ptState" onblur="validatePatientState()"
							class="form-control input-2" id="state" placeholder="State"
							autocomplete="off"> <label for="state">State</label> <span
							id="stateErrorField" class="error-class-span"></span>
							
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Zip Code col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="ptZipcode" class="form-control input-2"
							id="ptZipcode" placeholder="Zip Code" autocomplete="off">
						<label for="ptZipcode">Zip Code</label>
						<span id="ptZipcodeError" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Room #/ Suite(Optional) col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="ptRoom" class="form-control input-2"
							id="ptRoom" placeholder="Room #/ Suite(Optional)"
							autocomplete="off"> <label for="ptRoom">Room
							#/ Suite (Optional)</label>
					</div>
				</div>


				<div class="bottom-btns mt-3">
					<button type="submit" id="submit-btn-id"
						class="bottom-btns-submit shrink-btns">Submit</button>
					<button type="reset" class="bottom-btns-cancel shrink-btns">Cancel</button>
				</div>
			</div>

		</form>
	</div>
	<script src="<c:url value='/resources/js/loader.js' />"></script>
	<script>
		const phoneInputField = document.getElementsByClassName("phone");
		for (let i = 0; i < phoneInputField.length; ++i) {
			const phoneInput = window
					.intlTelInput(
							phoneInputField[i],
							{
								utilsScript : "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
							});
		}
	</script>
	<script src="<c:url value="/resources/js/darktheme.js" />"></script>
	<script src="<c:url value="/resources/js/business-request.js" />"></script>

</body>
</html>
<%@include file="footer-black.jsp"%>