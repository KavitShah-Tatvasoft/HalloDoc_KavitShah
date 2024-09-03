let checkBoxStatus = {
    coupon: false
};



const paginationContainer = document.querySelector('.pagination');
const pageList = document.getElementById('pageList');
const previousBtn = document.getElementById('previous');
const nextBtn = document.getElementById('next');

let currentPage = 1;
let totalPages = 10;

renderPagination();

function renderPagination() {
    pageList.innerHTML = '';

    // Calculate the range of visible pages
    let visiblePages = Math.min(totalPages, 5);

    let startPage = Math.max(1, currentPage - Math.floor(visiblePages / 2));
    let endPage = Math.min(totalPages, startPage + visiblePages - 1);

    if (endPage - startPage + 1 < visiblePages) {
        startPage = Math.max(1, endPage - visiblePages + 1);
    }

    // Previous button
    previousBtn.disabled = currentPage === 1;

    // Next button
    nextBtn.disabled = currentPage === totalPages;

    // Page buttons
    for (let i = startPage; i <= endPage; i++) {
        const li = document.createElement('li');
        li.textContent = i;
        li.className = (i === currentPage) ? 'active' : '';
        li.addEventListener('click', () => {
            currentButton(i);
        });
        pageList.appendChild(li);
    }
}

function nextButton() {
    nextBtn.disabled = currentPage === totalPages;
    if (currentPage < totalPages) {
        currentPage++
        renderPagination()
        getAllCouponData(false)
    }
}

function currentButton(current) {
    currentPage = current
    renderPagination()
    getAllCouponData(false)
}

function previousButton() {
    previousBtn.disabled = currentPage === 1;

    if (currentPage > 1) {
        currentPage--;
        renderPagination()
        getAllCouponData(false)
    }
}

// Pagination functions completed


function getAllCouponData(booleanValue){

    var pageSize = $("#page-size-id").val()
    var couponType = $("#coupon-type").val()
    var pageNumber = currentPage

    var payload = {}
    if (booleanValue === true) {
        payload["currentPage"] = 1
    } else {
        payload["currentPage"] = pageNumber
    }
    payload["couponType"] = couponType
    payload["pageSize"] = parseInt(pageSize)
    var couponBody = $(".main-coupon-row")

    $.ajax({
        url: CONTEXT_PATH + '/admin/get-all-coupon-data',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            console.log(xhr)
            totalPages = xhr.paginatedCoupnData.pages
            let paginationWidth = totalPages * 40 + 160; // Calculate width based on number of pages
            paginationContainer.style.width = `${paginationWidth}px`;
            renderPagination()
            couponBody.empty()

            $.each(xhr.paginatedCoupnData.allCouponsDetailsDtos, function (index, item){
                var cuoponClone =$(".clone-col").clone().removeClass("clone-col").removeClass("d-none")
                if(item.couponType === 1){
                    cuoponClone.find(".coupon-type-text").text("Cashback Coupon")
                }

                if(item.couponType === 2){
                    cuoponClone.find(".coupon-type-text").text("Discount Coupon")
                }

                if(item.couponType === 3){
                    cuoponClone.find(".coupon-type-text").text("Internet Packs")
                }

                if(item.maxRewards === "0 GB"){
                    cuoponClone.find(".max-amt-flex").addClass("d-none")
                }else {
                    cuoponClone.find(".max-amount-span").text(item.maxRewards)
                }

                cuoponClone.find(".reward-amount-text").text(item.couponHeading)
                cuoponClone.find(".update-coupon").attr("onclick", "updateCoupon(" + item.couponId + ")")

                if(item.couponAvailable === true){
                   cuoponClone.find(".coupon-availability").text("Available").removeClass("unavailable-text")

                }else {
                    cuoponClone.find(".coupon-availability").text("Unavailable").addClass("unavailable-text")
                }
                couponBody.append(cuoponClone)
            })
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

function createNewCoupon(){
    $(".coupon-header-title").text("Create New Coupon")
    checkBoxStatus['cashback'] = false
    checkBoxStatus['discount'] = false
    checkBoxStatus['coupon'] = true
    $(".common-btn-coupon-modal").addClass("red-btn-coupon-modal")
    $(".coupon-class").addClass("green-btn-coupon-modal").removeClass("red-btn-coupon-modal")
    $(".update-create-btn").text("Create")
    $(".coupon-available-btn").addClass("d-none")
    $("#couponModal").modal("show")
}

function updateCoupon(couponId){
    $.ajax({
        url: CONTEXT_PATH + '/admin/get-coupon-details',
        type: 'POST',
        dataType: 'json',
        data: {
            couponId : parseInt(couponId,10)
        },
        success: function (xhr, status, error) {
            console.log(xhr)
            var response = xhr["couponDetials"]
            $("#couponNameUpdate").val(response.couponName)
            if(parseInt(response.couponCategory,10) !== 3){
                $("#rewardAmountPercentageUpdate").val(response.couponReward)
                $(".mb-col").addClass("d-none")
                $(".percentage-col").removeClass("d-none")
                $("#maximumRewardUpdate").val(response.couponMaxReward)
                $(".max-reward-col").removeClass("d-none")
            }else{
                $("#rewardAmountMBUpdate").val(response.couponReward)
                $(".percentage-col").addClass("d-none")
                $(".mb-col").removeClass("d-none")
                $(".max-reward-col").addClass("d-none")
            }

            $("#validityPeriodUpdate").val(response.couponValidity)
            $("#couponDescriptionUpdate").val(response.couponDescription)

            alert(response.couponAvailable)
            if(response.couponAvailable === true){

                $("#couponAvailability").val(1)
                // checkBoxStatus['coupon'] = true
                // $(".coupon-available-btn").addClass("green-btn-coupon-modal").removeClass("red-btn-coupon-modal").removeClass("d-none")
            }else {
                $("#couponAvailability").val(2)
                // checkBoxStatus['coupon'] = false
                // $(".coupon-available-btn").addClass("red-btn-coupon-modal").removeClass("green-btn-coupon-modal").removeClass("d-none")
            }
            $("#hidden-update-coupon-id").val(response.couponId)
            $("#hidden-coupon-type-id").val(parseInt(response.couponCategory,10))
            $(".update-create-btn").text("Update")
            $(".reset-button-update").attr("onclick","updateCoupon(" + parseInt(couponId,10) + ")")
            $("#updateCouponModal").modal('show')
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

function showCorrespondingRewardInput(){
    var couponType = $("#couponType").val()
    if(couponType === "3"){
        $(".percentage-col").addClass("d-none")
        $(".mb-col").removeClass("d-none")
        $(".max-reward-col").addClass("d-none")
    }else {
        $(".percentage-col").removeClass("d-none")
        $(".mb-col").addClass("d-none")
        $(".max-reward-col").removeClass("d-none")
    }
}

function toggleStatus(key,element) {
    if (checkBoxStatus.hasOwnProperty(key)) {
        checkBoxStatus[key] = !checkBoxStatus[key];

        if (checkBoxStatus[key]) {
            element.classList.add('green-btn-coupon-modal');
            element.classList.remove('red-btn-coupon-modal');
        } else {
            element.classList.add('red-btn-coupon-modal');
            element.classList.remove('green-btn-coupon-modal');
        }
    } else {
       showAlert(true,"Error Occured", "failure")
    }
}

$.validator.addMethod("onlyNumbers", function(value, element) {
    return this.optional(element) || /^[0-9]+$/.test(value);
}, "Please enter only numbers.");

$.validator.addMethod("notZero", function(value, element) {
        return value !== "0";
    },"Please select a value.");



$("#update-coupon-form").validate({
    rules: {
        couponNameUpdate: {
            required: true,
            minlength: 5,
            maxlength: 50,
        },
        rewardAmountPercentageUpdate: {
            required: true,
            onlyNumbers: true,
            min: 5,
            max: 100
        },
        rewardAmountMBUpdate: {
            required: true,
            onlyNumbers: true,
            min: 250
        },
        validityPeriodUpdate: {
            required: true,
            onlyNumbers: true,
            min: 1,
            max: 365
        },
        maximumRewardUpdate: {
            required: true,
            onlyNumbers: true,
            min: 5
        },
        couponDescriptionUpdate: {
            required: true,
            minlength: 10,
            maxlength: 150
        }
    },
    messages: {
        couponNameUpdate: {
            required: "Coupon Name is required",
            maxlength: "Coupon Name should not be grater than 50 chars",
            minLength: "Coupon Name should be at least 5 chars"
        },
        rewardAmountPercentageUpdate: {
            required: "Please provide amount percentage",
            onlyNumbers: "Reward should only contain whole numbers",
            min: "Provide minimum 5% of discount",
            max: "Maximum 100% discount possible"
        },
        rewardAmountMBUpdate: {
            required: "Please provide amount percentage",
            onlyNumbers: "Reward should only contain whole numbers",
            min: "Minimum 250 MB reward required"
        },
        validityPeriodUpdate: {
            required: "Please provide validity days",
            onlyNumbers: "Validity should only be in number of days",
            min: "Coupon should be valid for at least 1 day",
            max: "Coupon validity can be maximum of 365 days"
        },
        maximumRewardUpdate: {
            required: "Please provide max amount reward",
            onlyNumbers: "Reward should only contain numbers",
            min: "5 Rs minimum required"
        },
        couponDescriptionUpdate: {
            required: "Please provide coupon description",
            minlength: "Description should be at least 10 chars",
            maxlength: "Description should be at max of 150 chars"
        },

    },
    ignore: ":hidden",
    errorElement: "span",
    errorClass: "error-class",
    submitHandler: function (form) {
        alert("Form submitted")
        updateCouponForm()
    }
});

$("#create-update-form").validate({
    rules: {
        couponType: {
            required: true,
            notZero: true
        },
        couponName: {
            required: true,
            minlength: 5,
            maxlength: 50,
        },
        rewardAmountPercentage: {
            required: true,
            onlyNumbers: true,
            min: 5,
            max: 100
        },
        rewardAmountMB: {
            required: true,
            onlyNumbers: true,
            min: 250
        },
        validityPeriod: {
            required: true,
            onlyNumbers: true,
            min: 1,
            max: 365
        },
        maximumReward: {
            required: true,
            onlyNumbers: true,
            min: 5
        },
        couponDescription: {
            required: true,
            minlength: 10,
            maxlength: 150
        }
    },
    messages: {
        couponType: {
            required: "Coupon Type is required",
            notZero: "Please select a coupon type"
        },
        couponName: {
            required: "Coupon Name is required",
            maxlength: "Coupon Name should not be grater than 50 chars",
            minLength: "Coupon Name should be at least 5 chars"
        },
        rewardAmountPercentage: {
            required: "Please provide amount percentage",
            onlyNumbers: "Reward should only contain whole numbers",
            min: "Provide minimum 5% of discount",
            max: "Maximum 100% discount possible"
        },
        rewardAmountMB: {
            required: "Please provide amount percentage",
            onlyNumbers: "Reward should only contain whole numbers",
            min: "Minimum 250 MB reward required"
        },
        validityPeriod: {
            required: "Please provide validity days",
            onlyNumbers: "Validity should only be in number of days",
            min: "Coupon should be valid for at least 1 day",
            max: "Coupon validity can be maximum of 365 days"
        },
        maximumReward: {
            required: "Please provide max amount reward",
            onlyNumbers: "Reward should only contain numbers",
            min: "5 Rs minimum required"
        },
        couponDescription: {
            required: "Please provide coupon description",
            minlength: "Description should be at least 10 chars",
            maxlength: "Description should be at max of 150 chars"
        },

    },
    ignore: ":hidden",
    errorElement: "span",
    errorClass: "error-class",
    submitHandler: function (form) {
        alert("Form submitted")
        submitCreateUpdateForm()
    }
});

function updateCouponForm(){
    debugger
    var couponId = $("#hidden-update-coupon-id").val()
    var couponType = $("#hidden-coupon-type-id").val()
    var couponName = $("#couponNameUpdate").val()
    var rewardPercentage = $("#rewardAmountPercentageUpdate").val()
    var rewardMB = $("#rewardAmountMBUpdate").val()
    var validity = $("#validityPeriodUpdate").val()
    var maxReward = $("#maximumRewardUpdate").val()
    var couponDescription = $("#couponDescriptionUpdate").val()
    var isAvailable = $("#couponAvailability").val()

    var payload = {}
    payload['couponCategory'] = couponType
   if(isAvailable === "1"){
       payload['couponAvailability'] = true
   }else {
       payload['couponAvailability'] = false
   }
    payload['couponName'] = couponName
    payload['couponId'] = parseInt(couponId,10)
    if(parseInt(couponType,10) === 3){
        payload['maxReward'] = 10
        payload['rewardPercentage'] = 5
        payload['rewardMB'] = parseInt(rewardMB,10)
    }else {
        payload['maxReward'] = parseInt(maxReward,10)
        payload['rewardPercentage'] = parseInt(rewardPercentage,10)
        payload['rewardMB'] = 250
    }
    payload['validity'] = parseInt(validity,10)
    payload['couponDescription'] = couponDescription

    $.ajax({
        url: CONTEXT_PATH + '/admin/update-coupon-details',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            console.log(xhr)
            $('#update-coupon-form')[0].reset()
            $(".update-coupon-close-button").click()
            getAllCouponData(true)
            showAlert(true,xhr['message'],"success")
        },
        error: function(xhr, status, error) {
            $(".coupon-available-btn").click()
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

function submitCreateUpdateForm(){
    var couponType = $("#couponType").val()
    var couponName = $("#couponName").val()
    var rewardPercentage = $("#rewardAmountPercentage").val()
    var rewardMB = $("#rewardAmountMB").val()
    var validity = $("#validityPeriod").val()
    var maxReward = $("#maximumReward").val()
    var couponDescription = $("#couponDescription").val()

    var payload = {}
    payload['couponType'] = parseInt(couponType,10)
    payload['couponName'] = couponName
    if(parseInt(couponType,10) === 3){
        payload['maxReward'] = 10
        payload['rewardPercentage'] = 5
        payload['rewardMB'] = rewardMB
    }else {
        payload['maxReward'] = maxReward
        payload['rewardPercentage'] = rewardPercentage
        payload['rewardMB'] = 250
    }
    payload['validity'] = validity
    payload['couponDescription'] = couponDescription

    $.ajax({
        url: CONTEXT_PATH + '/admin/create-new-coupon',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            console.log(xhr)
            $('#create-update-form')[0].reset()
            $(".coupon-close-button").click()
            getAllCouponData(true)
            showAlert(true,xhr['message'],"success")
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

