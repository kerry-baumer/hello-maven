package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.SearchFailedEventHandler;

public class SearchFailedEvent implements Event<SearchFailedEventHandler> {

	private final String message;

	public SearchFailedEvent(String message) {
		this.message = message;
	}
	
	@Override
	public void dispatch(SearchFailedEventHandler handler) {
		handler.onSearchFailed(this, message);
	}

}
