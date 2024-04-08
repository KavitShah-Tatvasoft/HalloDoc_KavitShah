package hallodoc.dto;

public class StatusWiseCountDto {

	private int status;
	private long count;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "StatusWiseCount [status=" + status + ", count=" + count + "]";
	}
	public StatusWiseCountDto(int status, long count) {
		super();
		this.status = status;
		this.count = count;
	}
	public StatusWiseCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
