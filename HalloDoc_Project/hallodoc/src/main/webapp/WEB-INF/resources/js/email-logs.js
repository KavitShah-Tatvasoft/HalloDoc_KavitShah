function clearFilters(){
	$(".filter-by-role").prop('selectedIndex', 0);
	$(".filter-by-name").val("")
	$(".filter-by-email").val("")
	$(".filter-by-create-date").val("")
	$(".filter-by-sent-date").val("")
	
	filterEmailLog()
}

function filterEmailLog(){
	
	debugger
	var roleId = $(".filter-by-role").val()
	var reciever = $(".filter-by-name").val()
	var email = $(".filter-by-email").val()
	var createdDate = $(".filter-by-create-date").val()
	var sentDate = $(".filter-by-sent-date").val()
	
	var payload = {}
	payload["roleId"] = roleId
	payload["reciever"] = reciever
	payload["email"] = email
	payload["createdDate"] = createdDate
	payload["sentDate"] = sentDate
	
	tbody = $(".email-log-tbody")
	accordionBody = $(".enpty-accordion-class") 
	
	$.ajax({
		url: 'getFilteredEmailLogData',
		type: 'POST',
		data: payload,
		success: function(res) {
			  
			  
			tbody.empty()
			accordionBody.empty()
			var count = 120
			res.forEach(function(data) {
				count++
				var card = $(".email-log-tr-clone").clone().removeClass("d-none").removeClass("email-log-tr-clone")
				card.find(".name-td").text(data.recipientName)
				card.find(".action-td").text(data.action)
				card.find(".role-td").text(data.roleName)
				card.find(".email-td").text(data.emailId)
				card.find(".created-date-td").text(data.createdDate)
				card.find(".send-date-td").text(data.sendDate)
				card.find(".mail-sent-td").text(data.isEmailSent)
				card.find(".send-tries-td").text(data.sentTries)
				card.find(".conf-td").text(data.confNumber)
				tbody.append(card)
				
				var accordionCard = $(".accordion-clone-class").clone().removeClass("d-none").removeClass("accordion-clone-class")
				accordionCard.find(".acc-name").text(data.recipientName)
				accordionCard.find(".acc-action").text(data.action)
				accordionCard.find(".acc-role").text(data.roleName)
				accordionCard.find(".acc-email").text(data.emailId)
				accordionCard.find(".acc-created").text(data.createdDate)
				accordionCard.find(".acc-sent").text(data.sendDate)
				accordionCard.find(".acc-target").attr("data-bs-target","#accordion"+count)
				accordionCard.find(".acc-id").attr("id","accordion"+count)
				accordionCard.find(".acc-sent-tries").text(data.sentTries)
				if(data.isEmailSent == true){
					accordionCard.find(".acc-sent-bool").text("True")
				}else{
					accordionCard.find(".acc-sent-bool").text("False")
				}
				accordionCard.find(".acc-conf").text(data.confNumber)
				accordionBody.append(accordionCard)
			})
			
		},
		error: function(data) {
			  
		}
	})	
	
}