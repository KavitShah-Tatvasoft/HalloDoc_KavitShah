package hallodoc.dto;

import java.util.List;

public class ReviewShiftDto {

	private List<ReviewShiftDetailsDto> reviewShiftDetailsDto;
	private Long count;
	
	
	public List<ReviewShiftDetailsDto> getReviewShiftDetailsDto() {
		return reviewShiftDetailsDto;
	}
	public void setReviewShiftDetailsDto(List<ReviewShiftDetailsDto> reviewShiftDetailsDto) {
		this.reviewShiftDetailsDto = reviewShiftDetailsDto;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public ReviewShiftDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
