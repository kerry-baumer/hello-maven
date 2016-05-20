/**
 * Jun 26, 2012 10:51:49 AM
 */
package org.surreal.lobster.sharedcore.databroker;

import org.surreal.lobster.sharedcore.common.DependentCommand;
import org.surreal.lobster.sharedcore.model.OptionItem;
import org.surreal.lobster.sharedcore.model.draft.IDvOptionItem;

/**
 * A databroker that delegates common operations to a central databroker to
 * perform.
 * @author daniel.wilkin
 */
public interface DelegatingDataBroker extends DataBroker {

	/**
	 * Convenience method to create an option item identical to the specified item containing
	 * identical values suitable for preserving in the model.
	 * @param item The client defined option item
	 * @return The persistable option item instance
	 */
	IDvOptionItem createDecode(OptionItem item);

	/**
	 * Creates a new instance of a populated decode represented as an {@link IDvOptionItem}.
	 * @param value The code for the decode
	 * @param text The text of the decode
	 * @return The persistable option item instance
	 */
	IDvOptionItem createDecode(String value, String text);

	/**
	 * Flushes the data managed by this data broker to the persistence tier.
	 */
	void flush();

	/**
	 * Flushes the data managed by this data broker to the persistence tier then
	 * executes the behavior in the specified callback.
	 * @param command The behavior to execute after the response from the server
	 * is received
	 */
	void flushThenExecute(DependentCommand command);

}
