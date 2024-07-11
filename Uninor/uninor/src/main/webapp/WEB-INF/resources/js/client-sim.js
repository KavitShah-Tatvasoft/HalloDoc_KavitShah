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

function createDeactivationRequest(){

    $.ajax({
        url: CONTEXT_PATH + '/client/create-sim-deactivation-request',
        type: 'GET',
        dataType: 'json',
        success: function (xhr, status, error) {
            console.log(xhr)
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