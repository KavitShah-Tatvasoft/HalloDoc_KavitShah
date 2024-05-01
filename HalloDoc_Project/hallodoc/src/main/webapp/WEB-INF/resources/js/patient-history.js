function clearFilter(){
	$(".record-first-name").val("")
	$(".record-last-name").val("")
	$(".record-email").val("")
	$(".record-phone").val("")
	
	filterPatientHistory()
	
}

function filterPatientHistory(){
	
	var firstName = $(".record-first-name").val()
	var lastName = $(".record-last-name").val()
	var email = $(".record-email").val()
	var phoneNumber = $(".record-phone").val()
	
	var payload = {}
	
	payload["firstName"] = firstName
	payload["lastName"] = lastName
	payload["email"] = email
	payload["phoneNumber"] = phoneNumber
	
	var tbody = $(".patient-history-empty-tbody")
	var accordionBody = $(".accordion-body-empty")
	$.ajax({
		url: 'getPatientHistoryData',
		type: 'POST',
		data: payload,
		success: function(res) {
			  
			  
			tbody.empty()
			accordionBody.empty()
			var count = 121
			res.forEach(function(data) {
				count++
				var card = $(".patient-history-tr").clone().removeClass("d-none").removeClass("patient-history-tr")
				card.find(".pt-fname-td").text(data.firstName)
				card.find(".pt-l-name-td").text(data.lastName)
				card.find(".pt-email-td").text(data.email)
				card.find(".pt-phone-td").text(data.phoneNumber)
				card.find(".pt-address-td").text(data.address)
				card.find(".pt-explore-td").attr("href","explore/"+data.userId)
				tbody.append(card)
				
				var accordionCard = $(".patient-history-accordion-clone").clone().removeClass("d-none").removeClass("patient-history-accordion-clone")
				accordionCard.find(".acc-name").text(data.firstName + " " + data.lastName)
				accordionCard.find(".acc-number").text(data.phoneNumber)
				accordionCard.find(".acc-email").text(data.email)
				accordionCard.find(".acc-phone").text(data.phoneNumber)
				accordionCard.find(".acc-change-target-class").attr("data-bs-target","#accordion"+count)
				accordionCard.find(".acc-change-id").attr("id","accordion"+count)
				accordionCard.find(".accordion-explore-case").attr("href","explore/"+data.userId)
				accordionBody.append(accordionCard)
			} )
		},
		error : function(res){
			  
		} })
}