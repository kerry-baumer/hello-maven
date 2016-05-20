package org.surreal.lobster.sharedcore.constants;

public enum AccountStatus {
	ACTIVE("This is a normal active account."),
	SUSPENDED("This is a suspended account."),
	PENDING("This is a pending account (new or moving uic?)"),
	IDLE("The account has exceeded maximum allowable idle time."),
	DELETED("This account has been deleted (figuratively)");
	
	private String description;
	
	private AccountStatus(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
