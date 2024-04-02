<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />


<link rel="stylesheet"
	href="<c:url value="/resources/css/navbar.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/footer.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/patient-dashboard.css" />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">
<script src="<c:url value="/resources/js/patient-dashboard.js" />"></script>
<title>Patient Dashboard</title>
</head>
<body onload="showToast(${showalert})">
	<%@ include file="patient-navbar.jsp"%>
	<div class="container-fluid main-container relative-pos-class">
		<div class="main-container-top-text">Medical History</div>
		<div class="row">
			<div class="col flex-create-request">
				<button class="request-btn" data-bs-toggle="modal"
					data-bs-target="#staticBackdrop">
					<img src="<c:url value="/resources/images/plus-lg.svg" />" alt=""
						class="plus-img"><span class="new-req-text">Create
						New Request</span>
				</button>


				<div class="modal fade" id="staticBackdrop"
					data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
					aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header modal-title-background ">
								<h2 class="modal-title fs-5" id="staticBackdropLabel">
									Create New Request</h2>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<span class="modal-header-text">Here, I want to create
									new request for...</span>
								<div class="request-type-pop-flex">
									<button class="me-type-btn" id="me" onclick="changeCss(this)">Me</button>
									<button class="else-type-btn" id="else"
										onclick="changeCss(this)">Some One Else</button>
								</div>



								<div class="continue-cancel-flex" id="link-for-me">
									<a href="<c:url value='registeredPatientMeRequest' />"><div
											type="button" class="me-type-btn">Continue</div></a>
									<button type="button" class="else-type-btn"
										data-bs-dismiss="modal">Cancel</button>
								</div>

								<div class="continue-cancel-flex" id="link-for-someone-else">
									<a href="<c:url value='registeredPatientOthersRequest' />"><div
											type="button" class="me-type-btn">Continue</div></a>
									<button type="button" class="else-type-btn"
										data-bs-dismiss="modal">Cancel</button>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>
		</div>

		<div class="row">
			<!-- <div class="table-responsive-lg"> -->
			<!-- Add col if required  -->
			<table class="table align-middle">
				<thead>
					<tr>
						<th scope="col" class="thead extra-padding-td width-col1">Created
							Date <img
							src="<c:url value='/resources/images/arrow-down.svg' />"
							class="add-padding" alt="arrow-down">
						</th>
						<th scope="col" class="thead width-col2">Current Status</th>
						<th scope="col" class="thead width-col4"></th>
						<th scope="col" class="thead width-col3 extra-padding-doc">Document</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestData}" var="dataOb">
						<tr>
							<fmt:formatDate value="${dataOb.createdDate}"
								pattern="MMMM dd,yyyy" var="formattedDate" />
							<td class="extra-padding-td">${formattedDate}</td>

							<c:set var="status" value="${dataOb.status}" />
							<c:choose>
								<c:when test="${status == 1}">
									<td>Unassigned</td>
								</c:when>

								<c:when test="${status == 2}">
									<td>Accepted</td>
								</c:when>

								<c:when test="${status == 3}">
									<td>Cancelled</td>
								</c:when>

								<c:when test="${status == 4}">
									<td>MDEnRoute</td>
								</c:when>

								<c:when test="${status == 5}">
									<td>MDONSite</td>
								</c:when>

								<c:when test="${status == 6}">
									<td>Conclude</td>
								</c:when>

								<c:when test="${status == 7}">
									<td>CancelledByPatient</td>
								</c:when>

								<c:when test="${status == 8}">
									<td>Closed</td>
								</c:when>

								<c:when test="${status == 9}">
									<td>Unpaid</td>
								</c:when>

								<c:when test="${status == 10}">
									<td>Clear</td>
								</c:when>
								<c:when test="${status == 11}">
									<td>Blocked</td>
								</c:when>
								<c:otherwise>
									<td>No Status</td>
								</c:otherwise>
							</c:choose>

							<td class="responsive-flex"><c:set var="adminName"
									value="${dataOb.adminFirstName}" /> <c:choose>
									<c:when test="${empty adminName}">
										<button class="table-btn-contact">
											<span class="table-btn-contact-flex"><img
												src="<c:url value='/resources/images/person.svg' />" alt="">No
												Admin</span>
										</button>
									</c:when>
									<c:otherwise>
										<button class="table-btn-contact">
											<span class="table-btn-contact-flex"><img
												src="<c:url value='/resources/images/person.svg' />" alt="">${dataOb.adminFirstName}</span>
										</button>
									</c:otherwise>
								</c:choose> <c:set var="physicianName" value="${dataOb.physicianFirstName}" />
								<c:choose>
									<c:when test="${empty physicianName}">
										<button class="table-btn-contact">
											<span class="table-btn-contact-flex"><img
												src="<c:url value='/resources/images/person-add.svg' />"
												alt="">No Physician</span>
										</button>
									</c:when>
									<c:otherwise>
										<button class="table-btn-contact">
											<span class="table-btn-contact-flex"><img
												src="<c:url value='/resources/images/person-add.svg' />"
												alt="">${dataOb.physicianFirstName}</span>
										</button>
									</c:otherwise>
								</c:choose></td>
							<c:set var="documents" value="${dataOb.count}" />
							<c:choose>
								<c:when test="${documents == 0}">
									<td></td>
								</c:when>
								<c:otherwise>
									<td><a
										href="<c:url value='/patientViewRequestDocuments/${dataOb.requestId }' />"><span
											class="doc-btn">(${dataOb.count}) Documents</span></a></td>
								</c:otherwise>
							</c:choose>

						</tr>
					</c:forEach>
				</tbody>
			</table>


			<!-- </div> -->

			<div class="accordion" id="accordionPanelsStayOpenExample">

				<c:forEach items="${requestData}" var="dataOb">

					<div class="accordion-item">
						<h2 class="accordion-header" id="panelsStayOpen-headingOne">
							<button class="accordion-button" type="button"
								data-bs-toggle="collapse"
								data-bs-target="#id${dataOb.requestId }" aria-expanded="false"
								aria-controls="panelsStayOpen-collapseOne">
								<span class="mobile-card-case-flex"> <img
									src="<c:url value='/resources/images/clock-blue.svg' />" alt="">
									<fmt:formatDate value="${dataOb.createdDate}"
										pattern="MMMM dd,yyyy" var="formattedDate" /> <span
									class="font-500">Created Date:</span> ${formattedDate }
								</span>
							</button>
						</h2>
						<div id="id${dataOb.requestId }"
							class="accordion-collapse collapse"
							aria-labelledby="panelsStayOpen-headingOne">
							<div class="accordion-body">
								<div class="accordian-case-outer-flex">
									<div class="accordian-case-inner-flex">
										<div class="img-icon-border">
											<img src="<c:url value='/resources/images/person-add.svg' />"
												alt="">
										</div>

										<c:set var="physicianName"
											value="${dataOb.physicianFirstName}" />
										<c:choose>
											<c:when test="${empty physicianName}">
												<span class="font-500">Provider : -- </span>
											</c:when>
											<c:otherwise>
												<span class="font-500">Provider :
													${dataOb.physicianFirstName } ${dataOb.physicianLastName }
												</span>
											</c:otherwise>
										</c:choose>

									</div>

									<div class="accordian-case-inner-flex">
										<span class="img-icon-border"> <img
											src="<c:url value='/resources/images/person.svg' />" alt="">
										</span>

										<c:set var="adminName" value="${dataOb.adminFirstName}" />
										<c:choose>
											<c:when test="${empty adminName}">
												<span class="font-500">Admin : -- </span>
											</c:when>
											<c:otherwise>
												<span class="font-500">Admin :
													${dataOb.adminFirstName} ${dataOb.adminLastName} </span>
											</c:otherwise>
										</c:choose>


									</div>

									<div class="accordian-case-inner-flex">
										<div class="img-icon-border">
											<img src="<c:url value='/resources/images/person-add.svg' />"
												alt="">
										</div>

										<c:set var="status" value="${dataOb.status}" />
										<c:choose>
											<c:when test="${status == 1}">
												<span class="font-500">Current Status : Unassigned</span>
											</c:when>

											<c:when test="${status == 2}">
												<span class="font-500">Current Status : Accepted</span>
											</c:when>

											<c:when test="${status == 3}">
												<span class="font-500">Current Status : Cancelled</span>
											</c:when>

											<c:when test="${status == 4}">
												<span class="font-500">Current Status : MD EnRoute</span>
											</c:when>

											<c:when test="${status == 5}">
												<span class="font-500">Current Status : MD On Site</span>
											</c:when>

											<c:when test="${status == 6}">
												<span class="font-500">Current Status : Conclude</span>
											</c:when>

											<c:when test="${status == 7}">
												<span class="font-500">Current Status : Cancelled By
													Patient</span>
											</c:when>

											<c:when test="${status == 8}">
												<span class="font-500">Current Status : Closed</span>
											</c:when>

											<c:when test="${status == 9}">
												<span class="font-500">Current Status : Unpaid</span>
											</c:when>

											<c:when test="${status == 10}">
												<span class="font-500">Current Status : Clear</span>
											</c:when>
											<c:when test="${status == 11}">
												<span class="font-500">Current Status : Blocked</span>
											</c:when>
											<c:otherwise>
												<span class="font-500">Current Status : No Status</span>
											</c:otherwise>
										</c:choose>



									</div>
								</div>

								<div>

									<c:set var="documents" value="${dataOb.count}" />
									<c:choose>
										<c:when test="${documents == 0}">
											<div class="doc-btn extra-prop-doc-btn">No File
												Uploaded</div>

										</c:when>
										<c:otherwise>
											<a
												href="<c:url value='/patientViewRequestDocuments/${dataOb.requestId }' />">
												<div class="doc-btn extra-prop-doc-btn">(${dataOb.count})
													Documents</div>
											</a>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>


		</div>
	</div>
	
		<div class="toaster">
		<div class="toaster-content">
				<c:set var="status" value="${showAlertTypeJsp}" />
				
				<c:choose>
					<c:when test="${status == 'faliure'}">
						<i class="uil uil-exclamation toaster-check"></i>
					</c:when>
					<c:otherwise>
						<i class="uil uil-check toaster-check"></i>
					</c:otherwise>
				</c:choose>
				
				
			
			<div class="message">

				<c:choose>
					<c:when test="${status == 'faliure'}">
						<span class="message-text text-1">Faliure</span>
					</c:when>
					<c:when test="${status == 'success'}">
						<span class="message-text text-1">Success</span>
					</c:when>
					<c:otherwise>
						<span class="message-text text-1">Message</span>
					</c:otherwise>
				</c:choose>

				<span class="message-text text-2"> ${msg}</span>
			</div>
		</div>
		<i class="uil uil-multiply toaster-close"></i>
		<div class="progress"></div>
	</div>

	<%@ include file="footer-black.jsp"%>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/toasters.js' />"></script>
	<script
		src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js' />">
		</body>
		</body>
		</html>
	