package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDv72HrProfileBean;
import org.surreal.lobster.sharedcore.model.draft.IDvBodyPartBean;
import org.surreal.lobster.sharedcore.model.draft.IDvInjuryBean;
import org.surreal.lobster.sharedcore.model.draft.IDvInvlvdChemicalBean;
import org.surreal.lobster.sharedcore.model.draft.IDvInvlvdPersonBean;
import org.surreal.lobster.sharedcore.model.draft.IDvLicenseBean;
import org.surreal.lobster.sharedcore.model.draft.IDvLostWorkTimeBean;
import org.surreal.lobster.sharedcore.model.draft.IDvOffsiteMedicalBean;
import org.surreal.lobster.sharedcore.model.draft.IDvPpeBean;
import org.surreal.lobster.sharedcore.model.draft.IDvSharpsBean;

/**
 * Provides data model access, manipulation, load, and save services for the client.  This 
 * interface manages the creation and wiring of the data model.  All model instances returned
 * by this broker will have a non-<code>null</code> unique identifier assigned in the bean.<p>
 * 
 * Provides methods for loading and saving data associated with people involved in
 * a mishap or hazard.
 * @author daniel.wilkin
 */
public interface InvolvedPersonDataBroker extends DelegatingDataBroker {
	
	/**
	 * Retrieves the list of persons associated with the event.  The result
	 * may be empty. 
	 * 
	 * @return A non-<code>null</code>, read only view of the persons associated
	 * with the event such that any changes to the returned list will not be reflected into
	 * the stored list and vice versa
	 */
	List<IDvInvlvdPersonBean> getInvolvedPersons();
	
	/**
	 * Determines if a person associated with the specified identifier exists within
	 * the data model.
	 * @param id The unique identifier of the person to locate
	 * @return <code>True</code> if the person exists, <code>false</code> otherwise
	 */
	boolean hasInvolvedPerson(String id);

	/**
	 * Retrieves the person that matches the specified unique identifier associated 
	 * with the event.
	 * 
	 * @param id The unique identifier
	 * @return A <code>IDvInvlvdPersonBean</code> matching the specified unique
	 * identifier
	 * @throws IllegalStateException If the person could not be found
	 */
	IDvInvlvdPersonBean getInvolvedPerson(String id);

	/**
	 * Creates a new person instance with a unique identifier and wires it to the model.
	 * 
	 * @return The new involved person, never <code>null</code>
	 */
	IDvInvlvdPersonBean addInvolvedPerson();

	/**
	 * Removes the person associated with the specified identifier.  All other beans
	 * are disassociated with this person.
	 * @param id The identifier of the person to be removed
	 */
	void removeInvolvedPerson(String id);

	/**
	 * Retrieves the 72 hour profile for the person associated with the specified identifier.
	 * @param id The identifier of the person whose 72 hour profile should be
	 * retrieved
	 * @return The 72 hour profile instance
	 */
	IDv72HrProfileBean get72HrProfile(String id);

	/**
	 * Retrieves the licenses for the person associated with the specified identifier.
	 * @param id The identifier of the person whose licenses should be retrieved
	 * @return The list of licenses as an immutable list, never <code>null</code>
	 */
	List<IDvLicenseBean> getLicenses(String id);

	/**
	 * Sets the list of licenses to be associated with the user given by the specified
	 * identifier. 
	 * @param licenseData The new list of license instances
	 * @param id The person to whom the licenses should be associated
	 */
	void setLicenses(List<IDvLicenseBean> licenseData, String id);

	/**
	 * Adds a license for the user associated with the specified identifier.
	 * @param id The person to whom the license should be associated
	 * @return The new license instance
	 */
	IDvLicenseBean addLicense(String id);

	/**
	 * Retrieves the list of PPE associated with the specified user.
	 * @param id The identifier of the person whose PPE is requested
	 * @return The list of PPE as an immutable list, never <code>null</code>
	 */
	List<IDvPpeBean> getPpe(String id);

	/**
	 * Sets the list of licenses to be associated with the user given by the specified
	 * identifier.
	 * @param ppeData The new list of PPE instances
	 * @param id The person to whom the PPE should be associated
	 */
	void setPpe(List<IDvPpeBean> ppeData, String id);

	/**
	 * Adds a PPE for the user associated with the specified identifier.
	 * @param id The person to whom the PPE should be associated
	 * @return The new PPE instance
	 */
	IDvPpeBean addPpe(String id);

	/**
	 * Retrieves the injury instance data associated with the person given by the 
	 * specified identifier.
	 * @param id The identifier of the person whose injury data is requested
	 * @return The injury instance, never <code>null</code>
	 */
	IDvInjuryBean getInjury(String id);

	/**
	 * Retrieves the sharps instance data associated with the person given by the
	 * specified identifier.
	 * @param id The identifier of the person whose sharp data is requested
	 * @return The sharps instance, never <code>null</code>
	 */
	IDvSharpsBean getSharps(String id);

	/**
	 * Determines if injury data is associated with the person given by the 
	 * specified identifier.
	 * @param id The identifier of the person to query
	 * @return <code>True</code> if the person has injury data, <code>false</code> otherwise
	 */
	boolean hasInjury(String id);

	/**
	 * Determines if sharps data is associated with the person given by the 
	 * specified identifier.
	 * @param id The identifier of the person to query
	 * @return <code>True</code> if the person has sharps data, <code>false</code> otherwise
	 */
	boolean hasSharps(String id);

	/**
	 * Retrieves the off-site medical instance data associated with the person given by the
	 * specified identifier.
	 * @param id The identifier of the person whose off-site medical data is requested
	 * @return The off-site medical instance, never <code>null</code>
	 */
	IDvOffsiteMedicalBean getOffsiteMedical(String id);

	/**
	 * Determines if off-site medical data is associated with the person given by the 
	 * specified identifier.
	 * @param id The identifier of the person to query
	 * @return <code>True</code> if the person has off-site medical data, <code>false</code> otherwise
	 */
	boolean hasOffsiteMedical(String id);

	/**
	 * Retrieves the list of body parts associated with the specified person.
	 * @param id The identifier of the person whose body parts are requested
	 * @return The list of body parts as an immutable list, never <code>null</code>
	 */
	List<IDvBodyPartBean> getBodyPart(String id);

	/**
	 * Retrieves the list of involved chemicals associated with the specified person.
	 * @param id The identifier of the person whose involved chemicals are requested
	 * @return The list of chemicals as an immutable list, never <code>null</code>
	 */
	List<IDvInvlvdChemicalBean> getChemical(String id);

	/**
	 * Retrieves the list of lost work time (hospital) associated with the specified person.
	 * @param id The identifier of the person whose lost work time (hospital) is requested
	 * @return The list of lost work time (hospital) as an immutable list, never <code>null</code>
	 */
	List<IDvLostWorkTimeBean> getHospital(String id);

	/**
	 * Retrieves the list of lost work time (light duty) associated with the specified person.
	 * @param id The identifier of the person whose lost work time (light duty) is requested
	 * @return The list of lost work time (light duty) as an immutable list, never <code>null</code>
	 */
	List<IDvLostWorkTimeBean> getLightDuty(String id);

	/**
	 * Retrieves the list of lost work time (Lwd) associated with the specified person.
	 * @param id The identifier of the person whose lost work time (Lwd) is requested
	 * @return The list of lost work time (Lwd) as an immutable list, never <code>null</code>
	 */
	List<IDvLostWorkTimeBean> getLostWorkDays(String id);

	/**
	 * Retrieves the list of lost work time (restricted duty) associated with the specified person.
	 * @param id The identifier of the person whose lost work time (restricted duty) is requested
	 * @return The list of lost work time (restricted duty) as an immutable list, never <code>null</code>
	 */
	List<IDvLostWorkTimeBean> getRestrictedDuty(String id);

	/**
	 * Retrieves the list of lost work time (Xfr) associated with the specified person.
	 * @param id The identifier of the person whose lost work time (Xfr) is requested
	 * @return The list of lost work time (Xfr) as an immutable list, never <code>null</code>
	 */
	List<IDvLostWorkTimeBean> getXfr(String id);

	/**
	 * Sets the list of body parts to be associated with the person given by the specified
	 * identifier.
	 * @param bodyPartData The new list of body part instances
	 * @param id The person to whom the body parts should be associated
	 */
	void setBodyPart(List<IDvBodyPartBean> bodyPartData, String id);

	/**
	 * Sets the list of chemicals to be associated with the person given by the specified
	 * identifier.
	 * @param chemicalData The new list of chemical instances
	 * @param id The person to whom the chemicals should be associated
	 */
	void setChemical(List<IDvInvlvdChemicalBean> chemicalData, String id);

	/**
	 * Sets the list of lost work time (hospital) to be associated with the person given by the specified
	 * identifier.
	 * @param hospitalData The new list of lost work time (hospital) instances
	 * @param id The person to whom the lost work times (hospital) should be associated
	 */
	void setHospital(List<IDvLostWorkTimeBean> hospitalData, String id);

	/**
	 * Sets the list of lost work time (light duty) to be associated with the person given by the specified
	 * identifier.
	 * @param lightDutyData The new list of lost work time (light duty) instances
	 * @param id The person to whom the lost work times (light duty) should be associated
	 */
	void setLightDuty(List<IDvLostWorkTimeBean> lightDutyData, String id);

	/**
	 * Sets the list of lost work time (lwd) to be associated with the person given by the specified
	 * identifier.
	 * @param lwdData The new list of lost work time (lwd) instances
	 * @param id The person to whom the lost work times (lwd) should be associated
	 */
	void setLostWorkDays(List<IDvLostWorkTimeBean> lwdData, String id);

	/**
	 * Sets the list of lost work time (lwd) to be associated with the person given by the specified
	 * identifier.
	 * @param lwdData The new list of lost work time (lwd) instances
	 * @param id The person to whom the lost work times (lwd) should be associated
	 */
	void setRestrictedDuty(List<IDvLostWorkTimeBean> restrictedDutyData, String id);

	/**
	 * Sets the list of lost work time (xfr) to be associated with the person given by the specified
	 * identifier.
	 * @param xfrData The new list of lost work time (xfr) instances
	 * @param id The person to whom the lost work times (xfr) should be associated
	 */
	void setXfr(List<IDvLostWorkTimeBean> xfrData, String id);

	/**
	 * Adds a body part for the person associated with the specified identifier.
	 * @param id The person to whom the body part should be associated
	 * @return The new body part instance
	 */
	IDvBodyPartBean addBodyPart(String id);

	/**
	 * Adds a chemical for the person associated with the specified identifier.
	 * @param id The person to whom the chemical should be associated
	 * @return The new chemical instance
	 */
	IDvInvlvdChemicalBean addChemical(String id);

	/**
	 * Adds a lost work time (hospital) for the person associated with the specified identifier.
	 * @param id The person to whom the lost work time (hospital) should be associated
	 * @return The new lost work time (hospital) instance
	 */
	IDvLostWorkTimeBean addHospital(String id);

	/**
	 * Adds a lost work time (light duty) for the person associated with the specified identifier.
	 * @param id The person to whom the lost work time (light duty) should be associated
	 * @return The new lost work time (light duty) instance
	 */
	IDvLostWorkTimeBean addLightDuty(String id);

	/**
	 * Adds a lost work time (lwd) for the person associated with the specified identifier.
	 * @param id The person to whom the lost work time (lwd) should be associated
	 * @return The new lost work time (lwd) instance
	 */
	IDvLostWorkTimeBean addLostWorkDays(String id);

	/**
	 * Adds a lost work time (restricted duty) for the person associated with the specified identifier.
	 * @param id The person to whom the lost work time (restricted duty) should be associated
	 * @return The new lost work time (restricted duty) instance
	 */
	IDvLostWorkTimeBean addRestrictedDuty(String id);

	/**
	 * Adds a lost work time (xfr) for the person associated with the specified identifier.
	 * @param id The person to whom the lost work time (xfr) should be associated
	 * @return The new lost work time (xfr) instance
	 */
	IDvLostWorkTimeBean addXfr(String id);

	void setInvolvedEntityDataBroker(InvolvedEntityDataBroker involvedEntityDataBrokerGwtImpl);
}
