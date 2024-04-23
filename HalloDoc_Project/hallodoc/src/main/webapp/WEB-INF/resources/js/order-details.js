function getVendors() {

	debugger
	var id = $('.profession-type-select option:selected').val()

	$.ajax({
		url: '../getVendorDetails',
		type: 'POST',
		data: {
			professionTypeId: id
		},
		success: function(res) {
			console.log("vendor data obtained")
			console.log(res)

			$(".business-type-select-class").empty()
			$(".business-type-select-class").append("<option value='0' hidden >Select Business</option>")
			$("#business-contact-orders").attr("value", "")
			$("#business-email-orders").attr("value", "")
			$("#business-fax-orders").attr("value", "")

			res.forEach(function(data) {
				$(".business-type-select-class").append("<option value='" + data.vendorId + "'>" + data.vendorName + "</option>")
			})

		},
		error: function(data) {
			console.log("failed to obtian vendor data")
		}
	})
}

function getSelectedVendorDetails() {

	var id = $('.business-type-select-class option:selected').val()

	$.ajax({
		url: '../getSelectedVendorDetails',
		type: 'POST',
		data: {
			vendorId: id
		},
		success: function(res) {
			console.log("vendor details obtained")
			console.log(res)

			$("#business-contact-orders").attr("value", res.businessContact)
			$("#business-email-orders").attr("value", res.businessEmail)
			$("#business-fax-orders").attr("value", res.businessFax)


		},
		error: function(data) {
			console.log("failed to obtian vendor details")
		}
	})

}

//$("#sendOrderForm").submit(function(event) {
//	event.preventDefault();
//	var pid = $('.profession-type-select option:selected').val()
//	var id = $('.business-type-select-class option:selected').val()
//
//	if (pid == 0) {
//		$('.profession-type-select').focus()
//	}
//
//	if (id == 0) {
//		$('.business-type-select-class').focus()
//	}
//
//
//})