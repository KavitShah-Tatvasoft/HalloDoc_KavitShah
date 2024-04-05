function switchBlack() {
	alert("switched to black theme")
}

function switchWhite() {
	alert("switched to white theme")
}

const changeStatus = (element) => {
	const states = document.getElementsByClassName("row-cards")
	const current_state = element.classList[1]
	console.log(current_state)
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

	let functions = {
		"new": "createNewReqRow",
		"pending": "createPendingReqRow",
		"active": "createActiveReqRow",
		"conclude": "createConcludeReqRow",
		"to-close": "createToCloseReqRow",
		"unpaid": "createUnpaidReqRow"
	}
	console.log(functions[current_state])


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
			tbody.empty()
			res.forEach(function(data) {


				debugger
				if (current_state == "new") {
					var card = createNewReqRow(data);
					tbody.append(card)
				}

				if (current_state == "pending") {
					var card = createPendingReqRow(data);
				}
				if (current_state == "active") {
					var card = createPendingReqRow(data);
				}
				if (current_state == "conclude") {
					var card = createPendingReqRow(data);
				}
				if (current_state == "to-close") {
					var card = createPendingReqRow(data);
				}
				if (current_state == "unpaid") {
					var card = createPendingReqRow(data);
				}

			})

		},
		error: function() {
			console.log("error fetching request data")
		}

	})

}

function createNewReqRow(data) {
	var card = `<tr class="tr-clr">`

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
						<td>`+ data.requestedDate + `</td>
						<td>`+ data.ptPhoneNumber + `</td>
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
												class="action-dropdown-text" role="button"
												data-bs-toggle="modal" data-bs-target="#assign-case">Assign
												Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="/hallodoc/resources/images/x-circle-grey.svg"
												class="dropdown-icons" alt=""> <a
												class=" action-dropdown-text" type="button"
												data-bs-toggle="modal" data-bs-target="#cancel-case">Cancel
												Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item ">
											<img
												src="/hallodoc/resources/images/x-circle-grey.svg"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#clear-case" type="button">Clear Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="/hallodoc/resources/images/view-case.png"
												class="dropdown-icons" alt=""> <a
												href="view-case.html" class=" action-dropdown-text"
												type="button">View Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="/hallodoc/resources/images/x-circle-grey.svg"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" role="button"
												href="close-case.html">Close Case</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="/hallodoc/resources/images/journal-text.svg"
												class="dropdown-icons" alt=""> <a
												href="view-notes.html" class="action-dropdown-text"
												type="button">View Notes</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="/hallodoc/resources/images/ban-grey.svg"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#block-case" role="button">Block Patient</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="/hallodoc/resources/images/view-upload.png"
												class="dropdown-icons" alt=""> <a
												href="<c:url value='' />" class="action-dropdown-text "
												type="button">View Uploads</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="/hallodoc/resources/images/journal-check-grey.svg"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#transfer-case" type="button">Transfer</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="/hallodoc/resources/images/document.png"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" data-bs-toggle="modal"
												data-bs-target="#send-agreement" type="button">Send
												Agreement</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img
												src="/hallodoc/resources/images/order-delivery.png"
												class="dropdown-icons" alt=""> <a
												href="<c:url value='' />" class="action-dropdown-text"
												type="button">Orders</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="/hallodoc/resources/images/document.png"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" type="button"
												href="<c:url value='' />">Doctor Notes</a>
										</div>
									</li>

									<li>
										<div class="action-dropdown-flex dropdown-item">
											<img src="/hallodoc/resources/images/document.png"
												class="dropdown-icons" alt=""> <a
												class="action-dropdown-text" type="button"
												href="<c:url value='' />">Encounter</a>
										</div>
									</li>

								</ul>
							</div>
						</td>
					<tr>`

	return card + card1;
}



let open = document.querySelectorAll(".show_cards");
console.log(open);
