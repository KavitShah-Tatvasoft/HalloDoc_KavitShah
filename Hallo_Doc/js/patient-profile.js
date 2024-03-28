function editPatientDetails(){

    var edit = document.getElementById("edit-btn-pdashboard");
    var save = document.getElementById("save-btn-pdashboard");
    var cancel = document.getElementById("cancel-btn-pdashboard");

    if(edit.style.display == "block"){
        save.style.display = "block";
        cancel.style.display = "block";
        edit.style.display = "none";
    }

    else{
        save.style.display = "none";
         cancel.style.display = "none";
        edit.style.display = "block";
    }
}