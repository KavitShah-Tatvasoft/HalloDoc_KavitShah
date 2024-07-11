

function getFilterData(classname) {
    var daysString = "";
    $("." + classname).each(function () {
        if ($(this).is(":checked")) {
            daysString += "1,";
        } else {
            daysString += "0,";
        }
    });

    daysString = daysString.slice(0, -1)
    console.log(daysString);
    return daysString
}

function countAppliedFilters() {

    var count = 0;
    $(".filter-checkbox").each(function () {
        if ($(this).is(":checked")) {
            count += 1;
        }
    });

    return count
}

function clearFilter() {
    $('.filter-checkbox').prop('checked', false);
    $(".filter-applied-count").html("0")
    applyFilter(false)
}

function applyFilter(bool) {
    var isfilterApplied = bool
    var refreshingPlanFilters = getFilterData("filter-on-refreshing-data")
    var fixedDataPlanFilters = getFilterData("filter-on-fixed-data")
    var amountFilters = getFilterData("filter-on-charges")
    var validityFilters = getFilterData("filter-on-validity")
    var addOnDataFilters = getFilterData("filter-on-add-on-data")
    var roamingDataFilters = getFilterData("filter-on-roaming-data")
    var filterCount = countAppliedFilters()

    $(".filter-applied-count").html(filterCount)

    var payload = {}
    payload["isfilterApplied"] = isfilterApplied
    payload["refreshingPlanFilters"] = refreshingPlanFilters
    payload["fixedDataPlanFilters"] = fixedDataPlanFilters
    payload["amountFilters"] = amountFilters
    payload["validityFilters"] = validityFilters
    payload["addOnDataFilters"] = addOnDataFilters
    payload["roamingDataFilters"] = roamingDataFilters

    $.ajax({
        url: CONTEXT_PATH + '/client/get-plan-data',
        type: 'POST',
        data: payload,
        success: function (data) {
            console.log("Helloooo")
            console.log(data)
            $(".popular-plan-count").html("(" + data["Popular Plans"].planCount + ")")
            populatePopularPlanCards(data["Popular Plans"])

            populateCategoryWisePlans(data)
        },
        error: function (data) {
            $(".failure-message-heading").text("Server Error")
            $(".failure-message-modal").text("Failed to fetch recharge plan data. Please try again after sometime.")
            $('#statusErrorsModal').modal('show');
        }
    })
}

function populateCategoryWisePlans(data) {
    var mainContainer = $('.plan-category-container')
    mainContainer.empty()
    var count = 1
    $.each(data, function (index, item) {
        if (index !== "Popular Plans") {
            if(item.prepaid){
                $(".filter-btn").removeClass("d-none")
            }else {
                $(".filter-btn").addClass("d-none")
            }
            if (item.planCount != 0) {
                var categoryContainer = $(".card-container-clone").clone().removeClass("d-none").removeClass("card-container-clone")
                categoryContainer.find(".unique-aria-controls").attr("aria-controls", "plan-category-" + count)
                categoryContainer.find(".change-accordion-id").attr("id", "plan-category-" + count)
                categoryContainer.find(".category-name-multiple-card").text(item.planCategory)
                categoryContainer.find(".plan-count-single-category").text("(" + item.planCount + ")")
                if (item.planCount <= 3) {
                    categoryContainer.find("#view-more-plans-toggle").addClass("d-none")
                }
                var multipleCardsContainer = categoryContainer.find(".multiple-cards-plan-body")
                multipleCardsContainer.empty()

                $.each(item.planList, function (index1, singlePlan) {
                    var singleRechargeCard = $(".single-recharge-card-clone").clone().removeClass("d-none").removeClass("single-recharge-card-clone")
                    singleRechargeCard.find("#recharge-amount-single-card").text(singlePlan.planAmount)
                    singleRechargeCard.find("#view-plan-single-card").attr("onclick", "viewPlanDetails(" + singlePlan.planId + ")")
                    if (singlePlan.planValidity == 0) {
                        singleRechargeCard.find("#validity-days-single-card").text("Base Plan")
                        singleRechargeCard.find(".days-postfix").addClass("d-none")
                    } else {
                        singleRechargeCard.find("#validity-days-single-card").text(singlePlan.planValidity)
                    }
                    singleRechargeCard.find("#data-amount-single-card").text(singlePlan.dataAmount)
                    if (singlePlan.dailyRefreshing === false) {
                        singleRechargeCard.find("#data-amount-unit-single-card").text("GB")
                    }
                    singleRechargeCard.find("#recharge-btn-single-card").attr("onclick", "buyPlan(" + singlePlan.planId + ")")
                    multipleCardsContainer.append(singleRechargeCard)
                    if(index === "Postpaid Plans"){
                        singleRechargeCard.find("#recharge-btn-single-card").attr("onclick", "openRechargeModal(" + singlePlan.planId + ")")
                    }
                })
                count += 1
                mainContainer.append(categoryContainer)
            }
        }

    })
}

function populatePopularPlanCards(data) {

    var popularPlanBody = $(".popular-swiper-body")
    popularPlanBody.empty()

    $.each(data.planList, function (index, item) {
        var card = $(".clone-swipe-card").clone("true").removeClass("d-none").removeClass("clone-swipe-card")
        card.find("#slide-amount-span").text(item.planAmount)
        card.find("#slide-validity-text").text(item.planValidity)
        card.find("#slide-data-amount-text").text(item.dataAmount)
        if (item.dailyRefreshing === false) {
            card.find(".data-unit-text").text("GB")
        }
        card.find("#slide-plan-text").attr("onclick", "viewPlanDetails(" + item.planId + ")")
        card.find("#slide-recharge-btn").attr("onclick", "buyPlan(" + item.planId + ")")
        popularPlanBody.append(card)
    })


}

let toggleState = true;

function getId(element) {

    var ariaControls = element.getAttribute('aria-controls');
    var mainItem = $("#" + ariaControls)
    var textChange = $(element).find('.view-all-card-text');
    if (toggleState) {
        mainItem.find('.recharge-cards-row').children().css('display', 'block');
        textChange.html("View Less")
    } else {
        mainItem.find('.recharge-cards-row').children().slice(3).css('display', 'none');
        textChange.html("View More")
    }
    toggleState = !toggleState;
    const chevronDownArrow = $(element).find('.chevron-arrows');
    chevronDownArrow.toggleClass('rotated');
}

var swiper = new Swiper(".slide-content", {
    slidesPerView: 3, spaceBetween: 25, loop: true, centerSlide: 'true', fade: 'true', grabCursor: 'true', pagination: {
        el: ".swiper-pagination", clickable: true, dynamicBullets: true,
    }, navigation: {
        nextEl: ".swiper-button-next", prevEl: ".swiper-button-prev",
    }, autoplay: {
        delay: 5000, // delay between slides in milliseconds
        disableOnInteraction: true, // autoplay will not be disabled after user interaction
    }, breakpoints: {
        0: {
            slidesPerView: 1,
        }, 520: {
            slidesPerView: 2,
        }, 950: {
            slidesPerView: 3,
        },
    },
});

document.querySelectorAll('.solution_card').forEach(card => {
    card.addEventListener('mouseover', () => {
        swiper.autoplay.stop();
    });
    card.addEventListener('mouseout', () => {
        swiper.autoplay.start();
    });
});

function openRechargeModal(planId){
    $("#confirmRechargeButton").attr("onclick","buyPostPaidPlan(" + planId + ")")
    $("#confirm-postpaid-recharge").modal('show')
}

function  buyPostPaidPlan(planId){
    $.ajax({
        url: CONTEXT_PATH + '/client/buy-post-paid-plan',
        type: 'POST',
        data: {
            planId: planId
        },
        success: function (data) {
            $(".success-message-modal").addClass("d-none")
            $(".success-message-heading-modal").text("Recharge done successfully!")
            $('#statusSuccessModal').modal('show');
        },
        error: function(xhr, status, error) {
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
                    $(".failure-message-heading").text("Error")
                    $(".failure-message-modal").text(errorMessage)
                    $('#statusErrorsModal').modal('show');
                }
            } else {
                $(".failure-message-heading").text("Server Error")
                $(".failure-message-modal").text("Failed to complete the recharge process!")
                $('#statusErrorsModal').modal('show');
            }
        }
    })
}

function buyPlan(planId) {
    debugger
    $(".close-view-plan-modal").click()
    $(".cupon-code-recharge-tab").val("").prop("readonly", false);
    $(".cupon-code-error").addClass("d-none")
    $(".i-icon-recahrge").addClass("d-none")
    $(".recahrge-wallet-amount-input").val("")
    $(".applied-btn").addClass("d-none")
    $(".remove-cupon").addClass('d-none')
    $(".verify-code").removeClass('d-none')
    var myModal = new bootstrap.Modal(document.getElementById('recharge-plan'), {});
    $.ajax({
        url: CONTEXT_PATH + '/client/buy-plan-details',
        type: 'POST',
        data: {
            planId: planId
        },
        success: function (data) {
            var plan = data["Plan"]
            $(".plan-id-field").text(plan.planId)
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

            $("#recahrge-plan-price-id").text(plan.planPriceWithoutTax)
            $("#recharge-discount-applied").text("0.00")
            $("#tax-amount-applied").text(plan.taxAmount)
            $("#recharge-total-amount").text(plan.planAmount)
            $("#recharge-wallet-amount").text(plan.walletAmount)
            $("#recharge-payable-amount").text(plan.planAmount)
            $(".final-pay-amount-btn").text(plan.planAmount)

            myModal.show()
        },
        error: function(xhr, status, error) {
            debugger
            $(".recharge-modal-close").click()
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
                    $(".failure-message-heading").text("Error")
                    $(".failure-message-modal").text(errorMessage)
                    $('#statusErrorsModal').modal('show');
                }
            } else {
                $(".failure-message-heading").text("Server Error")
                $(".failure-message-modal").text("Failed to fetch plan data!")
                $('#statusErrorsModal').modal('show');
            }
        }
    })
}


function viewPlanDetails(planId) {
    // $(".cupon-code-error").removeClass("d-none")
    $.ajax({
        url: CONTEXT_PATH + '/client/get-specific-plan-details',
        type: 'POST',
        data: {
            planId: planId
        },
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
                    $(".failure-message-heading").text("Some Error Occurred")
                    $(".failure-message-modal").text(errorMessage)
                    $('#statusErrorsModal').modal('show');
                }
            } else {
                $(".failure-message-heading").text("Server Error")
                $(".failure-message-modal").text("Some Error Occurred at server side!")
                $('#statusErrorsModal').modal('show');
            }
        }
    })
}



function verifyCuponWalletChanges(){
    var planId = $(".plan-id-field").text()
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
            url: CONTEXT_PATH + '/client/verify-cupon-wallet-details',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(payload),
            success: function(xhr, status, success) {
                // $(".i-icon-recahrge").addClass("d-none")
                console.log(xhr)
                console.log(xhr.updatedDetails)
                var plan = xhr.updatedDetails
                $(".plan-id-field").text(plan.planId)
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
                $("#recahrge-plan-price-id").text(plan.planPriceWithoutTax)
                $("#recharge-discount-applied").text(plan.discountApplied)
                $("#tax-amount-applied").text(plan.taxAmount)
                $("#recharge-total-amount").text(plan.planPriceWithoutWallet)
                $("#recharge-wallet-amount").text(plan.walletAmount)
                $("#recharge-payable-amount").text(plan.finalPayableAmount)
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
                $(".recharge-modal-close").click()
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
                        $(".failure-message-heading").text("Error")
                        $(".failure-message-modal").text(errorMessage)
                        $('#statusErrorsModal').modal('show');
                    }
                } else {
                    $(".failure-message-heading").text("Server Error")
                    $(".failure-message-modal").text("Some error occured at server side")
                    $('#statusErrorsModal').modal('show');
                }
                console.log(xhr)
            }
        })
    }


}

function checkWalletBalance(){
    var clientId = localStorage.getItem("clientId")
    var amount = $(".recahrge-wallet-amount-input").val()
    amount = Number(amount).toFixed(2)
    var payload = {}
    payload["clientId"] = clientId
    payload["amount"] = amount
    $.ajax({
        url: CONTEXT_PATH + '/client/check-client-wallet-amount',
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(payload),
        success: function (data) {
            $(".i-icon-recahrge").addClass("d-none")
        },
        error: function(xhr, status, error) {
            debugger
            if (xhr.status === 403) {
                $(".i-icon-recahrge").removeClass("d-none")
            } else {
                $(".failure-message-heading").text("Server Error")
                $(".failure-message-modal").text("Error occured at server side")
                $('#statusErrorsModal').modal('show');
            }
        }
    })
}

function verifyCuponCode(bool) {
    if (bool) {
        var clientId = localStorage.getItem("clientId")
        var cuponCode = $(".cupon-code-recharge-tab").val()

        var payload = {}
        payload["clientId"] = clientId
        payload["cuponCode"] = cuponCode
        $.ajax({
            url: CONTEXT_PATH + '/client/check-cupon-validity',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(payload),
            success: function (data) {
                $(".verify-code").addClass('d-none')
                $(".remove-cupon").removeClass('d-none')
            },
            error: function(xhr, status, error) {
                debugger
                if (xhr.status === 400) {
                   alert("cupon errrorrr")
                } else {
                    console.log("Some other error")
                }
            }
        })


    } else {
        // $(".remove-cupon").addClass('d-none')
        // $(".verify-code").removeClass('d-none')
    }

}

function removeCuponCode(){
    debugger
    $(".cupon-code-recharge-tab").val("")
    $(".remove-cupon").addClass('d-none')
    $(".verify-code").removeClass('d-none')
    $(".applied-btn").addClass("d-none")

    verifyCuponWalletChanges()
}

function validateCuponCode(){
    var cuponCode = $(".cupon-code-recharge-tab").val()
    const pattern = new RegExp('^([A-Z0-9]{6})?$');
    if(pattern.test(cuponCode)){
        $(".cupon-code-error").addClass("d-none")
        return true
    }else {
        $(".cupon-code-error").removeClass("d-none")
        $(".cupon-code-error").text("Invalid Cupon Code")
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



function buySelectedRechargePlan(){
    var planId = $(".plan-id-field").text()
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
            url: CONTEXT_PATH + '/client/buy-recharge-plan',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(payload),
            success: function(xhr, status, success) {
                // $(".i-icon-recahrge").addClass("d-none")
                console.log(xhr)
                console.log(xhr.downloadPath)
                $(".success-message-heading-modal").text("Plan Bought Successfully!")
                $(".success-message-modal").text(xhr.couponMessage).removeClass("d-none")
                $('#statusSuccessModal').modal('show');
                window.open(xhr.downloadPath, '_blank');
            },
            error: function(xhr, status, error) {
                debugger
                $(".recharge-modal-close").click()
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
                        $(".failure-message-heading").text("Error")
                        $(".failure-message-modal").text(errorMessage)
                        $('#statusErrorsModal').modal('show');
                    }
                } else {
                    $(".failure-message-heading").text("Server Error")
                    $(".failure-message-modal").text("Error Occurred at server side!")
                    $('#statusErrorsModal').modal('show');
                }
                console.log(xhr)
            }
        })
    }


}

$(document).ready(function(){
    $(".close-confirm-postpaid-recharge-modal").click(function(){
        $('#confirm-postpaid-recharge').modal('hide');
    })
});




