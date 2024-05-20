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
	href="<c:url value='/resources/css/mds-on-call.css' />">
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
<title>Scheduling Day</title>
</head>
<body>
	<div
		class="container-fluid patient-form p-3 rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="submit-info-txt">MDs on Call</div>

		<div class="all-region-btns-flex">

			<select class="form-select select-scheduling "
				aria-label="Default select example">
				<option value="0">All Regions</option>
				<c:forEach items="${regionList }" var="region">
					<option value="${region.regionId }">${region.name }</option>
				</c:forEach>
			</select>

			<div class="btns-flex">
				<a role="button"
					href="${pageContext.request.contextPath}/admin/scheduling-day"
					class="three-btns">Calendar View</a> <a role="button"
					href="${pageContext.request.contextPath}/admin/review-shift"
					class="three-btns">Shift for Review</a>
			</div>
		</div>

		<div class="shadow mt-4 p-3 white-background">
			<div class="mds-on-call-col mt-2">
				<div class="img-top-md">MD's On Call</div>

				<div class="row">

					<div class="col-6 col-sm-6 col-md-4 col-lg-4 mt-4 col-xs">
						<div class="img-name-flex">
							<img src="<c:url value='/resources/images/doctor.png' />"
								class="doc-img-size" alt=""> Dr Derek
						</div>
					</div>

				</div>
			</div>


			<div class="mds-on-call-col mt-4">
				<div class="img-top-md">Physicians Off Duty</div>

				<div class="row">
					<div class="col-6 col-sm-6 col-md-4 col-lg-4 mt-4 col-xs">
						<div class="img-name-flex">
							<img src="<c:url value='/resources/images/doctor.png' />"
								class="doc-img-size" alt=""> Dr Derek
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>

	<footer>
		<div class="footer-flex">
			<a href="#">Terms of Condition</a><span>|</span><a href="#">Privacy
				Policy</a>
		</div>
	</footer>

	<script src="<c:url value='/resources/js/provider-on-call.js' />"></script>
	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
</body>
</html>