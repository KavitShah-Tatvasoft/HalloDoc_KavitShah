package hallodoc.mapper;

import hallodoc.dto.DashboardDataDto;
import hallodoc.model.Request;

public class RequestToDashboardDataMapper {

	public static DashboardDataDto mapDashboardData(Request request) {
		
		DashboardDataDto dashboardDataDto = new DashboardDataDto();
		dashboardDataDto.setRequestId(request.getRequestId());
		
		if(request.getPhysician() != null) {			
			dashboardDataDto.setPhysicianId(request.getPhysician().getPhysicianId());
			dashboardDataDto.setPhysicianFirstName(request.getPhysician().getFirstName());
			dashboardDataDto.setPhysicianLastName(request.getPhysician().getLastName());
		}
		dashboardDataDto.setCreatedDate(request.getCreatedDate());
		dashboardDataDto.setStatus(request.getStatus());
		dashboardDataDto.setCount(request.getListRequestWiseFiles().size());
		
		return dashboardDataDto;
	}
}
