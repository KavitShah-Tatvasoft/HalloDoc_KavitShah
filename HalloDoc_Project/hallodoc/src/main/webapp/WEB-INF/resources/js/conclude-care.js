function submitForm(){
	$("#conclude-care-form").submit();
}

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