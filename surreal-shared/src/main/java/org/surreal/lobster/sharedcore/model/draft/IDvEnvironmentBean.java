package org.surreal.lobster.sharedcore.model.draft;


public interface IDvEnvironmentBean {

	Integer getAirTemp();
	void setAirTemp(Integer airTemp);

	Integer getCumPrecip();
	void setCumPrecip(Integer cumPrecip);

	String getDistnUomC();
	void setDistnUomC(String distnUomC);

	Integer getDistnVsblyRstrd();
	void setDistnVsblyRstrd(Integer distnVsblyRstrd);

	String getEnvironmentSerl();
	void setEnvironmentSerl(String environmentSerl);

	String getFireClassC();
	void setFireClassC(String fireClassC);

	String getLightgAdeqC();
	void setLightgAdeqC(String lightgAdeqC);

	String getLightngI();
	void setLightngI(String lightngI);

	String getNoiseLevelFacI();
	void setNoiseLevelFacI(String noiseLevelFacI);

	String getOnBaseI();
	void setOnBaseI(String onBaseI);

	Integer getPostedSpeedMph();
	void setPostedSpeedMph(Integer postedSpeedMph);

	String getRltnToRdwayC();
	void setRltnToRdwayC(String rltnToRdwayC);

	String getRoadDesignC();
	void setRoadDesignC(String roadDesignC);

	String getSeaDirnC();
	void setSeaDirnC(String seaDirnC);

	String getSeaStateC();
	void setSeaStateC(String seaStateC);

	String getSrcFireC();
	void setSrcFireC(String srcFireC);

	String getSurfCndnC();
	void setSurfCndnC(String surfCndnC);

	String getSurfDfectC();
	void setSurfDfectC(String surfDfectC);

	String getSurfTypeC();
	void setSurfTypeC(String surfTypeC);

	String getVsblyI();
	void setVsblyI(String vsblyI);

	Integer getWaterTemp();
	void setWaterTemp(Integer waterTemp);

	Integer getWbgt();
	void setWbgt(Integer wbgt);

	String getWindDirnC();
	void setWindDirnC(String windDirnC);

	Integer getWindSpeed();
	void setWindSpeed(Integer windSpeed);
}