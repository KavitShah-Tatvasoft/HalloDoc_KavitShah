function deleteRequest(reqId) {

	$.ajax({
		url: 'deleteRequest',
		type: 'POST',
		data: {

			reqId: reqId

		},
		success: function(res) {

			getSearchRecordsData()
		},
		error: function(res) {

		}

	})


}


function clearFilters() {

	$(".filter-request-status").prop('selectedIndex', 0);
	$(".filter-name").val("")
	$(".filter-request-type").prop('selectedIndex', 0);
	$(".filter-dos").val("")
	$(".filter-to-dos").val("")
	$(".filter-provider-name").val("")
	$(".filter-email").val("")
	$(".filter-phone").val("")

	getSearchRecordsData()
}

function changeActivePage(element) {
	debugger
	$(".add-active").removeClass("active")
	console.log(element)

	element.classList.add('active');
	getSearchRecordsData(false)
}

function prevPage() {
	debugger
	var pageNo = $(".page-link.active").attr("data-pg")
	const prevPage = pageNo - 1;
	const prevLink = document.querySelector(`.page-link[data-pg="${prevPage}"]`);
	if (prevLink) {
		prevLink.click();
	}
}

function nextPage() {
	debugger
	var pageNo = $(".page-link.active").attr("data-pg")
	const nextPage = parseInt(pageNo) + 1;
	const nextLink = document.querySelector(`.page-link[data-pg="${nextPage}"]`);
	if (nextLink) {
		nextLink.click();
	}
}


function getSearchRecordsData(bool) {

	debugger
	var requestStatus = $(".filter-request-status").val()
	var firstName = $(".filter-name").val()
	var requestType = $(".filter-request-type").val()
	var fromDOS = $(".filter-dos").val()
	var toDOS = $(".filter-to-dos").val()
	var providerName = $(".filter-provider-name").val()
	var email = $(".filter-email").val()
	var phoneNumber = $(".filter-phone").val()
	if (bool == true) {
		var pageNo = 1
	} else {
		var pageNo = $(".page-link.active").attr("data-pg")
	}

	var payload = {}

	payload["requestStatus"] = requestStatus
	payload["firstName"] = firstName
	payload["requestType"] = requestType
	payload["fromDOS"] = fromDOS
	payload["toDOS"] = toDOS
	payload["providerName"] = providerName
	payload["email"] = email
	payload["phoneNumber"] = phoneNumber
	payload["pageNo"] = pageNo

	tbody = $(".table-empty-class")
	accordionBody = $(".accordion-body-empty")
	$.ajax({
		url: 'getSearchRecordFilteredData',
		type: 'POST',
		data: payload,
		success: function(res) {


			tbody.empty()
			accordionBody.empty()
			count = 120

			let paginationBody = $(".empty-pagination")
			paginationBody.empty()
			var prev = $(".prev-navigation").clone().removeClass("prev-navigation").removeClass("d-none")
			var next = $(".next-pagination").clone().removeClass("next-pagination").removeClass("d-none")
			paginationBody.append(prev)

			var totalPageNumber = Math.ceil(res.count / 10)

			for (let i = 1; i <= totalPageNumber; i++) {
				paginationNumber = $(".pageno-pagination").clone().removeClass("pageno-pagination").removeClass("d-none")
				paginationNumber.find(".page-link").text(i)
				paginationNumber.find(".page-link").attr("data-pg", i)
				paginationNumber.find(".page-link").attr("onclick", "changeActivePage(this)")
				if (i == pageNo) {
					paginationNumber.find(".add-active").addClass("active")
				}

				paginationBody.append(paginationNumber)
			}

			if (pageNo == 1) {
				prev.addClass("disabled")
			} else {
				prev.removeClass("disabled")
			}

			if (pageNo == totalPageNumber) {
				next.addClass("disabled")
			} else {
				next.removeClass("disabled")
			}

			paginationBody.append(next)

			if (totalPageNumber == 1 || totalPageNumber == 0) {
				prev.addClass("disabled")
				next.addClass("disabled")
			}


			res.searchRecordsDashboardDatas.forEach(function(data) {
				count++
				var rowCard = $(".table-td-class-clone").clone().removeClass("d-none").removeClass("table-td-class-clone")
				rowCard.find(".name-td").text(data.patientName)
				rowCard.find(".requestor-td").text(data.requestorName)
				rowCard.find(".dos-td").text(data.dateOfService)
				rowCard.find(".doc-td").text(data.closeCaseDate)
				rowCard.find(".email-td").text(data.email)
				rowCard.find(".phone-number-td").text(data.phoneNumber)
				rowCard.find(".address-td").text(data.address)
				rowCard.find(".zip-td").text(data.zip)
				rowCard.find(".status-td").text(data.requestStatus)
				rowCard.find(".phy-td").text(data.physicianName)
				rowCard.find(".phy-nt-td").text(data.physicianNote)
				rowCard.find(".admin-nt-td").text(data.adminNotes)
				rowCard.find(".patient-nt-td").text(data.patientNotes)
				rowCard.find(".delete-td").attr("onclick", "deleteRequest(" + data.requestId + ")")
				tbody.append(rowCard)


				var accordionCard = $(".accordion-clone-class").clone().removeClass("d-none").removeClass("accordion-clone-class")
				accordionCard.find(".change-target-class").attr("data-bs-target", "#accordion" + count)
				accordionCard.find(".change-id-class").attr("id", "accordion" + count)
				accordionCard.find(".acc-name-td").text(data.patientName)
				accordionCard.find(".acc-address-td").text(data.address)
				accordionCard.find(".acc-request-type-td").text(data.requestorName)
				accordionCard.find(".acc-dos-td").text(data.dateOfService)
				accordionCard.find(".acc-doc-td").text(data.closeCaseDate)
				accordionCard.find(".acc-email-td").text(data.email)
				accordionCard.find(".acc-phone-td").text(data.phoneNumber)
				accordionCard.find(".acc-address-td1").text(data.address)
				accordionCard.find(".acc-zip-td").text(data.zip)
				accordionCard.find(".acc-status-td").text(data.requestStatus)
				accordionCard.find(".acc-provider-td").text(data.physicianName)
				accordionCard.find(".acc-provider-notes-td").text(data.physicianNote)
				accordionCard.find(".acc-admin-notes-td").text(data.adminNotes)
				accordionCard.find(".acc-pt-notes").text(data.patientNotes)
				accordionCard.find(".delete-td").attr("onclick", "deleteRequest(" + data.requestId + ")")
				accordionBody.append(accordionCard)
			})



		},
		error: function(res) {

		}

	})
}


function downloadFilteredData() {

	var requestStatus = $(".filter-request-status").val()
	var firstName = $(".filter-name").val()
	var requestType = $(".filter-request-type").val()
	var fromDOS = $(".filter-dos").val()
	var toDOS = $(".filter-to-dos").val()
	var providerName = $(".filter-provider-name").val()
	var email = $(".filter-email").val()
	var phoneNumber = $(".filter-phone").val()

	var payload = {}

	payload["requestStatus"] = requestStatus
	payload["firstName"] = firstName
	payload["requestType"] = requestType
	payload["fromDOS"] = fromDOS
	payload["toDOS"] = toDOS
	payload["providerName"] = providerName
	payload["email"] = email
	payload["phoneNumber"] = phoneNumber
	showLoader()
	$.ajax({
		url: 'exportSearchRecordsToExcel',
		type: 'POST',
		xhrFields: {
			responseType: 'blob'
		},
		data: payload,
		success: function(data) {

			var blob = new Blob([data]);
			var link = document.createElement("a");
			link.href = window.URL.createObjectURL(blob);
			link.download = "Documents.xlsx";
			document.body.appendChild(link);
			link.click();
			document.body.removeChild(link);
			hideLoader()
		}, error: function(data) {
			hideLoader()
		}


	})

}
