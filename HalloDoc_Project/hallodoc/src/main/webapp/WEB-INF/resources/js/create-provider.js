function checkUsername() {

	debugger
	var x = $(".pUsername").val()

	$.ajax({
		url: 'checkUsername',
		type: 'POST',
		data: {
			name: x
		},
		success: function(data) {
			  
			if (data == "false") {
				document.getElementById("create-provider-btn").disabled = true

			}
			else {
				document.getElementById("create-provider-btn").disabled = false
			}
		}, error: function(data) {

		}
	})

}

function getCheckBoxDetails() {
	debugger
	var selectedRegions = []; 
	document.querySelectorAll('input[type="checkbox"]:checked').forEach(checkbox => {
		selectedRegions.push(parseInt(checkbox.value));
	});

	$(".pRegions").attr("value",selectedRegions.join(','))

}

