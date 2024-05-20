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

			error: function() {
				alert("An error occured.......")
			}

		})
	}

}

$("#concierge-form-id").validate({
	rules: {
		reqFirstName: {
			required: true,
			minlength: 3
		},
		reqLastName: {
			required: true,
			minlength: 3
		},
		reqMobileNumber: {
			required: true,
			minlength: 10,
			maxlength: 10,
			digits: true
		},
		reqEmail: {
			required: true,
			email: true
		},
		reqProperty: {
			required: true,
			minlength: 3
		},
		reqStreet: {
			required: true,
			minlength: 2,
		},
		reqCity: {
			required: true,
			minlength: 2,
		},
		reqState: {
			required: true,
			minlength: 2,
		},
		reqZipcode: {
			required: true,
			minlength: 2,
		},
		ptFirstName: {
			required: true,
			minlength: 3
		},
		ptLastName: {
			required: true,
			minlength: 3
		},
		ptDob: {
			required: true,
		},
		ptEmail: {
			required: true,
			email: true
		},
		ptMobileNumber: {
			required: true,
			minlength: 10,
			maxlength: 10,
			digits: true
		},
		ptStreet: {
			required: true,
			minlength: 2,
		},
		ptCity: {
			required: true,
			minlength: 2,
		},
		ptState: {
			required: true,
			minlength: 2,
		},
		ptZipcode: {
			required: true,
			minlength: 6,
		},

	},
	messages: {
		reqFirstName: {
			required: "Please enter your first name",
			minlength: "Your name must be at least 3 characters long"
		},
		reqLastName: {
			required: "Please enter your last name",
			minlength: "Your name must be at least 3 characters long"
		},
		reqMobileNumber: {
			required: "Please enter your mobile number",
			digits: "Your mobile number should only contain digits",
			minlength: "Your mobile number must be at 10 characters long",
			maxlength: "Your mobile number must only be at 10 characters long"
		},
		reqEmail: {
			required: "Please enter your email address",
			email: "Please enter a valid email address"
		},
		reqProperty: {
			required: "Please enter your property name",
			minlength: "Property name must be at least 3 characters long"
		},
		reqStreet: {
			required: "Please enter your street",
			minlength: "Street name must be at least 2 characters long"
		},
		reqCity: {
			required: "Please enter your city",
			minlength: "City name must be at least 2 characters long"
		},
		reqState: {
			required: "Please enter your state",
			minlength: "State name must be at least 2 characters long"
		},
		reqZipcode: {
			required: "Please enter your zipcode",
			minlength: "Zipcode must be at least 6 characters long",
			digits: "Zipcode should only contain digits",

		},
		ptFirstName: {
			required: "Please enter patient's first name",
			minlength: "Patient first name must be at least 3 characters long"
		},
		ptLastName: {
			required: "Please enter patient's last name",
			minlength: "Patient last name must be at least 3 characters long"
		},
		ptEmail: {
			required: "Please enter patient's email address",
			email: "Please enter a valid email address"
		},
		ptDob: {
			required: "Please select patient's date of birth",
		},
		ptMobileNumber: {
			required: "Please enter patient's mobile number",
			digits: "Mobile number should only contain digits",
			minlength: "Mobile number must be at 10 characters long",
			maxlength: "Your mobile number must only be at 10 characters long"
		},
		ptStreet: {
			required: "Please enter patient's street",
			minlength: "Street name must be at least 2 characters long"
		},
		ptCity: {
			required: "Please enter patient's city",
			minlength: "City name must be at least 2 characters long"
		},
		ptState: {
			required: "Please enter patient's state",
			minlength: "State name must be at least 2 characters long"
		},
		ptZipcode: {
			required: "Please enter patient's zipcode",
			minlength: "Zipcode must be at least 6 characters long",
			digits: "Zipcode should only contain digits",
		}

	},
	errorPlacement: function(error, element) {
		if (element.attr("name") == "reqMobileNumber") {
			error.appendTo("#reqMobileNumberError");
			$('#reqMobileNumberError').addClass('visible');
		} else if (element.attr("name") == "ptMobileNumber") {
			error.appendTo("#ptMobileNumberError");
			$('#ptMobileNumberError').addClass('visible');
		}
		else if (element.attr("name") == "ptDob") {
			error.appendTo("#ptDobError");
			$('#ptDobError').addClass('visible');
		}
		else {
			$(element).next().next().html(error)
			$(element).next().next().addClass("visible")
		}
	},
	highlight: function(element) {
		
		if (element.attr("name") == "reqMobileNumber") {
			$(element).addClass('invalid');
			$('#reqMobileNumberError').addClass('visible');
		} else if (element.attr("name") == "ptMobileNumber") {
			$(element).addClass('invalid');
			$('#ptMobileNumberError').addClass('visible');
		}
		else if (element.attr("name") == "ptDob") {
			$(element).addClass('invalid');
			$('#ptDobError').addClass('visible');
		}
		else {
			$(element).addClass('invalid');
			$(element).next().next().addClass('visible');
		}
		
		
	},
	unhighlight: function(element) {
		
		if (element.attr("name") == "reqMobileNumber") {
			$(element).removeClass('invalid');
			$('#reqMobileNumberError').removeClass('visible');
		} else if (element.attr("name") == "ptMobileNumber") {
			$(element).removeClass('invalid');
			$('#ptMobileNumberError').removeClass('visible');
		}
		else if (element.attr("name") == "ptDob") {
			$(element).removeClass('invalid');
			$('#ptDobError').removeClass('visible');
		}
		else {
			$(element).removeClass('invalid');
			$(element).next().next().removeClass('visible');
		}
	},
	submitHandler: function(form) {
		showLoader()
		form.submit();
	}
});
