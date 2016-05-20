package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.SubmitEvent;

public interface SubmitEventHandler extends EventHandler {

	void onSubmit(SubmitEvent event);
}
