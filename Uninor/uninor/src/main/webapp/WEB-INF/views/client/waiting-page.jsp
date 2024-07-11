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
    <title>Uninor</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/toaster.css" />">
    <!-- Icon -->
    <link rel="icon" href="<c:url value="/resources/images/white-logo.png" />" type="image/icon type">
    <style>

        * {
            font-family: "Roboto", sans-serif;
            font-weight: 500;
            font-style: normal;
        }

        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #f8f9fa;
            margin: 0;
            padding-top: 60px; / Adjusted for fixed navbar /
        overflow: hidden;
            font-family: 'Roboto', sans-serif;
        }
        nav {
            background-color: #3750eb;
            width: 100%;
            padding: 10px 0;
            position: fixed;
            top: 0;
            z-index: 1000;
        }
        .waiting-container {
            text-align: center;
            padding: 40px;
            border: 1px solid #dee2e6;
            border-radius: 12px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative;
            z-index: 2;
            max-width: 600px;
            margin: auto;
        }
        .waiting-container h1 {
            font-size: 2rem;
            margin-bottom: 20px;
        }
        .description-text {
            font-size: 1.25rem;
            margin-bottom: 30px;
        }
        .waiting-animation {
            width: 100px;
            margin: 20px auto;
        }
        .waiting-animation svg {
            width: 100%;
            height: auto;
            max-width: 100px;
        }

        .nav-logo {
            height: 55px;
            width: auto;
        }

        .verification-img{
            height: 100px;
            width: auto;
        }

        @media screen and (max-width: 768px){
            .description-text{
                font-size: 18px;
            }
        }
    </style>
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
        <img src="<c:url value="/resources/images/verification.png" />" class="verification-img">

    </div>
    <p class="description-text">Document verification under process. You can login once it is completed.</p>
</div>
</body>
</html>
