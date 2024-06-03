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
                    let errorMessage = errorResponse.errors
                    showAlert(true,errorMessage,"faliure")
                }
            } else {
                console.log("Some other error")
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
}

function hideCompanyInput(){
    $(".company-name-container").addClass("d-none")
}

function getNumberSuggestions(){
    showLoader()
    $.ajax({
        url: CONTEXT_PATH + '/get-available-number-suggestions',
        type: 'GET',
        success: function (res) {
            debugger
            hideLoader()
            console.log(res)
        },
        error: function(xhr, status, error) {
            hideLoader()
            console.log("Errorr")
        }
    })

}
