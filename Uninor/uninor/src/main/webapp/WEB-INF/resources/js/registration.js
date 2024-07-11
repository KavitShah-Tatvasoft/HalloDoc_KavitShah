function getClientData(){
    debugger
    var clientId = localStorage.getItem("clientId")

    showLoader()
    $.ajax({
        url: CONTEXT_PATH + '/get-client-data',
        type: 'GET',
        data: {
            clientId : clientId
        },
        success: function (xhr, status, error) {
            debugger
            hideLoader()
            if(status){
                console.log("XHR: " + xhr)
                console.log("Inside Check condition")

                $("#fname").val(xhr.fname)
                $("#lname").val(xhr.lname)
                $("#email").val(xhr.email)
                $("#email").attr("readonly", "readonly")

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
                    window.location.assign(CONTEXT_PATH + "/sign-up")
                }
            } else {
                window.location.assign(CONTEXT_PATH + "/sign-up")
            }
        }
    })


}

const fileInput_1 = document.getElementById('panCardUploadedFile');
const fileNameField_1 = document.getElementById('pan-card-name-id');

// Listen for the change event on the file input element
fileInput_1.addEventListener('change', (event) => {

    $(".pan-card-view").removeClass("d-none")

    // Get the selected file(s)
    const files = event.target.files;

    // If one or more files are selected
    if (files.length > 0) {
        // Get the name of the first file
        const fileName = files[0].name;

        // Update the value of the input field with the file name
        fileNameField_1.value = fileName;
    } else {
        // If no files are selected, clear the input field
        fileNameField_1.value = 'Upload PAN Card';
    }
});

$(".pan-card-view").bind("click", (e) => {
    debugger
    e.preventDefault();
    const file = fileInput_1.files[0];
    const objUrl = URL.createObjectURL(file);
    const newTab = window.open(objUrl, "_blank");
    newTab.focus();
});

const fileInput_2 = document.getElementById('aadharCardUploadedFile');
const fileNameField_2 = document.getElementById('aadhar-card-name-id');

fileInput_2.addEventListener('change', (event) => {

    $(".aadhar-card-view").removeClass("d-none")
    // Get the selected file(s)
    const files = event.target.files;

    // If one or more files are selected
    if (files.length > 0) {
        // Get the name of the first file
        const fileName = files[0].name;

        // Update the value of the input field with the file name
        fileNameField_2.value = fileName;
    } else {
        // If no files are selected, clear the input field
        fileNameField_2.value = 'Upload Aadhar Card';
    }
});

$(".aadhar-card-view").bind("click", (e) => {
    debugger
    e.preventDefault();
    const file = fileInput_2.files[0];
    const objUrl = URL.createObjectURL(file);
    const newTab = window.open(objUrl, "_blank");
    newTab.focus();
});


const fileInput_3 = document.getElementById('profilePicUploadedFile');
const fileNameField_3 = document.getElementById('profile-pic-name-id');

fileInput_3.addEventListener('change', (event) => {
    $(".profile-pic-view").removeClass("d-none")
    // Get the selected file(s)
    const files = event.target.files;

    // If one or more files are selected
    if (files.length > 0) {
        // Get the name of the first file
        const fileName = files[0].name;

        // Update the value of the input field with the file name
        fileNameField_3.value = fileName;
    } else {
        // If no files are selected, clear the input field
        fileNameField_3.value = 'Upload Profile Pic';
    }
});

$(".profile-pic-view").bind("click", (e) => {
    debugger
    e.preventDefault();
    const file = fileInput_3.files[0];
    const objUrl = URL.createObjectURL(file);
    const newTab = window.open(objUrl, "_blank");
    newTab.focus();
});

function showCompanyInput(){
    $(".company-name-container").removeClass("d-none")
    $("#mobileNumber").val("")
    $("#mobileNumber").attr("data-number","0")
    $(".mobile-placeholder").html("Mobile Number to Port")
}

function hideCompanyInput(){
    $(".company-name-container").addClass("d-none")
    $(".mobile-placeholder").html("Mobile Number")
}

// function getNumberSuggestions(){
//     showLoader()
//     $.ajax({
//         url: CONTEXT_PATH + '/get-available-number-suggestions',
//         type: 'GET',
//         success: function (res) {
//             debugger
//             hideLoader()
//             console.log(res)
//         },
//         error: function(xhr, status, error) {
//             hideLoader()
//             console.log("Errorr")
//         }
//     })
//
// }

$.validator.addMethod("onlyCharacters", function(value, element) {
    return this.optional(element) || /^[a-zA-Z]+$/.test(value);
}, "Should only contain characters.");

$.validator.addMethod("panCard", function(value, element) {
    return this.optional(element) || /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/.test(value);
}, "Please enter a valid PAN Number");

$.validator.addMethod("aadharCard", function(value, element) {
    return this.optional(element) || /^[2-9]{1}[0-9]{3}[0-9]{4}[0-9]{4}$/.test(value);
}, "Please enter a valid Aadhar Card Number");

$.validator.addMethod("gstValidator", function(value, element) {
    return this.optional(element) || /^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/.test(value);
}, "Please enter a valid GST Number");


$.validator.addMethod("emailregex", function (value, element) {
    // Define your regex pattern
    var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    // Test the value against the pattern
    return this.optional(element) || pattern.test(value);
}, "Please enter a valid email address.");

$.validator.addMethod("fileFormat", function (value, element) {
    // Check if the file extension is valid
    var fileExtension = value.split('.').pop().toLowerCase();
    return this.optional(element) || (fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'pdf');
}, "Only .jpg, .jpeg, or .pdf files are allowed.");

$.validator.addMethod("photoFormat", function (value, element) {
    // Check if the file extension is valid
    var fileExtension = value.split('.').pop().toLowerCase();
    return this.optional(element) || (fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'png');
}, "Only .jpg, .jpeg, or .png files are allowed.");

$.validator.addMethod("lessThanToday", function(value, element) {
    var today = new Date();
    var inputDate = new Date(value);
    return inputDate < today;
}, "Date must be less than today's date");

$("#number-select-form").validate({
    rules: {
        radio: {
            required: true
        }
       },
    messages: {
        radio: {
            required: " * Please select a mobile number"
        }
    },
    errorPlacement: function(error, element) {
        error.appendTo(".error-div")
    },
    submitHandler: function (form){
        debugger
        var simCardId = $('input[name="radio"]:checked').val();
        var number = $('input[name="radio"]:checked').next().attr("data-value")

        $("#mobileNumber").val(number)
        $("#mobileNumber").attr("readonly","readonly")
        $("#mobileNumber").attr("data-number", simCardId)
        $('#staticBackdrop').modal('hide');
    }
})

$("#register-form").validate({
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
            emailregex: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
        },
        mobileNumber: {
            required: true,
            number: true,
            minlength: 10,
            maxlength: 10

        },
        companyName: {
            required: true
        },
        dob: {
            required: true,
            lessThanToday: true
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
            maxlength: 10,
            panCard: true
        },
        aadharCardNumber: {
            required: true,
            minlength: 12,
            maxlength: 12,
            aadharCard: true
        },

        // email: {
        //     required: function(element) {
        //         return $(element).val().length > 0;
        //     },
        //     email: function(element) {
        //         var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        //         return regex.test($(element).val());
        //     }
        // }

        GSTNumber: {
            required: function(element) {
                return $(element).val().length > 0;
            },
            gstValidator: function(element) {
                var regex = /^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/;
                return regex.test($(element).val());
            }
        },
        panCardUploadedFile:{
            required: true,
            fileFormat: true
        },
        aadharCardUploadedFile: {
            required: true,
            fileFormat: true
        },
        profilePicUploadedFile: {
            required: true,
            photoFormat: true
        }
    },
    messages: {
        fname: {
            required: "Please enter your first name",
            minlength: "Your first name must be at least 3 characters long",
            maxlength: "Your first name must not exceed 50 character",
            onlyCharacters: "Your first name should only contain characters"

        },
        lname: {
            required: "Please enter your last name",
            minlength: "Your last name must be at least 3 characters long",
            maxlength: "Your last name must not exceed 50 character",
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
            number: "Mobile number should only contains digits"
        },
        companyName: {
            required: "Please select a company"
        },
        dob: {
            require: "Please select a company name",
            lessThanToday: "Please select a valid date of birth"
        },
        street: {
            required: "Enter a street name",
            minlength: "Street name should be atleast 5 character long",
            max: "Street name should at max have 50 character"
        },
        city: {
            required: "Enter a city name",
            minlength: "City name should be atleast 3 character long",
            max: "City name should at max have 50 character"
        },
        state: {
            required: "Enter a state name",
            minlength: "State name should be atleast 3 character long",
            max: "State name should at max have 50 character"
        },
        zipcode: {
            required: "Enter a zipcode",
            minlength: "Zipcode should be atleast 6 character long",
            max: "Zipcode should  at max have 8 character",
            number: "Zipcode should only have numbers"
        },
        panNumber: {
            required: "Enter a PAN Card number",
            minlength: "PAN number should be not be less than 10 characters",
            maxlength: "PAN number should be not be more than 10 characters long",
            panCard: "Please enter a valid PAN Number"
        },
        aadharCardNumber: {
            required: "Enter a Aadhar Card number",
            minlength: "Aadhar Card number should not be less than 12 characters",
            maxlength: "Aadhar Card number should not be more than 12 characters long",
            aadharCard: "Please enter a valid aadhar card number"
        },
        GSTNumber: {
            required: "Enter a GST number",
            gstValidator: "Please enter a valid GST Number linked to your PAN Card"
        },
        panCardUploadedFile:{
            required: "Upload PAN Card",
            fileFormat: "Only .jpg, .jpeg, or .pdf files are allowed."
        },
        aadharCardUploadedFile: {
            required: "Upload Aadhar Card",
            fileFormat: "Only .jpg, .jpeg, or .pdf files are allowed."
        },
        profilePicUploadedFile: {
            required: "Upload PAN Card",
            photoFormat: "Only .jpg, .jpeg, or .png files are allowed."
        }

    },
    errorPlacement: function (error,element){
        if(element.attr("name") === "panCardUploadedFile") {
            $("#pan-error").html(error);
        } else if (element.attr("name") === "aadharCardUploadedFile") {
            error.appendTo("#aadhar-error");
        }else if(element.attr("name") === "profilePicUploadedFile")
            error.appendTo("#photo-error");
        else {
            error.insertAfter(element);
        }
    },
    errorElement: "span",
    errorClass: "error-class",
    submitHandler: function (form) {
        submitFormData()
    }
});

function submitFormData(){
    var selector = $('input[name="selector"]:checked').val();
    var clientId = localStorage.getItem("clientId")
    var fname = $("#fname").val()
    var lname = $("#lname").val()
    var mobileNumber = $("#mobileNumber").val()
    var mobileId = $("#mobileNumber").attr("data-number")
    var companyId = $("#companyName").val()
    var email = $("#email").val()
    var dob = $("#dob").val()
    var simType = $('input[name="planType"]:checked').val();
    var street = $("#street").val()
    var city = $("#city").val()
    var state = $("#state").val()
    var zipcode = $("#zipcode").val()
    var panNumber = $("#panNumber").val()
    var aadharCardNumber = $("#aadharCardNumber").val()
    var GSTNumber = $("#GSTNumber").val()
    var panCardUploadedFile = $("#panCardUploadedFile")
    var aadharCardUploadedFile = $("#aadharCardUploadedFile")
    var profilePicUploadedFile = $("#profilePicUploadedFile")

    var formData = new FormData();
    formData.append("selector",selector)
    formData.append("clientId",clientId)
    formData.append("fname",fname)
    formData.append("lname",lname)
    formData.append("mobileNumber",mobileNumber)
    formData.append("mobileId",mobileId)
    formData.append("companyId",companyId)
    formData.append("email",email)
    formData.append("dob",dob)
    formData.append("simType", simType)
    formData.append("street",street)
    formData.append("city",city)
    formData.append("state",state)
    formData.append("zipcode",zipcode)
    formData.append("panNumber",panNumber)
    formData.append("aadharCardNumber",aadharCardNumber)
    formData.append("GSTNumber",GSTNumber)
    formData.append("panCardUploadedFile",panCardUploadedFile[0].files[0])
    formData.append("aadharCardUploadedFile",aadharCardUploadedFile[0].files[0])
    formData.append("profilePicUploadedFile",profilePicUploadedFile[0].files[0])

    showLoader()
    debugger
    $.ajax({

        url: CONTEXT_PATH + '/register-user',
        type: 'POST',
        processData: false,
        contentType: false,
        dataType: 'json',
        enctype: 'multipart/form-data',
        data: formData,
        success: function (xhr,status,error) {
            debugger
            hideLoader()
            window.location.assign(CONTEXT_PATH + "/login")
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

$('.file-upload-class').on('change', function () {
    $(this).valid();
});

function getCompanyData(){
    showLoader()
    $.ajax({
        url: CONTEXT_PATH + '/get-company-details',
        type: 'GET',
        success: function (res) {
            debugger
            hideLoader()
            var select = $(".company-select")
            select.empty()
            let cloneOption = $(".option-clone").clone().removeClass("d-none").removeClass("option-clone")
            cloneOption.html("Select Company")
            cloneOption.attr("value","")
            select.append(cloneOption)
            res.forEach(function (data){
                let cloneOption = $(".option-clone").clone().removeClass("d-none").removeClass("option-clone")
                cloneOption.html(data.companyName)
                cloneOption.attr("value",data.companyId)
                select.append(cloneOption)
            })
        },
        error: function(xhr, status, error) {
            hideLoader()
            debugger
            alert("Error obtaining company data")
        }
    })
}


