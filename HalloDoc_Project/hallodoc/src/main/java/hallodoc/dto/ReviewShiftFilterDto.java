package hallodoc.dto;

public class ReviewShiftFilterDto {

	private int regionId;
	private int pageNo;

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public ReviewShiftFilterDto(int regionId, int pageNo) {
		super();
		this.regionId = regionId;
		this.pageNo = pageNo;
	}

	public ReviewShiftFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
