package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.DeleteFailedEventHandler;

public class DeleteFailedEvent implements Event<DeleteFailedEventHandler> {

	private Throwable cause;

	public DeleteFailedEvent() {
	}

	public DeleteFailedEvent(final Throwable cause) {
		this.cause = cause;
	}

	public Throwable getCause() {
		return cause;
	}

	@Override
	public void dispatch(final DeleteFailedEventHandler handler) {
		handler.onDeleteFailed(this);
	}
}
