/**
 * Mar 16, 2012 10:58:32 AM
 */
package org.surreal.lobster.sharedcore.databroker;

/**
 * Signals an error while handling of data by or with a data broker.
 * @author daniel.wilkin
 */
public class DataBrokerProcessingException extends RuntimeException {

	/** The serialVersionUID property */
	private static final long serialVersionUID = 2238989007230759829L;

	public DataBrokerProcessingException() {
		super();
	}
	
	public DataBrokerProcessingException(String message) {
		super(message);
	}
	
	public DataBrokerProcessingException(String message, Throwable t) {
		super(message, t);
	}
}
