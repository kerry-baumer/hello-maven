package org.surreal.lobster.sharedcore.constants;

/**
 * The status indicators used by the JBoss ESB SQL-Gateway.  A blank value
 * in the status column is disregarded by the Gateway.<p>
 * 
 * Contains the default list of possible states for the
 * status-column in the sql-message-filter attribute.
 * The ESB listener listens for records where the status-column
 * is pending and the data meets the whereCondition
 * attribute conditions. 
 * @author linda.j.smith
 */
public enum StatusState {
	
	/** User is editing/creating the Initial Notification */
	R("Draft"),
	
	/** Waiting for ESB processing */
	P("Pending"),
	
	/** Processed by the ESB-Gateway */
	W("Working"), 
	
	/** An error occurred */
	E("Error"),
	
	/** Processed by the ESB-Actions */
	D("Done");
	
	/** The status property */
	private String status;
	
	/**
	 * @param status
	 */
	private StatusState(String status) {
		this.status = status;
	}
	
	/**
	 * Accessor for property status.
	 * @return The status
	 */
	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {		
		return status;
	}	
}