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

function changeActiveRequestType(element, type) {
    $(".common-btn-type").removeClass("active-btn-type")
    element.classList.add("active-btn-type");
    getFilteredUsersData(true)

    if (type === 1) {
        $(".status-type-search").removeClass("d-none")
    } else {
        $(".status-type-search").addClass("d-none")
    }
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
        currentPage = 1
    } else {
        payload["currentPage"] = currentPage
    }
    payload["requestType"] = requestType
    payload["email"] = email
    payload["mobile"] = mobile
    payload["statusType"] = statusType
    payload["pageSize"] = parseInt(pageSize)

    $.ajax({
        url: CONTEXT_PATH + '/admin/get-filtered-users-details',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(payload),
        success: function (xhr, status, error) {
            console.log(xhr)
            response = xhr['clientDataDto']
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
        trClone.find(".tr-email").text(item.clientEmail)

        id += 1
        accordionClone.find(".change-accordion-target").text(item.clientName).attr("data-bs-target", "#accordionId" + id)
        accordionClone.find(".change-accordion-id").attr("id", "accordionId" + id)
        accordionClone.find(".accordion-email").text(item.clientEmail)


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

            trClone.find(".contact-user-btn").addClass("d-none")
            trClone.find(".dropdown-common-action").removeClass("d-none")
            trClone.find(".accept-btn-tb").attr("onclick","viewDocument(" + item.clientId + ")")
            trClone.find(".reject-btn-tb").attr("onclick","contactUser(" + item.clientId +")")


            accordionClone.find(".contact-user-btn-accordion").addClass("d-none")
            accordionClone.find(".accept-btn-accordion").removeClass("d-none").attr("onclick","viewDocument(" + item.clientId + ")")
            accordionClone.find(".reject-btn-accordion").removeClass("d-none").attr("onclick","contactUser(" + item.clientId +")")


        } else {


            trClone.find(".contact-user-btn").removeClass("d-none").text("Notify Client").attr("onclick","notifyClient(" + item.clientId +")")
            trClone.find(".dropdown-common-action").addClass("d-none")

            accordionClone.find(".contact-user-btn-accordion").removeClass("d-none")
            accordionClone.find(".accept-btn-accordion").addClass("d-none")
            accordionClone.find(".reject-btn-accordion").addClass("d-none")

            trClone.find(".new-btn-green").addClass("d-none")
            accordionClone.find(".new-btn-green").addClass("d-none")
            trClone.find(".reupload-btn-yellow").removeClass("d-none")
            accordionClone.find(".reupload-btn-yellow").removeClass("d-none")
            trClone.find(".reupload-btn-yellow").text("SIGNED UP")
            accordionClone.find(".reupload-btn-yellow").text("SIGNED UP")


        }
        tbody.append(trClone)
        accordionBody.append(accordionClone)
    })
}

function contactUser(clientId){
    $("#hidden-client-id-contact-form").val(clientId)
    $(".contact-description-class").val("")
    $("#contactModal").modal("show")
}

function viewUploadedFile(path) {
    console.log(path)
    window.open(path, '_blank');
}

function sendEmailToClient(){
    var clientId = $("#hidden-client-id-contact-form").val()
    var description = $("#contact-description-textarea").val()

    if(description == null || clientId == null){
        showAlert("true","Description or client id is empty", "failure")
    } else{
        $.ajax({
            url: CONTEXT_PATH + '/admin/send-contact-email',
            type: 'POST',
            data: {
                clientId: clientId,
                description: description
            },
            success: function (xhr, status, error) {
                $("#contactModal").modal("hide")
                // showAlert("true",xhr['message'],"success")
                $("#statusSuccessModal").modal('show')
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


}

function notifyClient(clientId){
    if(clientId == null){
        showAlert("true","Description or client id is empty", "failure")
    } else{
        $.ajax({
            url: CONTEXT_PATH + '/admin/send-signup-email',
            type: 'POST',
            data: {
                clientId: clientId
            },
            success: function (xhr, status, error) {
                $("#contactModal").modal("hide")
                // showAlert("true",xhr['message'],"success")
                $("#statusSuccessModal").modal('show')
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
}

function viewDocument(clientId) {
    if (clientId == null) {
        showAlert(true, "Client Id not found!", "faliure")
    } else {
        $.ajax({
            url: CONTEXT_PATH + '/admin/get-client-other-details',
            type: 'POST',
            data: {
                clientId: clientId
            },
            success: function (xhr, status, error) {
                console.log(xhr)
                var response = xhr['clientDetails']
                $(".view-doc-name-div").text(response.clientName)
                $(".view-doc-bday-div").text(response.clientBDate)
                $(".view-doc-aadhar-numb-div").text(response.clientAadharNumber)
                $(".view-doc-pan-numb-div").text(response.clientPanNumber)
                var aadharCardFilePath = response.aadharFilePath.replace(/\\/g, '/')
                var panCardFilePath = response.panFilePath.replace(/\\/g, '/')
                $(".view-aadhar-path-class").attr("onclick", "viewUploadedFile('" + aadharCardFilePath + "')")
                $(".view-pan-path-class").attr("onclick", "viewUploadedFile('" + panCardFilePath + "')")
                $("#verify-documents-modal").modal('show')
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
}