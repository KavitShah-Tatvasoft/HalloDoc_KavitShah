<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<link rel="stylesheet"
	href="<c:url value="/resources/css/navbar.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/view-documents-patient.css" />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<title>View Documents</title>
</head>
<body>
	<%@ include file="patient-navbar.jsp"%>
	<%@ page import="java.io.File"%>
	<%
	String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources" + File.separator
			+ "fileuploads" + File.separator + "patient";
	pageContext.setAttribute("path", path);
	%>
	<div
		class="container-fluid footer-container main-container relative-pos-class ">
		<div class="main-container-top-text">Documents</div>


		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value="/resources/images/chevron-left.svg" />"
				alt=""> Back
		</div>


		<div class="row extra-padding-main-container">
			<div class="col-12 small-text text">Patient Name</div>

			<div class="col-12">
				<span class="blue-text text">${userOb.firstName }
					${userOb.lastName }</span> <span class="text case-id-text">(${docList[0].confirmationNumber })</span>
			</div>

			<div class="col-12 text upper-margin">Check here for any files
				that you or the doctor of your subsequents requestors have attached
				for you to review.</div>

			<div class="col-12 mt-4">
				<form
					action="${pageContext.request.contextPath}/uploadRequestDocument"
					method="post" enctype="multipart/form-data">
					<div class="input-group mb-3" style="position: relative">
						<input type="file" name="documentFile" class="form-control"
							id="inputGroupFile02"> <input type="text"
							value="${reqId }" name="requestId" hidden /> <input type="text"
							value="${userOb.firstName } ${userOb.lastName }"
							name="uploaderName" hidden />

						<button type="submit" id="submit-button-upload"
							style="display: none;"></button>
						<button type="reset" id="reset-button-upload"
							style="display: none;"></button>
						<label class="input-group-text file-upload-btn"
							for="submit-button-upload"><img
							src="<c:url value="/resources/images/cloud-arrow-up-white.svg" />"
							class="upload-image-file" alt=""><span class="upload-txt">Upload</span></label>
						<label for="reset-button-upload"
							style="position: absolute; top: 12px; right: 110px"><img
							src="<c:url value="/resources/images/trash.svg" />" /></label>
					</div>
				</form>
			</div>

			<div class="col-12 doc-download-flex">
				<div class="doc-text">Documents</div>
				<button class="download-all-btn" onclick="downloadAll()"> 
					<span class="download-all-text">Download All</span> <img
						src="<c:url value="/resources/images/download-all.svg" />"
						class="download-all-image" alt="">
				</button>
			</div>
		</div>

		<div class="row mobile-cards">
			<c:forEach items="${docList}" var="listOb">
				<hr class="mt-3">
				<div class="col-12 single-mobile-card">
					<div class="mobile-checkbox-name-flex">
						<input type="checkbox" name="row1"> <span
							class="pdf-name-flex"><img
							src="<c:url value="/resources/images/pdf.png" />" class="pdf-img"
							alt=""><span>${listOb.filename}</span></span>
					</div>
					<div class="name-col common-margin-top">${listOb.uploaderName }</div>
					<fmt:formatDate value="${listOb.createdDate}"
						pattern="MMMM dd, yyyy" var="formattedDate" />
					<div class="date-col common-margin-top">${formattedDate }</div>
					<button class="cloud-download-btn common-margin-top">
						<img
							src="<c:url value="/resources/images/cloud-arrow-down.svg" />"
							class="cloud-btn-down" alt="">
					</button>
				</div>
			</c:forEach>
		</div>


		<!--  </div> <div class="container-fluid main-container"> -->
		<div class="row">
			<!-- <div class="col-12"> -->
			<table class="table align-middle">
				<thead>
					<tr class="table-active">
						<th class="px-3 width-col-1" scope="col"><input
							type="checkbox" name="row-all" class="selectall"></th>
						<th scope="col" class="width-col-2"></th>
						<th scope="col" class="width-col-3">Uploader</th>
						<th scope="col" class="width-col-4"><span>Uploader
								Date</span><img src="<c:url value="/resources/images/arrow-up.svg" />"
							class="up-arrow-img" alt=""></th>
						<th scope="col" class="width-col-5 text-align-center">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${docList}" var="listOb">
						<tr>
							<th class="px-3" scope="row"><input type="checkbox"
								class="justone" name="row1"></th>
							<td class="pdf-name-flex"><img
								src="<c:url value="/resources/images/pdf.png" />"
								class="pdf-img" alt=""><span>${listOb.filename }</span></td>
							<td>${listOb.uploaderName }</td>
							<td>${formattedDate }</td>
							<td><div class="cloud-download-btn"><a class="link" href="${listOb.url}" download="${listOb.filename}"
								class="cloud-download-btn"> <img
									src="<c:url value="/resources/images/cloud-arrow-down.svg" />"
									class="cloud-btn-down" alt="">
							</a></div></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<!-- </div> -->
		</div>
	</div>


	<%@ include file="footer-black.jsp"%>

	<style>
body {
	position: relative;
	min-height: 100vh;
}

.footer-flex {
	color: white;
	display: flex;
	justify-content: flex-end;
	align-items: center;
	gap: 10px;
	height: 50px;
	padding-right: 10px;
}

.footer-flex a {
	text-decoration: none !important;
	color: white;
}

body::after {
	content: '';
	display: block;
	height: 50px; /* Set same as footer's height */
}

footer {
	/* margin-top: 20px; */
	position: absolute;
	bottom: 0px;
	background-color: rgb(41, 39, 39);
	height: 50px;
	width: 100vw;
}

.footer-container {
	margin-bottom: 100px;
}
</style>

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

	<script src="<c:url value="/resources/js/darktheme.js" />"></script>
	<script src="<c:url value="/resources/js/patient-view-document.js" />"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>