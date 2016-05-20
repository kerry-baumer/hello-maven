package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ValidationSuccessEventHandler;

public class ValidationSuccessEvent implements Event<ValidationSuccessEventHandler>{

	private int errorCount;

	public ValidationSuccessEvent(int errorCount) {
		this.errorCount = errorCount;
	}
	
	@Override
	public void dispatch(ValidationSuccessEventHandler handler) {
		handler.onValidationSuccess(this);
	}

	/**
	 * @return the errorCount
	 */
	public final int getErrorCount() {
		return errorCount;
	}

	/**
	 * @param errorCount the errorCount to set
	 */
	public final void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

}
