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
<link rel="stylesheet"
	href="<c:url value='/resources/css/profession-menu-vendor.css' />">

<title>Profession Menu</title>
</head>
<body onload="showTableData()">
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="submit-info-txt">Vendor(s)</div>

		<div class="row">
			<div class="col-12 flex-create-account-search">

				<div class="main-search-btns-flex">
					<div class="input-parent">
						<input type="text" name="" id="" onblur="showTableData()"
							class="form-control search-vendors search-vendor-filter"
							placeholder="Search Vendor"> <img
							src="<c:url value='/resources/images/search.svg' />"
							class="select-search-img" alt="">
					</div>

					<div class="select-parent">
						<select name="physician" id="" onchange="showTableData()"
							class="form-select select-physician select-physician-filter">
							<option value="0" selected>All</option>
							<c:forEach items="${healthProfessionalTypes }" var="healthType">
								<option value="${healthType.healthProfessionalId }">${healthType.professionName }</option>
							</c:forEach>
						</select> <img src="<c:url value='/resources/images/search.svg' />"
							class="select-search-img" alt="">
					</div>
				</div>

				<a href="addNewBusiness" role="button" class="add-business-btn"><img
					src="<c:url value='/resources/images/plus-lg.svg' />" alt=""><span
					class="add-business-text">Add Business</span></a>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table align-middle mt-2">
				<thead class="table-active  align-middle ">
					<tr>
						<th scope="col" class="thead width-col1 extra-padding-td">Profession</th>
						<th scope="col" class="thead width-col2">Business Name</th>
						<th scope="col" class="thead width-col3">Email</th>
						<th scope="col" class="thead width-col4">Fax Number</th>
						<th scope="col" class="thead width-col5">Phone Number</th>
						<th scope="col" class="thead width-col6">Business Contact</th>
						<th scope="col" class="thead width-col7 text-center">Action</th>
					</tr>
				</thead>

				<tr class="profession-tr d-none">
					<td class="extra-padding-td profession-type">Medicne</td>
					<td class="profession-name">Shah Agency</td>
					<td class="profession-email">kavitshah324@gmail.com</td>
					<td class="profession-fax">(123)456-7890</td>
					<td class="profession-mobile">(123)456-7890</td>
					<td class="profession-contact">Derek</td>
					<td class="contact-flex">
					
					<a href="" class="edit-button-table">
                            <div class="contact-btn edit-btn">
                                <span>Edit</span>
                            </div>
                     </a>
    
                        <a type="button"  class="contact-btn delete-button-table">
                            <span>Delete</span>
                        </a>
				
						
						</td>
				</tr>

				<tbody class="profession-tbody">

				</tbody>
			</table>
		</div>

		<div class="accordion-item clone-business-accordion d-none">
				<h2 class="accordion-header" id="panelsStayOpen-headingOne">
					<button class="accordion-button change-target-class" type="button"
						data-bs-toggle="collapse" 
						data-bs-target="#panelsStayOpen-collapseOne-1"
						aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
						<span class="accordion-row-name accordion-clone-name">Testing Business</span>
					</button>
				</h2>
				<div id="panelsStayOpen-collapseOne-1"
					class="accordion-collapse collapse change-id-class"
					aria-labelledby="panelsStayOpen-headingOne">
					<div class="accordion-body">
						<div class="accordion-rows-main-flex">
							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img
										src="<c:url value='/resources/images/envelope-blue.svg' />"
										alt="">
								</div>
								<div>
									<span class="email-label ">Email: </span> <span
										class="email-text accordion-clone-email">kavitshah324@gmail.com</span>
								</div>
							</div>

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img
										src="<c:url value='/resources/images/telephone-blue.svg' />"
										alt="">
								</div>
								<div>
									<span class="email-label">Fax: </span> <span class="email-text accordion-clone-fax">1234567890</span>
								</div>
							</div>

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img
										src="<c:url value='/resources/images/telephone-blue.svg' />"
										alt="">
								</div>
								<div>
									<span class="email-label">Phone Number: </span> <span
										class="email-text accordion-clone-phone">1234567890</span>
								</div>
							</div>

							<div class="accordion-row-inner-flex">
								<div class="image-blue-border">
									<img
										src="<c:url value='/resources/images/envelope-blue.svg' />"
										alt="">
								</div>
								<div>
									<span class="email-label ">Business Contact: </span> <span
										class="email-text accordion-clone-business-contact">Derek</span>
								</div>
							</div>

							<div class="accordion-btn-inner-flex">
								<a href="" role="button" class="bottom-accordion-row-btn edit-business-id">Edit</a>
								<a href="" role="button" class="bottom-accordion-row-btn delete-business-id">Delete</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		


		<div class="accordion mt-4 empty-accordion"  id="accordionPanelsStayOpenExample">
			
		</div>
	</div>



	</div>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/profession-menu.js' />"></script>
	<script src="<c:url value='/resources/js/provider-menu.js' />"></script>
		<script type="text/javascript">
		
		$(".common-link-class").removeClass("active")
		$(".partners-link-class").addClass("active")
	
	</script>
</body>
</html>
<%@include file="footer-black.jsp"%>