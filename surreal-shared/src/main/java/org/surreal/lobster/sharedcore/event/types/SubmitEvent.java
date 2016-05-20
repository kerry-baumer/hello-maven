package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.SubmitEventHandler;

public class SubmitEvent implements Event<SubmitEventHandler> {

	@Override
	public void dispatch(SubmitEventHandler handler) {
		handler.onSubmit(this);
	}
	
}
