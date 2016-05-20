package org.surreal.lobster.sharedcore.model.draft;

public interface IDvFactorVersionBean {

	String getEventCauseI();
	void setEventCauseI(String eventCauseI);

	String getFactorNarr();
	void setFactorNarr(String factorNarr);

	String getFactorVersionSerl();
	void setFactorVersionSerl(String factorVersionSerl);

	IDvOptionItem getHfacActC();
	void setHfacActC(IDvOptionItem hfacActC);

	String getSeqNum();
	void setSeqNum(String seqNum);

	String getSynchId();
	void setSynchId(String synchId);
	
	String getStatement();
	void setStatement(String statement);
	
	String getRacC();
	void setRacC(String racC);
	
	IDvOptionItem getFactorType();
	void setFactorType(IDvOptionItem factorType);
	
	String getFactorSerl();
	void setFactorSerl(String factorSerl);
}