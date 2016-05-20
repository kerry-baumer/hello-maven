/**
 * Oct 10, 2012 4:30:45 PM
 */
package org.surreal.lobster.sharedcore.user;

import java.util.List;

import org.surreal.lobster.sam.model.Role;

/**
 * A user account with roles and permissions.
 * @author daniel.wilkin
 */
public interface PrivilegedUser extends UserAccount {

	List<Role> getUserRoles();
	void setUserRoles(List<Role> userRoles);
	
}
