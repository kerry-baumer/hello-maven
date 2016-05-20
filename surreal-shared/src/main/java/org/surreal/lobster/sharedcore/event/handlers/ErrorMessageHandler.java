package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ErrorMessageEvent;

public interface ErrorMessageHandler extends EventHandler {
	
	void onErrorMessage(ErrorMessageEvent event, boolean visible, String message);

}
