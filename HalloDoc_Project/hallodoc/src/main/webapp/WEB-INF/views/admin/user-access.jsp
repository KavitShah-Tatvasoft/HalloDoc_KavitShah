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
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/user-access.css' />">
<!-- <script src="./js/admin-dashboard.js"></script> -->
<title>User Access</title>
</head>
<body onload="loadUserAccessData()">
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="submit-info-txt">User Access</div>

		<div class="row">
			<div class="select-parent">
				<select name="physician" onchange="loadUserAccessData()" id="" class="form-select select-physician select-type-id">
					<option value="0" hidden>Select Account Type</option>
					<option value="0">All</option>
					<option value="1">Admin</option>
					<option value="2">Provider</option>
				</select> <img src="<c:url value='/resources/images/search.svg' />" class="select-search-img"
					alt="">
			</div>
		</div>

		<div class="table-responsive">
			<table class="table align-middle mt-2">
				<thead class="table-active  align-middle">
					<tr>
						<th scope="col" class="thead width-col1 extra-padding-td"><div
								class="table-row1-flex">
								<span>Account Type</span><img
									src="<c:url value='/resources/images/arrow-up.svg' />" class="arrow-up-icon">
							</div></th>
						<th scope="col" class="thead width-col2">Account POC</th>
						<th scope="col" class="thead width-col3">Phone</th>
						<th scope="col" class="thead width-col4">Status</th>
						<th scope="col" class="thead width-col5">Open Requests</th>
						<th scope="col" class="thead width-col6">Actions</th>
					</tr>
				</thead>
				
				<tr class="user-access-td-clone d-none">
						<td class="extra-padding-td type-td">Admin</td>
						<td class="name-td">Shah Agency</td>
						<td class="number-td">+91 3615262791</td>
						<td class="status-td">Active</td>
						<td class="request-td">7890</td>
						<td><a href="#" class="edit-td">
								<div class="edit-btn">
									<span>Edit</span>
								</div>
						</a></td>
					</tr>
				
				<tbody class="tbody-empty-td">
					
				</tbody>
			</table>
		</div>

		<div class="accordion mt-4" id="accordionPanelsStayOpenExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="panelsStayOpen-headingOne">
					<button class="accordion-button" type="button"
						data-bs-toggle="collapse"
						data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
						aria-controls="panelsStayOpen-collapseOne">
						<div class="accordion-button-flex">
							<span class="accordion-row-name">Testing Business</span> <small
								class="acount-type-btn-text">Account Type: Admin</small>
						</div>

					</button>
				</h2>
				<div id="panelsStayOpen-collapseOne"
					class="accordion-collapse collapse "
					aria-labelledby="panelsStayOpen-headingOne">
					<div class="accordion-body">
						<div class="accordion-rows-main-flex">

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img src="<c:url value='/resources/images/telephone-blue.svg' />" alt="">
								</div>
								<div>
									<span class="email-label">Phone: </span> <span
										class="email-text">1234567890</span>
								</div>
							</div>

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img src="<c:url value='/resources/images/tick.svg' />" class="tick-size"
										alt="">
								</div>
								<div>
									<span class="email-label">Status: </span> <span
										class="email-text">Active</span>
								</div>
							</div>

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img src="<c:url value='/resources/images/envelope-blue.svg' />" alt="">
								</div>
								<div>
									<span class="email-label">Open Request: </span> <span
										class="email-text">3066</span>
								</div>
							</div>

							<div class="accordion-btn-inner-flex">
								<a href="#" role="button"
									class="bottom-accordion-row-btn shrink-btns">Edit</a>
							</div>
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

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/user-access.js' />"></script>
</body>
</html>