const CONTEXT_PATH = "/uninor"

function logoutUser(){

    $.ajax({

        url: CONTEXT_PATH + '/logout',
        type: 'GET',
        dataType: 'json',
        success: function (xhr,status,error) {
            localStorage.clear()
            localStorage.setItem("showLogoutMessage","true")
            window.location.assign(CONTEXT_PATH + "/login")
        },
        error: function(xhr, status, error) {
            hideLoader()
            debugger
            if (xhr.status === 401 || xhr.status === 400 || xhr.status === 409) {
                let errorResponse;
                try {
                    errorResponse = JSON.parse(xhr.responseText);
                } catch (e) {
                    errorResponse = xhr.responseText;
                }
                console.log(errorResponse)
                if (errorResponse.errors) {
                    let errorMessage = errorResponse.errors
                    showAlert(true,errorMessage,"failure")
                }
            }
            else {
                showAlert(true,"Some Error Ocurred!","failure")
            }
        }
    })

}

function logoutAdmin(){

    $.ajax({
        url: CONTEXT_PATH + '/logout',
        type: 'GET',
        dataType: 'json',
        success: function (xhr,status,error) {
            localStorage.clear()
            localStorage.setItem("showLogoutMessage","true")
            window.location.assign(CONTEXT_PATH + "/admin-login")
        },
        error: function(xhr, status, error) {
            hideLoader()
            debugger
            if (xhr.status === 401 || xhr.status === 400 || xhr.status === 409) {
                let errorResponse;
                try {
                    errorResponse = JSON.parse(xhr.responseText);
                } catch (e) {
                    errorResponse = xhr.responseText;
                }
                console.log(errorResponse)
                if (errorResponse.errors) {
                    let errorMessage = errorResponse.errors
                    showAlert(true,errorMessage,"failure")
                }
            }
            else {
                showAlert(true,"Some Error Occurred!","faliure")
            }
        }
    })

}