package org.surreal.lobster.sharedcore.model.draft;


/**
 * A consolidated draft event data manipulation object. 
 * @author daniel.wilkin
 */
public interface IDvEventBean {

	String getAlchlInvlvdI();
	void setAlchlInvlvdI(String alchlInvlvdI);

	String getBriefNarr();
	void setBriefNarr(String briefNarr);

	String getClassSuppltI();
	void setClassSuppltI(String classSuppltI);

	double getDodPropCost();
	void setDodPropCost(double dodPropCost);

	String getDrugsInvlvdI();
	void setDrugsInvlvdI(String drugsInvlvdI);

	String getEiNarr();
	void setEiNarr(String eiNarr);

	String getEiStatC();
	void setEiStatC(String eiStatC);

	int getEventCost();
	void setEventCost(int eventCost);

	String getEventDate();
	void setEventDate(String eventDate);

	String getEventNarr();
	void setEventNarr(String eventNarr);

	String getEventSevtyC();
	void setEventSevtyC(String eventSevtyC);

	String getEventTime();
	void setEventTime(String eventTime);

	String getEventYear();
	void setEventYear(String eventYear);

	String getFinalizedDate();
	void setFinalizedDate(String finalizedDate);

	int getFy();
	void setFy(int fy);

	String getHighRiskTrngI();
	void setHighRiskTrngI(String highRiskTrngI);

	String getHtId();
	void setHtId(String htId);

	String getJagInvesnStatC();
	void setJagInvesnStatC(String jagInvesnStatC);

	String getLocalSerialNo();
	void setLocalSerialNo(String localSerialNo);

	int getMissionDaysLost();
	void setMissionDaysLost(int missionDaysLost);

	String getNavyOpernI();
	void setNavyOpernI(String navyOpernI);

	double getNonDodPropCost();
	void setNonDodPropCost(double nonDodPropCost);

	String getNotCountdRsnC();
	void setNotCountdRsnC(String notCountdRsnC);

	String getReportType();
	void setReportType(String reportType);

	String getRprtblEventSerl();
	void setRprtblEventSerl(String rprtblEventSerl);

	String getRprtgUnitCode();
	void setRprtgUnitCode(String rprtgUnitCode);

	String getSibInvlvdI();
	void setSibInvlvdI(String sibInvlvdI);

	String getSrcSeqC();
	void setSrcSeqC(String srcSeqC);

	String getTtDodPropDmgI();
	void setTtDodPropDmgI(String ttDodPropDmgI);

	String getTtEnvironmentI();
	void setTtEnvironmentI(String ttEnvironmentI);

	String getTtNonDodPropDmgI();
	void setTtNonDodPropDmgI(String ttNonDodPropDmgI);

	String getAreaRespC();
	void setAreaRespC(String areaRespC);
	
	String getMishapClassnC();
	void setMishapClassnC(String mishapClassnC);
	
	String getEapI();
	void setEapI(String eapI);
	
}