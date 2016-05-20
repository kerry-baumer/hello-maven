/**
 * Jan 13, 2012 1:30:49 PM
 */
package org.surreal.lobster.sharedcore.model;

import java.util.Collection;

/**
 * A level coded decode represented in the client.  Both code and decode are represented
 * as well as any children that may exist for this instance of the decode.
 * @author daniel.wilkin
 */
public interface LevelCodedOptionItem extends OptionItem {

	/**
	 * Obtains the children of this decode item in the tree.
	 * @return The children under this decode item
	 */
	Collection<LevelCodedOptionItem> getChildren();
}
