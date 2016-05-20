package org.surreal.lobster.sharedcore.model.draft;

public interface IDvLocationBean {

	String getBodyOfWtrC();
	void setBodyOfWtrC(String bodyOfWtrC);

	String getCity();
	void setCity(String city);

	String getCntryC();
	void setCntryC(String cntryC);

	String getCounty();
	void setCounty(String county);

	String getEventLocnUnitCode();
	void setEventLocnUnitCode(String eventLocnUnitCode);

	Integer getInterstateNo();
	void setInterstateNo(Integer interstateNo);

	String getLatitude();
	void setLatitude(String latitude);

	String getLocationSerl();
	void setLocationSerl(String locationSerl);

	String getLongitude();
	void setLongitude(String longitude);

	IDvOptionItem getPortC();
	void setPortC(IDvOptionItem portC);

	String getRouteDesig();
	void setRouteDesig(String routeDesig);

	String getRstrdWtrsI();
	void setRstrdWtrsI(String rstrdWtrsI);

	String getStateProvC();
	void setStateProvC(String stateProvC);

	String getStreetName();
	void setStreetName(String streetName);

	String getTownship();
	void setTownship(String township);

}