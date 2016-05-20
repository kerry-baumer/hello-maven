/**
 * 
 */
package org.surreal.lobster.sharedcore.model;

import java.util.Date;

import org.surreal.lobster.sharedcore.constants.ReportType;
import org.surreal.lobster.sharedcore.constants.UriOpenType;

/**
 * @author kerry.baumer
 *
 */
public interface SearchEditResultItemProxy {
	public String getLocalSerial();
	public void setLocalSerial(String localSerial);

	public String getShortNarrative();
	public void setShortNarrative(String shortNarrative);

	public Date getEventDate();
	public void setEventDate(Date ed);

	public String getEventSeverity();
	public void setEventSeverity(String eventSevty);

	public String getReportingUic();
	public void setReportingUic(String reportingUic);

	public boolean isEditable();
	public void setEditable(boolean editable);

	public void setEventSerial(String eventSerial);
	public String getEventSerial();

	public ReportType getReportType();
	public void setReportType(ReportType reportType);
	
	String getEditUri();
	UriOpenType getEditUriOpenType();
	void setEditUri(String uri);
	void setEditUriOpenType(UriOpenType type);
	
	String getViewUri();
	void setViewUri(String uri);
	
}
