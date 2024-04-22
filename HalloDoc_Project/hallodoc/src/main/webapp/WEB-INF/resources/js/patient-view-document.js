function downloadAll() {

	var classes = document.getElementsByClassName('justone-download');

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

