function editAdminDetails1() {
	var x = document.getElementsByClassName("admin-details-row-1");
	var y = document.getElementById("edit-btn-id");
	var z = document.getElementById("save-reset-btns-id");


	y.style.display = "none";
	z.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = false;
	}
}

function saveAdminDetails1() {
	var x = document.getElementsByClassName("admin-details-row-1");
	var y = document.getElementById("edit-btn-id");
	var z = document.getElementById("save-reset-btns-id");


	z.style.display = "none";
	y.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
}

function cancelAdminDetails1() {
	var x = document.getElementsByClassName("admin-details-row-1");
	var y = document.getElementById("edit-btn-id");
	var z = document.getElementById("save-reset-btns-id");


	z.style.display = "none";
	y.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
}


function resetPassword() {
	var x = document.getElementsByClassName("admin-reset-pass");
	var y = document.getElementById("reset-btn-bottom");
	var z = document.getElementById("reset-pass-cancel-id");


	y.style.display = "none";
	z.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = false;
	}
}

function changePassword() {
	var x = document.getElementsByClassName("admin-reset-pass");
	var y = document.getElementById("reset-btn-bottom");
	var z = document.getElementById("reset-pass-cancel-id");


	z.style.display = "none";
	y.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
}

function cancelChange() {
	var x = document.getElementsByClassName("admin-reset-pass");
	var y = document.getElementById("reset-btn-bottom");
	var z = document.getElementById("reset-pass-cancel-id");


	z.style.display = "none";
	y.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
}

function editAdminDetails2() {
	var x = document.getElementsByClassName("admin-details-row-2");
	var y = document.getElementById("edit-btn-id-1");
	var z = document.getElementById("save-reset-btns-id-1");


	y.style.display = "none";
	z.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = false;
	}
}

function saveAdminDetails2() {
	var x = document.getElementsByClassName("admin-details-row-2");
	var y = document.getElementById("edit-btn-id-1");
	var z = document.getElementById("save-reset-btns-id-1");


	z.style.display = "none";
	y.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
}

function cancelAdminDetails2() {
	var x = document.getElementsByClassName("admin-details-row-2");
	var y = document.getElementById("edit-btn-id-1");
	var z = document.getElementById("save-reset-btns-id-1");


	z.style.display = "none";
	y.style.display = "flex";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
}


function changePasswordAdmin() {

	var pass = $(".admin-reset-pass").val()

	$.ajax({
		url: 'changeAdminPassword',
		type: 'POST',
		data: {
			pass: pass
		},
		success: function(data) {
			console.log("password updated")

		}, error: function(data) {
			console.log("update failed")
		}


	})


}

function updateAdminAdress() {
	var address1 = $(".admin-address-1").val()
	var address2 = $(".admin-address-2").val()
	var city = $(".admin-address-city").val()
	var regionId = $(".admin-address-region").val()
	var zipcode = $(".admin-address-zipcode").val()
	var phone = $(".admin-address-phone").val()

	var payload = {}

	payload["address1"] = address1
	payload["address2"] = address2
	payload["city"] = city
	payload["regionId"] = regionId
	payload["zipcode"] = zipcode
	payload["phone"] = phone


	$.ajax({
		url: 'changeAdminAddress',
		type: 'POST',
		data: payload,
		success: function(data) {
			console.log("address updated")

		}, error: function(data) {
			console.log("address update failed")
		}


	})


}


function updateAdminContactDetails() {
	debugger
	var selectedRegions = [];
	document.querySelectorAll('input[type="checkbox"]:checked').forEach(checkbox => {
		selectedRegions.push(parseInt(checkbox.value));
	});

	var checkedId = selectedRegions.join(',')

	var phoneNumber = $(".phone-number-admin").val()
	var firstName = $(".first-name-admin").val()
	var lastName = $(".last-name-admin").val()

	var payload = {}
	
		payload["checkedId"] = checkedId
 		payload["phoneNumber"] = phoneNumber
		payload["firstName"] = firstName
		payload["lastName"] = lastName
        
	$.ajax({
		url: 'updateAdminContactDetails',
		type: 'POST',
		data: payload,
		success: function(data) {
			console.log("contact updated")

		}, error: function(data) {
			console.log("contact update failed")
		}


	})
	
}
