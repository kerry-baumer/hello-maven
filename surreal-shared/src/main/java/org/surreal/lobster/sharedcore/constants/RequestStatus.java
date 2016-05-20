package org.surreal.lobster.sharedcore.constants;

import static org.surreal.lobster.sharedcore.constants.AccountType.WA;

/**
 * The status indicator for a given <code>AccessRequest</code>.  Each
 * {@link RequestType} has it's own progression of status values. The
 * progression for any AccessRequest of a specific RequestType thru it's
 * <code>RequestStatus</code> values is given by {@link #getNext()} and
 * {@link #getNext(AccountType)}.  The initial status values for the various
 * <code>RequestType</code>s are given below:<ul><li>
 *    <code>RequestType.EMAIL_VERIFICATION</code> = {@link #PENDING_EMAIL}<li>
 *    <code>RequestType.UIC_CHANGE</code> = {@link #WAITING}<li>
 *    <code>RequestType.SUBSYSTEM_ACCESS</code> = {@link #WAITING}</ul>
 * Note that while these status indicators suggest who must evaluate a
 * request next before it may be approved, AccessRequests are held dormant
 * until certain pre-conditions are satisfied before the request is routed
 * to the individual for which the request is pending.  The routing of all
 * requests is managed by the <code>RequestNotifierEngine</code>.
 * @author michael.s.mckinney
 */
public enum RequestStatus {
	COMPLETE("This request has been granted and is final.", null),
	WAITING("This request is waiting for a previous request to be completed.", null),
	PENDING_WA("This request is pending final review by a WA.", COMPLETE),
	PENDING_VALID_SS("This request is pending a valid SubSystem to be approved by DA", PENDING_WA),
	PENDING_DA("This request is pending review by a DA.", PENDING_WA),
	PENDING_SA("This request is pending review by an SA.", PENDING_VALID_SS),
	PENDING_EMAIL("This request is pending email verification.", COMPLETE)
	;
	
	private String description;
	private RequestStatus next;
	
	private RequestStatus(String description, RequestStatus next) {
		this.description = description;
		this.next = next;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public RequestStatus getNext() {
		return getNext(null);
	}
	
	public RequestStatus getNext(AccountType type) {
		if (WA.equals(type)) { 
//			return COMPLETE;
			return next;
		} else if (next == null) {
			return this;
		}
		return next;
	}
}