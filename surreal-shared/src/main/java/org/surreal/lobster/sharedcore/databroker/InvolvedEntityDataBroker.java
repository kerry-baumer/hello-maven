package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDvInvlvdEntityBean;
import org.surreal.lobster.sharedcore.model.draft.IDvSmallCraftBean;

/**
 * Provides data model access, manipulation, load, and save services for the client.  This 
 * interface manages the creation and wiring of the data model.  All model instances returned
 * by this broker will have a non-<code>null</code> unique identifier assigned in the bean.<p>
 * 
 * Provides methods for loading and saving data associated with entities involved in
 * a mishap or hazard.
 * @author jeffrey.richley
 * @author daniel.wilkin
 */
public interface InvolvedEntityDataBroker extends DelegatingDataBroker {

	/**
	 * Retrieves the list of entities associated with the event.  The result
	 * may be empty. 
	 * 
	 * @return A non-<code>null</code>, read only view of the entities associated
	 * with the event such that any changes to the returned list will not be reflected into
	 * the stored list and vice versa
	 */
	List<IDvInvlvdEntityBean> getInvolvedEntities();
	
	/**
	 * Determines if an entity associated with the specified identifier exists within
	 * the data model.
	 * @param id The unique identifier of the entity to locate
	 * @return <code>True</code> if the entity exists, <code>false</code> otherwise
	 */
	boolean hasInvolvedEntity(String id);

	/**
	 * Retrieves the entity that matches the specified unique identifier associated 
	 * with the event.
	 * 
	 * @param id The unique identifier
	 * @return A <code>IDvInvlvdEntityBean</code> matching the specified unique
	 * identifier
	 * @throws IllegalStateException If the entity could not be found
	 */
	IDvInvlvdEntityBean getInvolvedEntity(String id);

	/**
	 * Creates a new entity instance with a unique identifier and wires it to the model.
	 * 
	 * @return The new involved entity, never <code>null</code>
	 */
	IDvInvlvdEntityBean addInvolvedEntity();

	/**
	 * Removes the entity associated with the specified identifier.  All other beans
	 * are disassociated with this entity such as <i>InvolvedProperty</i> and 
	 * <i>InvolvedPerson</i>.
	 * @param id The identifier of the entity to be removed
	 */
	void removeInvolvedEntity(String id);

	/**
	 * Creates or retrieves the small craft associated with the specified entity unique 
	 * identifier.
	 * @param entityId The unique identifier of the entity composing the small craft
	 * instance
	 * @return The corresponding small craft instance, never <code>null</code>
	 */
	IDvSmallCraftBean getSmallCraft(String entityId);
	
	/**
	 * Determines if the entity associated with the specified identifier composes a
	 * non-<code>null</code> small craft instance.
	 * @param entityId The unique identifier of the involved entity to evaluate
	 * @return <code>True</code> if the entity composes a small craft instance,
	 * <code>false</code> otherwise
	 */
	boolean hasSmallCraft(String entityId);
	
}
