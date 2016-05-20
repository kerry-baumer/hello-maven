package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ValidationNavigationEvent;

public interface ValidationNavigationEventHandler extends EventHandler {
	
	void onErrorLineClicked(ValidationNavigationEvent event);

}
