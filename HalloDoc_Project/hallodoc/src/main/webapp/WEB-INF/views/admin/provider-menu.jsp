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
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/provider-menu.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/pop-ups.css' />">
<title>Provider Menu</title>
</head>
<body onload="showProviderMenuData()">
	<div
		class="container-fluid footer-container main-container relative-pos-class">
		<div class="main-container-top-text">Provider Information</div>

		<div class="row">
			<div class="col-12 flex-create-account-search">

				<div class="select-parent">
					<select name="regions" id="" onchange="showProviderMenuData()" class="form-select select-physician select-physician-region-dropdown">
						<option value="0">All</option>
						
						<c:forEach items="${regionList}" var="region">
						
						<option value="${region.regionId }">${region.name }</option>
						
						</c:forEach>
						
					</select> <img src="<c:url value='/resources/images/search.svg' />"
						class="select-search-img" alt="">
				</div>

				<a class="account-btn" href="create-provider-account.html"> <span
					class="new-req-text">Create Provider Account</span>
				</a>
			</div>
		</div>

		<table class="table align-middle mt-2">
			<thead class="table-active  align-middle">
				<tr>
					<th scope="col"
						class="thead width-col1 extra-padding-td text-center">Stop
						Notification</th>
					<th scope="col" class="thead width-col2">Provider Name<img
						src="<c:url value='/resources/images/arrow-up.svg' />" class="add-padding"
						alt="arrow-down"></th>
					<th scope="col" class="thead width-col3">Role</th>
					<th scope="col" class="thead width-col4">On Call Status</th>
					<th scope="col" class="thead width-col5">Status</th>
					<th scope="col" class="thead width-col6 text-center">Action</th>
				</tr>
			</thead>
			
			<tr class="tr-clone d-none">
					<td class="extra-padding-td stop-checkbox-flex"><input class="noti-checkbox"
						type="checkbox" class="stop-checkbox" name="" id=""></td>
					<td class="name-td">Derek Shah</td>
					<td class="role-td">Main Admin</td>
					<td class="on-call-status-td">Available</td>
					<td class="active-td">Active</td>
					<td class="contact-flex">
						<button class="contact-btn contact-btn-menu" data-bs-toggle="modal" data-bs-target="#staticBackdrop"
							>
							<span>Contact</span>
						</button> <a href="edit-physician-account.html" class="edit-btn-menu">
							<div class="contact-btn edit-btn">
								<span>Edit</span>
							</div>
					</a>
					</td>
				</tr>
				
				
			<tbody class="empty-tbody-provider-menu">
				
			</tbody>
		</table>
		
		<div class="accordion-item clone-accordion d-none" >
					<h2 class="accordion-header" id="panelsStayOpen-headingOne">
						<button class="accordion-button accordian-btn-flex accordion-target" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
							aria-controls="panelsStayOpen-collapseOne">
							<div class="extra-padding-td stop-checkbox-flex">
								<input type="checkbox" class="stop-checkbox accordion-checkbox-menu" name="" id=""/>
								<span class="accordion-row-name accordion-row-name-menu">Derek Shah</span>
							</div>
							<div class="oncall-status-col">
								<span class="oncall-status-text">On Call Status : </span><strong
									class="status-text status-text-menu">Available</strong>
							</div>
						</button>
					</h2>
					<div id="panelsStayOpen-collapseOne"
						class="accordion-collapse collapse accordion-main-id"
						aria-labelledby="panelsStayOpen-headingOne">
						<div class="accordion-body accordion-rows-flex">
							<div class="accordion-body-row-flex">
								<div class="img-border">
									<img src="<c:url value='/resources/images/person.svg' />"
										class="accordion-row-img" alt="">
								</div>
								<span class="role-text-accordion">Role:</span> <span
									class="role-accordion role-accordion-menu">Master Admin</span>
							</div>

							<div class="accordion-body-row-flex">
								<div class="img-border">
									<img src="<c:url value='/resources/images/tick.svg' />"
										class="accordion-row-img" alt="">
								</div>
								<span class="role-text-accordion">Status:</span> <span
									class="role-accordion status-accordion-menu">Available</span>
							</div>

							<div class="accordion-bottom-btn-flex">
								<button data-bs-toggle="modal" data-bs-target="#staticBackdrop" 
									class="contact-btn shrink-btns contact-btn-accordion">
									<span>Contact</span>
								</button>

								<a href="edit-physician-account.html " class="edit-link-accordion">
									<div class="accordion-bottom-btn edit-btn">Edit</div>
								</a>
							</div>


						</div>
					</div>
				</div>
		

		<div class="row accordion-top">
			<div class="accordion empty-accordion" id="accordionPanelsStayOpenExample">
							</div>
		</div>


	</div>


	<!-- -----------pop up modal----------- -->

	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Contact
						Your Provider</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="communication-method-form" method="post">
					<div class="modal-body">
						<div class="font-clr-light">Choose communication to send
							message</div>
						<br> <input type="text" hidden class="hiddden-phy-id">
						<div class="radio-flex">
							<input type="radio" class="inp-radio-margin" name="method-communication"
								id="sms" value="SMS"> <label for="sms">SMS</label><br>
						</div>
						<div class="radio-flex">
							<input type="radio" class="inp-radio-margin" name="method-communication"
								id="email" value="EMAIL"> <label for="email">Email</label><br>
						</div>
						<div class="radio-flex">
							<input type="radio" class="inp-radio-margin" name="method-communication"
								id="both" value="BOTH"> <label for="both">Both</label><br>
						</div>
						<textarea name="message" class="form-control communication-description" id="" cols="10"
							rows="5" placeholder="Message"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="send-btn">Send</button>
						<button type="reset" class="cancel-btn close-pop-up" data-bs-dismiss="modal">Cancel</button>

					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/provider-menu.js' />"></script>
</body>
</html>
<%@include file="footer-black.jsp"%>