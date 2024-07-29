<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 22-07-2024
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
    <title>Admin Dashboard</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/admin-dashboard.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/notification-tab.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/toaster-1.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/loader.css' />">

    <!-- Style -->
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body class="show-sidebar body" onload="getAdminDashboardData(), getFilteredRequestData(true)">
<div class="loader-container">
    <div class="loader"></div>
</div>
<header>
    <%@include file="admin-navbar.jsp" %>
</header>

<div class="outermost-contianer ">
    <%@include file="admin-sidebar.jsp" %>
    <div class="custom-background"></div>
    <main>
        <div class="container-fluid dashboard-container">

            <div class="row">
                <div class="col-12 col-sm-6 col-md-4">
                    <div class="common-dashboard-card">
                        <div class="registered-user-icon-flex">
                            <img src="/uninor/resources/icons/person-filled-animated.svg" class="user-icon profile-pic">
                            <span>Registered Users</span>
                        </div>
                        <div class="details-value-class registered-user-count">

                        </div>
                    </div>
                </div>

                <div class="col-12 col-sm-6 col-md-4">
                    <div class="common-dashboard-card">
                        <div class="registered-user-icon-flex">
                            <img src="/uninor/resources/icons/person-filled-animated.svg" class="user-icon profile-pic">
                            <span>Signup User</span>
                        </div>
                        <div class="details-value-class signed-up-user-count">

                        </div>
                    </div>
                </div>

                <div class="col-12 col-sm-6 col-md-4">
                    <div class="common-dashboard-card">
                        <div class="registered-user-icon-flex">
                            <img src="/uninor/resources/icons/rupees.svg" class="user-icon profile-pic">
                            <span>Total Revenue</span>
                        </div>
                        <div class="details-value-class total-revenue-generated">

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
                    <div class="chart-background-class line-chart-height">
                        <div class="chart-heading">Daily Usage</div>
                        <canvas id="myChart1"></canvas>
                    </div>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-12">
                    <div class="common-dashboard-white-card full-height">
                        <div class="card-heading">User Requests</div>
                        <div class="button-type-flex">
                            <button class="common-btn-type active-btn-type" data-value="1" onclick="changeActiveRequestType(this)">Activation</button>
                            <button class="common-btn-type" data-value="2" onclick="changeActiveRequestType(this)">Deactivation</button>
                            <button class="common-btn-type" data-value="3" onclick="changeActiveRequestType(this)">Block</button>
                            <button class="common-btn-type" data-value="4" onclick="changeActiveRequestType(this)">Unblock</button>
                        </div>

                        <div class="row mt-3">
                            <div class="col-12 col-md-6">
                                <div class="search-box">
                                    <i class="bx bx-search-alt"></i>
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="emailId" id="emailId" onblur="getFilteredRequestData(true)">
                                        <label for="emailId">Search by email</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="search-box">
                                    <i class="bx bx-search-alt"></i>
                                    <div class="form-group">
                                        <input type="text" placeholder=" " name="mobileNumber" id="mobileNumber" onblur="getFilteredRequestData(true)">
                                        <label for="mobileNumber">Search by mobile</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-md-6 status-type-search">
                                <div class="search-box">
                                    <div class="form-group">
                                        <select id="statusType" onchange="getFilteredRequestData(true)">
                                            <option hidden="hidden" value="0">Search by status</option>
                                            <option value="1">All</option>
                                            <option value="2">New</option>
                                            <option value="3">Reuploaded</option>
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
                                        <div class="action-btn-tb view-document-btn table-btn-font">
                                            VIEW DOCUMENTS
                                        </div>
                                        <button class="action-btn-tb table-btn-font dropdown-common-action dropdown-toggle no-caret"
                                                type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            ACTIONS
                                        </button>
                                        <ul class="dropdown-menu dropdown-common-action">
                                            <li><a class="dropdown-item accept-btn-tb" >ACCEPT</a></li>
                                            <li><a class="dropdown-item reject-btn-tb">REJECT</a></li>
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
                            <select name="page-size" id="page-size-id" onchange="getFilteredRequestData(true)">
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

            <div class="modal fade" id="verify-documents-modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog  modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header approve-docs-heading">
                            <h1 class="modal-title approve-docs-heading fs-5" id="staticBackdropLabel">Approve Documents</h1>
                            <button type="button" class="btn-close approve-docs-btn-close" onclick="removeValidationError()" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="type-data-value-flex">
                                <div class="font-600">Documents for </div>
                                <div class="font-600 view-doc-name-div">Kavit Shah</div>
                            </div>
                            <div class="doc-name-btn-flex">
                                <div>BIRTH DATE</div>
                                <div class="approve-doc-bday view-doc-bday-div">15-01-2003</div>
                            </div>
                            <div class="doc-name-btn-flex">
                                <div>AADHAR NUMBER</div>
                                <div class="approve-doc-bday view-doc-aadhar-numb-div">15-01-2003</div>
                            </div>
                            <div class="doc-name-btn-flex mt-1">
                                <div>PAN NUMBER</div>
                                <div class="approve-doc-bday view-doc-pan-numb-div">15-01-2003</div>
                            </div>
                            <div class="doc-name-btn-flex mt-2">
                                <div class="addhar-text-approve">AADHAR CARD</div>
                                <div class="btn-flex-view-docs">
                                    <div class="view-docs-modal-buttons view-aadhar-path-class"><img src="/uninor/resources/icons/eye-white.svg" class="modal-btn-icon"></div>
                                    <div class="view-docs-modal-buttons accept-aadhar-btn-doc-validated" onclick="setDocumentStatus('aadhar', 'accepted')"><img src="/uninor/resources/icons/tick-white.svg" class="modal-btn-icon"></div>
                                    <div class="view-docs-modal-buttons reject-aadhar-btn-doc-validated" onclick="setDocumentStatus('aadhar', 'rejected')"><img src="/uninor/resources/icons/cross-white.svg" class="modal-btn-icon"></div>
                                </div>
                            </div>
                            <div class="doc-name-btn-flex mt-2">
                                <div class="addhar-text-approve">PAN CARD</div>
                                <div class="btn-flex-view-docs">
                                    <div class="view-docs-modal-buttons view-pan-path-class"><img src="/uninor/resources/icons/eye-white.svg" class="modal-btn-icon"></div>
                                    <div class="view-docs-modal-buttons accept-pan-btn-doc-validated " onclick="setDocumentStatus('pan', 'accepted')"><img src="/uninor/resources/icons/tick-white.svg" class="modal-btn-icon"></div>
                                    <div class="view-docs-modal-buttons reject-pan-btn-doc-validated" onclick="setDocumentStatus('pan', 'rejected')"><img src="/uninor/resources/icons/cross-white.svg" class="modal-btn-icon"></div>
                                </div>
                            </div>
                            <div class="view-doc-modal-note-flex d-none">
                                <div class="note-modal-doc-view">* Please verify both documents to continue</div>
                            </div>
                            <input type="hidden" name="requestId" id="hidden-request-id">
                        </div>
                        <div class="modal-footer approve-docs-footer">
                            <button type="button" class="btn btn-primary" onclick="approveVerifiedDocuments()">Approve Documents</button>
                        </div>
                    </div>
                </div>
            </div>


            <div class="modal fade" id="toggle-roaming-service" data-bs-backdrop="static" data-bs-keyboard="false"
                 tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
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
                                <p>Are you sure you want to switch &nbsp;<span class="roaming-type-toggle">on</span>&nbsp;roaming?
                                    It will deactivate your current plan till you <span class="roaming-status-text">deactivate</span>&nbsp;roaming!
                                </p>
                                <input type="text" class="roaming-confirm-input"
                                       placeholder="Enter confirm to continue"/>
                                <span class="confirm-error-text d-none">Please type confirm correctly to continue</span>
                                <div class="button-div">
                                    <button id="confirmRoamingSwitchButton" class="mt-4"
                                            onclick="changeRoamingStatus()">Activate Roaming
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>


            <div class="modal fade" id="statusErrorsModal" tabindex="-1" role="dialog" data-bs-keyboard="false">
                <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-body text-center p-lg-4">
                            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
                                <circle class="path circle" fill="none" stroke="#db3646" stroke-width="6"
                                        stroke-miterlimit="10" cx="65.1" cy="65.1" r="62.1"/>
                                <line class="path line" fill="none" stroke="#db3646" stroke-width="6"
                                      stroke-linecap="round" stroke-miterlimit="10" x1="34.4" y1="37.9" x2="95.8"
                                      y2="92.3"/>
                                <line class="path line" fill="none" stroke="#db3646" stroke-width="6"
                                      stroke-linecap="round" stroke-miterlimit="10" x1="95.8" y1="38" X2="34.4"
                                      y2="92.2"/>
                            </svg>
                            <h4 class="text-danger failure-message-heading mt-3">Payment failed!</h4>
                            <p class="mt-3 failure-message-modal">Please check the entered details and try again.</p>
                            <button type="button" class="btn btn-sm mt-3 btn-danger" data-bs-dismiss="modal">Close
                            </button>
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
                            <h4 class="text-success success-message-heading-modal  mt-3">Payment done successfully!</h4>
                            <p class="mt-3 success-message-modal d-none">Please check the entered details and try
                                again.</p>
                            <button type="button" class="btn btn-sm mt-3 btn-success" data-bs-dismiss="modal">Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="toaster-common-file.jsp" %>
    </main>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="<c:url value='/resources/js/global-js.js' />"></script>
<script src="<c:url value='/resources/js/main.js'/>"></script>
<script src="<c:url value='/resources/js/pagination.js' />"></script>
<script src="<c:url value='/resources/js/admin-dashboard.js' />"></script>
<script src="<c:url value='/resources/js/toasters-1.js' />"></script>
<script src="<c:url value='/resources/js/loader.js' />"></script>


<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

</body>
</html>
