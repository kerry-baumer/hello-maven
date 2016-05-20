package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ValidationStartedEventHandler;

public class ValidationStartedEvent implements Event<ValidationStartedEventHandler>{

	@Override
	public void dispatch(ValidationStartedEventHandler handler) {
		handler.onValidationStarted(this);
	}

}
