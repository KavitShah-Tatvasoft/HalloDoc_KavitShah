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

function transferCaseChangeId(id){
	$("#reqId-transfer-case").val(id)
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
				card.find(".name-tr").text(data.patientFirstName + " " + data.patientLastName)
				card.find(".number-tr").text(data.ptPhoneNumber)
				card.find(".number-req-tr").text(data.reqPhoneNumber)
				card.find(".accept-case-button-class").attr("onclick", "acceptCaseChangeId('" + data.reqId + "')")
				card.find(".transfer-case-button-class").attr("onclick","transferCaseChangeId('" + data.reqId + "')");
				card.find(".view-case-set-link").attr("href","viewCase/" + data.reqId)
				card.find(".view-notes-send-link").attr("href","viewNotes/" + data.reqId)
				card.find(".send-agreement-btn").attr("onclick","sendAgreement('" + data.reqId + "')")
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
			  
			  

			$(".agreement-phone").val(data.phoneNumber)
			$(".agreement-email").val(data.email)

		},
		error: function(data) {
			  
		}
	})
}

