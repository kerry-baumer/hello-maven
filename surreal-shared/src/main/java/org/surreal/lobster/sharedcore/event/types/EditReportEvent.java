package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.EditReportEventHandler;

public class EditReportEvent implements Event<EditReportEventHandler> {

	private final String message;

	public EditReportEvent(String message) {
		this.message = message;
	}
	
	@Override
	public void dispatch(EditReportEventHandler handler) {
		handler.onEditReport(this, message);
	}

}
