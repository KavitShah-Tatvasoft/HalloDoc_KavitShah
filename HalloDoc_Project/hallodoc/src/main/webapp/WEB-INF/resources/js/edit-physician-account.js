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
