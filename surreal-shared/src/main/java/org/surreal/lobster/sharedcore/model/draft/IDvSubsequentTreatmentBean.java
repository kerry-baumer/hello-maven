package org.surreal.lobster.sharedcore.model.draft;

public interface IDvSubsequentTreatmentBean {

	String getCmpltReliefDt();
	void setCmpltReliefDt(String cmpltReliefDt);

	String getInvlvdPerSerl();
	void setInvlvdPerSerl(String invlvdPerSerl);

	Integer getMaxTrtmDepth();
	void setMaxTrtmDepth(Integer maxTrtmDepth);

	String getMaxTrtmDt();
	void setMaxTrtmDt(String maxTrtmDt);

	String getRcmpnStartDt();
	void setRcmpnStartDt(String rcmpnStartDt);

	Integer getRecurNo();
	void setRecurNo(Integer recurNo);

	String getSubseqTrmtSerl();
	void setSubseqTrmtSerl(String subseqTrmtSerl);

	String getTrtmC();
	void setTrtmC(String trtmC);

	String getTrtmCmpltDt();
	void setTrtmCmpltDt(String trtmCmpltDt);

	Double getTrtmCmpltnPpo2();
	void setTrtmCmpltnPpo2(Double trtmCmpltnPpo2);

	String getTrtmOcmC();
	void setTrtmOcmC(String trtmOcmC);

}