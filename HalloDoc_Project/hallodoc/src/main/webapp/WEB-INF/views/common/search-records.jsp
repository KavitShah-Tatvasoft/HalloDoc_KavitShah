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
<link rel="stylesheet"
	href="<c:url value='/resources/css/loader.css' />">

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
	href="<c:url value='/resources/css/search-record.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<title>Search Records</title>
</head>
<body onload="getSearchRecordsData(true)">
	<div class="loader-container">
		<div class="loader"></div>
	</div>
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="submit-info-txt">Search Records</div>

		<button onclick="downloadFilteredData()" role="button"
			class="export-all-data-btn">
			<img src="<c:url value='/resources/images/export 1.svg' />" alt="">
			<span class="export-text">Export Data To Excel</span>
		</button>

		<div class="row p-3 pt-4">
			<div class="col-12 col-md-6 col-lg-3">
				<!--Mobile Type col-->
				<div class=" mb-3 inp">
					<select name="Number_Type" id="floatingInput-5"
						class="form-control form-select input-2 search-record-select-top filter-request-status">
						<option value="0" hidden>Select Request Status</option>
						<option value="0">All</option>
						<option value="1">New</option>
						<option value="2">Pending</option>
						<option value="3">Active</option>
						<option value="4">Conclude</option>
						<option value="5">Close</option>
						<option value="6">Unpaid</option>
					</select>
				</div>
			</div>

			<div class="col-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 filter-name"
						id="floatingInput-3" placeholder="First Name" autocomplete="off">
					<label for="floatingInput-3">First Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6 col-lg-3">
				<!--Mobile Type col-->
				<div class=" mb-3 inp">
					<select name="Number_Type" id="floatingInput-5"
						class="form-control form-select input-2 search-record-select-top filter-request-type">
						<option value="0" hidden>Select Request Type</option>
						<option value="0">All</option>
						<option value="1">Business</option>
						<option value="2">Patient</option>
						<option value="3">Family/Friend</option>
						<option value="4">Concierge</option>
					</select>
				</div>
			</div>

			<div class="col-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp custom-date-input">
					<!--Date Picker col-->
					<input type="date" class="form-control input-1 filter-dos"
						id="floatingInput-4" placeholder="From Date of Service"
						autocomplete="off"> <label for="floatingInput-4">From
						Date of Service</label> <img
						src="<c:url value='/resources/images/calendar4-week.svg' />"
						alt="" class="custom-date-icon">
				</div>
			</div>

			<div class="col-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp custom-date-input">
					<!--Date Picker col-->
					<input type="date" class="form-control input-1 filter-to-dos"
						id="floatingInput-4" placeholder="To Date of Service"
						autocomplete="off"> <label for="floatingInput-4">To
						Date of Service</label> <img
						src="<c:url value='/resources/images/calendar4-week.svg' />"
						alt="" class="custom-date-icon">
				</div>
			</div>

			<div class="col-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp">
					<input type="text"
						class="form-control input-1 filter-provider-name"
						id="floatingInput-3" placeholder="Provider Name"
						autocomplete="off"> <label for="floatingInput-3">Provider
						Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp">
					<input type="email" class="form-control input-1 filter-email"
						id="floatingInput-3" placeholder="Email" autocomplete="off">
					<label for="floatingInput-3">Email</label>
				</div>
			</div>

			<div class="col-12 col-md-6 col-lg-3">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 filter-phone"
						id="floatingInput-3" placeholder="Phone Number" autocomplete="off">
					<label for="floatingInput-3">Phone Number</label>
				</div>
			</div>

			<div class="row-1-clear-search-btn-flex">
				<!-- 				<button class="clear-btn select-all shrink-btns">Select All</button> -->
				<button class="clear-btn shrink-btns" onclick="clearFilters()">Clear</button>
				<button class="search-btn shrink-btns"
					onclick="getSearchRecordsData(true)">Search</button>
			</div>

		</div>

		<div class="table-responsive">
			<table class="table align-middle mt-2">
				<thead class="table-active  align-middle">
					<tr>
						<th scope="col"
							class="thead width-col1 extra-padding-td text-center">Patient
							Name</th>
						<th scope="col" class="thead width-col2 text-center">Requestor</th>
						<th scope="col" class="thead width-col3 text-center">Date of
							Service</th>
						<th scope="col" class="thead width-col4 text-center">
							<div class="close-arrow-flex">
								<span>Close Case Date</span> <img
									src="<c:url value='/resources/images/arrow-up.svg' />" alt="">
							</div>
						</th>
						<th scope="col" class="thead width-col5 text-center">Email</th>
						<th scope="col" class="thead width-col6 text-center">Phone
							Number</th>
						<th scope="col" class="thead width-col7 text-center">Address</th>
						<th scope="col" class="thead width-col8 text-center">Zip</th>
						<th scope="col" class="thead width-col9 text-center">Request
							Status</th>
						<th scope="col" class="thead width-col10 text-center">Physician</th>
						<th scope="col" class="thead width-col11 text-center">Physician
							Note</th>
						<!-- 						<th scope="col" class="thead width-col12 text-center">Cancelled -->
						<!-- 							By Provider Note</th> -->
						<th scope="col" class="thead width-col13 text-center">Admin
							Notes</th>
						<th scope="col" class="thead width-col14 text-center">Patient</th>
						<th scope="col" class="thead width-col15">Delete Permanently</th>
					</tr>
				</thead>

				<tr class="table-td-class-clone d-none">
					<td class="extra-padding-td name-td">John Potter, Harry Potter</td>
					<td class="requestor-td">Patient</td>
					<td class="text-center dos-td">Aug 23,2024</td>
					<td class="text-center doc-td">Aug 23,2024</td>
					<td class="email-td">derekshah@gmail.com</td>
					<td class="phone-number-td">1234567890</td>
					<td class="address-td">123, Dummy Address, Ahmedabad</td>
					<td class="zip-td">120002</td>
					<td class="status-td">Closed</td>
					<td class="text-center phy-td">-</td>
					<td class="text-center phy-nt-td">-</td>
					<td class="text-center admin-nt-td">-</td>
					<!-- 						<td class="text-center">-</td> -->
					<td class="text-center patient-nt-td">-</td>
					<td class="text-center"><div>
							<div type="button" class="edit-btn delete-td">
								<span>Delete </span>
							</div>
						</div></td>
				</tr>


				<tbody class="table-empty-class">



				</tbody>
			</table>
		</div>

		<div class="accordion-item accordion-clone-class d-none">
			<h2 class="accordion-header" id="panelsStayOpen-headingOne">
				<button class="accordion-button change-target-class" type="button"
					data-bs-toggle="collapse"
					data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
					aria-controls="panelsStayOpen-collapseOne">
					<div class="accordion-button-flex">
						<div class="name-checkbox-flex">
							<span class="accordion-row-name acc-name-td">Derek Shah</span>
						</div>

						<small class="acount-type-btn-text acc-address-td">Random
							Text</small>
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
								<img src="<c:url value='/resources/images/person.svg' />" alt="">
							</div>
							<div>
								<span class="email-label">Requestor: </span> <span
									class="email-text acc-request-type-td">Family/Friend</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img
									src="<c:url value='/resources/images/calendar4-week.svg' />"
									class="calendar-resize-search" alt="">
							</div>
							<div>
								<span class="email-label">Date Of Service: </span> <span
									class="email-text acc-dos-td">-</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img
									src="<c:url value='/resources/images/calendar4-week.svg' />"
									class="calendar-resize-search" alt="">
							</div>
							<div>
								<span class="email-label">Case Closed Date: </span> <span
									class="email-text acc-doc-td">Dec 5, 2022</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img src="<c:url value='/resources/images/envelope-blue.svg' />"
									alt="">
							</div>
							<div>
								<span class="email-label">Email: </span> <span
									class="email-text acc-email-td">kavitshah324@gmail.com</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img src="<c:url value='/resources/images/envelope-blue.svg' />"
									alt="">
							</div>
							<div>
								<span class="email-label">Phone: </span> <span
									class="email-text acc-phone-td">1234567890</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img src="<c:url value='/resources/images/geo-alt-fill.svg' />"
									alt="">
							</div>
							<div>
								<span class="email-label">Address: </span> <span
									class="email-text acc-address-td1">Eopic Hospital</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img src="<c:url value='/resources/images/geo-alt-fill.svg' />"
									alt="">
							</div>
							<div>
								<span class="email-label">Zip Code: </span> <span
									class="email-text acc-zip-td">20001</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img src="<c:url value='/resources/images/tick.svg' />"
									class="tick-resize-search" alt="">
							</div>
							<div>
								<span class="email-label">Request State: </span> <span
									class="email-text acc-status-td">Unpaid </span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img src="<c:url value='/resources/images/person-add.svg' />"
									alt="">
							</div>
							<div>
								<span class="email-label">Provider: </span> <span
									class="email-text acc-provider-td">-</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img src="<c:url value='/resources/images/journal-text.svg' />"
									class="note-icon-resize-search" alt="">
							</div>
							<div>
								<span class="email-label">Provider Note: </span> <span
									class="email-text acc-provider-notes-td">-</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img src="<c:url value='/resources/images/journal-text.svg' />"
									class="note-icon-resize-search" alt="">
							</div>
							<div>
								<span class="email-label">Admin Note: </span> <span
									class="email-text acc-admin-notes-td">-</span>
							</div>
						</div>

						<div class="accordion-row-inner-flex">
							<div class="image-blue-border">
								<img src="<c:url value='/resources/images/journal-text.svg' />"
									class="note-icon-resize-search" alt="">
							</div>
							<div>
								<span class="email-label">Patient Note: </span> <span
									class="email-text acc-pt-notes">-</span>
							</div>
						</div>

						<div class="accordion-btn-inner-flex">
							<button role="button" class="bottom-accordion-row-btn delete-td">Delete
								Permanently</button>
						</div>
					</div>
				</div>
			</div>
		</div>



		<div class="accordion accordion-body-empty"
			id="accordionPanelsStayOpenExample"></div>



	</div>

	<nav aria-label="..." class="pagination-center-class">

		<li class="page-item prev-navigation d-none"><a
			onclick="prevPage()" class="page-link" href="#" tabindex="-1">Previous</a></li>

		<li class="page-item pageno-pagination add-onclick d-none"><a
			class="page-link add-active common-active" href="#"></a></li>
		<li class="page-item next-pagination d-none"><a
			onclick="nextPage()" class="page-link" href="#">Next</a></li>

		<ul class="pagination empty-pagination">

		</ul>
	</nav>
	<script src="<c:url value='/resources/js/loader.js' />"></script>
	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/search-records.js' />"></script>
	<script type="text/javascript">
		$(".common-link-class").removeClass("active")
		$(".records-link-class").addClass("active")
	</script>

</body>
</html>
<%@include file="footer-black.jsp"%>