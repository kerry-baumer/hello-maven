package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.SaveEvent;

public interface SaveEventHandler extends EventHandler {

	void onSave(SaveEvent event);
	
}
