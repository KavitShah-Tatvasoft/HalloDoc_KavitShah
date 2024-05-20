function getOnCallStatus(){
	
	var regionId = $(".select-scheduling").val()
	showLoader()
	$.ajax({
		url: 'get-provider-on-call-data',
		type: 'POST',
		data: {

			regionId: regionId

		},
		success: function(data) {
			hideLoader()			
			console.log("data obtained")
			console.log(data)
			
		},
		error: function(data) {
			console.log("failed")
			hideLoader()
		}


	});

}