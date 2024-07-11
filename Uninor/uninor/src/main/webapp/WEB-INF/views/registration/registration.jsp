<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 30-05-2024
  Time: 09:53
  To change this template use File | Settings | File Templates.
--%>
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
<body onload="getClientData()">
<div class="loader-container">
    <div class="loader"></div>
</div>
<div class="container-fluid main-container">
    <form id="register-form" method="post" >
        <div class="row">
            <div class="col">
                <div class="registration-type-div mb-2">
                    <div class="selector">
                        <div class="selector-item">
                            <input type="radio" id="radio1" name="selector" value="1" class="selector-item_radio"
                                        checked>
                            <label for="radio1" class="selector-item_label" onclick="showCompanyInput()">Port
                                Number</label>
                        </div>
                        <div class="selector-item">
                            <input type="radio" id="radio2" name="selector" value="2" class="selector-item_radio">
                            <label for="radio2" class="selector-item_label" data-bs-toggle="modal"
                                   data-bs-target="#staticBackdrop" onclick="hideCompanyInput()">Get New
                                SIM</label>
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
                    <input type="text" id="mobileNumber" data-number="0" class="form-control taskName" autocomplete="off"
                           name="mobileNumber" required/>
                    <label class="floating-label place-holder mobile-placeholder">Mobile Number to Port</label>
                </div>
            </div>

            <div class="col-12 col-md-6 company-name-container">
                <div class="floating-label-group mt-2">
                        <%--                <input type="text" id="companyName" class="form-control taskName" autocomplete="off"--%>
                        <%--                       name="companyName" required/>--%>
                    <select id="companyName" class="form-control taskName" autocomplete="off"
                            name="companyName" required>
                        <option hidden="hidden" value="">Select Company Name</option>
                        <c:forEach items="${companyList}" var="company">
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

            <div class="col-12 col-md-6 mt-2">
                <div class="section questions">

                    <div class="section__inner">
                        <div id="loanTypesForm" class="form questions__form">
                            <div class="form__tab active">
                                <div class="form__group">
                                    <label class="form__label label" for="purchase">

                                        <!-- tabindex="0" -->
                                        <input class="form__input" checked required type="radio" name="planType" id="purchase" value="1">
                                        <span class="main-text-span">Prepaid</span>
                                        <svg class="form__label-check" viewBox="0 0 512 512" title="check-circle">
                                            <path d="M504 256c0 136.967-111.033 248-248 248S8 392.967 8 256 119.033 8 256 8s248 111.033 248 248zM227.314 387.314l184-184c6.248-6.248 6.248-16.379 0-22.627l-22.627-22.627c-6.248-6.249-16.379-6.249-22.628 0L216 308.118l-70.059-70.059c-6.248-6.248-16.379-6.248-22.628 0l-22.627 22.627c-6.248 6.248-6.248 16.379 0 22.627l104 104c6.249 6.249 16.379 6.249 22.628.001z" />
                                        </svg>
                                    </label>
                                    <!-- </button> -->
                                    <label class="form__label label" for="refinance">
                                        <!-- tabindex="0" -->
                                        <input class="form__input" required type="radio" name="planType" id="refinance" value="2">
                                        <span class="main-text-span">Postpaid</span>
                                        <svg class="form__label-check" viewBox="0 0 512 512" width="100" title="check-circle">
                                            <path d="M504 256c0 136.967-111.033 248-248 248S8 392.967 8 256 119.033 8 256 8s248 111.033 248 248zM227.314 387.314l184-184c6.248-6.248 6.248-16.379 0-22.627l-22.627-22.627c-6.248-6.249-16.379-6.249-22.628 0L216 308.118l-70.059-70.059c-6.248-6.248-16.379-6.248-22.628 0l-22.627 22.627c-6.248 6.248-6.248 16.379 0 22.627l104 104c6.249 6.249 16.379 6.249 22.628.001z" />
                                        </svg>
                                        <!-- </button> -->
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
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

            <div class="col-12 mt-3 upload-view-flex">
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

            <div class="col-12 mt-3 upload-view-flex">
                <div class="input-group">
                    <input id="profile-pic-name-id" type="text"
                           class="form-control p-2 verification-files custom-upload"
                           placeholder="Upload Profile Picture" disabled>
                    <button class="file-upload-btn btn-color-for-upload" type="button" id="upload">
                        <img src="<c:url value='/resources/images/upload-white.svg' />" class="upload-image-file"
                             alt="">
                        <span class="for-remove-upload ">Upload</span></button>
                    <input id="profilePicUploadedFile"  name="profilePicUploadedFile" multiple
                           class="file-input-hover-effect file-upload-class" type="file"
                           style="position: absolute; right: -8px;top: 0.5rem; opacity: 0;">
                </div>

                <button class="verification-files main-container-view-btn profile-pic-view d-none">
                    <div class="view-btn-flex">
                        <img src="<c:url value='/resources/images/view-white.svg' />" class="upload-image-file" alt="">
                        <span class="for-remove-upload ">View</span>
                    </div>
                </button>

            </div>
            <span id="photo-error" class="error-class"></span>

            <div class="col-12 mt-4">
                <div class="submit-cancel-flex">
                    <button type="submit" class="submit-btn-register">Submit</button>
                    <button type="reset" class="cancel-btn-register">Cancel</button>
                </div>
            </div>


        </div>

    </form>

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

</div>


<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">

        <div class="modal-content">
            <form id="number-select-form" method="post">
                <div class="modal-header modal-main-header">
                    <h5 class="modal-title">Select a Mobile Number</h5>
                </div>

                <div class="modal-body">
                    <div class="error-div"></div>
                    <!-- <img src="./SRS Screen Shorts/uninor-remove-bg.png" class="bg-image" alt="">  -->
                    <div class="container modal-container ">

                        <div class="row flex-numbers">

                            <c:forEach items="${simSuggestions}" var="simCard">
                                <label class="col-12 col-sm-4">
                                    <input type="radio" value="${simCard.simCardId}" name="radio"/>
                                    <span data-value="${simCard.phoneNumber}">${simCard.phoneNumber}</span>
                                </label>
                            </c:forEach>

                        </div>

                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-secondary">
                        Select
                    </button>
                </div>
            </form>
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

<style>

    label input:checked + span, label span:hover{
        background-color: transparent;
    }

    .label span:before{
        display: none;
    }

    .form__label-check{
        transform: scale(0.8)!important;
    }

    .main-text-span{
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 15px;
    }



    @media screen and (min-width: 992px) {
        .button {
            height: 44px;
        }
    }

    .button--transparent svg {
        margin-left: 6px;
    }
    svg {
        max-width: 100%;
        height: auto;
    }
    .questions {
        text-align: center;
    }

    .questions__form {
        max-width: 800rem;
        margin: 0 auto;
    }
    .questions .form__tab {
        display: none;
    }
    .questions .form__tab.active {
        display: block;
    }
    .questions .form__group {
        display: flex;
        flex-direction: row;
        align-items: center;
        gap: 20px;
    }

    .questions .form__group--full {
        flex-direction: column;
    }
    @media screen and (min-width: 992px) {
        .questions .form__group--full {
            flex-direction: column;
        }
    }
    .questions .form__title {
        margin-bottom: 3rem;
        padding: 0 4rem;
        font-size: 3.2rem;
    }
    .questions .form__input {
        position: absolute;
        opacity: 0;
    }
    .questions .form__label {
        align-items: start;
        background-color: transparent;
        border-radius: 5px;
        border: 1px solid #b8a5a5;
        cursor: pointer;
        justify-content: start;
        padding: 7px 5px;
        position: relative;
        text-align: left;
        width: 100%;
        max-width: 25rem;
        transition: all 0.2s ease;
    }
    .questions .form__label--simple {
        grid-template-columns: 1fr;
        grid-template-rows: 1fr;
        text-align: center;
        padding: 1rem 2rem;
        font-size: 16px;
        font-style: normal;
        font-weight: 350;
        line-height: normal;
        color: #12395b;
    }
    .questions .form__label:hover {
        background: rgba(8, 107, 148, 0.1);
    }

    .questions .form__label:has(input:checked) .form__label-check {
        display: block;
    }
    .questions .form__label:has(input:focus) {
        outline: 1px ridge rgba(0, 110, 200, 0.4);
    }
    .questions .form__label-img {
        grid-column: 1;
        grid-row: 0.3333333333;
        width: 60px;
    }
    .questions .form__label-name {
        font-size: 24px;
        font-style: normal;
        font-weight: 400;
        line-height: 42px;
        /* 175% */
    }
    .questions .form__label-text {
        font-size: 14px;
        font-style: normal;
        font-weight: 325;
        line-height: 20px;
        /* 142.857% */
    }
    .questions .form__label-check {
        color: #006ec8;
        fill: #006ec8;
        stroke-opacity: 1;
        stroke: #f7f7f7;
        background: #f7f7f7;
        border-radius: 100%;
        display: none;
        background-size: cover;
        background-repeat: no-repeat;
        position: absolute;
        top: -12px;
        left: 94%;
        width: 30px;
        height: 30px;
        transform: translate(-50%, -50%);
    }
    .questions .form__prev > svg {
        transform: rotate(180deg);
    }
    .questions .form__prev, .questions .form__next {
        width: 100%;
        max-width: 35rem;
        margin: 0 auto;
        border-radius: 10px;
        margin-top: 2rem;
    }

</style>
