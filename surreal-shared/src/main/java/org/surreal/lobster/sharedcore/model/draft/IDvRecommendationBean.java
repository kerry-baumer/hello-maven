package org.surreal.lobster.sharedcore.model.draft;

public interface IDvRecommendationBean {

//	String getObjId();
//	void setObjId(String objId);

	String getRecmSerl();
	void setRecmSerl(String recmSerl);

	String getEventSerl();
	void setEventSerl(String eventSerl);

	String getSeqNum();
	void setSeqNum(String seqNum);
	
	String getCurrent();
	void setCurrent(String recommendationVersionSerl);

}