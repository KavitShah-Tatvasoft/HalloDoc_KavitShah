<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="provider-navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
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
	href="<c:url value='/resources/css/provider-my-profile.css' />">
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<title>Physician Profile</title>
</head>
<body>
		
	<div class="loader-container">
		<div class="loader"></div>
	</div>
	
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="top-patient-view-text">
			<span class="view-reservation-text">My Profile</span>
		</div>

		<div class="request-top-btn">
			<a class="request-admin-btn shrink-btns" role="button"
				data-bs-toggle="modal" data-bs-target="#request-admin">Request
				To Admin</a>
		</div>

		<div class="row">
			<div class="col-12 mb-3 mt-2">
				<!--Patient Information col-->
				<span class="patient-text">Account Information</span>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2"
						value=${providerData.pUsername } disabled id="floatingInput-5"
						placeholder="User Name" autocomplete="off"> <label
						for="floatingInput-5">User Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2 provider-reset-pass"
						disabled id="floatingInput-5" placeholder="Password"
						autocomplete="off"> <label for="floatingInput-5">Password</label>
				</div>
			</div>

			<div class="common-row-bottom-btn">
				<button type="button" onclick="resetPassword()"
					class="reset-pass-btn shirnk-btns" id="reset-btn-bottom">Reset
					Password</button>
			</div>

			<div class="provider-password-flex" id="cancel-password-change-flex">
				<button type="button"
					onclick="changePassword(),changePasswordProvider()"
					class="reset-pass-btn shirnk-btns " id="reset-btn-bottom">Change
					Password</button>

				<button type="button" onclick="cancelChange()"
					class="reset-pass-btn shirnk-btns" id="reset-btn-bottom">Cancel</button>
			</div>


		</div>

		<div class="row">
			<div class="col-12 mb-3 mt-3">
				<!--Patient Information col-->
				<span class="patient-text">Physician Information</span>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2"
						value=${providerData.pFirstName } disabled id="floatingInput-5"
						placeholder="First Name" autocomplete="off"> <label
						for="floatingInput-5">First Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2"
						value=${providerData.pLastName } disabled id="floatingInput-5"
						placeholder="Last Name" autocomplete="off"> <label
						for="floatingInput-5">Last Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Email col-->
				<div class="form-floating mb-3 inp">
					<input type="email" class="form-control input-2"
						value=${providerData.pEmail } disabled id="floatingInput-5"
						placeholder="Email" autocomplete="off"> <label
						for="floatingInput-5">Email</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Phone Number col-->
				<div class="form-floating mb-3 phonecolheight">
					<input type="tel" value=${providerData.pPhone } disabled
						class="form-control phoneflags phone" />
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Last Name col-->
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 medical-marging-top"
						value="${providerData.pLicense }" disabled id="floatingInput-3"
						placeholder="Medical License #" autocomplete="off"> <label
						for="floatingInput-3">Medical License #</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Last Name col-->
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1"
						value="${providerData.pNPI }" disabled id="floatingInput-3"
						placeholder="NPI Number" autocomplete="off"> <label
						for="floatingInput-3">NPI Number</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="checkbox-flex">
					<c:forEach items="${regionList}" var="region">
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
		</div>

		<div class="row">
			<div class="col-12 mb-3 mt-3">
				<!--Patient Information col-->
				<span class="patient-text">Mailing & Billing Information</span>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2"
						value="${providerData.pAddressOne }" disabled id="floatingInput-2"
						placeholder="Address 1" autocomplete="off"> <label
						for="floatingInput-2">Address 1</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1"
						value="${providerData.pAddressTwo}" disabled id="floatingInput-3"
						placeholder="Address 2" autocomplete="off"> <label
						for="floatingInput-3">Address 2</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1"
						value="${providerData.pCity }" disabled id="floatingInput-3"
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

						<c:forEach items="${regionList}" var="region">

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
					<input type="text" class="form-control input-1"
						value="${providerData.pZip }" disabled id="floatingInput-3"
						placeholder="Zip" autocomplete="off"> <label
						for="floatingInput-3">Zip</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Phone Number col-->
				<div class="form-floating mb-3 phonecolheight phonecolheight-1">
					<input type="tel" value="${providerData.pMPhone }" disabled
						class="form-control phoneflags phone" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-12 mb-3 mt-3">
				<span class="patient-text">Provider Profile</span>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1"
						value="${providerData.pBusinessName }" disabled
						id="floatingInput-3" placeholder="Business Name"
						autocomplete="off"> <label for="floatingInput-3">Business
						Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1"
						value="${providerData.pBusinessWebsite }" disabled
						id="floatingInput-3" placeholder="Business Website"
						autocomplete="off"> <label for="floatingInput-3">Business
						Website</label>
				</div>
			</div>

			<div class="col-12">
				<hr class="hr-width">
			</div>


			<table>
				<tr>
					<th class="col-width-1"></th>
					<th class="col-width-2"></th>
				</tr>
				<tr class="border-bottom-mobile">
					<td><span class="doc-type-text">Provider Agreement</span></td>

					<c:choose>
						<c:when test="${providerData.pAgreement == true}">
							<td>
								<button class="view-doc-btn shrink-btns"
									onclick="showDoc('${uploadPath}/Agreement_Document.pdf')">
									<span class="view-txt">View</span><img
										src="<c:url value='/resources/images/password-eye-white.svg' />"
										class="view-img" alt="">
								</button>
							</td>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>


				</tr>
				<tr>
					<td><span class="doc-type-text">HIPAA Complaince</span></td>
					<c:choose>
						<c:when test="${providerData.pHipaa == true}">
							<td>
								<button class="view-doc-btn shrink-btns"
									onclick="showDoc('${uploadPath}/HIPAA_Document.pdf')">
									<span class="view-txt">View</span><img
										src="<c:url value='/resources/images/password-eye-white.svg' />"
										class="view-img" alt="">
								</button>
							</td>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td><span class="doc-type-text">Background Check</span></td>
					<c:choose>
						<c:when test="${providerData.pBackgroundCheck == true}">
							<td>
								<button class="view-doc-btn shrink-btns"
									onclick="showDoc('${uploadPath}/Background_Check_Document.pdf')">
									<span class="view-txt">View</span><img
										src="<c:url value='/resources/images/password-eye-white.svg' />"
										class="view-img" alt="">
								</button>
							</td>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td><span class="doc-type-text">Non Disclouser
							Agreement</span></td>
					<c:choose>
						<c:when test="${providerData.pNda == true}">
							<td>
								<button class="view-doc-btn shrink-btns"
									onclick="showDoc('${uploadPath}/NDS_Document.pdf')">
									<span class="view-txt">View</span><img
										src="<c:url value='/resources/images/password-eye-white.svg' />"
										class="view-img" alt="">
								</button>
							</td>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td><span class="doc-type-text">License Document</span></td>
					<c:choose>
						<c:when test="${providerData.pLicenseDoc == true}">
							<td>
								<button class="view-doc-btn shrink-btns"
									onclick="showDoc('${uploadPath}/License_Document.pdf')">
									<span class="view-txt">View</span><img
										src="<c:url value='/resources/images/password-eye-white.svg' />"
										class="view-img" alt="">
								</button>
							</td>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</div>

	</div>

	<footer>
		<div class="footer-flex">
			<a href="#">Terms of Condition</a><span>|</span><a href="#">Privacy
				Policy</a>
		</div>
	</footer>


	<script>
        const phoneInputField = document.getElementsByClassName("phone");
    for (let i = 0; i < phoneInputField.length; ++i) {
        const phoneInput = window.intlTelInput(phoneInputField[i], {
            utilsScript:
                "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
        });
    }


    const fileInput = document.getElementById('patietent-file-input');
        const fileNameField = document.getElementById('selected-file-by-patietent');

        // Listen for the change event on the file input element
        fileInput.addEventListener('change', (event) => {
            // Get the selected file(s)
            const files = event.target.files;

            // If one or more files are selected
            if (files.length > 0) {
                // Get the name of the first file
                const fileName = files[0].name;

                // Update the value of the input field with the file name
                fileNameField.value = fileName;
            } else {
                // If no files are selected, clear the input field
                fileNameField.value = '';
            }
        });

        const fileInput_1 = document.getElementById('patietent-file-input-1');
        const fileNameField_1 = document.getElementById('selected-file-by-patietent-1');

        // Listen for the change event on the file input element
        fileInput_1.addEventListener('change', (event) => {
            // Get the selected file(s)
            const files = event.target.files;

            // If one or more files are selected
            if (files.length > 0) {
                // Get the name of the first file
                const fileName = files[0].name;

                // Update the value of the input field with the file name
                fileNameField_1.value = fileName;
            } else {
                // If no files are selected, clear the input field
                fileNameField_1.value = '';
            }
        });
        
       

      </script>



	<!-- pop-ups -->

	<div class="modal fade" id="request-admin" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content cancel-pop-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Request
						to Administrator</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form method="post">
					<div class="modal-body">
						<span class="modal-header-text">Need to send message to
							edit</span>
						<textarea name="message"
							class="form-control cancel-pop-margin-top"
							id="request-admin-description" cols="12" rows="5"
							placeholder="Message"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="sendRequestToAdmin()" class="send-btn">Send</button>
						<button type="button" class="cancel-btn" id="cancel-btn-admin-request" data-bs-dismiss="modal">Cancel</button>

					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="<c:url value='/resources/js/loader.js' />"></script>
	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/provider-profile.js' />"></script>
</body>
</html>