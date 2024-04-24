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
	href="<c:url value='/resources/css/close-case.css' />">
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
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<title>Close Case</title>
</head>
<body>
	<div
		class="container-fluid patient-form shadow bg-white footer-container rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="top-patient-view-text">
			<span class="view-reservation-text">Close Case</span>
		</div>

		<div class="row padding-top-extra">
			<div class="patient-name-invoice-flex">
				<div>
					<div class="col-12 small-text text">Patient Name</div>

					<div class="col-12">
						<span class="blue-text text">${ptName }</span> <span
							class="text case-id-text">(${confNumber })</span>
					</div>
				</div>
			</div>

			<div class="mt-3">
				<span class="document-text">Documents</span>
			</div>
		</div>

		<div class="row">
			<table class="table align-middle">
				<thead>
					<tr class="table-active">
						<th scope="col" class="width-col-1"></th>
						<th scope="col" class="width-col-2"><span>Upload Date</span><img
							src="<c:url value='/resources/images/arrow-up.svg' />"
							class="up-arrow-img" alt=""></th>
						<th scope="col" class="width-col-3 text-align-center">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${docList}" var="list">
						<tr>
							<td class="pdf-name-flex"><img
								src="<c:url value='/resources/images/pdf.png' />"
								class="pdf-img" alt=""><span>${list.filename}</span></td>
							<td>${list.createdDate}</td>
							<td><a href="${list.url}" download="${list.filename}" class="cloud-download-btn">
									<img
										src="<c:url value='/resources/images/cloud-arrow-down.svg' />"
										class="cloud-btn-down" alt="">
								</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<hr class="mt-3 mobile-cards">
			<c:forEach items="${docList}" var="list">
			<div class="row mobile-cards">
				<div class="col-12 single-mobile-card">
					<div class="mobile-checkbox-name-flex">
						<span class="pdf-name-flex"><img
							src="<c:url value='/resources/images/pdf.png' />" class="pdf-img"
							alt=""><span>${list.filename}</span></span>
					</div>
					<div class="date-col common-margin-top">${list.createdDate}</div>
					<a href="${list.url}" download="${list.filename}" type="button" class="cloud-download-btn common-margin-top">
						<img
							src="<c:url value='/resources/images/cloud-arrow-down.svg' />"
							class="cloud-btn-down" alt="">
					</a>
					<button class="cloud-download-btn common-margin-top" onclick="showDocument('${list.url}')">
						<img
							src="<c:url value='/resources/images/cloud-arrow-down.svg' />"
							class="cloud-btn-down" alt="">
					</button>
				</div>
			</div>
			<hr class="mt-3 mobile-cards">
			</c:forEach>
		</div>

		<form method="post" id="closeCaseForm">

			<div class="row mt-3">

				<span class="document-text mb-3">Patient Information</span>

				<div class="col-12 col-md-6">
					<!--First Name col-->
					<div class="form-floating mb-3 ">
						<input type="text" class="form-control input-2 close-case-details close-case-fName" id="floatingInput-2" value="${userDetails.firstName}"
							disabled placeholder="First Name" autocomplete="off"> <label
							for="floatingInput-2">First Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<!--Last Name col-->
					<div class="form-floating mb-3">
						<input type="text" class="form-control input-1 close-case-details close-case-lName" value="${userDetails.lastName}"
							disabled placeholder="Last Name" autocomplete="off"> <label
							for="floatingInput-3">Last Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp custom-date-input">
						<!--Date Picker col-->
						<input type="date" class="form-control input-1 close-case-details close-case-dob" value="${userDetails.dob}"
							disabled placeholder="Date Of Birth" autocomplete="off">
						<label for="floatingInput-4">Date of Birth</label> <img
							src="<c:url value='/resources/images/calendar4-week.svg' />"
							alt="" class="custom-date-icon">
					</div>
				</div>

				<div class="col-9 col-md-5">
					<!--Phone Number col-->
					<div class="form-floating mb-3 phonecolheight">
						<input type="tel" value="${userDetails.phoneNumber}"
							class="form-control phoneflags close-case-details close-case-pNumber" disabled
							id="phone" />
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
						<input type="email" value="${userDetails.email}"
							class="form-control input-2" disabled
							placeholder="Email" autocomplete="off"> <label
							for="floatingInput-5">Email</label>
					</div>
				</div>

				<input type="text" hidden value=${reqId } class="close-case-request-id">
				<div class="bottom-btns mt-3">
					<button type="submit" class="bottom-btns-edit shrink-btns"
						onclick="saveCloseCase()" id="save-btn-close-case">Save</button>
					<button type="reset" class="bottom-btns-cancel shrink-btns"
						onclick="cancelCloseCase()" id="cancel-btn-close-case">Cancel</button>
					<button type="button" class="bottom-btns-edit shrink-btns"
						onclick="editCloseCase()" id="edit-btn-close-case">Edit</button>
					<a type="button" href="../closeCaseRequest/${reqId }" class="bottom-btns-close shrink-btns"
						id="close-btn-close-case">Close Case</a>
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
	<script src="<c:url value='/resources/js/close-case.js' />"></script>

</body>
</html>

<%@include file="footer-black.jsp"%>