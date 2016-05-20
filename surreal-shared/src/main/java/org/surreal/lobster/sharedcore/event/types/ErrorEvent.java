package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ErrorEventHandler;

public class ErrorEvent implements Event<ErrorEventHandler> {

	private final Throwable caught;

	public ErrorEvent(Throwable caught) {
		this.caught = caught;
	}

	@Override
	public void dispatch(ErrorEventHandler handler) {
		handler.onError(this);
	}

	public Throwable getCaught() {
		return caught;
	}
}
