package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.NavigationEventHander;


public class NavigationEvent implements Event<NavigationEventHander> {

	public enum Dir { PREVIOUS, NEXT }

	private final Dir dir;
	
	/**
	 * @return the dir
	 */
	public final Dir getDir() {
		return dir;
	}

	public NavigationEvent(Dir dir) {
		this.dir = dir;
	}
	
	@Override
	public void dispatch(NavigationEventHander handler) {
		handler.onNavigation(this);
	}
	
}
