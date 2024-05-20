package hallodoc.dto;

public class ProviderOnCallStatusDto {

	private String providerFirstName;
	private String providerLastName;
	private int providerId;
	private int status;
	private String photo;
	
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getProviderFirstName() {
		return providerFirstName;
	}
	public void setProviderFirstName(String providerFirstName) {
		this.providerFirstName = providerFirstName;
	}
	public String getProviderLastName() {
		return providerLastName;
	}
	public void setProviderLastName(String providerLastName) {
		this.providerLastName = providerLastName;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ProviderOnCallStatusDto(String providerFirstName, String providerLastName, int providerId, int status,
			String photo) {
		super();
		this.providerFirstName = providerFirstName;
		this.providerLastName = providerLastName;
		this.providerId = providerId;
		this.status = status;
		this.photo = photo;
	}
	public ProviderOnCallStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
