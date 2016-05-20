package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.SearchFailedEvent;


public interface SearchFailedEventHandler extends EventHandler {

	void onSearchFailed(SearchFailedEvent event, String message);
}
