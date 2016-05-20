/**
 * Feb 13, 2012 4:28:18 PM
 */
package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

/**
 * Provides security model access.  
 * 
 * Provides methods accessing the security associated with the current user
 * @author christopher.d.grimes
 */
public interface SecurityDataBroker {
	
	/**
	 * Obtains the EDIPI of the current user logged into the application.
	 * @return The EDIPI of this user
	 */
	public String getCurrentUserEdipi();

	/**
	 * Returns a List<String> of the current user's permissions.
	 * @return a List<String> of the current user's permissions.
	 */
	List<String> getCurrentUserPermissions();

	/**
	 * Obtains the Account Type of the current user logged into the application.
	 * @return User Account Type (ex: WC)
	 */
	String getCurrentUserAccountType();

}
