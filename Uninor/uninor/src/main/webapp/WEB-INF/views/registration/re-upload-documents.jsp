<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 24-07-2024
  Time: 14:37
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
    <title>Expiration Error</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/registration.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/reupload-doc.css' />">
    <link rel="stylesheet" href="<c:url value="/resources/css/toaster.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/session-expired.css" />">
    <!-- Icon -->
    <link rel="icon" href="<c:url value="/resources/images/white-logo.png" />" type="image/icon type">

</head>
<body onload="getReuploadFields()">
<nav id="common-nav-1" class="d-flex justify-content-start align-items-center container-fluid">
    <div class="d-flex justify-content-between align-items-center" style="color: white; font-size: 24px;">
        <div class="logo-text-flex"><img src="/uninor/resources/images/white-logo-removebg-preview.png" class="nav-logo"> <span>Uninor</span></div>
    </div>
</nav>
<div class="waiting-container">
    <div class="text-center reupload-heading">Reupload Documents</div>
    <form id="resubmit-doc-form" method="post">
        <div class="row">

            <div class="col-12">
                <div class="floating-label-group mt-2">
                    <input type="date" id="dob" class="form-control taskName dob-inner-label" autocomplete="off"
                           name="dob" required/>
                    <label class="floating-label place-holder dob-label">Date Of Birth</label>
                </div>
            </div>

            <div class="col-12">
                <div class="floating-label-group mt-2">
                    <input type="text" id="panNumber" class="form-control common-pan-class taskName" autocomplete="off"
                           name="panNumber" required/>
                    <label class="floating-label place-holder common-pan-class">PAN Number</label>
                </div>
            </div>

            <div class="col-12 mt-1 common-pan-class upload-view-flex">
                <div class="input-group">
                    <input id="pan-card-name-id"  type="text"
                           class="form-control p-2 verification-files custom-upload"
                           placeholder="Upload PAN Card" disabled/>
                    <button class="file-upload-btn btn-color-for-upload" type="button" id="upload">
                        <img src="<c:url value='/resources/images/upload-white.svg' />" class="upload-image-file"
                             alt="">
                        <span class="for-remove-upload ">Upload</span></button>
                    <input id="panCardUploadedFile" name="panCardUploadedFile" multiple class="file-input-hover-effect file-upload-class"
                           type="file"
                           style="position: absolute; right: -8px;top: 0.5rem; opacity: 0;">
                </div>

                <button class="verification-files main-container-view-btn pan-card-view d-none">
                    <div class="view-btn-flex">
                        <img src="<c:url value='/resources/images/view-white.svg' />" class="upload-image-file" alt="">
                        <span class="for-remove-upload ">View</span>
                    </div>
                </button>

            </div>
            <span id="pan-error" class="error-class"></span>

            <div class="col-12 mt-2">
                <div class="floating-label-group mt-2">
                    <input type="text" id="aadharCardNumber" class="form-control common-aadhar-class taskName" autocomplete="off"
                           name="aadharCardNumber" required/>
                    <label class="floating-label place-holder common-aadhar-class">Aadhar Card Number</label>
                </div>
            </div>

            <input type="hidden" name="token" id="token" value="${token}">

            <div class="col-12 mt-1 upload-view-flex common-aadhar-class">
                <div class="input-group">
                    <input id="aadhar-card-name-id" type="text"
                           class="form-control p-2 verification-files custom-upload"
                           placeholder="Upload Aadhar Card" disabled>
                    <button class="file-upload-btn btn-color-for-upload" type="button" id="upload">
                        <img src="<c:url value='/resources/images/upload-white.svg' />" class="upload-image-file"
                             alt="">
                        <span class="for-remove-upload ">Upload</span></button>
                    <input id="aadharCardUploadedFile"  name="aadharCardUploadedFile" multiple
                           class="file-input-hover-effect file-upload-class" type="file"
                           style="position: absolute; right: -8px;top: 0.5rem; opacity: 0;">
                </div>

                <button class="verification-files main-container-view-btn aadhar-card-view d-none">
                    <div class="view-btn-flex">
                        <img src="<c:url value='/resources/images/view-white.svg' />" class="upload-image-file" alt="">
                        <span class="for-remove-upload ">View</span>
                    </div>
                </button>

            </div>
            <span id="aadhar-error" class="error-class"></span>

        </div>
        <div class="btn-center-reupload">
            <button class="reuplaod-submit" type="submit">Submit</button>
            <button class="reuplaod-submit" type="submit">Reset</button>
        </div>
    </form>
    <%@include file="toaster-common-file.jsp" %>
</div>


</body>

<script src="<c:url value='/resources/js/global-js.js' />"></script>
<script src="<c:url value='/resources/js/main.js'/>"></script>
<script src="<c:url value='/resources/js/toasters-1.js' />"></script>
<script src="<c:url value='/resources/js/loader.js' />"></script>
<script src="<c:url value='/resources/js/re-upload-doc.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.js"></script>
</html>

