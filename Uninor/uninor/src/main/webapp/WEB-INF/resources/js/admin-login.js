function onlyNumberKey(input) {
    input.value = input.value.replace(/[^0-9]/g, '');
    if (isNaN(input.value)) {
        input.value = '';
    }
}

document.addEventListener("DOMContentLoaded", function (event) {

    function OTPInput() {
        const inputs = document.querySelectorAll('#otp > *[id]');
        for (let i = 0; i < inputs.length; i++) {
            inputs[i].addEventListener('keydown', function (event) {
                if (event.key === "Backspace") {
                    inputs[i].value = '';
                    if (i!== 0) inputs[i - 1].focus();
                } else {
                    const regex = /^[0-9]$/;
                    if (regex.test(event.key)) {
                        inputs[i].value = event.key;
                        if (i!== inputs.length - 1) inputs[i + 1].focus();
                        event.preventDefault();
                    } else {
                        event.preventDefault();
                    }
                }
            });
        }
    }

    OTPInput();


});

$.validator.addMethod("onlyCharacters", function(value, element) {
    return this.optional(element) || /^[a-zA-Z]+$/.test(value);
}, "Should only contain characters.");

$.validator.addMethod("emailregex", function (value, element) {
    // Define your regex pattern
    var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    // Test the value against the pattern
    return this.optional(element) || pattern.test(value);
}, "Please enter a valid email address.");

$("#login-form").validate({
    rules: {
        email: {
            required: true,
            emailregex: true
        }
    },
    messages: {
        email: {
            required: "Please enter your email",
            emailregex: "Please enter a valid email"

        }
    },
    ignore: '#otp *',
    errorElement: "span",
    errorClass: "error-class",
    submitHandler: function (form) {
        debugger
        var submitType = $(".active-submit").attr("data-value")
        if(submitType == 1){
            getOtpInput()
        }else {
            getOTPCode()
        }

    }
});

function getOtpInput() {

    $(".otp-text").html("")
    debugger

    var email = $("#email").val()

    showLoader()
    $.ajax({
        url: CONTEXT_PATH + '/get-admin-login-otp',
        type: 'POST',
        dataType: 'json',
        data: {
            email : email
        },
        success: function (xhr, status, error) {
            hideLoader()
            console.log("In success")
            if(status){
                let successResponse;

                try {
                    successResponse = JSON.parse(xhr);
                } catch (e) {
                    successResponse = xhr;

                }

                if (successResponse.messages) {
                    let successMessage = successResponse.messages
                    showAlert(true,successMessage, "success")
                }

                if (successResponse.clientId) {
                    $(".hidden-user-id").val(successResponse.clientId)
                    localStorage.setItem("clientId",successResponse.clientId)
                }

                $(".get-otp-btn-hide").addClass("d-none").removeClass("active-submit")
                $("#number").attr("readonly", "readonly")
                $(".otp-toggle").removeClass("d-none")
                $(".submit-sign-up-btn").removeClass("d-none").addClass("active-submit")
                $(".otp-text").removeClass("d-none")
            }

        },
        error: function(xhr, status, error) {
            hideLoader()
            debugger
            if (xhr.status === 400 || xhr.status === 409 || xhr.status === 404) { // Bad Request
                console.log(xhr.status)
                let errorResponse;
                try {
                    errorResponse = JSON.parse(xhr.responseText);
                } catch (e) {
                    errorResponse = xhr.responseText;
                }

                if (errorResponse.errors) {
                    let errorMessage = errorResponse.errors
                    showAlert(true,errorMessage,"faliure")
                }


            } else {
                showAlert(true,"Server side error","faliure")
            }
        }
    })

    setTimeout(function () {
        $(".otp-text").html("<span>* Didn't recieve the code yet? <a id='resend-otp-link' onclick='getOtpInput()'>Click here to resend</a>.</span>")
    }, 10000);
}

function getOTPCode() {
    const inputs = document.querySelectorAll('#otp > *[id]');
    let OTP = '';
    let hasError = false
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value === '' || inputs[i].value === null) {
            hasError = true;
            break;
        }
        OTP += inputs[i].value;
    }
    if (hasError) {
        showAlert(true,"OTP must be of 6 digits.","faliure")
    } else {
        // $(".hidden-otp-field").val(OTP)
        submitSignUpForm(OTP)
    }

}

function submitSignUpForm(OTP){
    debugger
    var email = $("#email").val()
    var otp = OTP

    var payload = {}
    payload["email"] = email
    payload["otp"] = otp

    showLoader()
    $.ajax({
        url: CONTEXT_PATH + '/validate-admin-login-otp',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            debugger
            hideLoader()
            console.log(xhr)
            let successResponse;
            localStorage.setItem("adminId",xhr.adminId)
            localStorage.setItem("showLoginToaster","true")
                window.location.assign(CONTEXT_PATH + "/admin/dashboard")
        },
        error: function(xhr, status, error) {
            hideLoader()
            debugger
            if (xhr.status === 401 || xhr.status === 404) {
                let errorResponse;
                try {
                    errorResponse = JSON.parse(xhr.responseText);
                } catch (e) {
                    errorResponse = xhr.responseText;
                }
                console.log(errorResponse)
                if (errorResponse.errors) {
                    let errorMessage = errorResponse.errors
                    showAlert(true,errorMessage,"faliure")
                }
            } else {
                console.log("Some other error")
            }
        }
    })
}

function showOnLoadMessages(){

    var showLogoutMessage = localStorage.getItem("showLogoutMessage")
    if(showLogoutMessage === "true"){
        showAlert(true,"Logout Successfully","success")
        localStorage.removeItem("showLogoutMessage")
    }
}