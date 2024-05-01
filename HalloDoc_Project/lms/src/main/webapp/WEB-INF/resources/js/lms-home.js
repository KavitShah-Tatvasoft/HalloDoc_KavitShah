function checkBook() {

	var book = $(".book-name").val()

	$.ajax({
		url: 'getBookAvaibility',
		type: 'POST',
		data: {
			bookName: book
		},
		success: function(data) {
			  
			  
			if (data == "false") {
				$(".book-name-error").text = "No such book available."
			} else {
				$(".book-name-error").text = ""
				$(".author").val(data)
			}


		},
		error: function(data) {
			  

		}
	})

}

function checkBorrower() {

	var name = $(".borrower-name").val()

	$.ajax({
		url: 'getOldBorrower',
		type: 'POST',
		data: {
			name: name
		},
		success: function(data) {
			  
			  
			$(".hidden-borrower-id").val(data)

		},
		error: function(data) {
			  
		}
	})
}

$("#lms-form").submit(function(event) {
	debugger
	event.preventDefault();
	var borrowerName = $(".borrower-name").val()
	var bookName = $(".book-name").val()
	var authorName = $(".author").val()
	var dateOfIssuance = $(".date-of-issuance").val()
	var genere = $(".genere-book").val()
	var city = $(".borrower-city").val()
	var borrowerId = $(".hidden-borrower-id").val()
	var payload = {}

	payload["borrowerName"] = borrowerName
	payload["bookName"] = bookName
	payload["authorName"] = authorName
	payload["dateOfIssuance"] = dateOfIssuance
	payload["genere"] = genere
	payload["city"] = city
	payload["borrowerId"] = borrowerId

	$(".cancel-btn").click()
	$.ajax({
		url: 'addBookRecord',
		type: 'POST',
		data: payload,
		success: function(data) {
			  
			  
			$("#lms-form").reset()

		},
		error: function(data) {
			  
		}
	})

})


//function getInitialData(){
//	
//	$.ajax({
//		url: 'getBookRecordData',
//		type: 'POST',
//		success: function(res) {
//			  
//			  
//		$(".tbody-empty").empty()
//		var tbody = $(".tbody-empty")
//		res.forEach(function(data){
//			
//			
//		} )
//	
//		},
//		error: function(data) {
//			  
//		}
//	})
//	
//}


function updateRecord(id){
	
	
		$.ajax({
		url: 'getUpdateData',
		type: 'POST',
		data: {
			id:id
		},
		success: function(res) {
	$(".borrower-name-1").val(res.borrowerName)
	$(".book-name-1").val(res.bookName)
	$(".author-1").val(res.author)
	$(".date-of-issuance-1").val(res.dateOfIssuance)
	$(".genere-book-1").val(res.genere)
	$(".borrower-city-1").val(res.city)
//	$(".hidden-borrower-id").val(res.borrowerName)
	
		},
		error: function(data) {
			  
		}
	})
	
}

$("#lms-form-1").submit(function(event) {
	debugger
	event.preventDefault();
	var borrowerName = $(".borrower-name-1").val()
	var bookName = $(".book-name-1").val()
	var authorName = $(".author-1").val()
	var dateOfIssuance = $(".date-of-issuance-1").val()
	var genere = $(".genere-book-1").val()
	var city = $(".borrower-city-1").val()
	var payload = {}

	payload["borrowerName"] = borrowerName
	payload["bookName"] = bookName
	payload["authorName"] = authorName
	payload["dateOfIssuance"] = dateOfIssuance
	payload["genere"] = genere
	payload["city"] = city

	$(".cancel-btn").click()
	$.ajax({
		url: 'updateTheRecord',
		type: 'POST',
		data: payload,
		success: function(data) {
			  
			  
			$("#lms-form-1").reset()

		},
		error: function(data) {
			  
		}
	})

})