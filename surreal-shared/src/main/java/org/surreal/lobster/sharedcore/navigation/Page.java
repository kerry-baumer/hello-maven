package org.surreal.lobster.sharedcore.navigation;

public enum Page {
	GENERAL_INFO(false, "General Information"), 
	POC(false, "Point of Contact"), 
	ENVIRONMENT(false, "Environment"), 
	COI(false, "Community of Interest"),
	AUTHORIZED_DRAFTERS(false, "Authorized Drafters"),
	INVOLVED_ENTITY(true, "Involved Entity"),
	INVOLVED_PERSONNEL(true, "Involved Personnel"),
	DIVING_ACTIVITY(true, "Diving Activity"),
	PPE(true, "Personal Protective Equipment"),
	CERTS(true, "Qualifications and Certifications"),
	INJURY(true, "Injury"),
	INVOLVED_PROPERTY(true, "Involved Property"),
	FACTORS(true, "Factors and Recommendations"),
	QA(false, "Quality Assurance"),
	VALIDATION(false, "Validation"),
	OTHER(false, "Other"),
	BLANK_ENTITY(false, "Blank Vessel"),
	BLANK_PERSON(false, "Blank Person"),
	RECOMMENDATIONS(true,"Recommendations"),
	BLANK_PROPERTY(false, "Blank Property");
	
	private boolean hasXinfo;
	private String titleCase;
	Page(boolean arg0, String arg1) {
		hasXinfo = arg0;
		titleCase = arg1;
	}
	public boolean hasXInfo() {
		return hasXinfo;
	}

	public String getTitleCase() {
		return titleCase;
	}
}