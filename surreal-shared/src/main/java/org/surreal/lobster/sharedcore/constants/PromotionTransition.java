package org.surreal.lobster.sharedcore.constants;


/**
 * All possible actions in the promotion system.
 * @author daniel.wilkin
 */
public enum PromotionTransition implements StateTransition {

	/** 
	 * The CREATE transition. Not a promotion transition only used for promotion history 
	 * auditing when an initial notification creates a draft event.
	 */
	CREATE, 
	/** The DELETE transition.  Any user may choose to delete the event */
	DELETE,
	/** The RELEASE transition.  A qualified user from the reporting command may submit the event to QA. */
	RELEASE,
	/** The ROUTE transition.  A qualified user in the reporting command may route to their superior(s). */
	ROUTE, 
	/** The SHARE transition.  An authorized drafter can add additional authorized drafters. */ 
	SHARE,
	/** The REJECT transition.  QA or a qualified router may reject an event. */
	REJECT,
	/** The EDIT transition.  Any user may call an event back for edit. */
	EDIT,
	/** The ENDORSE transition.  A qualified endorser may endorse an event. */
	ENDORSE,
	/** The REENDORSE transition.  A qualified endorser may re-endorse an event. */
	REENDORSE,
	/** The FINAL_ENDORSE transition.  The last qualified endorser in the endorsing chain may perform the final endorsement. */
	FINAL_ENDORSE,
	/** The RESTART transition.  QA may signal the endorsing chain to restart the endorsement process. */
	RESTART,
	/** The RECONVENE transition.  A qualified endorser may signal the reporting command to reconvene for analysis. */
	RECONVENE,
	/** The ACCEPT transition.  QA may accept the event for publication or allow the deletion of an event. */
	ACCEPT,
	/** The IMPORT Transition, QA may view and edit the report. Upon Accept, the report moves to RELEASE transition **/
	IMPORT,
	/** 
	 * The NOTIFICATION transition. Not a promotion transition only used for
	 * promotion history auditing 
	 */
	NOTIFICATION,
	/** 
	 * The REPOST transition. Not a promotion transition only to record
	 * event re-posting by janitor 
	 */
	REPOST,
	/** 
	 * The MDR transition. Not a promotion transition only used for
	 * promotion history auditing 
	 */
	MDR, 
	GRANT_EXTENSION, DENY_EXTENSION, ENDORSE_CHANGE;
}
