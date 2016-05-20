package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.SaveSuccessfulEventHandler;

public class SaveSuccessfulEvent implements Event<SaveSuccessfulEventHandler> {

	@Override
	public void dispatch(SaveSuccessfulEventHandler handler) {
		handler.onSaveSuccessful(this);
	}

}
