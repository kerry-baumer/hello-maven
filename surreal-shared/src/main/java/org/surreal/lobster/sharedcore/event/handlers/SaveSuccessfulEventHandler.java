package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.SaveSuccessfulEvent;

public interface SaveSuccessfulEventHandler extends EventHandler {

	void onSaveSuccessful(SaveSuccessfulEvent event);
	
}
