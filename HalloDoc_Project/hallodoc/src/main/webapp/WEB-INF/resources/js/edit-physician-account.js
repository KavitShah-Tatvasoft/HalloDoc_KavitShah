function editDetails1(){
    var x = document.getElementById("edit-btn-1");
    var y = document.getElementById("password-btn-12");
    var z = document.getElementById("save-btn-1");
    var w = document.getElementById("cancel-btn-12");
    var k = document.getElementsByClassName("provider-details-1");

    x.style.display = "none";
    y.style.display = "none";
    z.style.display = "block";
    w.style.display = "block";

    for(var i=0; i<k.length;i++){
        k[i].disabled=false;
    }
}

function cancelChange1(){
    var x = document.getElementById("edit-btn-1");
    var y = document.getElementById("password-btn-12");
    var z = document.getElementById("save-btn-1");
    var w = document.getElementById("cancel-btn-12");
    var k = document.getElementsByClassName("provider-details-1");

    z.style.display = "none";
    w.style.display = "none";
    x.style.display = "block";
    y.style.display = "block";

    for(var i=0; i<k.length;i++){
        k[i].disabled=true;
    }
}


function saveChange1(){
    var x = document.getElementById("edit-btn-1");
    var y = document.getElementById("password-btn-12");
    var z = document.getElementById("save-btn-1");
    var w = document.getElementById("cancel-btn-12");
    var k = document.getElementsByClassName("provider-details-1");

    z.style.display = "none";
    w.style.display = "none";
    x.style.display = "block";
    y.style.display = "block";

    for(var i=0; i<k.length;i++){
        k[i].disabled=true;
    }
}

function saveRoleStatus() {
	debugger
	var id = $(".pId").val()
	var status = $(".pStatus").val()
	var role = $(".pRole").val()
	
	var payload = {}
	payload["id"] = id
	payload["status"] = status
	payload["role"] = role
	
	$.ajax({
		url: '../updateProviderRoleStatus',
		type: 'POST',
		data: payload,
		success: function(data) {
			console.log(data)
		},
		error: function(data) {
			console.log("failed to update status data")
		}
	})
}

function persistPassword(){
	var id = $(".pId").val()
	var password = $(".pPassword").val()
	
	var payload = {}
	payload["id"] = id
	payload["password"] = password
	
		$.ajax({
		url: '../updateProviderPassword',
		type: 'POST',
		data: payload,
		success: function(data) {
			console.log(data)
		},
		error: function(data) {
			console.log("failed to update password")
		}
	})
	
}

function editMailingInformation(){
	var id = $(".pId").val()
	var address1 = $(".pAddressOne").val()
	var address2 = $(".pAddressTwo").val()
	var city =  $(".pCity").val()
	var state = $(".pState").val()
	var zip = $(".pZip").val()
	var phone = $(".pMPhone").val()
	var payload = {}
	
	payload["id"] = id
	payload["address1"] = address1
	payload["address2"] = address2
	payload["city"] = city
	payload["zip"] = zip
	payload["phone"] = phone
	payload["state"] = state
	
	$.ajax({
		url: '../updateProviderMailingDetails',
		type: 'POST',
		data: payload,
		success: function(data) {
			console.log(data)
		},
		error: function(data) {
			console.log("failed to update mailing details")
		}
	})
}

function editProviderInformation(){
	
	
	 var selectedRegions = []; // Clear any previous selections
        document.querySelectorAll('input[type="checkbox"]:checked').forEach(checkbox => {
            selectedRegions.push(parseInt(checkbox.value));
        });

	
    var regions = selectedRegions.join(',')
	var id = $(".pId").val()
	var fName =  $(".pFirstName").val()
	var lName = $(".pLastName").val()
	var phone = $(".pPhone").val()
	var license = $(".pLicense").val()
	var npi = $(".pNPI").val()
	var syncMail = $(".pSyncEmail").val()
	
	var payload = {}
	
	payload["regions"] = regions
	payload["id"] = id
	payload["fName"] = fName
	payload["lName"] = lName
	payload["phone"] = phone
	payload["license"] = license
	payload["npi"] = npi
	payload["syncMail"] = syncMail
	
	$.ajax({
		url: '../updateProviderInfoDetails',
		type: 'POST',
		data: payload,
		success: function(data) {
			console.log(data)
		},
		error: function(data) {
			console.log("failed to update info details")
		}
	})
	
}

function resetPassword(){
	var x = document.getElementById("edit-btn-1");
	var pass = document.getElementsByClassName("pPassword");
	var resetPassBtn = document.getElementById("password-btn-12")
	var saveBtn = document.getElementById("save-btn-2")
	var cancelBtn = document.getElementById("cancel-btn-2")
	
	 for(var i=0; i<pass.length;i++){
        pass[i].disabled=false;
    }
    
    resetPassBtn.style.display = "none";
    saveBtn.style.display = "block";
    cancelBtn.style.display = "block";
    x.style.display = "none";
}

function savePassword(){
	
	var x = document.getElementById("edit-btn-1");
	var pass = document.getElementsByClassName("pPassword");
	var resetPassBtn = document.getElementById("password-btn-12")
	var saveBtn = document.getElementById("save-btn-2")
	var cancelBtn = document.getElementById("cancel-btn-2")
	
	 for(var i=0; i<pass.length;i++){
        pass[i].disabled=true;
    }
    
    resetPassBtn.style.display = "block";
    saveBtn.style.display = "none";
    cancelBtn.style.display = "none";
    x.style.display = "block";
	
}

function editInformationDetails(){
	var save = document.getElementById("mailing-save")
	var cancel = document.getElementById("mailing-cancel")
	var edit = document.getElementById("mailing-edit")
	var comms =  document.getElementsByClassName("common-class-info");
	
	 for(var i=0; i<comms.length;i++){
        comms[i].disabled=false;
    }
    
    edit.style.display = "none"
    cancel.style.display = "block"
    save.style.display = "block"
    
}

function saveInformationDetails(){
	var save = document.getElementById("mailing-save")
	var cancel = document.getElementById("mailing-cancel")
	var edit = document.getElementById("mailing-edit")
	var comms =  document.getElementsByClassName("common-class-info");
	
	 for(var i=0; i<comms.length;i++){
        comms[i].disabled=true;
    }
    
    edit.style.display = "block"
    cancel.style.display = "none"
    save.style.display = "none"
    
}

function editMailingDetails(){
	var save = document.getElementById("billing-save")
	var cancel = document.getElementById("billing-cancel")
	var edit = document.getElementById("billing-edit")
	var comms =  document.getElementsByClassName("common-class-mailing");
	
	 for(var i=0; i<comms.length;i++){
        comms[i].disabled=false;
    }
    
    edit.style.display = "none"
    cancel.style.display = "block"
    save.style.display = "block"
}

function saveMailingDetails(){
	var save = document.getElementById("billing-save")
	var cancel = document.getElementById("billing-cancel")
	var edit = document.getElementById("billing-edit")
	var comms =  document.getElementsByClassName("common-class-mailing");
	
	 for(var i=0; i<comms.length;i++){
        comms[i].disabled=true;
    }
    
    edit.style.display = "block"
    cancel.style.display = "none"
    save.style.display = "none"
}
