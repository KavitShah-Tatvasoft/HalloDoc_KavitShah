<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="<c:url value="/resources/css/patient_submit_request.css" /> ">
<link rel="stylesheet" href="<c:url value="/resources/css/navbar.css" />">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet">
<title>Submit Request</title>
</head>
<body>
	 <div class="container-fluid shadow p-2 bg-white rounded">
        <div class="nav nav-flex">
            <div class="nav-left">
                <span class="ms-2">
                    <img src="<c:url value="/resources/images/logo-halloDoc.png" />" alt="HelloDoc Logo" class="halloDoc-img">
                </span>
            </div>

            <div class="nav-right">
                <div>
                    <button type="button"  class="theme-btn" id="moon-btn" onclick="changeThemeDarkBright(this)"></button>
                    <label for="moon-btn">
                        <img src="<c:url value="/resources/images/moon-blue.svg" />" alt="moon" class="moon" id="moon-id">
                    </label>

                    <button type="button"  class="theme-btn" id="sun-btn" onclick="changeThemeDarkBright(this)"></button>
                    <label for="sun-btn">
                        <img src="<c:url value="/resources/images/sun-blue.svg" />" alt="sun" class="sun"
                            id="sun-id">
                    </label>
             </div>

            </div>
        </div>
    </div>

    <div class="main-body">
        <div class="outer-flex-column relative-pos">
            <div class="iamtxt-div">
                <strong class="iamtxt">I am a...</strong>
            </div>

            <a type="button" onclick="history.go(-1)">
                <div class="back-btn-top absolute-pos">
                    <img src="<c:url value="/resources/images/chevron-left.svg" />" alt="left">
                    Back
                </div>
            </a>

            <a href="create-patient-request.html" class="flex-width patient-clr">
                PATIENT
            </a>

            <a href="FamilyFriend-request.html"class="flex-width family-clr">
                FAMILY/FRIEND
            </a>

            <a href="concierge-information-create-request.html"class="flex-width concierge-clr">
               CONCIERGE
            </a>

            <a href="business-information-create-request.html" class="flex-width business-clr">
                BUSINESS PARTNER
            </a>

        </div>
    </div>

    <footer>
        <a href="#">Terms of Condition</a> &nbsp;|&nbsp;<a href="#"> Privacy Policy </a>
    </footer>

    <script src="<c:url value="/resources/js/darktheme.js" />"></script>
</body>
</html>