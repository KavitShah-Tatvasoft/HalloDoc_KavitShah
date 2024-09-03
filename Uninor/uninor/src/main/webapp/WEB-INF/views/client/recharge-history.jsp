<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 04-07-2024
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <title>Recharge History</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/recharge-history.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/notification-tab.css' />">
</head>
<body class="show-sidebar body" onload="getRechargeData(),getNotificationDetails()">
<%@include file="navbar.jsp" %>
<div class="outermost-contianer ">
    <%@include file="sidebar.jsp" %>
    <div class="custom-background"></div>
    <main>
        <%@include file="notification-tab.jsp" %>
        <div class="container-fluid invoice-container no-invoice-container ">  <!-- Change inside content and class-->
            <div class="no-invoice-flex d-none">
<%--                <img src="<c:url value='/resources/images/no-invoice-found.jpg' />" class="no-invoice-image">--%>
                <img src="<c:url value='/resources/images/kavit-1.png' />" class="no-invoice-image">
                <div class="no-invoice-text">No Invoice Found</div>
            </div>

            <div class="available-invoice">
                <div class="paid-plans-text mt-1 paid-plan-class">Paid Plans</div>
                <div class="col-12 col-md-6 mt-3 clone-plan-history d-none">
                    <div class="single-invoice-flex">
                        <div class="plan-details-flex">
                            <div class="plan-text">Amount</div>
                            <div class="plan-amount">&#8377;<span class="invoice-amount-span">666</span></div>
                            <div class="purchase-date">Purchased on <span class="bought-date-span">12th July, 2024 12:56 PM</span></div>
                            <hr class="hr-1">
                            <div class="details-flex">
                                <div class="single-detail-flex">
                                    <div>Payment Mode</div>
                                    <div class="payment-mode-span">UPI</div>
                                </div>
                                <div class="single-detail-flex">
                                    <div>Reference Number</div>
                                    <div class="reference-number-span">PAY_W3V6N7OSYropC5</div>
                                </div>
                                <div class="single-detail-flex">
                                    <div>Invoice Number</div>
                                    <div class="invoice-number-span">INA2024070118410005</div>
                                </div>
                            </div>
                            <div class="download-invoice-flex">
                                <div class="download-invoice-btn download-prepaid-invoice">Download Invoice</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row paid-plan-class paid-plan-history-div">

                </div>

                <div class="pagination-flex paid-plan-class">
                    <select name="page-size" id="page-size-id" onchange="getRechargeData()">
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                    </select>
                    <div class="pagination">
                        <button id="previous" onclick="previousButton()">Prev.</button>
                        <ul id="pageList"></ul>
                        <button id="next" onclick="nextButton()">Next</button>
                    </div>
                </div>

                <div class="paid-plans-text unpaid-plan-class">Unpaid Plans</div>
                <div class="row unpaid-plan-class">
                    <div class="col-12 col-md-6 mt-3 add-btm-margin">
                        <div class="single-invoice-flex">
                            <div class="plan-details-flex">
                                <div class="plan-text">Plan</div>
                                <div class="plan-amount">&#8377;<span class="unpaid-invoice-amount-span">666</span></div>
                                <div class="purchase-date">Activated on <span class="unpaid-activation-date-span">12th July, 2024 12:56 PM</span></div>
<%--                                <hr class="hr-1">--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


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
<script src="<c:url value='/resources/js/recharge-history.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
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
