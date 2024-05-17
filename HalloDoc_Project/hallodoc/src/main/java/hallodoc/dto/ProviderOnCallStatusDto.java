package hallodoc.dto;

public class ProviderOnCallStatusDto {

	private String providerName;
	private int status;

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ProviderOnCallStatusDto(String providerName, int status) {
		super();
		this.providerName = providerName;
		this.status = status;
	}

	public ProviderOnCallStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
