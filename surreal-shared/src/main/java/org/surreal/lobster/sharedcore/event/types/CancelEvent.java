package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.CancelEventHandler;

public class CancelEvent implements Event<CancelEventHandler> {

	@Override
	public void dispatch(CancelEventHandler handler) {
		handler.onCancel(this);
	}

}
