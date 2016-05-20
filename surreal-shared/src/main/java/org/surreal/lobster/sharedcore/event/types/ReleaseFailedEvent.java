package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ReleaseFailedEventHandler;

public class ReleaseFailedEvent implements Event<ReleaseFailedEventHandler> {

	@Override
	public void dispatch(final ReleaseFailedEventHandler handler) {
		handler.onReleaseFailed(this);
	}
}
