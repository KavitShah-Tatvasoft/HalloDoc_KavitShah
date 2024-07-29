

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
            console.log("Helloooo")
            console.log(data)
            $(".popular-plan-count").html("(" + data["Popular Plans"].planCount + ")")
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
    alert(planId)
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




