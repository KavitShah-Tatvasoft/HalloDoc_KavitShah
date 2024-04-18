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
		error: function(res) {
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

	for (var i = 0; i < x.length; i++) {
		x[i].disabled = true;
	}
}


function assignCase(reqId) {
	$("#assign-case-request-id").val(reqId)
	var showPhysicianError = document.getElementById("select-physician-error")
	showPhysicianError.innerHTML = ""
}

function getPhysiciansByRegion() {

	var showRegionError = document.getElementById("select-region-error")
	var physicianId = $(".physician-name-class option:selected").val()
	var region = $(".region-name-class option:selected").val()

	if (region == "none") {
		showRegionError.innerHTML = "Please select a region."
	}
	console.log(region)
	$.ajax({
		url: '../getPhysiciansByRegion',
		type: 'POST',
		data: {
			regionId: region
		},
		success: function(res) {
			console.log(res)
			console.log("Physician List Obtained")

			$(".physician-name-class").empty()
			$(".physician-name-class").append("<option value='0' hidden selected>Select Physician</option>")

			res.forEach(function(data) {
				$(".physician-name-class").append("<option value='" + data[0] + "'>Dr. " + data[1] + " " + data[2] + "</option>")
			})

		},
		error: function(res) {
			console.log("Failed to Obtain Physician List ")
		}

	});

}


$("#assign-case-form").submit(function(event) {
	debugger
	event.preventDefault();
	var showPhysicianError = document.getElementById("select-physician-error")
	var reqId = document.getElementById("assign-case-request-id").value
	var physicianId = $(".physician-name-class option:selected").val()
	var description = document.getElementById("description-text-area").value

	payload = {}
	payload["reqId"] = reqId
	payload["physicianId"] = physicianId
	payload["description"] = description

	if (physicianId == 0) {

		showPhysicianError.innerHTML = "Please select a physician."
	} else {
		$(".close-assign-case").click()
		$.ajax({
			url: '../assignPhysician',
			type: 'POST',
			data: payload,
			success: function(data) {
				console.log("assigned sucessfully")
				
			},
			error: function(data) {
				console.log("failed to assign provider")
			}
		})

		$("#assign-case-form").get(0).reset()
	}
})

