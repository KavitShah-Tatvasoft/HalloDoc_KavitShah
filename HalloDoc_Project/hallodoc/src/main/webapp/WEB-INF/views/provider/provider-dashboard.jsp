<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@include file="provider-dashboard.jsp"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<c:url value='/resources/css/loader.css' />">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/provider-dashboard.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/pop-ups.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/FamilyFriend-request.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<title>Provider Dashboard</title>
</head>
<body onload="loadData('new'), loadCount()">
	<div class="container-fluid mt-5 footer-containerF">
		<div class="loader-container">
			<div class="loader"></div>
		</div>
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
					<h4 class="type_no_txt" id="new-request-count">1</h4>
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
					<h4 class="type_no_txt" id="pending-request-count">1</h4>
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
					<h4 class="type_no_txt" id="active-request-count">1</h4>
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
					<h4 class="type_no_txt" id="conclude-request-count">1</h4>
					<div id="conclude-img" class="absolutivity hidden">
						<img
							src="<c:url value='/resources/images/down-arrow-card4.svg' />"
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
						<span id="type-text" data-state="new"
							class="state-type-class-name">(New)</span>
					</div>
					<div>
						<a class="btn btn-info mx-1" role="button" data-bs-toggle="modal"
							data-bs-target="#send-link"> <img
							src="<c:url value='/resources/images/send.svg' />"
							class="btn-info-img"> <span class="btns-text">Send
								Link</span>
						</a> <a href="create-provider-new-request" class="btn btn-info mx-1"
							role="button"> <img
							src="<c:url value='/resources/images/pencil-square.svg' />"
							class="btn-info-img"> <span class="btns-text">Create
								Request</span>
						</a>


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

							<div class="relativity ms-2 search-2">
								<select class="form-select select-dropdown-admin" type="button">
									<option value="0"><a class="dropdown-item" href="#">All
											Regions</a></option>
									<c:forEach items="${regionList}" var="region">

										<option value="${region.regionId }"><a
												class="dropdown-item" href="#">${region.name }</a></option>
									</c:forEach>

								</select> <img src="<c:url value='/resources/images/search.svg' />"
									alt="" class="search-img">

							</div>

						</div>

						<div
							class="inner-table-top-flex-right inner-table-top-flex-right-top gap-2 me-2">
							<!-- provided flex to all child using class > * {}  -->
							<div>
								<button class="all-label button-class active-btn d-none"
									id="all-type-req" data-value="All"
									onclick="changeActiveBtn(this);filterRequest()"
									id="all-type-req">All</button>
								<label for="all-type-req" style="display: flex"
									class="show-active-button-class commom-label-class">
									<div class="gap-2 patient-type-flex inner-table-top-flex-right">
										<span class="table-top-flex-text">All</span>
									</div>
								</label>
							</div>

							<button id="patient-type-req" class="button-class"
								data-value="Patient"
								onclick="changeActiveBtn(this);filterRequest()"
								style="display: none;">Patient</button>
							<label for="patient-type-req" style="display: flex"
								class="commom-label-class">
								<div class="gap-2 patient-type-flex inner-table-top-flex-right">
									<div id="green-dot"></div>
									<span class="table-top-flex-text">Patient</span>
								</div>
							</label>

							<button id="family-type-req" class=" button-class"
								data-value="Family"
								onclick="changeActiveBtn(this);filterRequest()"
								style="display: none;">Family</button>
							<label for="family-type-req" style="display: flex"
								class="commom-label-class">
								<div class="gap-2 patient-type-flex inner-table-top-flex-right">
									<div id="orange-dot"></div>
									<span class="table-top-flex-text">Family/Friend</span>
								</div>
							</label>

							<button id="business-type-req" class=" button-class"
								data-value="Business"
								onclick="changeActiveBtn(this);filterRequest()"
								style="display: none;">Business</button>
							<label for="business-type-req" style="display: flex"
								class="commom-label-class">
								<div class="gap-2 patient-type-flex inner-table-top-flex-right">
									<div id="magenta-dot"></div>
									<span class="table-top-flex-text">Business</span>
								</div>
							</label>

							<button class="button-class" id="concierge-type-req"
								data-value="Concierge"
								onclick="changeActiveBtn(this);filterRequest()"
								style="display: none;">Concierge</button>
							<label for="concierge-type-req" style="display: flex"
								class="commom-label-class">
								<div class="gap-2 patient-type-flex inner-table-top-flex-right">
									<div id="blue-dot"></div>
									<span class="table-top-flex-text">Concierge</span>
								</div>
							</label>
						</div>
					</div>
				</div>
			</div>

		</div>

		<div class="table-responsive show-table">
			<table class="table">
				<thead>
					<tr class="thead-clr align-middle">
						<th colspan="2">Name</th>
						<th></th>
						<th>Phone</th>
						<th>Address</th>
						<th class="status-class-tr d-none">Status</th>
						<th>Actions</th>
					</tr>
				</thead>

				<tr class="tr-clr color-class phy-dash-tr-clone d-none">
					<th colspan="2"><span class="text-nowrap name-tr">Brown,
							Ernest</span></th>
					<td>
						<button type="button" class="theme-btn" id="message-btn"></button>
						<label for="message-btn"> <img
							src="<c:url value='/resources/images/envelope.svg' />"
							alt="message" class="envelope">
					</label>
					</td>
					<td>
						<div class="phone-number-type-flex">
							<div>
								<a role="button" class="phone-number-provider-flex"> <img
									src="<c:url value='/resources/images/telephone.svg' />" alt=""><span
									class="number-provider-row number-tr">+1(202)456 7890</span>
								</a>
							</div>

							<div class="type-patient">(Patient)</div>

							<div class="hide-number-div">
								<a role="button" class="phone-number-provider-flex"> <img
									src="<c:url value='/resources/images/telephone.svg' />" alt=""><span
									class="number-provider-row number-req-tr">+1(202)456
										7890</span>
								</a>
							</div>

							<div class="type-patient req-type hide-number-div">(Patient)</div>
						</div>
					</td>
					<td class="text-nowrap address-tr">1331, Maryland Ave SW
						Washington, DC 20024</td>
					<td class="status-class-tr d-none">
						<div role="button" class="house-call-status"
							data-bs-toggle="modal" data-bs-target="#house-call"
							id="housecall-consult-text">House Call</div>
					</td>


					<td>
						<div class="patient-records-document-action-button">
							<button type="button"
								class="btn dashboard-dropdown-btn dropdown-toggle"
								data-bs-toggle="dropdown" aria-expanded="false">
								Actions</button>
							<ul class="dropdown-menu dropdown-menu-end">





								<li>
									<div class=" action-dropdown-flex dropdown-item">
										<img src="<c:url value='/resources/images/view-case.png' />"
											class="dropdown-icons" alt=""> <a href="view-case.html"
											class=" action-dropdown-text view-case-set-link" type="button">View Case</a>
									</div>
								</li>



								<li>
									<div class=" action-dropdown-flex dropdown-item ">
										<img
											src="<c:url value='/resources/images/journal-text.svg' />"
											class="dropdown-icons" alt=""> <a
											href="view-notes.html" class="action-dropdown-text view-notes-send-link"
											type="button">View Notes</a>
									</div>
								</li>



								<li
									class="common-action-class pendings actives concludes d-none">
									<div class="action-dropdown-flex dropdown-item">
										<img src="<c:url value='/resources/images/view-upload.png' />"
											class="dropdown-icons" alt=""> <a
											href="view-uploads-patient-conclude.html"
											class="action-dropdown-text " type="button">View Uploads</a>
									</div>
								</li>

								<li class="common-action-class pendings news">
									<div class="action-dropdown-flex dropdown-item">
										<img
											src="<c:url value='/resources/images/journal-check-grey.svg' />"
											class="dropdown-icons" alt=""> <a
											class="action-dropdown-text transfer-case-button-class" data-bs-toggle="modal"
											data-bs-target="#transfer-case" type="button">Transfer</a>
									</div>
								</li>

								<li class="common-action-class pendings  d-none">
									<div class="action-dropdown-flex dropdown-item">
										<img src="<c:url value='/resources/images/document.png' />"
											class="dropdown-icons" alt=""> <a
											class="action-dropdown-text send-agreement-btn" data-bs-toggle="modal"
											data-bs-target="#send-agreement" type="button">Send
											Agreement</a>
									</div>
								</li>

								<li class="common-action-class actives concludes d-none">
									<div class="action-dropdown-flex dropdown-item">
										<img
											src="<c:url value='/resources/images/order-delivery.png' />"
											class="dropdown-icons" alt=""> <a
											href="send-order.html" class="action-dropdown-text"
											type="button">Orders</a>
									</div>
								</li>

								<li class="common-action-class actives concludes d-none">
									<div class="action-dropdown-flex dropdown-item">
										<img src="<c:url value='/resources/images/document.png' />"
											class="dropdown-icons" alt=""> <a
											class="action-dropdown-text" type="button"
											href="view-notes.html">Doctor Notes</a>
									</div>
								</li>

								<li class="common-action-class actives concludes d-none">
									<div class="action-dropdown-flex dropdown-item">
										<img src="<c:url value='/resources/images/document.png' />"
											class="dropdown-icons" alt=""> <a
											class="action-dropdown-text" type="button"
											href="encounter-form.html">Encounter</a>
									</div>
								</li>

								<li class="common-action-class news ">
									<div class="action-dropdown-flex dropdown-item">
										<img src="<c:url value='/resources/images/accept.png' />"
											class="dropdown-icons" alt=""> <a
											class="action-dropdown-text accept-case-button-class"
											role="button" data-bs-toggle="modal"
											data-bs-target="#accept-case">Accept</a>
									</div>
								</li>

								<li class="common-action-class concludes d-none">
									<div class="action-dropdown-flex dropdown-item">
										<img src="<c:url value='/resources/images/heart-rate.png' />"
											class="dropdown-icons" alt=""> <a
											class="action-dropdown-text" type="button"
											href="conclude-care.html">Conclude Care</a>
									</div>
								</li>

							</ul>
						</div>
					</td>
				</tr>


				<tbody class="align-middle tbody-empty">


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

								<a href="" class="map_btn_admin_accordian">Map Location</a>
							</div>
						</div>
					</button>
				</h2>
				<div id="panelsStayOpen-collapseOne"
					class="accordion-collapse collapse show"
					aria-labelledby="panelsStayOpen-headingOne">
					<div class="accordion-body">
						<hr class="no-margin-hr" />
						<div class="patient_card extended-1" id="extended-1">
							<a href="view-case.html" class="view_case_btn" role="button">
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
										role="button" href="close-case.html">Close Case</a>
								</div>

								<div class="col-6 mb-3">
									<a class="accordion-btn-admin orange-background-btn send-agreement-btn"
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
									<a href="view-notes.html"
										class="accordion-btn-admin green-background-btn" role="button">View
										Notes</a>
								</div>

								<div class="col-6 mb-3">
									<a href="view-uploads-patient-conclude.html"
										class="accordion-btn-admin green-background-btn" role="button">View
										Uploads</a>
								</div>

								<div class="col-6 mb-3">
									<a href="send-order.html"
										class="accordion-btn-admin orange-background-btn"
										role="button">Orders</a>
								</div>

								<div class="col-6 accordion-btns">
									<a class="accordion-btn-admin blue-background-btn transfer-case-button-class"
										data-bs-toggle="modal" data-bs-target="#transfer-case"
										role="button">Transfer</a>
								</div>

								<div class="col-6 mb-3">
									<a class="accordion-btn-admin green-background-btn"
										href="encounter-form.html" role="button">Encounter</a>
								</div>

								<div class="col-6 mb-3 accordion-btns">
									<a class="accordion-btn-admin dark-green-background-btn"
										href="view-notes.html" role="button">Doctors Note</a>
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
									<input type="text" disabled class="form-control input-1 agreement-inp agreement-phone"
										id="floatingInput-2" placeholder="Phone Number"
										autocomplete="off"> <label for="floatingInput-2"
										class="agreement-label">Phone Number</label>
								</div>
							</div>

							<div class="col-12">
								<div class="form-floating mb-3 inp">
									<input type="email" disabled class="form-control input-1 agreement-inp agreement-email"
										id="floatingInput-2" placeholder="Email" autocomplete="off">
									<label for="floatingInput-2" class="agreement-label">Email</label>
								</div>
								<input type="text" hidden id="send-agreement-req-id">
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
				<form id="sendLinkForm">
					<div class="modal-body">
						<div>

							<div class="col-12 mt-3">
								<div class="form-floating mb-3 inp">
									<input type="text" name="firstName"
										class="form-control input-1 agreement-inp"
										id="floatingInput-2" placeholder="First Name"
										autocomplete="off"> <label for="floatingInput-2"
										class="agreement-label">First Name</label>
								</div>
							</div>

							<div class="col-12 mt-3">
								<div class="form-floating mb-3 inp">
									<input type="text" name="lastName"
										class="form-control input-1 agreement-inp"
										id="floatingInput-2" placeholder="Last Name"
										autocomplete="off"> <label for="floatingInput-2"
										class="agreement-label">Last Name</label>
								</div>
							</div>

							<div class="col-12 col-md-6 ">
								<!--Phone Number col-->
								<div class="form-floating inp send-link-pop-up-only">
									<input type="tel" name="phoneNumber"
										class="form-control phoneflags phonecolheight" id="phone" />
								</div>
							</div>

							<div class="col-12">
								<div class="form-floating mb-3 inp">
									<input type="email" name="email"
										class="form-control input-1 agreement-inp"
										id="floatingInput-2" placeholder="Email" autocomplete="off">
									<label for="floatingInput-2" class="agreement-label">Email</label>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="send-btn">Send</button>
							<button type="reset" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

						</div>
				</form>
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

	<div class="modal fade" id="clear-case" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content modal-content-clear-case">
				<div class="modal-body family-pop">
					<img src="./SRS Screen Shorts/exclamation.png" alt=""
						class="set-img">
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

	<!-- clear-case -->


	<div class="modal fade" id="accept-case" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content agreement-scale">
				<form id="acceptCaseForm">
					<div class="modal-body modal-body-accept-case">
						<div>
							<h3 class="text-center mb-3">Accept Case</h3>

							<ul style="list-style-type: none;"
								class="text-center ul-padding-remove">
								<li class="mb-1">Are you sure you want to Accept this
									request?</li>
								<li>Once you accept this request then you are not able to
									Decline this request.</li>
							</ul>
						</div>
						<input type="text" hidden id="reqId-accept-case">
						<div
							class="modal-footer accpet-case-btn-flex grow-accpet-case-btn">
							<button type="submit" class="send-btn " data-bs-dismiss="modal">Accept</button>
							<button type="reset" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- accept-case -->

	<div class="modal fade" id="transfer-case" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel-1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel-1">Transfer
						Request</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="transferCaseForm">
					<div class="modal-body">
						<div class="font-clr-light">This request will be transferred
							to admin.</div>
						<br>
						
						<input type="text" hidden id="reqId-transfer-case">

						<textarea name="message" class="form-control transfer-reason" id="" cols="10"
							rows="5" placeholder="Description"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="send-btn" data-bs-dismiss="modal">Send</button>
						<button type="assign" class="cancel-btn" data-bs-dismiss="modal">Cancel</button>

					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- transfer case to admin  -->

	<div class="modal fade" id="house-call" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header modal-title-background">
					<h2 class="modal-title fs-5" id="staticBackdropLabel">Select
						Type Of Care</h2>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="request-type-pop-flex">
						<button class="me-type-btn" id="Housecall"
							onclick="changeCss(this)">House Call</button>
						<button class="else-type-btn" id="Consult"
							onclick="changeCss(this)">Consult</button>
					</div>

					<div class="continue-cancel-flex">
						<button type="button" class="me-type-btn">Save</button>
						<button type="button" class="else-type-btn"
							data-bs-dismiss="modal">Cancel</button>
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

	<!-- house-call-status -->


	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<script src="<c:url value='/resources/js/provider-dashboard.js' />"></script>
	<script src="<c:url value='/resources/js/darktheme.js' />"></script>

	<script type="text/javascript">
		const phoneInputField = document.querySelector("#phone");
		const phoneInput = window
				.intlTelInput(
						phoneInputField,
						{
							utilsScript : "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
						});

		function changeCss(element) {
			console.log(element.id)
			var x = document.getElementById("Housecall");
			var y = document.getElementById("Consult");
			var z = document.getElementById("housecall-consult-text");

			if (element.id == "Housecall") {
				x.style.backgroundColor = "#01bce9";
				x.style.color = "white";

				y.style.color = "#01bce9";
				y.style.backgroundColor = "white";

				z.innerHTML = element.id;

			}

			else {
				y.style.backgroundColor = "#01bce9";
				y.style.color = "white";

				x.style.color = "#01bce9";
				x.style.backgroundColor = "white";

				z.innerHTML = element.id;

			}
		}
	</script>
	<script src="<c:url value='/resources/js/loader.js' />"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>