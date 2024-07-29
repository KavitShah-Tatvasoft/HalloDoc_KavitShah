<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 26-07-2024
  Time: 00:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <title>Edit Coupon Page</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/pagination.css' />">
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
    <link rel="stylesheet" href="<c:url value='/resources/css/edit-coupon-page.css' />">
</head>
<body class="show-sidebar body" onload="getAllCouponData()">
<%@include file="admin-navbar.jsp" %>
<div class="outermost-contianer ">
    <%@include file="admin-sidebar.jsp" %>
    <div class="custom-background"></div>
    <main>
        <div class="container-fluid  coupon-container">  <!-- Change inside content and class-->
            <div class="row ">
                <div class="col-12 col-sm-6 col-md-4">
                    <div class="search-box">
                        <i class="bx bx-search-alt"></i>
                        <div class="form-group">
                            <select name="coupon-type" id="coupon-type" onchange="getAllCouponData(true)">
                                <c:forEach items="${cuponCategories}" var="categories">
                                    <option value=${categories.cuponCategoryId}>${categories.cuponCategory}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="heading-add-coupon-flex">
                    <div class="available-coupon-heading">Available Coupons</div>
                    <button class="add-new-coupon-btn" onclick="createNewCoupon()"><span class="add-coupon-txt">Add Coupons</span><img
                            class="plus-image-coupon" src="<c:url value='/resources/icons/plus-animated.svg' />"/>
                    </button>
                </div>
            </div>
            <div class="col-12 col-sm-6 col-md-4 coupon-col clone-col mt-3 d-none">
                <div class="coupon">
                    <div class="left">
                        <div class="coupon-type-text">Discount Coupon</div>
                    </div>
                    <div class="center">
                        <div>
                            <h2 class="reward-amount-text">50% OFF</h2>
                            <h3 class="coupon-only-text">Coupon</h3>
                            <div class="max-amt-flex">
                                <div class="max-amount-div"><span>Upto Rs</span>&nbsp;<span
                                        class="max-amount-span"></span></div>
                            </div>
                            <div class="redeem-btn-flex">
                                <div class="redeem-btn update-coupon">Update</div>
                            </div>
                        </div>
                    </div>

                    <div class="right">
                        <div class="coupon-availability"></div>
                    </div>

                </div>
            </div>

            <div class="row coupon-row main-coupon-row mt-4">

            </div>

            <footer class="mt-4">
                <div class="pagination-page-size-flex">
                    <select name="page-size" id="page-size-id" onchange="getAllCouponData(true)">
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                    </select>
                    <div class="pagination show-table-data">
                        <button id="previous" onclick="previousButton()">Prev</button>
                        <ul id="pageList"></ul>
                        <button id="next" onclick="nextButton()">Next</button>
                    </div>
                </div>
            </footer>
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

    <div class="modal fade" id="couponModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form name="create-update-form" id="create-update-form">
                    <div class="modal-header coupon-header">
                        <h1 class="modal-title fs-5 coupon-header-title" id="staticBackdropLabel"></h1>
                        <button type="button" class="btn-close coupon-close-button" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12">
                                <div class="search-box">
                                    <div class="form-group">
                                        <select name="couponType" id="couponType" onchange="showCorrespondingRewardInput()">
                                            <option value="0" hidden="hidden">Select Coupon Type</option>
                                            <c:forEach items="${cuponCategories}" var="categories">
                                                <option value=${categories.cuponCategoryId}>${categories.cuponCategory}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="couponName" id="couponName">
                                        <label for="couponName">Coupon Name</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 percentage-col">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="rewardAmountPercentage" id="rewardAmountPercentage">
                                        <label for="rewardAmountPercentage">Reward Percentage(%)</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 mb-col d-none">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="rewardAmountMB" id="rewardAmountMB">
                                        <label for="rewardAmountMB">Reward Data Amount(MB)</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="validityPeriod" id="validityPeriod">
                                        <label for="validityPeriod">Validity Period(Days)</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 max-reward-col">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="maximumReward" id="maximumReward">
                                        <label for="maximumReward">Maximum Rewards</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="search-box">
                                    <div class="form-group">
                                        <textarea id="couponDescription" name="couponDescription" placeholder=" "></textarea>
                                        <label for="couponDescription">Coupon Description</label>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="modal-footer coupon-footer">
                        <button type="button" class="common-btn-coupon-modal coupon-class coupon-available-btn"
                                onclick="toggleStatus('coupon',this)">Coupon Available
                        </button>
                        <button type="submit" class="btn btn-primary theme-background update-create-btn">Create
                        </button>
                        <button type="reset" class="btn btn-primary theme-background">Reset</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="updateCouponModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form name="update-coupon-form" id="update-coupon-form">
                    <div class="modal-header update-coupon-header">
                        <h1 class="modal-title fs-5 update-coupon-header-title" id="staticBackdropLabel1">Update Coupon</h1>
                        <button type="button" class="btn-close update-coupon-close-button" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">

                            <div class="col-12">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="couponNameUpdate" id="couponNameUpdate">
                                        <label for="couponNameUpdate">Coupon Name</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 percentage-col">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="rewardAmountPercentageUpdate" id="rewardAmountPercentageUpdate">
                                        <label for="rewardAmountPercentageUpdate">Reward Percentage(%)</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 mb-col">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="rewardAmountMBUpdate" id="rewardAmountMBUpdate">
                                        <label for="rewardAmountMBUpdate">Reward Percentage(MB)</label>
                                    </div>
                                </div>
                            </div>


                            <div class="col-12">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="validityPeriodUpdate" id="validityPeriodUpdate">
                                        <label for="validityPeriodUpdate">Validity Period(Days)</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 max-reward-col">
                                <div class="search-box">
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="maximumRewardUpdate" id="maximumRewardUpdate">
                                        <label for="maximumRewardUpdate">Maximum Rewards</label>
                                    </div>
                                </div>
                            </div>

                            <input type="hidden" name="hidden-update-coupon-id" id="hidden-update-coupon-id">
                            <input type="hidden" name="hidden-coupon-type-id" id="hidden-coupon-type-id">
                            <div class="col-12">
                                <div class="search-box">
                                    <div class="form-group">
                                        <textarea id="couponDescriptionUpdate" name="couponDescriptionUpdate" placeholder=" "></textarea>
                                        <label for="couponDescriptionUpdate">Coupon Description</label>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="modal-footer update-coupon-footer">
                        <button type="button" class="common-btn-coupon-modal coupon-class coupon-available-btn"
                                onclick="toggleStatus('coupon',this)">Coupon Available
                        </button>
                        <button type="submit" class="btn btn-primary theme-background update-create-btn">Update
                        </button>
                        <button type="button" class="btn btn-primary reset-button-update theme-background">Reset</button>
                    </div>
                </form>
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
<script src="<c:url value='/resources/js/edit-coupon-page.js' />"></script>
</body>
</html>