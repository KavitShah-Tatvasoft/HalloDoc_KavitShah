package hallodoc.mapper;

import java.util.Date;
import java.util.Locale;

import hallodoc.dto.NewRequestDataDto;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestType;

import java.text.*;

public class RequestNewDataDtoMapper {

	private static String requestDateFormat(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		String dateString = dateFormat.format(date);
		return dateString;
	}
	
	public static NewRequestDataDto mapDataNeWDataDto(Request request){
		NewRequestDataDto newDto = new NewRequestDataDto();
		RequestClient requestClient = request.getRequestClient();
		newDto.setCity(requestClient.getCity());
		newDto.setState(requestClient.getState());
		newDto.setZipcode(requestClient.getZipcode());
		newDto.setStreet(requestClient.getStreet());
		newDto.setDay(requestClient.getIntDate());
		newDto.setMonth(requestClient.getStrMonth());
		newDto.setYear(requestClient.getIntYear());
		newDto.setName(requestClient.getFirstName() + " " + requestClient.getLastName());
		newDto.setRequestor(request.getFirstName() + " " +request.getLastName() );
		newDto.setRequestedDate(requestDateFormat(request.getCreatedDate()));
		newDto.setPtPhoneNumber(requestClient.getPhoneNumber());
		newDto.setPtPhoneNumberType(hallodoc.enumerations.RequestType.PATIENT.getRequestType());
		newDto.setReqPhoneNumber(request.getPhoneNumber());
		newDto.setReqPhoneNumberType(request.getRequestType().getName());
		newDto.setNotes(requestClient.getNotes());
		
		return newDto;
	}
}
