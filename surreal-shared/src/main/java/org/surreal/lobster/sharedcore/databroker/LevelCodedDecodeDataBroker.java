/**
 * Jan 30, 2012 10:02:56 AM
 */
package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.LevelCodedOptionItem;
import org.surreal.lobster.sharedcore.presenter.LevelCodedDecodeField;

/**
 * An abstraction layer to support generics & Gin.
 * @author daniel.wilkin
 */
public interface LevelCodedDecodeDataBroker extends DecodeDataBroker<List<LevelCodedOptionItem>> {
	void getDecodeData(LevelCodedDecodeField specifier, Object subject, Object type, DataBrokerCallback<List<LevelCodedOptionItem>> callback);
}
