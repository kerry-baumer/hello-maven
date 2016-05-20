package org.surreal.lobster.sharedcore.event.types;

import java.util.List;

import org.surreal.lobster.sharedcore.event.handlers.SearchSuccessfulEventHandler;
import org.surreal.lobster.sharedcore.model.SearchEditResultItemProxy;

public class SearchSuccessfulEvent implements Event<SearchSuccessfulEventHandler> {

	List<SearchEditResultItemProxy> items;
	
	public SearchSuccessfulEvent(List<SearchEditResultItemProxy> items) {
		this.items = items;
	}
	
	@Override
	public void dispatch(SearchSuccessfulEventHandler handler) {
		handler.onSearchSuccessful(this, items);
	}

}
