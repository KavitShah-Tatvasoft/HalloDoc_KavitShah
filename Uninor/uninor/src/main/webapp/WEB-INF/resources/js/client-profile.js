$(document).ready(function(){
    $(".active-common-class").removeClass("active-link-sidebar")
    $(".active-profile").addClass("active-link-sidebar")
});


function validateImageUpload(input) {
    const file = input.files[0];
    const allowedExtensions = ['jpg', 'jpeg', 'png'];
    const fileSizeLimit = 1024 * 1024 * 2; // 1MB

    if (file.size > fileSizeLimit) {
        alert('File size exceeds the limit of 2MB');
        input.value = '';
        return;
    }

    const fileExtension = file.name.split('.').pop().toLowerCase();
    if (!allowedExtensions.includes(fileExtension)) {
        alert('Invalid file extension. Only jpg, jpeg, png are allowed');
        input.value = '';
        return;
    }

    const reader = new FileReader();
    reader.onload = function() {
        const imageDataUrl = reader.result;
        const img = document.getElementById('visible-profile-image');
        img.src = imageDataUrl;
    };
    reader.readAsDataURL(file);

    var profileImage = $("#profile-image")

    var formData = new FormData();
    formData.append("profilePhoto",profileImage[0].files[0])

    $.ajax({

        url: CONTEXT_PATH + '/client/update-profile-photo',
        type: 'POST',
        processData: false,
        contentType: false,
        dataType: 'json',
        enctype: 'multipart/form-data',
        data: formData,
        success: function (xhr,status,error) {
            debugger
            showAlert(true, "Profile Photo Updated", "success")
        },
        error: function(xhr, status, error) {
            hideLoader()
            debugger
            if (xhr.status === 401 || xhr.status === 400 || xhr.status == 409) {
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
            }else if(xhr.status === 404){
                try {
                    errorResponse = JSON.parse(xhr.responseText);
                } catch (e) {
                    errorResponse = xhr.responseText;
                }
                console.log(errorResponse)

                if (errorResponse.errors) {
                    localStorage.setItem("errorCode","404")
                    localStorage.setItem("errorType", "Data not found")
                    localStorage.setItem("errorMessgae",errorResponse.errors)
                    localStorage.setItem("showError","true")
                    window.location.assign("error/error-page")
                }

            }
            else {
                showAlert(true,"Some Error Ocurred!","faliure")
            }
        }
    })
}

function getProfileData(){
    $.ajax({
        url: CONTEXT_PATH + '/client/get-client-profile-details',
        type: 'GET',
        dataType: 'json',
        success: function (xhr, status, error) {
            var details = xhr['clientProfileDto']
            console.log(details)
            $("#visible-profile-image").attr("src",details.profilePicPath)
            $("#fname").val(details.firstName)
            $("#lname").val(details.lastName)
            $("#mobileNumber").val(details.phone).prop('readonly', true)
            $("#email").val(details.email).prop('readonly', true)
            $("#dob").val(details.birthDate).prop('readonly', true)
            $("#street").val(details.street)
            $("#city").val(details.city)
            $("#state").val(details.state)
            $("#zipcode").val(details.zipcode)
            $("#panNumber").val(details.panNumber).prop('readonly', true)
            $("#aadharCardNumber").val(details.aadharCardNumber).prop('readonly', true)
            $("#GSTNumber").val(details.gstNumber)
        },
        error: function(xhr, status, error) {
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
                showAlert(true,"Error at server side.","faliure")
            }
        }
    })
}

$.validator.addMethod("onlyCharacters", function(value, element) {
    return this.optional(element) || /^[a-zA-Z]+$/.test(value);
}, "Should only contain characters.");

$.validator.addMethod("emailregex", function (value, element) {
    var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    return this.optional(element) || pattern.test(value);
}, "Please enter a valid email address.");

$.validator.addMethod("photoFormat", function (value, element) {
    var fileExtension = value.split('.').pop().toLowerCase();
    return this.optional(element) || (fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'png');
}, "Only .jpg, .jpeg, or .png files are allowed.");

$.validator.addMethod("gstValidator", function (value, element) {
    var pattern = /^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/;
    return this.optional(element) || pattern.test(value);
}, "Please enter a valid gst number.");

$("#profile-form").validate({
    rules: {
        fname: {
            required: true,
            minlength: 3,
            maxlength: 50,
            onlyCharacters: true
        },
        lname: {
            required: true,
            minlength: 3,
            maxlength: 50,
            onlyCharacters: true
        },
        email: {
            required: true,
            emailregex: true
        },
        mobileNumber: {
            required: true,
            number: true,
            minlength: 10,
            maxlength: 10
        },
        dob: {
            required: true,
            date: true
        },
        street: {
            required: true,
            minlength: 5,
            maxlength: 50
        },
        city: {
            required: true,
            minlength: 3,
            maxlength: 50
        },
        state: {
            required: true,
            minlength: 3,
            maxlength: 50
        },
        zipcode: {
            required: true,
            number: true,
            minlength: 6,
            maxlength: 8
        },
        panNumber: {
            required: true,
            minlength: 10,
            maxlength: 10
        },
        aadharCardNumber: {
            required: true,
            minlength: 12,
            maxlength: 12
        },
        GSTNumber: {
            required: function(element) {
                return $(element).val().length > 0;
            },
            gstValidator: true
        }
    },
    messages: {
        fname: {
            required: "Please enter your first name",
            minlength: "Your first name must be at least 3 characters long",
            maxlength: "Your first name must not exceed 50 characters",
            onlyCharacters: "Your first name should only contain characters"
        },
        lname: {
            required: "Please enter your last name",
            minlength: "Your last name must be at least 3 characters long",
            maxlength: "Your last name must not exceed 50 characters",
            onlyCharacters: "Your last name should only contain characters"
        },
        email: {
            required: "Please enter your email address",
            emailregex: "Please enter a valid email address"
        },
        mobileNumber: {
            required: "Please enter a mobile number",
            minlength: "Mobile number should be not less than 10 characters",
            maxlength: "Mobile number should not be more than 10 characters long",
            number: "Mobile number should only contain digits"
        },
        dob: {
            required: "Please select a birthdate",
            date: "Please enter a valid date"
        },
        street: {
            required: "Enter a street name",
            minlength: "Street name should be at least 5 characters long",
            maxlength: "Street name should at max have 50 characters"
        },
        city: {
            required: "Enter a city name",
            minlength: "City name should be at least 3 characters long",
            maxlength: "City name should at max have 50 characters"
        },
        state: {
            required: "Enter a state name",
            minlength: "State name should be at least 3 characters long",
            maxlength: "State name should at max have 50 characters"
        },
        zipcode: {
            required: "Enter a zipcode",
            minlength: "Zipcode should be at least 6 characters long",
            maxlength: "Zipcode should at max have 8 characters",
            number: "Zipcode should only have numbers"
        },
        panNumber: {
            required: "Enter a PAN Card number",
            minlength: "PAN number should be not less than 10 characters",
            maxlength: "PAN number should be not more than 10 characters long"
        },
        aadharCardNumber: {
            required: "Enter an Aadhar Card number",
            minlength: "Aadhar Card number should not be less than 12 characters",
            maxlength: "Aadhar Card number should not be more than 12 characters long"
        },
        GSTNumber: {
            required: "Enter a GST number",
            gstValidator: "Please enter a valid GST Number linked to your PAN Card"
        }
    },
    errorPlacement: function (error, element) {
        error.insertAfter(element);
    },
    errorElement: "span",
    errorClass: "error-class",
    submitHandler: function (event) {
        // callAlert()
        updateClientProfileDetails()
    }
});

function updateClientProfileDetails(){
    debugger

    var profileImage = $("#profile-image")
    var firstName = $("#fname").val()
    var lastName = $("#lname").val()
    var email = $("#email").val()
    var street = $("#street").val()
    var city = $("#city").val()
    var state = $("#state").val()
    var zipcode = $("#zipcode").val()
    var gstNumber = $("#GSTNumber").val()


    var payload = {};
    payload['firstName'] = firstName;
    payload['lastName'] = lastName;
    payload['email'] = email;
    payload['street'] = street;
    payload['city'] = city;
    payload['state'] = state;
    payload['zipcode'] = zipcode;
    payload['gstNumber'] = gstNumber;


    $.ajax({

        url: CONTEXT_PATH + '/client/update-user-profile',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(payload),
        success: function (xhr,status,error) {
            showAlert(true,xhr['message'],"success")
        },
        error: function(xhr, status, error) {
            if (xhr.status === 401 || xhr.status === 400 || xhr.status === 409 || xhr.status === 405) {
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
            }else if(xhr.status === 404){
                try {
                    errorResponse = JSON.parse(xhr.responseText);
                } catch (e) {
                    errorResponse = xhr.responseText;
                }
                console.log(errorResponse)

                if (errorResponse.errors) {
                    localStorage.setItem("errorCode","404")
                    localStorage.setItem("errorType", "Data not found")
                    localStorage.setItem("errorMessgae",errorResponse.errors)
                    localStorage.setItem("showError","true")
                    window.location.assign("error/error-page")
                }

            }
            else {
                showAlert(true,"Some Error Ocurred!","faliure")
            }
        }
    })
}