function editDetails() {
	var x = document.getElementsByClassName("patient-details");
	var y = document.getElementById("save-cancel-details-btn-id");
	var z = document.getElementById("edit-details-id");


	y.style.display = "flex";
	z.style.display = "none";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = false;
	}
}


function saveDetails(reqId) {
	debugger
	console.log("in ajax of update case")
	var x = document.getElementsByClassName("patient-details");
	var y = document.getElementById("save-cancel-details-btn-id");
	var z = document.getElementById("edit-details-id");

	var firstName = document.getElementById("patient-fname").value;
	var lastName = document.getElementById("patient-lname").value;
	var phoneNumber = document.getElementById("phone").value;
	var dateOfBirth = document.getElementById("patient-bdate").value;
	
	

	var payLoadData = {}

	payLoadData["reqId"] = reqId
	payLoadData["firstName"] = firstName
	payLoadData["lastName"] = lastName
	payLoadData["phoneNumber"] = phoneNumber
	payLoadData["dateOfBirth"] = dateOfBirth
	
	y.style.display = "none";
	z.style.display = "block";

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
	$.ajax({
		url: 'updateCaseDetails',
		type: 'POST',
		data: payLoadData,
		success: function(res) {
			console.log(res)
		},
		error:function(res){
			console.log("Error")
		}
	})
	
}	


function cancelDetails() {
		var x = document.getElementsByClassName("patient-details");
		var y = document.getElementById("save-cancel-details-btn-id");
		var z = document.getElementById("edit-details-id");


		y.style.display = "none";
		z.style.display = "block";

		for(var i = 0; i<x.length; i++) {
		x[i].disabled = true;
	}
}