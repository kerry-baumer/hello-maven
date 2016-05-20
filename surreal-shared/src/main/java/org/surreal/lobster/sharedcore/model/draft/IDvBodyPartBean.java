package org.surreal.lobster.sharedcore.model.draft;

public interface IDvBodyPartBean {

	String getCnBodyPartSerial();
	void setCnBodyPartSerial(String cnBodyPartSerial);
	
	IDvOptionItem getBlsBodyPartC();
	void setBlsBodyPartC(IDvOptionItem blsBodyPartC);

	IDvOptionItem getBlsInjTypeC();
	void setBlsInjTypeC(IDvOptionItem blsInjTypeC);

	String getPrimI();
	void setPrimI(String primI);

}