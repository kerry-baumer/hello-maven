package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.AcceptFailedEvent;


public interface AcceptFailedEventHandler extends EventHandler {

	void onAcceptFailed(AcceptFailedEvent event);
}
