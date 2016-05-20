/**
 * Aug 29, 2012 8:49:43 AM
 */
package org.surreal.lobster.sharedcore.databroker;

import org.surreal.lobster.sharedcore.event.types.LoggerEvent;

/**
 * A data broker for sending client-side log messages to the server log.
 * @author daniel.wilkin
 */
public interface LoggerDataBroker extends DataBroker {
	
	/**
	 * Sends the message bundled in the specified event to the server for logging.
	 * @param event The event with the message to send
	 */
	public void emittLogMessage(LoggerEvent event);

}
