<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@include file="patient-navbar.jsp"%>
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
<link rel="stylesheet" href="<c:url value='/resources/css/create-patient-request.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<title>Patient Profile</title>
</head>
<body>

	<div
		class="container-fluid patient-form footer-container shadow p-3 bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />" alt=""> Back
		</div>
		<form action="">
		<div class="submit-info-txt">User Profile</div>

		<div class="row">
			<!--Row 1-->

			<div class="col-12 mb-3">
				<!--Patient Information col-->
				<span class="patient-text">General Information</span>
			</div>

			<div class="col-12 col-md-6">
				<!--First Name col-->
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2 editDetails" name="userFirstName"
						id="floatingInput-2" placeholder="First Name" value="${userOb.firstName }" autocomplete="off" disabled="disabled">
					<label for="floatingInput-2">First Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Last Name col-->
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 editDetails" name="userLastName"
						id="floatingInput-3" placeholder="Last Name" value="${userOb.lastName }" disabled="disabled" autocomplete="off">
					<label for="floatingInput-3">Last Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp custom-date-input">
					<!--Date Picker col-->
					<input type="date" class="form-control input-1 editDetails" value="" disabled="disabled"
						id="floatingInput-4" placeholder="Date Of Birth"  name="userDOB"
						autocomplete="off"> <label for="floatingInput-4">Date
						of Birth</label> <img src="<c:url value='/resources/images/calendar4-week.svg' />" alt=""
						class="custom-date-icon">
				</div>
			</div>

		</div>

		<div class="row">
			<!--Row 2-->

			<div class="col-12 mb-3 add-extra-margin">
				<!--Patient Contact Information col-->
				<span class="patient-text">Patient Contact Information</span>
			</div>

			<div class="col-4 col-md-2">
				<!--Mobile Type col-->
				<div class="form-floating mb-3 inp">
					<select name="Number_Type" id="floatingInput-5"
						class="form-control form-select input-2 editDetails" disabled="disabled">
						<option value="Mobile" selected>Mobile</option>
						<option value="Home">Home</option>
					</select> <label for="floatingInput-5">Type</label>
				</div>
			</div>

			<div class="col-8 col-md-4">
				<!--Phone Number col-->
				<div
					class="form-floating mb-3 inp phonecolheight phonecolheight-patient-profile-only">
					<input type="tel" class="form-control phoneflags editDetails" name="userMobile" id="phone" value="+91 ${userOb.mobile}" disabled="disabled"  />
				</div>
			</div>


			<div class="col-12 col-md-6">
				<!--Email col-->
				<div class="form-floating mb-3 inp">
					<input type="email" class="form-control input-2 editDetails" name="userEmail" value="${userOb.email }" disabled="disabled"
						id="floatingInput-5" placeholder="Email" autocomplete="off">
					<label for="floatingInput-5">Email</label>
				</div>
			</div>

		</div>

		<div class="row">
			<!--Row 3-->

			<div class="col-12 mb-3 add-extra-margin">
				<!--Patient Location col-->
				<span class="patient-text">Location Information</span>
			</div>

			<div class="col-12 col-md-6">
				<!--Street col-->
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2 editDetails" name="userStreet" value="${userOb.street }" disabled="disabled"
						id="floatingInput-7" placeholder="Street" autocomplete="off">
					<label for="floatingInput-7">Street</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--City col-->
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2 editDetails" name="userCity" value="${userOb.city }" disabled="disabled"
						id="floatingInput-8" placeholder="City" autocomplete="off">
					<label for="floatingInput-8">City</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--State col-->
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2 editDetails" name="userState" value="${userOb.state }" disabled="disabled"
						id="floatingInput-9" placeholder="State" autocomplete="off">
					<label for="floatingInput-9">State</label>
				</div>
			</div>

			<div class="col-8 col-md-4">
				<!--Zip Code col-->
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2 editDetails" name="userZipCode" value="${userOb.zipcode }" disabled="disabled"
						id="floatingInput-10" placeholder="Zip Code" autocomplete="off">
					<label for="floatingInput-10">Zip Code</label>
				</div>
			</div>

			<div class="col-2   ">
				<!--Room #/ Suite(Optional) col-->
				<div class="map-text-flex map-text-flex-patient-profile-only">
					<img src="<c:url value='/resources/images/geo-alt-fill.svg' />" alt=""
						class="map-icon"> <span class="map-text">Map</span>
				</div>
			</div>

		</div>

		<div class="bottom-btns mt-3">
			<button type="button" id="edit-btn-pdashboard" onclick="editPatientDetails()" class="bottom-btns-edit">Edit</button>
			<button type="submit" id="save-btn-pdashboard" class="bottom-btns-submit">Save</button>
			<button type="reset" id="cancel-btn-pdashboard" onclick="editPatientDetails()" class="bottom-btns-cancel">Cancel</button>
		</div>
		
		</form>
	</div>
	<%@include file="footer-black.jsp"%>
	<script>
		const phoneInputField = document.querySelector("#phone");
		const phoneInput = window
				.intlTelInput(
						phoneInputField,
						{
							utilsScript : "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
						});
	</script>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/patient-profile.js' />"></script>
	
</body>
</html>