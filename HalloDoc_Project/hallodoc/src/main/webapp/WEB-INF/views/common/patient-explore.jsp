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
<link rel="stylesheet" href="<c:url value='/resources/css/patient-explore-record.css' />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<title>Patient History</title>
</head>
<body>
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="submit-info-txt">Patient Record</div>

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />" alt=""> Back
		</div>

		<div class="table-responsive">
			<table class="table align-middle mt-4">
				<thead class="table-active  align-middle">
					<tr>
						<th scope="col" class="thead width-col-1 extra-padding-td">Client/Member</th>
						<th scope="col" class="thead width-col-2">
							<div class="table-row1-flex">
								<span>Created Date</span><img
									src="<c:url value='/resources/images/arrow-up.svg' />" class="arrow-up-icon">
							</div>
						</th>
						<th scope="col" class="thead width-col-3">Confirmation</th>
						<th scope="col" class="thead width-col-4">Provider Name</th>
						<th scope="col" class="thead width-col-5">Conclude Date</th>
						<th scope="col" class="thead width-col-6">Status</th>
						<th scope="col" class="thead width-col-7">Final Report</th>
						<th scope="col" class="thead width-col-8 text-center">Actions</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${dataLists }" var="request">
					
					<tr>
						<td class="extra-padding-td">${request.requestClient }</td>
						<td>${request.createdDate }</td>
						<td>${request.confNumber }</td>
						<td>${request.providerName }</td>
						<td>-</td>
						<td>${request.status }</td>
						<td><a href="#" role="button" class="view-final-report-btn">View</a></td>
						<td class="text-center">
							<div class="patient-records-document-action-button">
								<button type="button" class="btn row-btn-action dropdown-toggle"
									data-bs-toggle="dropdown" aria-expanded="false">
									Actions</button>
								<ul class="dropdown-menu dropdown-menu-end">
									<li><a href="../../admin/viewCase/${request.requestId}" class="dropdown-item"
										type="button">View Case</a></li>
									<li><a href="../viewRequestUploads/${request.requestId}"
										class="dropdown-item" type="button">(${request.docCount }) Documents</a></li>
								</ul>
							</div>
						</td>
					</tr>



					</c:forEach>
				</tbody>
			</table>

			<div class="accordion mt-4 ms-3 me-3 mb-3"
				id="accordionPanelsStayOpenExample">
				<c:forEach items="${dataLists }" var="request">
					<div class="accordion-item">
						<h2 class="accordion-header" id="panelsStayOpen-headingOne">
							<button class="accordion-button" type="button"
								data-bs-toggle="collapse"
								data-bs-target="#accordion${request.requestId}"
								aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
								<div class="accordion-button-flex">
									<span class="accordion-row-name">${request.requestClient }</span> <small
										class="acount-type-btn-text">${request.confNumber }</small>
								</div>

							</button>
						</h2>
						<div id="accordion${request.requestId}"
							class="accordion-collapse collapse "
							aria-labelledby="panelsStayOpen-headingOne">
							<div class="accordion-body">
								<div class="accordion-rows-main-flex">

									<div class="accordion-row-inner-flex">
										<div class="image-blue-border">
											<img
												src="<c:url value='/resources/images/calendar4-week.svg' />"
												class="calendar-icon-resize" alt="">
										</div>
										<div>
											<span class="email-label">Created Date: </span> <span
												class="email-text">${request.createdDate }</span>
										</div>
									</div>

									<div class="accordion-row-inner-flex">
										<div class="image-blue-border">
											<img src="<c:url value='/resources/images/person-add.svg' />"
												alt="">
										</div>
										<div>
											<span class="email-label">Provider: </span> <span
												class="email-text">${request.providerName }</span>
										</div>
									</div>

									<div class="accordion-row-inner-flex">
										<div class="image-blue-border">
											<img
												src="<c:url value='/resources/images/calendar4-week.svg' />"
												class="calendar-icon-resize" alt="">
										</div>
										<div>
											<span class="email-label">Concluded Date:</span> <span
												class="email-text">-</span>
										</div>
									</div>

									<div class="accordion-row-inner-flex">
										<div class="image-blue-border">
											<img src="<c:url value='/resources/images/tick.svg' />"
												class="tick-icon-resize" alt="">
										</div>
										<div>
											<span class="email-label">Status: </span> <span
												class="email-text">${request.status }</span>
										</div>
									</div>

									<div class="view-case-doc-btn-flex">
										<a href="../../admin/viewCase/${request.requestId}" role="button"
											class="view-case-doc-btn mt-4">View Case</a> <a
											href="../viewRequestUploads/${request.requestId}" role="button"
											class="view-case-doc-btn">(2) Documents</a>
									</div>

								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>


		</div>

	</div>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
</body>
</html>
<%@include file="footer-black.jsp"%>