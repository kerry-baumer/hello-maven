package org.surreal.lobster.sharedcore.constants;



/**
 * The states in the promotion system.
 * @author daniel.wilkin
 * @author earl.a.hill
 */
public enum PromotionState implements StateIdentifier { 
	INITIAL_NOTIFICATION("Initial Notification"),
	ACTION_NEEDED("Action Needed"),
	DELETED("Deleted"),
	DONE_WITH_QA("Done with Q/A"),
	DRAFT("Draft"),
	EDIT_RELEASED("Edit Released"),
	EDIT("Edit"),
	ENDORSEMENT("Endorsement"),
	REJECTED_BY_QA("Rejected by Q/A"),
	REJECTED_BY_ROUTING_CHAIN("Rejected by Routing Chain"),
	REQUEST_DELETE("Delete Requested"),
	RESUBMIT_TO_QA("Resubmit to Q/A"),
	SUBMITTED_TO_QA("Submitted to Q/A"),
	IMPORT("Imported"),
	NOTIFICATION_ONLY("Notification Only"),
	QA_REVIEW_ENDORSEMENTS("Q/A review Endorsements");
	private String label;
	PromotionState(String desc) {
		this.label = desc;
	}
	public String getLabel() {
		return label;
	}
}

