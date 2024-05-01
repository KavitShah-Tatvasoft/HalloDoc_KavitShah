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
<link rel="stylesheet" href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/patient-history.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<title>Patient History</title>
</head>
<body onload="filterPatientHistory()">
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="submit-info-txt">Patient History</div>

		<div class="row extra-margin-row">

			<div class="col-12 col-md-3">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 record-first-name"
						id="floatingInput-3" placeholder="First Name" autocomplete="off">
					<label for="floatingInput-3">First Name</label>
				</div>
			</div>

			<div class="col-12 col-md-3">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 record-last-name"
						id="floatingInput-3" placeholder="Last Name" autocomplete="off">
					<label for="floatingInput-3">Last Name</label>
				</div>
			</div>

			<div class="col-12 col-md-3">
				<div class="form-floating mb-3 inp">
					<input type="email" class="form-control input-1 record-email"
						id="floatingInput-3" placeholder="Email" autocomplete="off">
					<label for="floatingInput-3">Your Email</label>
				</div>
			</div>

			<div class="col-12 col-md-3">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 record-phone"
						id="floatingInput-3" placeholder="Phone Numbers"
						autocomplete="off"> <label for="floatingInput-3">Phone
						Number</label>
				</div>
			</div>

			<div class="row-1-clear-search-btn-flex">
				<button class="clear-btn shrink-btns" onclick="clearFilter()">Clear</button>
				<button class="search-btn shrink-btns" onclick="filterPatientHistory()">Search</button>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table align-middle mt-2">
				<thead class="table-active  align-middle">
					<tr>
						<th scope="col" class="thead width-col1 extra-padding-td">First
							Name</th>
						<th scope="col" class="thead width-col2">Last Name</th>
						<th scope="col" class="thead width-col3">Email</th>
						<th scope="col" class="thead width-col4">Phone</th>
						<th scope="col" class="thead width-col5">Address</th>
						<th scope="col" class="thead width-col6 text-center">Actions</th>
					</tr>
				</thead>
				
				<tr class="patient-history-tr d-none">
						<td class="extra-padding-td pt-fname-td">Derek</td>
						<td class="pt-l-name-td">Shah</td>
						<td class="pt-email-td">derekshah@gmail.com</td>
						<td class="pt-phone-td">1234567890</td>
						<td class="pt-address-td">-</td>
						<td class="text-center-flex"><a class="pt-explore-td"
							href="patient-explore-record.html">
								<div class="edit-btn">
									<span>Explore</span>
								</div>
						</a></td>
					</tr>

				<tbody class="patient-history-empty-tbody">
					
				</tbody>
			</table>
		</div>
		
		<div class="accordion-item patient-history-accordion-clone d-none">
				<h2 class="accordion-header" id="panelsStayOpen-headingOne">
					<button class="accordion-button acc-change-target-class" type="button"
						data-bs-toggle="collapse"
						data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
						aria-controls="panelsStayOpen-collapseOne">
						<div class="accordion-button-flex">
							<span class="accordion-row-name acc-name">Derek Shah</span> <small
								class="acount-type-btn-text acc-number">12345677890</small>
						</div>

					</button>
				</h2>
				<div id="panelsStayOpen-collapseOne"
					class="accordion-collapse collapse acc-change-id"
					aria-labelledby="panelsStayOpen-headingOne">
					<div class="accordion-body">
						<div class="accordion-rows-main-flex">

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img src="<c:url value='/resources/images/envelope-blue.svg' />" alt="">
								</div>
								<div>
									<span class="email-label ">Email: </span> <span
										class="email-text acc-email">kavitshah324@gmail.com</span>
								</div>
							</div>

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img src="<c:url value='/resources/images/telephone-blue.svg' />" alt="">
								</div>
								<div>
									<span class="email-label">Phone: </span> <span
										class="email-text acc-phone">1234567890</span>
								</div>
							</div>

							<div class="accordion-btn-inner-flex">
								<a href="patient-explore-record.html" role="button"
									class="bottom-accordion-row-btn accordion-explore-case">Explore</a>
							</div>
						</div>
					</div>
				</div>
			</div>

		

		<div class="accordion mt-4 accordion-body-empty" id="accordionPanelsStayOpenExample" >
			
		</div>

	</div>

	<script src="<c:url value='/resources/js/patient-history.js' />"></script>
	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script type="text/javascript">
		
		$(".common-link-class").removeClass("active")
		$(".records-link-class").addClass("active")
	
	</script>
</body>
</html>
<%-- <%@include file="footer-black.jsp"%> --%>