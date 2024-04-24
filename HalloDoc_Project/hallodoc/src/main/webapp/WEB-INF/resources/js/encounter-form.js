function editEncounterForm(type) {
	var x = document.getElementsByClassName("encounter-fields-disabled");

	if (type == 'edit') {
		$('.edit-hide-class').addClass('d-none')
		$('.edit-show-class').removeClass('d-none')

		
		for (var i = 0; i < x.length; i++) {
			x[i].disabled = false;
		}

	} else {
		$('.edit-hide-class').removeClass('d-none')
		$('.edit-show-class').addClass('d-none')
		for (var i = 0; i < x.length; i++) {
			x[i].disabled = true;
		}
	}

}