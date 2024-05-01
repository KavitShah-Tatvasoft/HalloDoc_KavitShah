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
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
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
<link rel="stylesheet" href="<c:url value='/resources/css/account-access.css' />">
<!-- <script src="./js/admin-dashboard.js"></script> -->
<title>Account Access</title>
</head>
<body onload="getRoles()">
	<div
		class="container-fluid footer-container main-container relative-pos-class">
		<div class="main-container-top-text">Account Access</div>

		<div class="row">
			<a href="create-role-all.html">
				<div class="col-12 access-btn-flex">
					<a href="createRoleAccess" class="access-btn access-btn-add-flex">
						<span class="new-req-text">Create Access</span><img
							src="<c:url value='/resources/images/plus-lg.svg' />"
							class="plus-img-create-access" alt="">
					</a>
				</div>
			</a>
		</div>

		<div class="row accordion-div">



			<div class="clone-accordion-card d-none">
				<hr>
				<div class="accordion-div-rows-flex">
					<div class="accordion-body-row-flex">
						<span class="role-text-accordion">Name:</span> <span
							class="role-accordion role-td">KavitShah</span>
					</div>

					<div class="accordion-body-row-flex">
						<span class="role-text-accordion">Type:</span> <span
							class="role-accordion role-type-td">Valo Admin</span>
					</div>

					<div class="accordion-body-row-flex">
						<button class="actions-access-btn shrink">Edit</button>
						<button class="actions-access-btn shrink">Delete</button>
					</div>
				</div>

			</div>
			
			<div class="col-12 accordion-body-empty">
			
			</div>

			<div class="col-12">
				<hr>
			</div>
		</div>

		<table class="table align-middle mt-2">
			<thead class="table-active  align-middle">
				<tr>
					<th scope="col" class="thead width-col1 extra-padding-td">Name</th>
					<th scope="col" class="thead width-col2">Account Type</th>
					<th scope="col" class="thead width-col3 text-center">Action</th>
				</tr>
			</thead>
			
			<tr class="clone-tr-class d-none">
					<td class="extra-padding-td role-td">Derek Shah</td>
					<td class="role-type-td">Admin</td>
					<td class="actions-flex-access">
						<a type="button" href="" class="actions-access-btn edit-btn-td">Edit</a>
						<button  class="actions-access-btn del delete-btn-td">Delete</button>
					</td>
				</tr>
			
			<tbody class="empty-tbody-class">
			
				

			</tbody>
		</table>

	</div>

	<footer>
		<div class="footer-flex">
			<a href="#">Terms of Condition</a><span>|</span><a href="#">Privacy
				Policy</a>
		</div>
	</footer>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/account-access.js' />"></script>
	<script type="text/javascript">
		
		$(".common-link-class").removeClass("active")
		$(".access-link-class").addClass("active")
	
	</script>
</body>
</html>