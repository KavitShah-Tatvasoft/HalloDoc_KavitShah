function showMap() {
	var street = document.getElementById("street");
	var city = document.getElementById("city");
	var state = document.getElementById("state");
	var zipcode = document.getElementById("zipcode");
	
	var street_error = document.getElementById("street_error");
	var city_error = document.getElementById("city_error");
	var state_error = document.getElementById("state_error");
	var zipcode_error = document.getElementById("zipcode_error");
	
	var gmap = document.getElementById("gmap_canvas");
	
	if(street.value == ""){
		street_error.innerHTML = "Please enter a street.";
	}
	
	else if(city.value == ""){
		street_error.innerHTML = "";
		city_error.innerHTML = "Please enter a city.";
	}
	
	else if(state.value == ""){
		street_error.innerHTML = "";
		city_error.innerHTML = "";
		state_error.innerHTML = "Please enter a state.";
	}
	
	else if(zipcode.value == ""){
		street_error.innerHTML = "";
		city_error.innerHTML = "";
		state_error.innerHTML = "";
		zipcode_error.innerHTML = "Please enter a zipcode.";
	}
	else{
		street_error.innerHTML = "";
		city_error.innerHTML = "";
		state_error.innerHTML = "";
		zipcode_error.innerHTML = "";
		
		var address = "https://maps.google.com/maps?q=" + street.value + city.value + state.value + zipcode.value + "&t=&z=13&ie=UTF8&iwloc=&output=embed";
		gmap.setAttribute("src",address);
		var modal = new bootstrap.Modal(document.getElementById('exampleModal')); 
		modal.show();
	}
}