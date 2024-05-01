function getSelectedMenu(){
	
	var selectedMenu = []; // Clear any previous selections
        document.querySelectorAll('input[type="checkbox"]:checked').forEach(checkbox => {
            selectedMenu.push(parseInt(checkbox.value));
        });

    $(".selected-menu-class-list").val(selectedMenu.join(',')) 
	
}


function getMenus(){
	
	var role = $(".role-type-class").val()
	var menuFlex = $(".empty-flex-class-menus")
	$.ajax({
		url: 'getMenus',
		type: 'POST',
		data: {

			role: role

		},
		success: function(res) {
			  
			  
			menuFlex.empty()
			
			res.forEach(function(data){
				
				var checkDiv = $(".clone-menu-class").clone().removeClass("d-none").removeClass("clone-menu-class")
				checkDiv.find(".name-menu-class").text(data.name)
				checkDiv.find(".menu-checkbox-class").attr("value",data.menuId)
				menuFlex.append(checkDiv)
			})
		},
		error: function(res) {
			  
		}

	})
	
}