package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.NavigationEvent;

public interface NavigationEventHander extends EventHandler {
	
	void onNavigation(NavigationEvent event);

}
