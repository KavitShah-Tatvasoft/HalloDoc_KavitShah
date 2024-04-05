<%@page import="org.apache.commons.fileupload.RequestContext"%>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
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
	href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/admin-dashboard.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/FamilyFriend-request.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/pop-ups.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/toaster.css' />">

<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" /> -->
<!-- <script -->
<!-- 	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script> -->

<title>Admin Dashboard</title>
</head>
<body onload="showToast(${showalert})">
	<div class="container-fluid  footer-container mt-5">
		<div class="row">
			<div class="col-lg-2 col-md-4 col-4 mb-2">
				<div class="row-cards new new-active" id="card1"
					onclick="changeStatus(this)">
					<div class="inner-row-cards">
						<img src="<c:url value='/resources/images/new-1.png' />" alt=""
							class="row-card-img hidden new-icon-resize" id="new"> <img
							src="<c:url value='/resources/images/new-white-1.png' />" alt=""
							class="row-card-img new-icon-resize" id="new-active"> <span
							class="row-card-text">NEW</span>
					</div>
					<h4 class="type_no_txt">1449</h4>
					<div id="new-img" class="absolutivity">
						<img
							src="<c:url value='/resources/images/down-arrow-card1.svg' />"
							alt="" class="arrow-width">
					</div>
				</div>
			</div>

			<div class="col-lg-2 col-md-4 col-4 mb-2">
				<div class="row-cards pending" id="card2"
					onclick="changeStatus(this)">
					<div>
						<img src="<c:url value='/resources/images/pending.svg' />" alt=""
							class="row-card-img new-icon-resize" id="pending"> <img
							src="<c:url value='/resources/images/pending_active.png' />"
							alt="" class="row-card-img hidden new-icon-resize"
							id="pending-active"> <span class="row-card-text">PENDING</span>
					</div>
					<h4 class="type_no_txt">271</h4>
					<div id="pending-img" class="absolutivity hidden">
						<img
							src="<c:url value='/resources/images/down-arrow-card2.svg' />"
							alt="" class="arrow-width">
					</div>
				</div>
			</div>

			<div class="col-lg-2 col-md-4 col-4 mb-2">
				<div class="row-cards active" id="card3"
					onclick="changeStatus(this)">
					<div>
						<img src="<c:url value='/resources/images/active.svg' />" alt=""
							class="row-card-img" id="active"> <img
							src="<c:url value='/resources/images/active_active.png' />"
							alt="" class="row-card-img hidden" id="active-active"> <span
							class="row-card-text">ACTIVE</span>
					</div>
					<h4 class="type_no_txt">22</h4>
					<div id="active-img" class="absolutivity hidden">
						<img
							src="<c:url value='/resources/images/down-arrow-card3.svg' />"
							alt="" class="arrow-width">
					</div>
				</div>
			</div>

			<div class="col-lg-2 col-md-4 col-4 mb-2">
				<div class="row-cards conclude" id="card4"
					onclick="changeStatus(this)">
					<div>
						<img src="<c:url value='/resources/images/conclude.svg' />" alt=""
							class="row-card-img new-icon-resize" id="conclude"> <img
							src="<c:url value='/resources/images/conclude_active.png' />"
							alt="" class="row-card-img hidden new-icon-resize"
							id="conclude-active"> <span class="row-card-text">CONCLUDE</span>
					</div>
					<h4 class="type_no_txt">1079</h4>
					<div id="conclude-img" class="absolutivity hidden">
						<img
							src="<c:url value='/resources/images/down-arrow-card4.svg' />"
							alt="" class="arrow-width">
					</div>
				</div>
			</div>

			<div class="col-lg-2 col-md-4 col-4 mb-2">
				<div class="row-cards to-close" id="card5"
					onclick="changeStatus(this)">
					<div>
						<img src="<c:url value='/resources/images/toclose.svg' />" alt=""
							class="row-card-img new-icon-resize" id="to-close"> <img
							src="<c:url value='/resources/images/toclose_active.png' />"
							alt="" class="row-card-img hidden new-icon-resize"
							id="to-close-active"> <span class="row-card-text">TO
							CLOSE</span>

					</div>
					<h4 class="type_no_txt">1452</h4>
					<div id="to-close-img" class="absolutivity hidden">
						<img
							src="<c:url value='/resources/images/down-arrow-card5.svg' />"
							alt="" class="arrow-width">
					</div>
				</div>
			</div>

			<div class="col-lg-2 col-md-4 col-4 mb-2">
				<div class="row-cards unpaid" id="card6"
					onclick="changeStatus(this)">
					<div>
						<img src="<c:url value='/resources/images/unpaid.svg' />" alt=""
							class="row-card-img unpaid-icon" id="unpaid"> <img
							src="<c:url value='/resources/images/unpaid_active.png' />"
							alt="" class="row-card-img unpaid-icon hidden" id="unpaid-active">
						<span class="row-card-text">UNPAID</span>
					</div>
					<h4 class="type_no_txt">1452</h4>
					<div id="unpaid-img" class="absolutivity hidden">
						<img
							src="<c:url value='/resources/images/down-arrow-card6.svg' />"
							alt="" class="arrow-width">
					</div>
				</div>
			</div>

		</div>

		<div class="row">
			<div class="col">
				<div class="patient-btns-flex mt-4">
					<div class="d-flex align-items-center">
						<h4 id="remove-extra-margin">Patients</h4>
						<span id="type-text">(New)</span>
					</div>
					<div>
						<a class="btn btn-info mx-1" role="button" data-bs-toggle="modal"
							data-bs-target="#send-link"> <img
							src="<c:url value='/resources/images/send.svg' />"
							class="btn-info-img"> <span class="btns-text">Send
								Link</span>
						</a> <a href="<c:url value='' />" class="btn btn-info mx-1"
							role="button"> <img
							src="<c:url value='/resources/images/pencil-square.svg' />"
							class="btn-info-img"> <span class="btns-text">Create
								Request</span>
						</a> <a href="#" class="btn btn-info mx-1" role="button"
							onclick="switchBlack()"> <img
							src="<c:url value='/resources/images/export 1.svg' />"
							class="btn-info-img"> <span class="btns-text">Export</span>
						</a> <a href="#" class="btn btn-info mx-1" role="button"
							onclick="switchBlack()"> <img
							src="<c:url value='/resources/images/exxport.svg' />"
							class="btn-info-img"> <span class="btns-text">Export
								All</span>
						</a>

						<button class="btn btn-info mx-1" data-bs-toggle="modal"
							data-bs-target="#staticBackdropdty">
							<img
								src="<c:url value='/resources/images/person-add-white.svg' />"
								class="btn-info-img"> <span class="btns-text">Request
								DTY Support</span>
						</button>
					</div>
				</div>
			</div>
		</div>

		<div class="row mt-2">
			<div class="col">
				<div class="table-top-flex">
					<div class="cover table-top-flex">
						<div class="inner-table-top-flex-left">

							<div class="relativity search-1">
								<input class="form-control search-btn" type="text"
									placeholder="Search Patients"> <img
									src="<c:url value='/resources/images/search.svg' />" alt=""
									class="search-img">
							</div>

							<!-- <div class="dropdown-center relativity ms-2 search-2">
                                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    <span class="region_dropdown">All Region</span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Action</a></li>
                                    <li><a class="dropdown-item" href="#">Action two</a></li>
                                    <li><a class="dropdown-item" href="#">Action three</a></li>
                                </ul>
                                <img src="./SRS Screen Shorts/search.svg" alt="" class="search-img">
                            </div> -->

							<!-- 							// dynamic states list here -->

							<!-- 							<div class="dropdown-center relativity ms-2 search-2"> -->
							<!-- 								<select class="btn btn-secondary form-select select-dropdown-admin"> -->
							<!-- 									<option value=""><a class="dropdown-item" href="#">All -->
							<!-- 											Regions</a></option> -->
							<!-- 									<option><a class="dropdown-item" href="#">Newyork</a></option> -->
							<!-- 									<option><a class="dropdown-item" href="#">Maryland</a></option> -->
							<!-- 									<option><a class="dropdown-item" href="#">London</a></option> -->
							<%-- 								</select> <img src="<c:url value='/resources/images/search.svg' />" alt="" --%>
							<!-- 									class="search-img"> -->

							<!-- 							</div> -->

							<div class="admin-table-filter">
								<div class="d-flex align-items-center">
									<!-- <button
                                                    class="btn btn-secondary dropdown-toggle d-flex align-items-center"
                                                    type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    Dropdown button
                                                </button> -->

									<div class="admin-table-filter-by-select">
										<select class="form-select on-select" id="select"
											aria-label="Default select example" style="color: gray;">
											<option selected value="All">All</option>

											<c:forEach items="${regionList}" var="region">
												<option value="${region.name }">${region.name }</option>
											</c:forEach>
										</select>
										<button type="button" class="dropdown-button-search">
											<img src="<c:url value='/resources/images/search.svg'/>"
												class="dropsearch" alt="" srcset="">
										</button>
									</div>

								</div>
							</div>

						</div>

						<div class="inner-table-top-flex-right gap-2 me-2">
							<!-- provided flex to all child using class > * {}  -->
							<div>
								<button class="all-label">All</button>
							</div>
							<div class="gap-2 patient-type-flex">
								<div id="green-dot"></div>
								<span class="table-top-flex-text">Patient</span>
							</div>
							<div class="gap-2 patient-type-flex">
								<div id="orange-dot"></div>
								<span class="table-top-flex-text">Family/Friend</span>
							</div>
							<div class="gap-2 patient-type-flex">
								<div id="magenta-dot"></div>
								<span class="table-top-flex-text">Business</span>
							</div>
							<div class="gap-2 patient-type-flex">
								<div id="blue-dot"></div>
								<span class="table-top-flex-text">Concierge</span>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

		<div class="table-responsive show-table">
			<table class="table" id="admin-table">
				<thead id="table-thead-admin">
					<tr class="thead-clr align-middle" id="table-headers-admin">
						<th colspan="2" >Name</th>
						<th ></th>
						<th class="table-columns s1 s2 s3 s4 s5">Date of Birth</th>
						<th class="table-columns  s5">Region</th>
						<th class="table-columns  s1 s2 s3">Requestor</th>
						<th class="table-columns  s2 s3 s4 s5 s6">Physician Name</th>
						<th class="table-columns  s2 s3 s4 s5 s6">Date of Service</th>
						<th class="table-columns  s1">Requested Date</th>
						<th class="table-columns  s1 s2 s3 s4 s6">Phone</th>
						<th >Address</th>
						<th class="table-columns  s1 s2 s3 s5">Notes</th>
						<th >Actions</th>
					</tr>
				</thead>
				<tbody class="align-middle">
					<tr class="tr-clr">
						<td colspan="2"><span class="text-nowrap">Brown,
								Ernest</span></td>
						<td>
							<button type="button" class="theme-btn" id="message-btn"></button>
							<label for="message-btn"> <img
								src="<c:url value='/resources/images/envelope.svg' />"
								alt="message" class="envelope">
						</label>
						</td>
						<td class="text-nowrap">Oct 13,2022(0)</td>
						<td class="text-nowrap">District of Columbia</td>
						<td></td>
						<td>Dr. Brown</td>
						<td></td>
						<td></td>
						<td>1331, Maryland Ave SW Washington, DC 20024</td>
						<td>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
							Vel tenetur rem, eius assumenda accusamus fugit!</td>
						<td>
							<button type="button" class="theme-btn" id="patient-btn"
								onclick="switchBlack()"></button> <label for="patient-btn"
							class="patient-label"> <img
								src="<c:url value='/resources/images/user.png' />" alt="message"
								class="patient-logo"> <span>Patient</span>
						</label>

							<button type="button" class="theme-btn" id="patient-btn"
								onclick="switchBlack()"></button> <label for="patient-btn"
							class="patient-label"> <img
								src="<c:url value='/resources/images/user.png' />" alt="message"
								class="patient-logo"> <span>Provider</span>
						</label>
						</td>
						<!-- <td class="table-dropdown">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                    Dropdown
                                </button>
                                <ul class="dropdown-menu">
                                    <li><button class="dropdown-item" type="button">Action</button></li>
                                    <li><button class="dropdown-item" type="button">Another action</button></li>
                                    <li><button class="dropdown-item" type="button">Something else here</button></li>
                                </ul>
                            </div>
                        </td> -->

						<td>
							<div class="patient-records-document-action-button">
								<button type="button"
									class="btn dashboard-dropdown-btn dropdown-toggle"
									data-bs-toggle="dropdown" aria-expanded="false">
									Action</button>
								<ul class="dropdown-menu dropdown-menu-end">
									<li>
										<div class="action-dropdown-flex dropdown-item ">
											<img
												src="<c:url value='/resources/images/journal-check-grey.svg' />"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" role="button"
												data-bs-toggle="modal" data-bs-target="#assign-case">Assign
												Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="<c:url value='/resources/images/x-circle-grey.svg' />"
												class="dropdown-icons" alt=""> <a
												class=" action-dropdown-text" type="button"
												data-bs-toggle="modal" data-bs-target="#cancel-case">Cancel
												Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item ">
											<img
												src="<c:url value='/resources/images/x-circle-grey.svg' />"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#clear-case" type="button">Clear Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="<c:url value='/resources/images/view-case.png' />"
												class="dropdown-icons" alt=""> <a
												href="view-case.html" class=" action-dropdown-text"
												type="button">View Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="<c:url value='/resources/images/x-circle-grey.svg' />"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" role="button"
												href="close-case.html">Close Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="<c:url value='/resources/images/journal-text.svg' />"
												class="dropdown-icons" alt=""> <a
												href="view-notes.html" class="action-dropdown-text"
												type="button">View Notes</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="<c:url value='/resources/images/ban-grey.svg' />"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#block-case" role="button">Block Patient</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="<c:url value='/resources/images/view-upload.png' />"
												class="dropdown-icons" alt=""> <a
												href="<c:url value='' />" class="action-dropdown-text "
												type="button">View Uploads</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="<c:url value='/resources/images/journal-check-grey.svg' />"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#transfer-case" type="button">Transfer</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="<c:url value='/resources/images/document.png' />"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#send-agreement" type="button">Send
												Agreement</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="<c:url value='/resources/images/order-delivery.png' />"
												class="dropdown-icons" alt=""> <a
												href="<c:url value='' />" class="action-dropdown-text"
												type="button">Orders</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="<c:url value='/resources/images/document.png' />"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" type="button"
												href="<c:url value='' />">Doctor Notes</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="<c:url value='/resources/images/document.png' />"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" type="button"
												href="<c:url value='' />">Encounter</a>
										</div>
									</li>

								</ul>
							</div>
						</td>
					</tr>


				</tbody>
			</table>
		</div>

		<div class="accordion mb-4" id="accordionPanelsStayOpenExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="panelsStayOpen-headingOne">
					<button class="accordion-button" type="button"
						data-bs-toggle="collapse"
						data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
						aria-controls="panelsStayOpen-collapseOne">

						<div class="card-head">
							<div class="patient-type-card-main-flex-item">
								<div class="patient_card_name">
									<div class="add-patient-type-margin">Patient, Test</div>
								</div>

								<div class="admin-dash-patient-type-flex">
									<span class="patient-type-text">Patient</span>
									<div class="patient-type-color-icon"></div>
								</div>
							</div>

							<div class="patient-address-map-btn-flex-admin">
								<div class="patient_card_address_admin">1331 Mayland
									Avenue, SW Washington, DC 20024</div>

								<a href="<c:url value='' />" class="map_btn_admin_accordian">Map
									Location</a>
							</div>
						</div>
					</button>
				</h2>
				<div id="panelsStayOpen-collapseOne"
					class="accordion-collapse collapse"
					aria-labelledby="panelsStayOpen-headingOne">
					<div class="accordion-body">
						<hr class="no-margin-hr" />
						<div class="patient_card extended-1" id="extended-1">
							<a href="<c:url value='' />" class="view_case_btn" role="button">
								View Case </a>
							<div class="extended-flex">
								<span class="circular_border"><img
									src="<c:url value='/resources/images/calendar4-week-blue.svg' />"
									alt="D.O.B" /></span> <span class="extended-label">Date of
									Birth:</span> <span class="extended-text">15-01-2003 (21)</span>
							</div>
							<div class="extended-flex">
								<span class="circular_border"><img
									src="<c:url value='/resources/images/envelope-blue.svg' />"
									alt="Email" /></span> <span class="extended-label">Email:</span> <span
									class="extended-text">kavit.shah@etatvasoft.com</span>
							</div>
							<div class="extended-flex">
								<span class="circular_border"><img
									src="<c:url value='/resources/images/telephone-blue.svg' />"
									alt="Number" /></span> <span class="extended-label">Patient:</span> <span
									class="extended-text">6351627219</span>
							</div>
							<div class="extended-flex">
								<span class="circular_border"><img
									src="<c:url value='/resources/images/journal-text.svg' />"
									alt="Note" /></span> <span class="extended-label">Transfer<br />Note:
								</span> <span class="extended-text">Lorem ipsum dolor sit amet
									consectetur adipisicing elit.</span>
							</div>
							<div class="extended-flex">
								<span class="circular_border"><img
									src="<c:url value='/resources/images/calendar4-week-blue.svg' />"
									alt="D.O.S" /></span> <span class="extended-label">Date of
									Service:</span> <span class="extended-text">Jan 31,2023 8:30 AM</span>
							</div>
							<div class="extended-flex">
								<span class="circular_border"><img
									src="<c:url value='/resources/images/person-add.svg' />"
									alt="Physician" /></span> <span class="extended-label">Physician:</span>
								<span class="extended-text">Dr. AGOLA</span>
							</div>
							<div class="extended-flex">
								<span class="circular_border"><img
									src="<c:url value='/resources/images/person.svg' />"
									alt="Requestor" /></span> <span class="extended-label">Requestor:</span>
								<span class="extended-text">Patient Agola Three,
									BhoomiOne</span>
							</div>

							<div class="row p-3 text-center">

								<div class="col-6 mb-3 accordion-btns">
									<a class="accordion-btn-admin purple-background-btn"
										role="button" data-bs-toggle="modal"
										data-bs-target="#assign-case">Assign Case</a>
								</div>

								<div class="col-6 mb-3">
									<a class="accordion-btn-admin purple-background-btn"
										role="button" href="<c:url value='' />">Close Case</a>
								</div>

								<div class="col-6 mb-3">
									<a class="accordion-btn-admin orange-background-btn"
										role="button" data-bs-toggle="modal"
										data-bs-target="#send-agreement">Send Agreement</a>
								</div>

								<div class="col-6 mb-3 accordion-btns">
									<a class="accordion-btn-admin red-background-btn" role="button"
										data-bs-toggle="modal" data-bs-target="#cancel-case">Cancel
										Case</a>
								</div>

								<div class="col-6 mb-3">
									<a class="accordion-btn-admin red-background-btn"
										data-bs-toggle="modal" data-bs-target="#block-case"
										role="button">Block Case</a>
								</div>

								<div class="col-6 mb-3 accordion-btns">
									<a href="<c:url value='' />"
										class="accordion-btn-admin green-background-btn" role="button">View
										Notes</a>
								</div>

								<div class="col-6 mb-3">
									<a href="view-uploads-patient-conclude.html"
										class="accordion-btn-admin green-background-btn" role="button">View
										Uploads</a>
								</div>

								<div class="col-6 mb-3">
									<a href="<c:url value='' />"
										class="accordion-btn-admin orange-background-btn"
										role="button">Orders</a>
								</div>

								<div class="col-6 accordion-btns">
									<a class="accordion-btn-admin blue-background-btn"
										data-bs-toggle="modal" data-bs-target="#transfer-case"
										role="button">Transfer</a>
								</div>

								<div class="col-6 mb-3">
									<a class="accordion-btn-admin green-background-btn"
										href="<c:url value='' />" role="button">Encounter</a>
								</div>

								<div class="col-6 mb-3 accordion-btns">
									<a class="accordion-btn-admin dark-green-background-btn"
										href="<c:url value='' />" role="button">Doctors Note</a>
								</div>

								<div class="col-6 mb-3">
									<a class="accordion-btn-admin orange-background-btn"
										data-bs-toggle="modal" data-bs-target="#clear-case"
										role="button">Clear Case</a>
								</div>

								<div class="col-6 mb-3 accordion-btns">
									<a class="accordion-btn-admin green-background-btn"
										role="button">Email</a>
								</div>



							</div>


							<div class="extended-flex-bottom">
								<span class="bottom-btn-texts">Chat with:</span>
								<div class="btn">
									<img src="<c:url value='/resources/images/person.svg' />"
										class="bottom-btn-img" alt="" />Patient
								</div>
								<div class="btn">
									<img src="<c:url value='/resources/images/person-add.svg' />"
										class="bottom-btn-img" alt="" />Provider
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- --------------All Pop Ups--------------- -->

	<div class="modal fade" id="send-agreement" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content agreement-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Send
						Agreement</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="agreement-patient-type-flex">
						<div class="patient-type"></div>
						<span class="agreement-text">Patient</span>
					</div>
					<span class="agreement-text">To Send Agreement please make
						sure that you are updating the correct contact information below
						for the responsible party. </span><br>
					<div>

						<form action="">
							<div class="col-12 mt-3">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 agreement-inp"
										id="floatingInput-2" placeholder="Phone Number"
										autocomplete="off"> <label for="floatingInput-2"
										class="agreement-label">Phone Number</label>
								</div>
							</div>

							<div class="col-12">
								<div class="form-floating mb-3 inp">
									<input type="email" class="form-control input-1 agreement-inp"
										id="floatingInput-2" placeholder="Email" autocomplete="off">
									<label for="floatingInput-2" class="agreement-label">Email</label>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="send-btn">Confirm</button>
						<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- send agreement -->


	<div class="modal fade" id="staticBackdropdty"
		data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel-1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel-1">Request
						Support</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="font-clr-light">To all unscheduled Physicians: We
						are short on coverage and needs additional support On Call to
						respond to Requests.</div>
					<br>

					<form action="">
						<textarea name="message" class="form-control" id="" cols="10"
							rows="5" placeholder="Message"></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="send-btn">Send</button>
					<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

				</div>
			</div>
		</div>
	</div>

	<!-- dty support  -->

	<div class="modal fade" id="send-link" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content agreement-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Send
						mail to patient for submitting request</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div>

						<form action="">
							<div class="col-12 mt-3">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 agreement-inp"
										id="floatingInput-2" placeholder="First Name"
										autocomplete="off"> <label for="floatingInput-2"
										class="agreement-label">First Name</label>
								</div>
							</div>

							<div class="col-12 mt-3">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 agreement-inp"
										id="floatingInput-2" placeholder="Last Name"
										autocomplete="off"> <label for="floatingInput-2"
										class="agreement-label">Last Name</label>
								</div>
							</div>

							<div class="col-12 col-md-6 ">
								<!--Phone Number col-->
								<div class="form-floating inp send-link-pop-up-only">
									<input type="tel"
										class="form-control phoneflags phonecolheight" id="phone" />
								</div>
							</div>

							<div class="col-12">
								<div class="form-floating mb-3 inp">
									<input type="email" class="form-control input-1 agreement-inp"
										id="floatingInput-2" placeholder="Email" autocomplete="off">
									<label for="floatingInput-2" class="agreement-label">Email</label>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="send-btn">Send</button>
						<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- send link -->

	<div class="modal fade" id="cancel-case" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content cancel-pop-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Confirm
						Cancellation</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div>
						<span class="font-clr-light">Patient Name :</span> <span
							class="cancel-case-blue-text">John Snow</span><br>
					</div>

					<form action="">
						<div class="container">
							<div
								class="row cancel-pop-up-select-height cancel-pop-margin-top">

								<select name="Number_Type" id="floatingInput-5"
									class="form-control form-select cancel-case-pop-text-clr">
									<option value="check" hidden selected>Reason for
										Cancellation</option>
									<option value="Mobile">Reason 1</option>
									<option value="Home">Reason 2</option>
								</select>

							</div>
						</div>
						<textarea name="message"
							class="form-control cancel-pop-margin-top" id="" cols="12"
							rows="5" placeholder="Provide Additional Notes  "></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="send-btn">Confirm</button>
					<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

				</div>
			</div>
		</div>
	</div>

	<!-- cancel-case -->

	<div class="modal fade" id="assign-case" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content assign-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Assign
						Request</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div>
						<span class="font-clr-light">To assign this request, search
							and select another Physician.</span><br>
					</div>

					<form action="">

						<div class="col-12 mt-3">
							<div class="form-floating mb-3 inp">
								<select name="Number_Type" id="floatingInput-5"
									class="form-control form-select input-2">
									<option value="Mobile" selected>Newyork</option>
									<option value="Home">London</option>
									<option value="Home">Germany</option>
								</select> <label for="floatingInput-5">Narrow Search by Region</label>
							</div>
						</div>

						<div class="container">
							<div class="row assign-case-select-height assign-case-margin-top">

								<select name="Number_Type" id="floatingInput-5"
									class="form-control form-select assign-case-text-clr">
									<option value="check" hidden selected>Select Physician</option>
									<option value="Mobile">Person 1</option>
									<option value="Home">Person 2</option>
								</select>

							</div>
						</div>
						<textarea name="message"
							class="form-control assign-case-margin-top" id="" cols="12"
							rows="5" placeholder="Description"></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="send-btn">Confirm</button>
					<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

				</div>
			</div>
		</div>
	</div>

	<!-- assign-case -->

	<div class="modal fade" id="block-case" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content cancel-pop-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Confirm
						Block</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div>
						<span class="font-clr-light">Patient Name :</span> <span
							class="cancel-case-blue-text">John Snow</span><br>
					</div>

					<form action="">
						<textarea name="message"
							class="form-control cancel-pop-margin-top" id="" cols="12"
							rows="5" placeholder="Reason for Block Request"></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="send-btn">Confirm</button>
					<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

				</div>
			</div>
		</div>
	</div>

	<!-- block-case -->

	<div class="modal fade" id="transfer-case" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content assign-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Transfer
						Request</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div>
						<span class="font-clr-light">To assign this request, search
							and select another Physician.</span><br>
					</div>

					<form action="">

						<div class="col-12 mt-3">
							<div class="form-floating mb-3 inp">
								<select name="Number_Type" id="floatingInput-5"
									class="form-control form-select input-2">
									<option value="Mobile" selected>Newyork</option>
									<option value="Home">London</option>
									<option value="Home">Germany</option>
								</select> <label for="floatingInput-5">Narrow Search by Region</label>
							</div>
						</div>

						<div class="container">
							<div class="row assign-case-select-height assign-case-margin-top">

								<select name="Number_Type" id="floatingInput-5"
									class="form-control form-select assign-case-text-clr">
									<option value="check" hidden selected>Select Physician</option>
									<option value="Mobile">Person 1</option>
									<option value="Home">Person 2</option>
								</select>

							</div>
						</div>
						<textarea name="message"
							class="form-control assign-case-margin-top" id="" cols="12"
							rows="5" placeholder="Description"></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="send-btn">Confirm</button>
					<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

				</div>
			</div>
		</div>
	</div>

	<!-- transfer-case -->

	<div class="modal fade" id="clear-case" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content modal-content-clear-case">
				<div class="modal-body family-pop">
					<img src="<c:url value='/resources/images/exclamation.png' />"
						alt="" class="set-img">
					<p class="information">Confirmation for clear case</p>
					<span class="popup-txt">Are you sure you want to clear this
						request? Once cleared this request then you are not able to see
						this request.</span>
					<div class="modal-footer mt-3">
						<button type="button" class="send-btn">Clear</button>
						<button type="button" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>
					</div>
				</div>
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


	<%@include file="footer-black.jsp"%>

	<!-- clear-case -->
	<script>
		const phoneInputField = document.querySelector("#phone");
		const phoneInput = window
				.intlTelInput(
						phoneInputField,
						{
							utilsScript : "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
						});
	</script>

	<script src="<c:url value='/resources/js/toasters.js' />"></script>
	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/admin-dashboard.js' />"></script>
	<script
		src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js' />"></script>
</body>
</html>