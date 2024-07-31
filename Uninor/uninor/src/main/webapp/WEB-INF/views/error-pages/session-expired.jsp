<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 10-07-2024
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Session Expired</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/toaster.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/session-expired.css" />">
    <!-- Icon -->
    <link rel="icon" href="<c:url value="/resources/images/white-logo.png" />" type="image/icon type">

</head>
<body>
<nav id="common-nav-1" class="d-flex justify-content-start align-items-center container-fluid">
    <div class="d-flex justify-content-between align-items-center" style="color: white; font-size: 24px;">
        <div class="logo-text-flex"><img src="/uninor/resources/images/white-logo-removebg-preview.png" class="nav-logo"> <span>Uninor</span></div>
    </div>
</nav>
<div class="waiting-container">
    <div class="waiting-animation">
        <!-- Replace with appropriate SVG or animated icon for waiting -->
        <img src="<c:url value="/resources/images/deadline.png" />" class="verification-img">

    </div>
    <p class="description-text text-center">Session Expired</p>
    <p>It seems like you have timed out of a session. Please login again to continue the service.</p>
    <div class="button-center-flex">
        <div class="button-center">
            <a href="${pageContext.request.contextPath}/login" type="button" class="login-btn-session">User Login Page</a>
        </div>
        <div class="button-center">
            <a href="${pageContext.request.contextPath}/admin-login" type="button" class="login-btn-session">Admin Login Page</a>
        </div>
    </div>

</div>
</body>
</html>
