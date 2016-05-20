package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.SaveEventHandler;

public class SaveEvent implements Event<SaveEventHandler> {

	@Override
	public void dispatch(SaveEventHandler handler) {
		handler.onSave(this);
	}

}
