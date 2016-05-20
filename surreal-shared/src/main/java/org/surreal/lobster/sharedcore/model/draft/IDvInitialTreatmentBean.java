package org.surreal.lobster.sharedcore.model.draft;

public interface IDvInitialTreatmentBean {

	String getFnlDgnssC();
	void setFnlDgnssC(String fnlDgnssC);

	String getInitDgnssC();
	void setInitDgnssC(String initDgnssC);

	Integer getOnsetSymptDepth();
	void setOnsetSymptDepth(Integer onsetSymptDepth);

	String getOnsetSymptDt();
	void setOnsetSymptDt(String onsetSymptDt);

	String getTrtdByC();
	void setTrtdByC(String trtdByC);
	
	void setInitTrtmtSerl(String initTrtmtSerl);
	String getInitTrtmtSerl();

}