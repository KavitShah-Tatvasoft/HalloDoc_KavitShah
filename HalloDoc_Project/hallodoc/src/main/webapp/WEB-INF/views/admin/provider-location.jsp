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
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/provider-location.css' />">
<!--     <script src="./js/admin-dashboard.js"></script> -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<title>Provider Location</title>
</head>
<body>
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="submit-info-txt">
			<span class="view-reservation-text">Provider Location</span>
		</div>

		<div class="modal-content">
			<section>
				<div class="mapouter">
					<div class="gmap_canvas">
						<iframe width="100%" height="500" id="gmap_canvas" src=""
							frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
					</div>
				</div>
			</section>
		</div>


	</div>

	<footer>
		<div class="footer-flex">
			<a href="#">Terms of Condition</a><span>|</span><a href="#">Privacy
				Policy</a>
		</div>
	</footer>

	<style>
.mapouter {
	position: relative;
	text-align: right;
	height: 100%;
	width: 100%;
}

.gmap_canvas {
	padding: 25px 15px;
	overflow: hidden;
	background: none !important;
	height: 100%;
	width: 100%;
}

@media screen and (max-width:500px) {
	.gmap_canvas {
		padding: 15px 5px;
	}
}
</style>


	<script>
		$(document).ready(
				function() {
					// $("#Map").click(function () {

					var Street = "Killol Nursery";
					var City = "Godhra";
					var State = "Gujarat"
					var ZipCode = "389001";

					// Define the address to geocode
					var address = "https://maps.google.com/maps?q=" + Street
							+ City + State + ZipCode
							+ "&t=&z=13&ie=UTF8&iwloc=&output=embed";
					$("#gmap_canvas").attr("src", address);

				});
		// });
	</script>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
</body>
</html>

<script type="text/javascript">
		
		$(".common-link-class").removeClass("active")
		$(".provider-location-class").addClass("active")
	
	</script>