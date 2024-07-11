function showError() {

    if (localStorage.getItem("showError") == "true") {

        $(".error-code").html(localStorage.getItem("errorCode"))
        $(".error-type").html(localStorage.getItem("errorType"))
        $(".error-message").html(localStorage.getItem("errorMessgae"))

        localStorage.setItem("showError", "false")
    }

}