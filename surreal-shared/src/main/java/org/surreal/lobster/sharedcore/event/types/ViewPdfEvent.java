package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ViewPdfEventHandler;

public class ViewPdfEvent implements Event<ViewPdfEventHandler> {
	
	String json;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public void dispatch(ViewPdfEventHandler handler) {
		handler.onViewPdf(this);
	}

}
