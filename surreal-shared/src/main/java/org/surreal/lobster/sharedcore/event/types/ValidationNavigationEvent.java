package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ValidationNavigationEventHandler;
import org.surreal.lobster.sharedcore.navigation.PageNode;

public class ValidationNavigationEvent implements Event<ValidationNavigationEventHandler> {

	private final String errorMessage;
	private final PageNode pageNode;

	public ValidationNavigationEvent(PageNode pageNode, String errorMessage) {
		this.pageNode = pageNode;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void dispatch(ValidationNavigationEventHandler handler) {
		handler.onErrorLineClicked(this);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public PageNode getPageNode() {
		return pageNode;
	}
	
}
