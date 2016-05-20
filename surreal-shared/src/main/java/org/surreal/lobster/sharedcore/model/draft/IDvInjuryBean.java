package org.surreal.lobster.sharedcore.model.draft;

public interface IDvInjuryBean {

	String getCnInjSerial();
	void setCnInjSerial(String cnInjSerial);

	IDvOptionItem getBlsAcdtTypeC();
	void setBlsAcdtTypeC(IDvOptionItem blsAcdtTypeC);

	IDvOptionItem getBlsObjInvlvdC();
	void setBlsObjInvlvdC(IDvOptionItem blsObjInvlvdC);

	String getCaSrcTypeC();
	void setCaSrcTypeC(String caSrcTypeC);

	String getChemInvlvdI();
	void setChemInvlvdI(String chemInvlvdI);

	Double getCoreBodyTemp();
	void setCoreBodyTemp(Double coreBodyTemp);

	String getDeathDate();
	void setDeathDate(String deathDate);

	String getEmerRoomI();
	void setEmerRoomI(String emerRoomI);

	String getFnlTempInjDgnssC();
	void setFnlTempInjDgnssC(String fnlTempInjDgnssC);

	String getHeatColdExpsrI();
	void setHeatColdExpsrI(String heatColdExpsrI);

	String getHospI();
	void setHospI(String hospI);

	String getInitMedTrtmC();
	void setInitMedTrtmC(String initMedTrtmC);

	String getNeuroSignsC();
	void setNeuroSignsC(String neuroSignsC);

	String getOffydTrtmAuthI();
	void setOffydTrtmAuthI(String offydTrtmAuthI);

	String getOshaClassnC();
	void setOshaClassnC(String oshaClassnC);

	String getSharpsInvlvdI();
	void setSharpsInvlvdI(String sharpsInvlvdI);

	String getTrnsfrPermI();
	void setTrnsfrPermI(String trnsfrPermI);

	String getTrnsfrUnitCode();
	void setTrnsfrUnitCode(String trnsfrUnitCode);

	String getTtLlrdI();
	void setTtLlrdI(String ttLlrdI);

	String getTtLwdI();
	void setTtLwdI(String ttLwdI);

	String getTtMedTrmtI();
	void setTtMedTrmtI(String ttMedTrmtI);

	String getTtTempXfrI();
	void setTtTempXfrI(String ttTempXfrI);

	Double getWbgt();
	void setWbgt(Double wbgt);
	
	String getLostAtSeaI();
	void setLostAtSeaI(String lostAtSeaI);

}