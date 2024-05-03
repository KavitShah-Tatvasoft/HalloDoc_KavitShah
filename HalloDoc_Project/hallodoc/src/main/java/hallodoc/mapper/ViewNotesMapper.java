package hallodoc.mapper;

import hallodoc.dto.DashboardDataDto;
import hallodoc.dto.ViewNotesDto;
import hallodoc.model.Request;
import hallodoc.model.RequestNotes;
import hallodoc.model.RequestStatusLog;

public class ViewNotesMapper {

	public static ViewNotesDto mapViewNotesData(RequestStatusLog requestStatusLog) {
		
		ViewNotesDto viewNotesDto = new ViewNotesDto();
		viewNotesDto.setLogId(requestStatusLog.getRequest_status_log_id());
		viewNotesDto.setStatus(requestStatusLog.getStatus());
		viewNotesDto.setCreatedDate(requestStatusLog.getCreatedDate());
		viewNotesDto.setNotes(requestStatusLog.getNotes());
		viewNotesDto.setTransToPhysician(requestStatusLog.isTransToAdmin());
		return viewNotesDto;
	}
}
