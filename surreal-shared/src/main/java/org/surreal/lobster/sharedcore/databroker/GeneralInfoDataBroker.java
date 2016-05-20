/**
 * Feb 13, 2012 4:28:18 PM
 */
package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.OptionItem;
import org.surreal.lobster.sharedcore.model.draft.IDvEnvironmentBean;
import org.surreal.lobster.sharedcore.model.draft.IDvEventBean;
import org.surreal.lobster.sharedcore.model.draft.IDvLocationBean;
import org.surreal.lobster.sharedcore.model.draft.IDvPocBean;
import org.surreal.lobster.sharedcore.model.draft.IDvReportSummary;

/**
 * Provides data model access, manipulation, load, and save services for the client.  This 
 * interface manages the creation and wiring of the data model.  All model instances returned
 * by this broker will have a non-<code>null</code> unique identifier assigned in the bean.<p>
 * 
 * Provides methods for manipulating data associated with the general information of a mishap
 * or hazard.
 * @author daniel.wilkin
 */
public interface GeneralInfoDataBroker extends DelegatingDataBroker {
	
	/* The following method is an unsupported implementation using AutoBeans in 
	 * GWT: JSO objects cannot extend multiple interfaces
	 */
//	public <T extends DataTransferObject> T createBean(Class<T> clazz);
	
	/**
	 * Loads the event into the databroker, if one is not found an <code>IllegalStateException</code>
	 * is thrown.
	 */
	public void loadEvent();

	/**
	 * Obtains the event model object
	 * @return The event model instance, never <code>null</code>
	 */
	public IDvEventBean getEvent();
	
	/**
	 * Determines if the data model contains an event.
	 * @return Always <code>true</code>
	 */
	public boolean hasEvent();
	
	/**
	 * Obtains the POC model object from the event.
	 * @return The POC model instance, never <code>null</code>
	 */
	public IDvPocBean getPoc();
	
	/**
	 * Determines if the event contains a POC bean.
	 * @return <code>True</code> if the model has a POC, <code>false</code> otherwise
	 */
	public boolean hasPoc();
	
	/**
	 * Obtains the ReportSummary model object from the event.
	 * @return The ReportSummary instance, never <code>null</code>
	 */
	public IDvReportSummary getSummary();
	
	/**
	 * Determines if the event contains a ReportSummary
	 * @return <code>True</code> if the model has a summary, <code>false</code> otherwise
	 */
	public boolean hasSummary();

	/**
	 * Obtains the Environment model object from the event.
	 * @return The Envrionment model instance, never <code>null</code>
	 */
	public IDvEnvironmentBean getEnvironment();
	
	/**
	 * Determines if the event has an Environment bean
	 * @return <code>True</code> if the model has an Environment bean, <code>false</code> otherwise
	 */
	public boolean hasEnvironment();

	/**
	 * Obtains the Location model object from the event.
	 * @return The Location model instance, never <code>null</code>
	 */
	public IDvLocationBean getLocation();
	
	/**
	 * Determines if the event contains a Location bean.
	 * @return <code>True</code> if the model has a Location, <code>false</code> otherwise
	 */
	public boolean hasLocation();

	/**
	 * Marks the specified list of users as authorized drafters on this event. 
	 * @param drafters The drafters authorized for this event where each element's
	 * <code>value</code> attribute is the user's EDI_PI
	 */
	public void setDrafters(List<OptionItem> drafters);

	/**
	 * Obtains an immutable view of the list of authorized drafters on the event.
	 * @return The list of drafters in the following format<ul><li>
	 *   <b>value</b>=EDI_PI<li>
	 *   <b>text</b>=User's formatted name <i>{last, first, rank}</i>
	 */
	public List<OptionItem> getDrafters();

	/**
	 * Obtains the commands the user has selected to include in the distribution list
	 * for notification of notable changes in the life of this event. 
	 * @return The list of commands/UICs
	 */
	public List<OptionItem> getAdditionalCommands();

	/**
	 * Specifies the list of commands to be notified of noteworthy changes in the
	 * life of this event.
	 * @param commands The commands/UICs to notify
	 */
	public void setAdditionalCommands(List<OptionItem> commands);
	
	/**
	 * Obtains the members in the Community of Interest surrounding this event.  Note,
	 * each member is not a UIC but rather a named grouping of 1 or more UICs.
	 * @return The members of the community of interest as specified by the
	 * drafters or other editors of this event
	 */
	public List<OptionItem> getCois();
	
	/**
	 * Specifies the members of the Community of Interest associated with this
	 * event.
	 * @param cois The members of the COI
	 */
	public void setCois(List<OptionItem> cois);
	
	/**
	 * Obtains the list of mishap types associated with the event.
	 * @return The mishap types
	 */
	public List<OptionItem> getMishapTypes();
	
	/**
	 * Specifies the list of mishap types to associate on this event. 
	 * @param mishapTypes The list of mishap types where each element's <code>value</code>
	 * attribute is the mishap type code
	 */
	public void setMishapTypes(List<OptionItem> mishapTypes);
	
	/**
	 * Obtains the list of visibility restricted decodes associated with this event.
	 * @return The list of visibility restricted decodes
	 */
	public List<OptionItem> getVisibilityRestrictedBy();
	
	/**
	 * Specifies the list of visibility restricted decodes to associate on this event.
	 * @param visibility The visibility restricted values where each element's <code>value</code>
	 * attribute is the visibility restricted code
	 */
	public void setVisibilityRestrictedBy(List<OptionItem> visibility);

	public void setInvolvedEntityDataBroker(InvolvedEntityDataBroker involvedEntityDataBrokerGwtImpl);


}
