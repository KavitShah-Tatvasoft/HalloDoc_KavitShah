function sendRequestToAdmin(){
	debugger
	var desc = $("#request-admin-description").val()
	showLoader()
	$.ajax({
			url: 'send-request-to-admin',
			type: 'POST',
			data: {
				desc: desc
			},
			success: function(data) {
				hideLoader()
				console.log(data)
				$("#cancel-btn-admin-request").click()
			}, error: function(data) {
				hideLoader()
				console.log("failed to send email to admin")
			}


		})

	
}


function changePasswordProvider() {

	var pass = $(".provider-reset-pass").val()
	if (pass != null) {
		$.ajax({
			url: 'change-provider-password',
			type: 'POST',
			data: {
				pass: pass
			},
			success: function(data) {
				console.log(data)

			}, error: function(data) {
				console.log("failed to change password")
			}


		})

	}


}

function showDoc(path) {
	window.open(path, "_blank")
}

function resetPassword() {
	var x = document.getElementsByClassName("provider-reset-pass");
	var y = document.getElementById("reset-btn-bottom");
	var z = document.getElementById("cancel-password-change-flex");


	y.style.display = "none";
	z.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = false;
	}
}

function changePassword() {
	var x = document.getElementsByClassName("provider-reset-pass");
	var y = document.getElementById("reset-btn-bottom");
	var z = document.getElementById("cancel-password-change-flex");


	z.style.display = "none";
	y.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
}

function cancelChange() {
	var x = document.getElementsByClassName("provider-reset-pass");
	var y = document.getElementById("reset-btn-bottom");
	var z = document.getElementById("cancel-password-change-flex");


	z.style.display = "none";
	y.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
}
