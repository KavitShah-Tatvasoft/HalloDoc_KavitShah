function fillData(reqId) {

	var transferNotes = 1;
	var physicianNotes = 1;
	var adminNotes = 1;
	var showadminCancellation = 0;
	var adminCancellationNotes = 1;
	var patientCancellationNotes = 1;
	var adminCancellationsShowNotes = 0

	$.ajax({
		url: '../getViewNotesData',
		type: 'POST',
		data: {
			reqId: reqId
		},
		success: function(res) {
			console.log(res)
			console.log("Data Obtained")

			res.forEach(function(data) {

				$(".view-notes-request-id").val(reqId)
				if (data.status == 1) {
					if (transferNotes == 1) {
						$(".transfer-notes-span").empty()
						transferNotes = 0
					}
					$(".transfer-notes-span").append("<div>" + data.notes + "</div>")
				}

				if (data.status == 3) {
					if (adminCancellationNotes == 1) {
						adminCancellationNotes = 0
						adminCancellationsShowNotes = 1
						$(".admin-cancellation-notes-span").empty()
						$(".admin-cancellation-notes-span-1").empty()
					}

					$(".admin-cancellation-notes-span").append("<div>" + data.notes + "</div>")
					$(".admin-cancellation-notes-span-1").append("<div>" + data.notes + "</div>")
				}

				if (data.status == 7) {
					if (patientCancellationNotes == 1) {
						$(".patient-cancellation-note-class").removeClass("d-none")
						patientCancellationNotes = 0
						showadminCancellation = 1
						$(".patient-cancellation-notes-span").empty()
					}

					$(".patient-cancellation-notes-span").append("<div>" + data.notes + "</div>")
				}

				if (data.adminNotes != null) {
//					if (adminNotes == 1) {
						$(".admin-notes-span").empty()
						$(".admin-notes-textarea").empty()
//						adminNotes = 0
//					}
					$(".admin-notes-span").append("<div>" + data.adminNotes + "</div>")
					$(".admin-notes-textarea").val(data.adminNotes)
				}

				if (data.providerNotes != null) {
//					if (physicianNotes == 1) {
						$(".physician-notes-span").empty()
//						physicianNotes = 0
//					}
					$(".physician-notes-span").append("<div>" + data.providerNotes + "</div>")

				}

				if (adminCancellationsShowNotes == 1) {
					if (showadminCancellation == 0) {
						$(".admin-cancellation-note-class-1").removeClass("d-none")
					} else {
						$(".admin-cancellation-note-class").removeClass("d-none")
					}
				}

			});

		}, error: function(res) {
			console.log("Failed to obtain data")
		}

	});

}


$("#adminNoteUpdateForm").submit(function(event) {

	event.preventDefault();


	var adminNote = document.getElementById("admin-note-textarea-id").value
	var reqId = document.getElementById("viewnotes-id").value

	var payload = {}

	payload["adminNote"] = adminNote
	payload["reqId"] = reqId

	$.ajax({
		url: '../updateAdminNote',
		type: 'POST',
		data: payload,
		success: function(res) {
			console.log("updated successfully")
			fillData(reqId)
		},
		error: function(res) { }
	});

});