package org.surreal.lobster.sharedcore.model.draft;

public interface IDvLegacyXrefBean {

	String getDbId();
	void setDbId(String dbId);

	int getEventSerial();
	void setEventSerial(int eventSerial);

	String getLegacyXrefSerl();
	void setLegacyXrefSerl(String legacyXrefSerl);

	int getXmlId();
	void setXmlId(int xmlId);

}