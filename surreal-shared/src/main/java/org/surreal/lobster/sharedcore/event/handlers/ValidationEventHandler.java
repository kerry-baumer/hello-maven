package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ValidationEvent;


public interface ValidationEventHandler extends EventHandler {
	
	void onValidate(ValidationEvent event);
}
