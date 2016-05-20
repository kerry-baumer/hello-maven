package org.surreal.lobster.sharedcore.model.draft;

public interface IDvMedicalIncidentBean {

	String getIncidentId();
	void setIncidentId(String incidentId);
	
	String getPatientId();
	void setPatientId(String patientId);
	
	int getFmp();
	void setFmp(int fmp);
	
	String getServiceDate();
	void setServiceDate(String serviceDate);
	
	String getPatientCategory();
	void setPatientCategory(String patientCategory);
	
	String getGender();
	void setGender(String gender);
	
	String getDob();
	void setDob(String dob);
	
	String getDiagnosis1();
	void setDiagnosis1(String diagnosis1);
	
	String getDiagnosis2();
	void setDiagnosis2(String diagnosis2);
	
	String getDiagnosis3();
	void setDiagnosis3(String diagnosis3);
	
	String getDiagnosis4();
	void setDiagnosis4(String diagnosis4);
	
	String getDiagnosisIcd9();
	void setDiagnosisIcd9(String diagnosisIcd9);
	
	String getInjuryCode();
	void setInjuryCode(String injuryCode);
	
	String getSource();
	void setSource(String source);
	
	String getTypeC();
	void setTypeC(String typeC);
	
	String getInjuryType();
	void setInjuryType(String injuryType);
	
	String getInjurySite();
	void setInjurySite(String injurySite);
	
	String getAssignedUnitCode();
	void setAssignedUnitCode(String assignedUnitCode);
	
	String getLastName();
	void setLastName(String lastName);
	
	String getFirstName();
	void setFirstName(String firstName);
}
