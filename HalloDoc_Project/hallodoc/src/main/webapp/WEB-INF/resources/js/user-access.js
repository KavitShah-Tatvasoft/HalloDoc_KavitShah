function loadUserAccessData(){
	
	debugger
	var tbody = $(".tbody-empty-td")
	var accordionBody = $(".accordion-body-empty")
	var typeId = $(".select-type-id").val()
	$.ajax({
		url: 'get-user-access-data',
		type: 'POST',
		data:{
			typeId : typeId
		},
		success: function(res) {
		  
		  
		
		tbody.empty()
		accordionBody.empty()
		var count = 120
		res.forEach(function(data){
			count++
			var cloneTr = $(".user-access-td-clone").clone().removeClass("d-none").removeClass("user-access-td-clone")
			cloneTr.find(".type-td").text(data.accountType)
			cloneTr.find(".name-td").text(data.name)
			cloneTr.find(".number-td").text(data.phoneNumber)
			cloneTr.find(".status-td").text(data.status)
			cloneTr.find(".request-td").text(data.openRequests)
			if(data.accountType == "Provider"){
				
			cloneTr.find(".edit-link-td").attr("href","updatePhysicianAccount/"+data.userId)
			}else{
				cloneTr.find(".edit-link-td").addClass("d-none")
			}
			tbody.append(cloneTr)
			
			var accordionCard = $(".accordion-clone-class").clone().removeClass("d-none").removeClass("accordion-clone-class")
			accordionCard.find(".type-td").text(data.accountType)
			accordionCard.find(".name-td").text(data.name)
			accordionCard.find(".number-td").text(data.phoneNumber)
			accordionCard.find(".status-td").text(data.status)
			accordionCard.find(".request-td").text(data.openRequests)
			accordionCard.find(".change-data-target").attr("data-bs-target","#accordion"+count)
			accordionCard.find(".change-id-target").attr("id","accordion"+count)
			
			if(data.accountType == "Provider"){
				
			accordionCard.find(".edit-link-td").attr("href","updatePhysicianAccount/"+data.userId)
			}else{
				accordionCard.find(".edit-link-td").addClass("d-none")
			}
			
			
			accordionBody.append(accordionCard)
			
		})
		
		},
		error: function(res){
		  
		}
		
		})
	
}