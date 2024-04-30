function deleteRole(roleId){
	
	$.ajax({
		url: 'deleteRole',
		type: 'POST',
		data: {
			roleId : roleId
		},
		success: function(res) {
		console.log(res)
		getRoles()
			
		},
		error: function(res){
		console.log("failed to delete")
		}
		
		})
	
}

function getRoles() {
	
	var tbody = $(".empty-tbody-class")
	var accordionBody = $(".accordion-body-empty")
	$.ajax({
		url: 'getRoleDetails',
		type: 'GET',
		success: function(res) {
			console.log(res)
			console.log("roles obtained")
			tbody.empty()
			accordionBody.empty()
			
			res.forEach(function(data) {
	
			var tRow = $(".clone-tr-class").clone().removeClass("d-none").removeClass("clone-tr-class")
			
			tRow.find(".role-td").text(data.roleName)
			
			if(data.accountType == 1){
			tRow.find(".role-type-td").text("Admin")				
			}else{
				tRow.find(".role-type-td").text("Provider")
			}
			
			tRow.find(".edit-btn-td").attr("href","editRoleAccess/"+data.roleId)
			tRow.find(".delete-btn-td").attr("onclick","deleteRole("+ data.roleId +")")
			
			tbody.append(tRow)	
			
			
			var accordionCard = $(".clone-accordion-card").clone().removeClass("d-none").removeClass("clone-accordion-card")
			
			accordionCard.find(".role-td").text(data.roleName)
			
			if(data.accountType == 1){
			accordionCard.find(".role-type-td").text("Admin")				
			}else{
				accordionCard.find(".role-type-td").text("Provider")
			}
			
			accordionBody.append(accordionCard)	
			
			})
		},
		error: function(res) {
			console.log("failed to obtain roles data")
		}

	})


}