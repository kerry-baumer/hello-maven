/**
 * 
 */
package org.surreal.lobster.sharedcore.user;


import java.io.Serializable;

import org.surreal.lobster.sharedcore.constants.AccountStatus;
import org.surreal.lobster.sharedcore.constants.AccountType;

 

/**
 * @author kerry.baumer
 *
 */
public interface UserAccount  extends Serializable {

	String getFirstName();
	void setFirstName(String string);

	String getLastName();
	void setLastName(String string);

	String getEdiPi();
	void setEdiPi(String string);

	String getUic();
	void setUic(String string);

	String getPhone();
	void setPhone(String string);

	String getEmail();
	void setEmail(String string);

	String getCitizen();
	void setCitizen(String string);
	
	String getMiddleName();
	void setMiddleName(String string);
	
	String getRank();
	void setRank(String string);

	AccountStatus getAccountStatus();
	void setAccountStatus(AccountStatus status);

	AccountType getAccountType();
	void setAccountType(AccountType type);
	
}
