/**
 * 
 */
package org.surreal.lobster.sharedcore.exception;

/**
 * @author kerry.baumer
 *
 */
public class ServiceUnavailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ServiceUnavailableException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ServiceUnavailableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ServiceUnavailableException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceUnavailableException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
