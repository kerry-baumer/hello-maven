package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.RejectEvent;

public interface RejectEventHandler extends EventHandler {

	void onReject(RejectEvent event);
}
