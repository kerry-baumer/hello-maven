package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ValidationEventHandler;

public class ValidationEvent implements Event<ValidationEventHandler> {

	private final boolean forcedValidation;
	
	public ValidationEvent() {
		this(false);
	}
	
	public ValidationEvent(boolean forcedValidation) {
		this.forcedValidation = forcedValidation;
	}
	
	@Override
	public void dispatch(ValidationEventHandler handler) {
		handler.onValidate(this);
	}

	public boolean isForcedValidation() {
		return forcedValidation;
	}
	
}
