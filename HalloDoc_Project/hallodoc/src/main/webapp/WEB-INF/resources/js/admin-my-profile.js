function editAdminDetails1(){
    var x = document.getElementsByClassName("admin-details-row-1");
    var y = document.getElementById("edit-btn-id");
    var z = document.getElementById("save-reset-btns-id");


    y.style.display="none";
    z.style.display="flex";

    for(var i=0; i<x.length;i++){
        x[i].disabled=false;
    }
}

function saveAdminDetails1(){
    var x = document.getElementsByClassName("admin-details-row-1");
    var y = document.getElementById("edit-btn-id");
    var z = document.getElementById("save-reset-btns-id");


    z.style.display="none";
    y.style.display="flex";

    for(var i=0; i<x.length;i++){
        x[i].disabled=true;
    }
}   

function cancelAdminDetails1(){
    var x = document.getElementsByClassName("admin-details-row-1");
    var y = document.getElementById("edit-btn-id");
    var z = document.getElementById("save-reset-btns-id");


    z.style.display="none";
    y.style.display="flex";

    for(var i=0; i<x.length;i++){
        x[i].disabled=true;
    }
}  


function resetPassword(){
    var x = document.getElementsByClassName("admin-reset-pass");
    var y = document.getElementById("reset-btn-bottom");
    var z = document.getElementById("reset-pass-cancel-id");


    y.style.display="none";
    z.style.display="flex";

    for(var i=0; i<x.length;i++){
        x[i].disabled=false;
    }
}

function changePassword(){
    var x = document.getElementsByClassName("admin-reset-pass");
    var y = document.getElementById("reset-btn-bottom");
    var z = document.getElementById("reset-pass-cancel-id");


    z.style.display="none";
    y.style.display="flex";

    for(var i=0; i<x.length;i++){
        x[i].disabled=true;
    }
}

function cancelChange(){
    var x = document.getElementsByClassName("admin-reset-pass");
    var y = document.getElementById("reset-btn-bottom");
    var z = document.getElementById("reset-pass-cancel-id");


    z.style.display="none";
    y.style.display="flex";

    for(var i=0; i<x.length;i++){
        x[i].disabled=true;
    }
}

function editAdminDetails2(){
    var x = document.getElementsByClassName("admin-details-row-2");
    var y = document.getElementById("edit-btn-id-1");
    var z = document.getElementById("save-reset-btns-id-1");


    y.style.display="none";
    z.style.display="flex";

    for(var i=0; i<x.length;i++){
        x[i].disabled=false;
    }
}

function saveAdminDetails2(){
    var x = document.getElementsByClassName("admin-details-row-2");
    var y = document.getElementById("edit-btn-id-1");
    var z = document.getElementById("save-reset-btns-id-1");


    z.style.display="none";
    y.style.display="flex";

    for(var i=0; i<x.length;i++){
        x[i].disabled=true;
    }
}   

function cancelAdminDetails2(){
    var x = document.getElementsByClassName("admin-details-row-2");
    var y = document.getElementById("edit-btn-id-1");
    var z = document.getElementById("save-reset-btns-id-1");


    z.style.display="none";
    y.style.display="flex";

    for(var i=0; i<x.length;i++){
        x[i].disabled=true;
    }
}  
