const changeStatus = (element) => {
	debugger
	const stateName = document.getElementById("type-text")
	const states = document.getElementsByClassName("row-cards")
	const current_state = element.classList[1]
	const capitalizedState = current_state.charAt(0).toUpperCase() + current_state.slice(1);

	stateName.innerHTML = "(" + capitalizedState + ")"
	$(".state-type-class-name").attr("data-state", current_state)

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

	loadData(current_state)
	loadCount()
}


function changeActiveBtn(element) {

	$(".button-class").removeClass("active-btn")
	$(".commom-label-class").removeClass("show-active-button-class")
	$(element).addClass("active-btn")
	$(element).next().addClass("show-active-button-class")
}

function acceptCaseChangeId(id) {
	$("#reqId-accept-case").val(id)
}

function transferCaseChangeId(id) {
	$("#reqId-transfer-case").val(id)
}

function filterRequest() {
	debugger
	var patientName = document.getElementById("patient-name-search").value
	var stateName = document.getElementById("region-name-search").value
	var requestType = $(".button-class.active-btn").attr("data-value")
	var statusType = $("#type-text").html();

	

	statusType = statusType.slice(1, -1).toLowerCase();
	var current_state = statusType
	
	if (current_state == "to close") { current_state = "to-close" }

	if (current_state == "active") {
		$(".status-class-tr").removeClass("d-none")
	} else {
		$(".status-class-tr").addClass("d-none")
	}

	let tbody = $(".tbody-empty")

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



	$.ajax({
		url: 'get-physician-request-filtered-data',
		type: 'POST',
		data: payLoadData,
		success: function(res) {

			tbody.empty()
			var count = 120;
			res.forEach(function(data) {

				count = count + 1

				var card = $(".phy-dash-tr-clone").clone().removeClass("phy-dash-tr-clone").removeClass("d-none")

				if (current_state != "active") {
					card.find(".show-call-type").addClass("d-none")
				}
				if (current_state == "active" && data.callType != 1) {
					card.find(".show-call-type").addClass("d-none")
				} else {
					card.find(".show-call-type").attr("onclick", "concludeCase('" + data.reqId + "')")
				}

				card.find(".name-tr").text(data.patientFirstName + " " + data.patientLastName)
				card.find(".number-tr").text(data.ptPhoneNumber)
				card.find(".number-req-tr").text(data.reqPhoneNumber)
				card.find(".accept-case-button-class").attr("onclick", "acceptCaseChangeId('" + data.reqId + "')")
				card.find(".transfer-case-button-class").attr("onclick", "transferCaseChangeId('" + data.reqId + "')");
				card.find(".view-case-set-link").attr("href", "viewCase/" + data.reqId)
				card.find(".view-notes-send-link").attr("href", "viewNotes/" + data.reqId)
				card.find(".send-agreement-btn").attr("onclick", "sendAgreement('" + data.reqId + "')")
				card.find(".view-uploads-btn").attr("href", "../user/viewRequestUploads/" + data.reqId)
				card.find(".send-order-details").attr("href", "../user/sendOrderDetails/" + data.reqId)
				card.find(".conclude-care-btn").attr("href", "conclude-care/" + data.reqId);

				if (data.finalized == true) {
					card.find(".encounter-form-btn").attr("type", "button")
					card.find(".encounter-form-btn").attr("data-bs-toggle", "modal")
					card.find(".encounter-form-btn").attr("data-bs-target", "#finalized-encounter-form")
				} else if (data.callType != 0) {
					card.find(".encounter-form-btn").attr("href", "../user/encounterForm/" + data.reqId)
				} else {
					card.find(".encounter-form-btn").attr("type", "button")
					card.find(".encounter-form-btn").attr("data-bs-toggle", "modal")
					card.find(".encounter-form-btn").attr("data-bs-target", "#house-call")
					card.find(".encounter-form-btn").attr("onclick", "encounterChangeId('" + data.reqId + "')");
				}

				if (data.reqPhoneType == "Business") {
					card.find(".color-class").removeClass("tr-clr").addClass("tr-business")
				}
				if (data.reqPhoneType == "Family") {
					card.find(".color-class").removeClass("tr-clr").addClass("tr-family")
				}
				if (data.reqPhoneType == "Concierge") {
					card.find(".color-class").removeClass("tr-clr").addClass("tr-concierge")
				}

				card.find(".req-type").text("(" + data.reqPhoneType + ")")
				card.find(".address-tr").text(data.ptStreet + ", " + data.ptCity + ", " + data.ptState + ", " + data.ptZipcode)
				tbody.append(card)


			})

			const currentActions = document.getElementsByClassName(current_state + "s")
			for (let i = 0; i < currentActions.length; i++) {
				currentActions[i].classList.remove('d-none')
			}


		},
		error: function(res) {

		}
	});

}


function loadData(current_state) {
	debugger
	if (current_state == "active") {
		$(".status-class-tr").removeClass("d-none")
	} else {
		$(".status-class-tr").addClass("d-none")
	}

	let tbody = $(".tbody-empty")

	$.ajax({
		url: 'get-request-by-physician',
		type: 'POST',
		dataType: 'json',
		data: {
			status: current_state
		},

		success: function(res) {

			console.log(res)
			console.log("data obtained")

			tbody.empty()
			//			accordionBody.empty()
			var count = 120;
			res.forEach(function(data) {

				count = count + 1

				var card = $(".phy-dash-tr-clone").clone().removeClass("phy-dash-tr-clone").removeClass("d-none")

				if (current_state != "active") {
					card.find(".show-call-type").addClass("d-none")
				}
				if (current_state == "active" && data.callType != 1) {
					card.find(".show-call-type").addClass("d-none")
				} else {
					card.find(".show-call-type").attr("onclick", "concludeCase('" + data.reqId + "')")
				}

				card.find(".name-tr").text(data.patientFirstName + " " + data.patientLastName)
				card.find(".number-tr").text(data.ptPhoneNumber)
				card.find(".number-req-tr").text(data.reqPhoneNumber)
				card.find(".accept-case-button-class").attr("onclick", "acceptCaseChangeId('" + data.reqId + "')")
				card.find(".transfer-case-button-class").attr("onclick", "transferCaseChangeId('" + data.reqId + "')");
				card.find(".view-case-set-link").attr("href", "viewCase/" + data.reqId)
				card.find(".view-notes-send-link").attr("href", "viewNotes/" + data.reqId)
				card.find(".send-agreement-btn").attr("onclick", "sendAgreement('" + data.reqId + "')")
				card.find(".view-uploads-btn").attr("href", "../user/viewRequestUploads/" + data.reqId)
				card.find(".send-order-details").attr("href", "../user/sendOrderDetails/" + data.reqId)
				card.find(".conclude-care-btn").attr("href", "conclude-care/" + data.reqId);

				if (data.finalized == true) {
					card.find(".encounter-form-btn").attr("type", "button")
					card.find(".encounter-form-btn").attr("data-bs-toggle", "modal")
					card.find(".encounter-form-btn").attr("data-bs-target", "#finalized-encounter-form")
				} else if (data.callType != 0) {
					card.find(".encounter-form-btn").attr("href", "../user/encounterForm/" + data.reqId)
				} else {
					card.find(".encounter-form-btn").attr("type", "button")
					card.find(".encounter-form-btn").attr("data-bs-toggle", "modal")
					card.find(".encounter-form-btn").attr("data-bs-target", "#house-call")
					card.find(".encounter-form-btn").attr("onclick", "encounterChangeId('" + data.reqId + "')");
				}

				if (data.reqPhoneType == "Business") {
					card.find(".color-class").removeClass("tr-clr").addClass("tr-business")
				}
				if (data.reqPhoneType == "Family") {
					card.find(".color-class").removeClass("tr-clr").addClass("tr-family")
				}
				if (data.reqPhoneType == "Concierge") {
					card.find(".color-class").removeClass("tr-clr").addClass("tr-concierge")
				}

				card.find(".req-type").text("(" + data.reqPhoneType + ")")
				card.find(".address-tr").text(data.ptStreet + ", " + data.ptCity + ", " + data.ptState + ", " + data.ptZipcode)
				tbody.append(card)
			})


			$(".common-action-class").addClass("d-none")
			const currentActions = document.getElementsByClassName(current_state + "s")
			for (let i = 0; i < currentActions.length; i++) {
				currentActions[i].classList.remove('d-none')
			}

		},
		error: function() {
			console.log("failed to obtain data")
		}

	})
}

function concludeCase(reqId) {

	var current_state = $(".state-type-class-name").attr("data-state")

	$.ajax({
		url: 'conclude-requested-case',
		type: 'POST',
		data: {

			reqId: reqId

		},
		success: function(data) {

			console.log("concluded case")
			loadData(current_state)
			loadCount()
			hideLoader()
		},
		error: function(data) {
			console.log("failed to conclude case")
			hideLoader()
		}


	});


}

function encounterChangeId(reqId) {
	$(".call-type-req-id").val(reqId)
}

function loadCount() {

	$.ajax({
		url: 'get-status-wise-physician-request-count',
		type: 'POST',
		dataType: 'json',
		success: function(res) {
			var countNew = document.getElementById("new-request-count");
			var countPending = document.getElementById("pending-request-count");
			var countActive = document.getElementById("active-request-count");
			var countConclude = document.getElementById("conclude-request-count");



			countNew.innerHTML = res[0];
			countPending.innerHTML = res[1];
			countActive.innerHTML = res[2];
			countConclude.innerHTML = res[3];


		},
		error: function() {

		}
	})

}



$("#sendLinkForm").submit(function(event) {

	event.preventDefault();
	var serializedForm1 = $(this).serializeArray();

	var payloadData = {}
	$(serializedForm1).each(function(i, value) {
		payloadData[value.name] = value.value
	})

	showLoader()
	$.ajax({
		url: '../admin/sendLinkByEmail',
		type: 'POST',
		data: payloadData,
		success: function(data) {


			hideLoader()
		},
		error: function(data) {
			hideLoader()


		}


	});

	$("#sendLinkForm").get(0).reset()

});



$("#transferCaseForm").submit(function(event) {
	debugger
	event.preventDefault();
	var reqId = $("#reqId-transfer-case").val()
	var description = $(".transfer-reason").val()
	var current_state = $(".state-type-class-name").attr("data-state")
	var payload = {}

	payload["description"] = description
	payload["reqId"] = reqId

	$.ajax({
		url: 'transfer-case-admin',
		type: 'POST',
		data: payload,
		success: function(data) {

			console.log(data)
			loadData(current_state)
			loadCount()
			hideLoader()
		},
		error: function(data) {
			console.log("failed to transfer")
			hideLoader()


		}


	});

})


$("#acceptCaseForm").submit(function(event) {

	event.preventDefault();
	var reqId = $("#reqId-accept-case").val()
	$.ajax({
		url: 'accept-case',
		type: 'POST',
		data: {

			reqId: reqId

		},
		success: function(data) {

			console.log(data)
			loadData("new")
			loadCount()
			hideLoader()
		},
		error: function(data) {
			console.log("failed to accept")
			hideLoader()


		}


	});

})

function sendAgreement(reqId) {
	debugger
	$("#send-agreement-req-id").val(reqId)

	$.ajax({
		url: 'getSendAgreementData',
		type: 'POST',
		data: {
			reqId: reqId
		},
		success: function(data) {


			$("#send-agreement-req-id").val(reqId)
			$(".agreement-phone").val(data.phoneNumber)
			$(".agreement-email").val(data.email)

		},
		error: function(data) {

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
	showLoader()
	$.ajax({
		url: 'sendAgreementToPatient',
		type: 'POST',
		data: payload,
		success: function(data) {
			$(".close-btn-class").click()
			hideLoader()


		},
		error: function(data) {
			hideLoader()

			var error = document.getElementById("error-send-agreement")
			error.innerHTML = "Error sending agreement! Please try again!"
		}
	})

})

function changeCss(element) {
	console.log(element.id)
	var x = document.getElementById("Housecall");
	var y = document.getElementById("Consult");

	if (element.id == "Housecall") {
		x.style.backgroundColor = "#01bce9";
		x.style.color = "white";

		y.style.color = "#01bce9";
		y.style.backgroundColor = "white";

		$(".call-type-val-id").val("1")
	}

	else {
		y.style.backgroundColor = "#01bce9";
		y.style.color = "white";

		x.style.color = "#01bce9";
		x.style.backgroundColor = "white";

		$(".call-type-val-id").val("2")

	}
}

$("#callTypeFormId").submit(function(event) {
	debugger
	event.preventDefault();
	var reqId = $(".call-type-req-id").val()
	var callType = $(".call-type-val-id").val()
	var current_state = $(".state-type-class-name").attr("data-state")

	var payload = {}
	payload["reqId"] = reqId
	payload["callType"] = callType

	$.ajax({
		url: 'set-call-type',
		type: 'POST',
		data: payload,
		success: function(data) {

			console.log("call type set")
			$(".close-call-type-btn").click()
			loadData(current_state)
			loadCount()
			hideLoader()
		},
		error: function(data) {
			console.log("failed to set call type")
			hideLoader()
		}


	});


})
