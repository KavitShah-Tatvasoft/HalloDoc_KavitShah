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


