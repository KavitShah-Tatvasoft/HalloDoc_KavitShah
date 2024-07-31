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
        getFilteredUsersData(false)
    }
}

function currentButton(current) {
    currentPage = current
    renderPagination()
    getFilteredUsersData(false)
}

function previousButton() {
    previousBtn.disabled = currentPage === 1;

    if (currentPage > 1) {
        currentPage--;
        renderPagination()
        getFilteredUsersData(false)
    }
}

// Pagination functions completed

function changeActiveRequestType(element) {
    $(".common-btn-type").removeClass("active-btn-type")
    element.classList.add("active-btn-type");
    getFilteredUsersData(true)
}


function getFilteredUsersData(booleanValue) {
    var pageSize = $("#page-size-id").val()
    var requestType = $(".active-btn-type").attr("data-value")
    var email = $("#emailId").val()
    var mobile = $("#mobileNumber").val()
    var statusType = $("#statusType").val()
    var pageNumber = currentPage

    var payload = {}
    if (booleanValue === true) {
        payload["currentPage"] = 1
    } else {
        payload["currentPage"] = currentPage
    }
    payload["requestType"] = requestType
    payload["email"] = email
    payload["mobile"] = mobile
    payload["statusType"] = statusType
    payload["pageSize"] = parseInt(pageSize)

    $.ajax({
        url: CONTEXT_PATH + '/admin/get-filtered-user-request',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            console.log(xhr)
            response = xhr['clientRequestPaginatedDto']
            totalPages = response.totalPages
            let paginationWidth = totalPages * 40 + 160; // Calculate width based on number of pages
            paginationContainer.style.width = `${paginationWidth}px`;
            renderPagination()

            if (response.dataAvailable === false) {
                $(".no-data-div").removeClass("d-none")
                $(".pagination-page-size-flex").addClass("d-none")
                $(".table-outer-class").addClass("d-none")
                $(".accordion-common-body").addClass("d-none")
            } else {
                $(".no-data-div").addClass("d-none")
                $(".pagination-page-size-flex").removeClass("d-none")
                $(".table-outer-class").removeClass("d-none")
                $(".accordion-common-body").removeClass("d-none")
            }

            if (response.requestType == 1) {
                $(".status-column-common").removeClass("d-none")
                $(".status-type-search").removeClass("d-none")
            } else {
                $(".status-column-common").addClass("d-none")
                $(".status-type-search").addClass("d-none")
            }

            populateTableAccordionData(response)


        },
        error: function (xhr, status, error) {
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
                    showAlert(true, errorMessage, "faliure")
                }
            } else {
                showAlert(true, "Server side error", "faliure")
            }
        }
    })
}

function populateTableAccordionData(response) {

    var tbody = $(".request-table-body")
    var accordionBody = $(".accordion-common-body")
    tbody.empty()
    accordionBody.empty()
    var id = 0
    $.each(response.clientFilteredRequests, function (index, item) {
        var trClone = $(".clone-tr").clone().removeClass("clone-tr").removeClass("d-none")
        var accordionClone = $(".accordion-clone-item").clone(true).removeClass("accordion-clone-item").removeClass("d-none")
        trClone.find(".tr-name").text(item.clientName)
        trClone.find(".tr-number").text(item.clientPhone)
        trClone.find(".tr-email").text(item.clientEmail)

        id += 1
        accordionClone.find(".change-accordion-target").text(item.clientName).attr("data-bs-target", "#accordionId" + id)
        accordionClone.find(".change-accordion-id").attr("id", "accordionId" + id)
        accordionClone.find(".accordion-email").text(item.clientEmail)
        accordionClone.find(".accordion-number").text(item.clientPhone)


        if (response.requestType === 1) {

            if (item.currentRequestStatus === 1) {
                trClone.find(".new-btn-green").removeClass("d-none")
                accordionClone.find(".new-btn-green").removeClass("d-none")
                trClone.find(".reupload-btn-yellow").addClass("d-none")
                accordionClone.find(".reupload-btn-yellow").addClass("d-none")
            }

            if (item.currentRequestStatus === 2) {
                trClone.find(".new-btn-green").addClass("d-none")
                accordionClone.find(".new-btn-green").addClass("d-none")
                trClone.find(".reupload-btn-yellow").removeClass("d-none")
                accordionClone.find(".reupload-btn-yellow").removeClass("d-none")
            }

            trClone.find(".view-document-btn").removeClass("d-none").attr("onclick", "viewRequestData(" + item.requestId + ")")
            trClone.find(".dropdown-common-action").addClass("d-none")

            accordionClone.find(".view-doc-btn-accordion").removeClass("d-none")
            accordionClone.find(".accept-btn-accordion").addClass("d-none")
            accordionClone.find(".reject-btn-accordion").addClass("d-none")


        } else {


            trClone.find(".view-document-btn").addClass("d-none")
            trClone.find(".dropdown-common-action").removeClass("d-none")

            accordionClone.find(".view-doc-btn-accordion").addClass("d-none")
            accordionClone.find(".accept-btn-accordion").removeClass("d-none")
            accordionClone.find(".reject-btn-accordion").removeClass("d-none")

            if (response.requestType === 2) {
                trClone.find(".accept-btn-tb").attr("onclick", "acceptDeactivationRequest(" + item.requestId + ")")
                trClone.find(".reject-btn-tb").attr("onclick", "rejectDeactivationRequest(" + item.requestId + ")")

                accordionClone.find(".accept-btn-accordion").attr("onclick", "acceptDeactivationRequest(" + item.requestId + ")")
                accordionClone.find(".reject-btn-accordion").attr("onclick", "rejectDeactivationRequest(" + item.requestId + ")")
            }

            if (response.requestType === 3) {
                trClone.find(".accept-btn-tb").attr("onclick", "acceptBlockRequest(" + item.requestId + ")")
                trClone.find(".reject-btn-tb").attr("onclick", "rejectBlockRequest(" + item.requestId + ")")

                accordionClone.find(".accept-btn-accordion").attr("onclick", "acceptBlockRequest(" + item.requestId + ")")
                accordionClone.find(".reject-btn-accordion").attr("onclick", "rejectBlockRequest(" + item.requestId + ")")
            }
            if (response.requestType === 4) {
                trClone.find(".accept-btn-tb").attr("onclick", "acceptUnblockRequest(" + item.requestId + ")")
                trClone.find(".reject-btn-tb").attr("onclick", "rejectUnblockRequest(" + item.requestId + ")")

                accordionClone.find(".accept-btn-accordion").attr("onclick", "acceptUnblockRequest(" + item.requestId + ")")
                accordionClone.find(".reject-btn-accordion").attr("onclick", "rejectUnblockRequest(" + item.requestId + ")")
            }
        }
        tbody.append(trClone)
        accordionBody.append(accordionClone)
    })
}