function changeActivePage(element) {
	debugger
	$(".add-active").removeClass("active")
	console.log(element)

	element.classList.add('active');
	filterReviewShifts(false)
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

function convertTo12HourFormat(time) {
	var [hours, minutes] = time.split(":");
	let ampm = "AM";
	if (hours >= 12) {
		ampm = "PM";
		if (hours > 12) {
			hours -= 12;
		}
	}
	if (hours == 0) {
		hours = 12;
	}
	return `${hours.toString().padStart(2, "0")}:${minutes} ${ampm}`;
}

function approveSelected() {
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
	var values = [];
	for (var i = 0; i < checkboxes.length; i++) {
		values.push(checkboxes[i].value);
	}

	$.ajax({
		url: 'approve-selected-shifts',
		type: 'POST',
		contentType: "application/json",
		data: JSON.stringify(values),

		success: function(data) {

			console.log("approve-selected")
			filterReviewShifts(true)
		},
		error: function(data) {
			console.log("failure approve-selected")
		}
	})

}

function deleteSelected() {

	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
	var values = [];
	for (var i = 0; i < checkboxes.length; i++) {
		values.push(checkboxes[i].value);
	}

	$.ajax({
		url: 'delete-selected-shifts',
		type: 'POST',
		contentType: "application/json",
		data: JSON.stringify(values),

		success: function(data) {

			console.log("deleted-selected")
			filterReviewShifts(true)
		},
		error: function(data) {
			console.log("failure deleted-selected")
		}
	})


}

function filterReviewShifts(bool) {
	debugger
	var regionId = $(".region-review-shift").val()

	if (bool == true) {
		var pageNo = 1
	} else {
		var pageNo = $(".page-link.active").attr("data-pg")
	}

	var payload = {}
	payload["regionId"] = parseInt(regionId)
	payload["pageNo"] = parseInt(pageNo)

	var tbody = $(".tbody-empty")
	var accordionBody = $(".empty-accordion-body")

	$.ajax({
		url: 'get-review-shift-details',
		type: 'POST',
		data: payload,
		success: function(res) {

			let paginationBody = $(".empty-pagination")
			paginationBody.empty()
			accordionBody.empty()
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

			tbody.empty()
			console.log(res.reviewShiftDetailsDto)
			res.reviewShiftDetailsDto.forEach(function(data) {

				var row = $(".clone-tr-review").clone(true).removeClass("d-none").removeClass("clone-tr-review")
				row.find(".checkbox-review").attr("value", data.shiftDetailId)
				row.find(".physician-name-review").text(data.physicainName)
				row.find(".shift-date-review").text(data.shiftDate)
				row.find(".shift-time-review").text(convertTo12HourFormat(data.startTime) + " - " + convertTo12HourFormat(data.endTime))
				row.find(".shift-region-review").text(data.regionName)
				tbody.append(row)

				var card = $(".clone-accordion-card").clone(true).removeClass("d-none").removeClass("clone-accordion-card")
				card.find(".checkbox-review").attr("value", data.shiftDetailId)
				card.find(".physician-name-review").text(data.physicainName)
				card.find(".shift-date-review").text(data.shiftDate)
				card.find(".shift-time-review").text(convertTo12HourFormat(data.startTime) + " - " + convertTo12HourFormat(data.endTime))
				card.find(".shift-region-review").text(data.regionName)
				accordionBody.append(card)
			})

			console.log("review-shift")
		},
		error: function(data) {
			console.log("failed review-shift")
		}
	})
}