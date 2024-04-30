function loadUserAccessData(){
	
	debugger
	var tbody = $(".tbody-empty-td")
	var typeId = $(".select-type-id").val()
	$.ajax({
		url: 'get-user-access-data',
		type: 'POST',
		data:{
			typeId : typeId
		},
		success: function(res) {
		console.log(res)
		console.log("data obtained")
		
		tbody.empty()
		
		res.forEach(function(data){
			
			var cloneTr = $(".user-access-td-clone").clone().removeClass("d-none").removeClass("user-access-td-clone")
			cloneTr.find(".type-td").text(data.accountType)
			cloneTr.find(".name-td").text(data.name)
			cloneTr.find(".number-td").text(data.phoneNumber)
			cloneTr.find(".status-td").text(data.status)
			cloneTr.find(".request-td").text(data.openRequests)
			tbody.append(cloneTr)
		})
		
		},
		error: function(res){
		console.log("failed to obtain data")
		}
		
		})
	
}