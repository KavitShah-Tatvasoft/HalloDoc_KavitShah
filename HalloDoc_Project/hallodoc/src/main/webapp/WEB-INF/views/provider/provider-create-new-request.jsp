<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="UTF-8">
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
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/admin-create-request.css' />">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
<title>Create Patient Request</title>
</head>
<body onload="hideLoader()">
	<div class="loader-container">
		<div class="loader"></div>
	</div>
	<div
		class="container-fluid patient-form footer-container shadow p-3 bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt="" class="back-icon-top"> Back
		</div>

		<div class="submit-info-txt">Submit Information</div>

		<form action="adminCreatePtRequest" method="post"
			onsubmit="showLoader()">

			<div class="row">
				<!--Row 1-->

				<div class="col-12 mb-3">
					<!--Patient Information col-->
					<span class="patient-text">Patient</span>
				</div>

				<div class="col-12 col-md-6">
					<!--First Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2" name="firstName"
							id="floatingInput-2" placeholder="First Name" autocomplete="off">
						<label for="floatingInput-2">First Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1" name="lastName"
							id="floatingInput-3" placeholder="Last Name" autocomplete="off">
						<label for="floatingInput-3">Last Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 inp phonecolheight">
						<input type="tel" class="form-control phoneflags" id="phone"
							name="mobileNumber" />
					</div>
				</div>

				<div class="col-12 col-md-6 add-extra-margin">
					<!--Email col-->
					<div class="form-floating mb-3 inp">
						<input type="email" class="form-control input-2" name="email"
							id="floatingInput-5" placeholder="Email" autocomplete="off">
						<label for="floatingInput-5">Email</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp custom-date-input">
						<!--Date Picker col-->
						<input type="date" name="dob" class="form-control input-1"
							id="floatingInput-4" placeholder="Date Of Birth"
							autocomplete="off"> <label for="floatingInput-4">Date
							of Birth</label> <img
							src="<c:url value='/resources/images/calendar4-week.svg' />"
							alt="" class="custom-date-icon">
					</div>
				</div>

			</div>


			<div class="row">
				<!--Row 3-->

				<div class="col-12 mb-3">
					<!--Patient Location col-->
					<span class="patient-text">Location</span>
				</div>

				<div class="col-12 col-md-6">
					<!--Street col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="street" class="form-control input-2"
							id="street" placeholder="Street" autocomplete="off"> <label
							for="street">Street</label> <span id="street_error"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--City col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="city" class="form-control input-2"
							id="city" placeholder="City" autocomplete="off"> <label
							for="city">City</label> <span id="city_error"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--State col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="state" class="form-control input-2"
							id="state" placeholder="State" autocomplete="off"> <label
							for="state">State</label> <span id="state_error"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Zip Code col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="zipcode" class="form-control input-2"
							id="zipcode" placeholder="Zip Code" autocomplete="off"> <label
							for="zipcode">Zip Code</label> <span id="zipcode_error"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Room #/ Suite(Optional) col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2" name="room"
							id="floatingInput-11" placeholder="Room #/ Suite(Optional)"
							autocomplete="off"> <label for="floatingInput-11">Room
							#/ Suite(Optional)</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Room #/ Suite(Optional) col-->
					<div class="request-admin-btn-flex">
						<div class="map-text-flex">
							<span class="verify-text">Verify</span>
						</div>


						<div class="map-text-flex" type="button " onclick='showMap("")'>
							<img src="<c:url value='/resources/images/geo-alt-fill.svg' />"
								alt="" class="map-icon"> <span class="map-text">Map</span>
						</div>
					</div>
				</div>

				<div class="col-12 mb-3">
					<!--Patient Information col-->
					<span class="patient-text">Notes</span>
				</div>

				<div class="col-12 inc-height">
					<!--System Information col-->
					<div class="form-floating mb-4 ">
						<textarea name="symptoms"
							class="form-control input-1 inc-inp-height" id="floatingInput-1"
							placeholder="Enter Brief Details Of Symptoms(Optional)"></textarea>
						<label for="floatingInput-1" class="username-clr">Provider
							Notes <span class="optional-toggle">(Optional)</span>
						</label>
					</div>
				</div>

			</div>


			<div class="bottom-btns mt-3">
				<button type="submit" class="bottom-btns-submit">Save</button>
				<button type="reset" class="bottom-btns-cancel">Cancel</button>
			</div>

		</form>
	</div>

	<!-- Modal -->
	<div class="modal fade " id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<div class="modal-body">


					<div class="mapouter">
						<div class="gmap_canvas">
							<iframe width="100%" height="500" id="gmap_canvas" src=""
								frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
						</div>
					</div>


				</div>

			</div>
		</div>
	</div>


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

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/view-map.js' />"></script>

</body>
</html>

<%@include file="footer-black.jsp"%>