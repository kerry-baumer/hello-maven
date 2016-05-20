package org.surreal.lobster.sharedcore.databroker;


/**
 * A marker interface for transiting data to and from clients that use it.
 * @author daniel.wilkin
 */
public interface DataBroker {
	// marker interface
	
	interface HandlesError {
		
		void handleError(Throwable error);
	}
}
