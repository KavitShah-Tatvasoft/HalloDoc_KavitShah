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
	href="<c:url value='/resources/css/add-business.css' />">
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

<title>Update Business</title>
</head>
<body>
	<div
		class="container-fluid patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="top-patient-view-text">
			<span class="view-reservation-text">Update Business</span>
		</div>
		<form action="../editBusinessRequest" , method="post">

			<input type="text" hidden value="${businessId}" name="businessId">

			<div class="row">
				<div class="col-12 mb-3 mt-3">
					<!--Patient Information col-->
					<span class="patient-text">Submit Information</span>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2"
							value="${healthProfessional.vendorName }" id="floatingInput-5"
							name="businessName" placeholder="Business Name"
							autocomplete="off"> <label for="floatingInput-5">Business
							Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<select name="businessProfession" id="floatingInput-5"
							class="form-control form-select input-2">
<!-- 							<option value="0" hidden>Select Profession</option> -->
							<c:set var="userProfession" value="${healthProfessional.healthProfessionalTypes.healthProfessionalId }" />
							<c:forEach items="${healthProfessionalTypes }" var="type">
								
								<c:choose>
									<c:when test="${type.healthProfessionalId == userProfession}">
										<option value="${type.healthProfessionalId }" selected>${type.professionName }</option>
									</c:when>
									<c:otherwise>
										<option value="${type.healthProfessionalId }">${type.professionName }</option>
									</c:otherwise>
								</c:choose>
								
<%-- 								<option value="${type.healthProfessionalId }">${type.professionName }</option> --%>

							</c:forEach>
						</select> <label for="floatingInput-5">Profession</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" name="businessFaxNumber"
							value="${healthProfessional.faxNumber }"
							class="form-control input-2" id="floatingInput-5"
							placeholder="Fax Number" autocomplete="off"> <label
							for="floatingInput-5">Fax Number</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 inp phonecolheight">
						<input name="businessPhone"
							value="${healthProfessional.phoneNumber }" type="tel"
							class="form-control phoneflags" id="phone" />
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 margin-top-mobile-email">
						<input type="email" value="${healthProfessional.email }"
							name="businessEmail" class="form-control input-2"
							id="floatingInput-5" placeholder="Email" autocomplete="off">
						<label for="floatingInput-5">Email</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" name="businessContact"
							value="${healthProfessional.businessContact }"
							class="form-control input-2" id="floatingInput-5"
							placeholder="Business Contact" autocomplete="off"> <label
							for="floatingInput-5">Business Contact</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input name="businessStreet"
							value="${healthProfessional.address }" type="text"
							class="form-control input-2" id="floatingInput-5"
							placeholder="Street" autocomplete="off"> <label
							for="floatingInput-5">Street</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input name="businessCity" type="text"
							value="${healthProfessional.city }" class="form-control input-2"
							id="floatingInput-5" placeholder="City" autocomplete="off">
						<label for="floatingInput-5">City</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<!-- 						<input type="text" class="form-control input-2" -->
						<!-- 							name="businessState" id="floatingInput-5" placeholder="State" -->
						<!-- 							autocomplete="off"> <label for="floatingInput-5">State</label> -->

						<select id="floatingInput-5" name="businessState"
							class="form-control form-select input-2">
							<c:set var="userState" value="${healthProfessional.state }" />
							<c:forEach items="${regionList }" var="region">
								<c:choose>
									<c:when test="${region.name == userState}">
										<option value="${region.regionId}" selected>${region.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${region.regionId }">${region.name}</option>
									</c:otherwise>
								</c:choose>

								<%-- 								<option value="${region.regionId }">${region.name }</option> --%>

							</c:forEach>

						</select> <label for="floatingInput-5">State</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2"
							value="${healthProfessional.zip}" name="businessZip"
							id="floatingInput-5" placeholder="Zip / Postal"
							autocomplete="off"> <label for="floatingInput-5">Zip
							/ Postal</label>
					</div>
				</div>
				<div class="common-row-bottom-btn mb-4">
					<button type="submit" class="edit-btn extra-margin-right-edit shrink-btns">Save</button>
					<button type="reset" class="reset-pass-btn shrink-btns">Cancel</button>
				</div>

			</div>
		</form>
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

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>


</body>
</html>
<%@include file="footer-black.jsp"%>