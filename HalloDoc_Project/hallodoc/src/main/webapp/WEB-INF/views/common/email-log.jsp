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
<link rel="stylesheet" href="<c:url value='/resources/css/email-log.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<title>Email Logs</title>
</head>
<body onload="filterEmailLog()">
	<div
		class="container-fluid patient-form footer-container shadow bg-white rounded relative-position extra-margin">

		<div class="submit-info-txt">Email Logs (Gmail)</div>

		<div class="back-btn-top" role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />" alt=""> Back
		</div>

		<div class="row p-3 pt-4">

			<div class="col-12 col-sm-6 col-md-6 col-lg-6 col-xl-2">
				<div class="form-floating mb-3 inp">
					<select name="Refill" id="floatingInput-5" 
						class="form-control form-select input-2 filter-by-role">
						<option value="0">All</option>
						<option value="1">Admin</option>
						<option value="2">Provider</option>
						<option value="3">Patient</option>
						<option value="4">Vendor</option>
					</select> <label for="floatingInput-5">Search By Role</label>
				</div>
			</div>

			<div class="col-12 col-sm-6 col-md-6 col-lg-6 col-xl-2">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-1 filter-by-name"
						id="floatingInput-3" placeholder="Receiver Name"
						autocomplete="off"> <label for="floatingInput-3">Receiver
						Name</label>
				</div>
			</div>

			<div class="col-12 col-sm-4 col-md-4 col-lg-4 col-xl-2">
				<div class="form-floating mb-3 inp">
					<input type="email" class="form-control input-1 filter-by-email"
						id="floatingInput-3" placeholder="Email" autocomplete="off">
					<label for="floatingInput-3">Email ID</label>
				</div>
			</div>

			<div class="col-12 col-sm-4 col-md-4 col-lg-4 col-xl-2">
				<div class="form-floating mb-3 inp custom-date-input">
					<!--Date Picker col-->
					<input type="date" class="form-control input-1 filter-by-create-date"
						id="floatingInput-4" placeholder="From Date of Service"
						autocomplete="off"> <label for="floatingInput-4">Create
						Date</label> <img src="<c:url value='/resources/images/calendar4-week.svg' />" alt=""
						class="custom-date-icon">
				</div>
			</div>

			<div class="col-12 col-sm-4 col-md-4 col-lg-4 col-xl-2">
				<div class="form-floating mb-3 inp custom-date-input">
					<!--Date Picker col-->
					<input type="date" class="form-control input-1 filter-by-sent-date"
						id="floatingInput-4" placeholder="To Date of Service"
						autocomplete="off"> <label for="floatingInput-4">Sent
						Date</label> <img src="<c:url value='/resources/images/calendar4-week.svg' />" alt=""
						class="custom-date-icon">
				</div>
			</div>



			<div class="col-12 col-lg-2 justify-content-end btn-col">
				<div class="row-1-clear-search-btn-flex">
					<button class="search-btn shrink-btns" onclick="filterEmailLog()">Search</button>
					<button class="clear-btn shrink-btns" onclick="clearFilters()">Clear</button>
				</div>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table align-middle mt-2">
				<thead class="table-active  align-middle">
					<tr>
						<th scope="col" class="thead width-col1 extra-padding-td">Recipient</th>
						<th scope="col" class="thead width-col2">Action</th>
						<th scope="col" class="thead width-col3">Role Name</th>
						<th scope="col" class="thead width-col3">Email ID</th>
						<th scope="col" class="thead width-col4">
							<div class="close-arrow-flex">
								<span>Create Date</span> <img
									src="./SRS Screen Shorts/arrow-up.svg" alt="">
							</div>
						</th>
						<th scope="col" class="thead width-col5 text-center">Send
							Date</th>
						<th scope="col" class="thead width-col6 text-center">Sent</th>
						<th scope="col" class="thead width-col7 text-center">Sent
							Tries</th>
						<th scope="col" class="thead width-col8 text-center">Confirmation
							Number</th>
					</tr>
				</thead>
				
				<tr class="email-log-tr-clone d-none">
						<td class="extra-padding-td name-td">Shah, Nilomi</td>
						<td class="action-td">Request Monthly Data</td>
						<td class="role-td">Physician</td>
						<td class="email-td">derekshah@gmail.com</td>
						<td class="created-date-td">Aug 23,2024</td>
						<td class="send-date-td">Aug 23,2024</td>
						<td class="mail-sent-td">Yes</td>
						<td class="send-tries-td">1</td>
						<td class="conf-td">MD1234567SDF5678DR</td>
					</tr>
				

				<tbody class="email-log-tbody">
					
				</tbody>
			</table>
			
			<div class="accordion-item accordion-clone-class d-none">
					<h2 class="accordion-header" id="panelsStayOpen-headingOne">
						<button class="accordion-button acc-target"  type="button"
							data-bs-toggle="collapse"
							data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
							aria-controls="panelsStayOpen-collapseOne">
							<div class="accordion-button-flex">

								<big class="mb-3"><span class="email-label acc-name">Patel
										Sagar</span></big> <small class="acount-type-btn-text"> <span
									class="email-label">Action Name: </span> <span
									class="email-text acc-action">Schedule Remainder</span>
								</small> <small class="acount-type-btn-text"> <span
									class="email-label">Email: </span> <span class="email-text acc-email">sagar@gmail.com</span>
								</small>

							</div>

						</button>
					</h2>
					<div id="panelsStayOpen-collapseOne"
						class="accordion-collapse collapse acc-id"
						aria-labelledby="panelsStayOpen-headingOne">
						<div class="accordion-body">
							<div class="accordion-rows-main-flex">

								<div class="accordion-row-inner-flex">
									<div class="image-blue-border">
										<img src="<c:url value='/resources/images/person.svg' />" alt="">
									</div>
									<div>
										<span class="email-label">Role Name: </span> <span
											class="email-text acc-role">Physician</span>
									</div>
								</div>

								<div class="accordion-row-inner-flex">
									<div class="image-blue-border">
										<img src="<c:url value='/resources/images/calendar4-week.svg' />"
											class="calendar-resize-search" alt="">
									</div>
									<div>
										<span class="email-label">Create Date: </span> <span
											class="email-text acc-created">Dec 5, 2022</span>
									</div>
								</div>

								<div class="accordion-row-inner-flex">
									<div class="image-blue-border">
										<img src="<c:url value='/resources/images/calendar4-week.svg' />"
											class="calendar-resize-search" alt="">
									</div>
									<div>
										<span class="email-label">Sent Date: </span> <span
											class="email-text acc-sent">Dec 5, 2022</span>
									</div>
								</div>




								<div class="accordion-row-inner-flex">
									<div class="image-blue-border">
										<img src="<c:url value='/resources/images/tick.svg' />"
											class="tick-icon-resize-search" alt="">
									</div>
									<div>
										<span class="email-label">Sent: </span> <span
											class="email-text acc-sent-bool">Yes </span>
									</div>
								</div>

								<div class="accordion-row-inner-flex">
									<div class="image-blue-border">
										<img src="<c:url value='/resources/images/envelope-blue.svg' />" alt="">
									</div>
									<div>
										<span class="email-label">Sent Tries: </span> <span
											class="email-text acc-sent-tries">1</span>
									</div>
								</div>

								<div class="accordion-row-inner-flex">
									<div class="image-blue-border">
										<img src="<c:url value='/resources/images/tick.svg' />"
											class="tick-icon-resize-search" alt="">
									</div>
									<div>
										<span class="email-label">Confirmation Number: </span> <span
											class="email-text acc-conf">MD1234567SDF5678DR</span>
									</div>
								</div>


							</div>
						</div>
					</div>
				</div>
			

			<div class="accordion  enpty-accordion-class" id="accordionPanelsStayOpenExample">
				

			</div>



		</div>

	</div>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/email-logs.js' />"></script>
	<script type="text/javascript">
		
		$(".common-link-class").removeClass("active")
		$(".records-link-class").addClass("active")
	
	</script>
</body>
</html>
	<%@include file="footer-black.jsp"%>