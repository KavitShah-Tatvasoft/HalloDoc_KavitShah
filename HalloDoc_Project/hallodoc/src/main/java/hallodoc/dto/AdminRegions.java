package hallodoc.dto;

public class AdminRegions {

	private int regionId;
	private String regionName;
	private int isSelected;
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public int getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(int isSelected) {
		this.isSelected = isSelected;
	}
	@Override
	public String toString() {
		return "AdminRegions [regionId=" + regionId + ", regionName=" + regionName + ", isSelected=" + isSelected + "]";
	}
	public AdminRegions(int regionId, String regionName, int isSelected) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.isSelected = isSelected;
	}
	public AdminRegions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
