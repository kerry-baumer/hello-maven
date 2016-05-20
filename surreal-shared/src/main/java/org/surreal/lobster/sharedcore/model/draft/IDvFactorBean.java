package org.surreal.lobster.sharedcore.model.draft;

public interface IDvFactorBean {

	String getFactorSerl();
	void setFactorSerl(String factorSerl);

	String getEventSerl();
	void setEventSerl(String eventSerl);
	
//	String getObjId();
//	void setObjId(String objId);

	String getSeqNum();
	void setSeqNum(String seqNum);
	
	String getCurrent();
	void setCurrent(String current);

}