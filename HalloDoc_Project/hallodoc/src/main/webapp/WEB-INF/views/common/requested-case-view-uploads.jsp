<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/view-uploads-patient-conclude.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<title>View Uploads</title>
</head>
<body>
	<div
		class="container-fluid footer-container main-container relative-pos-class ">
		<div class="main-container-top-text">Documents</div>


		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>


		<div class="row extra-padding-main-container">
			<div class="col-12 small-text text">Patient Name</div>

			<div class="col-12">
				<span class="blue-text text">${ptName }</span> <span
					class="text case-id-text">(${confNumber })</span>
			</div>

			<div class="col-12 text upper-margin">Check here to review and
				add files that you or your Client/Member has attached to the
				Request.</div>

			<div class="col-12 mt-4">
				<form
					action="${pageContext.request.contextPath}/user/uploadRequestDocument"
					method="post" enctype="multipart/form-data">
					<div class="input-group mb-3">
						<input type="file" name="documentFile" class="form-control"
							id="inputGroupFile02"> <input type="text"
							value="${reqId }" name="requestId" hidden /> <input type="text"
							value="${userOb.firstName } ${userOb.lastName }"
							name="uploaderName" hidden />

						<button type="submit" id="submit-button-upload"
							style="display: none;"></button>

						<label class="input-group-text file-upload-btn"
							for="submit-button-upload"><img
							src="<c:url value='/resources/images/cloud-arrow-up-white.svg' />"
							class="upload-image-file" alt=""><span class="upload-txt">Upload</span></label>
					</div>
			</div>

			<div class="col-12 doc-download-flex">
				<div class="doc-text">Documents</div>
				<div class="    ">
					<button class="download-all-btn" type="button" onclick="downloadAll()">
						<span class="download-all-text">Download All</span> <img
							src="<c:url value='/resources/images/download-all.svg' />"
							class="download-all-image" alt="">
					</button>

					<button class="download-all-btn" type="button" onclick="deleteAll()">
						<span class="download-all-text">Delete All</span> <img
							src="<c:url value='/resources/images/Dustbin.svg' />"
							class="download-all-image" alt="">
					</button>

					<button class="download-all-btn" type="button" type="button" onclick="sendMailToPatient(${reqId})">
						<span class="download-all-text">Send Mail</span> <img
							src="<c:url value='/resources/images/envelope-blue.svg' />"
							class="download-all-image" alt="">
					</button>
				</div>

			</div>
		</div>

		<div class="row mobile-cards">
			<c:forEach items="${docList}" var="listOb">
				<c:if test="${not empty listOb.filename}">
					<hr class="mt-3">
					<div class="col-12 single-mobile-card">
						<div class="mobile-checkbox-name-flex">
							<input type="checkbox" class="justone-mobile justone-download" name="row1" data-value=${listOb.requestWiseFileId }>
							<span class="pdf-name-flex"><img
								src="<c:url value='/resources/images/pdf.png' />"
								class="pdf-img" alt=""><span class="stored-filename-class" data-value="${listOb.storedFileName }" >${listOb.filename}</span></span>
						</div>
						<fmt:formatDate value="${listOb.createdDate}"
							pattern="MMMM dd, yyyy" var="formattedDate" />
						<div class="date-col common-margin-top">${formattedDate}</div>
						<div class="accordion-div-flex">
							<button class="cloud-download-btn common-margin-top">
								<a class="link cloud-download-btn" href="${listOb.url}" download="${listOb.filename} " >
									<img src="<c:url value="/resources/images/cloud-arrow-down.svg" />" class="cloud-btn-down" alt="">
								</a>
							</button>
							<button class="cloud-download-btn common-margin-top">
								<img
									src="<c:url value='/resources/images/password-eye-blue.svg' />"
									class="cloud-btn-down" alt="">
							</button>
							<button class="cloud-download-btn common-margin-top">
								<img src="<c:url value='/resources/images/Dustbin.svg' />"
									class="cloud-btn-down" alt="">
							</button>
						</div>

					</div>
				</c:if>
			</c:forEach>
		</div>



		<!-- </div>

    <div class="container-fluid main-container"> -->
		<div class="row">
			<!-- <div class="col-12"> -->
			<table class="table align-middle">
				<thead>
					<tr class="table-active">
						<th class="px-3 width-col-1" scope="col"><input
							type="checkbox" name="row-all" class="selectall"></th>
						<th scope="col" class="width-col-2"></th>
						<th scope="col" class="width-col-3"><span>Uploader
								Date</span><img src="<c:url value='/resources/images/arrow-up.svg' />"
							class="up-arrow-img" alt=""></th>
						<th scope="col" class="width-col-4 text-center">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${docList}" var="listOb">
						<c:if test="${not empty listOb.filename}">
							<tr>
								<th class="px-3" scope="row"><input type="checkbox" data-value="${listOb.requestWiseFileId }"
									class="justone justone-download" name="row1"></th>
								<td class="pdf-name-flex"><img
									src="<c:url value='/resources/images/pdf.png' />"
									class="pdf-img" alt=""><span class="stored-filename-class" data-value="${listOb.storedFileName }">${listOb.filename}</span></td>
								<fmt:formatDate value="${listOb.createdDate}"
									pattern="MMMM dd, yyyy" var="formattedDate" />
								<td>${formattedDate}</td>
								<td class="text-center">
									<div class="download-delete-btns-flex">
										<div class="cloud-download-btn">
											<a class="link" href="${listOb.url}" download="${listOb.filename}" class="cloud-download-btn">
												<img src="<c:url value="/resources/images/cloud-arrow-down.svg" />" class="cloud-btn-down" alt="">
											</a>
										</div>
										<button class="cloud-download-btn" type="button" onclick="showDoc('${listOb.url}')">
											<img
												src="<c:url value='/resources/images/password-eye-blue.svg' />"
												class="cloud-btn-down" alt="">
										</button>
										<div class="cloud-download-btn" onclick="deleteFile(${listOb.requestWiseFileId})">
											<img src="<c:url value='/resources/images/Dustbin.svg' />"
												class="cloud-btn-down" alt="">
										</div>
									</div>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<!-- </div> -->
		</div>
	</div>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/requested-case-view-uploads.js' />"></script>
</body>
</html>
<%@include file="footer-black.jsp"%>