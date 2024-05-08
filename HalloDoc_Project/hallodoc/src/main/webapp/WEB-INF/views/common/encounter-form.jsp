<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${sessionScope.aspUser.user.aspNetRoles.id == 1}">
		<%@include file="common-navbar.jsp"%>
	</c:when>
	<c:otherwise>
		<%@include file="provider-navbar.jsp"%>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/encounter-form.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/footer.css' />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<title>Encounter Form</title>
</head>
<body>
	<div
		class="container-fluid container-fluid-width footer-container shadow-sm p-3 mb-5 bg-body rounded relative-position extra-margin">

		<div class="back-btn-top " role="button" onclick="history.go(-1)">
			<img src="<c:url value='/resources/images/chevron-left.svg' />"
				alt=""> Back
		</div>

		<div class="encounter-text-top">Encounter Form</div>

		<div class="row mb-4">
			<!--row-1-->
			<div class="col-12 top-text">
				Medical <br class="top-br">Report-Confidential
			</div>
		</div>

		<form action="../editEncounterForm" method="post">

			<div class="row">
				<!--row-2---- till email input-->
				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1"
							value="${userDetails.firstName }" id="floatingInput-1"
							placeholder="First Name" autocomplete="off" disabled> <label
							for="floatingInput-1">First Name</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1"
							value="${userDetails.lastName }" id="floatingInput-2"
							placeholder="Last Name" autocomplete="off" disabled> <label
							for="floatingInput-2">Last Name</label>
					</div>
				</div>

				<div class="col-12">
					<div class="form-floating mb-3 inp">
						<input type="text" class="form-control input-1"
							value="${userDetails.location }" disabled id="floatingInput-3"
							placeholder="Location" autocomplete="off"> <label
							for="floatingInput-3">Location</label>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp custom-date-input">
						<input type="date" class="form-control input-1"
							value="${userDetails.dob }" disabled id="floatingInput-4"
							placeholder="Date Of Birth" autocomplete="off"> <label
							for="floatingInput-4">Date of Birth</label> <img
							src="<c:url value='/resources/images/calendar4-week.svg' />"
							alt="" class="custom-date-icon">
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp custom-date-input">
						<input type="date" class="form-control input-1"
							value="${userDetails.dos }" disabled id="floatingInput-5"
							placeholder="Date" autocomplete="off"> <label
							for="floatingInput-5">Date of Service</label> <img
							src="<c:url value='/resources/images/calendar4-week.svg' />"
							alt="" class="custom-date-icon">
					</div>
				</div>

				<!--Phone Number col-->

				<div class="col-12 col-md-6">
					<div class="form-floating mb-3 inp phonecolheight">
						<input type="tel" class="form-control phoneflags" id="phone"
							value="${userDetails.phoneNumber }" disabled />
					</div>
				</div>

				<div class="col-12 col-md-6 add-extra-margin">
					<div class="form-floating mb-3 inp">
						<input type="email" class="form-control input-1"
							value="${userDetails.email}" disabled id="floatingInput-6"
							placeholder="Email" autocomplete="off"> <label
							for="floatingInput-6">Email</label>
					</div>
				</div>

			</div>

			<div class="row">
				<!--row-3-- 4 extra height inputs-->

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							value="${encounterForm.historyOfIllness}" disabled="disabled"
							id="floatingInput-7" name="historyOfIllness"
							placeholder="History of Present Illness Or Injury"
							autocomplete="off"> <label for="floatingInput-7">History
							of Present Illness Or Injury</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							value="${encounterForm.medicalHistory}" disabled="disabled"
							id="floatingInput-8" placeholder="Medical History"
							name="medicalHistory" autocomplete="off"> <label
							for="floatingInput-8">Medical History</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="medications" value="${encounterForm.medications}"
							disabled="disabled" id="floatingInput-9"
							placeholder="Medications" autocomplete="off"> <label
							for="floatingInput-9">Medications</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="allergies" value="${encounterForm.allergies}"
							disabled="disabled" id="floatingInput-10" placeholder="Allergies"
							autocomplete="off"> <label for="floatingInput-10">Allergies</label>
					</div>
				</div>

			</div>

			<div class="row">
				<!--row-4-- TEMP,HR,RR-->
				<div class="col-6 col-md-4">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 encounter-fields-disabled"
							name="temp" value="${encounterForm.temp}" disabled="disabled"
							id="floatingInput-11" placeholder="Temp" autocomplete="off">
						<label for="floatingInput-11">Temp</label>
					</div>
				</div>

				<div class="col-6 col-md-4">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 encounter-fields-disabled" name="hr"
							value="${encounterForm.hr}" disabled="disabled"
							id="floatingInput-12" placeholder="HR" autocomplete="off">
						<label for="floatingInput-12">HR</label>
					</div>
				</div>

				<div class="col-12 col-md-4">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 encounter-fields-disabled" name="rr"
							value="${encounterForm.rr}" disabled="disabled"
							id="floatingInput-13" placeholder="RR" autocomplete="off">
						<label for="floatingInput-13">RR</label>
					</div>
				</div>
			</div>

			<div class="row">
				<!--row-5-- BP,O2,Pain-->
				<div class="col-6 col-md-2">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 encounter-fields-disabled"
							name="bloodPresurePlus" value="${encounterForm.bloodPresurePlus}"
							disabled="disabled" id="floatingInput-14"
							placeholder="Blood Pressure()" autocomplete="off"> <label
							for="floatingInput-14">Blood Pressure()</label>
					</div>
				</div>

				<div class="col-6 col-md-2">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 encounter-fields-disabled"
							name="bloodPresureneg" value="${encounterForm.bloodPresureneg}"
							disabled="disabled" id="floatingInput-15"
							placeholder="Blood Pressure()" autocomplete="off"> <label
							for="floatingInput-15">Blood Pressure()</label>
					</div>
				</div>

				<div class="col-6 col-md-4">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 encounter-fields-disabled" name="o2"
							value="${encounterForm.o2}" disabled="disabled"
							id="floatingInput-16" placeholder="O2" autocomplete="off">
						<label for="floatingInput-16">O2</label>
					</div>
				</div>

				<div class="col-6 col-md-4">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 encounter-fields-disabled"
							name="pain" value="${encounterForm.pain}" disabled="disabled"
							id="floatingInput-17" placeholder="Pain" autocomplete="off">
						<label for="floatingInput-17">Pain</label>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							value="${encounterForm.heent}" disabled="disabled"
							id="floatingInput-18" placeholder="Medications" name="heent"
							autocomplete="off"> <label for="floatingInput-18">Heent</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="cv" value="${encounterForm.cv}" disabled="disabled"
							id="floatingInput-19" placeholder="Allergies" autocomplete="off">
						<label for="floatingInput-19">CV</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							value="${encounterForm.chest}" disabled="disabled"
							id="floatingInput-20" placeholder="Medications" name="chest"
							autocomplete="off"> <label for="floatingInput-20">Chest</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="abd" value="${encounterForm.abd}" disabled="disabled"
							id="floatingInput-21" placeholder="Allergies" autocomplete="off">
						<label for="floatingInput-21">ABD</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="extr" value="${encounterForm.extr}" disabled="disabled"
							id="floatingInput-22" placeholder="Medications"
							autocomplete="off"> <label for="floatingInput-22">Extr</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="skin" value="${encounterForm.skin}" disabled="disabled"
							id="floatingInput-23" placeholder="Allergies" autocomplete="off">
						<label for="floatingInput-23">Skin</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="neuro" value="${encounterForm.neuro}" disabled="disabled"
							id="floatingInput-24" placeholder="Medications"
							autocomplete="off"> <label for="floatingInput-24">Neuro</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="other" value="${encounterForm.other}" disabled="disabled"
							id="floatingInput-25" placeholder="Allergies" autocomplete="off">
						<label for="floatingInput-25">Other</label>
					</div>
				</div>


				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							value="${encounterForm.diagnosis}" disabled="disabled"
							id="floatingInput-26" placeholder="Medications" name="diagnosis"
							autocomplete="off"> <label for="floatingInput-26">Diagnosis</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="treatmentPlan" value="${encounterForm.treatmentPlan}"
							disabled="disabled" id="floatingInput-27" placeholder="Allergies"
							autocomplete="off"> <label for="floatingInput-27">Treatment
							Plan</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							value="${encounterForm.medicationsDespensed}" disabled="disabled"
							id="floatingInput-28" placeholder="Medications"
							name="medicationsDespensed" autocomplete="off"> <label
							for="floatingInput-28">Medications Dispensed</label>
					</div>
				</div>

				<div class="col-12 col-md-6 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="procedures" value="${encounterForm.procedures}"
							disabled="disabled" id="floatingInput-29" placeholder="Allergies"
							autocomplete="off"> <label for="floatingInput-29">Procedures</label>
					</div>
				</div>

				<div class="col-12 inc-height">
					<div class="form-floating mb-3 inp">
						<input type="text"
							class="form-control input-1 inc-inp-height encounter-fields-disabled"
							name="followUp" value="${encounterForm.followUp}"
							disabled="disabled" id="floatingInput-30" placeholder="Allergies"
							autocomplete="off"> <label for="floatingInput-30">Followup
						</label>
					</div>
				</div>
			</div>

			<input type="text" hidden value="${reqId }" name="requestId">

			<div class="row">
				<div class="bottom-btns mt-3 mb-3">
					<button type="button" class="bottom-btns-submit edit-hide-class"
						onclick="editEncounterForm('edit')">Edit</button>
					<button type="submit"
						class="bottom-btns-submit edit-show-class d-none">Save
						Changes</button>

					<c:choose>
						<c:when test="${sessionScope.aspUser.user.aspNetRoles.id != 1}">
							<a type="button" href="../finalize-encounter-form/${reqId }"  class="bottom-btns-finalize">Finalize</a>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>


					
					<button type="reset"
						class="bottom-btns-cancel edit-show-class d-none"
						onclick="editEncounterForm('cancel')">Cancel</button>
				</div>
			</div>

		</form>

	</div>

	<script>
		const phoneInputField = document.querySelector("#phone");
		const phoneInput = window
				.intlTelInput(
						phoneInputField,
						{
							utilsScript : "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
						});

		$(window).on('scroll', function() {
			positionItiContainer();
		});

		$('.iti__selected-flag').click(function() {
			positionItiContainer();
		});

		var positionItiContainer = function() {
			if ($('body.iti-mobile').length) {
				var phoneElement = document.getElementById('phone');
				var selectedFlag = document
						.querySelector('.iti__selected-flag');
				var phoneRect = phoneElement.getBoundingClientRect();
				var selectedFlagRect = selectedFlag.getBoundingClientRect();

				$('.iti--container').css('top', selectedFlagRect.bottom + 'px');
				$('.iti--container').css('left', selectedFlagRect.left + 'px');
			}
		};
	</script>

	<script src="<c:url value='/resources/js/darktheme.js' />"></script>
	<script src="<c:url value='/resources/js/encounter-form.js' />"></script>

</body>
</html>

<%@include file="footer-black.jsp"%>