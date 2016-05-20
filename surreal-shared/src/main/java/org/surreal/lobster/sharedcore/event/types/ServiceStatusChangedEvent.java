package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.ServiceStatusChangedEventHandler;

public class ServiceStatusChangedEvent implements Event<ServiceStatusChangedEventHandler> {

	private final String serviceStatusCode;

	/**
	 * @return the serviceStatusCode
	 */
	public String getServiceStatusCode() {
		return serviceStatusCode;
	}

	public ServiceStatusChangedEvent(String code) {
		this.serviceStatusCode = code;
	}

	@Override
	public void dispatch(ServiceStatusChangedEventHandler handler) {
		handler.onServiceStatusChanged(this);
	}

}
