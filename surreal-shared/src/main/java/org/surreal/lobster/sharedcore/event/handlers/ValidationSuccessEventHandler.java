package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ValidationSuccessEvent;

public interface ValidationSuccessEventHandler extends EventHandler {

	void onValidationSuccess(ValidationSuccessEvent validationSuccessEvent);

}
