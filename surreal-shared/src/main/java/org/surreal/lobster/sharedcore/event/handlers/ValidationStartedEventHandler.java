package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ValidationStartedEvent;

public interface ValidationStartedEventHandler extends EventHandler {

	void onValidationStarted(ValidationStartedEvent validationStartedEvent);

}
