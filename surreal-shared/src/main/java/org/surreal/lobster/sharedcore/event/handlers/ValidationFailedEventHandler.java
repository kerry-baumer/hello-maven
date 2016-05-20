package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ValidationFailedEvent;

public interface ValidationFailedEventHandler extends EventHandler{

	void onValidationFailed(ValidationFailedEvent validationFailedEvent);

}
