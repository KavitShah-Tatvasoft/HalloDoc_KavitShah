<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid shadow p-2 bg-white rounded">
		<div class="nav nav-flex">
			<div class="nav-left">
				<div class="hamburger-menu">
					<button type="button" class="menu-btn" data-bs-toggle="offcanvas"
						data-bs-target="#offcanvasExample"
						aria-controls="offcanvasExample">
						<img src="./SRS Screen Shorts/list.svg" alt="menu"
							class="menu-img">
					</button>

					<div class="offcanvas offcanvas-start" tabindex="-1"
						id="offcanvasExample" aria-labelledby="offcanvasExampleLabel"
						data-transition="push" aria-modal="true" role="dialog">
						<div class="offcanvas-body">

							<div class="sidebar-profile-text">
								<span> Welcome, </span> <span> <strong>Kavit
										Shah</strong>
								</span>
							</div>
							<ul class="nav sidebar-list-flex">
								<li class="nav-item"><a class="nav-link"
									aria-current="page" href="patient-dashboard.html">Dashboard</a>
								</li>
								<li class="nav-item"><a class="nav-link"
									href="patient-profile.html">Profile</a></li>
							</ul>
						</div>
					</div>
				</div>

				<span class="ms-2"> <img
					src="./SRS Screen Shorts/logo-halloDoc.png" alt="HelloDoc Logo"
					class="halloDoc-img">
				</span>
			</div>

			<div class="nav-right">
				<span class="mx-1 profile-name"> Welcome </span> <span
					class="mx-1 profile-name"> <strong>Kavit Shah</strong>
				</span>
				<div>
					<a href="patient-login.html">
						<div class="logout-btn">
							<img src="./SRS Screen Shorts/box-arrow-right.svg"
								class="logout-img" alt="Logout" /><span class="logout-btn-text">Logout</span>
						</div>
					</a>
				</div>

				<div>
					<button type="button" onclick="changeThemeDarkBright(this)"
						class="theme-btn" id="moon-btn"></button>
					<label for="moon-btn"> <img
						src="./SRS Screen Shorts/moon-blue.svg" alt="moon" class="moon"
						id="moon-id">
					</label>

					<button type="button" onclick="changeThemeDarkBright(this)"
						class="theme-btn" id="sun-btn"></button>
					<label for="sun-btn"> <img
						src="./SRS Screen Shorts/sun-blue.svg" alt="sun" class="sun"
						id="sun-id">
					</label>
				</div>

			</div>
		</div>
		<hr>

<!-- 		Main Nav Bar -->

		<ul class="nav nav-underline cm-margin ms-3 inner-nav">
			<li class="nav-item"><a class="nav-link" aria-current="page"
				href="patient-dashboard.html">Dashboard</a></li>
			<li class="nav-item"><a class="nav-link"
				href="patient-profile.html">Profile</a></li>
		</ul>
	</div>


</body>
</html>