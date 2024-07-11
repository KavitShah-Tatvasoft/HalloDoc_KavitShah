function getClientCupons(){

    $.ajax({
        url: CONTEXT_PATH + '/client/get-user-coupons',
        type: 'GET',
        success: function(xhr, status, success) {

            var mainRow = $(".main-coupon-row")
            mainRow.empty()
            var couponList = xhr.cuponList
            console.log(couponList)
            if(couponList.length === 0){
                $(".no-coupons-text-flex").removeClass("d-none")
                $(".coupon-container").removeClass("coupon-container")
            }else{
                $.each(xhr.cuponList, function (index, coupon){
                    // couponList.forEach(coupon){
                    var cuponBody = $(".clone-col").clone().removeClass("d-none").removeClass("clone-col")
                    if(coupon.discountAvailable){
                        cuponBody.find(".coupon-type-text").text("Discount Coupon")
                        cuponBody.find(".reward-amount-text").text(coupon.couponHeading + " OFF")
                        cuponBody.find(".max-amount-span").text("Rs. " + coupon.maxRewards)
                        cuponBody.find(".max-amt-flex").removeClass("d-none")
                        cuponBody.find(".redeem-btn-flex").addClass("d-none")
                    }

                    if(coupon.cashbackAvailable){
                        cuponBody.find(".coupon-type-text").text("Cashback Coupon")
                        cuponBody.find(".reward-amount-text").text(coupon.couponHeading + " OFF")
                        cuponBody.find(".max-amount-span").text("Rs. " + coupon.maxRewards)
                        cuponBody.find(".max-amt-flex").removeClass("d-none")
                        cuponBody.find(".redeem-btn-flex").addClass("d-none")
                    }

                    if(!coupon.discountAvailable && !coupon.cashbackAvailable){
                        cuponBody.find(".coupon-type-text").text("Internet Coupon")
                        var rewardAmt = parseFloat(coupon.couponHeading)
                        var reward = (rewardAmt/1000).toFixed(2)
                        cuponBody.find(".reward-amount-text").text(reward + " GB")
                    }

                    cuponBody.find(".validity-text").text(coupon.expirationDate)
                    cuponBody.find(".coupon-code-place").text(coupon.couponCode)
                    mainRow.append(cuponBody)
                })
            }
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
                    showAlert(true,errorMessage,"faliure")
                }
            } else {
                showAlert(true,"Server Side Error","faliure")
            }
            console.log(xhr)
        }
    })
}

function redeemReward(element){

    const couponElement = element.closest('.coupon');
    const couponCodeElement = couponElement.querySelector('.coupon-code-place');
    var couponCode = couponCodeElement.textContent;

    let regex = new RegExp('^[A-Z0-9]{6}$');
    couponCode = couponCode.toUpperCase()

    if(regex.test(couponCode)){
        $.ajax({
            url: CONTEXT_PATH + '/client/redeem-coupon',
            type: 'POST',
            data: {
                couponCode : couponCode
            },

            success: function(xhr, status, success) {
                $("#statusSuccessModal").modal('show')
                getClientCupons()
                console.log(xhr)
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
                        showAlert(true,errorMessage,"faliure")
                    }
                } else {
                    showAlert(true,"Server Side Error","faliure")
                }
                console.log(xhr)
            }
        })
    }
    else{
        showAlert(true,"Please redeem a valid coupon!","faliure")
    }

}