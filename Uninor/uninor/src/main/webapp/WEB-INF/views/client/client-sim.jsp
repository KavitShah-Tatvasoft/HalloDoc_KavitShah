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
    <title>Simcard Details</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/client-sim.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/notification-tab.css' />">
</head>
<body class="show-sidebar body" onload="getSimDetails(),getNotificationDetails()">
<%@include file="navbar.jsp" %>
<div class="outermost-contianer ">
    <%@include file="sidebar.jsp" %>
    <div class="custom-background"></div>
    <main>
        <%@include file="notification-tab.jsp" %>
        <div class="container-fluid  main-container">  <!-- Change inside content and class-->
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
                        <input type="text" id="mobileNumber" data-number="0" class="form-control taskName"
                               autocomplete="off"
                               name="mobileNumber" required/>
                        <label class="floating-label place-holder mobile-placeholder">Mobile Number</label>
                    </div>
                </div>
            </div>

            <div class="row">

                <div class="col-12 mt-3 mb-2">
                    <span class="common-input-headers">Sim Information</span>
                </div>
                <div class="col-12 col-md-6">
                    <div class="floating-label-group mt-2">
                        <input type="text" id="iccid" class="form-control taskName" autocomplete="off"
                               name="iccid" required/>
                        <label class="floating-label place-holder">ICCID</label>
                    </div>
                </div>

                <div class="col-12 col-md-6">
                    <div class="floating-label-group mt-2">
                        <input type="text" id="imsi" class="form-control taskName" autocomplete="off"
                               name="imsi" required/>
                        <label class="floating-label place-holder">IMSI</label>
                    </div>
                </div>

                <div class="col-12 col-md-6">
                    <div class="floating-label-group mt-2">
                        <input type="text" id="imei" class="form-control taskName" autocomplete="off"
                               name="imei" required/>
                        <label class="floating-label place-holder">IMEI</label>
                    </div>
                </div>

                <div class="col-12 col-md-6">
                    <div class="floating-label-group mt-2">
                        <input type="text" id="puk" class="form-control taskName" autocomplete="off"
                               name="puk" required/>
                        <label class="floating-label place-holder">PUK Number</label>
                    </div>
                </div>

                <div class="col-12 col-md-6">
                    <div class="floating-label-group mt-2">
                        <input type="text" id="sim-type" class="form-control taskName" autocomplete="off"
                               name="sim-type" required/>
                        <label class="floating-label place-holder">Sim Type</label>
                    </div>
                </div>

                <div class="col-12 col-md-6">
                    <div class="floating-label-group mt-2">
                        <input type="text" id="block-status" class="form-control taskName" autocomplete="off"
                               name="block-status" required/>
                        <label class="floating-label place-holder">Block Status</label>
                    </div>
                </div>

                <div class="col-12 col-md-6">
                    <div class="floating-label-group mt-2">
                        <input type="text" id="roaming" class="form-control taskName" autocomplete="off"
                               name="roaming" required/>
                        <label class="floating-label place-holder">Roaming Status</label>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="sim-deactivation-flex">
                        <button class="deactivation-btn" data-bs-toggle="modal" data-bs-target="#deactivate-modal">Deactivate Sim</button>
                    </div>
                </div>
            </div>

        </div>
    </main>

    <div class="modal fade" id="deactivate-modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content deactivate-modal-service-main-content">
                <div class="modal-header deactivate-service-main-header">
                    <header class="deactivate-toggle-service">
                        <span class="block-toggle-type-text">Sim Deactivation</span>
                        <div class="close close-block-toggle-modal"><i class="uil uil-times"></i></div>
                    </header>
                </div>
                <div class="modal-body">
                    <div class="content">
                        <p class="deactivation-text">You are about to deactivate you sim card. Once you deactivate your sim you cannot access the services provided.</p>
                        <input type="text" class="deactivate-confirm-input" placeholder="Enter Confirm to continue"/>
                        <span class="confirm-error-text d-none">Please enter confirm properly to deactivate sim!</span>
                        <div class="button-div">
                            <button id="deactivateSwitchButton" class="mt-4" onclick="deactivateSim()">Deactivate Sim</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
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
<script src="<c:url value='/resources/js/client-sim.js' />"></script>
<%--//web socket------%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"
        integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"
        integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="<c:url value='/resources/js/notification-tab.js' />"></script>
</body>
</html>

<style>

    .deactivation-text{
        color: #232323;
        font-weight: 500;
    }

    .deactivate-modal-service-main-content{
        border-radius: 18px;
    }

    .toggle-service-main-content .modal-body{
        padding: 20px 0px;
    }

    .deactivate-service-main-header{
        padding: 0px;
        width: 100%;
    }

    .button-div{
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .deactivate-confirm-input{
        height: 30px;
        width: 100%;
        outline: none;
        border: 1px solid #5b5c5f;
        border-radius: 6px;
        padding: 15px;
    }

    .deactivate-toggle-service{
        display: flex;
        justify-content: space-between;
        width: 100%;
        padding: 15px 25px 15px 25px;
        background-color: #3750EB;
        border-radius: 15px 15px 0px 0px;
        color: white;
    }



    .content{
        padding: 0px 25px 0px 25px;
    }

    button{
        background-color: #3750eb;
        padding: 6px 12px;
        color: white;
        outline: none;
        cursor: pointer;
        font-weight: 500;
        border-radius: 4px;
        border: 1px solid transparent;
        transition: background 0.1s linear, border-color 0.1s linear, color 0.1s linear;
    }

    .popup :is(header, .icons, .field){
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    .popup header{
        padding-bottom: 15px;
        border-bottom: 1px solid #ebedf9;
    }
    header span{
        font-size: 21px;
        font-weight: 600;
    }
    header .close, .icons a{
        display: flex;
        align-items: center;
        border-radius: 50%;
        justify-content: center;
        transition: all 0.3s ease-in-out;
    }
    header .close{
        color: black;
        font-size: 17px;
        background: white;
        height: 33px;
        width: 33px;
        cursor: pointer;
    }
    header .close:hover{
        background: #ebedf9;
    }
    .popup .content{
        margin: 20px 0;
    }
    .popup .icons{
        margin: 15px 0 20px 0;
    }
</style>


