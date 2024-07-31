<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 29-07-2024
  Time: 22:53
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
    <link rel="stylesheet" href="<c:url value='/resources/css/pagination.css' />">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro:400,600&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value='/resources/fonts/icomoon/style.css' />">

    <link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.min.css' />">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <!-- Style -->
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/users-details.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/toaster-1.css' />">
</head>

<body class="show-sidebar body" onload="getFilteredUsersData(true)">
<%@include file="admin-navbar.jsp" %>
<div class="outermost-contianer ">
    <%@include file="admin-sidebar.jsp" %>
    <div class="custom-background"></div>
    <main>
        <div class="container-fluid user-details-container">  <!-- Change inside content and class-->

            <div class="row mt-4">
                <div class="col-12">
                    <div class="common-dashboard-white-card full-height">
                        <div class="card-heading">User Requests</div>
                        <div class="button-type-flex">
                            <button class="common-btn-type active-btn-type" data-value="1" onclick="changeActiveRequestType(this)">Register Users</button>
                            <button class="common-btn-type" data-value="2" onclick="changeActiveRequestType(this)">Sign Up Users</button>
                        </div>

                        <div class="row mt-3">
                            <div class="col-12 col-md-6">
                                <div class="search-box">
                                    <i class="bx bx-search-alt"></i>
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="emailId" id="emailId" onblur="getFilteredUsersData(true)">
                                        <label for="emailId">Search by email</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-md-6 status-type-search">
                                <div class="search-box">
                                    <div class="form-group">
                                        <select id="statusType" onchange="getFilteredUsersData(true)">
                                            <option hidden="hidden" value="1">Search by status</option>
                                            <option value="1">All</option>
                                            <option value="2">Registered</option>
                                            <option value="3">Pending</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="table-responsive mt-2 show-table-data table-outer-class">
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th scope="col" class="th-class-color">User Name</th>
                                    <th scope="col" class="th-class-color">Mobile</th>
                                    <th scope="col" class="th-class-color">Email</th>
                                    <th scope="col" class="th-class-color text-center status-column-common">Status</th>
                                    <th scope="col" class="th-class-color text-center">Action</th>
                                </tr>
                                </thead>

                                <tr class="clone-tr d-none">
                                    <td class="tr-name">Mark Zukerberg</td>
                                    <td class="tr-number">9999999999</td>
                                    <td class="tr-email">kavitshah324@gmail.com</td>
                                    <td class="status-column-common">
                                        <div class="new-btn-green table-btn-font">
                                            NEW
                                        </div>
                                        <div class="reupload-btn-yellow table-btn-font">
                                            REUPLOADED
                                        </div>
                                    </td>
                                    <td class="action-class">
                                        <div class="action-btn-tb contact-user table-btn-font">
                                            CONTACT USER
                                        </div>
                                        <button class="action-btn-tb table-btn-font dropdown-common-action dropdown-toggle no-caret"
                                                type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            ACTIONS
                                        </button>
                                        <ul class="dropdown-menu dropdown-common-action">
                                            <li><a class="dropdown-item accept-btn-tb" >VIEW DOCUMENTS</a></li>
                                            <li><a class="dropdown-item reject-btn-tb">CONTACT USER</a></li>
                                        </ul>
                                    </td>
                                </tr>

                                <tbody class="request-table-body">

                                </tbody>
                            </table>
                        </div>

                        <div class="accordion-item accordion-clone-item d-none accordion-request mt-2">
                            <h2 class="accordion-header">
                                <button class="accordion-button change-accordion-target accordion-custom-clr collapsed" type="button"
                                        data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo"
                                        aria-expanded="false" aria-controls="flush-collapseOne">
                                    Kavit Shah
                                </button>
                            </h2>
                            <div id="flush-collapseTwo" class="accordion-collapse collapse change-accordion-id"
                                 data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body accordion-body-class">
                                    <div class="accordion-body-flex">
                                        <div class="accordion-single-item-flex">
                                            <div>Email :</div>
                                            <div class="accordion-email">kavitshah2101@gmail.com</div>
                                        </div>

                                        <div class="accordion-single-item-flex mt-1">
                                            <div>Contact :</div>
                                            <div class="accordion-number">9999999999</div>
                                        </div>

                                        <div class="accordion-single-item-flex mt-1 status-column-common">
                                            <div>Status :</div>
                                            <div class="reupload-btn-yellow table-btn-font">
                                                REUPLOADED
                                            </div>
                                            <div class="new-btn-green table-btn-font extra-width-new-btn-accordion">
                                                NEW
                                            </div>
                                        </div>
                                        <div class="accordion-action-btn-flex mt-4">
                                            <button class="action-btn-tb action-btn-extra-width-accordion view-doc-btn-accordion">View
                                                Documents
                                            </button>
                                            <button class="action-btn-tb extra-width-new-btn-accordion accept-btn-accordion">Accept
                                            </button>
                                            <button class="delete-request-btn-tb extra-width-new-btn-accordion reject-btn-accordion">
                                                Reject
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="accordion accordion-flush accordion-common-body" id="accordionFlushExample">

                        </div>

                        <div class="pagination-page-size-flex">
                            <select name="page-size" id="page-size-id" onchange="getFilteredUsersData(true)">
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


                        <div class="no-data-div mt-4 d-none">
                            <img src="/uninor/resources/images/kavit.png" class="no-data-found-img">
                            <span class="no-data-text">NO REQUEST AVAILABLE!</span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal -->
            <div class="modal recharge-plan-modal fade fade-scale in" id="recharge-plan" data-bs-backdrop="static"
                 data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog recharge-plan-modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content recharge-plan-modal-content">
                        <div class="modal-header recharge-plan-modal-header">
                            <h1 class="modal-title recharge-plan-modal-title fs-5" id="staticBackdropLabel">Recharge
                                Details</h1>
                            <button type="button" class="btn-close recharge-modal-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body recharge-plan-modal-body">

                            <div class="accordion accordion-flush" id="accordion-plan-details">
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button plan-details-view-btn custom-icon collapsed"
                                                type="button" data-bs-toggle="collapse"
                                                data-bs-target="#recharge-modal-plan-details" aria-expanded="false"
                                                aria-controls="flush-collapseOne">
                                            Plan Details
                                        </button>
                                    </h2>
                                    <div id="recharge-modal-plan-details" class="accordion-collapse collapse"
                                         data-bs-parent="#accordion-plan-details">
                                        <div class="accordion-body">
                                            <div class="table-responsive">
                                                <table class="table table-bordered">
                                                    <tbody>

                                                    <tr>
                                                        <td>Pack Validity</td>
                                                        <td><span class="recharge-modal-validity">84</span>&nbsp;<span
                                                                class="recharge-modal-days">days</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Total Data</td>
                                                        <td class="recharge-modal-total-data">126 GB</td>
                                                    </tr>
                                                    <tr>
                                                        <td><span class="relative-class">Data at high speed<span
                                                                class="astreik-class">*</span></span></td>
                                                        <td><span
                                                                class="recharge-modal-data-amount">84</span>&nbsp;<span
                                                                class="recharge-modal-refresh">GB/Day</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Voice Call</td>
                                                        <td class="recharge-modal-voice-amount">Unlimited</td>
                                                    </tr>
                                                    <tr>
                                                        <td>SMS</td>
                                                        <td><span
                                                                class="recharge-modal-sms-amount">100</span>&nbsp;<span
                                                                class="recharge-modal-sms-refresh">SMS/Day</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td><span class="relative-class">Additional Data<span
                                                                class="astreik-class">*</span></span></td>
                                                        <td><span
                                                                class="recharge-modal-additional-data-amount">10</span>
                                                            GB
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button plan-details-view-btn custom-icon collapsed"
                                                type="button" data-bs-toggle="collapse" data-bs-target="#cupon-code"
                                                aria-expanded="false" aria-controls="flush-collapseOne">
                                            Add Cupon Code <span class="applied-btn d-none">Applied</span>
                                        </button>
                                    </h2>
                                    <div id="cupon-code" class="accordion-collapse collapse"
                                         data-bs-parent="#accordion-plan-details">
                                        <div class="accordion-body">
                                            <div class="cupon-text-code-flex">
                                                <div class="add-cupon-text">Cupon Code :</div>
                                                <input type="text" class="cupon-code-input cupon-code-recharge-tab"
                                                       onblur="validateCuponCode()">
                                                <span class="verify-code"
                                                      onclick="verifyCuponWalletChanges()">Apply</span>
                                                <span class="d-none remove-cupon"
                                                      onclick="removeCuponCode()">Remove</span>
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
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>"
                                                 class="summary-rs-icon">
                                            <div class="summary-value" id="recahrge-plan-price-id">569.00</div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex">
                                        <div class="summary-title">Discount Applied</div>
                                        <div class="summary-value-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>"
                                                 class="summary-rs-icon">
                                            <div class="summary-value" id="recharge-discount-applied">25.05</div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex">
                                        <div class="summary-title">Tax Amount</div>
                                        <div class="summary-value-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>"
                                                 class="summary-rs-icon">
                                            <div class="summary-value" id="tax-amount-applied">38.50</div>
                                        </div>
                                    </div>

                                    <hr class="summary-recharge-hr">

                                    <div class="summary-row-flex final-price-flex">
                                        <div class="summary-title">Total Amount</div>
                                        <div class="summary-value-flex ">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>"
                                                 class="summary-rs-icon">
                                            <div class="summary-value" id="recharge-total-amount">630.50</div>
                                        </div>
                                    </div>


                                    <div class="wallet-card">
                                        <div class="wallet-details-flex">
                                            <div class="wallet-left-side">
                                                <img src="<c:url value='/resources/icons/wallet.svg'/>"
                                                     class="wallet-wallet-icon">
                                                <div class="wallet-balance-flex">
                                                    <span class="avaiable-amount-text">Amount</span>
                                                    <span class="wallet-total-amount-span">
                                                <img src="<c:url value='/resources/icons/rupees.svg'/>"
                                                     class="wallet-rs-icon">
                                                <span class="wallet-amount" id="recharge-wallet-amount">500</span>
                                            </span>
                                                </div>
                                            </div>
                                            <div class="wallet-right-side">
                                                <img src="<c:url value='/resources/icons/i-icon-animated-red.svg'/>"
                                                     class="i-icon-recahrge d-none" data-toggle="tooltip"
                                                     title="Invalid balance amount!">
                                                <img src="<c:url value='/resources/icons/rupees.svg'/>"
                                                     class="input-rs-icon">
                                                <input type="text" class="amount-input recahrge-wallet-amount-input"
                                                       onblur="verifyCuponWalletChanges()" placeholder="Amount"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="summary-row-flex final-price-div">
                                        <div class="summary-title">Payable Amount <img
                                                src="<c:url value='/resources/icons/i-icon-animated-black.svg'/>"
                                                class="i-icon-recahrge" data-toggle="tooltip"
                                                title="Minimum transcation of 10 Rs required."></div>
                                        <div class="summary-value-flex">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>"
                                                 class="summary-rs-icon">
                                            <div class="summary-value" id="recharge-payable-amount">38.50</div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>


                        <div class="modal-footer recharge-plan-modal-footer">
                            <button type="button" class="pay-now" data-bs-dismiss="modal"
                                    onclick="buySelectedRechargePlan()">Pay <span
                                    class="final-pay-amount-btn">666.00</span></button>
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

        </div>
        <%--  added this at last --%>


        <div class="modal fade" id="statusErrorsModal" tabindex="-1" role="dialog" data-bs-keyboard="false">
            <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body text-center p-lg-4">
                        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
                            <circle class="path circle" fill="none" stroke="#db3646" stroke-width="6"
                                    stroke-miterlimit="10" cx="65.1" cy="65.1" r="62.1"/>
                            <line class="path line" fill="none" stroke="#db3646" stroke-width="6" stroke-linecap="round"
                                  stroke-miterlimit="10" x1="34.4" y1="37.9" x2="95.8" y2="92.3"/>
                            <line class="path line" fill="none" stroke="#db3646" stroke-width="6" stroke-linecap="round"
                                  stroke-miterlimit="10" x1="95.8" y1="38" X2="34.4" y2="92.2"/>
                        </svg>
                        <h4 class="text-danger failure-message-heading mt-3">Recharge failed!</h4>
                        <p class="mt-3 failure-message-modal recharge-failure-reason">Please check the entered details
                            and try again.</p>
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
                            <circle class="path circle" fill="none" stroke="#198754" stroke-width="6"
                                    stroke-miterlimit="10" cx="65.1" cy="65.1" r="62.1"/>
                            <polyline class="path check" fill="none" stroke="#198754" stroke-width="6"
                                      stroke-linecap="round" stroke-miterlimit="10"
                                      points="100.2,40.2 51.5,88.8 29.8,67.5 "/>
                        </svg>
                        <h4 class="text-success success-message-heading-modal  mt-3">Recharge done successfully!</h4>
                        <p class="mt-3 success-message-modal d-none">Please check the entered details and try again.</p>
                        <button type="button" class="btn btn-sm mt-3 btn-success" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

    </main>


</div>


<!-- JavaScript -->
<!--Uncomment this line-->
<script src="<c:url value='/resources/js/users-details.js' />"></script>
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
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
</body>
</html>
