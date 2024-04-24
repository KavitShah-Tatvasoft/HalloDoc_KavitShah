function editCloseCase(){
    var x = document.getElementById("save-btn-close-case");
    var y = document.getElementById("cancel-btn-close-case");
    var z = document.getElementById("edit-btn-close-case");
    var w = document.getElementById("close-btn-close-case");
    var k = document.getElementsByClassName("close-case-details");

    z.style.display = "none";
    w.style.display = "none";
    x.style.display = "block";
    y.style.display = "block";

    for(var i=0; i<k.length;i++){
        k[i].disabled=false;
    }
}

function saveCloseCase(){
    var x = document.getElementById("save-btn-close-case");
    var y = document.getElementById("cancel-btn-close-case");
    var z = document.getElementById("edit-btn-close-case");
    var w = document.getElementById("close-btn-close-case");
    var k = document.getElementsByClassName("close-case-details");

    x.style.display = "none";
    y.style.display = "none";
    z.style.display = "block";
    w.style.display = "block";

    for(var i=0; i<k.length;i++){
        k[i].disabled=true;
    }
}

function cancelCloseCase(){
    var x = document.getElementById("save-btn-close-case");
    var y = document.getElementById("cancel-btn-close-case");
    var z = document.getElementById("edit-btn-close-case");
    var w = document.getElementById("close-btn-close-case");
    var k = document.getElementsByClassName("close-case-details");

    x.style.display = "none";
    y.style.display = "none";
    z.style.display = "block";
    w.style.display = "block";

    for(var i=0; i<k.length;i++){
        k[i].disabled=true;
    }
}

function showDocument(path) {
	window.open(path, "_blank")
}

$("#closeCaseForm").submit(function(event) {
	debugger
	event.preventDefault();
	var fName = $(".close-case-fName").val()
	var lName = $(".close-case-lName").val()
	var pNumber = $(".close-case-pNumber").val()
	var dob = $(".close-case-dob").val()
	var reqId = $(".close-case-request-id").val()
	
	var payload = {}
	payload["fName"] = fName
	payload["lName"] = lName
	payload["pNumber"] = pNumber
	payload["dob"] = dob
	payload["reqId"]= reqId
	
		$.ajax({
		url: '../updateCloseCaseDetails',
		type: 'POST',
		data: payload,
		success: function(data) {
			console.log("updated close case details")
			console.log(data)
		},
		error: function(data) {
			console.log("failed to update close case details")
		}
	})
})



