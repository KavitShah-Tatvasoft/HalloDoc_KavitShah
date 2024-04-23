package hallodoc.dto;

public class VendorDetailsDto {

	private Integer vendorId;
	private String vendorName;

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Override
	public String toString() {
		return "VendorDetailsDto [vendorId=" + vendorId + ", vendorName=" + vendorName + "]";
	}

	public VendorDetailsDto(Integer vendorId, String vendorName) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}

	public VendorDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
