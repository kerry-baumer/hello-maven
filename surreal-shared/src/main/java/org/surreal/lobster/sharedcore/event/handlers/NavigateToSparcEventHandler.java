package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.NavigateToSparcEvent;

public interface NavigateToSparcEventHandler extends EventHandler {

	void onNavigateToSparc(NavigateToSparcEvent navigateToSparcEvent);

}
