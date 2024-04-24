package com.lms.enumerations;

public enum Genere {
	SCIENCE_FICTION(1, "Science Fiction"),
	NOVEL(2, "Novel"),
	HISTORY(3, "History");
	
	private int genereId;
	private String genereType;
	
	
	public int getGenereId() {
		return genereId;
	}
	public void setGenereId(int genereId) {
		this.genereId = genereId;
	}
	public String getGenereType() {
		return genereType;
	}
	public void setGenereType(String genereType) {
		this.genereType = genereType;
	}
	private Genere(int genereId, String genereType) {
		this.genereId = genereId;
		this.genereType = genereType;
	}
	
	
}
