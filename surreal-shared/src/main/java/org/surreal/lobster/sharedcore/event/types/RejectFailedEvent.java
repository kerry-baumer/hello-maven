package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.RejectFailedEventHandler;

public class RejectFailedEvent implements Event<RejectFailedEventHandler> {

	private Throwable cause;

	public RejectFailedEvent() {
	}

	public RejectFailedEvent(final Throwable cause) {
		this.cause = cause;
	}

	public Throwable getCause() {
		return cause;
	}

	@Override
	public void dispatch(final RejectFailedEventHandler handler) {
		handler.onRejectFailed(this);
	}
}
