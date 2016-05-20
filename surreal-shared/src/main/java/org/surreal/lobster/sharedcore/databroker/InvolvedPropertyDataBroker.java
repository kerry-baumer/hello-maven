package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDvInvlvdPropertyBean;

public interface InvolvedPropertyDataBroker extends DelegatingDataBroker {

	/**
	 * Retrieves the list of Properties associated with the event.  The result
	 * may be empty. 
	 * 
	 * @return A non-<code>null</code>, read only view of the Properties associated
	 * with the event such that any changes to the returned list will not be reflected into
	 * the stored list and vice versa
	 */
	List<IDvInvlvdPropertyBean> getInvolvedProperties();
	
	/**
	 * Determines if a Property associated with the specified identifier exists within
	 * the data model.
	 * @param id The unique identifier of the Property to locate
	 * @return <code>True</code> if the Property exists, <code>false</code> otherwise
	 */
	boolean hasInvolvedProperty(String id);

	/**
	 * Retrieves the Property that matches the specified unique identifier associated 
	 * with the event.
	 * 
	 * @param id The unique identifier
	 * @return A <code>IDvInvlvdPropertyBean</code> matching the specified unique
	 * identifier
	 * @throws IllegalStateException If the property could not be found
	 */
	IDvInvlvdPropertyBean getInvolvedProperty(String id);

	/**
	 * Creates a new Property instance with a unique identifier and wires it to the model.
	 * 
	 * @return The new involved Property, never <code>null</code>
	 */
	IDvInvlvdPropertyBean addInvolvedProperty();

	/**
	 * Removes the Property associated with the specified identifier.  All other beans
	 * are disassociated with this Property.
	 * @param id The identifier of the Property to be removed
	 */
	void removeInvolvedProperty(String id);

	void setInvolvedEntityDataBroker(InvolvedEntityDataBroker involvedEntityDataBrokerGwtImpl);

}
