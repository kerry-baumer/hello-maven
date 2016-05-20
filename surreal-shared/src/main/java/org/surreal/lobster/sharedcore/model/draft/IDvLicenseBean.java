package org.surreal.lobster.sharedcore.model.draft;

public interface IDvLicenseBean {

	IDvOptionItem getCertQualLicnsC();
	void setCertQualLicnsC(IDvOptionItem certQualLicnsC);

	String getCertsC();
	void setCertsC(String certsC);

	String getCmpltnDate();
	void setCmpltnDate(String cmpltnDate);

	String getDesnsC();
	void setDesnsC(String desnsC);

	String getExpireDate();
	void setExpireDate(String expireDate);

	String getInvlvdPerSerl();
	void setInvlvdPerSerl(String invlvdPerSerl);

	String getLicnsC();
	void setLicnsC(String licnsC);

	String getLicnsSerl();
	void setLicnsSerl(String licnsSerl);

	String getQualsC();
	void setQualsC(String qualsC);

	String getRevokdLpsdI();
	void setRevokdLpsdI(String revokdLpsdI);

	String getRevokdLpsdNarr();
	void setRevokdLpsdNarr(String revokdLpsdNarr);

	String getRstrnI();
	void setRstrnI(String rstrnI);

	String getSuspdI();
	void setSuspdI(String suspdI);

}