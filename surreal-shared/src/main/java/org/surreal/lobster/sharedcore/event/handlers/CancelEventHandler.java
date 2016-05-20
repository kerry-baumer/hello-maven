package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.CancelEvent;

public interface CancelEventHandler extends EventHandler {

	void onCancel(CancelEvent event);
	
}
