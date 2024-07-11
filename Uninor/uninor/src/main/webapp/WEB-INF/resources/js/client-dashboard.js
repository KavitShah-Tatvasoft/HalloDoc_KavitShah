function showLoginLoader(){
    var showToaster = localStorage.getItem("showLoginToaster")
    if(showToaster === "true"){
        showAlert(true,"Logged in successfully!","success")
        localStorage.setItem("showLoginToaster","false")
    }
}

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});

let barChartOb
let polarChartOb

function getDashboardData(){
        // showLoader()
        $.ajax({
            url: CONTEXT_PATH + '/client/get-dashboard-data',
            type: 'GET',
            dataType: 'json',
            success: function (xhr, status, error) {
                var responseData = xhr.DashboardDetails
                console.log(responseData)
                if(responseData.simBlocked){
                    $(".block-sim-btn").addClass("d-none")
                    $(".unblock-sim-btn").removeClass("d-none")
                }else{
                    $(".block-sim-btn").removeClass("d-none")
                    $(".unblock-sim-btn").addClass("d-none")
                }

                $("#client-name-id").text(responseData.clientName)
                $(".phone-number-flex-col .sim-type").text(responseData.mobileType)
                $(".phone-number-flex-col .phone-number").text(responseData.mobileNumber)
                var walletAmount = parseFloat(responseData.walletAmount).toFixed(2)
                $("#dashboard-wallet-amount").text(walletAmount)
                $("#data-left-progress-top").text(responseData.remainingDataAmount)
                $("#total-data-progress-top").text(responseData.totalDataAmount)
                $(".renews-in-text").text(responseData.renewTime)
                $(".current-subscription-amount").text(responseData.planAmount)
                $(".subscription-expirtaion-date").text(responseData.planExpiryDate)
                $(".data-progress-bar").css('width', responseData.availableDataPercentage + '%');
                $(".voice-usage-details").text(responseData.voiceUsage)
                if(responseData.planExpired){
                    $(".active-plan-row").addClass("d-none")
                    $(".no-active-plan-row").removeClass("d-none")

                    if(!responseData.simPrepaid){
                        $("#view-plan-btn").addClass("d-none")
                        $("#pay-postpaid-bill").removeClass("d-none")
                    }

                }else{
                    $(".active-plan-row").removeClass("d-none")
                    $(".no-active-plan-row").addClass("d-none")
                }

                if(!responseData.simPrepaid) {
                    $(".switch-postpaid-btn").addClass("d-none")
                    $(".switch-prepaid-btn").removeClass("d-none")
                    $(".toggle-type-text").text("Switch to Prepaid")
                    $("#confirmSwitchButton").text("Switch to Prepaid")
                    $(".para-type-toggle").text("prepaid")
                    $(".add-additional-data").addClass("d-none")
                    $(".extra-data-flex").removeClass("d-none")
                    $('.activate-roaming-btn .single-icon img').css({
                        'filter': 'invert(40%) sepia(9%) saturate(0%) hue-rotate(145deg) brightness(98%) contrast(103%)'
                    });
                    $(".activate-roaming-btn").attr('data-bs-toggle','').attr('data-bs-target','');
                    if(responseData.postpaidExtraDataUsed){
                        $("#data-left-progress-top").text("0.00 GB")
                        $(".extra-data-usage").text(responseData.remainingDataAmount)
                        $(".data-progress-bar").css('width', '0%');
                    }

                }else
                {
                    $(".switch-postpaid-btn").removeClass("d-none")
                    $(".switch-prepaid-btn").addClass("d-none")
                    $(".toggle-type-text").text("Switch to Postpaid")
                    $("#confirmSwitchButton").text("Switch to Postpaid")
                    $(".para-type-toggle").text("postpaid")
                    $('.activate-roaming-btn .single-icon img').css({
                        'filter': 'invert(25%) sepia(81%) saturate(2368%) hue-rotate(226deg) brightness(92%) contrast(100%)'
                    });
                    $(".activate-roaming-btn").attr('data-bs-toggle','modal').attr('data-bs-target','#toggle-roaming-service');
                }

                if(responseData.roamingActive){
                    $(".change-roaming-btn-status").text("Deactivate")
                    $(".roaming-toggle-type-text").text("Deactivate Roaming")
                    $(".roaming-type-toggle").text("off")
                    $(".roaming-status-text").text("activate")
                    $("#confirmRoamingSwitchButton").text("Deactivate Roaming")
                    $(".renews-time-voice-flex").removeClass("d-none")
                    $(".renews-time-text-flex").addClass("d-none")
                }else{
                    $(".roaming-toggle-type-text").text("Activate Roaming")
                    $(".roaming-type-toggle").text("on")
                    $(".roaming-status-text").text("deactivate")
                    $("#confirmRoamingSwitchButton").text("Activate Roaming")
                    $(".change-roaming-btn-status").text("Activate")
                    $(".renews-time-voice-flex").addClass("d-none")
                    $(".renews-time-text-flex").removeClass("d-none")
                }

                if(barChartOb){
                    barChartOb.destroy();
                }

                if(polarChartOb){
                    polarChartOb.destroy();
                }
                const ctx1 = document.getElementById('myChart1');
                barChartOb = new Chart(ctx1, {
                    type: 'bar',
                    data: {
                        labels: responseData.usageDateList,
                        datasets: [{
                            label: 'Data Used in MB',
                            data: responseData.usageAmountList,
                            backgroundColor: 'rgba(55, 80, 235, 1)',
                            borderColor: 'rgba(55, 80, 235, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        maintainAspectRatio: false,
                        borderJoinStyle: "round",
                        scales: {
                            x: {
                                title: {
                                    display: true,
                                    text: 'Usage Date',
                                    color: 'black', // Color of the title text
                                    font: {
                                        size: 14, // Font size
                                    }
                                }
                            },
                            y: {
                                title: {
                                    display: true,
                                    text: 'Data Usage',
                                    color: 'black', // Color of the title text
                                    font: {
                                        size: 14, // Font size
                                    }
                                }
                            }
                    }   }
                });

                const ctx = document.getElementById('myChart');

                polarChartOb = new Chart(ctx, {
                    type: 'polarArea',
                    data: {
                        labels: responseData.topCategoryName,
                        datasets: [{
                            label: 'Bought Count',
                            data: responseData.topCategoryBoughtCount,
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.8)',
                                'rgba(54, 162, 235, 0.8)',
                                'rgba(255, 206, 86, 0.8)',
                                'rgba(75, 192, 192, 0.8)',
                                'rgba(153, 102, 255, 0.8)',
                                'rgba(76, 175, 80, 0.8)'

                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(76, 175, 80, 0.8)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        plugins: {
                            legend: {
                                labels: {
                                    font: {
                                        size: 11 // Set the font size here
                                    }
                                },
                                itemSize: 10, // Set the size of the color boxes
                                boxWidth: 10,
                                boxHeight: 20
                            }
                        },
                    }
                });

                showLoginLoader()

            },
            error: function(xhr, status, error) {
                // hideLoader()
                debugger
                if (xhr.status === 400 || xhr.status === 404 || xhr.status === 401) {
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
                    showAlert(true,"Error at server side!","faliure")
                }
            }
        })
}

// window.addEventListener('resize', function() {
//     polarChartOb.resize();
//     barChartOb.resize();
// });

function getPlanData(){
    $.ajax({
        url: CONTEXT_PATH + '/client/get-active-plan-details',
        type: 'GET',
        success: function (data) {
            var plan = data["Plan"]
            $(".recharge-modal-amount").text(plan.planAmount)
            $(".recharge-modal-validity").text(plan.planValidity)
            $(".recharge-modal-total-data").text(plan.totalData + " GB")
            if (plan.dailyRefreshing != true) {
                $(".recharge-modal-refresh").text("GB")
                $(".recharge-modal-sms-refresh").text("SMS")
            } else {
                $(".recharge-modal-refresh").text("GB/Day")
                $(".recharge-modal-sms-refresh").text("SMS/Day")
            }
            $(".recharge-modal-data-amount").text(plan.dataAmount)
            $(".recharge-modal-voice-amount").text(plan.voiceAllowance)
            $(".recharge-modal-sms-amount").text(plan.smsAllowance)
            $(".recharge-modal-additional-data-amount").text(plan.additionalData)
            $(".show-recharge-modal").attr("onclick", "buyPlan(" + plan.planId + ")")

            var myModal = new bootstrap.Modal(document.getElementById('view-plan-modal'), {});
            myModal.show()
        },
        error: function(xhr, status, error) {
            debugger
            if (xhr.status === 400 || xhr.status === 401 || xhr.status === 404 || xhr.status === 405) {
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
                showAlert(true,"Server error occured!","faliure")
            }
        }
    })
}


$(".close-toggle-modal").click(function (){
    $('#toggle-service').modal('hide');

})

$(".close-roaming-toggle-modal").click(function (){
    $('#toggle-roaming-service').modal('hide');
    $(".roaming-confirm-input").text("").val("")
    $(".confirm-error-text").addClass("d-none")
})

$(".close-block-toggle-modal").click(function (){
    $('#toggle-block-service').modal('hide');
    $('#toggle-unblock-service').modal('hide');
    $(".unblock-confirm-input").text("").val("")
    $(".block-confirm-input").text("").val("")
    $(".confirm-error-text").addClass("d-none")
})


function removeCuponCode(){
    debugger
    $(".cupon-code-recharge-tab").val("")
    $(".remove-cupon").addClass('d-none')
    $(".verify-code").removeClass('d-none')
    $(".applied-btn").addClass("d-none")

    verifyPostpaidCuponWalletChanges()
}

function validateCuponCode(){
    var cuponCode = $(".cupon-code-recharge-tab").val()
    const pattern = new RegExp('^([A-Z0-9]{6})?$');
    if(pattern.test(cuponCode)){
        $(".cupon-code-error").addClass("d-none")
        return true
    }else {
        $(".cupon-code-error").removeClass("d-none").text("Invalid Cupon Code")
        return false
    }
}

function validateWalletAmount(){
    var walletAmount = $(".recahrge-wallet-amount-input").val()
    const pattern = new RegExp('^(\\d+(\\.\\d+)?)?$')
    if(pattern.test(walletAmount)){
        $(".i-icon-recahrge").addClass("d-none")
        return true
    }else {
        $(".i-icon-recahrge").removeClass("d-none")
        return false
    }
}



function getPaymentDetails(){
    $.ajax({
        url: CONTEXT_PATH + '/client/postpaid-billing-details',
        type: 'GET',
        success: function (xhr, status, error) {
            console.log(xhr)
            var plan = xhr.Plan
            localStorage.setItem("planId",plan.planId)
            $(".recharge-modal-amount").text(plan.planAmount)
            $(".recharge-modal-validity").text(plan.planValidity)
            $(".recharge-modal-total-data").text(plan.totalData + " GB")
            if (plan.dailyRefreshing != true) {
                $(".recharge-modal-refresh").text("GB")
                $(".recharge-modal-sms-refresh").text("SMS")
            } else {
                $(".recharge-modal-refresh").text("GB/Day")
                $(".recharge-modal-sms-refresh").text("SMS/Day")
            }
            $(".recharge-modal-data-amount").text(plan.dataAmount)
            $(".recharge-modal-voice-amount").text(plan.voiceAllowance)
            $(".recharge-modal-sms-amount").text(plan.smsAllowance)
            $(".recharge-modal-additional-data-amount").text(plan.additionalData)
            $(".recharge-modal-additional-data-charge").text(plan.extraDataCharges)
            $(".recharge-modal-additional-sms-charge").text(plan.extraSmsCharges)
            $("#recahrge-plan-price-id").text(plan.planPriceWithoutTax)
            $("#recahrge-plan-sms-used").text(plan.smsUsed)
            $("#recahrge-plan-sms-charges").text(plan.smsCharge)
            $("#recahrge-plan-extra-data-used").text(plan.dataUsed)
            $("#recahrge-plan-extra-data-charges").text(plan.dataCharges)
            $("#recharge-discount-applied").text("0.00")
            $("#tax-amount-applied").text(plan.taxAmount)
            $("#recharge-wallet-amount").text(plan.walletAmount)
            $("#recharge-payable-amount").text(plan.payableAmount)
            $("#recharge-total-amount").text(plan.totalAmount)
            $(".final-pay-amount-btn").text(plan.payableAmount)

        },
        error: function(xhr, status, error) {
            debugger
            if (xhr.status === 400 || xhr.status === 401 || xhr.status === 405 || xhr.status === 409) {
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
                showAlert(true,"Server side error!","faliure")
            }
        }
    })
}


function toggleServiceType(){
    var confirmText = $(".confirm-input").val().toUpperCase()
    if(confirmText === "CONFIRM"){
        $(".confirm-error-text").addClass("d-none")
        $.ajax({
            url: CONTEXT_PATH + '/client/toggle-sim-type',
            type: 'GET',
            success: function (xhr, status, error) {
                $("#toggle-service").modal('hide');
                console.log(xhr)
                $(".success-message-modal").addClass("d-none")
                if(xhr.serviceType === "prepaid" && xhr.payment === "true"){
                    localStorage.setItem("showErrorOnClose","true")
                    getPaymentDetails()
                    $(".switch-prepaid-btn").addClass("d-none")
                    $(".switch-postpaid-btn").removeClass("d-none")
                    $("#recharge-plan").modal('show');
                    getDashboardData()
                }
                if(xhr.serviceType === "prepaid" && xhr.payment === "false"){
                    $(".success-message-heading-modal").text("Service changed to prepaid!")
                    $('#statusSuccessModal').modal('show');
                    // showAlert(true,"Service changed to prepaid!","success")
                    $(".switch-prepaid-btn").addClass("d-none")
                    $(".switch-postpaid-btn").removeClass("d-none")
                    getDashboardData()
                }
                if(xhr.serviceType === "postpaid"){
                    // showAlert(true,"Service changed to postpaid!","success")
                    $(".success-message-heading-modal").text("Service changed to postpaid!")
                    $('#statusSuccessModal').modal('show');
                    $(".switch-prepaid-btn").removeClass("d-none")
                    $(".switch-postpaid-btn").addClass("d-none")
                    getDashboardData()
                }
            },
            error: function(xhr, status, error) {
                debugger
                $('#toggle-service').modal('hide');
                if (xhr.status === 400 || xhr.status === 401 || xhr.status === 405 || xhr.status === 409) {
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
                    showAlert(true,"Server error","faliure")
                }
            }
        })
    }
    else {
        $(".confirm-error-text").removeClass("d-none")
    }
}

function hideConfirmError(){
    $(".confirm-error-text").addClass("d-none")
    $(".confirm-input").val("")

}



function closePaymentModal(){
    $(".cupon-code-recharge-tab").val("")
    $(".remove-cupon").addClass('d-none')
    $(".verify-code").removeClass('d-none')
    $(".applied-btn").addClass("d-none")
    var showError = localStorage.getItem("showErrorOnClose")
    if(showError === "true"){
        localStorage.setItem("showErrorOnClose","false")
        // showAlert(true,"Service wasn't changed to prepaid! Complete the payment to change service type!","faliure")
        $(".failure-message-heading").text("Service changed failed!")
        $(".failure-message-modal").text("Complete the payment to change service typee!")
        $('#statusErrorsModal').modal('show');
    }
}

function verifyPostpaidCuponWalletChanges(){
    var planId = localStorage.getItem("planId")
    var cuponCode = $(".cupon-code-recharge-tab").val()
    var enteredWalletAmount = $(".recahrge-wallet-amount-input").val()
    enteredWalletAmount = Number(enteredWalletAmount).toFixed(2)
    var payload = {}

    payload["clientId"] = localStorage.getItem("clientId")
    payload["planId"] = planId
    payload["cuponCode"] = cuponCode
    payload["enteredWalletAmount"] = enteredWalletAmount

    var cuponVerification = validateCuponCode()
    var walletVerification = validateWalletAmount()

    if(walletVerification && cuponVerification){
        $.ajax({
            url: CONTEXT_PATH + '/client/verify-postpaid-payment-details',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(payload),
            success: function(xhr, status, success) {
                console.log(xhr)
                var plan = xhr.updatedDetails
                localStorage.setItem("planId",plan.planId)
                $(".recharge-modal-amount").text(plan.planAmount)
                $(".recharge-modal-validity").text(plan.planValidity)
                $(".recharge-modal-total-data").text(plan.totalData + " GB")
                if (plan.dailyRefreshing != true) {
                    $(".recharge-modal-refresh").text("GB")
                    $(".recharge-modal-sms-refresh").text("SMS")
                } else {
                    $(".recharge-modal-refresh").text("GB/Day")
                    $(".recharge-modal-sms-refresh").text("SMS/Day")
                }
                $(".recharge-modal-data-amount").text(plan.dataAmount)
                $(".recharge-modal-voice-amount").text(plan.voiceAllowance)
                $(".recharge-modal-sms-amount").text(plan.smsAllowance)
                $(".recharge-modal-additional-data-amount").text(plan.additionalData)
                $(".recharge-modal-additional-data-charge").text(plan.extraDataCharges)
                $(".recharge-modal-additional-sms-charge").text(plan.extraSmsCharges)
                $("#recahrge-plan-price-id").text(plan.planPriceWithoutTax)
                $("#recahrge-plan-sms-used").text(plan.smsUsed)
                $("#recahrge-plan-sms-charges").text(plan.smsCharge)
                $("#recahrge-plan-extra-data-used").text(plan.dataUsed)
                $("#recahrge-plan-extra-data-charges").text(plan.dataCharges)
                $("#recharge-discount-applied").text(plan.discountApplied)
                $("#tax-amount-applied").text(plan.taxAmount)
                $("#recharge-wallet-amount").text(plan.walletAmount)
                $("#recharge-payable-amount").text(plan.finalPayableAmount)
                $("#recharge-total-amount").text(plan.planPriceWithoutWallet)
                $(".final-pay-amount-btn").text(plan.finalPayableAmount)
                $(".recahrge-wallet-amount-input").text(plan.usedWalletAmount)
                $(".recahrge-wallet-amount-input").val(plan.usedWalletAmount)
                if(plan.walletError){
                    $(".i-icon-recahrge").removeClass("d-none")
                }else {
                    $(".i-icon-recahrge").addClass("d-none")
                }

                if(plan.cuponError){
                    $(".cupon-code-error").removeClass("d-none")
                    $(".cupon-code-error").text(plan.cuponErrorMessage)
                }else{
                    $(".cupon-code-error").addClass("d-none")
                }

                if(plan.cuponApplied){
                    $(".applied-btn").removeClass("d-none")
                    $(".remove-cupon").removeClass('d-none')
                    $(".verify-code").addClass('d-none')
                    $(".cupon-code-recharge-tab").prop("readonly", true);
                }else {
                    $(".applied-btn").addClass("d-none")
                    $(".remove-cupon").addClass('d-none')
                    $(".verify-code").removeClass('d-none')
                    $(".cupon-code-recharge-tab").prop("readonly", false);
                }

            },
            error: function(xhr, status, error) {
                debugger
                // $(".recharge-modal-close").click()
                if (xhr.status === 400) {
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
                console.log(xhr)
            }
        })
    }


}

function payPostPaidBill(){
    var planId = localStorage.getItem("planId")
    var cuponCode = $(".cupon-code-recharge-tab").val()
    var enteredWalletAmount = $(".recahrge-wallet-amount-input").val()
    enteredWalletAmount = Number(enteredWalletAmount).toFixed(2)
    var payload = {}

    payload["clientId"] = localStorage.getItem("clientId")
    payload["planId"] = planId
    payload["cuponCode"] = cuponCode
    payload["enteredWalletAmount"] = enteredWalletAmount

    var cuponVerification = validateCuponCode()
    var walletVerification = validateWalletAmount()

    if(walletVerification && cuponVerification){
        $.ajax({
            url: CONTEXT_PATH + '/client/pay-postpaid-bill',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(payload),
            success: function(xhr, status, success) {
                console.log(xhr)
                localStorage.setItem("showErrorOnClose","false")

                $('#recharge-plan').modal('hide');
                $(".success-message-heading-modal").text("Payment Successful!");
                $(".success-message-modal").text("Sim Service changed to prepaid.").removeClass("d-none")
                $('#statusSuccessModal').modal('show');
                $(".switch-prepaid-btn").removeClass("d-none")
                $(".switch-postpaid-btn").addClass("d-none")
                getDashboardData()
                window.open(xhr.filePath, '_blank');
            },
            error: function(xhr, status, error) {
                debugger
                // $(".recharge-modal-close").click()

                    let errorResponse;
                    try {
                        errorResponse = JSON.parse(xhr.responseText);
                    } catch (e) {
                        errorResponse = xhr.responseText;
                    }
                    console.log(errorResponse)
                    if (errorResponse.errors) {
                        let errorMessage = errorResponse.errors
                        localStorage.setItem("showErrorOnClose","false")
                        $('#recharge-plan').modal('hide');
                        $("#failure-message-modal").text(errorMessage)
                        $('#statusErrorsModal').modal('show');
                    }
            }
        })
    }


}

function changeRoamingStatus(){
    var confirmText = $(".roaming-confirm-input").val().toUpperCase()
    if(confirmText === "CONFIRM"){
        $(".confirm-error-text").addClass("d-none")
        $.ajax({
            url: CONTEXT_PATH + '/client/toggle-roaming-status',
            type: 'GET',
            success: function (xhr, status, error) {
                $("#toggle-roaming-service").modal('hide');
                $(".change-roaming-btn-status").text(xhr.toggleTo)
                $(".roaming-confirm-input").text("").val("")
                if(xhr.toggleTo == "Activate"){
                    $(".roaming-toggle-type-text").text("Activate Roaming")
                    $(".roaming-type-toggle").text("on")
                    $(".roaming-status-text").text("deactivate")
                    $(".confirmRoamingSwitchButton").text("Activate Roaming")
                }else{
                    $(".roaming-toggle-type-text").text("Deactivate Roaming")
                    $(".roaming-type-toggle").text("off")
                    $(".roaming-status-text").text("activate")
                    $(".confirmRoamingSwitchButton").text("Deactivate Roaming")
                }
                getDashboardData()
                showAlert(true,xhr.message,"success")
                console.log("heyy")
            },
            error: function(xhr, status, error) {
                debugger
                $('#toggle-roaming-service').modal('hide');
                $(".roaming-confirm-input").text("").val("")
                $(".confirm-error-text").addClass("d-none")
                if (xhr.status === 400 || xhr.status === 401 || xhr.status === 405 || xhr.status === 409) {
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
                    showAlert(true,"Server error","faliure")
                }
            }
        })

    }else{
        $(".confirm-error-text").removeClass("d-none")
    }
}

function createBlockRequest(){
    var confirmText = $(".block-confirm-input").val().toUpperCase()
    if(confirmText === "CONFIRM"){
        $(".confirm-error-text").addClass("d-none")
        $(".unblock-confirm-input").text("").val("")
        $(".block-confirm-input").text("").val("")

        $.ajax({
            url: CONTEXT_PATH + '/client/create-block-request',
            type: 'GET',
            success: function (xhr, status, error) {
                console.log(xhr)
                $('#toggle-block-service').modal('hide')

                showAlert(true,xhr.message,"success")
            },
            error: function(xhr, status, error) {
                debugger
                $('#toggle-block-service').modal('hide')
                if (xhr.status === 400 || xhr.status === 401 || xhr.status === 405 || xhr.status === 409) {
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
                    showAlert(true,"Server error","faliure")
                }
            }
        })

    }else{
        $(".confirm-error-text").removeClass("d-none")
    }
}

function onlyNumber(str) {
    let regex = /^\d{6}$/;
    return regex.test(str);
}

function createUnblockRequest(){
    var pukNumber = $(".unblock-confirm-input").val()

    if(onlyNumber(pukNumber)){
        $(".confirm-error-text").addClass("d-none")
        $.ajax({
            url: CONTEXT_PATH + '/client/create-unblock-request',
            type: 'GET',
            data: {
                pukCode : pukNumber
            },
            success: function (xhr, status, error) {
                console.log(xhr)
                $('#toggle-unblock-service').modal('hide')
                showAlert(true,xhr.message,"success")
            },
            error: function(xhr, status, error) {
                debugger
                $('#toggle-unblock-service').modal('hide')
                if (xhr.status === 400 || xhr.status === 401 || xhr.status === 405 || xhr.status === 409) {
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
                    showAlert(true,"Server error","faliure")
                }
            }
        })

    }else{
        $(".confirm-error-text").removeClass("d-none")
    }
}





