const paginationContainer = document.querySelector('.pagination');
const pageList = document.getElementById('pageList');
const previousBtn = document.getElementById('previous');
const nextBtn = document.getElementById('next');

let currentPage = 1;
let totalPages = 10; // Example: Replace with your actual total number of pages

function getRechargeData(){

    var pageSize = $("#page-size-id").val()

    var payload = {}
    payload["currentPage"] = currentPage
    payload["pageSize"] = parseInt(pageSize)

    $.ajax({
        url: CONTEXT_PATH + '/client/get-recharge-history-details',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            console.log(xhr)
            response = xhr['message']
            totalPages = response.totalPages
            let paginationWidth = totalPages * 40 + 160; // Calculate width based on number of pages
            paginationContainer.style.width = `${paginationWidth}px`;
            renderPagination()
            var paidPlanBody = $(".paid-plan-history-div")
            paidPlanBody.empty()
            if(!response.paidDataAvailable && !response.unpaidDataAvailable){
                $(".available-invoice").addClass("d-none")
                $(".no-invoice-flex").removeClass("d-none")
            } else{
                $(".available-invoice").removeClass("d-none")
                $(".no-invoice-flex").addClass("d-none")
            }

            if(response.paidDataAvailable){
                $(".paid-plan-class").removeClass("d-none")
                $.each(response.paidList, function (index, invoice){
                    var invoiceCard = $(".clone-plan-history").clone().removeClass("d-none").removeClass("clone-plan-history")
                    invoiceCard.find(".invoice-amount-span").text(invoice.planAmount)
                    invoiceCard.find(".bought-date-span").text(invoice.planBoughtDate)
                    invoiceCard.find(".payment-mode-span").text(invoice.paymentMethod)
                    invoiceCard.find(".reference-number-span").text(invoice.paymentReference)
                    invoiceCard.find(".invoice-number-span").text(invoice.invoiceNumber)
                    invoiceCard.find(".download-invoice-btn").attr("onclick","downloadInvoice(" + invoice.invoiceId + ")")
                    paidPlanBody.append(invoiceCard)
                })
            }else {
                $(".paid-plan-class").addClass("d-none")
            }

            if(response.unpaidDataAvailable){
                var unpaidPlan = response.unpaidPlan
                $(".unpaid-plan-class").removeClass("d-none")
                $(".unpaid-invoice-amount-span").text(unpaidPlan.planAmount)
                $(".unpaid-activation-date-span").text(unpaidPlan.planBoughtDate)
            }else {
                $(".unpaid-plan-class").addClass("d-none")
            }


        },
        error: function(xhr, status, error) {
            debugger
            if (xhr.status === 400 || xhr.status === 401 || xhr.status === 404 || xhr.status === 405 || xhr.status === 415) {
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
                showAlert(true,"Server side error","faliure")
            }
        }
    })
}

function renderPagination() {
    pageList.innerHTML = '';

    // Calculate the range of visible pages
    let visiblePages = Math.min(totalPages, 5); // Show up to 5 pages or total pages available

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
        currentPage++;
        getRechargeData()
    }
}

function currentButton(current) {
    currentPage = current;
    getRechargeData()
}

function previousButton() {
    previousBtn.disabled = currentPage === 1;

    if (currentPage > 1) {
        currentPage--;
        getRechargeData();
    }
}

function downloadInvoice(invoiceNumber){
    var invoiceId = invoiceNumber

    $.ajax({
        url: CONTEXT_PATH + '/client/download-invoice',
        type: 'POST',
        data: {
            invoiceId : invoiceId
        },
        success: function (xhr, status, error) {
            console.log(xhr)
            window.open(xhr['invoiceLink'], '_blank');
            },
        error: function(xhr, status, error) {
            debugger
            if (xhr.status === 400 || xhr.status === 401 || xhr.status === 404 || xhr.status === 405 || xhr.status === 415) {
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
                showAlert(true,"Server side error","faliure")
            }
        }
    })

}
