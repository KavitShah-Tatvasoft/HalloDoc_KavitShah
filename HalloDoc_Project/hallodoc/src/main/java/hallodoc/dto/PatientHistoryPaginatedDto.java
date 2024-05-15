package hallodoc.dto;

import java.util.List;

public class PatientHistoryPaginatedDto {

	private List<PatientHistoryDto> patientHistoryDtos;
	private Long count;
	public List<PatientHistoryDto> getPatientHistoryDtos() {
		return patientHistoryDtos;
	}
	public void setPatientHistoryDtos(List<PatientHistoryDto> patientHistoryDtos) {
		this.patientHistoryDtos = patientHistoryDtos;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public PatientHistoryPaginatedDto(List<PatientHistoryDto> patientHistoryDtos, Long count) {
		super();
		this.patientHistoryDtos = patientHistoryDtos;
		this.count = count;
	}
	public PatientHistoryPaginatedDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
