package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.AcceptFailedEventHandler;

public class AcceptFailedEvent implements Event<AcceptFailedEventHandler> {

	public void dispatch(AcceptFailedEventHandler handler) {
		handler.onAcceptFailed(this);
	}

}
