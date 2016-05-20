/**
 * Jan 30, 2012 10:13:49 AM
 */
package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.OptionItemDetail;

/**
 * An abstraction layer to support generics & Gin.
 * @author daniel.wilkin
 */
public interface MishapTypeDecodeDataBroker extends DecodeDataBroker<List<OptionItemDetail>> {
	// empty
}
