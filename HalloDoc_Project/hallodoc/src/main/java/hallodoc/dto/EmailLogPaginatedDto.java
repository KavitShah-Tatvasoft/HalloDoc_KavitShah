package hallodoc.dto;

import java.util.List;

public class EmailLogPaginatedDto {
	
	private List<EmailLogDashboardDto> emailLogDashboardDto;
	private Long count;
	
	
	public List<EmailLogDashboardDto> getEmailLogDashboardDto() {
		return emailLogDashboardDto;
	}
	public void setEmailLogDashboardDto(List<EmailLogDashboardDto> emailLogDashboardDto) {
		this.emailLogDashboardDto = emailLogDashboardDto;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public EmailLogPaginatedDto(List<EmailLogDashboardDto> emailLogDashboardDto, Long count) {
		super();
		this.emailLogDashboardDto = emailLogDashboardDto;
		this.count = count;
	}
	public EmailLogPaginatedDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}
