package org.surreal.lobster.sharedcore.model;


/**
 * An immutable decode data instance represented on the client.
 * @author linda.nichols
 */
public class OptionItemBean implements OptionItem  {
	
	/** The code */
	private final String value;
	
	/** The decode text */
	private final String text;
	
	/**
	 * Creates a new decode instance with the specified data.
	 * @param value The code
	 * @param text The decode
	 */
	public OptionItemBean(String value, String text) {
		this.value = value;
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getValue() {
		return value;
	}
}
