package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ViewPdfEvent;

public interface ViewPdfEventHandler extends EventHandler {
	
	void onViewPdf(ViewPdfEvent event);

}
