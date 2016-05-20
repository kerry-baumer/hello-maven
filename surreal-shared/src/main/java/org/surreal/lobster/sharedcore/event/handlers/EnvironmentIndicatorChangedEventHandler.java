package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.EnvironmentIndicatorChangedEvent;

public interface EnvironmentIndicatorChangedEventHandler extends EventHandler {

	void onEnvironmentIndicatorChanged(EnvironmentIndicatorChangedEvent event);
}
