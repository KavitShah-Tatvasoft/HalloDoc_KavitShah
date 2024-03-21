package hallodoc.enumerations;

public enum DocType {
	TEST_ONE(1, "TestOne"),
	MEDICAL_REPORTS(2, "MedicalReport"),
	COST_RECIEPTS(3, "CostReceipt");
	
	private int docId;
	private String docType;
	
	DocType(int docId, String docType){
		this.docId = docId;
		this.docType = docType;
	}

	public int getDocId() {
		return docId;
	}

	public String getDocType() {
		return docType;
	}
	
	
}
