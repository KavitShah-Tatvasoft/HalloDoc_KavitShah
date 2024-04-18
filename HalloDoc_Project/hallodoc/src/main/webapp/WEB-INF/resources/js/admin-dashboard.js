function switchBlack() {
	alert("switched to black theme")
}

function switchWhite() {
	alert("switched to white theme")
}

function cancelCase(ptName, reqId) {
	$(".cancel-case-text-change").text(ptName)
	$("#cancel-case-request-id").val(reqId)
}

function clearCase(reqId) {
	$("#clear-case-request-id").val(reqId)
}

function blockCase(ptName, reqId) {
	$(".block-patient-name-text").text(ptName)
	$("#block-case-request-id").val(reqId)
}

function assignCase(reqId) {
	$("#assign-case-request-id").val(reqId)
	var showPhysicianError = document.getElementById("select-physician-error")
	showPhysicianError.innerHTML = ""
}

function transferCase(reqId) {
	$("#transfer-case-request-id").val(reqId)
	var showPhysicianError = document.getElementById("select-physician-error-transfer")
	var showRegionError = document.getElementById("region-select-transfer-error")
	showPhysicianError.innerHTML = ""
	showRegionError.innerHTML = ""
}

function removePhysicianError() {
	var showPhysicianError = document.getElementById("select-physician-error-transfer")
	showPhysicianError.innerHTML = ""
}



const changeStatus = (element) => {
	const stateName = document.getElementById("type-text")
	const states = document.getElementsByClassName("row-cards")
	const current_state = element.classList[1]
	const capitalizedState = current_state.charAt(0).toUpperCase() + current_state.slice(1);
	console.log(current_state)
	stateName.innerHTML = "(" + capitalizedState + ")"
	$(".state-type-class-name").attr("data-state", current_state)

	$(".export-btn-state-admindashboard").attr("onclick", "exportData('" + current_state.toUpperCase() + "')")

	if (current_state == "to-close") {
		stateName.innerHTML = "(To Close)"
		$(".state-type-class-name").attr("data-state", "to-close")
	}

	for (let i = 0; i < states.length; ++i) {
		if (states[i].classList.length == 3) {
			states[i].classList.remove(`${states[i].classList[1]}-active`)
			document.getElementById(`${states[i].classList[1]}-active`).classList.add('hidden')
			document.getElementById(`${states[i].classList[1]}`).classList.remove('hidden')
			document.getElementById(`${states[i].classList[1]}-img`).classList.add('hidden')
		}
		else if (states[i].classList[1] == current_state) {
			states[i].classList.add(`${states[i].classList[1]}-active`)
			document.getElementById(`${states[i].classList[1]}-active`).classList.remove('hidden')
			document.getElementById(`${states[i].classList[1]}`).classList.add('hidden')
			document.getElementById(`${states[i].classList[1]}-img`).classList.remove('hidden')
		}
	}

	//	const obj = {};
	//	const columns = document.getElementsByClassName("table-columns");
	//	const th_states_key = ['new', 'pending', 'active', 'conclude', 'to-close', 'unpaid'];
	//	const th_states_values = ['s1', 's2', 's3', 's4', 's5', 's6'];
	//
	//	for (let i = 0; i < th_states_key.length; i++) {
	//		obj[th_states_key[i]] = th_states_values[i];
	//	}
	//
	//	for (let i = 0; i < columns.length; ++i) {
	//		columns[i].classList.add('d-none');
	//	}
	//
	//	for (let i = 0; i < columns.length; ++i) {
	//		if (columns[i].classList.contains(obj[current_state])) {
	//			columns[i].classList.remove('d-none');
	//		}
	//	}
	//
	//
	//	$.ajax({
	//		url: 'getRequestData',
	//		type: 'POST',
	//		dataType: 'json',
	//		data: {
	//			status: current_state
	//		},
	//
	//		success: function(res) {
	//			console.log("Success")
	//			console.log(res)
	//			let tbody = $("#admin-table tbody")
	//			let accordionBody = $(".empty-accordion")
	//			tbody.empty()
	//			accordionBody.empty()
	//			var count = 120;
	//			res.forEach(function(data) {
	//
	//				count = count + 1
	//
	//				if (current_state == "new" && !(data.deleted)) {
	//					var card = createNewReqRow(data);
	//					tbody.append(card)
	//				}
	//
	//				if (current_state != "new" && !(data.deleted)) {
	//					var card = createOtherReqRow(data, current_state);
	//					tbody.append(card)
	//				}
	//
	//
	//				var accordionCard = poplulateAccordions(data, current_state, count)
	//				console.log(accordionCard);
	//				accordionBody.append(accordionCard)
	//
	//			})
	//
	//			const currentActions = document.getElementsByClassName(current_state + "s")
	//			for (let i = 0; i < currentActions.length; i++) {
	//				currentActions[i].classList.remove('d-none')
	//			}
	//
	//		},
	//		error: function() {
	//			console.log("error fetching request data")
	//		}
	//
	//	})

	loadData(current_state)
	loadCount()


}

function loadData(current_state) {
	const obj = {};
	const columns = document.getElementsByClassName("table-columns");
	const th_states_key = ['new', 'pending', 'active', 'conclude', 'to-close', 'unpaid'];
	const th_states_values = ['s1', 's2', 's3', 's4', 's5', 's6'];

	for (let i = 0; i < th_states_key.length; i++) {
		obj[th_states_key[i]] = th_states_values[i];
	}

	for (let i = 0; i < columns.length; ++i) {
		columns[i].classList.add('d-none');
	}

	for (let i = 0; i < columns.length; ++i) {
		if (columns[i].classList.contains(obj[current_state])) {
			columns[i].classList.remove('d-none');
		}
	}


	$.ajax({
		url: 'getRequestData',
		type: 'POST',
		dataType: 'json',
		data: {
			status: current_state
		},

		success: function(res) {
			console.log("Success")
			console.log(res)
			let tbody = $("#admin-table tbody")
			let accordionBody = $(".empty-accordion")
			tbody.empty()
			accordionBody.empty()
			var count = 120;
			res.forEach(function(data) {

				count = count + 1

				if (current_state == "new" && !(data.deleted)) {
					var card = createNewReqRow(data);
					tbody.append(card)
				}

				if (current_state != "new" && !(data.deleted)) {
					var card = createOtherReqRow(data, current_state);
					tbody.append(card)
				}


				var accordionCard = poplulateAccordions(data, current_state, count)
				console.log(accordionCard);
				accordionBody.append(accordionCard)

			})

			const currentActions = document.getElementsByClassName(current_state + "s")
			for (let i = 0; i < currentActions.length; i++) {
				currentActions[i].classList.remove('d-none')
			}

		},
		error: function() {
			console.log("error fetching request data")
		}

	})
}

function loadCount() {

	$.ajax({
		url: 'getStatusWiseCount',
		type: 'POST',
		dataType: 'json',
		success: function(res) {
			var countNew = document.getElementById("new-request-count");
			var countPending = document.getElementById("pending-request-count");
			var countActive = document.getElementById("active-request-count");
			var countConclude = document.getElementById("conclude-request-count");
			var countToClose = document.getElementById("to-close-request-count");
			var countUnpaid = document.getElementById("unpaid-request-count");
			console.log(res)

			countNew.innerHTML = res[0];
			countPending.innerHTML = res[1];
			countActive.innerHTML = res[2];
			countConclude.innerHTML = res[3];
			countToClose.innerHTML = res[4];
			countUnpaid.innerHTML = res[5];

		},
		error: function() {
			console.log("Error 2")
		}
	})

}

function createNewReqRow(data) {

	if (data.reqPhoneNumberType == "Patient") {
		var card = `<tr class="tr-clr">`
	}

	if (data.reqPhoneNumberType == "Family") {
		var card = `<tr class="tr-family">`
	}

	if (data.reqPhoneNumberType == "Concierge") {
		var card = `<tr class="tr-concierge">`
	}

	if (data.reqPhoneNumberType == "Business") {
		var card = `<tr class="tr-business">`
	}
	var card1 = `				<td colspan="2"><span class="text-nowrap">` + data.name + `</span></td>
						<td>
							<button type="button" class="theme-btn" id="message-btn"></button>
							<label for="message-btn"> <img
								src="/hallodoc/resources/images/envelope.svg"
								alt="message" class="envelope">
						</label>
						</td>
						<td class="text-nowrap">`+ data.month + ` ` + data.day + `,` + data.year + `</td>
						<td class="text-nowrap">`+ data.requestor + `</td>
						<td >`+ data.requestedDate + `<div>` + data.requestedTimeDifference + `</div></td>
						<td> <div class="telephone-border"><img src="/hallodoc/resources/images/telephone.svg">`+ data.ptPhoneNumber + `</div>
							 <div>(`+ data.ptPhoneNumberType + `)</div>
							  <div class="telephone-border"><img src="/hallodoc/resources/images/telephone.svg">`+ data.reqPhoneNumber + `</div>
							 <div class="margin-bottoms">(`+ data.reqPhoneNumberType + `)</div>
						</td>
						<td>`+ data.street + `, ` + data.city + `, ` + data.state + `, ` + data.zipcode + `</td>
						<td>`+ data.notes + `</td>
						
						<td>
							<div class="patient-records-document-action-button">
								<button type="button"
									class="btn dashboard-dropdown-btn dropdown-toggle"
									data-bs-toggle="dropdown" aria-expanded="false">
									Action</button>
								<ul class="dropdown-menu dropdown-menu-end">
									<li>
										<div class="action-dropdown-flex dropdown-item ">
											<img
												src="/hallodoc/resources/images/journal-check-grey.svg"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" role="button"  onclick="assignCase('` + data.requestId + `')"
												data-bs-toggle="modal" data-bs-target="#assign-case">Assign
												Case</a>
										</div>
									</li>
									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="/hallodoc/resources/images/x-circle-grey.svg"
												class="dropdown-icons" alt=""> <a
												class=" action-dropdown-text" type="button"  onclick="cancelCase('`+ data.name + `','` + data.requestId + `')"
												data-bs-toggle="modal" data-bs-target="#cancel-case">Cancel
												Case</a>
										</div>
									</li>
									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="/hallodoc/resources/images/view-case.png"
												class="dropdown-icons" alt=""> <a
												href="viewCase/` + data.requestId + `" class=" action-dropdown-text"
												type="button">View Case</a>
										</div>
									</li>
									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="/hallodoc/resources/images/journal-text.svg"
												class="dropdown-icons" alt=""> <a
												href="viewNotes/`+ data.requestId + `" class="action-dropdown-text"
												type="button">View Notes</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="/hallodoc/resources/images/ban-grey.svg"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" data-bs-toggle="modal" onclick="blockCase('`+ data.name + `','` + data.requestId + `')"
												data-bs-target="#block-case" role="button">Block Patient</a>
										</div>
									</li>
								</ul>
							</div>
						</td>
					<tr>`

	return card + card1;
}

function createOtherReqRow(data, current_state) {

	if (data.reqPhoneNumberType == "Patient") {
		var card = `<tr class="tr-clr">`
	}

	if (data.reqPhoneNumberType == "Family") {
		var card = `<tr class="tr-family">`
	}

	if (data.reqPhoneNumberType == "Concierge") {
		var card = `<tr class="tr-concierge">`
	}

	if (data.reqPhoneNumberType == "Business") {
		var card = `<tr class="tr-business">`
	}

	var card1 = `				<td colspan="2"><span class="text-nowrap">` + data.name + `</span></td>
						<td>
							<button type="button" class="theme-btn" id="message-btn"></button>
							<label for="message-btn"> <img
								src="/hallodoc/resources/images/envelope.svg"
								alt="message" class="envelope">
						</label>
						</td>
						<td class="text-nowrap news pendings actives concludes to-closes d-none">`+ data.month + ` ` + data.day + `, ` + data.year + `</td>
						<td class="to-closes d-none">`+ data.region + `</td>
						<td class="text-nowrap news pendings actives d-none">`+ data.requestor + `</td>
						<td class="text-nowrap pendings actives concludes to-closes unpaids d-none">`+ data.physicianName + `</td>
						<td class="pendings actives concludes to-closes unpaids d-none">`+ data.dateOfService + `</td>
						<td class="news pendings actives concludes unpaids d-none"> <div class="telephone-border"><img src="/hallodoc/resources/images/telephone.svg">`+ data.ptPhoneNumber + `</div>
							 <div>(`+ data.ptPhoneNumberType + `)</div>
							  <div class="telephone-border"><img src="/hallodoc/resources/images/telephone.svg">`+ data.reqPhoneNumber + `</div>
							 <div class="margin-bottoms">(`+ data.reqPhoneNumberType + `)</div>
						</td>
						<td >`+ data.street + `, ` + data.city + `, ` + data.state + `, ` + data.zipcode + `</td>
						<td class="news pendings actives to-closes d-none">`+ data.notes + `</td> 
						
						<td>
							<div class="patient-records-document-action-button">
								<button type="button"
									class="btn dashboard-dropdown-btn dropdown-toggle"
									data-bs-toggle="dropdown" aria-expanded="false">
									Action</button>
								<ul class="dropdown-menu dropdown-menu-end">
									
									

									<li class="pendings to-closes d-none">
										<div class="action-dropdown-flex dropdown-item" id="clear-case">
											<img
												src="/hallodoc/resources/images/x-circle-grey.svg"
												class="dropdown-icons" alt=""> <a onclick="clearCase('` + data.requestId + `')"
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#clear-case-this" type="button">Clear Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item" id="view-case">
											<img src="/hallodoc/resources/images/view-case.png"
												class="dropdown-icons" alt=""> <a
												href="viewCase/` + data.requestId + `" class=" action-dropdown-text"
												type="button">View Case</a>
										</div>
									</li>

									<li class="to-closes d-none">
										<div class="action-dropdown-flex dropdown-item" id="close-case">
											<img
												src="/hallodoc/resources/images/x-circle-grey.svg"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" role="button"
												href="close-case.html">Close Case</a>
										</div>
									</li>

									<li >
										<div class="action-dropdown-flex dropdown-item" id="view-notes">
											<img
												src="/hallodoc/resources/images/journal-text.svg"
												class="dropdown-icons" alt=""> <a
												href="viewNotes/`+ data.requestId + `" class="action-dropdown-text"
												type="button">View Notes</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item" id="view-uploads">
											<img
												src="/hallodoc/resources/images/view-upload.png"
												class="dropdown-icons" alt=""> <a
												href="<c:url value='' />" class="action-dropdown-text "
												type="button">View Uploads</a>
										</div>
									</li>

									<li class="pendings d-none">
										<div class="action-dropdown-flex dropdown-item" id="transfer">
											<img
												src="/hallodoc/resources/images/journal-check-grey.svg"
												class="dropdown-icons" alt=""> <a onclick="transferCase(`+ data.requestId + `)"
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#transfer-case" type="button">Transfer</a>
										</div>
									</li>

									<li class="pendings d-none">
										<div class="action-dropdown-flex dropdown-item" id=send-agreement">
											<img src="/hallodoc/resources/images/document.png"
												class="dropdown-icons" alt=""> <a onclick="sendAgreement(`+ data.requestId + `)"
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#send-agreement" type="button">Send
												Agreement</a>
										</div>
									</li>

									<li class="actives to-closes d-none">
										<div class="action-dropdown-flex dropdown-item" id="orders">
											<img
												src="/hallodoc/resources/images/order-delivery.png"
												class="dropdown-icons" alt=""> <a
												href="<c:url value='' />" class="action-dropdown-text"
												type="button">Orders</a>
										</div>
									</li>

									<li class="actives concludes to-closes d-none">
										<div class="action-dropdown-flex dropdown-item" id="doctor-notes">
											<img src="/hallodoc/resources/images/document.png"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" type="button"
												href="<c:url value='' />">Doctor Notes</a>
										</div>
									</li>

									<li class="actives concludes to-closes d-none">
										<div class="action-dropdown-flex dropdown-item" id="encounter">
											<img src="/hallodoc/resources/images/document.png"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" type="button"
												href="<c:url value='' />">Encounter</a>
										</div>
									</li>
									
									<li class="concludes d-none">
										<div class="action-dropdown-flex dropdown-item" id="encounter">
											<img src="/hallodoc/resources/images/document.png"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" type="button"
												href="<c:url value='' />">Conclude Care</a>
										</div>
									</li>

								</ul>
							</div>
						</td>
						<tr>`





	return card + card1

}

$(document).ready(function() {

	loadCount()

	$.ajax({
		url: 'getRequestData',
		type: 'POST',
		dataType: 'json',
		data: {
			status: "new"
		},

		success: function(res) {
			console.log("Success")
			console.log(res)
			let tbody = $("#admin-table tbody")
			let accordionBody = $(".empty-accordion")
			tbody.empty()
			accordionBody.empty()
			let count = 121;
			res.forEach(function(data) {

				const obj = {};
				const columns = document.getElementsByClassName("table-columns");
				const th_states_key = ['new', 'pending', 'active', 'conclude', 'to-close', 'unpaid'];
				const th_states_values = ['s1', 's2', 's3', 's4', 's5', 's6'];

				for (let i = 0; i < th_states_key.length; i++) {
					obj[th_states_key[i]] = th_states_values[i];
				}

				for (let i = 0; i < columns.length; ++i) {
					columns[i].classList.add('d-none');
				}

				for (let i = 0; i < columns.length; ++i) {
					if (columns[i].classList.contains(obj["new"])) {
						columns[i].classList.remove('d-none');
					}
				}


				if (!(data.deleted)) {
					var card = createNewReqRow(data);
					tbody.append(card)
				}

				var initalAccordionCard = poplulateAccordions(data, "new", count)
				accordionBody.append(initalAccordionCard)
				count += 1
			})

			const currentActions = document.getElementsByClassName("news")
			for (let i = 0; i < currentActions.length; i++) {
				currentActions[i].classList.remove('d-none')
			}


		},
		error: function() {
			console.log("error fetching initial request data")
		}
	})
})

function poplulateAccordions(data, current_state, count) {

	var card = $("#accordion-single-card").clone().removeClass("d-none")

	card.find(".action-class").addClass("d-none")

	var button = card.find(".accordion-button")
	button.attr("data-bs-target", "#p" + data.zipcode + data.ptPhoneNumber + count)

	var ptName = card.find("#accordion-patient-name-id")
	ptName.text(data.name)

	card.find(".patient-type-text").text(data.reqPhoneNumberType)

	var colorIcon = card.find(".color-icon")

	if (data.reqPhoneNumberType == "Patient") {
		colorIcon.addClass("patient-type-color-icon")
		card.find(".requestor-phone-class").remove()

	}
	if (data.reqPhoneNumberType == "Family") {
		colorIcon.addClass("family-type-color-icon")
	}
	if (data.reqPhoneNumberType == "Business") {
		colorIcon.addClass("business-type-color-icon")
	}
	if (data.reqPhoneNumberType == "Concierge") {
		colorIcon.addClass("concierge-type-color-icon")
	}

	card.find(".patient_card_address_admin").text(data.street + ", " + data.city + ", " + data.state + ", " + data.zipcode)

	card.find(".change-id").attr("id", "p" + data.zipcode + data.ptPhoneNumber + count)

	card.find(".dateOfBirth").text(data.month + " " + data.day + ", " + data.year)

	card.find(".accordion-pt-email").text(data.ptEmail)

	card.find(".accordion-pt-phone").text(data.ptPhoneNumber)

	card.find(".accordion-requestor-phone").text(data.reqPhoneNumber)

	card.find(".accordion-transfer-note").text(data.notes)

	card.find(".accordion-date-of-service").text(data.dateOfService)

	card.find(".accordion-physician-name").text(data.physicianName)

	card.find(".accordion-requestor").text(data.requestor)

	card.find(".requestor-type-text").text(data.reqPhoneNumberType + ":")

	card.find(".request-time-diffrence").text(data.requestedDate + " " + data.requestedTime + "(" + data.requestedTimeDifference + ")")

	card.find(".view-case-anchor-class").attr("href", "viewCase/" + data.requestId)

	card.find(".assign-case-anchor-class").attr("onclick", "assignCase('" + data.requestId + "')")

	card.find(".cancel-case-anchor-class").attr("onclick", "cancelCase('" + data.name + "','" + data.requestId + "')")

	card.find(".view-notes-anchor-class").attr("href", "viewNotes/" + data.requestId)

	card.find(".block-case-anchor-class").attr("onclick", "blockCase('" + data.name + "','" + data.requestId + "')")
	return card;
}

// SendLinkFormSubmit

$("#sendLinkForm").submit(function(event) {

	event.preventDefault();
	var serializedForm1 = $(this).serializeArray();

	var payloadData = {}
	$(serializedForm1).each(function(i, value) {
		payloadData[value.name] = value.value
	})

	console.log(payloadData);

	$.ajax({
		url: 'sendLinkByEmail',
		type: 'POST',
		data: payloadData,
		success: function(data) {
			console.log(data)
			console.log("Form Submitted")
		},
		error: function(data) {
			console.log(data)
			console.log("Error while send link ajax call")
		}


	});

	$("#sendLinkForm").get(0).reset()

});

function changeActiveBtn(element) {
	console.log("change me")
	$(".button-class").removeClass("active-btn")
	$(".commom-label-class").removeClass("show-active-button-class")
	$(element).addClass("active-btn")
	$(element).next().addClass("show-active-button-class")
}

function filterRequest() {
	debugger
	//	var requestType = $(".button-class.active-btn").html()
	var patientName = document.getElementById("patient-name-search").value
	var stateName = document.getElementById("region-name-search").value
	var requestType = $(".button-class.active-btn").attr("data-value")
	var statusType = $("#type-text").html();

	statusType = statusType.slice(1, -1).toLowerCase();
	var current_state = statusType
	if (current_state == "to close") { current_state = "to-close" }


	if (statusType == "new") {
		statusType = 1
	}
	if (statusType == "pending") {
		statusType = 2
	}
	if (statusType == "active") {
		statusType = 3
	}
	if (statusType == "conclude") {
		statusType = 4
	}
	if (statusType == "to close") {
		statusType = 5
	}
	if (statusType == "unpaid") {
		statusType = 6
	}

	var payLoadData = {}

	payLoadData["patientName"] = patientName
	payLoadData["stateName"] = stateName
	payLoadData["requestType"] = requestType
	payLoadData["statusType"] = statusType

	console.log(payLoadData)

	$.ajax({
		url: 'searchRequestFilter',
		type: 'POST',
		data: payLoadData,
		success: function(res) {
			console.log("Filter Successfully Applied")

			let tbody = $("#admin-table tbody")
			let accordionBody = $(".empty-accordion")
			tbody.empty()
			accordionBody.empty()
			var count = 120;
			res.forEach(function(data) {

				count = count + 1

				if (current_state == "new" && !(data.deleted)) {
					var card = createNewReqRow(data);
					tbody.append(card)
				}

				if (current_state != "new" && !(data.deleted)) {
					var card = createOtherReqRow(data, current_state);
					tbody.append(card)
				}


				var accordionCard = poplulateAccordions(data, current_state, count)
				console.log(accordionCard);
				accordionBody.append(accordionCard)


			})

			const currentActions = document.getElementsByClassName(current_state + "s")
			for (let i = 0; i < currentActions.length; i++) {
				currentActions[i].classList.remove('d-none')
			}


		},
		error: function(res) {
			console.log("Filter Failed to be Applied")
		}
	});

}


$("#cancelCaseForm").submit(function(event) {
	debugger
	event.preventDefault();
	var caseTagId = document.getElementById("cancellation-reason").value
	var additionalNotes = document.getElementById("additional-notes-cancellation").value
	var requestId = document.getElementById("cancel-case-request-id").value
	var current_state = $(".state-type-class-name").attr("data-state")
	var payLoadData = {}

	payLoadData["caseTagId"] = caseTagId
	payLoadData["additionalNotes"] = additionalNotes
	payLoadData["requestId"] = requestId

	console.log(payLoadData)

	$.ajax({
		url: 'cancelRequestedCase',
		type: 'POST',
		data: payLoadData,
		success: function(res) {
			loadCount()
			loadData(current_state)
			console.log("Request Cancelled")
		},
		error: function(res) {
			console.log("Request Failed to be Cancelled")
		}

	});

	$("#cancelCaseForm").get(0).reset()


});

$("#blockPatientForm").submit(function(event) {
	event.preventDefault();

	var blockReason = document.getElementById("block-case-reason").value
	var requestId = document.getElementById("block-case-request-id").value
	var current_state = $(".state-type-class-name").attr("data-state")
	var payLoadData = {}

	payLoadData["blockReason"] = blockReason
	payLoadData["requestId"] = requestId

	console.log(payLoadData)

	$.ajax({
		url: 'blockRequestedCase',
		type: 'POST',
		data: payLoadData,
		success: function(res) {
			loadCount()
			loadData(current_state)
			console.log("Request Blocked")
		},
		error: function(res) {
			console.log("Failed to block request")
		}

	});

	$("blockPatientForm").get(0).reset()



});

function getAssignCaseRegion() {
	var region = $(".region-name-class option:selected").val()
	getPhysiciansByRegion(region)

}

function getTransferCaseRegion() {
	var showRegionError = document.getElementById("region-select-transfer-error")
	showRegionError.innerHTML = ""
	var region = $(".region-name-class-transfer option:selected").val()
	getPhysiciansByRegion(region)

}

function getPhysiciansByRegion(region) {


	debugger
	console.log(region)
	$.ajax({
		url: 'getPhysiciansByRegion',
		type: 'POST',
		data: {
			regionId: region
		},
		success: function(res) {
			console.log(res)
			console.log("Physician List Obtained")

			$(".physician-name-class").empty()
			$(".physician-name-class").append("<option value='0' hidden selected>Select Physician</option>")

			res.forEach(function(data) {
				$(".physician-name-class").append("<option value='" + data[0] + "'>Dr. " + data[1] + " " + data[2] + "</option>")
			})

		},
		error: function(res) {
			console.log("Failed to Obtain Physician List ")
		}

	});

}

$("#assign-case-form").submit(function(event) {
	debugger
	event.preventDefault();
	var showPhysicianError = document.getElementById("select-physician-error")
	var reqId = document.getElementById("assign-case-request-id").value
	var physicianId = $(".physician-name-class option:selected").val()
	var description = document.getElementById("description-text-area").value

	payload = {}
	payload["reqId"] = reqId
	payload["physicianId"] = physicianId
	payload["description"] = description

	if (physicianId == 0) {

		showPhysicianError.innerHTML = "Please select a physician."
	} else {
		$(".close-assign-case").click()
		$.ajax({
			url: 'assignPhysician',
			type: 'POST',
			data: payload,
			success: function(data) {
				console.log("assigned sucessfully")
			},
			error: function(data) {
				console.log("failed to assign provider")
			}
		})

		$("#assign-case-form").get(0).reset()
	}
})

function exportData(current_status) {

	console.log(current_status)
	debugger

	if (current_status != "ALL") {

		var patientName = document.getElementById("patient-name-search").value
		var stateName = document.getElementById("region-name-search").value
		var requestType = $(".button-class.active-btn").attr("data-value")

		var statusType = $("#type-text").html();

		statusType = statusType.slice(1, -1).toLowerCase();

		if (statusType == "new") {
			statusType = 1
		}
		if (statusType == "pending") {
			statusType = 2
		}
		if (statusType == "active") {
			statusType = 3
		}
		if (statusType == "conclude") {
			statusType = 4
		}
		if (statusType == "to close") {
			statusType = 5
		}
		if (statusType == "unpaid") {
			statusType = 6
		}



		var payload = {}
		payload["patientName"] = patientName
		payload["stateName"] = stateName
		payload["requestType"] = requestType
		payload["statusType"] = statusType
		payload["currentStatus"] = current_status

	}
	else {
		var payload = {}
		payload["patientName"] = ""
		payload["stateName"] = "All"
		payload["requestType"] = "All"
		payload["statusType"] = 7
		payload["currentStatus"] = current_status
	}
	$.ajax({
		url: 'exportStatusWiseData',
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


$("#clear-case-form").submit(function(event) {
	debugger
	event.preventDefault();
	var current_state = $(".state-type-class-name").attr("data-state")
	var reqId = document.getElementById("clear-case-request-id").value
	var error = document.getElementById("clear-case-error")

	$.ajax({
		url: 'clearCase',
		type: 'POST',
		data: {
			reqId: reqId
		},
		success: function(data) {
			$(".cancel-case-btn-class").click()
			loadCount()
			loadData(current_state)
			console.log("Case Cleared Succesfully")

		},
		error: function(data) {
			console.log("failed to assign provider")
			error.innerHTML = "Failed to clear the case"
		}
	})



})

$("#transfer-request-form").submit(function(event) {
	debugger
	event.preventDefault();
	var showPhysicianError = document.getElementById("select-physician-error-transfer")
	var showRegionError = document.getElementById("region-select-transfer-error")
	var reqId = document.getElementById("transfer-case-request-id").value
	var regionId = $(".region-name-class-transfer option:selected").val()
	var physicianId = $(".physician-name-class-1 option:selected").val()
	var description = document.getElementById("transfer-case-description-text").value
	var current_state = $(".state-type-class-name").attr("data-state")
	payload = {}
	payload["reqId"] = reqId
	payload["physicianId"] = physicianId
	payload["description"] = description

	if (regionId == 0) {
		showRegionError.innerHTML = "Please select a region"
	}
	else if (physicianId == 0) {

		showPhysicianError.innerHTML = "Please select a physician."
	} else {
		$(".transfer-pop-close").click()
		$.ajax({
			url: 'transferPhysician',
			type: 'POST',
			data: payload,
			success: function(data) {
				console.log("transferred physician sucessfully")
				loadCount()
				loadData(current_state)
			},
			error: function(data) {
				console.log("failed to transfer provider")
			}
		})

		$("#transfer-request-form").get(0).reset()
	}
})


function sendAgreement(reqId) {
	$("#send-agreement-req-id").val(reqId)

	$.ajax({
		url: 'getSendAgreementData',
		type: 'POST',
		data: {
			reqId: reqId
		},
		success: function(data) {
			console.log("data obtianed of email and phone")
			console.log(data)

			$(".agreement-phone").val(data.phoneNumber)
			$(".agreement-email").val(data.email)

		},
		error: function(data) {
			console.log("failed to obtian data of email and phone")
		}
	})
}

$("#sendAgreementForm").submit(function(event) {
	event.preventDefault();
	debugger
	var reqId = document.getElementById("send-agreement-req-id").value
	var phoneNumber = document.getElementById("send-agreement-phone-no").value
	var email = document.getElementById("send-agreement-email").value

	var payload = {}
	payload["reqId"] = reqId
	payload["phoneNumber"] = phoneNumber
	payload["email"] = email

	$.ajax({
		url: 'sendAgreementToPatient',
		type: 'POST',
		data: payload,
		success: function(data) {
			$(".send-agreement-reset").click()
			console.log("send agreement")
			console.log(data)
		},
		error: function(data) {
			console.log("failed to send agreement")
			var error = document.getElementById("error-send-agreement")
			error.innerHTML = "Error sending agreement! Please try again!"
		}
	})

})

