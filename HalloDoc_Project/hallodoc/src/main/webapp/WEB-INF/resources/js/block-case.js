function clearFilters() {
	$(".filter-name").val("")
	$(".filter-date").val("")
	$(".filter-email").val("")
	$(".filter-phone").val("")

	getFilteredBlockData()
}

function unblockCase(blockId) {

	$.ajax({
		url: 'unblockCase',
		type: 'POST',
		data: {

			blockId: blockId

		},
		success: function(res) {
			  
			getFilteredBlockData()
		},
		error: function(res) {
			  
		}
	})
}

function getFilteredBlockData() {

	var name = $(".filter-name").val()
	var date = $(".filter-date").val()
	var email = $(".filter-email").val()
	var phone = $(".filter-phone").val()

	var payload = {}
	payload["name"] = name
	payload["date"] = date
	payload["email"] = email
	payload["phone"] = phone

	var accordionBody = $(".empty-accordion-class")
	var tbody = $(".empty-tbody-block")

	$.ajax({
		url: 'getBlockHistoryData',
		type: 'POST',
		data: payload,
		success: function(res) {
			  
			  

			tbody.empty()
			accordionBody.empty()
			var count = 121
			res.forEach(function(data) {
				count++
				var card = $(".clone-block-tr").clone().removeClass("d-none").removeClass("clone-block-tr")
				card.find(".bname-td").text(data.patientName)
				card.find(".bnumber-td").text(data.phoneNumber)
				card.find(".bemail-td").text(data.email)
				card.find(".bdate-td").text(data.createdDate)
				card.find(".bnotes-td").text(data.notes)
				card.find(".bunblock-td").attr("onclick", "unblockCase(" + data.requestId + ")")
				if(data.isActive == true){
					card.find(".bactive-td").prop('checked', true);
					card.find(".bunblock-td").hide()
				}
				tbody.append(card)
				
				var accordionCard = $(".clone-accordion-class").clone().removeClass("d-none").removeClass("clone-accordion-class")
				accordionCard.find(".acc-name").text(data.patientName)
				accordionCard.find(".acc-email").text(data.email)
				accordionCard.find(".acc-phone").text(data.phoneNumber)
				accordionCard.find(".acc-create-date").text(data.createdDate)
				accordionCard.find(".acc-notes").text(data.notes)
				accordionCard.find(".change-target-class").attr("data-bs-target","#accordion"+count)
				accordionCard.find(".change-id-class").attr("id","accordion"+count)
				if(data.isActive == true){
				accordionCard.find(".acc-active").text("Yes")
				accordionCard.find(".acc-unblock").hide()					
				}else{
				accordionCard.find(".acc-active").text("No")
				accordionCard.find(".acc-unblock").attr("onclick", "unblockCase(" + data.requestId + ")")
				}
				
				accordionBody.append(accordionCard)

			})
		},
		error: function(res) {

			  

		}



	})

}