function clearFilter() {
	$(".record-first-name").val("")
	$(".record-last-name").val("")
	$(".record-email").val("")
	$(".record-phone").val("")

	filterPatientHistory(true)

}

function changeActivePage(element) {
	debugger
	$(".add-active").removeClass("active")
	console.log(element)

	element.classList.add('active');
	filterPatientHistory(false)
}

function prevPage() {
	debugger
	var pageNo = $(".page-link.active").attr("data-pg")
	const prevPage = pageNo - 1;
	const prevLink = document.querySelector(`.page-link[data-pg="${prevPage}"]`);
	if (prevLink) {
		prevLink.click();
	}
}

function nextPage() {
	debugger
	var pageNo = $(".page-link.active").attr("data-pg")
	const nextPage = parseInt(pageNo) + 1;
	const nextLink = document.querySelector(`.page-link[data-pg="${nextPage}"]`);
	if (nextLink) {
		nextLink.click();
	}
}


function filterPatientHistory(bool) {

	var firstName = $(".record-first-name").val()
	var lastName = $(".record-last-name").val()
	var email = $(".record-email").val()
	var phoneNumber = $(".record-phone").val()

	if (bool == true) {
		var pageNo = 1
	} else {
		var pageNo = $(".page-link.active").attr("data-pg")
	}


	var payload = {}

	payload["firstName"] = firstName
	payload["lastName"] = lastName
	payload["email"] = email
	payload["phoneNumber"] = phoneNumber
	payload["pageNo"] = pageNo

	var tbody = $(".patient-history-empty-tbody")
	var accordionBody = $(".accordion-body-empty")
	$.ajax({
		url: 'getPatientHistoryData',
		type: 'POST',
		data: payload,
		success: function(res) {


			tbody.empty()
			accordionBody.empty()
			var count = 121

			let paginationBody = $(".empty-pagination")
			paginationBody.empty()
			var prev = $(".prev-navigation").clone().removeClass("prev-navigation").removeClass("d-none")
			var next = $(".next-pagination").clone().removeClass("next-pagination").removeClass("d-none")
			paginationBody.append(prev)

			var totalPageNumber = Math.ceil(res.count / 10)

			for (let i = 1; i <= totalPageNumber; i++) {
				paginationNumber = $(".pageno-pagination").clone().removeClass("pageno-pagination").removeClass("d-none")
				paginationNumber.find(".page-link").text(i)
				paginationNumber.find(".page-link").attr("data-pg", i)
				paginationNumber.find(".page-link").attr("onclick", "changeActivePage(this)")
				if (i == pageNo) {
					paginationNumber.find(".add-active").addClass("active")
				}

				paginationBody.append(paginationNumber)
			}

			if (pageNo == 1) {
				prev.addClass("disabled")
			} else {
				prev.removeClass("disabled")
			}

			if (pageNo == totalPageNumber) {
				next.addClass("disabled")
			} else {
				next.removeClass("disabled")
			}

			paginationBody.append(next)

			if (totalPageNumber == 1 || totalPageNumber == 0) {
				prev.addClass("disabled")
				next.addClass("disabled")
			}


			res.patientHistoryDtos.forEach(function(data) {
				count++
				var card = $(".patient-history-tr").clone().removeClass("d-none").removeClass("patient-history-tr")
				card.find(".pt-fname-td").text(data.firstName)
				card.find(".pt-l-name-td").text(data.lastName)
				card.find(".pt-email-td").text(data.email)
				card.find(".pt-phone-td").text(data.phoneNumber)
				card.find(".pt-address-td").text(data.address)
				card.find(".pt-explore-td").attr("href", "explore/" + data.userId)
				tbody.append(card)

				var accordionCard = $(".patient-history-accordion-clone").clone().removeClass("d-none").removeClass("patient-history-accordion-clone")
				accordionCard.find(".acc-name").text(data.firstName + " " + data.lastName)
				accordionCard.find(".acc-number").text(data.phoneNumber)
				accordionCard.find(".acc-email").text(data.email)
				accordionCard.find(".acc-phone").text(data.phoneNumber)
				accordionCard.find(".acc-change-target-class").attr("data-bs-target", "#accordion" + count)
				accordionCard.find(".acc-change-id").attr("id", "accordion" + count)
				accordionCard.find(".accordion-explore-case").attr("href", "explore/" + data.userId)
				accordionBody.append(accordionCard)
			})
		},
		error: function(res) {

		}
	})
}