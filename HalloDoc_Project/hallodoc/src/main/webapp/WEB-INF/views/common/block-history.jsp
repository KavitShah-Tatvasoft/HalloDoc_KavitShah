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
<link rel="stylesheet" href="<c:url value='/resources/css/block-history.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<title>Block History</title>
<body onload="getFilteredBlockData()">
	<div
		class="container-fluid patient-form shadow bg-white rounded footer-containers relative-position extra-margin">

		<div class="submit-info-txt">Block History</div>

		<div class="row p-3 pt-4">

			<div class="col-12 col-sm-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 filter-name" 
						id="floatingInput-3" placeholder="Name" autocomplete="off">
					<label for="floatingInput-3">Name</label>
				</div>
			</div>

			<div class="col-12 col-sm-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp custom-date-input">
					<input type="date" class="form-control input-1 filter-date"
						id="floatingInput-4" placeholder="Date" autocomplete="off">
					<label for="floatingInput-4">Date</label> <img
						src="<c:url value='/resources/images/calendar4-week.svg' />" alt=""
						class="custom-date-icon">
				</div>
			</div>

			<div class="col-12 col-sm-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp">
					<input type="email" class="form-control input-1 filter-email"
						id="floatingInput-3" placeholder="Email" autocomplete="off">
					<label for="floatingInput-3">Email</label>
				</div>
			</div>

			<div class="col-12 col-sm-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 filter-phone"
						id="floatingInput-3" placeholder="Phone Number" autocomplete="off">
					<label for="floatingInput-3">Phone Number</label>
				</div>
			</div>

			<div class="btn-col">
				<div class="row-1-clear-search-btn-flex">
					<button class="clear-btn shrink-btns" onclick="clearFilters()">Clear</button>
					<button class="search-btn shrink-btns" onclick="getFilteredBlockData()">Search</button>
				</div>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table align-middle mt-2">
				<thead class="table-active  align-middle">
					<tr>
						<th scope="col" class="thead width-col1 extra-padding-td">Patient
							Name</th>
						<th scope="col" class="thead width-col2">Phone Number</th>
						<th scope="col" class="thead width-col3">Email</th>
						<th scope="col" class="thead width-col4">
							<div class="close-arrow-flex">
								<span>Create Date</span> <img
									src="./SRS Screen Shorts/arrow-up.svg" alt="">
							</div>
						</th>
						<th scope="col" class="thead width-col5 ">Notes</th>
						<th scope="col" class="thead width-col6">is Active</th>
						<th scope="col" class="thead width-col7">Action</th>
					</tr>
				</thead>
				
				<tr class="clone-block-tr d-none">
						<td class="extra-padding-td bname-td">Shah, Nilomi</td>
						<td class="bnumber-td">1234567890</td>
						<td class="bemail-td">nilomi@gmail.com</td>
						<td class="bdate-td">Aug 23,2024</td>
						<td class="bnotes-td">test</td>
						<td><input type="checkbox" name="" id=""
							class="check-box-resize bactive-td" disabled="disabled"></td>
						<td><button type="button" class="unblock-btn bunblock-td">Unblock</a></td>
					</tr>

				<tbody class="empty-tbody-block">
					

				</tbody>
			</table>
		</div>
		
		<div class="accordion-item clone-accordion-class d-none">
				<h2 class="accordion-header" id="panelsStayOpen-headingOne">
					<button class="accordion-button change-target-class" type="button"
						data-bs-toggle="collapse"
						data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
						aria-controls="panelsStayOpen-collapseOne">
						<div class="accordion-button-flex">

							<span class="email-label acc-name">Patel Sagar</span> <small
								class="acount-type-btn-text mb-3"> <span
								class="email-label acc-email">xyz.abc@gmail.com</span>
							</small>

						</div>

					</button>
				</h2>
				<div id="panelsStayOpen-collapseOne"
					class="accordion-collapse collapse change-id-class"
					aria-labelledby="panelsStayOpen-headingOne">
					<div class="accordion-body">
						<div class="accordion-rows-main-flex">

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img src="<c:url value='/resources/images/telephone-blue.svg' />" alt="">
								</div>
								<div>
									<span class="email-label ">Phone: </span> <span
										class="email-text acc-phone">(91) 1234567890</span>
								</div>
							</div>

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img src="<c:url value='/resources/images/calendar4-week.svg' />"
										class="calendar-resize-search" alt="">
								</div>
								<div>
									<span class="email-label">Created Date: </span> <span
										class="email-text acc-create-date">Dec 5, 2022</span>
								</div>
							</div>

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img src="<c:url value='/resources/images/journal-text.svg' />"
										class="calendar-resize-search" alt="">
								</div>
								<div>
									<span class="email-label">Notes: </span> <span
										class="email-text acc-notes">test dummy</span>
								</div>
							</div>

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img src="<c:url value='/resources/images/tick.svg' />"
										class="tick-icon-resize-search" alt="">
								</div>
								<div>
									<span class="email-label">is Active: </span> <span
										class="email-text acc-active">Yes </span>
								</div>
							</div>

							<div class="bottom-btn-flex-block-history">
								<a role="button"
									class="unblock-btn extra-changes-unblock shrink-btns acc-unblock">Unblock</a>
							</div>

						</div>
					</div>
				</div>
			</div>

		<div class="accordion empty-accordion-class" id="accordionPanelsStayOpenExample">
			
		</div>


	</div>


	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/block-case.js' />"></script>
</body>
</html>
<%@include file="footer-black.jsp"%>