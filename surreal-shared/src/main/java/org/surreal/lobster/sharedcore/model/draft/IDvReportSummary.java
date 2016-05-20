/**
 * Apr 17, 2012 8:01:37 AM
 */
package org.surreal.lobster.sharedcore.model.draft;

/**
 * A status summary of the event.
 * @author daniel.wilkin
 */
public interface IDvReportSummary {

	String getId();
	void setId(String id);
	
	String getReportSerial();
	void setReportSerial(String reportSerial);
	
	String getShortNarrative();
	void setShortNarrative(String shortNarrative);
	
	String getLocalSerial();
	void setLocalSerial(String localSerial);
	
	String getEventDate();
	void setEventDate(String eventDate);
	
	String getReportingUnit();
	void setReportingUnit(String reportingUnit);
	
	String getReportType();
	void setReportType(String reportType);
	
	String getHtId();
	void setHtId(String htId);
	
	String getSeverity();
	void setSeverity(String severity);
	
	String getCurrentState();
	void setCurrentState(String currentState);
	
	String getPreviousState();
	void setPreviousState(String previousState);
	
}
