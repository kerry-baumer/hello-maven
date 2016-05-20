package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.AcceptEventHandler;

public class AcceptEvent implements Event<AcceptEventHandler> {

	public void dispatch(AcceptEventHandler handler) {
		handler.onAccept(this);
	}
}
