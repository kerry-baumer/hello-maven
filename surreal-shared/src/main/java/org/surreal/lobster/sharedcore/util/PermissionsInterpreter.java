package org.surreal.lobster.sharedcore.util;

import java.util.List;

import org.surreal.lobster.sharedcore.databroker.SecurityDataBroker;

public class PermissionsInterpreter {

	List<String> userPermissions;
	String accountType;

	public PermissionsInterpreter(final SecurityDataBroker db) {
		userPermissions = db.getCurrentUserPermissions();
		accountType = db.getCurrentUserAccountType();
	}

	public boolean canAccessDeveloperOnlyFields() {
		return userPermissions.contains(UserPermissions.DEVELOPER.getPermissionCode());
	}
	
	public boolean canReleaseAfloatDrafts() {
		return userPermissions.contains(UserPermissions.AFLOAT_RELEASE_DRAFT.getPermissionCode());
	}

	public boolean canCreateAfloatInitialNotificiations() {
		return userPermissions.contains(UserPermissions.AFLOAT_CREATE_INITIAL_NOTIFICATION.getPermissionCode());
	}

	public boolean canCreateAfloatDrafts() {
		return userPermissions.contains(UserPermissions.AFLOAT_CREATE_DRAFT.getPermissionCode());
	}

	public boolean canCreateShoreGroundInitialNotifications() {
		// THIS DOES NOT EXIST IN ANY DATABASE YET 5-27-14
		return userPermissions.contains(UserPermissions.SHORE_CREATE_INITIAL_NOTIFICATION.getPermissionCode());
	}
	
	public boolean canCreateShoreGroundDrafts() {
		// THIS DOES NOT EXIST IN ANY DATABASE YET 5-27-14
		return userPermissions.contains(UserPermissions.SHORE_CREATE_DRAFT.getPermissionCode());
	}
	
	public boolean canCreateMotorVehicleDrafts() {
		return true; // if you're in sparc you're good.
	}
	
	public boolean canCreateMotorVehicleInitialNotifications() {
		return true; // if you're in sparc you're good
	}

	public boolean canCreateAviationDrafts() {
		return userPermissions.contains(UserPermissions.AVIATION_CREATE_DRAFT.getPermissionCode()) ||
				userPermissions.contains(UserPermissions.AVIATION_ENDORSER.getPermissionCode());
	}
	
	public boolean canCreateAviationInitialNotifications() {
		return canCreateAviationDrafts();
	}

	public boolean canNavigateToCollective() {
		return userPermissions.contains(UserPermissions.DEVELOPER.getPermissionCode()) ||
				UserAccountType.SAFETY_AUTHORITY.getAccountTypeCode().equals(accountType) ||
				UserAccountType.DESIGNATED_AUTHORITY.getAccountTypeCode().equals(accountType) ||
				UserAccountType.WESS_ADMINISTRATOR.getAccountTypeCode().equals(accountType);
	}
}
