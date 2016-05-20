package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ErrorEvent;

public interface ErrorEventHandler extends EventHandler {

	void onError(ErrorEvent event);
}
