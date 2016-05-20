package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.PageChangeEventHandler;
import org.surreal.lobster.sharedcore.navigation.PageNode;

public class PageChangeEvent implements Event<PageChangeEventHandler> {

	PageNode pageNode;
	
	/**
	 * @return the pageNode
	 */
	public final PageNode getPageNode() {
		return pageNode;
	}

	public PageChangeEvent(PageNode pageNode) {
		this.pageNode = pageNode;
	}
	
	@Override
	public void dispatch(PageChangeEventHandler handler) {
		handler.onPageChange(this);
	}

}
