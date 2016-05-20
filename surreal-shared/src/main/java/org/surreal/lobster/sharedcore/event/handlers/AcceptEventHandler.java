package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.AcceptEvent;

public interface AcceptEventHandler extends EventHandler {

	void onAccept(AcceptEvent event);
}
