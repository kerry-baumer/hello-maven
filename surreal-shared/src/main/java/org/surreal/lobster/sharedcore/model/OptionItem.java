package org.surreal.lobster.sharedcore.model;

/**
 * A client-side representation of a decode data instance.
 * @author unascribed
 */
public interface OptionItem {

	/**
	 * Accessor for the text field
	 * @return the readable text for the paired code value
	 */
	String getText();

	/**
	 * Accessor for the value field
	 * @return the code value
	 */
	String getValue();
}
