function showAlert(bool,message,type) {
    var toast
    var toast_progress
    var closeIcon
    if(type == "success"){
        toast = document.querySelector(".toast-success");
        $(".toaster-success-message").text(message)
        toast_progress = document.querySelector(".toast-success-progress")
        closeIcon = document.querySelector(".success-close")
    }else{
        toast = document.querySelector(".toast-failure");
        $(".toaster-failure-message").text(message)
        toast_progress = document.querySelector(".toast-failure-progress")
        closeIcon = document.querySelector(".failure-close")
    }



    let timer1, timer2;

    toast.classList.add("active");
    toast_progress.classList.add("active");

    timer1 = setTimeout(() => {
        toast.classList.remove("active");
    }, 10000); //1s = 1000 milliseconds

    timer2 = setTimeout(() => {
        toast_progress.classList.remove("active");
    }, 10300);

    closeIcon.addEventListener("click", () => {
        toast.classList.remove("active");

        setTimeout(() => {
            toast_progress.classList.remove("active");
        }, 300);

        clearTimeout(timer1);
        clearTimeout(timer2);
    });
}
