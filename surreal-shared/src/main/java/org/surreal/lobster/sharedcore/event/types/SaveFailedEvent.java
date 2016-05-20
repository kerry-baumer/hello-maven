package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.SaveFailedEventHandler;

public class SaveFailedEvent implements Event<SaveFailedEventHandler> {

	private final String message;

	public SaveFailedEvent(String message) {
		this.message = message;
	}
	
	@Override
	public void dispatch(SaveFailedEventHandler handler) {
		handler.onSaveFailed(this, message);
	}

}
