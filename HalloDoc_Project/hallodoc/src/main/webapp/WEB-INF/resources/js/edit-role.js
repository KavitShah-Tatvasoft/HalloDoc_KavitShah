function getSelectedMenu(){
	
	var selectedMenu = []; // Clear any previous selections
        document.querySelectorAll('input[type="checkbox"]:checked').forEach(checkbox => {
            selectedMenu.push(parseInt(checkbox.value));
        });

    $(".role-menu-input").val(selectedMenu.join(',')) 
	
}