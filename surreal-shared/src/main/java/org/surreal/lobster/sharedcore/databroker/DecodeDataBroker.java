package org.surreal.lobster.sharedcore.databroker;

import org.surreal.lobster.sharedcore.presenter.LevelCodedDecodeField;

/**
 * Emits decode values from the data tier to clients.
 * @author daniel.wilkin
 * @author adam.thomas
 */
public interface DecodeDataBroker<T> extends DataBroker {

	/**
	 * Retrieves the collection of decode data and their selectable values as type 
	 * <code>T</code> associated with the criteria specifier.  The specifier is 
	 * typically either a <code>String</code> such as for UICs or an enumerated value,
	 * such as {@link LevelCodedDecodeField}.
	 * @param specifier The criteria specifier describing the data to be retrieved
	 * @param callback The code block to execute once the decodes have been retrieved
	 */
	public void getDecodeData(Object specifier, DataBrokerCallback<T> callback);
	
}
