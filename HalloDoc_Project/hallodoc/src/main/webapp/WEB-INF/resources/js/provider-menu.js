function showProviderMenuData(){
	
	debugger
	var region = $(".select-physician-region-dropdown").val()
	
		$.ajax({
		url: 'getFilteredProviderMenuData',
		type: 'POST',
		data: {
			region : region
		},
		success: function(res) {
			console.log("Data obtained")
			console.log(res)
			var tbody = $(".empty-tbody-provider-menu")
			var accordionBody = $(".empty-accordion")
			tbody.empty()
			accordionBody.empty()
			var count = 120
			res.forEach(function(data) {
				count+=1
				var card = $(".tr-clone").clone().removeClass("d-none").removeClass("tr-clone")
				if(data.stopNotification == true){
					card.find(".noti-checkbox").prop('checked', true)
				}
				card.find(".noti-checkbox").attr("onclick", "changeNotification("+ data.physicianId +")")
				card.find(".contact-btn-menu").attr("onclick","changeId("+ data.physicianId +")")
				card.find(".name-td").text(data.providerName)
				card.find(".role-td").text(data.role)
				card.find(".on-call-status-td").text(data.onCallStatus)
				card.find(".active-td").text(data.status)
				card.find(".edit-btn-menu").attr("href","updatePhysicianAccount/"+data.physicianId)
				tbody.append(card)
				
				var accordionCard = $(".clone-accordion").clone().removeClass("d-none").removeClass("clone-accordion")
				if(data.stopNotification == true){
					accordionCard.find(".accordion-checkbox-menu").prop('checked', true)
				}
				
				accordionCard.find(".accordion-main-id").attr("id",data.status+count)
				accordionCard.find(".accordion-target").attr("data-bs-target","#"+data.status+count)
				accordionCard.find(".contact-btn-accordion").attr("onclick","changeId("+ data.physicianId +")")
				accordionCard.find(".accordion-row-name-menu").text(data.providerName)
				accordionCard.find(".role-accordion-menu").text(data.role)
				accordionCard.find(".status-text-menu").text(data.onCallStatus)
				accordionCard.find(".status-accordion-menu").text(data.status)
				accordionCard.find(".edit-link-accordion").attr("href","updatePhysicianAccount/"+data.physicianId)
				accordionBody.append(accordionCard)
			})
			
		},
		error: function(res) {
			console.log("Failed to obtain data")
		}

	});
	
}

function changeId(id){
	$(".hiddden-phy-id").text(id)
	$(".hiddden-phy-id").attr("value",id)
}


function changeNotification(id){
	
	$.ajax({
		url: 'toggleNotification',
		type: 'POST',
		data: {
			
			id : id
			
		},
		success: function(data) {
			console.log(data)
		},
		error: function(data) {
			console.log("failed to toggle")
		}
	})
	
}

$("#communication-method-form").submit(function(event) {

	debugger
	event.preventDefault();
	
	var id = $(".hiddden-phy-id").val()
	var method = $('input[name="method-communication"]:checked').val()
	var message = $(".communication-description").val()
	
	var payload = {}
	payload["id"] = id
	payload["method"] = method
	payload["message"] = message
	
	$.ajax({
		url: 'contactProvider',
		type: 'POST',
		data: payload,
		success: function(data) {
			console.log(data)
			$(".close-pop-up").click()
		},
		error: function(data) {
			console.log("failed to contact")
		}
	})
	
	})