package hallodoc.dto;

import java.util.Date;

public class ViewNotesDto {

	private int logId;
	private int status;
	private String notes;
	private Date createdDate;
	private String adminNotes;
	private String providerNotes;
	private boolean transToPhysician;

	

	
	public ViewNotesDto(int logId, int status, String notes, Date createdDate, String adminNotes, String providerNotes,
			boolean transToPhysician) {
		super();
		this.logId = logId;
		this.status = status;
		this.notes = notes;
		this.createdDate = createdDate;
		this.adminNotes = adminNotes;
		this.providerNotes = providerNotes;
		this.transToPhysician = transToPhysician;
	}

	public boolean isTransToPhysician() {
		return transToPhysician;
	}

	public void setTransToPhysician(boolean transToPhysician) {
		this.transToPhysician = transToPhysician;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAdminNotes() {
		return adminNotes;
	}

	public void setAdminNotes(String adminNotes) {
		this.adminNotes = adminNotes;
	}

	public String getProviderNotes() {
		return providerNotes;
	}

	public void setProviderNotes(String providerNotes) {
		this.providerNotes = providerNotes;
	}

	public ViewNotesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
