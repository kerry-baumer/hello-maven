package org.surreal.lobster.sharedcore.constants;

public enum AccountType {
	
	NOBODY("Nobody", "Reserved fo users that have not completed an access request."),
	WC("WESS Customer", "The baseline account type for normal users."),
	SA("WESS Safety Authority", "SOMETHING ABOUT SA"),
	DA("WESS Designated Authority", "SOMETHING ABOUT DA"),
	WA("WESS Administrator", "A system level administrator with all access.");
	
	private String shortDescription;
	private String longDescription;

	private AccountType(String shortDescription, String longDescription) {
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}
	
	/**
	 * Get the short description for this AccountType
	 * @return
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Get the long description for this AccountType
	 * @return
	 */
	public String getLongDescription() {
		return longDescription;
	}
}
