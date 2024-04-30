function deleteRequest(reqId) {

	$.ajax({
		url: 'deleteRequest',
		type: 'POST',
		data: {

			reqId: reqId

		},
		success: function(res) {
			console.log(res)
			getSearchRecordsData()
		},
		error: function(res) {
			console.log("failed to delete data")
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

function getSearchRecordsData() {

	debugger
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

	tbody = $(".table-empty-class")
	accordionBody = $(".accordion-body-empty")
	$.ajax({
		url: 'getSearchRecordFilteredData',
		type: 'POST',
		data: payload,
		success: function(res) {
			console.log(res)
			console.log("Data Obtained")
			tbody.empty()
			accordionBody.empty()
			count = 120
			res.forEach(function(data) {
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
			console.log("Failed to obtain data")
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

	$.ajax({
		url: 'exportSearchRecordsToExcel',
		type: 'POST',
		xhrFields: {
			responseType: 'blob'
		},
		data: payload,
		success: function(data) {
			console.log("export succesful")
			var blob = new Blob([data]);
			var link = document.createElement("a");
			link.href = window.URL.createObjectURL(blob);
			link.download = "Documents.xlsx";
			document.body.appendChild(link);
			link.click();
			document.body.removeChild(link);
		}, error: function(data) {
			console.log("export failed")
		}


	})

}
