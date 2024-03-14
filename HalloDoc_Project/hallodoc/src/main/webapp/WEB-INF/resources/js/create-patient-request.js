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

function validateEmail() {

	var email = document.getElementById("emailField").value;
	var emailError = document.getElementById("emailErrorField");
	var passwordRow = document.getElementById("passwordRowId").style;

	var validRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (email == '') {
		emailError.innerHTML = "Please enter email!"
	}
	else if (!(email.match(validRegex))) {
		emailError.innerHTML = "Please enter valid email!"
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
				console.log("Success")
				if (data == "failure") {
					console.log("Success1")
					passwordRow.display = "flex";
				}
				else {
					console.log("Success2")
					passwordRow.display = "none";
				}
			}

		})
	}

}
