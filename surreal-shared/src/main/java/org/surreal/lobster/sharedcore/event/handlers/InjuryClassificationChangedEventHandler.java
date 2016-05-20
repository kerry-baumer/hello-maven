package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.InjuryClassificationChangedEvent;

public interface InjuryClassificationChangedEventHandler extends EventHandler {

	void onInjuryClassificationChanged(InjuryClassificationChangedEvent injuryClassificationChangedEvent);

}
