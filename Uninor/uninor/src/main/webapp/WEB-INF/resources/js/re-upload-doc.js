function setMinimumBdate(){
    const today = new Date();
    const minDate = new Date(today.getFullYear() - 18, today.getMonth(), today.getDate());
    const formattedMinDate = minDate.toISOString().split('T')[0];
    document.getElementById('dob').setAttribute('max', formattedMinDate);
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

$(document).ready(function() {
    $.validator.addMethod("panCard", function(value, element) {
        return this.optional(element) || /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/.test(value);
    }, "Please enter a valid PAN Number");

    $.validator.addMethod("aadharCard", function(value, element) {
        return this.optional(element) || /^[2-9]{1}[0-9]{3}[0-9]{4}[0-9]{4}$/.test(value);
    }, "Please enter a valid Aadhar Card Number");

    $.validator.addMethod("fileFormat", function (value, element) {
        // Check if the file extension is valid
        var fileExtension = value.split('.').pop().toLowerCase();
        return this.optional(element) || (fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'pdf');
    }, "Only .jpg, .jpeg, or .pdf files are allowed.");

    $.validator.addMethod("fileSize", function (value, element) {
        // Check if the file size is within the allowed limit
        var fileSize = element.files[0].size;
        var maxSize = 1024*1024*5; // 5MB
        return this.optional(element) || (fileSize <= maxSize);
    }, "File size exceeds the maximum allowed size of 5MB.");

    $.validator.addMethod("lessThanToday", function(value, element) {
        var today = new Date();
        var inputDate = new Date(value);
        return inputDate < today;
    }, "Date must be less than today's date");

    $("#resubmit-doc-form").validate({
        rules: {
            dob: {
                required: true,
                lessThanToday: true
            },
            panNumber: {
                required: true,
                minlength: 10,
                maxlength: 10,
                panCard: true,
            },
            aadharCardNumber: {
                required: true,
                minlength: 12,
                maxlength: 12,
                aadharCard: true
            },
            panCardUploadedFile:{
                required: true,
                fileFormat: true,
                fileSize: true
            },
            aadharCardUploadedFile: {
                required: true,
                fileFormat: true,
                fileSize: true
            }
        },
        messages: {
            dob: {
                require: "Please select a company name",
                lessThanToday: "Please select a valid date of birth"
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
            panCardUploadedFile:{
                required: "Upload PAN Card",
                fileFormat: "Only .jpg, .jpeg, or .pdf files are allowed.",
                fileSize: "File size upto 5MB accepted"
            },
            aadharCardUploadedFile: {
                required: "Upload Aadhar Card",
                fileFormat: "Only .jpg, .jpeg, or .pdf files are allowed.",
                fileSize: "File size upto 5MB accepted"
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
});

function submitFormData(){
    debugger
    var token = $("#token").val()
    var dob = $("#dob").val()
    var panNumber = $("#panNumber").val()
    var aadharCardNumber = $("#aadharCardNumber").val()
    var panCardUploadedFile = $("#panCardUploadedFile")
    var aadharCardUploadedFile = $("#aadharCardUploadedFile")

    let formData1 = new FormData();
    formData1.append("token", token)
    formData1.append("dob",dob)
    formData1.append("panNumber",panNumber)
    formData1.append("aadharCardNumber",aadharCardNumber)
    if(panCardUploadedFile[0].files[0] != null){
        formData1.append("panCardUploadedFile",panCardUploadedFile[0].files[0])
    }else {
        formData1.append("panCardUploadedFile",'')
    }

    if(aadharCardUploadedFile[0].files[0] != null){
        formData1.append("aadharCardUploadedFile",aadharCardUploadedFile[0].files[0])
    }else {
        formData1.append("aadharCardUploadedFile",'')
    }



    debugger
    $.ajax({
        url: CONTEXT_PATH + '/resubmit-client-documents',
        type: 'POST',
        processData: false,
        contentType: false,
        dataType: 'json',
        enctype: 'multipart/form-data',
        data: formData1,
        success: function (xhr,status,error) {
            debugger
            localStorage.setItem("showReuploadSubmitMessgae", "true")
            window.location.assign(CONTEXT_PATH + "/login")
        },
        error: function(xhr, status, error) {
           // hideLoader()
            debugger
            if (xhr.status === 401 || xhr.status === 400 || xhr.status === 409 || xhr.status === 405 ) {
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
            }
            else {
                showAlert(true,"Error occured at server side!","faliure")
            }
        }
    })

}

function getReuploadFields(){
    var token = $("#token").val()
    $.ajax({
        url: CONTEXT_PATH + '/get-resubmit-fields',
        type: 'POST',
        dataType: 'json',
        data: {
            token : token
        },
        success: function (xhr,status,error) {
            debugger
            console.log(xhr)
            var response = xhr['showResubmitFields']
            if(response.showAadharCardField === true){
                $(".common-aadhar-class").removeClass("hidden-class").removeClass("d-none")
            }else {
                $(".common-aadhar-class").addClass("hidden-class").addClass("d-none")
            }

            if(response.showPanCardField === true){
                $(".common-pan-class").removeClass("hidden-class").removeClass("d-none")
            }else {
                $(".common-pan-class").addClass("hidden-class").addClass("d-none")
            }
        },
        error: function(xhr, status, error) {
            // hideLoader()
            debugger
            if (xhr.status === 401 || xhr.status === 400 || xhr.status === 409 || xhr.status === 405 ) {
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
            }
            else {
                showAlert(true,"Error occured at server side!","faliure")
            }
        }
    })
}


