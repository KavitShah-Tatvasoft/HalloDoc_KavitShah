const pAgreement = document.getElementById("upload-1");
const pAgreementViewBtn = document.getElementById("pAgreement-view-btn");

pAgreement.addEventListener("change", () => {
	pAgreementViewBtn.style.display = "block";
});

pAgreementViewBtn.addEventListener("click", (e) => {
	debugger
	e.preventDefault();
	const file = pAgreement.files[0];
	const objUrl = URL.createObjectURL(file);
	const newTab = window.open(objUrl, "_blank");
	newTab.focus();
});

const pHipaa = document.getElementById("upload-2");
const pHipaaViewBtn = document.getElementById("pHipaa-view-btn");

pHipaa.addEventListener("change", () => {
	pHipaaViewBtn.style.display = "block";
});

pHipaaViewBtn.addEventListener("click", (e) => {
	debugger
	e.preventDefault();
	const file = pHipaa.files[0];
	const objUrl = URL.createObjectURL(file);
	const newTab = window.open(objUrl, "_blank");
	newTab.focus();
});

const pBackgroundCheck = document.getElementById("upload-3");
const pBackgroundCheckViewBtn = document.getElementById("pBackgroundCheck-view-btn");

pBackgroundCheck.addEventListener("change", () => {
	pBackgroundCheckViewBtn.style.display = "block";
});

pBackgroundCheckViewBtn.addEventListener("click", (e) => {
	e.preventDefault();
	const file = pBackgroundCheck.files[0];
	const objUrl = URL.createObjectURL(file);
	const newTab = window.open(objUrl, "_blank");
	newTab.focus();
});

const pNda = document.getElementById("upload-4");
const pNdaViewBtn = document.getElementById("pNda-view-btn");

pNda.addEventListener("change", () => {
	pNdaViewBtn.style.display = "block";
});

pNdaViewBtn.addEventListener("click", (e) => {
	e.preventDefault();
	const file = pNda.files[0];
	const objUrl = URL.createObjectURL(file);
	const newTab = window.open(objUrl, "_blank");
	newTab.focus();
});

const pLicense = document.getElementById("upload-5");
const pLicenseViewBtn = document.getElementById("pLicense-view-btn");

pLicense.addEventListener("change", () => {
	pLicenseViewBtn.style.display = "block";
});

pLicenseViewBtn.addEventListener("click", (e) => {
	e.preventDefault();
	const file = pLicense.files[0];
	const objUrl = URL.createObjectURL(file);
	const newTab = window.open(objUrl, "_blank");
	newTab.focus();
});



function checkUsername() {

	debugger
	var x = $(".pUsername").val()

	$.ajax({
		url: 'checkUsername',
		type: 'POST',
		data: {
			name: x
		},
		success: function(data) {

			if (data == "false") {
				document.getElementById("create-provider-btn").disabled = true

			}
			else {
				document.getElementById("create-provider-btn").disabled = false
			}
		}, error: function(data) {

		}
	})

}

function getCheckBoxDetails() {
	debugger
	var selectedRegions = [];
	document.querySelectorAll('input[type="checkbox"]:checked').forEach(checkbox => {
		selectedRegions.push(parseInt(checkbox.value));
	});

	$(".pRegions").attr("value", selectedRegions.join(','))

}

