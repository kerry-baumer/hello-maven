package org.surreal.lobster.sharedcore.model.draft;

public interface IDvRecommendationVersionBean {

	String getDueDate();
	void setDueDate(String dueDate);

	String getRecmNarr();
	void setRecmNarr(String recmNarr);

	String getRecmVersionSerl();
	void setRecmVersionSerl(String recmVersionSerl);

	String getSeqNum();
	void setSeqNum(String seqNum);

	String getStatus();
	void setStatus(String status);
	
	String getStatement();
	void setStatement(String statement);
	
	String getRecommendationSerl();
	void setRecommendationSerl(String recommendationSerl);

}