package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.NavigateToSparcEventHandler;

public class NavigateToSparcEvent implements Event<NavigateToSparcEventHandler> {

	@Override
	public void dispatch(NavigateToSparcEventHandler handler) {
		handler.onNavigateToSparc(this);
	}

}
