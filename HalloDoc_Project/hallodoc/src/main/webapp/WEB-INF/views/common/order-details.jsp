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
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/send-order.css' />">
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
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<title>Send Order</title>
</head>
<body>
	<div
		class="container-fluid footer-container patient-form shadow p-3 bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="top-patient-view-text">
			<span class="view-reservation-text">Send Orders</span>
		</div>
		<form method="post" id="sendOrderForm" action="../sendOrderDetails">
			<div class="row">
				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<select name="professionTypeId" id="floatingInput-5"
							class="form-control form-select input-2 profession-type-select"
							onchange="getVendors()">
							<option value="0" hidden>Select Profession</option>
							<c:forEach items="${professionList }" var="profession">
								<option value="${profession.healthProfessionalId }">${profession.professionName }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<select name="businessTypeId" id="floatingInput-29"
							class="form-control form-select input-2 business-type-select-class"
							onchange="getSelectedVendorDetails()">
							<option value="0" hidden>Select Business</option>
						</select>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2"
							name="businessContactNumber" id="business-contact-orders"
							placeholder="Business Contact" readonly="readonly"
							autocomplete="off"> <label for="business-contact-orders">Business
							Contact</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="email" class="form-control input-2"
							name="businessEmailContact" id="business-email-orders"
							placeholder="Email" autocomplete="off" readonly="readonly">
						<label for="business-email-orders">Email</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2"
							name="businessFaxNumber" id="business-fax-orders"
							placeholder="Fax Number" autocomplete="off" readonly="readonly">
						<label for="business-fax-orders">Fax Number</label>
					</div>
				</div>

				<div class="col-12">
					<div class="form-floating">
						<!-- <div class="w-90 background-colour-white"> -->
						<div class="form-floating mb-3">
							<textarea class="form-control" placeholder="Leave a comment here"
								name="businessPrescriptionDetails"
								id="prescription-details-orders" style="height: 115px"></textarea>
							<label for="prescription-details-orders">Prescription or
								Order Details</label>
						</div>
						<!-- </div> -->
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<select name="refillsDetails" id="floatingInput-5"
							id="prescription-refills-orders"
							class="form-control form-select-btn form-select input-2">
							<option value="0">Not Required</option>
							<option value="1">1 Time</option>
							<option value="2">2 Time</option>
							<option value="3">3 Time</option>
						</select> <label for="floatingInput-5">Number of Refills</label>
					</div>
				</div>

				<input type="text" hidden name="orderRequestId" value=${reqId }>
				<div class="bottom-btns mt-3">
					<button type="submit" class="bottom-btns-submit shrink-btns">Submit</button>
					<button type="reset" class="bottom-btns-cancel shrink-btns">Cancel</button>
				</div>

			</div>
		</form>
	</div>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/order-details.js' />"></script>
</body>
</html>
<%@include file="footer-black.jsp"%>