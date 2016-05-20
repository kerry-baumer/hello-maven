package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.EditReportEvent;

public interface EditReportEventHandler extends EventHandler {

	void onEditReport(EditReportEvent event, String reportSerial);
}
