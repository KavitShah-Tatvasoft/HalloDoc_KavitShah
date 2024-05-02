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
}


function changeActiveBtn(element) {

	$(".button-class").removeClass("active-btn")
	$(".commom-label-class").removeClass("show-active-button-class")
	$(element).addClass("active-btn")
	$(element).next().addClass("show-active-button-class")
}


function loadData(current_state) {
	debugger
	if(current_state == "active"){
		$(".status-class-tr").removeClass("d-none")
	}else{
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
				card.find(".name-tr").text(data.patientFirstName +" "+ data.patientLastName)
				card.find(".number-tr").text(data.ptPhoneNumber)
				card.find(".number-req-tr").text(data.reqPhoneNumber)
				

				if(data.reqPhoneType == "Business"){
				card.find(".color-class").removeClass("tr-clr").addClass("tr-business")	
				}
				if(data.reqPhoneType == "Family"){
				card.find(".color-class").removeClass("tr-clr").addClass("tr-family")	
				}
				if(data.reqPhoneType == "Concierge"){
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


