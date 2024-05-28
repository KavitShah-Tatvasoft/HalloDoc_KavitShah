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
                <h2 class="text-center">Sign Up</h2>
                <div class="floating-label-group mt-2">
                    <input type="text" id="fname" class="form-control taskName" autocomplete="off"
                           name="Email" required/>
                    <label class="floating-label place-holder">First Name</label>
                    <%--                    <span id="emailError" class="error">Please enter a valid Email.</span>--%>

                </div>

                <div class="floating-label-group mt-2">
                    <input type="text" id="lname" class="form-control taskName" autocomplete="off"
                           name="lname" required/>
                    <label class="floating-label place-holder">Last Name</label>
                    <%--                    <span id="emailError" class="error">Please enter a valid Email.</span>--%>

                </div>

                <div class="floating-label-group mt-2">
                    <input type="text" id="otp-code" class="form-control taskName" autocomplete="off"
                           name="otp-code" required/>
                    <label class="floating-label place-holder">Email</label>
                    <%--                    <span id="emailError" class="error">Please enter a valid Email.</span>--%>

                    <div id="otp" class="inputs d-flex flex-row justify-content-center mt-4"><input
                            class="m-2 text-center form-control rounded" type="text" id="first" maxlength="1"/> <input
                            class="m-2 text-center form-control rounded" type="text" id="second" maxlength="1"/> <input
                            class="m-2 text-center form-control rounded" type="text" id="third" maxlength="1"/> <input
                            class="m-2 text-center form-control rounded" type="text" id="fourth" maxlength="1"/> <input
                            class="m-2 text-center form-control rounded" type="text" id="fifth" maxlength="1"/> <input
                            class="m-2 text-center form-control rounded" type="text" id="sixth" maxlength="1"/></div>
                </div>

                <button class="uninor-blue-btn" onclick="getOTPCode()">Get OTP</button>
            </div>

        </div>
    </div>

    <div id="right-side-div">


    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<script>

    function getOTPCode(){
        const inputs = document.querySelectorAll('#otp > *[id]');
        let OTP = '';
        for (let i = 0; i < inputs.length; i++) {
            OTP += inputs[i].value;
        }
        alert(OTP)
    }

    document.addEventListener("DOMContentLoaded", function (event) {

        function OTPInput() {
            const inputs = document.querySelectorAll('#otp > *[id]');
            for (let i = 0; i < inputs.length; i++) {
                inputs[i].addEventListener('keydown', function (event) {
                    if (event.key === "Backspace") {
                        inputs[i].value = '';
                        if (i !== 0) inputs[i - 1].focus();
                    } else {
                        if (i === inputs.length - 1 && inputs[i].value !== '') {
                            return true;
                        } else if (event.keyCode > 47 && event.keyCode < 58) {
                            inputs[i].value = event.key;
                            if (i !== inputs.length - 1) inputs[i + 1].focus();
                            event.preventDefault();
                        } else if (event.keyCode > 64 && event.keyCode < 91) {
                            inputs[i].value = String.fromCharCode(event.keyCode);
                            if (i !== inputs.length - 1) inputs[i + 1].focus();
                            event.preventDefault();
                        }
                    }
                });
            }
        }

        OTPInput();


    });

</script>
</body>
</html>
