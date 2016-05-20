package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ReleaseFailedEvent;


public interface ReleaseFailedEventHandler extends EventHandler {

	void onReleaseFailed(ReleaseFailedEvent event);

}
