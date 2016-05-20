package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ServiceStatusChangedEvent;

public interface ServiceStatusChangedEventHandler extends EventHandler {

	void onServiceStatusChanged(ServiceStatusChangedEvent event);
	
}
