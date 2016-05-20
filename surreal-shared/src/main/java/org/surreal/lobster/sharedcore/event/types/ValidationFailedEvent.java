package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ValidationFailedEventHandler;

public class ValidationFailedEvent implements Event<ValidationFailedEventHandler>{

	@Override
	public void dispatch(ValidationFailedEventHandler handler) {
		handler.onValidationFailed(this);
	}

}
