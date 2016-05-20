package org.surreal.lobster.sharedcore.model.draft;

public interface IDvAircraftBean {

	String getAcftModel();
	void setAcftModel(String acftModel);

	String getAcftUnitOwner();
	void setAcftUnitOwner(String acftUnitOwner);

	String getBunoFaaIdn();
	void setBunoFaaIdn(String bunoFaaIdn);

	String getDelivDataNarr();
	void setDelivDataNarr(String delivDataNarr);

	String getInvlvdEntitySerl();
	void setInvlvdEntitySerl(String invlvdEntitySerl);

	String getLoadConfigNarr();
	void setLoadConfigNarr(String loadConfigNarr);

	String getOrdnConfigNarr();
	void setOrdnConfigNarr(String ordnConfigNarr);

	String getRecAcftModel();
	void setRecAcftModel(String recAcftModel);

	String getRecAcftSerial();
	void setRecAcftSerial(String recAcftSerial);

	int getReleaseAlt();
	void setReleaseAlt(int releaseAlt);

	int getReleaseSpeed();
	void setReleaseSpeed(int releaseSpeed);

	String getSgnlsGvnI();
	void setSgnlsGvnI(String sgnlsGvnI);

	String getSgnlsVsblI();
	void setSgnlsVsblI(String sgnlsVsblI);

	String getTfoaI();
	void setTfoaI(String tfoaI);

}