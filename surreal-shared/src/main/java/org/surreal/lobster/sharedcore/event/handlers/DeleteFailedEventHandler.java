package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.DeleteFailedEvent;

public interface DeleteFailedEventHandler extends EventHandler {

	void onDeleteFailed(DeleteFailedEvent event);
}
