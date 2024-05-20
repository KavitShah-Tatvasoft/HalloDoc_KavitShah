package hallodoc.dto;

import java.util.List;

public class OnCallDataList {

	List<ProviderOnCallStatusDto> onClassPhysicians;
	List<ProviderOnCallStatusDto> offDutyPhysicians;
	public List<ProviderOnCallStatusDto> getOnClassPhysicians() {
		return onClassPhysicians;
	}
	public void setOnClassPhysicians(List<ProviderOnCallStatusDto> onClassPhysicians) {
		this.onClassPhysicians = onClassPhysicians;
	}
	public List<ProviderOnCallStatusDto> getOffDutyPhysicians() {
		return offDutyPhysicians;
	}
	public void setOffDutyPhysicians(List<ProviderOnCallStatusDto> offDutyPhysicians) {
		this.offDutyPhysicians = offDutyPhysicians;
	}
	public OnCallDataList(List<ProviderOnCallStatusDto> onClassPhysicians,
			List<ProviderOnCallStatusDto> offDutyPhysicians) {
		super();
		this.onClassPhysicians = onClassPhysicians;
		this.offDutyPhysicians = offDutyPhysicians;
	}
	public OnCallDataList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
