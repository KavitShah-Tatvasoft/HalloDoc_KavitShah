<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="common-navbar.jsp"%>
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
	href="<c:url value='/resources/css/view-case.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/pop-ups.css' />">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<title>View Case</title>
</head>
<body>

	<div
		class="container-fluid footer-container patient-form shadow p-3 bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="top-patient-view-text">

			<c:choose>
				<c:when test="${requestOb.status == 1}">
					<span class="view-reservation-text">New Request</span>
				</c:when>
				<c:otherwise>
					<span class="view-reservation-text">View Reservation</span>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${requestOb.requestType.requestTypeId == 2}">
					<div class="patient-type-color-icon">
						<span class="type-text">Patient</span>
					</div>
				</c:when>
				<c:when test="${requestOb.requestType.requestTypeId == 1}">
					<div class="business-type-color-icon">
						<span class="type-text">Business</span>
					</div>
				</c:when>
				<c:when test="${requestOb.requestType.requestTypeId == 3}">
					<div class="family-type-color-icon">
						<span class="type-text">Family/Friend</span>
					</div>
				</c:when>
				<c:when test="${requestOb.requestType.requestTypeId == 4}">
					<div class="concierge-type-color-icon">
						<span class="type-text">Concierge</span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="type-color">
						<span class="type-text">Patient</span>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<form>
			<div class="row">
				<!--Row 1-->

				<div class="col-12 mb-3">
					<!--Patient Information col-->
					<span class="patient-text">Patient Information</span>
				</div>

				<div class="col-12 mb-4 confirmation-number-text-flex ">
					<span class="confirmation-number-text">Confirmation Number</span> <span
						class="confirmation-number">${requestOb.confirmationNumber }</span>
				</div>

				<div class="col-12 inc-height">
					<!--System Information col-->
					<div class="form-floating mb-4 ">
						<textarea name="" class="form-control input-1 inc-inp-height"
							id="patient-notes"
							placeholder="Enter Brief Details Of Symptoms(Optional)" disabled>${requestOb.requestClient.notes}</textarea>
						<label for="floatingInput-1" class="username-clr">Patient
							Notes</label>
					</div>
				</div>

				<div class="col-12">
					<hr class="dotted-hr">
				</div>

				<div class="col-12 col-md-6">
					<!--First Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2 patient-details"
							id="patient-fname" placeholder="First Name"
							value=${requestOb.requestClient.firstName } autocomplete="off"
							disabled> <label for="floatingInput-2">First Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1 patient-details"
							id="patient-lname" placeholder="Last Name"
							value="${requestOb.requestClient.lastName}" autocomplete="off"
							disabled> <label for="floatingInput-3">Last Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp custom-date-input">
						<!--Date Picker col-->
						<input type="date" class="form-control input-1 patient-details" value="${requestOb.requestClient.dateObject }"
							id="patient-bdate" placeholder="Date Of Birth" autocomplete="off"
							disabled> <label for="floatingInput-4">Date of
							Birth</label> <img
							src="<c:url value='/resources/images/calendar4-week.svg' />"
							alt="" class="custom-date-icon">
					</div>
				</div>

				<div class="col-9 col-md-5">
					<!--Phone Number col-->
					<div class="form-floating mb-3 inp phonecolheight">
						<input type="tel" class="form-control phoneflags patient-details"
							value="${requestOb.requestClient.phoneNumber}" id="phone"
							disabled />
					</div>
				</div>

				<div class="col-1 remove-padding-left">
					<div class="telephone-border-class">
						<img src="<c:url value='/resources/images/telephone-blue.svg' />"
							class="telephone-icon" alt="telephone">
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Email col-->
					<div class="form-floating mb-3 inp">
						<input type="email" class="form-control input-2"
							value="${requestOb.requestClient.email}" id="patient-email"
							disabled placeholder="Email" autocomplete="off"> <label
							for="floatingInput-5">Email</label>
					</div>
				</div>

				<c:choose>
					<c:when test="${requestOb.status != 1}">
						<div class="col-1" id="edit-details-id">
							<!-- <div class="edit-border"> -->
							<button class="edit-text" type="button" id="EditBtn"
								onclick="editDetails()">Edit</button>
							<!-- </div> -->
						</div>

						<div class="col-3 save-cancel-details-btn"
							id="save-cancel-details-btn-id">
							<button class="save-text" type="button" id="SaveBtn"
								onclick="saveDetails(${requestOb.requestId})">Save</button>
							<button class="cancel-text" type="reset"
								onclick="cancelDetails()">Cancel</button>
						</div>
					</c:when>
				</c:choose>





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
						<input type="text" class="form-control input-2"
							value="${requestOb.requestClient.state}" disabled
							id="floatingInput-7" placeholder="Region" autocomplete="off">
						<label for="floatingInput-7">Region</label>
					</div>
				</div>



				<div class="col-9 col-md-5">
					<!--City col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2"
							value="${requestOb.requestClient.street}, ${requestOb.requestClient.city}, ${requestOb.requestClient.state} - ${requestOb.requestClient.zipcode}"
							id="floatingInput-8" placeholder="Business Name / Address"
							autocomplete="off" disabled> <label for="floatingInput-8">Business
							Name / Address</label>
					</div>
				</div>

				<div class="col-1 remove-padding-left">
					<div class="location-border">
						<img class="location-icon"
							src="<c:url value='/resources/images/geo-alt-fill.svg' />" alt="">
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Room #/ Suite(Optional) col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2"
							id="floatingInput-11" placeholder="Room #" autocomplete="off"
							disabled> <label for="floatingInput-11">Room #</label>
					</div>
				</div>

			</div>

			<div class="bottom-btns mt-3">
				<button type="button" class="bottom-btns-submit shrink-btns"
					data-bs-toggle="modal" data-bs-target="#assign-case">Assign</button>
				<a role="button" href="view-notes.html"
					class="bottom-btns-submit shrink-btns">View Notes</a>
				<button type="reset" class="bottom-btns-cancel shrink-btns">Cancel</button>
			</div>
		</form>
	</div>


	<!-- pop ups  -->

	<div class="modal fade" id="assign-case" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content assign-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Assign
						Request</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div>
						<span class="font-clr-light">To assign this request, search
							and select another Physician.</span><br>
					</div>

					<form>

						<div class="col-12 mt-3">
							<div class="form-floating mb-3 inp">
								<select name="Number_Type" id="floatingInput-5"
									class="form-control form-select input-2">
									<c:forEach items="${regionList}" var="region">
										<option value="${region.name }">${region.name }</option>
									</c:forEach>
								</select> <label for="floatingInput-5">Narrow Search by Region</label>
							</div>
						</div>

						<div class="container">
							<div class="row assign-case-select-height assign-case-margin-top">

								<select name="Number_Type" id="floatingInput-5"
									class="form-control form-select assign-case-text-clr">
									<option value="check" hidden selected>Select Physician</option>
									<option value="Mobile">Person 1</option>
									<option value="Home">Person 2</option>
								</select>

							</div>
						</div>
						<textarea name="message"
							class="form-control assign-case-margin-top" id="" cols="12"
							rows="5" placeholder="Description"></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="send-btn">Confirm</button>
					<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

				</div>
			</div>
		</div>
	</div>


	<script>
		const phoneInputField = document.querySelector("#phone");
		const phoneInput = window
				.intlTelInput(
						phoneInputField,
						{
							utilsScript : "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
						});
	</script>

	<script src="<c:url value='/resources/js/view-case.js' />"></script>
	<script src="<c:url value='/resources/js/darktheme.js' />"></script>

</body>
</html>
<%@include file="footer-black.jsp"%>