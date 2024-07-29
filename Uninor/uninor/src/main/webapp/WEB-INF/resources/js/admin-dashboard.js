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
        getFilteredRequestData(false)
    }
}

function currentButton(current) {
    currentPage = current
    renderPagination()
    getFilteredRequestData(false)
}

function previousButton() {
    previousBtn.disabled = currentPage === 1;

    if (currentPage > 1) {
        currentPage--;
        renderPagination()
        getFilteredRequestData(false)
    }
}

// Pagination functions completed

function showLoginLoader() {
    var showToaster = localStorage.getItem("showLoginToaster")
    if (showToaster === "true") {
        showAlert(true, "Logged in successfully!", "success")
        localStorage.setItem("showLoginToaster", "false")
    }
}

let barChartOb
let polarChartOb

function getAdminDashboardData() {
    $.ajax({
        url: CONTEXT_PATH + '/admin/get-dashboard-data',
        type: 'GET',
        dataType: 'json',
        success: function (xhr, status, error) {
            var responseData = xhr.adminDashboardData
            console.log(responseData)

            $(".registered-user-count").text(responseData.totalRegisteredUsers)
            $(".signed-up-user-count").text(responseData.totalSignedUpUsers)
            $(".total-revenue-generated").text(responseData.totalGeneratedRevenue)

            if (barChartOb) {
                barChartOb.destroy();
            }

            if (polarChartOb) {
                polarChartOb.destroy();
            }
            const ctx1 = document.getElementById('myChart1');
            barChartOb = new Chart(ctx1, {
                type: 'bar',
                data: {
                    labels: responseData.currentMonthDateList,
                    datasets: [{
                        label: 'Income per day',
                        data: responseData.currentMonthExpenditureList,
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
                                text: 'Date',
                                color: 'black', // Color of the title text
                                font: {
                                    size: 14, // Font size
                                }
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: 'Income Generated',
                                color: 'black', // Color of the title text
                                font: {
                                    size: 14, // Font size
                                }
                            }
                        }
                    }
                }
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
        error: function (xhr, status, error) {
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
                    showAlert(true, errorMessage, "faliure")
                }
            } else {
                showAlert(true, "Error at server side!", "faliure")
            }
        }
    })
}

function getFilteredRequestData(booleanValue) {
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

function changeActiveRequestType(element) {
    $(".common-btn-type").removeClass("active-btn-type")
    element.classList.add("active-btn-type");
    getFilteredRequestData()
}

function viewUploadedFile(path) {
    console.log(path)
    window.open(path, '_blank');
}

let documentStatuses = {
    aadhar: null,
    pan: null
};

function setDocumentStatus(documentId, status) {
    documentStatuses[documentId] = status;
    if (documentId === "aadhar") {
        if (status === "accepted") {
            $(".accept-aadhar-btn-doc-validated").addClass("doc-verified-green-class")
            $(".reject-aadhar-btn-doc-validated").removeClass("doc-reject-red-class")
        } else {
            $(".reject-aadhar-btn-doc-validated").addClass("doc-reject-red-class")
            $(".accept-aadhar-btn-doc-validated").removeClass("doc-verified-green-class")
        }
    }

    if (documentId === "pan") {
        if (status === "accepted") {
            $(".accept-pan-btn-doc-validated").addClass("doc-verified-green-class")
            $(".reject-pan-btn-doc-validated").removeClass("doc-reject-red-class")
        } else {
            $(".reject-pan-btn-doc-validated").addClass("doc-reject-red-class")
            $(".accept-pan-btn-doc-validated").removeClass("doc-verified-green-class")
        }
    }
}

function viewRequestData(requestId) {
    if (requestId == null) {
        showAlert(true, "Provide a valid requestId", "faliure")
    } else {
        $.ajax({
            url: CONTEXT_PATH + '/admin/get-request-data',
            type: 'POST',
            data: {
                requestId: requestId
            },
            success: function (xhr, status, error) {
                console.log(xhr)
                $(".reject-aadhar-btn-doc-validated").removeClass("doc-reject-red-class")
                $(".accept-aadhar-btn-doc-validated").removeClass("doc-verified-green-class")
                $(".reject-pan-btn-doc-validated").removeClass("doc-reject-red-class")
                $(".accept-pan-btn-doc-validated").removeClass("doc-verified-green-class")
                var response = xhr['viewDocumentRequestDataDto']
                $(".view-doc-name-div").text(response.userName)
                $(".view-doc-bday-div").text(response.birthDate)
                $(".view-doc-aadhar-numb-div").text(response.aadharCardNumber)
                $(".view-doc-pan-numb-div").text(response.panCardNumber)
                var aadharCardFilePath = response.aadharCardFilePath.replace(/\\/g, '/')
                var panCardFilePath = response.panCardFilePath.replace(/\\/g, '/')
                $(".view-aadhar-path-class").attr("onclick", "viewUploadedFile('" + aadharCardFilePath + "')")
                $(".view-pan-path-class").attr("onclick", "viewUploadedFile('" + panCardFilePath + "')")

                if (response.aadharCardVerified) {
                    $(".accept-aadhar-btn-doc-validated").addClass("doc-verified-green-class")
                    documentStatuses['aadhar'] = "accepted"
                } else {
                    documentStatuses['aadhar'] = ''
                }
                if (response.panCardVerified) {
                    $(".accept-pan-btn-doc-validated").addClass("doc-verified-green-class")
                    documentStatuses['pan'] = "accepted"
                } else {
                    documentStatuses['pan'] = ''
                }
                $("#hidden-request-id").val(response.requestId)
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

function removeValidationError() {
    $(".view-doc-modal-note-flex").addClass("d-none")
}

function approveVerifiedDocuments() {
    var requestId = $("#hidden-request-id").val()
    const statuses = Object.values(documentStatuses);
    if (statuses.includes(null)) {
        $(".view-doc-modal-note-flex").removeClass("d-none")
    } else {
        var payload = {}
        payload["requestId"] = requestId
        payload["aadharVerified"] = documentStatuses["aadhar"]
        payload["panVerified"] = documentStatuses["pan"]
        showLoader()
        $.ajax({
            url: CONTEXT_PATH + '/admin/verified-complete-documents',
            type: 'POST',
            data: payload,
            success: function (xhr, status, error) {
                hideLoader()
                $(".approve-docs-btn-close").click()
                showAlert(true, xhr["message"], "success")
                getFilteredRequestData(true)
            },
            error: function (xhr, status, error) {
                hideLoader()
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

function acceptBlockRequest(requestId) {
    $.ajax({
        url: CONTEXT_PATH + '/admin/accept-block-request',
        type: 'POST',
        data: {
            requestId: requestId
        },
        success: function (xhr, status, error) {
            hideLoader()
            console.log(xhr["messages"])
            showAlert(true, xhr["messages"], "success")
            getFilteredRequestData(true)
        },
        error: function (xhr, status, error) {
            hideLoader()
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

function rejectBlockRequest(requestId) {
    $.ajax({
        url: CONTEXT_PATH + '/admin/reject-block-request',
        type: 'POST',
        data: {
            requestId: requestId
        },
        success: function (xhr, status, error) {
            hideLoader()
            console.log(xhr["messages"])
            showAlert(true, xhr["messages"], "success")
            getFilteredRequestData(true)
        },
        error: function (xhr, status, error) {
            hideLoader()
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

function acceptUnblockRequest(requestId){
    $.ajax({
        url: CONTEXT_PATH + '/admin/accept-unblock-request',
        type: 'POST',
        data: {
            requestId: requestId
        },
        success: function (xhr, status, error) {
            hideLoader()
            console.log(xhr["messages"])
            showAlert(true, xhr["messages"], "success")
            getFilteredRequestData(true)
        },
        error: function (xhr, status, error) {
            hideLoader()
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

function rejectUnblockRequest(requestId){
    $.ajax({
        url: CONTEXT_PATH + '/admin/reject-unblock-request',
        type: 'POST',
        data: {
            requestId: requestId
        },
        success: function (xhr, status, error) {
            hideLoader()
            console.log(xhr["messages"])
            showAlert(true, xhr["messages"], "success")
            getFilteredRequestData(true)
        },
        error: function (xhr, status, error) {
            hideLoader()
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

function acceptDeactivationRequest(requestId){
    $.ajax({
        url: CONTEXT_PATH + '/admin/accept-deactivation-request',
        type: 'POST',
        data: {
            requestId: requestId
        },
        success: function (xhr, status, error) {
            hideLoader()
            console.log(xhr["messages"])
            showAlert(true, xhr["messages"], "success")
            getFilteredRequestData(true)
        },
        error: function (xhr, status, error) {
            hideLoader()
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

function rejectDeactivationRequest(requestId){
    $.ajax({
        url: CONTEXT_PATH + '/admin/reject-deactivation-request',
        type: 'POST',
        data: {
            requestId: requestId
        },
        success: function (xhr, status, error) {
            hideLoader()
            console.log(xhr["messages"])
            showAlert(true, xhr["messages"], "success")
            getFilteredRequestData(true)
        },
        error: function (xhr, status, error) {
            hideLoader()
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








