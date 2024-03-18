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
		console.log("Valid Email");
		var trimmedEmail = email.trim().toLowerCase();
		console.log(trimmedEmail);

		$.ajax({
			url: 'isPatientValidByEmail',
			type: 'post',
			dataType: 'text',
			data: {
				name: trimmedEmail
			},

			success: function(data) {
//				console.log("Success")
				if (data == "failure") {
//					console.log("Success1")
					passwordRow.display = "flex";
					isExsistingPatient.value = "false";
				}
				else {
//					console.log("Success2")
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
		console.log(trimmedState);

		$.ajax({
			url: 'isPatientStateValid',
			type: 'post',
			dataType: 'text',
			data: {
				state: trimmedState
			},

			success: function(data) {
//				console.log("Success")
				if (data == "failure") {
//					console.log("Success1")
					stateError.innerHTML = "We don't provide service here."
					submitBtn.disabled = true;
					submitBtn.style.opacity = 0.7;
				}
				else {
//					console.log("Success2")
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


