package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ReleaseEventHandler;

public class ReleaseEvent implements Event<ReleaseEventHandler> {

	@Override
	public void dispatch(ReleaseEventHandler handler) {
		handler.onRelease(this);
	}

}
