package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.EnvironmentIndicatorChangedEventHandler;

public class EnvironmentIndicatorChangedEvent implements Event<EnvironmentIndicatorChangedEventHandler> {
	
	private final String environmentIndicator;
	
	/**
	 * @return the environmentIndicator
	 */
	public String getEnvironmentIndicator() {
		return environmentIndicator;
	}

	/**
	 * Constructor
	 */
	public EnvironmentIndicatorChangedEvent(String indicator) {
		this.environmentIndicator = indicator;
	}

	@Override
	public void dispatch(EnvironmentIndicatorChangedEventHandler handler) {
		handler.onEnvironmentIndicatorChanged(this);
	}

}
