function clearFilters() {
	$(".filter-name").val("")
	$(".filter-date").val("")
	$(".filter-email").val("")
	$(".filter-phone").val("")

	getFilteredBlockData()
}

function unblockCase(blockId) {

	$.ajax({
		url: 'unblockCase',
		type: 'POST',
		data: {

			blockId: blockId

		},
		success: function(res) {

			getFilteredBlockData()
		},
		error: function(res) {

		}
	})
}

function changeActivePage(element) {
	debugger
	$(".add-active").removeClass("active")
	console.log(element)

	element.classList.add('active');
	getFilteredBlockData(false)
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


function getFilteredBlockData(bool) {

	var name = $(".filter-name").val()
	var date = $(".filter-date").val()
	var email = $(".filter-email").val()
	var phone = $(".filter-phone").val()

	if (bool == true) {
		var pageNo = 1
	} else {
		var pageNo = $(".page-link.active").attr("data-pg")
	}

	var payload = {}
	payload["name"] = name
	payload["date"] = date
	payload["email"] = email
	payload["phone"] = phone
	payload["pageNo"] = pageNo

	var accordionBody = $(".empty-accordion-class")
	var tbody = $(".empty-tbody-block")

	$.ajax({
		url: 'getBlockHistoryData',
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


			res.blockRequestsTableDatas.forEach(function(data) {
				count++
				var card = $(".clone-block-tr").clone().removeClass("d-none").removeClass("clone-block-tr")
				card.find(".bname-td").text(data.patientName)
				card.find(".bnumber-td").text(data.phoneNumber)
				card.find(".bemail-td").text(data.email)
				card.find(".bdate-td").text(data.createdDate)
				card.find(".bnotes-td").text(data.notes)
				card.find(".bunblock-td").attr("onclick", "unblockCase(" + data.requestId + ")")
				if (data.isActive == true) {
					card.find(".bactive-td").prop('checked', true);
					card.find(".bunblock-td").hide()
				}
				tbody.append(card)

				var accordionCard = $(".clone-accordion-class").clone().removeClass("d-none").removeClass("clone-accordion-class")
				accordionCard.find(".acc-name").text(data.patientName)
				accordionCard.find(".acc-email").text(data.email)
				accordionCard.find(".acc-phone").text(data.phoneNumber)
				accordionCard.find(".acc-create-date").text(data.createdDate)
				accordionCard.find(".acc-notes").text(data.notes)
				accordionCard.find(".change-target-class").attr("data-bs-target", "#accordion" + count)
				accordionCard.find(".change-id-class").attr("id", "accordion" + count)
				if (data.isActive == true) {
					accordionCard.find(".acc-active").text("Yes")
					accordionCard.find(".acc-unblock").hide()
				} else {
					accordionCard.find(".acc-active").text("No")
					accordionCard.find(".acc-unblock").attr("onclick", "unblockCase(" + data.requestId + ")")
				}

				accordionBody.append(accordionCard)

			})
		},
		error: function(res) {



		}



	})

}