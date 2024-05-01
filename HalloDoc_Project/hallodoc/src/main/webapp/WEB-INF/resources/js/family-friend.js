function validatePatientState() {
	var state = document.getElementById("state").value;
	var stateError = document.getElementById("stateErrorField");
	var submitBtn = document.getElementById("submit-btn-id");
	if (state == "") {
		stateError.innerHTML = "Please enter a state";
		submitBtn.disabled = true;
		submitBtn.style.opacity = 0.7;
	}
	else {
		var trimmedState = state.trim().toLowerCase();
		  ;

		$.ajax({
			url: 'isPatientStateValid',
			type: 'post',
			dataType: 'text',
			data: {
				state: trimmedState
			},

			success: function(data) {
//				  
				if (data == "failure") {
//					  
					stateError.innerHTML = "We don't provide service here."
					submitBtn.disabled = true;
					submitBtn.style.opacity = 0.7;
				}
				else {
//					  
					stateError.innerHTML = "";
					submitBtn.disabled = false;
					submitBtn.style.opacity = 1;
				}
			},
			
			error: function(){
				alert("An error occured.......")
			}

		})
	}

}
