package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.EventHandler;

public interface Event<T extends EventHandler> {

	void dispatch(T handler);
}
