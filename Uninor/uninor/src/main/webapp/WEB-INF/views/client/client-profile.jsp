<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 10-07-2024
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <title>Profile</title>
    <link rel="icon" href="<c:url value="/resources/images/white-logo.png" />" type="image/icon type">
    <%--    font link--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='/resources/css/navbar.css' />">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro:400,600&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value='/resources/fonts/icomoon/style.css' />">

    <link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.min.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/toaster-1.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/loader.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/success-faliure-modal.css' />">

    <!-- Style -->
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/client-profile.css' />">
</head>
<body class="show-sidebar body" onload="getProfileData()">
<%@include file="navbar.jsp" %>
<div class="outermost-contianer ">
    <%@include file="sidebar.jsp" %>
    <div class="custom-background"></div>
    <main>
        <div class="container-fluid  main-container">  <!-- Change inside content and class-->
             <form id="profile-form" method="post">
                <div class="row">
                    <div class="col-12">
                        <div class="image-div">
                            <label for="profile-image">
                                <img src="<c:url value='/resources/images/profile-pic.jpg' />" id="visible-profile-image">
                            </label>

                            <input type="file" id="profile-image" onchange="validateImageUpload(this)" name="profile-pic" class="d-none">
                        </div>
                    </div>
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
                            <input type="text" id="mobileNumber" data-number="0" class="form-control taskName"
                                   autocomplete="off"
                                   name="mobileNumber" required/>
                            <label class="floating-label place-holder mobile-placeholder">Mobile Number</label>
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


                    <div class="col-12 mt-4">
                        <div class="submit-cancel-flex">
                            <button type="submit" class="submit-btn-register">Update Profile</button>
                            <button type="button" onclick="getProfileData()" class="cancel-btn-register">Cancel</button>
                        </div>
                    </div>


                </div>

            </form>
        </div>
    </main>

    <div class="toast toast-failure">

        <div class="toast-content toast-failure-content">
            <i class='bx bx-error icon'></i>

            <div class="message">
                <span class="text text-1">Failure</span> <span
                    class="text text-2 toaster-failure-message"></span>
            </div>
        </div>
        <i class='bx bx-x failure-close close'></i>


        <div class="toast-progress toast-failure-progress"></div>
    </div>

    <div class="toast toast-success">

        <div class="toast-content toast-success-content">
            <i class='bx bx-check icon'></i>

            <div class="message">
                <span class="text text-1">Success</span> <span
                    class="text text-2 toaster-success-message"></span>
            </div>
        </div>
        <i class='bx bx-x success-close close'></i>


        <div class="toast-progress toast-success-progress"></div>
    </div>

    <%--Success Faliure Modal--%>

    <div class="modal fade" id="statusErrorsModal" tabindex="-1" role="dialog" data-bs-keyboard="false">
        <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-body text-center p-lg-4">
                    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
                        <circle class="path circle" fill="none" stroke="#db3646" stroke-width="6" stroke-miterlimit="10"
                                cx="65.1" cy="65.1" r="62.1"/>
                        <line class="path line" fill="none" stroke="#db3646" stroke-width="6" stroke-linecap="round"
                              stroke-miterlimit="10" x1="34.4" y1="37.9" x2="95.8" y2="92.3"/>
                        <line class="path line" fill="none" stroke="#db3646" stroke-width="6" stroke-linecap="round"
                              stroke-miterlimit="10" x1="95.8" y1="38" X2="34.4" y2="92.2"/>
                    </svg>
                    <h4 class="text-danger failure-message-heading mt-3">Payment failed!</h4>
                    <p class="mt-3 failure-message-modal">Please check the entered details and try again.</p>
                    <button type="button" class="btn btn-sm mt-3 btn-danger" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="statusSuccessModal" tabindex="-1" role="dialog" data-bs-keyboard="false">
        <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-body text-center p-lg-4">
                    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
                        <circle class="path circle" fill="none" stroke="#198754" stroke-width="6" stroke-miterlimit="10"
                                cx="65.1" cy="65.1" r="62.1"/>
                        <polyline class="path check" fill="none" stroke="#198754" stroke-width="6"
                                  stroke-linecap="round"
                                  stroke-miterlimit="10" points="100.2,40.2 51.5,88.8 29.8,67.5 "/>
                    </svg>
                    <h4 class="text-success success-message-heading-modal  mt-3">Coupon Redeemed Successfully!</h4>
                    <p class="mt-3 success-message-modal">Your data would be updated accordingly.</p>
                    <button type="button" class="btn btn-sm mt-3 btn-success" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="//cdn.jsdelivr.net/gh/freeps2/a7rarpress@main/script.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="<c:url value='/resources/js/main.js' />"></script>
<script src="<c:url value='/resources/js/global-js.js' />"></script>
<script src="<c:url value='/resources/js/toasters-1.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script src="<c:url value='/resources/js/client-profile.js' />"></script>
</body>
</html>

