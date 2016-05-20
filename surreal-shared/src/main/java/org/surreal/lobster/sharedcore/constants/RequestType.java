package org.surreal.lobster.sharedcore.constants;

import java.util.ArrayList;
import java.util.List;

public enum RequestType {
	UNKNOWN("Unknown, please notify support."), 
	EMAIL_VERIFICATION("verify user email address"),
	SUBSYSTEM_ACCESS("request to gain access to a sub system"), 
	UIC_CHANGE("request to change UIC");
	
	private String description;
	
	private RequestType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Returns a proper case string version name of this request type minus the underscore 
	 * e.g. type "SUBSYSTEM_ACCESS" returns "Subsystem Access"
	 * There is also a hard list of strings that 
	 * 
	 * @return A proper case string version name of this request type
	 */
	public String getName() {
		
		List<String> allCaps = new ArrayList<String>();
		allCaps.add("UIC");
		
		String[] ar = this.toString().split("_");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ar.length; i++) {
			if (i>0) {
				sb.append(' ');
			}
			if (allCaps.contains(ar[i].toUpperCase())) {
				sb.append(ar[i].toUpperCase());
			} else {
				sb.append(ar[i].substring(0,1).toUpperCase());
				sb.append(ar[i].substring(1,ar[i].length()).toLowerCase());
			}
		}
		return sb.toString();
	}
}
