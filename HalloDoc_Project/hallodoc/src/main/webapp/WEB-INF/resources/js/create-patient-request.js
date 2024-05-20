function showPass() {
	//	alert("hello1")
	var x = document.getElementById("open-eye-id").style;
	var y = document.getElementById("close-eye-id").style;

	//	alert("hello4")
	var z = document.getElementById("floatingPassword-pass1");
	z.type = "text";

	//	alert("hello2")
	x.display = "none";
	y.display = "block";

	//    alert("hello3")

}

function hidePass() {
	//	alert("Hello1")
	var x = document.getElementById("open-eye-id").style;
	var y = document.getElementById("close-eye-id").style;
	//	alert("Hello4")
	var z = document.getElementById("floatingPassword-pass1");
	z.type = "password";
	//	alert("Hello3")
	y.display = "none";
	x.display = "block";
	//	alert("Hello4")
}


function showPass1() {
	var x = document.getElementById("open-eye-id-1").style;
	var y = document.getElementById("close-eye-id-1").style;

	var z = document.getElementById("floatingPassword-pass2");
	z.type = "text";

	x.display = "none";
	y.display = "block";

}

function hidePass1() {
	var x = document.getElementById("open-eye-id-1").style;
	var y = document.getElementById("close-eye-id-1").style;

	var z = document.getElementById("floatingPassword-pass2");
	z.type = "password";

	y.display = "none";
	x.display = "block";

}

function validatePassword() {

	var pass = document.getElementById("floatingPassword-pass1").value;;
	var confPass = document.getElementById("floatingPassword-pass2").value;
	var passError = document.getElementById("passError");
	var passError1 = document.getElementById("passError1");
	if (pass == '') {
		passError1.innerHTML = "Please enter a password";
		passError.innerHTML = "";
	}
	else if (confPass != '') {
		if (pass == confPass) {
			passError.innerHTML = "";
			passError1.innerHTML = "";
		}
		else {
			passError.innerHTML = "Password dosen't match";
		}
	}
	else { passError1.innerHTML = ""; }
}

function validateConfirmPassword() {
	debugger
	var pass = document.getElementById("floatingPassword-pass1").value;;
	var confPass = document.getElementById("floatingPassword-pass2").value;
	var passError = document.getElementById("passError");
	if (confPass == '') {
		passError.innerHTML = "Please enter a confirm password";
	}
	else if (pass != '') {
		if (pass == confPass) { passError.innerHTML = ""; }
		else {
			passError.innerHTML = "Password dosen't match";
		}
	}
	else if (pass == '') {
		passError.innerHTML = "";
	}
	else { passError.innerHTML = ""; }
}


function validateEmail() {

	var email = document.getElementById("emailField").value;
	var emailError = document.getElementById("emailErrorField");
	var passwordRow = document.getElementById("passwordRowId").style;
	var isExsistingPatient = document.getElementById("isExsistingPatient");

	var validRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (email == '') {
		emailError.innerHTML = "Please enter email!";
		passwordRow.display = "none";
		document.getElementById("emailField").focus();
	}
	else if (!(email.match(validRegex))) {
		emailError.innerHTML = "Please enter valid email!";
		passwordRow.display = "none";
		document.getElementById("emailField").focus();
	}
	else {
		emailError.innerHTML = "";
		;
		var trimmedEmail = email.trim().toLowerCase();
		;

		$.ajax({
			url: 'isPatientValidByEmail',
			type: 'post',
			dataType: 'text',
			data: {
				name: trimmedEmail
			},

			success: function(data) {
				//				  
				if (data == "failure") {
					//					  
					passwordRow.display = "flex";
					isExsistingPatient.value = "false";
				}
				else {
					//					  
					passwordRow.display = "none";
					isExsistingPatient.value = "true";
				}
			}

		})
	}

}

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

$.validator.addMethod("password", function(value, element) {
	return this.optional(element) || /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/.test(value);
}, "Password must contain at least one letter, one digit, and one special character and 8 digit in length.");

$("#patientRequestForm").validate({
	rules: {
		firstName: {
			required: true,
			minlength: 3
		},
		lastName: {
			required: true,
			minlength: 3
		},
		dob: {
			required: true,
		},
		email: {
			required: true,
			email: true
		},
		mobileNumber: {
			required: true,
			minlength: 10,
			digits: true
		},
		street: {
			required: true,
			minlength: 2,
		},
		city: {
			required: true,
			minlength: 2,
		},
		state: {
			required: true,
			minlength: 2,
		},
		zipcode: {
			required: true,
			minlength: 6,
		},
		password: {
			required: true,
			password: true,
		},
		confirmPassword: {
			password: true,
			required: true,
			equalTo: "#floatingPassword-pass1"
		}
	},
	messages: {
		firstName: {
			required: "Please enter your first name",
			minlength: "Your name must be at least 3 characters long"
		},
		lastName: {
			required: "Please enter your first name",
			minlength: "Your name must be at least 3 characters long"
		},
		email: {
			required: "Please enter your email address",
			email: "Please enter a valid email address"
		},
		dob: {
			required: "Please select your date of birth",
		},
		mobileNumber: {
			required: "Please enter your mobile number",
			digits: "Your mobile number should only contain digits",
			minlength: "Your mobile number must be at 10 characters long"
		},
		street: {
			required: "Please enter your street",
			minlength: "Your street name must be at least 2 characters long"
		},
		city: {
			required: "Please enter your city",
			minlength: "Your city name must be at least 2 characters long"
		},
		state: {
			required: "Please enter your state",
			minlength: "Your state name must be at least 2 characters long"
		},
		zipcode: {
			required: "Please enter your zipcode",
			digits: "Your zipcode should only contain digits",
			minlength: "Your zipcode must be at least 6 characters long",
		},
		password: {
			required: "Please enter password"
		},
		confirmPassword: {
			required: "Please enter confirm password",
			equalTo: "Please enter confirm password same as password"
		}

	},
	errorPlacement: function(error, element) {
		if (element.attr("name") == "firstName") {
			error.appendTo("#firstNameError");
			$('#firstNameError').addClass('visible');
		} else if (element.attr("name") == "lastName") {
			error.appendTo("#lastNameError");
			$('#lastNameError').addClass('visible');
		} else if (element.attr("name") == "dob") {
			error.appendTo("#dobError");
			$('#dobError').addClass('visible');
		} else if (element.attr("name") == "email") {
			error.appendTo("#emailErrorField");
			$('#emailErrorField').addClass('visible');
		} else if (element.attr("name") == "mobileNumber") {
			error.appendTo("#mobileNumberError");
			$('#mobileNumberError').addClass('visible');
		} else if (element.attr("name") == "street") {
			error.appendTo("#streetError");
			$('#streetError').addClass('visible');
		} else if (element.attr("name") == "city") {
			error.appendTo("#cityError");
			$('#cityError').addClass('visible');
		} else if (element.attr("name") == "state") {
			error.appendTo("#stateError");
			$('#stateError').addClass('visible');
		} else if (element.attr("name") == "zipcode") {
			error.appendTo("#zipError");
			$('#zipError').addClass('visible');
		} else if (element.attr("name") == "password") {
			error.appendTo("#passError1");
		} else if (element.attr("name") == "confirmPassword") {
			error.appendTo("#passError");
		}

		else {
			error.insertAfter(element);
		}
	},
	highlight: function(element) {
		$(element).addClass('invalid');
		$(element).next().next().addClass('visible');

	},
	unhighlight: function(element) {
		$(element).removeClass('invalid');
		$(element).next().next().removeClass('visible');
	},
	submitHandler: function(form) {
		showLoader()
		form.submit();
	}
});


