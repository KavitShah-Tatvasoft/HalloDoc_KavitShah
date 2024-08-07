package hallodoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "encounter_form")
public class EncounterForm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "encounter_form_id")
	private int encounterFormId;

	@OneToOne
	@JoinColumn(name = "request_id")
	private Request request;

	@Column(name = "admin_id")
	private int adminId;

	@Column(name = "physician_id")
	private int physicianId;

	@Column(name = "history_of_illness")
	private String historyOfIllness;

	@Column(name = "medical_history")
	private String medicalHistory;

	private String medications;

	private String allergies;

	private String temp;

	private String hr;

	private String rr;

	@Column(name = "blood_presure_plus")
	private String bloodPresurePlus;

	@Column(name = "blood_presure_neg")
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

	@Column(name = "treatment_plan")
	private String treatmentPlan;

	@Column(name = "medications_despensed")
	private String medicationsDespensed;

	private String procedures;

	@Column(name = "follow_up")
	private String followUp;
	
	@Column(name = "is_finalized")
	private boolean isFinalized;
	
	

	public boolean isFinalized() {
		return isFinalized;
	}

	public void setFinalized(boolean isFinalized) {
		this.isFinalized = isFinalized;
	}

	public int getEncounterFormId() {
		return encounterFormId;
	}

	public void setEncounterFormId(int encounterFormId) {
		this.encounterFormId = encounterFormId;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
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

	

	
	public EncounterForm(int encounterFormId, Request request, int adminId, int physicianId, String historyOfIllness,
			String medicalHistory, String medications, String allergies, String temp, String hr, String rr,
			String bloodPresurePlus, String bloodPresureneg, String o2, String pain, String heent, String cv,
			String chest, String abd, String extr, String skin, String neuro, String other, String diagnosis,
			String treatmentPlan, String medicationsDespensed, String procedures, String followUp,
			boolean isFinalized) {
		super();
		this.encounterFormId = encounterFormId;
		this.request = request;
		this.adminId = adminId;
		this.physicianId = physicianId;
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
		this.isFinalized = isFinalized;
	}

	public EncounterForm() {
		super();
		// TODO Auto-generated constructor stub
	}

}
