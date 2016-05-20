package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.InjuryClassificationChangedEventHandler;

public class InjuryClassificationChangedEvent implements Event<InjuryClassificationChangedEventHandler>{

	private final String injClassnC;

	public InjuryClassificationChangedEvent(String injClassnC) {
		this.injClassnC = injClassnC;
	}
	
	@Override
	public void dispatch(InjuryClassificationChangedEventHandler handler) {
		handler.onInjuryClassificationChanged(this);
	}

	/**
	 * @return the injClassnC
	 */
	public final String getInjClassnC() {
		return injClassnC;
	}

}
