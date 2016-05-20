package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.OptionItem;
import org.surreal.lobster.sharedcore.model.draft.IDvFactorVersionBean;
import org.surreal.lobster.sharedcore.model.draft.IDvInvlvdPersonBean;
import org.surreal.lobster.sharedcore.model.draft.IDvInvlvdPropertyBean;
import org.surreal.lobster.sharedcore.model.draft.IDvRecommendationVersionBean;

/**
 * Provides data model access, manipulation, load, and save services for the client.  This 
 * interface manages the creation and wiring of the data model.  All model instances returned
 * by this broker will have a non-<code>null</code> unique identifier assigned in the bean.<p>
 * 
 * Provides methods for loading and saving data associated factors and recommendations involved in
 * a mishap or hazard.
 * @author christopher.d.grimes
 * @author daniel.wilkin
 */
public interface FactorsDataBroker extends DelegatingDataBroker {

	/**
	 * Retrieves the list of factor versions associated with the event.  The result
	 * may be empty. 
	 * 
	 * @return A non-<code>null</code>, read only view of the factor versions associated
	 * with the event such that any changes to the returned list will not be reflected into
	 * the stored list and vice versa
	 */
	List<IDvFactorVersionBean> getFactorVersions();

	/**
	 * Retrieves the list of recommendation versions associated with the event.  The result
	 * may be empty. 
	 * 
	 * @return A non-<code>null</code>, read only view of the recommendation versions associated
	 * with the event such that any changes to the returned list will not be reflected into
	 * the stored list and vice versa
	 */
	List<IDvRecommendationVersionBean> getRecommendationVersions();
	
	/**
	 * Determines if a factor version associated with the specified identifier exists within
	 * the data model.
	 * @param id The unique identifier of the factor version to locate
	 * @return <code>True</code> if the factor version exists, <code>false</code> otherwise
	 */
	boolean hasFactorVersion(String id);

	/**
	 * Determines if a recommendation version associated with the specified identifier exists within
	 * the data model.
	 * @param id The unique identifier of the recommendation version to locate
	 * @return <code>True</code> if the recommendation version exists, <code>false</code> otherwise
	 */
	boolean hasRecommendationVersion(String id);
	
	/**
	 * Retrieves the factor version that matches the specified unique identifier associated 
	 * with the event.
	 * 
	 * @param id The unique identifier
	 * @return A <code>IDvFactorVersionBean</code> matching the specified unique
	 * identifier
	 * @throws IllegalStateException If the factor version could not be found
	 */
	IDvFactorVersionBean getFactorVersion(String id);
	
	/**
	 * Retrieves the recommendation version that matches the specified unique identifier associated 
	 * with the event.
	 * 
	 * @param id The unique identifier
	 * @return A <code>IDvRecommendationVersionBean</code> matching the specified unique
	 * identifier
	 * @throws IllegalStateException If the recommendation version could not be found
	 */
	IDvRecommendationVersionBean getRecommendationVersion(String id);
	
	/**
	 * Creates a new factor version instance with a unique identifier and wires it to the model.
	 * 
	 * @return The new factor version, never <code>null</code>
	 */
	IDvFactorVersionBean addFactorVersion();

	/**
	 * Creates a new recommendation version instance with a unique identifier and wires it to the model.
	 * 
	 * @return The new recommendation version, never <code>null</code>
	 */
	IDvRecommendationVersionBean addRecommendationVersion();
	
	/**
	 * Removes the factor version associated with the specified identifier.  All other beans
	 * are disassociated with this factor version.
	 * @param id The identifier of the factor version to be removed
	 */
	void removeFactorVersion(String id);

	/**
	 * Removes the recommendation version associated with the specified identifier.  All other beans
	 * are disassociated with this recommendation version.
	 * @param id The identifier of the recommendation version to be removed
	 */
	void removeRecommendationVersion(String id);
	
	/**
	 * Retrieves the list of people associated with the factor version with the given id
	 * @param id The id of the factor version with which the list of people is associated 
	 * @return The list of people associated with the factor version with the given id
	 */
	List<IDvInvlvdPersonBean> getAssociatedPeople(String factorVersionId);
	
	/**
	 * Retrieves the list of properties associated with the factor version with the given id
	 * @param id The id of the factor version with which the list of properties is associated 
	 * @return A read-only list of properties associated with the factor version or an empty list if
	 * none are associated
	 */
	List<IDvInvlvdPropertyBean> getAssociatedProperty(String factorVersionId);
	
	/**
	 * Retrieves the list of factor versions associated with the recommendation version with the given id
	 * @param id The id of the recommendation version with which the list of factor versions is associated
	 * @return A read-only list of factor versions associated with the recommendation version or an empty
	 * list if none are associated
	 */
	List<IDvFactorVersionBean> getAssociatedFactorVersions(String recommendationVersionId);

	/**
	 * Associates the Factor version with the given factorVersionId with the list of given people
	 * @param factorVersionId The id of the Factor version to associate with the list of given people
	 * @param people The list of people to associate with the factor version with the given factorVersionId
	 */
	void associateFactorVersionToPeople(String factorVersionId, List<IDvInvlvdPersonBean> people);

	/**
	 * Associates the Factor version with the given factorVersionId with the list of given properties
	 * @param factorVersionId The id of the Factor version to associate with the list of given properties
	 * @param properties The list of properties to associate with the factor version with the given factorVersionId
	 */
	void associateFactorVersionToProperties(String factorVersionId, List<IDvInvlvdPropertyBean> properties);

	/**
	 * Associates the Recommendation version with the given recommendationVersionId with the list of given factor versions 
	 * @param recommendationVersionId The id of the recommendation version to associate with the list of given factor versions
	 * @param factorVersions The list of factor versions to associate with the recommendation version with the given recommendationVersionId
	 */
	void associateRecommendationVersionToFactorVersions(String recommendationId, List<IDvFactorVersionBean> factorVersions);

	/**
	 * Retrieves the list of preconditions associated with the factor version given by the
	 * specified serial.
	 * @param factorVersionSerl The serial id of the factor version whose preconditions are
	 * to be retrieved
	 * @return A read-only view of the list of associated preconditions or an empty list if
	 * none are associated
	 */
	List<OptionItem> getPreconditions(String factorVersionSerl);

	/**
	 * Associates the given list of preconditions to the factor version given by the specified
	 * factor version serial.
	 * @param factorVersionSerl The serial identifier of the factor version to which the preconditions
	 * should be associated
	 * @param items The preconditions to associate
	 */
	void associatePreconditions(String factorVersionSerl, List<OptionItem> items);

	/**
	 * Disassociates the person with the given id from all factors
	 * @param id The id of the person to be disassociated from all the factors.
	 */
	void disassociatePerson(String id);

	/**
	 * Disassociates the property with the given id from all factors
	 * @param id The id of the property to be disassociated from all the factors
	 */
	void disassociateProperty(String id);

}
