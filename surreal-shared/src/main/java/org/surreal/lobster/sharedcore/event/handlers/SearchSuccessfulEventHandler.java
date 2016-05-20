package org.surreal.lobster.sharedcore.event.handlers;

import java.util.List;

import org.surreal.lobster.sharedcore.event.types.SearchSuccessfulEvent;
import org.surreal.lobster.sharedcore.model.SearchEditResultItemProxy;

public interface SearchSuccessfulEventHandler extends EventHandler {

	void onSearchSuccessful(SearchSuccessfulEvent event, List<SearchEditResultItemProxy> items);
	
}
