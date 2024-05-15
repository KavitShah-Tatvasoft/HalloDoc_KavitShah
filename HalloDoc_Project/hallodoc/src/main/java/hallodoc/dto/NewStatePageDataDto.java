package hallodoc.dto;

import java.util.List;

public class NewStatePageDataDto {

	private int count;
	private List<NewRequestDataDto> newRequestDataDtos;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<NewRequestDataDto> getNewRequestDataDtos() {
		return newRequestDataDtos;
	}
	public void setNewRequestDataDtos(List<NewRequestDataDto> newRequestDataDtos) {
		this.newRequestDataDtos = newRequestDataDtos;
	}
	public NewStatePageDataDto(int count, List<NewRequestDataDto> newRequestDataDtos) {
		super();
		this.count = count;
		this.newRequestDataDtos = newRequestDataDtos;
	}
	public NewStatePageDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
