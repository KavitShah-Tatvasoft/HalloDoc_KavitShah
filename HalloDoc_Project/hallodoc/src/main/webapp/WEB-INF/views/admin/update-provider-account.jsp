<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
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
	href="<c:url value='/resources/css/edit-physician-account.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<script src="<c:url value='/resources/js/edit-physician-account.js' />"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<title>Physician Profile</title>
</head>
<body>
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="top-patient-view-text">
			<span class="view-reservation-text">Edit Physician Account</span>
		</div>

		<div class="row">
			<div class="col-12 mb-3 mt-3">
				<!--Patient Information col-->
				<span class="patient-text">Account Information</span>
			</div>

			<input type="text" name="pId" class="pId"
				value="${providerData.pId }" hidden>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2 pUsername"
						value="${providerData.pUsername }" name="pUsername" disabled
						placeholder="User Name" autocomplete="off"> <label
						for="floatingInput-5">User Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" name="pPassword"
						class="form-control pPassword input-2 provider-details-2" disabled
						placeholder="Password" autocomplete="off"> <label
						for="floatingInput-5">Password</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<select name="pStatus" id="floatingInput-5"
						class="form-control pStatus form-select input-2 provider-details-1"
						disabled>
						<c:set var="userStatus" value="${providerData.pStatus }" />
						<c:choose>
							<c:when test="${providerData.pStatus == 1}">
								<option value="1" selected>Active</option>
								<option value="0">Not Active</option>
							</c:when>
							<c:otherwise>
								<option value="0" selected>Not Active</option>
								<option value="1">Active</option>
							</c:otherwise>
						</c:choose>

					</select> <label for="floatingInput-5">Status</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<select name="pRole" id="floatingInput-5"
						class="form-control pRole form-select input-2 provider-details-1"
						disabled>
						<c:forEach items="${rolesDto }" var="role">
							<option value="${role.roleId }">${role.roleName }</option>
						</c:forEach>
					</select> <label for="floatingInput-5">Role</label>
				</div>
			</div>

			<div class="common-row-bottom-btn">
				<button type="button" id="save-btn-1"
					onclick="saveChange1(),saveRoleStatus()"
					class="edit-btn extra-margin-right-edit">Save</button>
				<button type="button" id="save-btn-2"
					onclick="savePassword(),persistPassword()"
					class="edit-btn extra-margin-right-edit">Save</button>
				<button type="reset" id="cancel-btn-2" onclick="savePassword()"
					class="cancel-btn extra-margin-right-edit">Cancel</button>
				<button type="reset" id="cancel-btn-12" onclick="cancelChange1()"
					class="cancel-btn extra-margin-right-edit">Cancel</button>
				<button class="edit-btn extra-margin-right-edit"
					onclick="editDetails1()" id="edit-btn-1">Edit</button>
				<button class="reset-pass-btn" id="password-btn-12"
					onclick="resetPassword()">Reset Password</button>
			</div>


		</div>

		<div class="row">
			<div class="col-12 mb-3 mt-3">
				<!--Patient Information col-->
				<span class="patient-text">Physician Information</span>
			</div>

			<div class="col-12 col-md-6">
				<!--First Name col-->
				<div class="form-floating mb-3 inp">
					<input type="text"
						class="form-control input-2 pFirstName common-class-info "
						disabled name="pFirstName" value="${providerData.pFirstName }"
						id="floatingInput-2" placeholder="First Name" autocomplete="off">
					<label for="floatingInput-2">First Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Last Name col-->
				<div class="form-floating mb-3 inp">
					<input type="text"
						class="form-control input-1 pLastName common-class-info" disabled
						name="pLastName" value="${providerData.pLastName }"
						id="floatingInput-3" placeholder="Last Name" autocomplete="off">
					<label for="floatingInput-3">Last Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Email col-->
				<div class="form-floating mb-3 inp">
					<input type="email" name="pEmail" value="${providerData.pEmail } "
						class="pEmail form-control input-2" disabled id="floatingInput-5"
						placeholder="Email" autocomplete="off"> <label
						for="floatingInput-5">Email</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Phone Number col-->
				<div class="form-floating mb-3  phonecolheight-1">
					<input type="tel" name="pPhone" value="${providerData.pPhone }"
						class="pPhone form-control phoneflags phone common-class-info"
						disabled />
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Last Name col-->
				<div class="form-floating mb-3 inp">
					<input type="text" name="pLicense" value="${providerData.pLicense}"
						class="pLicense form-control input-1 common-class-info " disabled
						id="floatingInput-3" placeholder="Medical License #"
						autocomplete="off"> <label for="floatingInput-3">Medical
						License #</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Last Name col-->
				<div class="form-floating mb-3 inp">
					<input type="text"
						class="form-control input-1 pNPI common-class-info" disabled
						name="pNPI" value="${providerData.pNPI}" id="floatingInput-3"
						placeholder="NPI Number" autocomplete="off"> <label
						for="floatingInput-3">NPI Number</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Email col-->
				<div class="form-floating mb-3 inp">
					<input type="email"
						class="form-control input-2 pSyncEmail common-class-info" disabled
						name="pSyncEmail" id="floatingInput-5"
						value="${providerData.pSyncEmail}"
						placeholder="Synchronization Email Address" autocomplete="off">
					<label for="floatingInput-5">Synchronization Email Address</label>
				</div>
			</div>


			<div class="col-12 col-md-6">
				<div class="checkbox-flex">

					<c:forEach items="${listRegions}" var="region">
						<div class="single-checkbox-flex">
							<input type="checkbox" value="${region.regionId }" name="region"
								class="common-class-info" disabled
								<c:forEach items="${providerData.pRegions}" var="adRegion">
											<c:if test = "${adRegion.regionId == region.regionId}">checked</c:if>
								</c:forEach>
								id="${region.regionId }${region.name}"> <label
								for="${region.regionId }${region.name}">${region.name }</label>
						</div>
					</c:forEach>
				</div>
			</div>

			<input type="text" name="pRegions" class="pRegions" hidden>

			<div class="common-row-bottom-btn bottom-btns-mailing-flex">
				<button class="edit-btn shrink-btns"
					onclick="editInformationDetails()" id="mailing-edit">Edit</button>
				<button class="edit-btn shrink-btns"
					onclick="saveInformationDetails(),editProviderInformation()"
					id="mailing-save">Save</button>
				<button class="cancel-btn shrink-btns"
					onclick="saveInformationDetails()" id="mailing-cancel">Cancel</button>
			</div>

		</div>


		<div class="row">
			<div class="col-12 mb-3 mt-3">
				<!--Patient Information col-->
				<span class="patient-text">Mailing & Billing Information</span>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" name="pAddressOne"
						value="${providerData.pAddressOne}"
						class="form-control input-2 pAddressOne common-class-mailing"
						disabled id="floatingInput-2" placeholder="Address 1"
						autocomplete="off"> <label for="floatingInput-2">Address
						1</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" name="pAddressTwo"
						value="${providerData.pAddressTwo}"
						class="pAddressTwo form-control input-1 common-class-mailing"
						disabled id="floatingInput-3" placeholder="Address 2"
						autocomplete="off"> <label for="floatingInput-3">Address
						2</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" name="pCity"
						class=" pCity form-control input-1 common-class-mailing" disabled
						value="${providerData.pCity}" id="floatingInput-3"
						placeholder="City" autocomplete="off"> <label
						for="floatingInput-3">City</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<c:set var="userState" value="${providerData.pState}" />
					<select name="pState" id="floatingInput-5 "
						class="pState form-control form-select input-2 common-class-mailing"
						disabled>

						<c:forEach items="${listRegions}" var="region">

							<c:choose>
								<c:when test="${region.regionId == userState}">
									<option value="${region.regionId}" selected>${region.name}</option>
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
					<input type="text" name="pZip"
						class="form-control input-1 pZip common-class-mailing" disabled
						value="${providerData.pZip}" id="floatingInput-3"
						placeholder="Zip" autocomplete="off"> <label
						for="floatingInput-3">Zip</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Phone Number col-->
				<div class="form-floating  mb-3 phonecolheight phonecolheight-1">
					<input type="tel" name="pMPhone" value="${providerData.pMPhone}"
						class="pMPhone form-control phoneflags phone common-class-mailing"
						disabled />
				</div>
			</div>

			<div class="common-row-bottom-btn bottom-btns-mailing-flex mb-4">
				<button class="edit-btn shrink-btns " onclick="editMailingDetails()"
					id="billing-edit">Edit</button>
				<button class="edit-btn shrink-btns"
					onclick="saveMailingDetails(),editMailingInformation()"
					id="billing-save">Save</button>
				<button class="cancel-btn shrink-btns"
					onclick="saveMailingDetails()" id="billing-cancel">Cancel</button>
			</div>
		</div>

		<div class="row">
			<div class="col-12 mb-3 mt-3">
				<span class="patient-text">Provider Profile</span>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" name="pBusinessName"
						value="${providerData.pBusinessName}"
						class="pBusinessName form-control input-1" id="floatingInput-3"
						placeholder="Business Name" autocomplete="off"> <label
						for="floatingInput-3">Business Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" name="pBusinessWebsite"
						value="${providerData.pBusinessName}"
						class="pBusinessWebsite form-control input-1" id="floatingInput-3"
						placeholder="Business Website" autocomplete="off"> <label
						for="floatingInput-3">Business Website</label>
				</div>
			</div>

			<!-- <div class="col-12 col-md-6">
                <div class="input-group mb-3">
                    <input type="file" class="form-control" title="Select Photo" id="inputGroupFile02">
                    <label class="input-group-text file-upload-btn" for="inputGroupFile02"><img src="./SRS Screen Shorts/cloud-arrow-up-white.svg"  class="upload-image-file" alt=""><span class="upload-txt">Upload</span></label>
                </div>
            </div> -->

			<div class="col-12 col-md-6 mb-3">
				<div class="input-group ">
					<input id="selected-file-by-patietent " type="text"
						class="form-control p-2 custom-upload " placeholder="Select Photo"
						disabled>
					<button class="btn-color-for-upload" type="button" id="upload">
						<img
							src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
							class="up-cloud-upload" alt=""> <span
							class="for-remove-upload ">Upload</span>
					</button>
					<input id="patietent-file-input" multiple
						class="file-input-hover-effect pPhoto" name="pPhoto" type="file"
						style="position: absolute; right: -8px; top: 0.5rem; opacity: 0; width: 100%;">
				</div>
			</div>

			<!-- <div class="col-12 col-md-6">
                <div class="input-group mb-3">
                    <input type="file" class="form-control" title="Select Photo" id="inputGroupFile02">
                    <label class="input-group-text file-upload-btn" for="inputGroupFile02"><img src="./SRS Screen Shorts/cloud-arrow-up-white.svg"  class="upload-image-file" alt=""><span class="upload-txt">Upload</span></label>
                </div>
            </div> -->

			<div class="col-12 col-md-6 mb-3">
				<div class="upload-create-flex">

					<div class="input-group upload-class">
						<input id="selected-file-by-patietent-1" type="text"
							class="form-control p-2 custom-upload"
							placeholder="Select Signature" disabled>
						<button class="btn-color-for-upload" type="button" id="upload">
							<img
								src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
								class="up-cloud-upload" alt=""> <span
								class="for-remove-upload ">Upload</span>
						</button>
						<input id="patietent-file-input-1" multiple
							class="file-input-hover-effect pSignature" name="pSignature"
							type="file"
							style="position: absolute; right: -8px; top: 0.5rem; opacity: 0; width: 95%;">
					</div>

				</div>
			</div>

			<div class="signatur-flex mb-2">
				<img src="./SRS Screen Shorts/signature-mock.png" alt="signature"
					class="signature-img">
			</div>

			<div class="col-12 inc-height">
				<div class="form-floating mb-4 ">
					<textarea name="" class="form-control input-1 inc-inp-height"
						id="floatingInput-1" placeholder="Admin Notes">${providerData.pNotes}</textarea>
					<label for="floatingInput-1" class="username-clr">Admin
						Notes</label>
				</div>
			</div>

			<div class="common-row-bottom-btn">
				<button class="edit-btn shrink-btns">Edit</button>
			</div>

			<div class="col-12">
				<hr class="hr-width">
			</div>
		</div>


		<div class="row">
			<div class="col-12 mb-3 mt-3">
				<!--Patient Information col-->
				<span class="patient-text">Onboarding</span>
			</div>

			<table class="ms-3">
				<tr>
					<th class="col-1-width"></th>
					<th class="col-2-width"></th>
					<th class="col-3-width"></th>
				</tr>
				<tr>
					<td class="align-baseline"><input type="checkbox"
						name="onboarding"></td>
					<td class="align-baseline"><span>Independent Contract
							Agreement</span></td>
					<td class="btns-flex align-baseline">
						<div>
							<button class="common-btn-onboarding">
								<span class="toggle-text">Upload</span><img
									src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
									class="toggle-img-btn" alt="">
							</button>
							</button>
						</div>
						<div>
							<button class="common-btn-onboarding">
								<span class="toggle-text">View</span><img
									src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
									class="toggle-img-btn" alt="">
							</button>
						</div>
					</td>
				</tr>

				<tr>
					<td class="align-baseline"><input type="checkbox"
						name="onboarding"></td>
					<td class="align-baseline"><span>Background Check</span></td>
					<td class="btns-flex align-baseline">
						<div>
							<button class="common-btn-onboarding">
								<span class="toggle-text">Upload</span><img
									src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
									class="toggle-img-btn" alt="">
							</button>
							</button>
						</div>
						<div>
							<button class="common-btn-onboarding">
								<span class="toggle-text">View</span><img
									src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
									class="toggle-img-btn" alt="">
							</button>
						</div>
					</td>
				</tr>

				<tr>
					<td class="align-baseline"><input type="checkbox"
						name="onboarding"></td>
					<td class="align-baseline"><span>HIPAA Complaince</span></td>
					<td class="btns-flex align-baseline">
						<div>
							<button class="common-btn-onboarding">
								<span class="toggle-text">Upload</span><img
									src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
									class="toggle-img-btn" alt="">
							</button>
							</button>
						</div>
						<div>
							<button class="common-btn-onboarding">
								<span class="toggle-text">View</span><img
									src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
									class="toggle-img-btn" alt="">
							</button>
						</div>
					</td>
				</tr>

				<tr>
					<td class="align-baseline"><input type="checkbox"
						name="onboarding"></td>
					<td class="align-baseline"><span>Non-Disclosure
							Agreement</span></td>
					<td class="btns-flex align-baseline">
						<div>
							<button class="common-btn-onboarding">
								<span class="toggle-text">Upload</span><img
									src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
									class="toggle-img-btn" alt="">
							</button>
							</button>
						</div>
					</td>
				</tr>

				<tr>
					<td class="align-baseline"><input type="checkbox"
						name="onboarding"></td>
					<td class="align-baseline"><span>License Document</span></td>
					<td class="btns-flex align-baseline">
						<div>
							<button class="common-btn-onboarding">
								<span class="toggle-text">Upload</span><img
									src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
									class="toggle-img-btn" alt="">
							</button>
							</button>
						</div>
					</td>
				</tr>
			</table>

			<div class="col-12">
				<hr class="hr-width">
			</div>

			<div class="common-row-bottom-btn mb-4">
				<a href="../deleteProviderAccount/${providerData.pId }"
					class="delete-btn shrink-btns">Delete Account</a>
			</div>


		</div>



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
<%@include file="footer-black.jsp"%>