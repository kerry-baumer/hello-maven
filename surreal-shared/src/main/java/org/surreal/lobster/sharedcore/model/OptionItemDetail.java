/**
 * Jan 13, 2012 11:31:45 AM
 */
package org.surreal.lobster.sharedcore.model;

/**
 * A decode item with additional info.
 * @author daniel.wilkin
 */
public interface OptionItemDetail extends OptionItem {

	/**
	 * The additional information from the decode.
	 * @return The detail
	 */
	String getAdditionalInfo();
	
}
