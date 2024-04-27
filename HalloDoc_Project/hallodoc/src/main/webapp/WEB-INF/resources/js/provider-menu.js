function showProviderMenuData(){
	
	debugger
	var region = $(".select-physician-region-dropdown").val()
	
		$.ajax({
		url: 'getFilteredProviderMenuData',
		type: 'POST',
		data: {
			region : region
		},
		success: function(res) {
			console.log("Data obtained")
			console.log(res)
//			var tbody = $(".")
//			var accordionBody = $(".")
			
		},
		error: function(res) {
			console.log("Failed to obtain data")
		}

	});
	
}