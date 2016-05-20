/**
 * Jan 30, 2012 10:15:11 AM
 */
package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.OptionItem;

/**
 * An abstraction layer to support generics & Gin.
 * @author daniel.wilkin
 */
public interface PortDecodeDataBroker extends DecodeDataBroker<List<OptionItem>> {
	// empty
}
