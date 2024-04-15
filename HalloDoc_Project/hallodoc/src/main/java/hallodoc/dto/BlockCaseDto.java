package hallodoc.dto;

public class BlockCaseDto {

	private String blockReason;
	private int requestId;
	
	
	public String getBlockReason() {
		return blockReason;
	}
	public void setBlockReason(String blockReason) {
		this.blockReason = blockReason;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	@Override
	public String toString() {
		return "blockCaseDto [blockReason=" + blockReason + ", requestId=" + requestId + "]";
	}
	public BlockCaseDto(String blockReason, int requestId) {
		super();
		this.blockReason = blockReason;
		this.requestId = requestId;
	}
	public BlockCaseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
