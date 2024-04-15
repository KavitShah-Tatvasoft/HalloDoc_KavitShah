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
<link rel="stylesheet" href="<c:url value='/resources/css/view-notes.css' />">
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

<title>View Notes</title>
</head>
<body>
	<div
		class="container-fluid footer-container patient-form p-3 rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="top-patient-view-text">
			<span class="view-reservation-text">Notes</span>
		</div>

		<div class="row">

			<div class="col-12 col-md-6 mt-3">
				<div class="w-90 background-colour-white">
					<div class="note-heading-icon-flex">
						<img class="transfer-icon"
							src="<c:url value='/resources/images/transfer.svg' />" alt="">
						<span class="note-heading">Transfer Notes</span>
					</div>
					<span class="note-text"> Admin transfered this note........
					</span>
				</div>
			</div>

			<div class="col-12 col-md-6 ">
				<div class="w-90 background-colour-white mt-3">
					<div class="note-heading-icon-flex">
						<img class="transfer-icon"
							src="<c:url value='/resources/images/person-fill-add.svg' />"
							alt=""> <span class="note-heading">Physician Notes</span>
					</div>
					<span class="note-text"> - </span>
				</div>
			</div>

			<div class="col-12 col-md-6 ">
				<div class="w-90 background-colour-white mt-3">
					<div class="note-heading-icon-flex">
						<img class="transfer-icon"
							src="<c:url value='/resources/images/person-fill.svg' />" alt="">
						<span class="note-heading">Admin Cancellation Notes</span>
					</div>
					<span class="note-text"> - </span>
				</div>
			</div>

			<div class="col-12 col-md-6 ">
				<div class="w-90 background-colour-white mt-3">
					<div class="note-heading-icon-flex">
						<img class="transfer-icon"
							src="<c:url value='/resources/images/ban-black.svg' />" alt="">
						<span class="note-heading">Patient Cancellation Notes</span>
					</div>
					<span class="note-text"> - </span>
				</div>
			</div>

		</div>

		<div class="row">
			<div class="col-12 col-md-6 ">
				<div class="w-90 background-colour-white mt-3">
					<div class="note-heading-icon-flex">
						<img class="transfer-icon"
							src="<c:url value='/resources/images/ban-black.svg' />" alt="">
						<span class="note-heading">Admin Notes</span>
					</div>
					<span class="note-text"> - </span>
				</div>
			</div>


			<div class="col-12 mt-3">
				<div class="form-floating">
					<div class="w-90 background-colour-white">
						<div class="form-floating">
							<textarea class="form-control" placeholder="Leave a comment here"
								id="floatingTextarea2" style="height: 115px">-</textarea>
							<label for="floatingTextarea2">Admin Notes</label>
						</div>

						<div class="submit-btn-flex mt-4">
							<button type="submit" class="bottom-btns-submit">Save
								Changes</button>
						</div>

					</div>
				</div>
			</div>


		</div>

	</div>


	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
</body>
</html>
<%@include file="footer-black.jsp"%>