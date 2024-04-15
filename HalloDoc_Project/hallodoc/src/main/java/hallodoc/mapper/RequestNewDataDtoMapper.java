package hallodoc.mapper;

import java.util.Date;
import java.util.Locale;

import hallodoc.dto.NewRequestDataDto;
import hallodoc.model.Physician;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestType;

import java.text.*;

public class RequestNewDataDtoMapper {

	private static String requestDateFormat(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("MMMM dd, YYYY", Locale.ENGLISH);
		String dateString = dateFormat.format(date);
		return dateString;
	}

	private static String requestTimeFormat(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
		String timeString = dateFormat.format(date);
		return timeString;
	}

	private static String requestTimeDifference(Date date) {
		Date now = new Date();
		long diff = now.getTime() - date.getTime();
		long diffSeconds = diff / 1000 % 60;
		int diffInDays = (int) diff / (1000 * 60 * 60 * 24);

		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = (diff / (60 * 60 * 1000)) + diffInDays * 24;

		return Long.toString(diffHours) + " hrs " + Long.toString(diffMinutes) + " mins ";

	}

	public static NewRequestDataDto mapDataNeWDataDto(Request request) {
		NewRequestDataDto newDto = new NewRequestDataDto();
		RequestClient requestClient = request.getRequestClient();
		int status = request.getStatus();
		Physician physician = request.getPhysician();
		if (physician == null) {
			newDto.setPhysicianName("-");
		} else {
			newDto.setPhysicianName(physician.getFirstName() + " " + physician.getLastName());
		}

		if (status != 1) {
			newDto.setDateOfService(requestDateFormat(request.getAcceptedDate()));
			newDto.setTimeOfService(requestTimeFormat(request.getAcceptedDate()));
		}

		newDto.setCity(requestClient.getCity());
		newDto.setState(requestClient.getState());
		newDto.setZipcode(requestClient.getZipcode());
		newDto.setStreet(requestClient.getStreet());
		newDto.setDay(requestClient.getIntDate());
		newDto.setMonth(requestClient.getStrMonth());
		newDto.setYear(requestClient.getIntYear());
		newDto.setName(requestClient.getFirstName() + " " + requestClient.getLastName());
		newDto.setRequestor(request.getFirstName() + " " + request.getLastName());
		newDto.setRequestedDate(requestDateFormat(request.getCreatedDate()));
		newDto.setRequestedTime(requestTimeFormat(request.getCreatedDate()));
		newDto.setRequestedTimeDifference(requestTimeDifference(request.getCreatedDate()));
		newDto.setPtPhoneNumber(requestClient.getPhoneNumber());
		newDto.setPtPhoneNumberType(hallodoc.enumerations.RequestType.PATIENT.getRequestType());
		newDto.setReqPhoneNumber(request.getPhoneNumber());
		newDto.setReqPhoneNumberType(request.getRequestType().getName());
		newDto.setNotes((requestClient.getNotes() != null?requestClient.getNotes():"-"));
		newDto.setRegion(requestClient.getState());
		newDto.setDeleted(request.isDeleted());
		newDto.setPtEmail(requestClient.getEmail());
		newDto.setRequestId(request.getRequestId());
		return newDto;
	}
}
