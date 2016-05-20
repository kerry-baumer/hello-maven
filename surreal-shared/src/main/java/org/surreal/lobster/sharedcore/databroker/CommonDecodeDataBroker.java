/**
 * Jan 26, 2012 3:08:24 PM
 */
package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.OptionItem;
import org.surreal.lobster.sharedcore.presenter.DecodeGroup;

/**
 * An abstraction layer to support generics & Gin.
 * @see DecodeGroup
 * @see TwoPhaseDecodeDataBroker
 * @author daniel.wilkin
 */
public interface CommonDecodeDataBroker extends TwoPhaseDecodeDataBroker<List<OptionItem>> {
	// empty
}
