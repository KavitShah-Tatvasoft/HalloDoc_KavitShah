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
        number: {
            required: "Please enter your mobile number",
            minlength: "Your Mobile Number should not be less than 10 digits",
            maxlength: "Your Mobile Number should not be more than 10 digits",
            number: "Your Mobile Number must only have digits"

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

    var number = $("#number").val()
    const regex = /^\d{10}$/;

    if(!regex.test(number)){
        showAlert(true,"Invalid Mobile Number!","faliure")
    }else {
        showLoader()
        $.ajax({
            url: CONTEXT_PATH + '/get-login-otp',
            type: 'POST',
            dataType: 'json',
            data: {
                number : number
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
                    console.log(errorResponse)
                    if (errorResponse.errors) {
                        let errorMessage = errorResponse.errors
                        showAlert(true,errorMessage,"faliure")
                    }

                    if(errorResponse.docError){
                        let errorMessage = errorResponse.docError
                        showAlert(true,errorMessage,"faliure")
                    }

                } else {
                    console.log("Some other error")
                }
            }
        })

        setTimeout(function () {
            $(".otp-text").html("<span>* Didn't recieve the code yet? <a id='resend-otp-link' onclick='getOtpInput()'>Click here to resend</a>.</span>")
        }, 10000);
    }


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
    var number = $("#number").val()
    var otp = OTP

    var payload = {}
    payload["number"] = number
    payload["otp"] = otp

    showLoader()
    $.ajax({
        url: CONTEXT_PATH + '/validate-login-otp',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            debugger
            hideLoader()
            console.log(xhr)
            let successResponse;
            localStorage.setItem("clientId",xhr.clientId)
            localStorage.setItem("showLoginToaster","true")
            if(xhr.docValidation === "true"){
                window.location.assign(CONTEXT_PATH + "/client/dashboard")
            }else {
                window.location.assign(CONTEXT_PATH + "/document-under-verification")
            }
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
    var showReuploadSubmitMessgae = localStorage.getItem("showReuploadSubmitMessgae")
    var showLogoutMessage = localStorage.getItem("showLogoutMessage")
    if(showReuploadSubmitMessgae === "true"){
        showAlert(true,"Files uploaded successfully","success")
    }

    if(showLogoutMessage === "true"){
        showAlert(true,"Logout Successfully","success")
        localStorage.removeItem("showLogoutMessage")
    }
}