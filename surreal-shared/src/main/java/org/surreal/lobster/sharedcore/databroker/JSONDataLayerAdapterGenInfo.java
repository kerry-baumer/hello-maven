/**
 * Feb 14, 2012 11:33:15 AM
 */
package org.surreal.lobster.sharedcore.databroker;

import org.surreal.lobster.sharedcore.model.draft.navigable.IDtEventBean;

/**
 * Adapts the native underlying data layer for use by the data broker in this
 * client.
 * @author daniel.wilkin
 */
public interface JSONDataLayerAdapterGenInfo extends JSONDataLayer {
	
	/**
	 * Composes an event data model from the specified JSON string.
	 * @param json The string whose model is to be composed
	 * @return The data model as a graph
	 */
	public IDtEventBean deserializeFromJson(String json);

	/**
	 * Decomposes the specified event model to a JSON string.
	 * @param event The data model to decompose
	 * @return The representative JSON string
	 */
	public String serializeToJson(IDtEventBean event);

	/**
	 * Obtains the embedded object model stored as a string from the payload.
	 * @return The object model as a string or a well formed empty model as
	 * a string if no model is embedded
	 */
	public String extractEmbeddedEvent();

}
