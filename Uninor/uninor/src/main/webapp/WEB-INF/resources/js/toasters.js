function showToast(show,message) {

	if(show){
	$(".toaster-custom-message").text(message)
	var toast = document.querySelector(".toaster");
	var close = document.querySelector(".toaster-close");
	var progress = document.querySelector(".progress");
	toast.style.display = "block";
	toast.classList.add("active");
	progress.classList.add("active");
	setTimeout(() => {
		toast.classList.remove("active");
		toast.style.transition = "all 0.5s cubic-bezier(0.68, -0.55, 0.25, 1.35)";
		toast.style.transform = "translateX(calc(100% + 30px))";
		toast.style.display = "none";
	}, 5000)

	setTimeout(() => {
		progress.classList.remove("active");
	}, 5300)


	close.addEventListener("click", () => {
		toast.classList.remove("active");
		toast.style.transition = "all 0.5s cubic-bezier(0.68, -0.55, 0.25, 1.35);"
		toast.style.transform = "translateX(calc(100% + 30px))";
		toast.style.display = "none";
		setTimeout(() => {
			progress.classList.remove("active");
		}, 300)
	})

}	
		
	}
	