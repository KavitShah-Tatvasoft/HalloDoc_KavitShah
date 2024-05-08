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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/loader.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/conclude-care.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<title>View Uploads</title>
</head>
<body>
	<div class="loader-container">
		<div class="loader"></div>
	</div>


	<div
		class="container-fluid footer-container main-container relative-pos-class extra-padding-main-container">
		<div class="main-container-top-text">Conclude Care</div>


		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt="" class="back-icon-top"> Back
		</div>


		<div class="row p-4">
			<div class="col-12 small-text text">Patient Name</div>

			<div class="col-12">
				<span class="blue-text text">${ptName}</span> <span
					class="text case-id-text">(${confNumber})</span>
			</div>

			<div class="col-12 doc-download-flex">
				<div class="doc-text">Encounter Forms</div>
				<div class="    ">

					<form action="../upload-files-conclude-care" method="post"
						enctype="multipart/form-data" id="conclude-care-form">
						<input type="text" hidden value="${reqId}" name="reqId"> <input
							type="file" hidden name="fileupload" id="fileupload"
							onchange="submitForm()"> <label for='fileupload'>
							<button class="upload-conclude-care"
								onclick="document.getElementById('fileupload').click();">
								<img
									src="<c:url value='/resources/images/cloud-arrow-up-blue.svg' />"
									class="download-all-image" alt=""><span
									class="download-all-text">Upload</span>
							</button>
						</label>
					</form>
				</div>

			</div>
		</div>

		<div class="row mobile-cards">
			<c:forEach items="${docList }" var="document">
				<hr>
				<div class="col-12 single-mobile-card">
					<div class="mobile-checkbox-name-flex">
						<span class="pdf-name-flex"><img
							src="<c:url value='/resources/images/pdf.png' />" class="pdf-img"
							alt=""><span>${document.filename }</span></span>
					</div>
					<div class="accordion-div-flex">
						<a type="button" href="${document.url}" download="${document.filename }" class="cloud-download-btn common-margin-top" id="btn-b-1">
							<img
								src="<c:url value='/resources/images/cloud-arrow-down.svg' />"
								class="cloud-btn-down" alt="">
						</a>
						<button onclick="deleteFile(${document.requestWiseFileId})" class="cloud-download-btn common-margin-top" id="btn-b-2">
							<img src="<c:url value='/resources/images/Dustbin.svg' />"
								class="cloud-btn-down" alt="">
						</button>
					</div>

				</div>
			</c:forEach>
		</div>

		<div class="row">
			<div class="col-12">
				<table class="table align-middle">
					<thead>
						<tr class="table-active">
							<th scope="col" class="width-col-2 px-3">Documents</th>
							<th scope="col" class="width-col-4 text-center">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${docList }" var="document">
							<tr>
								<td class="pdf-name-flex px-3"><img
									src="<c:url value='/resources/images/pdf.png' />"
									class="pdf-img" alt=""><span>${document.filename }</span></td>
								<td class="text-center">
									<div class="download-delete-btns-flex">
										<a href="${document.url}" download="${document.filename }"
											class="cloud-download-btn" id="btn-b-1"> <img
											src="<c:url value='/resources/images/cloud-arrow-down.svg' />"
											class="cloud-btn-down" alt="">
										</a>
										<button class="cloud-download-btn"
											onclick="deleteFile(${document.requestWiseFileId})"
											id="btn-b-2">
											<img src="<c:url value='/resources/images/Dustbin.svg' />"
												class="cloud-btn-down" alt="">
										</button>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="col-12 text-area-input-add-padding">
				<div class="mb-2">
					<strong>Provider Note</strong>
				</div>
				<div class="form-floating">
					<textarea class="form-control" placeholder="Leave a comment here"
						id="floatingTextarea2" style="height: 110px;">${physicianNotes }</textarea>
					<label for="floatingTextarea2">Provider Notes</label>
				</div>
			</div>

			<c:choose>
				<c:when test="${isFinalized == true}">
					<div class="conclude-care-btn-flex">
						<a type="button" href="../close-case-by-physician/${reqId }"
							class="conclude-care-btn">Conclude Care</a>
					</div>

				</c:when>
				<c:otherwise>
					<div class="conclude-care-btn-flex">
						<button type="button" data-bs-toggle="modal"
							data-bs-target="#conclude-care" class="conclude-care-btn">Conclude
							Care</button>
					</div>

				</c:otherwise>
			</c:choose>


		</div>

	</div>

	<div class="modal fade" id="conclude-care" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content agreement-scale">
				<form id="acceptCaseForm">
					<div class="modal-body modal-body-accept-case">
						<div>

							<ul style="list-style-type: none;"
								class="text-center ul-padding-remove">
								<li class="mb-1"><h5>Conclude care failed!</h5></li>
								<li>The encounter form is yet not finalized. Please
									finalize the encounter form so that you can conlude the patient
									care.</li>
							</ul>
						</div>

						<div
							class="modal-footer accpet-case-btn-flex grow-accpet-case-btn">
							<button type="reset" class="cancel-btn" data-bs-dismiss="modal">Close</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!--conclude-case -->

	<footer>
		<div class="footer-flex">
			<a href="#">Terms of Condition</a><span>|</span><a href="#">Privacy
				Policy</a>
		</div>
	</footer>

	<script>
		$('.selectall').click(function() {
			if ($(this).is(':checked')) {
				$('input:checkbox').prop('checked', true);
			} else {
				$('input:checkbox').prop('checked', false);
			}
		});

		$("input[type='checkbox'].justone").change(function() {
			var a = $("input[type='checkbox'].justone");
			if (a.length == a.filter(":checked").length) {
				$('.selectall').prop('checked', true);
			} else {
				$('.selectall').prop('checked', false);
			}
		});
	</script>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/loader.js' />"></script>
	<script src="<c:url value='/resources/js/conclude-care.js' />"></script>
</body>
</html>