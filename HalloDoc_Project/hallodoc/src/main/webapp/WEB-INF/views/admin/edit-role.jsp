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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/create-role-all.css' />">
<!--     <script src="./js/admin-dashboard.js"></script> -->
<title>Create Role</title>
</head>
</head>
<body onload="getSelectedMenu()">
	<div
		class="container-fluid footer-container patient-form shadow p-3 bg-white rounded relative-position extra-margin">
		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="submit-info-txt">Edit Role</div>

		<div class="row">

			<div class="col-12 mb-3">
				<span class="patient-text">Details</span>
			</div>

			<div class="col-12 col-md-6">
				<div class="form-floating mb-3 inp">
					<input type="text" class="form-control input-2 role-name-class"
						disabled value="${editDetails.roleName }" id="floatingInput-2"
						placeholder="Role Name" autocomplete="off"> <label
						for="floatingInput-2">Role Name</label>
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!--Mobile Type col-->
				<div class="form-floating mb-3 inp">
					<select name="roleType" id="floatingInput-5" disabled
						onchange="getMenus()"
						class="role-type-class form-control form-select input-2">

						<c:if test="${editDetails.roleType == 1}">
							<option value="1" selected>Admin</option>
							<option value="2">Provider</option>

						</c:if>

						<c:if test="${editDetails.roleType == 2}">
							<option value="1">Admin</option>
							<option value="2" selected>Provider</option>

						</c:if>

					</select> <label for="floatingInput-5">Role Type</label>
				</div>
			</div>



			<div class="col-12">
				<div class="all-create-role-checkbox-flex empty-flex-class-menus">

					<c:forEach items="${editDetails.allRoleTypeMenuList }" var="menu">

						<div class="single-role-checkbox-flex">
							<input type="checkbox" value="${menu.menuId }"
								class="role-type-checkbox menu-checkbox-class"
								<c:forEach items="${editDetails.menuList}" var="selmenu">
										<c:if test = "${menu.menuId == selmenu.menuId}">checked</c:if>
									</c:forEach>
								onchange="getSelectedMenu()"> <span
								class="role-type name-menu-class">${menu.name }</span>
						</div>

					</c:forEach>


				</div>
			</div>
		</div>

		<form action = "../updateRole" method="post">
			<input type="text" name="selectedMenu" hidden class="selected-menu-class-list role-menu-input"> 
			<input type="text" name="roleId" value="${roleId}" hidden>
			<div class="bottom-btns-save-cancel-flex">
				<button role="submit" class="save-btn-role">Save</button>
				<button role="reset" class="cancel-btn-role">
					Cancel</a>
			</div>

		</form>
	</div>

	<footer>
		<div class="footer-flex">
			<a href="#">Terms of Condition</a><span>|</span><a href="#">Privacy
				Policy</a>
		</div>
	</footer>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/edit-role.js' />"></script>

</body>
</html>