function editDetails(){
    var x = document.getElementsByClassName("patient-details");
    var y = document.getElementById("save-cancel-details-btn-id");
    var z = document.getElementById("edit-details-id");


    y.style.display="flex";
    z.style.display="none";

    for(var i=0; i<x.length;i++){
        x[i].disabled=false;
    }
}


function saveDetails(){
    var x = document.getElementsByClassName("patient-details");
    var y = document.getElementById("save-cancel-details-btn-id");
    var z = document.getElementById("edit-details-id");


    y.style.display="none";
    z.style.display="block";

    for(var i=0; i<x.length;i++){
        x[i].disabled=true;
    }
}


function cancelDetails(){
    var x = document.getElementsByClassName("patient-details");
    var y = document.getElementById("save-cancel-details-btn-id");
    var z = document.getElementById("edit-details-id");


    y.style.display="none";
    z.style.display="block";

    for(var i=0; i<x.length;i++){
        x[i].disabled=true;
    }
}