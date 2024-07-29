<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 29-07-2024
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <title>Edit Plan</title>
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

    <!-- Swiper CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/freeps2/a7rarpress@main/swiper-bundle.min.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <!-- Style -->
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/edit-plan-page.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/toaster-1.css' />">
</head>
<body class="show-sidebar body" onload="applyFilter(false)">
    <%@include file="admin-navbar.jsp" %>
    <div class="outermost-contianer ">
        <%@include file="admin-sidebar.jsp" %>
        <div class="custom-background"></div>
        <main>
            <div class="container-fluid recharge-container">
                <div class="row ">
                    <div class="col-12">
                        <div class="create-plan-container">
                            <h6>Create new plan?</h6>
                            <button type="button" onclick="openNewPlanModal()">Create</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="slide-container swiper">
                <div class="slide-content">
                    <div class="recharge-text">Edit Plans</div>
                    <div type="button" data-bs-toggle="modal" data-bs-target="#filterModal" class="filter-btn">
                        <div class="filter-count">
                            <span class="filter-applied-count">0</span>
                        </div>
                        <div class="filter-icon-flex">
                            <span class="filter-text">Filter</span>
                            <img src="<c:url value='/resources/icons/filter-animated.svg'/>" class="filter-icon">
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid recharge-container">  <!-- Change inside content and class-->

                <div class="card-recharge single-recharge-card-clone d-none col-12 col-sm-6 col-md-4">
                    <div class="our_solution_category">
                        <div class="solution_cards_box">
                            <div class="solution_card">
                                <div class="hover_color_bubble"></div>
                                <div class="solu_title">
                                    <div class="amount-view-btn-flex">
                                        <h3 class="rs-amount-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                            <span class="amount-span" id="recharge-amount-single-card">666</span>
                                        </h3>

                                        <%--                                    <span class="view-plan-text" id="view-plan-single-card" data-bs-toggle="modal" data-bs-target="#view-plan-modal">View Plan</span>--%>
                                        <span class="view-plan-text" id="view-plan-single-card">View Plan</span>
                                    </div>
                                </div>
                                <div class="solu_description responsive-card">
                                    <div class="validity-data-flex">
                                        <div class="validity-flex">
                                            <span class="label-text">VALIDITY</span>
                                            <span class="label-value"><span id="validity-days-single-card">364</span>&nbsp;<span class="days-postfix">days</span></span>
                                        </div>

                                        <div class="validity-flex">
                                            <span class="label-text">DATA</span>
                                            <span class="label-value"><span id="data-amount-single-card">1.5</span>&nbsp;<span id="data-amount-unit-single-card">GB/ Day</span></span>
                                        </div>
                                    </div>
                                    <div class="center-recharge-button">
                                        <button type="button" class="read_more_btn" id="recharge-btn-single-card">Update</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="accordion-item plan-data-items card-container-clone d-none">
                    <h2 class="accordion-header">
                        <div class="accordion-button accordion-button-custom collapsed accordion-btn unique-aria-controls" type="button" onclick="getId(this)" data-bs-toggle="collapse"  aria-expanded="false" aria-controls="card-body-1">
                            <div class="category-name-view-flex">
                                <div class="category-name-card " id="category-name-id"><span class="category-name-multiple-card">Daily Refreshing Plans</span>&nbsp;<span class="plan-count-single-category"></span></div>
                                <div class="view-more-icon-flex" id="view-more-plans-toggle">
                                    <div class="view-all-card-text">View More</div>
                                    <img src="<c:url value='/resources/icons/chevron-down.svg'/>" class="chevron-arrows">
                                </div>
                            </div>
                        </div>
                    </h2>
                    <div id="card-body-1" class="accordion-collapse collapse show change-accordion-id"   data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                            <div class="section_our_solution">
                                <div class="row recharge-cards-row multiple-cards-plan-body">


                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="accordion accordion-item-container always-open  accordion-flush mt-3 plan-category-container" id="accordionFlushExample">




                </div>

                <!-- Modal -->
                <div class="modal fade" id="filterModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                        <div class="modal-content">

                            <div class="modal-body">
                                <div class="main-modal-container">
                                    <div class="modal-filter-icon-flex">
                                        <img src="<c:url value='/resources/icons/filter-filled-animated.svg'/>" class="modal-filter-icon">
                                        <span class="modal-filter-plans">Filter Plans</span>
                                    </div>

                                    <div class="accordion accordion-flush mt-4" id="accordionFlushExample1">
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                                    Daily Refreshing Plans
                                                </button>
                                            </h2>
                                            <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample1">
                                                <div class="accordion-body">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-refreshing-data filter-checkbox">
                                                                    <span></span>
                                                                    1 GB/Day
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-refreshing-data filter-checkbox">
                                                                    <span></span>
                                                                    1.5 GB/Day
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-refreshing-data filter-checkbox" >
                                                                    <span></span>
                                                                    2 GB/Day
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-refreshing-data filter-checkbox">
                                                                    <span></span>
                                                                    2.5 GB/Day
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-refreshing-data filter-checkbox">
                                                                    <span></span>
                                                                    3 GB/Day
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFour" aria-expanded="false" aria-controls="flush-collapseFour">
                                                    Fixed Data Plans
                                                </button>
                                            </h2>
                                            <div id="flush-collapseFour" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample1">
                                                <div class="accordion-body">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="justify-col col-12 col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-fixed-data filter-checkbox">
                                                                    <span></span>
                                                                    100 - 200 GB Data
                                                                </label>
                                                            </div>
                                                            <div class="justify-col col-12 col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-fixed-data filter-checkbox">
                                                                    <span></span>
                                                                    200 - 300 GB Data
                                                                </label>
                                                            </div>
                                                            <div class="justify-col col-12 col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-fixed-data filter-checkbox">
                                                                    <span></span>
                                                                    300 GB Data & More
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                                                    Charges
                                                </button>
                                            </h2>
                                            <div id="flush-collapseTwo" class="accordion-collapse collapse charge-row" data-bs-parent="#accordionFlushExample1">
                                                <div class="accordion-body">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-charges filter-checkbox">
                                                                    <span></span>
                                                                    <div class="only-amount-flex">
                                                                        <div class="amount-rs-icon-flex">
                                                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                            <div>020</div>
                                                                        </div> <div>&nbsp-&nbsp</div>
                                                                        <div class="amount-rs-icon-flex">
                                                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                            <div>300</div>
                                                                        </div>
                                                                    </div>

                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-charges filter-checkbox">
                                                                    <span></span>
                                                                    <div class="only-amount-flex">
                                                                        <div class="amount-rs-icon-flex">
                                                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                            <div>301</div>
                                                                        </div> <div>&nbsp-&nbsp</div>
                                                                        <div class="amount-rs-icon-flex">
                                                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                            <div>600</div>
                                                                        </div>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-charges filter-checkbox">
                                                                    <span></span>
                                                                    <div class="only-amount-flex">
                                                                        <div class="amount-rs-icon-flex">
                                                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                            <div>601</div>
                                                                        </div> <div>&nbsp-&nbsp</div>
                                                                        <div class="amount-rs-icon-flex">
                                                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                            <div>900</div>
                                                                        </div>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-charges filter-checkbox">
                                                                    <span></span>
                                                                    <div class="only-amount-flex">
                                                                        <div class="amount-rs-icon-flex">
                                                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                            <div>900</div>
                                                                        </div> <div>&nbsp&&nbsp</div>
                                                                        <div class="amount-rs-icon-flex">
                                                                            <div>above</div>
                                                                        </div>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                                                    Validity
                                                </button>
                                            </h2>
                                            <div id="flush-collapseThree" class="accordion-collapse collapse validity-row"  data-bs-parent="#accordionFlushExample1">
                                                <div class="accordion-body">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-validity filter-checkbox">
                                                                    <span></span>
                                                                    Upto 30 Days
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-validity filter-checkbox">
                                                                    <span></span>
                                                                    31 - 60 Days
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-validity filter-checkbox">
                                                                    <span></span>
                                                                    61 Days and more
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-validity filter-checkbox">
                                                                    <span></span>
                                                                    Annual Plans
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFive" aria-expanded="false" aria-controls="flush-collapseFive">
                                                    Add On Data Packs
                                                </button>
                                            </h2>
                                            <div id="flush-collapseFive" class="accordion-collapse collapse validity-row"  data-bs-parent="#accordionFlushExample1">
                                                <div class="accordion-body">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-add-on-data filter-checkbox">
                                                                    <span></span>
                                                                    1 - 25 GB
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-add-on-data filter-checkbox">
                                                                    <span></span>
                                                                    25 - 50 GB
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-add-on-data filter-checkbox">
                                                                    <span></span>
                                                                    50 - 100 GB
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-add-on-data filter-checkbox">
                                                                    <span></span>
                                                                    100 GB & more
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseSix" aria-expanded="false" aria-controls="flush-collapseSix">
                                                    International Roaming Plans
                                                </button>
                                            </h2>
                                            <div id="flush-collapseSix" class="accordion-collapse collapse validity-row"  data-bs-parent="#accordionFlushExample1">
                                                <div class="accordion-body">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-roaming-data filter-checkbox">
                                                                    <span></span>
                                                                    Only Data Pack
                                                                </label>
                                                            </div>
                                                            <div class="col-xs col-sm-6">
                                                                <label class="checkbox-btn">
                                                                    <input type="checkbox" class="filter-on-roaming-data filter-checkbox">
                                                                    <span></span>
                                                                    Voice and Data Pack
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary apply-filter-btn" onclick="applyFilter(true)" data-bs-dismiss="modal">Apply Filter</button>
                                <button type="button" class="btn btn-primary clear-filter-btn" onclick="clearFilter()" data-bs-dismiss="modal">Clear Filter</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#view-plan-modal">
                    View Plan
                </button>

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
                                </div>
                            </div>
                            <div class="modal-footer view-plan-modal">
                                <button class="modal-update-button show-update-modal">Update</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#recharge-plan">
                    Recharge Now
                </button>

                <!-- Modal -->
                <div class="modal recharge-plan-modal fade fade-scale in" id="recharge-plan" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog recharge-plan-modal-dialog modal-dialog-centered modal-dialog-scrollable">
                        <div class="modal-content recharge-plan-modal-content">
                            <div class="modal-header recharge-plan-modal-header">
                                <h1 class="modal-title recharge-plan-modal-title fs-5" id="staticBackdropLabel">Recharge Details</h1>
                                <button type="button" class="btn-close recharge-modal-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                                                    <span class="verify-code" onclick="verifyCuponWalletChanges()">Apply</span>
                                                    <span class="d-none remove-cupon" onclick="removeCuponCode()">Remove</span>
                                                </div>
                                                <span class="cupon-code-error d-none"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="recharge-summary-flex">
                                    <%--                            <div class="recharge-summary-top">--%>
                                    <%--                                <div class="recharge-summary-title">Recharge Summary</div>--%>
                                    <%--                            </div>--%>
                                    <div class="summary-details">
                                        <div class="summary-row-flex">
                                            <input type="text" class="plan-id-field" hidden="hidden">
                                            <div class="summary-title">Plan Amount</div>
                                            <div class="summary-value-flex">
                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                                <div class="summary-value" id="recahrge-plan-price-id">569.00</div>
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
                                                    <input type="text" class="amount-input recahrge-wallet-amount-input" onblur="verifyCuponWalletChanges()" placeholder="Amount"/>
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


                            <div class="modal-footer recharge-plan-modal-footer">
                                <button type="button" class="pay-now" data-bs-dismiss="modal" onclick="buySelectedRechargePlan()">Pay <span class="final-pay-amount-btn">666.00</span></button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="toast toast-failure">

                    <div class="toast-content toast-failure-content">
                        <i class='bx bx-error icon' ></i>

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

                <div class="modal fade" id="confirm-postpaid-recharge" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content confirm-postpaid-recharge-service-main-content">
                            <div class="modal-header confirm-postpaid-recharge-service-main-header">
                                <header class="roaming-toggle-service confirm-postpaid-recharge-service">
                                    <span class="postpaid-recharge-type-text">Buy Plan</span>
                                    <div class="close close-confirm-postpaid-recharge-modal"><i class="uil uil-times"></i></div>
                                </header>
                            </div>
                            <div class="modal-body">
                                <div class="content">
                                    <p>Buy Selected Postpaid Plan? You can only buy one postpaid plan at a time.</p>
                                    <div class="button-div">
                                        <button id="confirmRechargeButton" class="mt-4" onclick="buyPostPaidPlan()">Buy Plan</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div> <%--  added this at last --%>



            <div class="modal fade" id="statusErrorsModal" tabindex="-1" role="dialog" data-bs-keyboard="false">
                <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-body text-center p-lg-4">
                            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
                                <circle class="path circle" fill="none" stroke="#db3646" stroke-width="6" stroke-miterlimit="10" cx="65.1" cy="65.1" r="62.1" />
                                <line class="path line" fill="none" stroke="#db3646" stroke-width="6" stroke-linecap="round" stroke-miterlimit="10" x1="34.4" y1="37.9" x2="95.8" y2="92.3" />
                                <line class="path line" fill="none" stroke="#db3646" stroke-width="6" stroke-linecap="round" stroke-miterlimit="10" x1="95.8" y1="38" X2="34.4" y2="92.2" />
                            </svg>
                            <h4 class="text-danger failure-message-heading mt-3">Recharge failed!</h4>
                            <p class="mt-3 failure-message-modal recharge-failure-reason">Please check the entered details and try again.</p>
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
                            <h4 class="text-success success-message-heading-modal  mt-3">Recharge done successfully!</h4>
                            <p class="mt-3 success-message-modal d-none">Please check the entered details and try again.</p>
                            <button type="button" class="btn btn-sm mt-3 btn-success" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="create-new-coupon" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header new-plan-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Create new plan</h1>
                            <button type="button" class="btn-close new-plan-close-btn" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body new-plan-body">
                            <form id="newPlanForm" name="newPlanForm">
                                <div class="col-12">
                                    <div class="search-box">
                                        <div class="form-group">
                                            <input type="text" placeholder=" " name="couponName" id="couponName">
                                            <label for="couponName">Coupon Name</label>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer new-plan-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Understood</button>
                        </div>
                    </div>
                </div>
            </div>

        </main>


    </div>
    <!-- Swiper JS -->
    <script src="//cdn.jsdelivr.net/gh/freeps2/a7rarpress@main/swiper-bundle.min.js"></script>

    <!-- JavaScript -->
    <!--Uncomment this line-->
    <script src="<c:url value='/resources/js/edit-plan-page.js' />"></script>
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
    <script src="https://www.gstatic.com/charts/loader.js"></script>
</body>
</html>
