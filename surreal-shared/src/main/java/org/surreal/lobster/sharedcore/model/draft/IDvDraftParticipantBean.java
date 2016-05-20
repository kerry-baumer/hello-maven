/**
 * Apr 17, 2012 10:19:08 AM
 */
package org.surreal.lobster.sharedcore.model.draft;


/**
 * A user involved in the drafting of an event.
 * @author daniel.wilkin
 */
public interface IDvDraftParticipantBean {
	
	String getFirstName();
	void setFirstName(String firstName);

	String getLastName();
	void setLastName(String lastName);

	String getEdiPi();
	void setEdiPi(String ediPi);

	String getUic();
	void setUic(String uic);

	String getPhone();
	void setPhone(String phone);

	String getEmail();
	void setEmail(String email);

	String getCitizen();
	void setCitizen(String citizen);
	
	String getMiddleName();
	void setMiddleName(String middleName);
	
	String getRank();
	void setRank(String rank);

	String getAccountStatus();
	void setAccountStatus(String accountStatus);

	String getAccountType();
	void setAccountType(String accountType);
	
	String getDsnPhone();
	void setDsnPhone(String dsnPhone);

}
