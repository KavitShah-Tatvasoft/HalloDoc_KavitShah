<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 30-05-2024
  Time: 09:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page isELIgnored="false" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <title>Registration</title>
    <%--    font link--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
          rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='/resources/css/registration.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/toaster-1.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/loader.css' />">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body onload="getClientData(), getNumberSuggestions()">
<div class="loader-container">
    <div class="loader"></div>
</div>
<div class="container-fluid main-container">
    <div class="row">
        <div class="col">
            <div class="registration-type-div mb-2">
                <div class="selector">
                    <div class="selector-item">
                        <input type="radio" id="radio1" name="selector" value="1" class="selector-item_radio" checked>
                        <label for="radio1" class="selector-item_label" onclick="showCompanyInput()">Port Number</label>
                    </div>
                    <div class="selector-item">
                        <input type="radio" id="radio2" name="selector" value="2" class="selector-item_radio">
                        <label for="radio2" class="selector-item_label" onclick="hideCompanyInput()">Get New SIM</label>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">

        <div class="col-12  mb-2">
            <span class="common-input-headers">User Information</span>
        </div>
        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="fname" class="form-control taskName" autocomplete="off"
                       name="fname" required/>
                <label class="floating-label place-holder">First Name</label>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="lname" class="form-control taskName" autocomplete="off"
                       name="lname" required/>
                <label class="floating-label place-holder">Last Name</label>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="mobileNumber" class="form-control taskName" autocomplete="off"
                       name="mobileNumber" required/>
                <label class="floating-label place-holder">Mobile Number to Port</label>
            </div>
        </div>

        <div class="col-12 col-md-6 company-name-container">
            <div class="floating-label-group mt-2">
                <%--                <input type="text" id="companyName" class="form-control taskName" autocomplete="off"--%>
                <%--                       name="companyName" required/>--%>
                <select id="companyName" class="form-control taskName" autocomplete="off"
                        name="companyName" required>
                    <option hidden="hidden">Select Company Name</option>
                    <c:forEach items="${companies}" var="company">
                        <option value="${company.companyId}">${company.companyName}</option>
                    </c:forEach>
                </select>
                <label class="floating-label place-holder dob-label">Current Service Provider</label>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="email" class="form-control taskName" autocomplete="off"
                       name="email" required/>
                <label class="floating-label place-holder">Email</label>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="date" id="dob" class="form-control taskName dob-inner-label" autocomplete="off"
                       name="dob" required/>
                <label class="floating-label place-holder dob-label">Date Of Birth</label>
            </div>
        </div>

    </div>

    <div class="row">

        <div class="col-12 mt-3 mb-2">
            <span class="common-input-headers">Address Information</span>
        </div>
        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="street" class="form-control taskName" autocomplete="off"
                       name="street" required/>
                <label class="floating-label place-holder">Street</label>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="city" class="form-control taskName" autocomplete="off"
                       name="city" required/>
                <label class="floating-label place-holder">City</label>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="state" class="form-control taskName" autocomplete="off"
                       name="state" required/>
                <label class="floating-label place-holder">State</label>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="zipcode" class="form-control taskName" autocomplete="off"
                       name="zipcode" required/>
                <label class="floating-label place-holder">Zipcode</label>
            </div>
        </div>

    </div>

    <div class="row">

        <div class="col-12 mt-3 mb-2">
            <span class="common-input-headers">Verification Details</span>
        </div>
        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="panNumber" class="form-control taskName" autocomplete="off"
                       name="panNumber" required/>
                <label class="floating-label place-holder">PAN Number</label>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="aadharCardNumber" class="form-control taskName" autocomplete="off"
                       name="aadharCardNumber" required/>
                <label class="floating-label place-holder">Aadhar Card Number</label>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="floating-label-group mt-2">
                <input type="text" id="GSTNumber" class="form-control taskName" autocomplete="off"
                       name="GSTNumber" required/>
                <label class="floating-label place-holder">GST Number (Optional)</label>
            </div>
        </div>

        <div class="col-12 mt-2 upload-view-flex">
            <div class="input-group">
                <input id="pan-card-name-id" type="text"
                       class="form-control p-2 verification-files custom-upload"
                       placeholder="Upload PAN Card" disabled/>
                <button class="file-upload-btn btn-color-for-upload" type="button" id="upload">
                    <img src="<c:url value='/resources/images/upload-white.svg' />" class="upload-image-file" alt="">
                    <span class="for-remove-upload ">Upload</span></button>
                <input id="panCardUploadedFile" multiple class="file-input-hover-effect" type="file"
                       style="position: absolute; right: -8px;top: 0.5rem; opacity: 0;">
            </div>

            <button class="verification-files main-container-view-btn pan-card-view d-none">
                <div class="view-btn-flex">
                    <img src="<c:url value='/resources/images/view-white.svg' />" class="upload-image-file" alt="">
                    <span class="for-remove-upload ">View</span>
                </div>
            </button>

        </div>

        <div class="col-12 mt-3 upload-view-flex">
            <div class="input-group">
                <input id="aadhar-card-name-id" type="text"
                       class="form-control p-2 verification-files custom-upload"
                       placeholder="Upload Aadhar Card" disabled>
                <button class="file-upload-btn btn-color-for-upload" type="button" id="upload">
                    <img src="<c:url value='/resources/images/upload-white.svg' />" class="upload-image-file" alt="">
                    <span class="for-remove-upload ">Upload</span></button>
                <input id="aadharCardUploadedFile" multiple class="file-input-hover-effect" type="file"
                       style="position: absolute; right: -8px;top: 0.5rem; opacity: 0;">
            </div>

            <button class="verification-files main-container-view-btn aadhar-card-view d-none">
                <div class="view-btn-flex">
                    <img src="<c:url value='/resources/images/view-white.svg' />" class="upload-image-file" alt="">
                    <span class="for-remove-upload ">View</span>
                </div>
            </button>

        </div>

        <div class="col-12 mt-3 upload-view-flex">
            <div class="input-group">
                <input id="profile-pic-name-id" type="text"
                       class="form-control p-2 verification-files custom-upload"
                       placeholder="Upload Profile Picture" disabled>
                <button class="file-upload-btn btn-color-for-upload" type="button" id="upload">
                    <img src="<c:url value='/resources/images/upload-white.svg' />" class="upload-image-file" alt="">
                    <span class="for-remove-upload ">Upload</span></button>
                <input id="profilePicUploadedFile" multiple class="file-input-hover-effect" type="file"
                       style="position: absolute; right: -8px;top: 0.5rem; opacity: 0;">
            </div>

            <button class="verification-files main-container-view-btn profile-pic-view d-none">
                <div class="view-btn-flex">
                    <img src="<c:url value='/resources/images/view-white.svg' />" class="upload-image-file" alt="">
                    <span class="for-remove-upload ">View</span>
                </div>
            </button>

        </div>


    </div>


</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script src="<c:url value='/resources/js/registration.js' />"></script>
<script src="<c:url value='/resources/js/global-js.js' />"></script>
<script src="<c:url value='/resources/js/toasters-1.js' />"></script>
<script src="<c:url value='/resources/js/loader.js' />"></script>
</body>
</html>
