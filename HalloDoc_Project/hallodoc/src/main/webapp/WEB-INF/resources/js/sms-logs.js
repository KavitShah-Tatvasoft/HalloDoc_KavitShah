function clearFilter() {
	$(".filter-by-role").prop('selectedIndex', 0);
	$(".filter-by-name").val("")
	$(".filter-by-email").val("")
	$(".filter-by-create-date").val("")
	$(".filter-by-sent-date").val("")

	filterSMSRecords()
}

function changeActivePage(element) {
	debugger
	$(".add-active").removeClass("active")
	console.log(element)

	element.classList.add('active');
	filterSMSRecords(false)
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


function filterSMSRecords(bool) {

	debugger
	var roleId = $(".filter-by-role").val()
	var reciever = $(".filter-by-name").val()
	var email = $(".filter-by-email").val()
	var createdDate = $(".filter-by-create-date").val()
	var sentDate = $(".filter-by-sent-date").val()
	if (bool == true) {
		var pageNo = 1
	} else {
		var pageNo = $(".page-link.active").attr("data-pg")
	}

	
	var payload = {}
	payload["roleId"] = roleId
	payload["reciever"] = reciever
	payload["email"] = email
	payload["createdDate"] = createdDate
	payload["sentDate"] = sentDate
	payload["pageNo"] = pageNo
	tbody = $(".sms-log-tbody")
	accordionBody = $(".empty-sms-accordion")

	$.ajax({
		url: 'getFilteredSMSLogData',
		type: 'POST',
		data: payload,
		success: function(res) {


			tbody.empty()
			accordionBody.empty()
			var count = 121;

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


			res.smsLogDashboardDataDtos.forEach(function(data) {
				count++;
				var card = $(".sms-log-tr-clone").clone().removeClass("d-none").removeClass("sms-log-tr-clone")
				card.find(".name-td").text(data.recipientName)
				card.find(".action-td").text(data.action)
				card.find(".role-td").text(data.roleName)
				card.find(".phone-td").text(data.mobileNumber)
				card.find(".created-date-td").text(data.createdDate)
				card.find(".send-date-td").text(data.sendDate)
				card.find(".sent-sms-td").text(data.isEmailSent)
				card.find(".send-tries-td").text(data.sentTries)
				card.find(".conf-td").text(data.confNumber)
				tbody.append(card)

				var accordionCard = $(".accordion-clone-class").clone().removeClass("d-none").removeClass("accordion-clone-class")
				accordionCard.find(".change-id").attr("id", "accordion" + count)
				accordionCard.find(".change-target").attr("data-bs-target", "#accordion" + count)
				accordionCard.find(".acc-name").text(data.recipientName)
				accordionCard.find(".acc-action").text(data.action)
				accordionCard.find(".acc-role").text(data.roleName)
				accordionCard.find(".acc-mobile").text(data.mobileNumber)
				accordionCard.find(".acc-created").text(data.createdDate)
				accordionCard.find(".acc-sent").text(data.sendDate)
				accordionCard.find(".acc-sent-tries").text(data.sentTries)
				accordionCard.find(".acc-sent-bool").text(data.isEmailSent)
				accordionCard.find(".acc-conf").text(data.confNumber)
				accordionBody.append(accordionCard)
			})

		},
		error: function(data) {

		}
	})

}