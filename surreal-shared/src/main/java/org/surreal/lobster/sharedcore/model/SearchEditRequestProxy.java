/**
 * 
 */
package org.surreal.lobster.sharedcore.model;


/**
 * @author kerry.baumer
 *
 */
public interface SearchEditRequestProxy {

	/** The getter method for the eventSerial property */
    public String getEventSerial();

    /** The getter method for the localSerialNumber property */
    public String getLocalSerialNumber();

    /** The getter method for the dateStartRange property */
    public String getDateStartRange();

    /** The getter method for the dateEndRange property */
    public String getDateEndRange();

    /** The getter method for the invovledPersonUic property */
    public String getInvovledPersonUic();

    /** The getter method for the reportingUic property */
    public String getReportingUic();

    String getShortNarrative();
    
    /** 
     * Set the eventSerial property
     * @param eventSerial The new value for the eventSerial property
     */
    public void setEventSerial(String eventSerial);

    /** 
     * Set the localSerialNumber property
     * @param localSerialNumber The new value for the localSerialNumber property
     */
    public void setLocalSerialNumber(String localSerialNumber);

    /** 
     * Set the dateStartRange property
     * @param newVal The new value for the dateStartRange property
     */
    public void setDateStartRange(String newVal);

    /** 
     * Set the dateEndRange property
     * @param dateEndRange The new value for the dateEndRange property
     */
    public void setDateEndRange(String newVal);

    /** 
     * Set the invovledPersonUic property
     * @param invovledPersonUic The new value for the invovledPersonUic property
     */
    public void setInvovledPersonUic(String invovledPersonUic);

    /** 
     * Set the reportingUic property
     * @param reportingUic The new value for the reportingUic property
     */
    public void setReportingUic(String reportingUic);
    
    void setShortNarrative(String shortNarr);

;	public String getEndorsingUic();

	public void setEndorsingUic(String endorsingUic);

	/**
	 * @param finalizedOnly the finalizedOnly to set
	 */
	public void setFinalizedOnly(boolean newVal);

	/**
	 * @return the finalizedOnly
	 */
	public boolean isFinalizedOnly();

	/**
	 * @param srch3750Rpts the srch3750Rpts to set
	 */
	public void setSrch3750Rpts(boolean srch3750Rpts);

	/**
	 * @return the srch3750Rpts
	 */
	public boolean isSrch3750Rpts();

	/**
	 * @param srch5102Rpts the srch5102Rpts to set
	 */
	public void setSrch5102Rpts(boolean srch5102Rpts);

	/**
	 * @return the srch5102Rpts
	 */
	public boolean isSrch5102Rpts();
}
