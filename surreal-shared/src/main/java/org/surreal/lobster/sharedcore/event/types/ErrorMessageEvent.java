package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ErrorMessageHandler;

public class ErrorMessageEvent implements Event<ErrorMessageHandler> {

	private String message;
	private boolean visible;

	public ErrorMessageEvent(String message, boolean visible) {
		this.message = message;
		this.visible = visible;
	}
	
	@Override
	public void dispatch(ErrorMessageHandler handler) {
		handler.onErrorMessage(this, visible, message);
	}

}
