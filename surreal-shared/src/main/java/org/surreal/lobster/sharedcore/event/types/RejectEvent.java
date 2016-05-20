package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.RejectEventHandler;

public class RejectEvent implements Event<RejectEventHandler> {

	private String reason = null;
	
	public RejectEvent() {
		// empty
	}
	
	public RejectEvent(String reason) {
		this.reason = reason;
	}
	
	@Override
	public void dispatch(RejectEventHandler handler) {
		handler.onReject(this);
	}

	/**
	 * @return the reason
	 */
	public final String getReason() {
		return reason;
	}
}
