package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.RejectFailedEvent;

public interface RejectFailedEventHandler extends EventHandler {

	void onRejectFailed(RejectFailedEvent event);
}
