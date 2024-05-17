<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="common-navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/pop-ups.css' />">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet"
	href="<c:url value='/resources/css/scheduling-day.css' />" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
<title>Scheduling Month</title>
</head>
<body onload="setMonthDate()">
	<div
		class="container-fluid patient-form p-3 rounded relative-position extra-margin">
		<div class="back-btn-top" role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt="" /> Back
		</div>

		<div class="submit-info-txt">Scheduling</div>

		<div class="all-region-btns-flex">
			<select onchange="regionFilter()"
				class="form-select select-scheduling select-scheduling-region"
				aria-label="Default select example">
				<option value="0">All Regions</option>
				<c:forEach items="${regionList }" var="region">
					<option value="${region.regionId }">${region.name }</option>
				</c:forEach>
			</select>

			<div class="btns-flex">
				<a role="button" href="${pageContext.request.contextPath}/admin/provider-on-call" class="three-btns">Provider
					On Call</a> <a role="button" href="${pageContext.request.contextPath}/admin/review-shift"
					class="three-btns">Shift for Review</a> <a role="button"
					onclick="hideShiftRepeatDetials()" class="three-btns"
					data-bs-toggle="modal" data-bs-target="#create-shift">Add New
					Shifts</a>
			</div>
		</div>

		<div class="show-date mt-4">
			<span class="current-date-class">Monday, 4 December, 2023</span>
		</div>

		<div class="type-shift">
			<div class="pending-shift"></div>
			<span class="pending-shift-text">Pending Shift</span>
			<div class="approved-shift"></div>
			<span class="approved-shift-text">Approved Shift</span>
		</div>

		<div class="date-change-type-flex">
			<div class="date-change-flex">
				<a
					href="javascript:dp.startDate = dp.startDate.addMonths(-1); dp.update();setMonthDate();">
					<img src="<c:url value='/resources/images/back.png' />"
					class="arrow-img">
				</a>

				<!-- <a href="#" id="change"> -->
				<!-- <img src="./SRS Screen Shorts/calendar4-week-blue.svg" class="calendar-img" alt=""> -->
				<!-- </a> -->

				<a href="#" id="change"> <img
					src="<c:url value='/resources/images/calendar4-week-blue.svg' />"
					class="calendar-img" alt="" />
				</a><a
					href="javascript:dp.startDate = dp.startDate.addMonths(1); dp.update();setMonthDate();">
					<img src="<c:url value='/resources/images/next.png' />"
					class="arrow-img">
				</a>
			</div>

			<div class="days-week-month-flex">
				<a href="${pageContext.request.contextPath}/admin/scheduling-day"
					role="button" class="calendar-type-btn">Day</a> <a
					href="${pageContext.request.contextPath}/admin/scheduling-week" role="button"
					class="calendar-type-btn ">Week</a> <a href="${pageContext.request.contextPath}/admin/scheduling-month"
					role="button" class="calendar-type-btn calendar-active">Month</a>
			</div>
		</div>

		<!-- <h1><span id="start"></span> Change</h1> -->

		<span id="start" class="hide"></span>

		<!--            
           <div class="tools">
            <span id="start" class="hide"></span>
           </div>   -->

		<div class="main">
			<div id="dp"></div>
		</div>



	</div>


	<footer>
		<div class="footer-flex">
			<a href="#">Terms of Condition</a><span>|</span><a href="#">Privacy
				Policy</a>
		</div>
	</footer>

	<div class="modal fade" id="accept" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content cancel-pop-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">View
						Shift</h3>
					<button type="button" class="btn-close dismiss-button"
						data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="physician-unavailable-edit-div d-none">
						<em class="physician-unavailable-error">Physician is not
							available at the time current shift is selected.</em>
					</div>
					<form action="">
						<div class="col-12">
							<div class="form-floating mb-3 inp">
								<select name="Number_Type" id="floatingInput-5" disabled
									class="form-control form-select input-2 view-shift-inp">
									<option class="region-details-edit-shift" value=""></option>
								</select> <label for="floatingInput-5" class="view-shift-label">Region</label>
							</div>
						</div>

						<div class="col-12">
							<div class="form-floating mb-3 inp">
								<select name="Number_Type" id="floatingInput-5" disabled
									class="form-control form-select input-2 view-shift-inp">
									<option class="physician-details-edit-shift" value=""></option>
								</select> <label for="floatingInput-5" class="view-shift-label">Physician</label>
							</div>

							<div class="col-12">
								<div class="form-floating mb-3 inp custom-date-input">
									<input type="date"
										class="form-control shift-date-edit-shift input-1 view-shift-inp"
										id="floatingInput-4" placeholder="Date Of Birth"
										autocomplete="off"> <label for="floatingInput-4"
										class="view-shift-label">Date of Shift</label> <img
										src="<c:url value='/resources/images/calendar4-week.svg' />"
										alt="" class="custom-date-icon">
								</div>
							</div>

							<div class="row">
								<div class="col-6">
									<div class="form-floating mb-3 inp custom-clock-input">
										<input type="time"
											class="form-control shift-start-edit-shift input-1 view-shift-inp"
											id="floatingInput-4" placeholder="Start" autocomplete="off">
										<label for="floatingInput-4" class="view-shift-label">Start</label>
										<img src="<c:url value='/resources/images/clock.svg' />"
											alt="" class="custom-clock-icon">
									</div>
								</div>

								<div class="col-6">
									<div class="form-floating mb-3 inp custom-clock-input">
										<input type="time"
											class="form-control shift-end-edit-shift input-1 view-shift-inp"
											id="floatingInput-4" placeholder="End" autocomplete="off">
										<label for="floatingInput-4" class="view-shift-label">End</label>
										<img src="<c:url value='/resources/images/clock.svg' />"
											alt="" class="custom-clock-icon">
									</div>
								</div>

								<input type="text" class="hidden-shift-id" hidden>

							</div>


						</div>
					</form>
				</div>
				<div class="modal-footer view-shift-footer-flex">
					<button type="button" onclick="toggleStatus()" class="send-btn"
						data-bs-dismiss="modal">Return</button>
					<button type="button" onclick="editShift()" class="send-btn">Edit</button>
					<button type="button" onclick="deleteShift()" class="delete-btn"
						data-bs-dismiss="modal">Delete</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="create-shift" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content create-shift-scale">
				<div class="modal-header">
					<h3 class="modal-title fs-5" id="staticBackdropLabel">Create
						Shift</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="physician-unavailable-error-div d-none">
						<em class="physician-unavailable-error">Physician is not
							available at the time current shift is created.</em>
					</div>
					<form id="create-shift-form">
						<div class="col-12">
							<div class="form-floating mb-3 inp">
								<select name="region-id" id="region-id"
									onchange="getPhysician()"
									class="form-control form-select input-2 region-name-class-shift view-shift-inp">
									<option value="0" selected hidden>Select a Region</option>

									<c:forEach items="${regionList }" var="region">
										<option value="${region.regionId }">${region.name }</option>
									</c:forEach>


								</select> <label for="region-id" class="view-shift-label">Region</label>
							</div>

							<div class="col-12">
								<div class="form-floating mb-3 inp">
									<select name="Number_Type" id="physician-id-case"
										class="form-control form-select input-2 view-shift-inp physician-name-class">
										<option value="0" selected hidden>Select Physician</option>

									</select> <label for="physician-id-case" class="view-shift-label">Physician</label>
								</div>

								<div class="col-12">
									<div class="form-floating mb-3 inp custom-date-input">
										<input type="date" required
											class="form-control input-1 view-shift-inp"
											id="shift-date-case" placeholder="Date Of Shift"
											autocomplete="off"> <label for="shift-date-case"
											class="view-shift-label">Date of Shift</label> <img
											src="<c:url value='/resources/images/calendar4-week.svg' />"
											alt="" class="custom-date-icon">
									</div>
								</div>

								<div class="row">
									<div class="col-6">
										<div class="form-floating mb-3 inp custom-clock-input">
											<input type="time" required
												class="form-control input-1 view-shift-inp"
												id="start-time-case" placeholder="Start" autocomplete="off">
											<label for="start-time-case" class="view-shift-label">Start</label>
											<img src="<c:url value='/resources/images/clock.svg' />"
												alt="" class="custom-clock-icon">
										</div>
									</div>

									<div class="col-6">
										<div class="form-floating mb-3 inp custom-clock-input">
											<input type="time" required
												class="form-control input-1 view-shift-inp"
												id="end-time-case" placeholder="End" autocomplete="off">
											<label for="end-time-case" class="view-shift-label">End</label>
											<img src="<c:url value='/resources/images/clock.svg' />"
												alt="" class="custom-clock-icon">
										</div>
									</div>
								</div>

								<div class="col-12">
									Repeat <label class="switch shrink"> <input
										class="toggle-repeat-details" type="checkbox"
										onchange="toggleRepeatDetails()"> <span
										class="slider round"></span>
									</label>
								</div>

								<div
									class="create-shift-days-main-flex repeat-details-class d-none">
									<div class="create-shift-days-checkbox-colum-flex">
										<div class="create-shift-days-checkbox-flex">
											<input type="checkbox" class="day-checkbox"
												onclick="selectedDays()"> <span
												class="create-shift-day-name">Every Sunday</span>
										</div>
										<div class="create-shift-days-checkbox-flex">
											<input type="checkbox" class="day-checkbox"
												onclick="selectedDays()"> <span
												class="create-shift-day-name">Every Wednesday</span>
										</div>
										<div class="create-shift-days-checkbox-flex">
											<input type="checkbox" class="day-checkbox"
												onclick="selectedDays()"> <span
												class="create-shift-day-name">Every Saturday</span>
										</div>
									</div>

									<div class="create-shift-days-checkbox-colum-flex ">
										<div class="create-shift-days-checkbox-flex">
											<input type="checkbox" class="day-checkbox"
												onclick="selectedDays()"> <span
												class="create-shift-day-name">Every Monday</span>
										</div>
										<div class="create-shift-days-checkbox-flex">
											<input type="checkbox" class="day-checkbox"
												onclick="selectedDays()"> <span
												class="create-shift-day-name">Every Thursday</span>
										</div>
									</div>

									<div class="create-shift-days-checkbox-colum-flex">
										<div class="create-shift-days-checkbox-flex">
											<input type="checkbox" class="day-checkbox"
												onclick="selectedDays()"> <span
												class="create-shift-day-name">Every Tuesday</span>
										</div>
										<div class="create-shift-days-checkbox-flex">
											<input type="checkbox" class="day-checkbox"
												onclick="selectedDays()"> <span
												class="create-shift-day-name">Every Friday</span>
										</div>
									</div>
								</div>

								<input type="text" hidden class="repeated-week-days"
									id="selected-days-case" value="0,0,0,0,0,0,0">

								<div class="col-12 mt-3 repeat-details-class d-none">
									<div class="form-floating mb-3 inp">
										<select name="Number_Type" id="repeat-times-case"
											class="form-control form-select input-2 view-shift-inp">
											<option value="1">1-times</option>
											<option value="2">2-times</option>
											<option value="3">3-times</option>
											<option value="4">4-times</option>
										</select> <label for="repeat-times-case" class="view-shift-label">Repeat
											End</label>
									</div>

								</div>

							</div>
							<div class="modal-footer create-shift-footer">
								<!-- 					<button type="button" class="send-btn" data-bs-dismiss="modal">Return</button> -->
								<button type="submit" class="send-btn">Create Shift</button>
								<button type="reset" class="delete-btn reset-shift-modal-btn"
									data-bs-dismiss="modal">Cancel</button>
							</div>
						</div>
					</form>
				</div>
			</div>

			<script src="<c:url value='/resources/js/daypilot-all.min.js' />"></script>
			<script src="<c:url value='/resources/js/scheduling-day.js' />"></script>

			<script>
        const picker = new DayPilot.DatePicker({
        target: 'start',
        pattern: 'yyyy-MM-dd',
        resetTarget: true,
        onTimeRangeSelected: args => {
            dp.update({
                startDate: args.start
                });
            setMonthDate()
            }
        
        });
        
        function getPhysicianData(eventData){
        	var regionId = $(".select-scheduling-region").val()
            const events = [];
        	const resources = []
        	$.ajax({
        		url: 'get-physician-details-scheduling',
        		type: 'POST',
        		data: {
        			regionId : regionId
        		},
        		success: function(res) {
        			console.log(eventData)
        			 for (let i = 0; i < res.length; ++i) {
        				 	var nameHtml = "<div class='coverage-flex'><div class='coverage-inner-flex'><img src =  " + res[i].path + " class='coverage-img' /><span> " + res[i].physicianName + "</span></div></div>"
        				 	let obj = {
        	                    html: nameHtml,
        	                    id: res[i].physicianId
        	                }
        	                resources.push(obj)
        	         }
        			
        			for (let i=0; i < eventData.length; ++i ){
        				
        				var shiftText = new DayPilot.Date(eventData[i].shiftDate + "T" + eventData[i].startTime + ":00").toString("h:mm tt") + " - " + new DayPilot.Date(eventData[i].shiftDate + "T" + eventData[i].endTime + ":00").toString("h:mm tt") + ", " + eventData[i].regionAbbr + ", " + eventData[i].physicianName 
        				 let obj = {
        		                    id: eventData[i].shiftDetailId,
        		                    text: shiftText , 
        		                    start: eventData[i].shiftDate + "T" + eventData[i].startTime + ":00",
        		                    end: eventData[i].shiftDate + "T" + eventData[i].endTime + ":00",
        		                    resource: eventData[i].physicianId,
        		                    regionid: eventData[i].regionId,
        		                    backColor: eventData[i].status == 0 ? "#FEADF9" : "#A5CEA4"
        		                }
        		                events.push(obj)
        				
        				
        			}
        			 dp.update({resources,events});
        		},
        		error: function(res) {

        		}

        	});
        }

        
        const dp = new DayPilot.Month("dp", {
          locale: "en-us",
          viewType: "Month",
          showWeekend: true,
          eventHeight: 45,
          timeRangeSelectedHandling: "Disabled",
          eventDeleteHandling: "Disabled",
          eventMoveHandling: "Disabled",
          eventResizeHandling: "Disabled",
          eventClickHandling: "Enabled",
          
          onEventClicked: (args) => {
              getShiftDetails(args.e.id())
              var myModal = new bootstrap.Modal(document.getElementById('accept'), {});
              myModal.show()
          },
          eventHoverHandling: "Disabled",
        });
        dp.events.list = [];
        dp.eventTextWrappingEnabled = true;
        dp.init();
        const app = {
    elements: {
        previous: document.getElementById("previous"),
        next: document.getElementById("next"),
        start: document.querySelector("#start"),
        change: document.querySelector("#change")
    },
    loadData() {
        const events = [];
        let start = DayPilot.Date.today().firstDayOfMonth();
        for (let i = 0; i < 15; i++) {
            const add = Math.floor(Math.random() * 2);
            // const add = 1;
            start = start.addDays(add);
            if (start.getDayOfWeek() === 6) {
                start = start.addDays(2);
            }
            if (start.getDayOfWeek() === 0) {
                start = start.addDays(1);
            }

            
        }
        
        var regionId = $(".select-scheduling-region").val()
    	
		$.ajax({
			url: 'get-physician-events',
			type: 'POST',
			data : {
				regionId : regionId
			},
			success: function(res) {
				
				getPhysicianData(res)
				
			},
			error: function(res) {

			}

		});
        },
        barColor(i) {
            const colors = ["#3c78d8", "#6aa84f", "#f1c232", "#cc0000"];
            return colors[i % 4];
        },
        init() {
            this.addEventHandlers();
            this.loadData();
        },
        addEventHandlers() {
           
            this.elements.change.addEventListener("click", (ev) => {
                        ev.preventDefault();
                        picker.show();
                    });
        }
    };
    app.init();
    
    
      </script>
</body>
</html>