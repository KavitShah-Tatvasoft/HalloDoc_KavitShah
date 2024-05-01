function deleteFile(fileId) {

	$.ajax({
		url: '../softDeleteUploadedFile',
		type: 'POST',
		data: {
			fileId: fileId
		},
		success: function(data) {
			  
			location.reload()
		},
		error: function(data) {
			  
		}
	})


}

$('.selectall').click(function() {
	if ($(this).is(':checked')) {
		$('input:checkbox').prop('checked', true);
	} else {
		$('input:checkbox').prop('checked', false);
	}
});

$("input[type='checkbox'].justone").change(function() {
	var a = $("input[type='checkbox'].justone");
	if (a.length == a.filter(":checked").length) {
		$('.selectall').prop('checked', true);
	} else {
		$('.selectall').prop('checked', false);
	}
});


function downloadAll() {

	var classes = document.getElementsByClassName('justone');

	var count = 0;
	for (var i = 0; i < classes.length; i++) {
		if (classes[i].checked == true) {
			document.getElementsByClassName('link')[i].click();
		} else {
			count++;
		}
	}
	if (count == classes.length) {
		alert('No checkboxes are ticked');
	}

}

function deleteAll() {

	var classes = document.getElementsByClassName('justone-download');
	var deleteList = []
	var count = 0;
	for (var i = 0; i < classes.length; i++) {
		if (classes[i].checked == true) {
			var temp = $(".justone-download")[i]
			deleteList.push($(temp).attr("data-value"))
		} else {
			count++;
		}
	}
	alert(deleteList)
	if (count == classes.length) {
		alert('No checkboxes are ticked');
	} else {

		$.ajax({
			url: '../deleteMultipleFiles',
			type: 'POST',
			data: { data: deleteList.join(',') },
			success: function(data) {
				  
				location.reload()
			},
			error: function(data) {
				  
			}
		})

	}

}

function showDoc(path) {
	window.open(path, "_blank")
}

function sendMailToPatient(reqId) {
	var classes = document.getElementsByClassName('justone-download');
	var attachmentList = []
	var count = 0;
	for (var i = 0; i < classes.length; i++) {
		if (classes[i].checked == true) {
			var temp = $(".justone-download")[i]
			attachmentList.push($(temp).attr("data-value"))
		} else {
			count++;
		}
	}
	alert(attachmentList)
	if (count == classes.length) {
		alert('No checkboxes are ticked');
	} else {
			
			var payload = {}
			payload["reqId"] = reqId
			payload["attachmentList"] = attachmentList.join(',')

		$.ajax({
			url: '../sendFilesToPatient',
			type: 'POST',
			data: payload,
			success: function(data) {
				  
				location.reload()
			},
			error: function(data) {
				  
			}
		})

	}


}