package org.surreal.lobster.sharedcore.util;

public enum UserAccountType {
	SAFETY_AUTHORITY("SA"),
	DESIGNATED_AUTHORITY("DA"),
	WESS_ADMINISTRATOR("WA");
	
	private String accountTypeCode;
	
	private UserAccountType(final String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}
	public String getAccountTypeCode() {
		return this.accountTypeCode;
	}

}
