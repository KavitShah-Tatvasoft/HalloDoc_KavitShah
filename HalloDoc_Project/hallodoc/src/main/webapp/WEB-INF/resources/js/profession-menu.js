function showTableData(){
	debugger
	var name = $(".search-vendor-filter").val()
	var typeId = $(".select-physician-filter").val()
	
	var payload={}
	
	payload["name"] = name
	payload["typeId"] = typeId
		
	$.ajax({
		url: 'getProfessionMenuData',
		type: 'POST',
		data: payload,
		success: function(res) {
			console.log("data obtaines")
			console.log(res)
			var tbody = $(".profession-tbody")
			var accordionBody = $(".empty-accordion")
			tbody.empty()
			accordionBody.empty()
			debugger
			var count = 121;
			res.forEach(function(data) {
				count++
				var card = $(".profession-tr").clone().removeClass("d-none").removeClass("profession-tr")
				card.find(".profession-type").text(data.profession)
				card.find(".profession-name").text(data.businessName)
				card.find(".profession-email").text(data.email)
				card.find(".profession-fax").text(data.faxNumber)
				card.find(".profession-mobile").text(data.phoneNumber)
				card.find(".profession-contact").text(data.businessContact)
				card.find(".edit-button-table").attr("href","editExsistingBusiness/"+data.businessId)
				card.find(".delete-button-table").attr("onclick","deleteBusiness(" + data.businessId + ")")
//				"deleteExsistingBusiness/"+data.businessId
				card.append(accordionCard)
				
				tbody.append(card)
				
				var accordionCard = $(".clone-business-accordion").clone().removeClass("d-none").removeClass("clone-business-accordion")
				accordionCard.find(".change-target-class").attr("data-bs-target","#id"+count)
				accordionCard.find(".change-id-class").attr("id","id"+count)
				accordionCard.find(".accordion-clone-name").text(data.businessName)
				accordionCard.find(".accordion-clone-email").text(data.email)
				accordionCard.find(".accordion-clone-fax").text(data.faxNumber)
				accordionCard.find(".accordion-clone-phone").text(data.phoneNumber)
				accordionCard.find(".accordion-clone-business-contact").text(data.businessContact)
				accordionBody.find(".edit-business-id").attr("href","editExsistingBusiness/"+data.businessId)
				accordionBody.find(".delete-business-id").attr("onclick","deleteBusiness(" + data.businessId + ")")
				
				accordionBody.append(accordionCard)
			})
			

		}, error: function(data) {
			console.log("failed")
		}


	})
	
}

function deleteBusiness(businessId){
	
	$.ajax({
		url: 'deleteExsistingBusiness',
		type: 'POST',
		data: {
			businessId : businessId
		},
		success : function(data){
			console.log("deleted business")
			showTableData()
		},
		error : function(data){
			console.log("unable to delete")
		}
		})
	
}