function editPatientDetails() {

	var edit = document.getElementById("edit-btn-pdashboard");
	var save = document.getElementById("save-btn-pdashboard");
	var cancel = document.getElementById("cancel-btn-pdashboard");
	var inputs = document.getElementsByClassName("editDetails");

	if (edit.style.display == "block") {
		save.style.display = "block";
		cancel.style.display = "block";
		edit.style.display = "none";

		for (var i = 0; i < inputs.length; i++) {
			inputs[i].disabled = false;
		}
	}

	else {
		save.style.display = "none";
		cancel.style.display = "none";
		edit.style.display = "block";
		
		for (var i = 0; i < inputs.length; i++) {
			inputs[i].disabled = true;
		}
	}
}