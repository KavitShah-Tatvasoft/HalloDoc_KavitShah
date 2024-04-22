<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<c:url value='/resources/css/review-agreement.css' />">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/pop-ups.css' />">
<title>Review Agreement</title>
</head>
<body>
	<div class="container-fluid  margin-top align-middle main-container">

		<div class="text-center-span">To provide best medical service,
			we cannot determine the cost right away. If you agree to our service,
			so we will provide care and follow-up until all care is completed. So
			with this points, if you like us to provide care to you click on
			"Agree" and we'll get stated immediately, if you do not agree simply
			click "Cancel"</div>

		<div class="bottom-btns-flex">
			<a role="button" class="accept-btn"
				href="../acceptedReviewAgreement/${reqId}">Accept</a> <a
				role="button" class="cancel-btn-pop" data-bs-toggle="modal"
				data-bs-target="#cancel-agreemnt">Cancel</a>
		</div>
	</div>




	<!-- pop ups -->

	<div class="modal fade" id="cancel-agreemnt" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content cancel-pop-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Cancellation
						Confirm</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="../cancelAgreementDetails/${reqId}" method="post">
					<div class="modal-body">
						<div>
							<span class="font-clr-light">${name } :</span>
						</div>

						<textarea name="cancel-details"
							class="form-control cancel-pop-margin-top" cols="12" rows="5"
							placeholder="Please provide a brief reason for your cancelation."
							required="required"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="send-btn">Confirm</button>
						<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>