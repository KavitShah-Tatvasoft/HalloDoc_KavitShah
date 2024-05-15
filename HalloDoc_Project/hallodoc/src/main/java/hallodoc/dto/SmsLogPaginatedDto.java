package hallodoc.dto;

import java.util.List;

public class SmsLogPaginatedDto {

	private List<SMSLogDashboardDataDto> smsLogDashboardDataDtos;
	private Long count;
	public List<SMSLogDashboardDataDto> getSmsLogDashboardDataDtos() {
		return smsLogDashboardDataDtos;
	}
	public void setSmsLogDashboardDataDtos(List<SMSLogDashboardDataDto> smsLogDashboardDataDtos) {
		this.smsLogDashboardDataDtos = smsLogDashboardDataDtos;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public SmsLogPaginatedDto(List<SMSLogDashboardDataDto> smsLogDashboardDataDtos, Long count) {
		super();
		this.smsLogDashboardDataDtos = smsLogDashboardDataDtos;
		this.count = count;
	}
	public SmsLogPaginatedDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
