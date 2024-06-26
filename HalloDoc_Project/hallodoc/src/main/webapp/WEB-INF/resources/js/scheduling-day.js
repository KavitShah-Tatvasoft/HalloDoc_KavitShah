var physicianResources = []

function getShiftDetails(eventId) {

	$(".physician-unavailable-edit-div").addClass("d-none")
	$.ajax({
		url: 'get-event-details',
		type: 'POST',
		data: {
			eventId: eventId
		},
		success: function(data) {
			console.log(data)
			$(".region-details-edit-shift").val(data.regionId)
			$(".region-details-edit-shift").text(data.regionName)
			$(".physician-details-edit-shift").val(data.physicianId)
			$(".physician-details-edit-shift").text(data.physicianName)
			$(".shift-date-edit-shift").val(data.shiftDate)
			$(".shift-start-edit-shift").val(data.startTime)
			$(".shift-end-edit-shift").val(data.endTime)
			$(".hidden-shift-id").val(data.shiftDetailId)

		},
		error: function(data) {
			console.log("Failed")
		}
	})

}

function editShift() {
	debugger
	var shiftDetailId = $(".hidden-shift-id").val()
	var shiftDate = $(".shift-date-edit-shift").val()
	var startTime = $(".shift-start-edit-shift").val()
	var endTime = $(".shift-end-edit-shift").val()

	var payload = {}
	payload["shiftDetailId"] = shiftDetailId
	payload["shiftDate"] = shiftDate
	payload["startTime"] = startTime
	payload["endTime"] = endTime

	$.ajax({
		url: 'edit-old-shift-details',
		type: 'POST',
		data: payload,
		success: function(data) {
			if (data == true) {

				$(".dismiss-button").click()
				app.init()
			} else {
				$(".physician-unavailable-edit-div").removeClass("d-none")
			}

		},
		error: function(data) {
			console.log("Failed")
		}
	})

}

function regionFilter() {
	app.init();
}


function getPhysicianData() {
	$.ajax({
		url: 'get-physician-details-scheduling',
		type: 'GET',
		success: function(res) {

			physicianResources = res


		},
		error: function(res) {

		}

	});

	console.log(physicianResources)
}

function getPhysician() {
	//	var showRegionError = document.getElementById("region-select-transfer-error")
	//	showRegionError.innerHTML = ""
	var region = $(".region-name-class-shift option:selected").val()
	getPhysiciansByRegion(region)

}



function getPhysiciansByRegion(region) {


	debugger

	$.ajax({
		url: 'getPhysiciansByRegion',
		type: 'POST',
		data: {
			regionId: region
		},
		success: function(res) {



			$(".physician-name-class").empty()
			$(".physician-name-class").append("<option value='0' hidden selected>Select Physician</option>")

			res.forEach(function(data) {
				$(".physician-name-class").append("<option value='" + data[0] + "'>Dr. " + data[1] + " " + data[2] + "</option>")
			})

		},
		error: function(res) {

		}

	});

}

function toggleRepeatDetails() {

	if ($(".toggle-repeat-details").prop("checked")) {
		$(".repeat-details-class").removeClass("d-none")
	} else {
		$(".repeat-details-class").addClass("d-none")
	}


}

function changeDate() {
	debugger
	$(".current-date-class").text($(".scheduler_default_timeheadergroup_inner").text())
}

function setInitialDate() {
	$(".current-date-class").text($(".scheduler_default_timeheadergroup_inner").text())
}

function setWeekDate() {
	var date = `${new DayPilot.Date(dp.startDate).toString("MMM d")} - ${new DayPilot.Date(dp.startDate).addDays(6).toString("MMM d, yyyy")}`
	$(".current-date-class").text(date)
}

function setMonthDate(){
	var date = `${new DayPilot.Date(dp.startDate).toString("MMM yyyy")}`
	$(".current-date-class").text(date)
}

function selectedDays() {
	var daysString = "";
	$(".day-checkbox").each(function() {
		if ($(this).is(":checked")) {
			daysString += "1,";
		} else {
			daysString += "0,";
		}
	});

	daysString = daysString.slice(0, -1)
	console.log(daysString);

	$(".repeated-week-days").val(daysString)


}

function hideShiftRepeatDetials() {
	$(".physician-unavailable-error-div").addClass("d-none")
	$(".repeat-details-class").addClass("d-none")
}

function toggleStatus() {
	var shiftDetailId = $(".hidden-shift-id").val()

	$.ajax({
		url: 'toggle-shift-status',
		type: 'POST',
		data: {
			shiftDetailId: shiftDetailId
		},
		success: function(data) {
			app.init()
			console.log("shift-toggled")
		},
		error: function(data) {
			console.log("failed to toggle")
		}
	})

}

function deleteShift() {
	var shiftDetailId = $(".hidden-shift-id").val()

	$.ajax({
		url: 'delete-shift',
		type: 'POST',
		data: {
			shiftDetailId: shiftDetailId
		},
		success: function(data) {
			app.init()
			console.log("shift-deleted")
		},
		error: function(data) {
			console.log("failed to delete")
		}
	})
}

$("#create-shift-form").submit(function(event) {
	debugger
	event.preventDefault();


	var region = $(".region-name-class-shift").val()
	var physicianId = $("#physician-id-case").val()
	var shiftDate = $("#shift-date-case").val()
	var startTime = $("#start-time-case").val()
	var endTime = $("#end-time-case").val()
	if ($(".toggle-repeat-details").prop("checked")) {
		var isRepeated = "true"
	} else {
		var isRepeated = "false"
	}
	var selectedDays = $("#selected-days-case").val()
	var repeatTimes = $("#repeat-times-case").val()

	var payload = {}
	payload["region"] = region
	payload["physicianId"] = physicianId
	payload["shiftDate"] = shiftDate
	payload["startTime"] = startTime
	payload["endTime"] = endTime
	payload["isRepeated"] = isRepeated
	payload["selectedDays"] = selectedDays
	payload["repeatTimes"] = repeatTimes

	$.ajax({
		url: 'create-shift',
		type: 'POST',
		data: payload,
		success: function(data) {
			if (data == true) {
				$(".reset-shift-modal-btn").click()
				location.reload()
			} else {
				$(".physician-unavailable-error-div").removeClass("d-none")
			}
			console.log("create shift success")
		},
		error: function(data) {
			console.log("create shift faliure")
		}
	})
})