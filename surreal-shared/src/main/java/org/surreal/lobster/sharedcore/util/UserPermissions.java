package org.surreal.lobster.sharedcore.util;

public enum UserPermissions {
	AFLOAT_RELEASE_DRAFT("AFLT_DRFT"),
	AFLOAT_CREATE_INITIAL_NOTIFICATION("AFLT_CRT_IN"),
	AFLOAT_CREATE_DRAFT("AFLT_CRT_DRFT"),
	SHORE_CREATE_INITIAL_NOTIFICATION("SHORE_CRT_IN"),
	SHORE_CREATE_DRAFT("SHORE_CRT_DRFT"),
	AVIATION_CREATE_DRAFT("AV_DATA_ENTRY"),
	DEVELOPER("DEVELOPER"),
	MV_CREATE_DRAFT("MISH_USER"),
	AVIATION_ENDORSER("ENDORSEMENT");

	private String permCode;

	private UserPermissions(final String permCode) {
		this.permCode = permCode;
	}

	public String getPermissionCode() {
		return permCode;
	}
}
