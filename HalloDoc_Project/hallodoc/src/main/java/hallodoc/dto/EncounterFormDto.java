package hallodoc.dto;

public class EncounterFormDto {

	private int requestId;
	private String historyOfIllness;
	private String medicalHistory;
	private String medications;
	private String allergies;
	private String temp;
	private String hr;
	private String rr;
	private String bloodPresurePlus;
	private String bloodPresureneg;
	private String o2;
	private String pain;
	private String heent;
	private String cv;
	private String chest;
	private String abd;
	private String extr;
	private String skin;
	private String neuro;
	private String other;
	private String diagnosis;
	private String treatmentPlan;
	private String medicationsDespensed;
	private String procedures;
	private String followUp;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getHistoryOfIllness() {
		return historyOfIllness;
	}

	public void setHistoryOfIllness(String historyOfIllness) {
		this.historyOfIllness = historyOfIllness;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getHr() {
		return hr;
	}

	public void setHr(String hr) {
		this.hr = hr;
	}

	public String getRr() {
		return rr;
	}

	public void setRr(String rr) {
		this.rr = rr;
	}

	public String getBloodPresurePlus() {
		return bloodPresurePlus;
	}

	public void setBloodPresurePlus(String bloodPresurePlus) {
		this.bloodPresurePlus = bloodPresurePlus;
	}

	public String getBloodPresureneg() {
		return bloodPresureneg;
	}

	public void setBloodPresureneg(String bloodPresureneg) {
		this.bloodPresureneg = bloodPresureneg;
	}

	public String getO2() {
		return o2;
	}

	public void setO2(String o2) {
		this.o2 = o2;
	}

	public String getPain() {
		return pain;
	}

	public void setPain(String pain) {
		this.pain = pain;
	}

	public String getHeent() {
		return heent;
	}

	public void setHeent(String heent) {
		this.heent = heent;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getChest() {
		return chest;
	}

	public void setChest(String chest) {
		this.chest = chest;
	}

	public String getAbd() {
		return abd;
	}

	public void setAbd(String abd) {
		this.abd = abd;
	}

	public String getExtr() {
		return extr;
	}

	public void setExtr(String extr) {
		this.extr = extr;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getNeuro() {
		return neuro;
	}

	public void setNeuro(String neuro) {
		this.neuro = neuro;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatmentPlan() {
		return treatmentPlan;
	}

	public void setTreatmentPlan(String treatmentPlan) {
		this.treatmentPlan = treatmentPlan;
	}

	public String getMedicationsDespensed() {
		return medicationsDespensed;
	}

	public void setMedicationsDespensed(String medicationsDespensed) {
		this.medicationsDespensed = medicationsDespensed;
	}

	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}

	public String getFollowUp() {
		return followUp;
	}

	public void setFollowUp(String followUp) {
		this.followUp = followUp;
	}

	@Override
	public String toString() {
		return "EncounterFormDto [requestId=" + requestId + ", historyOfIllness=" + historyOfIllness
				+ ", medicalHistory=" + medicalHistory + ", medications=" + medications + ", allergies=" + allergies
				+ ", temp=" + temp + ", hr=" + hr + ", rr=" + rr + ", bloodPresurePlus=" + bloodPresurePlus
				+ ", bloodPresureneg=" + bloodPresureneg + ", o2=" + o2 + ", pain=" + pain + ", heent=" + heent
				+ ", cv=" + cv + ", chest=" + chest + ", abd=" + abd + ", extr=" + extr + ", skin=" + skin + ", neuro="
				+ neuro + ", other=" + other + ", diagnosis=" + diagnosis + ", treatmentPlan=" + treatmentPlan
				+ ", medicationsDespensed=" + medicationsDespensed + ", procedures=" + procedures + ", followUp="
				+ followUp + "]";
	}

	public EncounterFormDto(int requestId, String historyOfIllness, String medicalHistory, String medications,
			String allergies, String temp, String hr, String rr, String bloodPresurePlus, String bloodPresureneg,
			String o2, String pain, String heent, String cv, String chest, String abd, String extr, String skin,
			String neuro, String other, String diagnosis, String treatmentPlan, String medicationsDespensed,
			String procedures, String followUp) {
		super();
		this.requestId = requestId;
		this.historyOfIllness = historyOfIllness;
		this.medicalHistory = medicalHistory;
		this.medications = medications;
		this.allergies = allergies;
		this.temp = temp;
		this.hr = hr;
		this.rr = rr;
		this.bloodPresurePlus = bloodPresurePlus;
		this.bloodPresureneg = bloodPresureneg;
		this.o2 = o2;
		this.pain = pain;
		this.heent = heent;
		this.cv = cv;
		this.chest = chest;
		this.abd = abd;
		this.extr = extr;
		this.skin = skin;
		this.neuro = neuro;
		this.other = other;
		this.diagnosis = diagnosis;
		this.treatmentPlan = treatmentPlan;
		this.medicationsDespensed = medicationsDespensed;
		this.procedures = procedures;
		this.followUp = followUp;
	}

	public EncounterFormDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
