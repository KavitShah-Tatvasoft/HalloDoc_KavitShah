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
	href="<c:url value='/resources/css/admin-my-profile.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<script src="<c:url value='/resources/js/admin-my-profile.js' />"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<title>Admin Profile</title>
</head>
<body>
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="top-patient-view-text">
			<span class="view-reservation-text">My Profile</span>
		</div>

		<form action="">

			<div class="row">
				<div class="col-12 mb-3 mt-3">
					<!--Admin Information col-->
					<span class="patient-text">Account Information</span>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-2 admin-details-row-1"
							placeholder="User Name" autocomplete="off" value="${username }" disabled> <label
							for="floatingInput-5">User Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2 admin-reset-pass"
							placeholder="Password" autocomplete="off" disabled> <label
							for="floatingInput-5">Password</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<select name="Refill" id="floatingInput-5"
							class="form-control form-select input-2 admin-details-row-1"
							disabled>
							<option value="Active" selected>Active</option>
							<option value="Not Active">Not Active</option>
						</select> <label for="floatingInput-5">Status</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp ">
						<select name="Refill" id="floatingInput-5"
							class="form-control admin-details-row-1 form-select input-2"
							disabled>
							<option value="Home">Master Admin</option>
							<option value="Home">Admin</option>
							<option value="Home">Provider</option>
						</select> <label for="floatingInput-5">Role</label>
					</div>
				</div>

				<div class="common-row-bottom-btn">
					<button type="button" onclick="resetPassword()"
						class="reset-pass-btn shirnk-btns" id="reset-btn-bottom">Reset
						Password</button>
				</div>

				<div class="reset-pass-cancel-flex" id="reset-pass-cancel-id">
					<button type="button" onclick="changePassword(),changePasswordAdmin()"
						class="edit-btn shirnk-btns">Change Password</button>
					<button type="reset" onclick="cancelChange()"
						class="cancel-btn shirnk-btns">Cancel</button>
				</div>
			</div>

			<div class="row">
				<div class="col-12 mb-3 mt-3">
					<!--Patient Information col-->
					<span class="patient-text">Administrator Information</span>
				</div>

				<div class="col-12 col-md-6">
					<!--First Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-2 admin-details-row-1 first-name-admin" disabled value="${adminOb.firstName }"
							id="floatingInput-2" placeholder="First Name" autocomplete="off">
						<label for="floatingInput-2">First Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 admin-details-row-1 last-name-admin" value="${adminOb.lastName }" disabled
							id="floatingInput-3" placeholder="Last Name" autocomplete="off">
						<label for="floatingInput-3">Last Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Email col-->
					<div class="form-floating mb-3 inp">
						<input type="email"
							class="form-control input-2" value="${adminEmail}" disabled
							id="floatingInput-5" placeholder="Email" autocomplete="off">
						<label for="floatingInput-5">Email</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Email col-->
					<div class="form-floating mb-3 inp">
						<input type="email"
							class="form-control input-2 " value="${adminEmail}" disabled
							id="floatingInput-5" placeholder="Confirmation Email"
							autocomplete="off"> <label for="floatingInput-5">Confirmation
							Email</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 phonecolheight">
						<input type="tel" value="${phoneNumber}" 
							class="form-control phoneflags phone phone-number-admin admin-details-row-1"
							disabled />
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="checkbox-flex">
						<c:forEach items="${regions}" var="region">
							<c:choose>
								<c:when test="${region.isSelected == 1}">
										<div class="single-checkbox-flex">
								<input type="checkbox" checked  name="${region.regionName}-${region.regionId}" value="${region.regionId}" id="${region.regionName}-${region.regionId}"
									class="admin-details-row-1 region-checkboxes" disabled> <span>${region.regionName}</span>
							</div>
									</c:when>
									<c:otherwise>
										<div class="single-checkbox-flex">
								<input type="checkbox" name="${region.regionName}-${region.regionId}" value="${region.regionId}" id="${region.regionName}-${region.regionId}"
									class="admin-details-row-1 region-checkboxes" disabled> <span>${region.regionName}</span>
							</div>
									</c:otherwise>
							</c:choose>
						
							
						</c:forEach>
					</div>
				</div>

				<div class="common-row-bottom-btn">
					<button type="button" class="edit-btn shirnk-btns" id="edit-btn-id"
						onclick="editAdminDetails1()">Edit</button>
				</div>

				<div class="save-reset-btns-flex" id="save-reset-btns-id">
					<button type="button" class="edit-btn shirnk-btns"
						onclick="saveAdminDetails1(), updateAdminContactDetails()">Save</button>
					<button type="reset" onclick="cancelAdminDetails1()"
						class="cancel-btn shirnk-btns">Cancel</button>
				</div>

			</div>


			<div class="row">
				<div class="col-12 mb-3 mt-3">
					<!--Patient Information col-->
					<span class="patient-text">Mailing & Billing Information</span>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-2 admin-address-1 admin-details-row-2" value="${adminOb.addressOne}" disabled
							placeholder="Address 1" autocomplete="off"> <label
							for="floatingInput-2">Address 1</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 admin-details-row-2 admin-address-2" value="${adminOb.addressTwo }" disabled
							placeholder="Address 2" autocomplete="off"> <label
							for="floatingInput-3">Address 2</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 admin-details-row-2 admin-address-city" value="${adminOb.city }" disabled
							placeholder="City" autocomplete="off"> <label
							for="floatingInput-3">City</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<select name="Refill" id="floatingInput-5"
							class="form-control form-select input-2 admin-details-row-2 admin-address-region"
							disabled>
							
							<c:set var="userRegion" value="${adminOb.regionId}" />
							
							<c:forEach items="${regionList}" var="region">
								<c:choose>
								<c:when test="${region.regionId == userRegion}">
										<option value="${region.regionId}" selected>${region.name }</option>
									</c:when>
									<c:otherwise>
										<option value="${region.regionId}">${region.name}</option>
									</c:otherwise>
								</c:choose>
								</c:forEach>
						</select> <label for="floatingInput-5">State</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" value="${adminOb.zip }"
							class="form-control input-1 admin-details-row-2 admin-address-zipcode" disabled
							placeholder="Zip" autocomplete="off"> <label
							for="floatingInput-3">Zip</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 phonecolheight phonecolheight-1">
						<input type="tel" value="${adminOb.altPhone }"
							class="form-control phoneflags phone admin-details-row-2 admin-address-phone"
							disabled />
					</div>
				</div>

				<div class="common-row-bottom-btn mb-4">
					<button class="edit-btn shirnk-btns" type="button"
						id="edit-btn-id-1" onclick="editAdminDetails2()">Edit</button>
				</div>

				<div class="save-reset-btns-flex" id="save-reset-btns-id-1">
					<button type="button"
						class="edit-btn margin-top-remove shirnk-btns"
						onclick="saveAdminDetails2(), updateAdminAdress()">Save</button>
					<button type="reset" onclick="cancelAdminDetails2()"
						class="cancel-btn margin-top-remove shirnk-btns">Cancel</button>
				</div>

			</div>

		</form>
	</div>

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

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>

</body>
</html>