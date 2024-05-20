<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;400&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<link rel="stylesheet"
	href="<c:url value="/resources/css/footer.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/FamilyFriend-request.css" />">
<title>Family/Friend Request</title>
</head>
<body onload="hideLoader()">
	<div class="loader-container">
		<div class="loader"></div>
	</div>
	<div
		class="container-fluid patient-form shadow p-3 bg-white rounded relative-position extra-margin">

		<!--     <a href="patient_submit_request.html"> -->
		<div class="back-btn-top " type="button" onclick="history.go(-1)">
			<img src="<c:url value="/resources/images/chevron-left.svg" />"
				alt=""> Back
		</div>

		<form id="family-friend-form-id" action="createNewFamilyRequest"
			method="post" enctype="multipart/form-data">

			<!--     </a> -->
			<div class="row">
				<!--Row 1-->

				<div class="col-12 mb-3">
					<!--Patient Information col-->
					<span class="patient-text">Family/Friend Information</span>
				</div>

				<div class="col-12 col-md-6">
					<!--First Name col-->
					<div class="form-floating mb-3 inp">
						<input name="reqFirstName" type="text"
							class="form-control input-2" id="reqFirstName"
							placeholder="First Name" autocomplete="off"> <label
							for="reqFirstName">Your First Name</label> <span
							class="error-class-span" id="reqFirstNameError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input name="reqLastName" type="text" class="form-control input-1"
							id="reqLastName" placeholder="Last Name" autocomplete="off">
						<label for="reqLastName">Your Last Name</label> <span
							class="error-class-span" id="reqLastNameError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 inp phonecolheight">
						<input name="reqMobileNumber" type="tel"
							class="form-control phoneflags phone" /> <span
							class="error-class-span" id="reqMobileNumberError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6 only-family-friend-margin-top">
					<!--Email col-->
					<div class="form-floating mb-3 inp">
						<input name="reqEmail" type="email" class="form-control input-2"
							id="reqEmail" placeholder="Email" autocomplete="off">
						<label for="reqEmail">Your Email</label> <span
							class="error-class-span" id="reqEmailError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Relation with patient col-->
					<div class="form-floating mb-3 inp">
						<input name="reqRelation" type="text" class="form-control input-2"
							id="reqRelation" placeholder="Relation With Patient"
							autocomplete="off"> <label for="reqRelation">Relation
							With Patient</label> <span class="error-class-span" id="reqRelationError"></span>
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
						<input name="ptFirstName" type="text" class="form-control input-2"
							id="ptFirstName" placeholder="First Name" autocomplete="off">
						<label for="ptFirstName">First Name</label> <span
							class="error-class-span" id="ptFirstNameError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input name="ptLastName" type="text" class="form-control input-1"
							id="ptLastName" placeholder="Last Name" autocomplete="off">
						<label for="ptLastName">Last Name</label> <span
							class="error-class-span" id="ptLastNameError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp custom-date-input">
						<!--Date Picker col-->
						<input name="ptDob" type="date" class="form-control input-1"
							id="ptDob" placeholder="Date Of Birth"
							autocomplete="off"> <label for="ptDob">Date
							of Birth</label> <img src="./SRS Screen Shorts/calendar4-week.svg" alt=""
							class="custom-date-icon"> <span class="error-class-span"
							id="ptDobError"></span>
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
						<input name="ptEmail" type="email" class="form-control input-2"
							id="ptEmail" placeholder="Email" autocomplete="off">
						<label for="ptEmail">Email</label> <span
							class="error-class-span" id="ptEmailError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 inp phonecolheight">
						<input name="ptMobileNumber" type="tel"
							class="form-control phoneflags phone" /> <span
							class="error-class-span" id="ptMobileNumberError"></span>
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
						<input name="ptStreet" type="text" class="form-control input-2"
							id="ptStreet" placeholder="Street" autocomplete="off">
						<label for="ptStreet">Street</label> <span
							class="error-class-span" id="ptStreetError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--City col-->
					<div class="form-floating mb-3 inp">
						<input name="ptCity" type="text" class="form-control input-2"
							id="ptCity" placeholder="City" autocomplete="off">
						<label for="ptCity">City</label> <span
							class="error-class-span" id="ptCityError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--State col-->
					<div class="form-floating inp">
						<input name="ptState" type="text" class="form-control input-2"
							id="state" placeholder="State" autocomplete="off"
							onblur="validatePatientState()"> <label for="state">State</label>
						<span id="stateErrorField" class="error-class-span"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Zip Code col-->
					<div class="form-floating mb-3 inp">
						<input name="ptZipcode" type="text" class="form-control input-2"
							id="ptZipcode" placeholder="Zip Code" autocomplete="off">
						<label for="ptZipcode">Zip Code</label> <span
							class="error-class-span" id="ptZipcodeError"></span>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Room #/ Suite(Optional) col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2" name="ptRoom"
							id="ptRoom" placeholder="Room #/ Suite(Optional)"
							autocomplete="off"> <label for="ptRoom">Room
							#/ Suite(Optional)</label>
					</div>
				</div>

				<div class="row">
					<!--Row 5-->
					<div class="col-12 mb-3">
						<!--Patient Contact Information col-->
						<span class="patient-text">(Optional) Upload Photo or
							Document</span>
					</div>

					<div class="col-12">
						<div class="input-group mb-3 inc-width-family">
							<input name="document" type="file" class="form-control"
								id="inputGroupFile02"> <label
								class="input-group-text file-upload-btn" for="inputGroupFile02"><img
								src="<c:url value="/resources/images/cloud-arrow-up-white.svg" />"
								alt=""><span class="upload-txt">Upload</span></label>
						</div>
					</div>


				</div>

				<div class="bottom-btns mt-3">
					<button type="submit" class="bottom-btns-submit shrink-btns">Submit</button>
					<button type="reset" class="bottom-btns-cancel shrink-btns">Cancel</button>
				</div>
			</div>

		</form>
	</div>



	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body family-pop">
					<img src="<c:url value="/resources/images/exclamation.png" />"
						alt="" class="set-img">
					<p class="information">Information</p>
					<span class="popup-txt">When submit a request, you must
						provide the correct contact information for the patient or the
						responsibly party. Failure to provide the correct email and phone
						number will delay service or be declined.</span>
					<button type="button" class="ok-btn" data-bs-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
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

		var myModal = new bootstrap.Modal(document
				.getElementById('staticBackdrop'), {});
		;
		myModal.show();
	</script>

	<script src="<c:url value="/resources/js/darktheme.js" />"></script>
	<script src="<c:url value="/resources/js/family-friend.js" />"></script>

</body>
</html>

<%@include file="footer-black.jsp"%>