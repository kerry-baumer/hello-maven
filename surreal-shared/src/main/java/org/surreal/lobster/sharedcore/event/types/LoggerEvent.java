/**
 * Aug 29, 2012 8:35:23 AM
 */
package org.surreal.lobster.sharedcore.event.types;

import org.surreal.lobster.sharedcore.event.handlers.LoggerEventHandler;

/**
 * An event wrapping a log message for emitting to the server to record
 * in the server log.<p>
 * 
 * <i><b>Dispatch this event judiciously as it consumes a server call.</b></i>
 * @author daniel.wilkin
 */
public class LoggerEvent implements Event<LoggerEventHandler> {
	
	/** The ERROR_INDICATOR constant */
	public static final String ERROR_INDICATOR = "[ERR8472] ";
	/** The message property */
	private final String message;

	/**
	 * Logs an info message on the server.
	 * @param message The message to log
	 */
	public LoggerEvent(String message) {
		this.message = message;
	}
	
	/**
	 * Logs an error message on the server.
	 * @param error The message to log
	 * @param t The relevant exception or <code>null</code> if not applicable
	 */
	public LoggerEvent(String error, Throwable t) {
		final StringBuilder buffer = new StringBuilder();
		buffer
				.append(ERROR_INDICATOR);
		if (t == null) {
			buffer
					.append(error == null || error.isEmpty() ? "blank" : error);
		}
		else {
			final StackTraceElement[] trace = t.getStackTrace();
			buffer
					.append("Client encountered exception, '")
					.append(error == null || error.isEmpty() ? "blank" : error)
					.append("'\n\t")
					.append(t.getClass())
					.append("['")
					.append(t.getMessage())
					.append("']\n");
			for (StackTraceElement elem : trace) {
				buffer.append("\t\tat ")
						.append(elem.toString())
						.append('\n');
			}
		}
		this.message = buffer.toString();
	}

	@Override
	public void dispatch(LoggerEventHandler handler) {
		handler.onLoggingEvent(this);
	}

	/**
	 * Accessor for <code>message</code> property.
	 * @return The <code>message</code> value
	 */
	public String getMessage() {
		return message;
	}
	
}
