package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ReleaseEvent;

public interface ReleaseEventHandler extends EventHandler {

	void onRelease(ReleaseEvent releaseEvent);

}
