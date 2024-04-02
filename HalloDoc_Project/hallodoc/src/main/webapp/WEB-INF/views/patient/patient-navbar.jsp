<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<div class="container-fluid shadow p-2 bg-white rounded">
		<div class="nav nav-flex">
			<div class="nav-left">
				<div class="hamburger-menu">
					<button type="button" class="menu-btn" data-bs-toggle="offcanvas"
						data-bs-target="#offcanvasExample"
						aria-controls="offcanvasExample">
						<img src="<c:url value="/resources/images/list.svg" />" alt="menu"
							class="menu-img">
					</button>

					<div class="offcanvas offcanvas-start" tabindex="-1"
						id="offcanvasExample" aria-labelledby="offcanvasExampleLabel"
						data-transition="push" aria-modal="true" role="dialog">
						<div class="offcanvas-body">

							<div class="sidebar-profile-text">
								<span> Welcome, </span> <span> <strong>${aspUser.user.firstName} ${aspUser.user.lastName} </strong>
								</span>
							</div>
							<ul class="nav sidebar-list-flex">
								<li class="nav-item"><a class="nav-link"
									aria-current="page" href="<c:url value='/patientDashboard' />">Dashboard</a>
								</li>
								<li class="nav-item"><a class="nav-link"
									href="<c:url value='/patientProfile' />">Profile</a></li>
							</ul>
						</div>
					</div>
				</div>

				<span class="ms-2"> <img
					src="<c:url value="/resources/images/logo-halloDoc.png" />" alt="HelloDoc Logo"
					class="halloDoc-img">
				</span>
			</div>

			<div class="nav-right">
				<span class="mx-1 profile-name"> Welcome </span> <span
					class="mx-1 profile-name"> <strong>${aspUser.user.firstName} ${aspUser.user.lastName}</strong>
				</span>
				<div>
					<a href="<c:url value='/userLogout' />">
						<div class="logout-btn">
							<img src="<c:url value="/resources/images/box-arrow-right.svg" />"
								class="logout-img" alt="Logout" /><span class="logout-btn-text">Logout</span>
						</div>
					</a>
				</div>

				<div>
					<button type="button" onclick="changeThemeDarkBright(this)"
						class="theme-btn" id="moon-btn"></button>
					<label for="moon-btn"> <img
						src="<c:url value="/resources/images/moon-blue.svg" />" alt="moon" class="moon"
						id="moon-id">
					</label>

					<button type="button" onclick="changeThemeDarkBright(this)"
						class="theme-btn" id="sun-btn"></button>
					<label for="sun-btn"> <img
						src="<c:url value="/resources/images/sun-blue.svg" />" alt="sun" class="sun"
						id="sun-id">
					</label>
				</div>

			</div>
		</div>
		<hr>

<!-- 		Main Nav Bar -->

		<ul class="nav nav-underline cm-margin ms-3 inner-nav">
			<li class="nav-item"><a class="nav-link active" aria-current="page"
				href="<c:url value='/patientDashboard' />">Dashboard</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/patientProfile' />">Profile</a></li>
				
				
		</ul>
	</div>


</body>
</html>