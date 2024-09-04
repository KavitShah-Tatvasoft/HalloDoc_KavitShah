<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 07-06-2024
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <title>Sign Up</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/client-dashboard.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/notification-tab.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/toaster-1.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/loader.css' />">

    <!-- Style -->
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body class="show-sidebar body" onload="getDashboardData(),getNotificationDetails()">
<div class="loader-container">
    <div class="loader"></div>
</div>
<header>
    <%@include file="navbar.jsp" %>
</header>

<div class="outermost-contianer ">
    <%@include file="sidebar.jsp" %>
    <div class="custom-background"></div>
    <main>
        <%@include file="notification-tab.jsp" %>
        <div class="container-fluid dashboard-container">
            <div class="row">
                <div class="col-12 col-md-6  user-card user-wallet-row">
                    <div class="common-dashboard-card">
                        <div class="name-icon-profile-flex">
                            <div class="name-icon-flex">
                                <div class="person-icon"><img
                                        src="<c:url value='/resources/icons/person-filled-animated.svg'/> "
                                        class="person-filled-icon"></div>
                                <div class="card-client-name" id="client-name-id">John Snow Stark</div>
                            </div>

                            <div class="profile-arrow">
                                <a class="profile-arrow-bg" href="${pageContext.request.contextPath}/client/client-profile"><img
                                        src="<c:url value='/resources/icons/chevron-right-animated.svg'/> "
                                        class="profile-arrow"></a>
                            </div>
                        </div>
                        <div class="phone-number-flex-col">
                            <div class="sim-type">Prepaid Mobile</div>
                            <div class="phone-number">9898585965</div>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-md-6 user-wallet-row">
                    <div class="common-dashboard-card">
                        <div class="wallet-text-flex">
                            <div class="wallet-rewards-text">
                                <div class="name-icon-flex">
                                    <div class="person-icon"><img src="<c:url value='/resources/icons/wallet.svg'/> "
                                                                  class="person-filled-icon"></div>
                                    <div class="card-client-name">Wallet</div>
                                </div>
                                <div class="rewards-text">
                                    <span>See your <br><span class="rewards-keyword">Rewards</span> at glance!</span>
                                </div>
                            </div>

                            <div class="money-text-flex">
                                <img src="<c:url value='/resources/icons/rupees.svg'/> " class="wallet-icon-rs">
                                <span class="wallet-money" id="dashboard-wallet-amount">2900</span>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="row active-plan-row">
                <div class="col-12 col-md-6 data-usage-card plan-usage-row">
                    <div class="common-dashboard-card">
                        <div class="name-icon-flex">
                            <div class="person-icon"><img
                                    src="<c:url value='/resources/icons/internet-arrows-animated.svg'/> "
                                    class="person-filled-icon"></div>
                            <div class="card-client-name">Daily Internet Usage</div>
                        </div>
                        <div class="data-usage-left-progress-flex">
                            <div class="left-data-add-flex">
                                <div class="left-data" id="left-data-single-id">
                                    <span class="data-left" id="data-left-progress-top">1.29 GB</span>
                                    <span class="total-data">left of <span id="total-data-progress-top">1.5 GB</span></span>
                                </div>

                                <div>
                                    <span class="additional-data-class d-none"></span>
                                </div>

                            </div>
                            <div class="progress">
                                <div class="progress-bar data-progress-bar" role="progressbar" aria-label="Basic example"
                                     style="width: 89%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="renews-time-text-flex">
                                <span class="renews-in-text">Renews in 2.5 hours</span>
                            </div>

                            <div class="renews-time-voice-flex d-none">
                                <span class="voice-usage">Voice : <span class="voice-usage-details"></span></span>
                                <span class="renews-in-text">Renews in 2.5 hours</span>
                            </div>

                            <div class="add-data-button-flex add-additional-data">
                                <a class="add-data-button " href="${pageContext.request.contextPath}/client/recharge-tab">Add Data</a>
                            </div>

                            <div class="left-data extra-data-flex d-none">
                                <span class="extra-data-text extra-data-class">Extra Data Used : </span>
                                <span class="extra-data-usage extra-data-class">10 GB</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-md-6 plan-usage-row">
                    <div class="common-dashboard-card">
                        <div class="name-icon-flex">
                            <div class="person-icon"><img src="<c:url value='/resources/icons/subscription.svg'/> "
                                                          class="person-filled-icon"></div>
                            <div class="card-client-name">Current Subscription</div>
                        </div>

                        <div class="amount-expire-flex">
                            <div class="amount-icon-flex">
                                <img src="<c:url value='/resources/icons/rupees.svg'/> " class="">
                                <span class="current-subscription-amount">666</span>
                            </div>

                            <div class="expire-flex">
                                <span>Expires on</span>
                                <span class="subscription-expirtaion-date">20th May, 2025</span>
                            </div>
                        </div>
                        <div class="add-data-button-flex extra-margin">
                            <button class="add-data-button" id="view-plan-btn" onclick="getPlanData()">View Plan</button>
                            <button class="add-data-button d-none" id="pay-postpaid-bill ">Pay Bill</button>
                        </div>
                    </div>
                </div>

            </div>

            <div class="row no-active-plan-row d-none">
                <div class="col-12 col-md-6 data-usage-card plan-usage-row">
                    <div class="common-dashboard-card no-active-plan-flex">
                        <div>
                            <div class="no-active-plan-heading-icon-flex">
                                <img class="timer-icon-red" src="<c:url value='/resources/icons/time-expired.svg'/> ">
                                <p class="no-active-plan">No Active Plan</p>
                            </div>
                            <span class="plan-expire-text">Please recharge your number before it gets disconnected!</span>
                        </div>
                        <div class="add-data-button-flex">
                            <a type="button" href="${pageContext.request.contextPath}/client/recharge-tab" class="add-data-button">Buy Plan</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="common-dashboard-card features-main-card">
                        <div class="row">
                            <div class="col-12 col-md-6 features-col-1">
                                <div class="features-icon-flex features-icon-flex-1">
                                    <a type="button" href="${pageContext.request.contextPath}/client/sim-details" class="icon-name-flex">
                                        <div class="single-icon">
                                            <img src="<c:url value='/resources/icons/sim.svg'/> "
                                                 class="person-filled-icon">
                                        </div>
                                        <div class="text-flex-features">
                                            <span>
                                                SIM
                                            </span>
                                            <span>Details</span>
                                        </div>
                                    </a>

                                        <a class="icon-name-flex" href="${pageContext.request.contextPath}/client/redeem-rewards">
                                            <div class="single-icon">
                                                <img src="<c:url value='/resources/icons/gift.svg'/> "
                                                     class="person-filled-icon">
                                            </div>
                                            <div class="text-flex-features">
                                            <span>
                                                Redeem
                                            </span>
                                                <span>Rewards</span>
                                            </div>
                                        </a>

                                        <div class="icon-name-flex activate-roaming-btn" onclick="hideConfirmError()" data-bs-toggle="modal"  data-bs-target="#toggle-roaming-service">
                                            <div class="single-icon">
                                                <img src="<c:url value='/resources/icons/globe-pc.svg'/> " class="person-filled-icon">
                                            </div>
                                            <div class="text-flex-features">
                                            <span class="change-roaming-btn-status">
                                                Activate
                                            </span>
                                                <span>Roaming</span>
                                            </div>
                                        </div>

                                    <div class="icon-name-flex d-none roaming-plans-btn">
                                        <div class="single-icon">
                                            <img src="<c:url value='/resources/icons/roaming-plans.svg'/> "
                                                 class="person-filled-icon">
                                        </div>
                                        <div class="text-flex-features">
                                            <span>
                                                Roaming
                                            </span>
                                            <span>Plans</span>
                                        </div>
                                    </div>

                                    <div class="icon-name-flex d-none pay-bill-btn" onclick="checkPostPaidBillDue()" >
                                        <div class="single-icon">
                                            <img src="<c:url value='/resources/icons/roaming-plans.svg'/> "
                                                 class="person-filled-icon">
                                        </div>
                                        <div class="text-flex-features">
                                            <span>
                                                Pay
                                            </span>
                                            <span>Bills</span>
                                        </div>
                                    </div>

                                    </div>
                                </div>

                                <div class="col-12 col-md-6 features-col-2">
                                    <div class="features-icon-flex features-icon-flex-2">
                                        <a class="icon-name-flex" href="${pageContext.request.contextPath}/client/recharge-history">
                                            <div class="single-icon">
                                                <img src="<c:url value='/resources/icons/recharge-history.svg'/> "
                                                     class="person-filled-icon">
                                            </div>
                                            <div class="text-flex-features">
                                            <span>
                                                Recharge
                                            </span>
                                                <span>History</span>
                                            </div>
                                        </a>

                                        <div class="icon-name-flex switch-postpaid-btn" type="button" onclick="hideConfirmError()" data-bs-toggle="modal" data-bs-target="#toggle-service">
                                            <div class="single-icon">
                                                <img src="<c:url value='/resources/icons/switch.svg'/> "
                                                     class="person-filled-icon">
                                            </div>
                                            <div class="text-flex-features">
                                            <span>
                                                Switch
                                            </span>
                                                <span>to Postpaid</span>
                                            </div>
                                        </div>

                                        <div class="icon-name-flex switch-prepaid-btn d-none" type="button" onclick="hideConfirmError()" data-bs-toggle="modal" data-bs-target="#toggle-service">
                                            <div class="single-icon">
                                                <img src="<c:url value='/resources/icons/switch.svg'/> "
                                                     class="person-filled-icon">
                                            </div>
                                            <div class="text-flex-features">
                                            <span>
                                                Switch
                                            </span>
                                                <span>to Prepaid</span>
                                            </div>
                                        </div>

                                        <div class="icon-name-flex block-sim-btn" style="margin-bottom: 17px" data-bs-toggle="modal" data-bs-target="#toggle-block-service">
                                            <div class="single-icon">
                                                <img src="<c:url value='/resources/icons/lost-sim.svg'/> "
                                                     class="person-filled-icon">
                                            </div>
                                            <span class="lost-sim-text">Block Sim</span>
                                        </div>

                                        <div class="icon-name-flex unblock-sim-btn d-none" data-bs-toggle="modal"  data-bs-target="#toggle-unblock-service">
                                            <div class="single-icon">
                                                <img src="<c:url value='/resources/icons/lost-sim.svg'/> "
                                                     class="person-filled-icon">
                                            </div>
                                            <div class="text-flex-features">
                                            <span>
                                                Unblock
                                            </span>
                                                <span>Sim</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>

                <div class="row maps-row">
                    <div class="col-12 col-md-6 polar-chart-col">
                        <div class="chart-background-class">
                        <div class="chart-heading">Popular Plans</div>
                            <canvas id="myChart"></canvas>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 line-chart-col">
                        <div class="chart-background-class">
                            <div class="chart-heading">Daily Usage</div>
                            <canvas id="myChart1"></canvas>
                        </div>
                    </div>
                </div>

            <div class="modal fade" id="toggle-service" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content toggle-service-main-content">
                        <div class="modal-header toggle-service-main-header">
                            <header class="toggle-service">
                                <span class="toggle-type-text">Switch To Postpaid</span>
                                <div class="close close-toggle-modal"><i class="uil uil-times"></i></div>
                            </header>
                        </div>
                        <div class="modal-body">
                            <div class="content">
                                <p>Are you sure you want to switch to a&nbsp;<span class="para-type-toggle">postpaid</span>&nbsp; SIM? Once changed all your current plans and pending plans would expire.</p>
                                <input type="text" class="confirm-input" placeholder="Enter confirm to continue"/>
                                <span class="confirm-error-text d-none">Please type confirm correctly to continue</span>
                                <div class="button-div">
                                    <button id="confirmSwitchButton" class="mt-4" onclick="toggleServiceType()">Switch to Postpaid</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="modal fade" id="toggle-roaming-service" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content roaming-toggle-service-main-content">
                        <div class="modal-header roaming-toggle-service-main-header">
                            <header class="roaming-toggle-service">
                                <span class="roaming-toggle-type-text">Activate Roaming</span>
                                <div class="close close-roaming-toggle-modal"><i class="uil uil-times"></i></div>
                            </header>
                        </div>
                        <div class="modal-body">
                            <div class="content">
                                <p>Are you sure you want to switch &nbsp;<span class="roaming-type-toggle">on</span>&nbsp;roaming? It will deactivate your current plan till you <span class="roaming-status-text">deactivate</span>&nbsp;roaming!</p>
                                <input type="text" class="roaming-confirm-input" placeholder="Enter confirm to continue"/>
                                <span class="confirm-error-text d-none">Please type confirm correctly to continue</span>
                                <div class="button-div">
                                    <button id="confirmRoamingSwitchButton" class="mt-4" onclick="changeRoamingStatus()">Activate Roaming</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="modal fade" id="toggle-block-service" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content block-toggle-service-main-content">
                        <div class="modal-header block-toggle-service-main-header">
                            <header class="block-toggle-service">
                                <span class="block-toggle-type-text">Block Sim?</span>
                                <div class="close close-block-toggle-modal"><i class="uil uil-times"></i></div>
                            </header>
                        </div>
                        <div class="modal-body">
                            <div class="content">
                                <p>Are you sure you want to deactivate your SIM? Once deactivated you will not be able to use it until you reactive it!</p>
                                <input type="text" class="block-confirm-input" placeholder="Enter confirm to continue"/>
                                <span class="confirm-error-text d-none">Please type confirm correctly to continue</span>
                                <div class="button-div">
                                    <button id="confirmBlockSwitchButton" class="mt-4" onclick="createBlockRequest()">Block Sim</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="modal fade" id="toggle-unblock-service" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content block-toggle-service-main-content">
                        <div class="modal-header block-toggle-service-main-header">
                            <header class="block-toggle-service">
                                <span class="block-toggle-type-text">Unblock Sim Request</span>
                                <div class="close close-block-toggle-modal"><i class="uil uil-times"></i></div>
                            </header>
                        </div>
                        <div class="modal-body">
                            <div class="content">
                                <p>To active your sim back, please enter the SIM card PUK code. Once request is accepted it will reactivate your sim.</p>
                                <input type="text" class="unblock-confirm-input" placeholder="Enter PUK code to continue"/>
                                <span class="confirm-error-text d-none">Please enter valid PUK code!</span>
                                <div class="button-div">
                                    <button id="confirmUnblockSwitchButton" class="mt-4" onclick="createUnblockRequest()">Unblock Sim</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="modal fade" id="payment-confirmation-service" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content payment-confirmation-main-content ">
                        <div class="modal-header payment-confirmation-main-header">
                            <header class="payment-confirmation-toggle-service">
                                <span class=" payment-confirmation-type-text">Pay Postpaid Bills?</span>
                                <div class="close close-toggle-modal"><i class="uil uil-times"></i></div>
                            </header>
                        </div>
                        <div class="modal-body">
                            <div class="content">
                                <p>You are about to pay dues of current postpaid plan. As you would know your current plan is not expired still. Once paid, then your current plan would expired immediately.</p>
                                <input type="text" class="payment-confirm-input" placeholder="Enter confirm to continue"/>
                                <span class="confirm-error-text d-none">Please type confirm correctly to continue</span>
                                <div class="button-div">
                                    <button id="confirmPayDueSwitchButton" class="mt-4" onclick="showRechargeModal()">Confirm Payment</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="view-plan-modal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="view-plan-header modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">
                                <div class="modal-amount-icon-flex">
                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="modal-rs-icon">
                                    <span class="recharge-modal-amount">666</span>
                                </div>
                            </h1>

                            <button type="button" class="btn-close close-view-plan-modal" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body view-plan-body">
                            <div class="plan-detials-modal-text">Plan Details</div>
                            <div class="table-responsive">
                                <table class="table table-bordered">
                                    <tbody>
                                    <tr>
                                        <td>Pack Validity</td>
                                        <td><span class="recharge-modal-validity">84</span>&nbsp;<span class="recharge-modal-days">days</span></td>
                                    </tr>
                                    <tr>
                                        <td>Total Data</td>
                                        <td class="recharge-modal-total-data">126 GB</td>
                                    </tr>
                                    <tr>
                                        <td><span class="relative-class">Data at high speed<span class="astreik-class">*</span></span></td>
                                        <td><span class="recharge-modal-data-amount">84</span>&nbsp;<span class="recharge-modal-refresh">GB/Day</span></td>
                                    </tr>
                                    <tr>
                                        <td>Voice Call</td>
                                        <td class="recharge-modal-voice-amount">Unlimited</td>
                                    </tr>
                                    <tr>
                                        <td>SMS</td>
                                        <td><span class="recharge-modal-sms-amount">100</span>&nbsp;<span class="recharge-modal-sms-refresh">SMS/Day</span></td>
                                    </tr>
                                    <tr>
                                        <td><span class="relative-class">Additional Data<span class="astreik-class">*</span></span></td>
                                        <td><span class="recharge-modal-additional-data-amount">10</span> GB</td>
                                    </tr>
                                    </tbody>
                                </table>

                                <div class="notes-text-modal">* Notes</div>
                                <div class="notes-flex">
                                    <span class="notes-view-modal">- Post which unlimited @ 64 Kbps</span>
                                    <span class="notes-view-modal">- Unlimited 5G data for eligible subscribers</span>
                                    <span class="notes-view-modal">- Additional Data will be valid till the base plan is not expired.</span>
                                    <span class="notes-view-modal">- For any recharge plan a minimum of Rs.10 transaction is required.</span>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer view-plan-modal">
                            <button class="modal-recharge-button show-recharge-modal" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal recharge-plan-modal fade fade-scale in" id="recharge-plan" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog recharge-plan-modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content recharge-plan-modal-content">
                        <div class="modal-header recharge-plan-modal-header">
                            <h1 class="modal-title recharge-plan-modal-title fs-5" id="staticBackdropLabel">Recharge Details</h1>
                            <button type="button" class="btn-close recharge-modal-close" onclick="closePaymentModal()" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body recharge-plan-modal-body">

                            <div class="accordion accordion-flush" id="accordion-plan-details">
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button plan-details-view-btn custom-icon collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#recharge-modal-plan-details" aria-expanded="false" aria-controls="flush-collapseOne">
                                            Plan Details
                                        </button>
                                    </h2>
                                    <div id="recharge-modal-plan-details" class="accordion-collapse collapse" data-bs-parent="#accordion-plan-details">
                                        <div class="accordion-body">
                                            <div class="table-responsive">
                                                <table class="table table-bordered">
                                                    <tbody>

                                                    <tr>
                                                        <td>Pack Validity</td>
                                                        <td><span class="recharge-modal-validity">84</span>&nbsp;<span class="recharge-modal-days">days</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Total Data</td>
                                                        <td class="recharge-modal-total-data">126 GB</td>
                                                    </tr>
                                                    <tr>
                                                        <td><span class="relative-class">Data at high speed<span class="astreik-class">*</span></span></td>
                                                        <td><span class="recharge-modal-data-amount">84</span>&nbsp;<span class="recharge-modal-refresh">GB/Day</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Voice Call</td>
                                                        <td class="recharge-modal-voice-amount">Unlimited</td>
                                                    </tr>
                                                    <tr>
                                                        <td>SMS</td>
                                                        <td><span class="recharge-modal-sms-amount">100</span>&nbsp;<span class="recharge-modal-sms-refresh">SMS/Day</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td><span class="relative-class">Additional Data<span class="astreik-class">*</span></span></td>
                                                        <td><span class="recharge-modal-additional-data-amount">10</span> GB</td>
                                                    </tr>
                                                    <tr>
                                                        <td><span class="relative-class">Extra Data Charges<span class="astreik-class">*</span></span></td>
                                                        <td><span class="recharge-modal-additional-data-charge">10</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td><span class="relative-class">Extra SMS Charges<span class="astreik-class">*</span></span></td>
                                                        <td><span class="recharge-modal-additional-sms-charge">10</span></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button plan-details-view-btn custom-icon collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#cupon-code" aria-expanded="false" aria-controls="flush-collapseOne">
                                            Add Cupon Code <span class="applied-btn d-none">Applied</span>
                                        </button>
                                    </h2>
                                    <div id="cupon-code" class="accordion-collapse collapse" data-bs-parent="#accordion-plan-details">
                                        <div class="accordion-body">
                                            <div class="cupon-text-code-flex">
                                                <div class="add-cupon-text">Cupon Code :</div>
                                                <input type="text" class="cupon-code-input cupon-code-recharge-tab" onblur="validateCuponCode()">
                                                <span class="verify-code" onclick="verifyPostpaidCuponWalletChanges()">Apply</span>
                                                <span class="d-none remove-cupon" onclick="removeCuponCode()">Remove</span>
                                            </div>
                                            <span class="cupon-code-error d-none"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="recharge-summary-flex">
                                <div class="summary-details">
                                    <div class="summary-row-flex">
                                        <input type="text" class="plan-id-field" hidden="hidden"/>
                                        <div class="summary-title">Plan Amount</div>
                                        <div class="summary-value-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                            <div class="summary-value" id="recahrge-plan-price-id">569.00</div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex">
                                        <input type="text" class="plan-id-field" hidden="hidden"/>
                                        <div class="summary-title">SMS Used</div>
                                        <div class="summary-value-flex">
                                            <div class="summary-value" id="recahrge-plan-sms-used">25</div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex">
                                        <input type="text" class="plan-id-field" hidden="hidden"/>
                                        <div class="summary-title">SMS Charges</div>
                                        <div class="summary-value-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                            <div class="summary-value" id="recahrge-plan-sms-charges">250.00</div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex">
                                        <input type="text" class="plan-id-field" hidden="hidden"/>
                                        <div class="summary-title">Extra Data Used</div>
                                        <div class="summary-value-flex">
                                            <div class="summary-value" id="recahrge-plan-extra-data-used">25.5</div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex">
                                        <input type="text" class="plan-id-field" hidden="hidden"/>
                                        <div class="summary-title">Extra Data Charges</div>
                                        <div class="summary-value-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                            <div class="summary-value" id="recahrge-plan-extra-data-charges">255.00</div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex">
                                        <div class="summary-title">Discount Applied</div>
                                        <div class="summary-value-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                            <div class="summary-value" id="recharge-discount-applied">25.05</div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex">
                                        <div class="summary-title">Tax Amount</div>
                                        <div class="summary-value-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                            <div class="summary-value" id="tax-amount-applied">38.50</div>
                                        </div>
                                    </div>

                                    <hr class="summary-recharge-hr">

                                    <div class="summary-row-flex final-price-flex">
                                        <div class="summary-title">Total Amount</div>
                                        <div class="summary-value-flex ">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                            <div class="summary-value" id="recharge-total-amount">630.50</div>
                                        </div>
                                    </div>


                                    <div class="wallet-card">
                                        <div class="wallet-details-flex">
                                            <div class="wallet-left-side">
                                                <img src="<c:url value='/resources/icons/wallet.svg'/>" class="wallet-wallet-icon">
                                                <div class="wallet-balance-flex">
                                                    <span class="avaiable-amount-text">Amount</span>
                                                    <span class="wallet-total-amount-span">
                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="wallet-rs-icon">
                                                <span class="wallet-amount" id="recharge-wallet-amount">500</span>
                                            </span>
                                                </div>
                                            </div>
                                            <div class="wallet-right-side">
                                                <img src="<c:url value='/resources/icons/i-icon-animated-red.svg'/>" class="i-icon-recahrge d-none" data-toggle="tooltip" title="Invalid balance amount!">
                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="input-rs-icon">
                                                <input type="text" class="amount-input recahrge-wallet-amount-input" onblur="verifyPostpaidCuponWalletChanges()" placeholder="Amount"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex final-price-div">
                                        <div class="summary-title">Payable Amount <img src="<c:url value='/resources/icons/i-icon-animated-black.svg'/>" class="i-icon-recahrge" data-toggle="tooltip" title="Minimum transcation of 10 Rs required."></div>
                                        <div class="summary-value-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                            <div class="summary-value" id="recharge-payable-amount">38.50</div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="post-recharge-sim-toggle" id="post-recharge-sim-toggle">
                        <div class="modal-footer recharge-plan-modal-footer">
                            <button type="button" class="pay-now" data-bs-dismiss="modal" onclick="payPostPaidBill()">Pay <span class="final-pay-amount-btn">666.00</span></button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="statusErrorsModal" tabindex="-1" role="dialog" data-bs-keyboard="false">
                <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-body text-center p-lg-4">
                            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
                                <circle class="path circle" fill="none" stroke="#db3646" stroke-width="6" stroke-miterlimit="10" cx="65.1" cy="65.1" r="62.1" />
                                <line class="path line" fill="none" stroke="#db3646" stroke-width="6" stroke-linecap="round" stroke-miterlimit="10" x1="34.4" y1="37.9" x2="95.8" y2="92.3" />
                                <line class="path line" fill="none" stroke="#db3646" stroke-width="6" stroke-linecap="round" stroke-miterlimit="10" x1="95.8" y1="38" X2="34.4" y2="92.2" />
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
                                <circle class="path circle" fill="none" stroke="#198754" stroke-width="6" stroke-miterlimit="10" cx="65.1" cy="65.1" r="62.1" />
                                <polyline class="path check" fill="none" stroke="#198754" stroke-width="6" stroke-linecap="round" stroke-miterlimit="10" points="100.2,40.2 51.5,88.8 29.8,67.5 " />
                            </svg>
                            <h4 class="text-success success-message-heading-modal  mt-3">Payment done successfully!</h4>
                            <p class="mt-3 success-message-modal d-none">Please check the entered details and try again.</p>
                            <button type="button" class="btn btn-sm mt-3 btn-success" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="toaster-common-file.jsp"%>
    </main>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="<c:url value='/resources/js/global-js.js' />"></script>
<script src="<c:url value='/resources/js/main.js' />"></script>
<script src="<c:url value='/resources/js/client-dashboard.js' />"></script>
<script src="<c:url value='/resources/js/toasters-1.js' />"></script>
<script src="<c:url value='/resources/js/loader.js' />"></script>
<script src="<c:url value='/resources/js/notification-tab.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<%--<script src="https://www.gstatic.com/charts/loader.js"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<%--//web socket------%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"
        integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"
        integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>

</html>

<style>

    .chart-heading{
        position: absolute;
        top: 20px;
        left: 25px;
        font-size: 25px;
        font-weight: 600;
    }

    #myChart1{
        height: 90% !important;
    }

    .maps-row{
        align-items: stretch;
    }


    .chart-background-class{
        position: relative;
        height: 100%;
        background-color: white;
        border-radius: 8px;
        padding: 18px 20px;
        padding-top: 60px;
        box-shadow: rgba(0, 0, 0, 0.09) 0px 2px 1px, rgba(0, 0, 0, 0.09) 0px 4px 2px, rgba(0, 0, 0, 0.09) 0px 8px 4px, rgba(0, 0, 0, 0.09) 0px 16px 8px, rgba(0, 0, 0, 0.09) 0px 32px 16px;
    }

    .timer-icon-red{
        height: 22px;
        margin-bottom: 1rem;
    }

    .no-active-plan-heading-icon-flex{
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        gap: 8px;
    }

    .no-active-plan-flex{
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .no-active-plan{
        font-size: 26px;
        font-weight: 600;
        color: #f11e1e;
    }

    .plan-expire-text{
        font-size: 16px;
    }

    .dashboard-container{
        position: relative;
        min-height: 100vh;
    }

    .footer-flex{
        color: white;
        display: flex;
        justify-content: flex-end;
        align-items: center;
        gap: 10px;
        height: 50px;
    }

    .footer-flex a{
        text-decoration: none!important;
        color: white;
    }

    body::after {
        content: '';
        display: block;
        height: 50px;
    }

    footer{

        /*position: absolute;*/
        /*bottom: 0px;*/
        /*background-color: rgb(41, 39, 39);*/
        /*height: 50px;*/
        /*width: calc(100vw - var(--side-bar-width));*/

        margin-top: auto;
        position: relative;

        background-color: rgb(41, 39, 39);
        height: 50px;
        width: calc(100vw - var(--side-bar-width));
    }

    .footer-container{
        margin-bottom: 50px;
    }
</style>



