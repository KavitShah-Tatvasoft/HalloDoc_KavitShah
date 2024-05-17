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
<link rel="stylesheet"
	href="<c:url value='/resources/css/requested-shifts.css' />">
<!-- <script src="./js/admin-dashboard.js"></script> -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<title>Profession Menu</title>
</head>
<body onload="filterReviewShifts(true)">
	<div
		class="container-fluid footer-container patient-form shadow bg-white rounded relative-position extra-margin">

		<div class="submit-info-txt">Requested Shifts</div>

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="row m-2">
			<div class="col-12 flex-create-account-search">

				<div class="main-shifts-btns-flex mt-3 mb-3">

					<div class="select-parent">
						<select name="physician" onchange="filterReviewShifts(true)" id=""
							class="form-select select-physician region-review-shift">
							<option value="0">All Regions</option>
							<c:forEach items="${regionList }" var="region">
								<option value="${region.regionId }">${region.name }</option>
							</c:forEach>
						</select> <img src="<c:url value='/resources/images/search.svg' />"
							class="select-search-img" alt="">
					</div>

					<div class="shifts-btns-flex">
						<a href="${pageContext.request.contextPath}/admin/scheduling-month" role="button"
							class="current-month-btn">View Current Month Shift</a> <a onclick="approveSelected()"
							href="#" role="button" class="approved-shift-btn">Approved
							Selected</a> <a href="#" role="button" class="delete-shift-btn" onclick="deleteSelected()">Delete
							Selected</a>
					</div>
				</div>
			</div>
			
			<div class="mobile-cards clone-accordion-card d-none">
					<hr>
					<div class="checkbox-name-flex">
						<input type="checkbox" class="input-checkbox checkbox-review"> <span
							class="doc-name physician-name-review">Dr. Shah</span>
					</div>

					<div class="date-time-region-flex">
						<span class="shift-date-review">Dec 09, 2023</span> <span
							class="shift-time-review">8.15 PM - 9.15 PM</span> <span
							class="shift-region-review">Newyork</span>
					</div>
					<hr>
				</div>

			<div class="card-div empty-accordion-body">
				
			</div>

		</div>

		<div class="table-responsive">
			<table class="table align-middle mt-2">
				<thead class="table-active align-middle">
					<tr class="align-middle">
						<th scope="col" class="thead width-col1 extra-padding-td"><input
							type="checkbox" name="" id="" class="input-checkbox selectall"></th>
						<th scope="col" class="thead width-col2">
							<div class="staff-arrow-flex">
								<span class="staff-text">Staff</span> <img
									src="<c:url value='/resources/images/arrow-up.svg' />" alt="">
							</div>
						</th>
						<th scope="col" class="thead width-col3">Day</th>
						<th scope="col" class="thead width-col4">Time</th>
						<th scope="col" class="thead width-col5">Region</th>
					</tr>
				</thead>

				<tr class="clone-tr-review d-none">
					<td class="thead width-col1 extra-padding-td"><input
						type="checkbox" name="" id=""
						class="input-checkbox justone checkbox-review"></td>
					<td class="physician-name-review">Dr Shah</td>
					<td class="shift-date-review">Dec 09, 2023</td>
					<td class="shift-time-review">8.15 PM - 9.15 PM</td>
					<td class="shift-region-review">Newyork</td>
				</tr>

				<tbody class="tbody-empty">

				</tbody>
			</table>

		</div>
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

	<footer>
		<div class="footer-flex">
			<a href="#">Terms of Condition</a><span>|</span><a href="#">Privacy
				Policy</a>
		</div>
	</footer>

	<script>
		$('.selectall').click(function() {
			if ($(this).is(':checked')) {
				$('input:checkbox').prop('checked', true);
			} else {
				$('input:checkbox').prop('checked', false);
			}
		});

		$("input[type='checkbox'].justone").change(function() {
			debugger
			var a = $("input[type='checkbox'].justone");
			if (a.length == a.filter(":checked").length) {
				$('.selectall').prop('checked', true);
			} else {
				$('.selectall').prop('checked', false);
			}
		});
	</script>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/review-shifts.js' />"></script>
</body>
</html>