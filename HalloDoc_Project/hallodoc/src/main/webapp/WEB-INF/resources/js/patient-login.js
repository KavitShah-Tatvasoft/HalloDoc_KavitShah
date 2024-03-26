function switchBlack() {
	var v1 = document.getElementById("term").style;
	v1.color = "white";

	var v2 = document.getElementById("of").style;
	v2.color = "white";

	var v3 = document.getElementById("privacy").style;
	v3.color = "white";

	var x = document.getElementById("main-right-id").style;
	x.backgroundColor = "black";

	var y = document.getElementById("Login-text-id").style;
	y.color = "white";

	var z = document.getElementById("moon-id").style;
	z.display = "none";

	var w = document.getElementById("sun-id").style;
	w.display = "block";

}

function switchWhite() {
	var v1 = document.getElementById("term").style;
	v1.color = "black";

	var v2 = document.getElementById("of").style;
	v2.color = "black";

	var v3 = document.getElementById("privacy").style;
	v3.color = "black";

	var x = document.getElementById("main-right-id").style;
	x.backgroundColor = "#FAFAFA";

	var y = document.getElementById("Login-text-id").style;
	y.color = "black";

	var z = document.getElementById("moon-id").style;
	z.display = "block";

	var w = document.getElementById("sun-id").style;
	w.display = "none";

}

function showPass() {
	var x = document.getElementById("open-eye-id").style;
	var y = document.getElementById("close-eye-id").style;

	var z = document.getElementById("floatingPassword");
	z.type = "text";

	x.display = "none";
	y.display = "block";

}

function hidePass() {
	var x = document.getElementById("open-eye-id").style;
	var y = document.getElementById("close-eye-id").style;

	var z = document.getElementById("floatingPassword");
	z.type = "password";

	y.display = "none";
	x.display = "block";

}

function showPassword() {
	var x = document.getElementById("open-eye-id-1").style;
	var y = document.getElementById("close-eye-id-1").style;

	var z = document.getElementById("floatingPassword-1");
	z.type = "text";

	x.display = "none";
	y.display = "block";

}

function hidePassword() {
	var x = document.getElementById("open-eye-id-1").style;
	var y = document.getElementById("close-eye-id-1").style;

	var z = document.getElementById("floatingPassword-1");
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







