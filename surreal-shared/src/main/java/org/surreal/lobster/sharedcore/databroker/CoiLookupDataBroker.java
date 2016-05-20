/**
 * Jun 9, 2012 9:54:23 AM
 */
package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.constants.ReportType;
import org.surreal.lobster.sharedcore.model.OptionItemDetail;

/**
 * Retrieves COIs from the server for selection by the user.
 * @author daniel.wilkin
 */
public interface CoiLookupDataBroker extends DataBroker {

	/**
	 * Retrieves all COIs from the server associated with the specified <code>ReportType</code>.
	 * @param type The report type to search by
	 * @param callback The callback to notify
	 */
	public void findCois(ReportType type, DataBrokerCallback<List<OptionItemDetail>> callback);
}
