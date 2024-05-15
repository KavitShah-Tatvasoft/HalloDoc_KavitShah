package hallodoc.dto;

import java.util.List;

public class SearchRecordsPaginationDto {
	
	private List<SearchRecordsDashboardData> searchRecordsDashboardDatas;
	private Long count;
	
	
	public List<SearchRecordsDashboardData> getSearchRecordsDashboardDatas() {
		return searchRecordsDashboardDatas;
	}
	public void setSearchRecordsDashboardDatas(List<SearchRecordsDashboardData> searchRecordsDashboardDatas) {
		this.searchRecordsDashboardDatas = searchRecordsDashboardDatas;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public SearchRecordsPaginationDto(List<SearchRecordsDashboardData> searchRecordsDashboardDatas, Long count) {
		super();
		this.searchRecordsDashboardDatas = searchRecordsDashboardDatas;
		this.count = count;
	}
	public SearchRecordsPaginationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
