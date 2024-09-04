

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
        url: CONTEXT_PATH + '/admin/get-plan-data',
        type: 'POST',
        data: payload,
        success: function (data) {
            console.log(data)
            $(".popular-plan-count").html("(" + data["Popular Plans"].planCount + ")")
            populateCategoryWisePlans(data)
            var totalPlanCount = 0
            for (const key in data) {
                if (data.hasOwnProperty(key)) {
                    const plan = data[key];
                    // Check if the planCategory is not 'Popular Plans' before adding the count
                    if (plan.planCategory !== 'Popular Plans') {
                        totalPlanCount += plan.planCount;
                    }
                }
            }

            if(totalPlanCount === 0){
                $(".no-data-div").removeClass("d-none")
            }else {
                $(".no-data-div").addClass("d-none")
            }
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
                    singleRechargeCard.find("#recharge-btn-single-card").attr("onclick", "updatePlan(" + singlePlan.planId + ")")
                    multipleCardsContainer.append(singleRechargeCard)

                })
                count += 1
                mainContainer.append(categoryContainer)
            }
        }

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

function openNewPlanModal(){
    document.getElementById('newPlanForm').reset();
    $("#create-new-coupon").modal('show')
}

function updatePlan(planId){
    $.ajax({
        url: CONTEXT_PATH + '/admin/get-updating-plan-details',
        type: 'POST',
        data: {
            planId: planId
        },
        success: function (xhr, status, error) {
            console.log(xhr)

            var response = xhr["planData"]
            $("#updateRechargeAmount").val(response.planAmount)
            $("#updateSmsAllowance").val(response.smsAllowance)
            $("#updateVoiceAllowance").val(response.voiceAllowance)
            $("#updateDataAllowance").val(response.dataAllowance)
            $("#updateExtraData").val(response.extraDataAllowance)
            $("#updateValidity").val(response.validityPeriod)
            $("#updateIsAvailable").val(response.isAvailable)
            $("#updateExtraDataAvailable").val(response.extraDataAvailable)
            $("#updateRefreshesDaily").val(response.dailyRefresh)
            $("#hiddenCouponTypeValue").val(response.planCategoryId)
            $("#hiddenPlanIdValue").val(planId)
            $("#update-coupon-modal").modal('show')
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


function viewPlanDetails(planId) {
    // $(".cupon-code-error").removeClass("d-none")
    $.ajax({
        url: CONTEXT_PATH + '/admin/get-specific-plan-details',
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
            $(".show-update-modal").attr("onclick", "updatePlan(" + plan.planId + ")")

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


$(document).ready(function(){
    $(".close-confirm-postpaid-recharge-modal").click(function(){
        $('#confirm-postpaid-recharge').modal('hide');
    })
});

$(document).ready(function() {
    $.validator.addMethod("notZero", function(value, element) {
        return value !== "0";
    },"Please select a value.");

    $.validator.addMethod("voiceAllowance", function (value,element){
        return this.optional(element) || /^(Unlimited|\d+ minutes)$/.test(value);
    }, "Please provide proper value")

    $.validator.addMethod("validityField", function(value, element, params) {
        var hiddenFieldValue = $(params).val();
        if (hiddenFieldValue === "7") {
            return value === '0'; // Allow 0 only if hidden field is 1
        } else {
            return value !== '0'; // Disallow 0 if hidden field is not 1
        }
    }, "Invalid plan validity.");

    $("#newPlanForm").validate({
        rules: {
            planCategory: {
                required: true,
                notZero: true,
            },
            rechargeAmount: {
                required: true,
                number: true,
                min: 19,
                max: 9999
            },
            smsAllowance: {
                required: true,
                number: true,
                max: 1000
            },
            voiceAllowance: {
                required: true,
                voiceAllowance: true
            },
            dataAllowance: {
                required: true,
                number: true
            },
            extraData: {
                required: true,
                number: true
            },
            validity: {
                required: true,
                number: true,
                validityField: "#planCategory",
                max: 365
            },
            isAvailable: {
                required: true
            },
            extraDataAvailable:{
                required: true
            },
            refreshesDaily:{
                required: true
            },

        },
        messages: {
            planCategory: {
                required: "Plan Category Required",
                notZero: "Select a plan category",
            },
            rechargeAmount: {
                required: "Recharge amount required",
                number: "Recharge amount should only be integer",
                min: "Minimum recharge of Rs.19 required",
                max: "Maximum recharge amount is 9999"
            },
            smsAllowance: {
                required: "SMS allowance required",
                number: "SMS allowance should only be integer",
                max: "Maximum 1000 SMS can be provided"
            },
            voiceAllowance: {
                required: "Voice allowance required",
                voiceAllowance: "Provide valid details"
            },
            dataAllowance: {
                required: "Data allowance required",
                number: "Data amount should only be integer"
            },
            extraData: {
                required: "Extra data amount required",
                number: "Data amount should only be integer"
            },
            validity: {
                required: "Validity days required",
                number: "Validity days should only be integer",
                validityField: "Minimum 1 day validity required",
                max: "Maximum 365 days validity possible"
            },
            isAvailable: {
                required: "Select a value"
            },
            extraDataAvailable:{
                required: "Select a value"
            },
            refreshesDaily:{
                required: "Select a value"
            },
        },
        ignore: ":hidden",
        errorElement: "span",
        errorClass: "error-class",
        submitHandler: function (form) {
            addNewPlan()
        }
    });

    $("#updatePlanForm").validate({
        rules: {
            updateRechargeAmount: {
                required: true,
                number: true,
                min: 19,
                max: 9999
            },
            updateSmsAllowance: {
                required: true,
                number: true,
                max: 1000
            },
            updateVoiceAllowance: {
                required: true,
                voiceAllowance: true
            },
            updateDataAllowance: {
                required: true,
                number: true
            },
            updateExtraData: {
                required: true,
                number: true
            },
            updateValidity: {
                required: true,
                number: true,
                validityField: "#hiddenCouponTypeValue",
                max: 365
            },
            updateIsAvailable: {
                required: true
            },
            updateExtraDataAvailable:{
                required: true
            },
            updateRefreshesDaily:{
                required: true
            }
        },
        messages: {
            updateRechargeAmount: {
                required: "Recharge amount required",
                number: "Recharge amount should only be integer",
                min: "Minimum recharge of Rs.19 required",
                max: "Maximum recharge amount is 9999"
            },
            updateSmsAllowance: {
                required: "SMS allowance required",
                number: "SMS allowance should only be integer",
                max: "Maximum 1000 SMS can be provided"
            },
            updateVoiceAllowance: {
                required: "Voice allowance required",
                voiceAllowance: "Provide valid details"
            },
            updateDataAllowance: {
                required: "Data allowance required",
                number: "Data amount should only be integer"
            },
            updateExtraData: {
                required: "Extra data amount required",
                number: "Data amount should only be integer"
            },
            updateValidity: {
                required: "Validity days required",
                number: "Validity days should only be integer",
                validityField: "Minimum 1 day validity required",
                max: "Maximum 365 days validity possible"
            },
            updateIsAvailable: {
                required: "Select a value"
            },
            updateExtraDataAvailable:{
                required: "Select a value"
            },
            updateRefreshesDaily:{
                required: "Select a value"
            }
        },
        ignore: ":hidden",
        errorElement: "span",
        errorClass: "error-class",
        submitHandler: function (form) {
            updateCurrentPlan()
        }
    });

});

function updateCurrentPlan(){
    var planId = $("#hiddenPlanIdValue").val()
    var planCategory = $("#hiddenCouponTypeValue").val()
    var rechargeAmount = $("#updateRechargeAmount").val()
    var smsAllowance = $("#updateSmsAllowance").val()
    var voiceAllowance = $("#updateVoiceAllowance").val()
    var dataAllowance = $("#updateDataAllowance").val()
    var extraData = $("#updateExtraData").val()
    var validity = $("#updateValidity").val()
    var isAvailable = $("#updateIsAvailable").val()
    var extraDataAvailable = $("#updateExtraDataAvailable").val()
    var refreshesDaily = $("#updateRefreshesDaily").val()

    var payload = {}
    payload["planId"] = planId
    payload["planCategoryId"] = planCategory
    payload["planAmount"] = rechargeAmount
    payload["smsAllowance"] = smsAllowance
    payload["voiceAllowance"] = voiceAllowance
    payload["dataAllowance"] = dataAllowance
    payload["extraDataAllowance"] = extraData
    payload["validityPeriod"] = validity
    payload["isAvailable"] = isAvailable
    payload["extraDataAvailable"] = extraDataAvailable
    payload["dailyRefresh"] = refreshesDaily
    payload["isNewPlan"] = 0

    $.ajax({
        url: CONTEXT_PATH + '/admin/update-current-plan-details',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            console.log(xhr)
            $('#updatePlanForm')[0].reset()
            $(".new-plan-close-btn").click()
            applyFilter(false)
            // showAlert(true,xhr['message'],"success")
            $(".success-message-heading-modal").text("Plan Updated!")
            $(".success-message-modal").text(xhr['message'])
            $("#statusSuccessModal").modal('show')
        },
        error: function(xhr, status, error) {
            $(".new-plan-close-btn").click()
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
                    // showAlert(true,errorMessage,"faliure")
                    $(".failure-message-heading").text("Plan update failed")
                    $(".failure-message-modal").text(errorMessage)
                    $("#statusErrorsModal").modal('show')
                }
            } else {
                // showAlert(true,"Error at server side.","faliure")
                $(".failure-message-heading").text("Plan update failed")
                $(".failure-message-modal").text("Error at server side.")
                $("#statusErrorsModal").modal('show')
            }
        }
    })
}

function addNewPlan(){
    var planCategory = $("#planCategory").val()
    var rechargeAmount = $("#rechargeAmount").val()
    var smsAllowance = $("#smsAllowance").val()
    var voiceAllowance = $("#voiceAllowance").val()
    var dataAllowance = $("#dataAllowance").val()
    var extraData = $("#extraData").val()
    var validity = $("#validity").val()
    var isAvailable = $("#isAvailable").val()
    var extraDataAvailable = $("#extraDataAvailable").val()
    var refreshesDaily = $("#refreshesDaily").val()

    var payload = {}
    payload["planCategoryId"] = planCategory
    payload["planAmount"] = rechargeAmount
    payload["smsAllowance"] = smsAllowance
    payload["voiceAllowance"] = voiceAllowance
    payload["dataAllowance"] = dataAllowance
    payload["extraDataAllowance"] = extraData
    payload["validityPeriod"] = validity
    payload["isAvailable"] = isAvailable
    payload["extraDataAvailable"] = extraDataAvailable
    payload["dailyRefresh"] = refreshesDaily
    payload["isNewPlan"] = 1

    $.ajax({
        url: CONTEXT_PATH + '/admin/add-new-plan',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            console.log(xhr)
            $('#newPlanForm')[0].reset()
            $(".new-plan-close-btn").click()
            applyFilter(false)
            // showAlert(true,xhr['message'],"success")
            $(".success-message-heading-modal").text("Plan Created!")
            $(".success-message-modal").text(xhr['message'])
            $("#statusSuccessModal").modal('show')
        },
        error: function(xhr, status, error) {
            $(".new-plan-close-btn").click()
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
                    // showAlert(true,errorMessage,"faliure")
                    $(".failure-message-heading").text("Failed to create plan")
                    $(".failure-message-modal").text(errorMessage)
                    $("#statusErrorsModal").modal('show')
                }
            } else {
                // showAlert(true,"Error at server side.","faliure")
                $(".failure-message-heading").text("Failed to create plan")
                $(".failure-message-modal").text("Error at server side.")
                $("#statusErrorsModal").modal('show')
            }
        }
    })
}






