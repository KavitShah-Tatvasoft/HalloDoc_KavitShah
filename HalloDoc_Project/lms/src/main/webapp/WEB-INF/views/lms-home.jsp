<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/home.css" />">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous" />
<title>Library Management System</title>
</head>
<body>
	<div class="w-100 top-strip">
		<h4>Library Management System</h4>
	</div>

	<div class="container-fluid lms-form mt-3">
		<div class="top-row-flex">
			<input class="form-control search-top" type="text"
				placeholder="Search" aria-label="default input example">

			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#staticBackdrop">Add Book</button>

		</div>

		<div class="container-fluid">
			<table class="table mt-4">
			<thead>
				<tr>
					<th scope="col">Book Id</th>
					<th scope="col">Book Name</th>
					<th scope="col">Author</th>
					<th scope="col">Borrower Name</th>
					<th scope="col">Date of Issue</th>
					<th scope="col">City</th>
					<th scope="col">Genere</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody class="tbody-empty">
				<c:forEach items="${initailData }" var="data">
				<tr>
					<td>${data.bookId }</td>
					<td>${data.bookName }</td>
					<td>${data.author }</td>
					<td>${data.borrowerName }</td>
					<td>${data.dateOfIssuance }</td>
					<td>${data.city }</td>
					<td>${data.genere }</td>
					<td>
						<button type="button" onclick="updateRecord(${data.bookRecordId})" data-bs-toggle="modal"
				data-bs-target="#update">Edit</button>
						<button type="button">Delete</button>
					</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>



	</div>





	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header modal-book-form">
					<h1 class="modal-title fs-5 mt-4" id="staticBackdropLabel">
						Book Form</h1>
				</div>
				<div class="modal-body">
					<form method="post" id="lms-form">
						<div class="row">

							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 book-name"
										id="floatingInput-1" placeholder="First Name"
										autocomplete="off" onblur="checkBook()"> <label
										for="floatingInput-1">Book Name</label>
								</div>
								<span class="book-name-error"></span>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 author"
										id="floatingInput-2" placeholder="First Name"
										autocomplete="off"> <label for="floatingInput-2">Author</label>
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 borrower-name"
										onblur="checkBorrower()" id="floatingInput-3"
										placeholder="First Name" autocomplete="off"> <label
										for="floatingInput-3">Borrower Name</label>
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="date"
										class="form-control input-1 date-of-issuance"
										id="floatingInput-4" placeholder="First Name"
										autocomplete="off"> <label for="floatingInput-4">Date
										of Issue</label>
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<select class="form-select form-select-lg mb-3 genere-book">
										<option value="none" hidden>Genere</option>
										<option value="Science Fiction">Science Fiction</option>
										<option value="Novel">Novel</option>
										<option value="History">History</option>
									</select>
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 borrower-city"
										id="floatingInput-5" placeholder="First Name"
										autocomplete="off"> <label for="floatingInput-5">City</label>
								</div>
							</div>

							<input type="text" hidden class="hidden-borrower-id">
							<div class="btm-btns-flex">
								<button type="reset" data-bs-dismiss="modal" class="cancel-btn">Cancel</button>
								<button type="submit" class="save-btn">Save</button>
							</div>

						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	
	<div class="modal fade" id="update" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header modal-book-form">
					<h1 class="modal-title fs-5 mt-4" id="staticBackdropLabel">
						Book Form</h1>
				</div>
				<div class="modal-body">
					<form method="post" id="lms-form-1">
						<div class="row">

							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 book-name-1"
										id="floatingInput-1" placeholder="First Name"
										autocomplete="off"> <label
										for="floatingInput-1">Book Name</label>
								</div>
								<span class="book-name-error"></span>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 author-1"
										id="floatingInput-2" placeholder="First Name"
										autocomplete="off"> <label for="floatingInput-2">Author</label>
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 borrower-name-1"
										 id="floatingInput-3"
										placeholder="First Name" autocomplete="off"> <label
										for="floatingInput-3">Borrower Name</label>
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="date"
										class="form-control input-1 date-of-issuance-1"
										id="floatingInput-4" placeholder="First Name"
										autocomplete="off"> <label for="floatingInput-4">Date
										of Issue</label>
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<select class="form-select form-select-lg mb-3 genere-book-1">
										<option value="none" hidden>Genere</option>
										<option value="Science Fiction">Science Fiction</option>
										<option value="Novel">Novel</option>
										<option value="History">History</option>
									</select>
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="form-floating mb-3 inp">
									<input type="text" class="form-control input-1 borrower-city-1"
										id="floatingInput-5" placeholder="First Name"
										autocomplete="off"> <label for="floatingInput-5">City</label>
								</div>
							</div>

							<input type="text" hidden class="hidden-borrower-id-1">
							<div class="btm-btns-flex">
								<button type="reset" data-bs-dismiss="modal" class="cancel-btn">Cancel</button>
								<button type="submit" class="save-btn">Save</button>
							</div>

						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

	<script src="<c:url value="/resources/js/lms-home.js" />"></script>
</body>
</html>

<style>

</style>