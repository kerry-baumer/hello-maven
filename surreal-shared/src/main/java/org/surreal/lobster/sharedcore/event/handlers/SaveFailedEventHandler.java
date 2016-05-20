package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.SaveFailedEvent;


public interface SaveFailedEventHandler extends EventHandler {

	void onSaveFailed(SaveFailedEvent event, String message);
}
