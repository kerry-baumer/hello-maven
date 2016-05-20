package org.surreal.lobster.sharedcore.user;

import java.util.List;
import java.util.Set;

/**
 * INFO:SEAN - IN THE FUTURE DO NOT USE THIS INTERFACE
 * USE THE USER ACCOUNT INDEX READER FACTORY FROM THE LANTARU JAR
 * DO NOT USE
 * DO NOT USE
 * DO NOT DO NOT DO NOT USE
 * PLEASE PLEASE DO NOT USE THIS
 * DON'T CONTINUE THE NASTINESS
 * 
 * @deprecated Please consider using Lantaru directly before expanding the behavior in this class.
 */
@Deprecated
public interface IUserAccountCacheEntryLocator {

	UserAccount findUserNullOk(String ediPi);

	UserAccount findUser(String ediPi);

	public UserAccount findUserFromEmail(String email);

	public List<UserAccount> findSafetyAuthorities(String uic);

	public List<String> getNotifiableEdiPiInPermission(String permission);
	
	public List<String> getNotifiableEmailInPermission(String permission);

	public List<String> getNotifiableEdiPiInRole(String role);

	public List<String> getNotifiableEmailInRole(String role);

	public List<String> getNotifiableEdiPiInRoleAndUic(String role, String uic);

	public List<String> getNotifiableEmailInRoleAndUic(String role, String uic);

	// THIS IS SO AWFULLY HIDEOUS
	public List<String> getNotifiableEmailInPermissionAndUic(String permission, String uic);

	public List<String> getNotifiableEdiPiInPermissionAndUic(String permission, String uic);

	public List<UserAccount> getUserAccountsInPermissionAndUic(String permission, String uic);

	public Set<String> getUserPermissions(String ediPi);

	String getUserAccountType(String ediPi);
}