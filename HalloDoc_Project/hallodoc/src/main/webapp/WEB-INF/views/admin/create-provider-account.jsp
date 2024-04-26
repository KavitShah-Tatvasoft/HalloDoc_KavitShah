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
	href="<c:url value='/resources/css/create-provider-account.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="./css/<c:url value='/resources/css/pop-ups.css' />.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<title>Create Physician Profile</title>
</head>
<body>
	<div
		class="container-fluid patient-form footer-container shadow bg-white rounded relative-position extra-margin">

		<div class="back-btn-top" role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="top-patient-view-text">
			<span class="view-reservation-text">Create Provider Account</span>
		</div>
		<form action="createNewProviderAccount" method="post" id="createProviderForm" enctype="multipart/form-data">
			<div class="row">
				<div class="col-12 mb-3 mt-3">
					<!--Patient Information col-->
					<span class="patient-text">Account Information</span>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2 pUsername" onblur="checkUsername()"
							id="floatingInput-5" name="pUsername" placeholder="User Name"
							autocomplete="off"> <label for="floatingInput-5">User
							Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<select name="pStatus" id="floatingInput-5"
							class="form-control form-select input-2 pStatus">
							<option value="1">Active</option>
							<option value="0">Not Active</option>
						</select> <label for="floatingInput-5">Status</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<select name="pRole" id="floatingInput-5"
							class="form-control form-select input-2 pRole">
							<option value="1">Admin</option>
							<option value="3">Patient</option>
							<option value="2">Provider</option>
						</select> <label for="floatingInput-5">Role</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2 pPassword" name="pPassword"
							id="floatingInput-5" placeholder="Password" autocomplete="off">
						<label for="floatingInput-5">Password</label>
					</div>
				</div>

			</div>

			<div class="row">
				<div class="col-12 mb-3 mt-3">
					<!--Patient Information col-->
					<span class="patient-text">Physician Information</span>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2 pFirstName"
							name="pFirstName" id="floatingInput-5" placeholder="First Name"
							autocomplete="off"> <label for="floatingInput-5">First
							Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-2 pLastName"
							name="pLastName" id="floatingInput-5" placeholder="Last Name"
							autocomplete="off"> <label for="floatingInput-5">Last
							Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Email col-->
					<div class="form-floating mb-3 inp">
						<input type="email" class="form-control input-2 pEmail"
							name="pEmail" id="floatingInput-5" placeholder="Email"
							autocomplete="off"> <label for="floatingInput-5">Email</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 phonecolheight">
						<input type="tel" class="form-control phoneflags phone pPhone"
							name="pPhone" />
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 medical-marging-top pLicense"
							name="pLicense" id="floatingInput-3"
							placeholder="Medical License #" autocomplete="off"> <label
							for="floatingInput-3">Medical License #</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1 pNPI"
							id="floatingInput-3" name="pNPI" placeholder="NPI Number"
							autocomplete="off"> <label for="floatingInput-3">NPI
							Number</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Email col-->
					<div class="form-floating mb-3 inp">
						<input type="email" class="form-control input-2 pSyncEmail"
							name="pSyncEmail" id="floatingInput-5"
							placeholder="Synchronization Email Address" autocomplete="off">
						<label for="floatingInput-5">Synchronization Email Address</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="checkbox-flex">

						<c:forEach items="${regionList }" var="region">

							<div class="single-checkbox-flex">
								<input onchange="getCheckBoxDetails()" type="checkbox" value="${region.regionId }" id="${region.name}${region.regionId }">
								<label for="${region.name}${region.regionId }">${region.name}</label>
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
						<input type="text" class="form-control input-2 pAddressOne"
							name="pAddressOne" id="floatingInput-2" placeholder="Address 1"
							autocomplete="off"> <label for="floatingInput-2">Address
							1</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1 pAddressTwo"
							name="pAddressTwo" id="floatingInput-3" placeholder="Address 2"
							autocomplete="off"> <label for="floatingInput-3">Address
							2</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1 pCity" name="pCity"
							id="floatingInput-3" placeholder="City" autocomplete="off">
						<label for="floatingInput-3">City</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<select name="pState" id="floatingInput-5"
							class="form-control form-select input-2 pState">
							<c:forEach items="${regionList }" var="regions">

								<option value="${regions.regionId }">${regions.name}</option>

							</c:forEach>

						</select> <label for="floatingInput-5">State</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1 pZip" name="pZip"
							id="floatingInput-3" placeholder="Zip" autocomplete="off">
						<label for="floatingInput-3">Zip</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Phone Number col-->
					<div class="form-floating mb-3 phonecolheight phonecolheight-1">
						<input type="tel" class="form-control phoneflags phone pMPhone"
							name=pMPhone />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12 mb-3 mt-3">
					<span class="patient-text">Provider Profile</span>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1 pBusinessName"
							name="pBusinessName" id="floatingInput-3"
							placeholder="Business Name" autocomplete="off"> <label
							for="floatingInput-3">Business Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1 pBusinessWebsite"
							name="pBusinessWebsite" id="floatingInput-3"
							placeholder="Business Website" autocomplete="off"> <label
							for="floatingInput-3">Business Website</label>
					</div>
				</div>



				<div class="col-12 col-md-6 mb-3">
					<div class="input-group ">
						<input id="selected-file-by-patietent" type="text"
							class="form-control p-2 custom-upload" 
							placeholder="Select Photo" disabled>
						<button class="btn-color-for-upload" type="button" id="upload">
							<img
								src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
								class="up-cloud-upload" alt=""> <span
								class="for-remove-upload ">Upload</span>
						</button>
						<input id="patietent-file-input" multiple
							class="file-input-hover-effect pPhoto" name="pPhoto"
							type="file"
							style="position: absolute; right: -8px; top: 0.5rem; opacity: 0; width: 100%;">
					</div>
				</div>

				<div class="col-12 col-md-6 mb-3">
					<div class="">

						<div class="input-group">
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
								class="file-input-hover-effect pSignature" name="pSignature" type="file"
								style="position: absolute; right: -8px; top: 0.5rem; opacity: 0; width: 95%;">
						</div>

						<!--                     <div class="create-class"> -->
						<%--                         <a href="#" role="button" class="create-btn-provider-profile"><img src="<c:url value='/resources/images/pencil-white.svg' />" alt=""><span class="create-text">Create</span></a> --%>
						<!--                     </div> -->

					</div>
				</div>

				<div class="signatur-flex">
					<img src="<c:url value='/resources/images/signature-mock.png' />"
						alt="signature" class="signature-img">
				</div>

				<div class="col-12 inc-height">
					<div class="form-floating mb-4 ">
						<textarea name="pNotes" class="form-control input-1 pNotes" 
							style="height: 140px; margin-top: 20px;" id="floatingInput-1"
							placeholder="Admin Notes"></textarea>
						<label for="floatingInput-1" class="username-clr">Admin
							Notes</label>
					</div>
				</div>

				<div class="col-12 mt-5">
					<hr class="hr-width">
				</div>


				<table class="mt-2">
					<tr>
						<th class="col-width-1"></th>
						<th class="col-width-2"></th>
					</tr>
					<tr class="border-bottom-mobile">
						<td><span class="doc-type-text">Provider Agreement</span></td>
						<td>
							<div class="view-doc-btns-flex">
								<input type="file" class="bottom-upload-hide pAgreement"
									name="pAgreement" id="upload-1"> <label for="upload-1">
									<a class="view-doc-btn view-doc-btn-upload" role="button">
										<span class="view-txt">Upload</span><img
										src="./SRS Screen Shorts/cloud-arrow-up-white.svg"
										class="view-img-1" alt="">
								</a>
								</label> <a class="view-doc-btn "><span class="view-txt">View</span><img
									src="./SRS Screen Shorts/password-eye-white.svg"
									class="view-img" alt=""></a>
							</div>
						</td>
					</tr>
					<tr>
						<td><span class="doc-type-text">HIPAA Complaince</span></td>
						<td>
							<div class="view-doc-btns-flex">
								<input type="file" class="bottom-upload-hide pHipaa"
									name="pHipaa" id="upload-2"> <label for="upload-2">
									<a class="view-doc-btn view-doc-btn-upload" role="button">
										<span class="view-txt">Upload</span><img
										src="./SRS Screen Shorts/cloud-arrow-up-white.svg"
										class="view-img-1" alt="">
								</a>
								</label> <a class="view-doc-btn"><span class="view-txt">View</span><img
									src="./SRS Screen Shorts/password-eye-white.svg"
									class="view-img" alt=""></a>
							</div>
						</td>
					</tr>

					<tr>
						<td><span class="doc-type-text">Background Check</span></td>
						<td>
							<div class="view-doc-btns-flex">
								<input type="file" class="bottom-upload-hide pBackgroundCheck"
									name="pBackgroundCheck" id="upload-3"> <label
									for="upload-3"> <a
									class="view-doc-btn view-doc-btn-upload" role="button"> <span
										class="view-txt">Upload</span><img
										src="./SRS Screen Shorts/cloud-arrow-up-white.svg"
										class="view-img-1" alt="">
								</a>
								</label> <a class="view-doc-btn"><span class="view-txt">View</span><img
									src="./SRS Screen Shorts/password-eye-white.svg"
									class="view-img" alt=""></a>
							</div>
						</td>
					</tr>

					<tr>
						<td><span class="doc-type-text">Non Disclosure
								Agreement</span></td>
						<td>
							<div class="view-doc-btns-flex">
								<input type="file" class="bottom-upload-hide pNda" name="pNda"
									id="upload-4"> <label for="upload-4"> <a
									class="view-doc-btn view-doc-btn-upload" role="button"> <span
										class="view-txt">Upload</span><img
										src="./SRS Screen Shorts/cloud-arrow-up-white.svg"
										class="view-img-1" alt="">
								</a>
								</label> <a class="view-doc-btn"><span class="view-txt">View</span><img
									src="./SRS Screen Shorts/password-eye-white.svg"
									class="view-img" alt=""></a>
							</div>
						</td>
					</tr>

					<tr>
						<td><span class="doc-type-text">License Document</span></td>
						<td>
							<div class="view-doc-btns-flex">
								<input type="file" class="bottom-upload-hide pLicenseDoc"
									name="pLicenseDoc" id="upload-5"> <label for="upload-5">
									<a class="view-doc-btn view-doc-btn-upload" role="button">
										<span class="view-txt">Upload</span><img
										src="./SRS Screen Shorts/cloud-arrow-up-white.svg"
										class="view-img-1" alt="">
								</a>
								</label> <a class="view-doc-btn"><span class="view-txt">View</span><img
									src="./SRS Screen Shorts/password-eye-white.svg"
									class="view-img" alt=""></a>
							</div>
						</td>
					</tr>
				</table>
				<input type="text" name="pRegions" class="pRegions" hidden>
				<div class="submission-btns-flex">
					<button role="submit" class="submission-create shrink-btns " id="create-provider-btn">Create</button> <button
						role="reset" class="submission-cancel shrink-btns">Cancel</button>
				</div>

			</div>

		</form>



	</div>

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

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/create-provider.js' />"></script>
	
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
				<div class="modal-body">
					<span class="modal-header-text">Need to send message to edit</span>
					<form action="">
						<textarea name="message"
							class="form-control cancel-pop-margin-top" id="" cols="12"
							rows="5" placeholder="Message"></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="send-btn">Send</button>
					<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

				</div>
			</div>
		</div>
	</div>

</body>
</html>
<%@include file="footer-black.jsp"%>