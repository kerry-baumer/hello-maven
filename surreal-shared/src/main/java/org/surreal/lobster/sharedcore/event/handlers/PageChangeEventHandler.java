package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.PageChangeEvent;

public interface PageChangeEventHandler extends EventHandler {

	void onPageChange(PageChangeEvent event);

}
