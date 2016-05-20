/**
 * Aug 29, 2012 8:33:53 AM
 */
package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.LoggerEvent;

/**
 * A handler for server logging events.
 * @author daniel.wilkin
 */
public interface LoggerEventHandler extends EventHandler {

	public void onLoggingEvent(LoggerEvent event);
}
