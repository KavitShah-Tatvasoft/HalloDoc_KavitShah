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
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/patient-profile-map.css' />">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="./css/create-patient-request.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<title>Patient Request</title>
</head>
<body>
	<div
		class="container-fluid patient-form shadow p-3 bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="submit-info-txt">Submit Information</div>

		<form action="createReqisteredPatientRequestForMe" method="post"
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
					<div class="form-floating mb-3 inp">
						<input type="text" name="firstName" value="${userOb.firstName}"
							readonly="readonly" class="form-control input-2"
							id="floatingInput-2" placeholder="First Name" autocomplete="off">
						<label for="floatingInput-2">First Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" name="lastName" value="${userOb.lastName}"
							readonly="readonly" class="form-control input-1"
							id="floatingInput-3" placeholder="Last Name" autocomplete="off">
						<label for="floatingInput-3">Last Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp custom-date-input">
						<!--Date Picker col-->
						<input type="date" name="dob" value="${userOb.dateObject}"
							readonly="readonly" class="form-control input-1"
							id="floatingInput-4" placeholder="Date Of Birth"
							autocomplete="off"> <label for="floatingInput-4">Date
							of Birth</label> <img
							src="<c:url value='/resources/images/calendar4-week.svg' />"
							alt="" class="custom-date-icon">
					</div>
				</div>

			</div>

			<div class="row">
				<!--Row 2-->

				<div class="col-12 mb-3 ">
					<!--Patient Contact Information col-->
					<span class="patient-text">Patient Contact Information</span>
				</div>

				<div class="col-12 col-md-6">
					<!--Email col-->
					<div class="form-floating mb-3 inp">
						<input type="email" name="email" value="${userOb.email}"
							readonly="readonly" class="form-control input-2"
							id="floatingInput-5" placeholder="Email" autocomplete="off">
						<label for="floatingInput-5">Email</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating  mb-3 inp phonecolheight">
						<input type="tel" value="+91 ${userOb.mobile}" readonly="readonly"
							name="mobileNumber" class="form-control phoneflags" id="phone" />
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
						<!-- 						<input type="text" name="state" class="form-control input-2" -->
						<!-- 							id="state" placeholder="State" autocomplete="off"> -->
						<!-- 						<label for="state">State</label> -->

						<select name="state" id="state" class="form-control input-2">


							<c:forEach items="${regions}" var="regionOb">
								<option value="${regionOb.name}">${regionOb.name}</option>
							</c:forEach>


						</select> <label for="state">State</label> <span id="state_error"></span>
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
						<input type="text" name="room" class="form-control input-2"
							id="floatingInput-11" placeholder="Room #/ Suite(Optional)"
							autocomplete="off"> <label for="floatingInput-11">Room
							#/ Suite(Optional)</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Room #/ Suite(Optional) col-->
					<div type="button" class="map-text-flex" onclick='showMap("")'>
						<img src="<c:url value='/resources/images/geo-alt-fill.svg' />"
							alt="" class="map-icon"> <span class="map-text">Map</span>
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
							src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
							alt=""><span class="upload-txt">Upload</span></label>
					</div>
				</div>

			</div>

			<div class="bottom-btns mt-3">
				<button type="submit" class="bottom-btns-submit shrink-btns">Submit</button>
				<button type="reset" class="bottom-btns-cancel shrink-btns">Cancel</button>
			</div>
		</form>


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
	<script
		src="<c:url value='/resources/js/submit-information-me-else.js' />"></script>
</body>
</html>