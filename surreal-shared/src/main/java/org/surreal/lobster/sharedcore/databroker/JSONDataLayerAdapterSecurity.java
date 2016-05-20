package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

public interface JSONDataLayerAdapterSecurity {

	/**
	 * Obtains the embedded user permissions of the currently logged in user
	 * viewing the application.  
	 * @return The permissions of the current user
	 */
	public List<String> extractEmbeddedUserPerms();

	/**
	 * Obtains the embedded user identifier of the currently logged in user
	 * viewing the application.  The identifier is the EDIPI.
	 * @return The EDIPI of the current user
	 */
	public String extractEmbeddedUserId();

	/**Obtains the embedded user account type of the currently logged in user
	 * viewing the application. 
	 * @return The Account Type of the user currently logged into the system. 
	 */
	public String extractEmbeddedUserAccountType();

	
}
