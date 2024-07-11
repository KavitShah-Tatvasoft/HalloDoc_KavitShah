<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 28-05-2024
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page isELIgnored="false" %>
<html>
<head>
    <title>Sign Up</title>
<%--    font link--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='/resources/css/registration-page.css' />">
</head>
<body>

<div id="main-div">
    <div id="left-side-div">
        <div id="logo-login-div">
            <div id="logo-div">
                <img src="<c:url value='/resources/images/uninor_logo_big.jpg' />" alt="Company Logo"
                     class="company-logo-top"/>
            </div>

            <div id="login-div">
                <form method="post" id="sign-up-form">
                    <h2 class="text-center">Sign Up</h2>
                    <div class="floating-label-group mt-3">
                        <input type="text" id="fname" class="form-control taskName" autocomplete="off"
                               name="fname" required/>
                        <label class="floating-label place-holder">First Name</label>
                        <span id="fNameError" class="error text-danger d-none"></span>

                    </div>

                    <div class="floating-label-group mt-3">
                        <input type="text" id="lname" class="form-control taskName" autocomplete="off"
                               name="lname" required/>
                        <label class="floating-label place-holder">Last Name</label>
                        <span id="lNameError" class="error text-danger d-none"></span>

                    </div>

                        <div class="floating-label-group mt-3">
                            <input type="text" id="email" class="form-control taskName" autocomplete="off"
                                   name="email" required/>
                            <label class="floating-label place-holder">Email</label>
                            <span id="emailError" class="error text-danger d-none"></span>


                        </div>

                        <div class="text-info otp-text d-none"> * An OTP is send to your above mentioned email.</div>

                    <button type="submit" class="uninor-blue-btn w-100 get-otp-btn-hide">GET OTP</button>


                <div id="otp" class="inputs d-flex flex-row justify-content-center mt-2 d-none otp-toggle"><input
                        class="m-2 text-center form-control rounded" type="text" id="first" maxlength="1" />
                    <input
                            class="m-2 text-center form-control rounded" type="text" id="second" maxlength="1" />
                    <input
                            class="m-2 text-center form-control rounded" type="text" id="third" maxlength="1" />
                    <input
                            class="m-2 text-center form-control rounded" type="text" id="fourth" maxlength="1" />
                    <input
                            class="m-2 text-center form-control rounded" type="text" id="fifth" maxlength="1" />
                    <input
                            class="m-2 text-center form-control rounded" type="text" id="sixth" maxlength="1" />
                </div>

                <button class="uninor-blue-btn w-100 submit-sign-up-btn d-none" onclick="getOTPCode()">SIGN UP</button>


                </form>



            </div>

        </div>
    </div>

    <div id="right-side-div">
        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0"
                        class="active slide-btn-clr" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                        class="slide-btn-clr" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                        class="slide-btn-clr" aria-label="Slide 3"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3"
                        class="slide-btn-clr " aria-label="Slide 4"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="<c:url value='/resources/images/slide-1.jpg' />" class="d-block w-100 max-height"
                         alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <%--                        <h5>First slide label</h5>--%>
                        <%--                        <p>Some representative placeholder content for the first slide.</p>--%>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="<c:url value='/resources/images/slide-2.jpg' />" class="d-block w-100 max-height"
                         alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <%--                        <h5>Second slide label</h5>--%>
                        <%--                        <p>Some representative placeholder content for the second slide.</p>--%>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="<c:url value='/resources/images/slide-3.jpg' />" class="d-block w-100 max-height"
                         alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <%--                        <h5>Third slide label</h5>--%>
                        <%--                        <p>Some representative placeholder content for the third slide.</p>--%>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="<c:url value='/resources/images/slide-4.jpg' />" class="d-block w-100 resize-img"
                         alt="...">
                    <%--                    <div class="carousel-caption d-none d-md-block">--%>
                    <%--                        <h5 class="text-dark">Fourth slide label</h5>--%>
                    <%--                        <p class="text-dark">Some representative placeholder content for the fourth slide.</p>--%>
                    <%--                    </div>--%>
                </div>
            </div>
            <%--            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">--%>
            <%--                <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
            <%--                <span class="visually-hidden">Previous</span>--%>
            <%--            </button>--%>
            <%--            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">--%>
            <%--                <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
            <%--                <span class="visually-hidden">Next</span>--%>
            <%--            </button>--%>
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
<script>





</script>
</body>
</html>
