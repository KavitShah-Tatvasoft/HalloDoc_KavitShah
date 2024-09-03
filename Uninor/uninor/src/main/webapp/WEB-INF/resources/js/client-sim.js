$(document).ready(function(){
    $(".active-common-class").removeClass("active-link-sidebar")
    $(".active-sim").addClass("active-link-sidebar")
});
function getSimDetails(){

    $.ajax({
        url: CONTEXT_PATH + '/client/get-current-sim-details',
        type: 'GET',
        dataType: 'json',
        success: function (xhr, status, error) {
            var details = xhr['simDetailsDto']
            console.log(details)
            $("#fname").val(details.firstName).prop('readonly', true)
            $("#lname").val(details.lastName).prop('readonly', true)
            $("#mobileNumber").val(details.mobileNumber).prop('readonly', true)
            $("#iccid").val(details.iccidNumber).prop('readonly', true)
            $("#imsi").val(details.imsiNumber).prop('readonly', true)
            $("#imei").val(details.imeiNumber).prop('readonly', true)
            $("#puk").val(details.pukNumber).prop('readonly', true)
            $("#sim-type").val(details.simType).prop('readonly', true)
            $("#block-status").val(details.blockStatus).prop('readonly', true)
            $("#roaming").val(details.roamingStatus).prop('readonly', true)
        },
        error: function(xhr, status, error) {
            debugger
            if (xhr.status === 401 || xhr.status === 404 || xhr.status === 400 || xhr.status === 405 || xhr.status === 409) {
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

$(".close-block-toggle-modal").click(function (){
    $('#deactivate-modal').modal('hide');
    $(".deactivate-confirm-input").text("").val("")
    $(".confirm-error-text").addClass("d-none")
    $(".modal-backdrop").removeClass("show")
})

function deactivateSim(){
    var text = $(".deactivate-confirm-input").val().toUpperCase()
    if(text === "CONFIRM"){
        createDeactivationRequest()
    }else {
        $(".confirm-error-text").removeClass('d-none')
    }
}

function redirectFunction(){
    window.location.assign(CONTEXT_PATH + "/error/deactivation-request")
}

function createDeactivationRequest(){

    $.ajax({
        url: CONTEXT_PATH + '/client/create-sim-deactivation-request',
        type: 'GET',
        dataType: 'json',
        success: function (xhr, status, error) {
            console.log(xhr)
            $(".close-block-toggle-modal").click()
            showAlert(true,xhr["message"],"success")
            setTimeout(redirectFunction(), 2000);
        },
        error: function(xhr, status, error) {
            debugger
            if (xhr.status === 401 || xhr.status === 404 || xhr.status === 400 || xhr.status === 405 || xhr.status === 409) {
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