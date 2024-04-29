function clearFilter(){
	$(".filter-by-role").prop('selectedIndex', 0);
	$(".filter-by-name").val("")
	$(".filter-by-email").val("")
	$(".filter-by-create-date").val("")
	$(".filter-by-sent-date").val("")
	
	filterSMSRecords()
}

function filterSMSRecords(){
	
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
	
	tbody = $(".sms-log-tbody")
	accordionBody = $(".empty-sms-accordion") 
	
	$.ajax({
		url: 'getFilteredSMSLogData',
		type: 'POST',
		data: payload,
		success: function(res) {
			console.log(res)
			console.log("Email Log data obtained")
			tbody.empty()
			accordionBody.empty()
			var count = 121;
			res.forEach(function(data) {
				count++;
				var card = $(".sms-log-tr-clone").clone().removeClass("d-none").removeClass("sms-log-tr-clone")
				card.find(".name-td").text(data.recipientName)
				card.find(".action-td").text(data.action)
				card.find(".role-td").text(data.roleName)
				card.find(".phone-td").text(data.mobileNumber)
				card.find(".created-date-td").text(data.createdDate)
				card.find(".send-date-td").text(data.sendDate)
				card.find(".sent-sms-td").text(data.isEmailSent)
				card.find(".send-tries-td").text(data.sentTries)
				card.find(".conf-td").text(data.confNumber)
				tbody.append(card)
				
				var accordionCard = $(".accordion-clone-class").clone().removeClass("d-none").removeClass("accordion-clone-class")
				accordionCard.find(".change-id").attr("id","accordion"+count)
				accordionCard.find(".change-target").attr("data-bs-target","#accordion"+count)
				accordionCard.find(".acc-name").text(data.recipientName)
				accordionCard.find(".acc-action").text(data.action)
				accordionCard.find(".acc-role").text(data.roleName)
				accordionCard.find(".acc-mobile").text(data.mobileNumber)
				accordionCard.find(".acc-created").text(data.createdDate)
				accordionCard.find(".acc-sent").text(data.sendDate)
				accordionCard.find(".acc-sent-tries").text(data.sentTries)
				accordionCard.find(".acc-sent-bool").text(data.isEmailSent)
				accordionCard.find(".acc-conf").text(data.confNumber)
				accordionBody.append(accordionCard)
			})
			
		},
		error: function(data) {
			console.log("Email Log data not obtained")
		}
	})	
	
}