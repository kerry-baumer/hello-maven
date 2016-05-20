/**
 * Jun 22, 2012 1:57:46 PM
 */
package org.surreal.lobster.sharedcore.model;

/**
 * An immutable decode data instance with descriptive data represented on the client.
 * @author daniel.wilkin
 */
public class OptionItemDetailBean extends OptionItemBean implements OptionItemDetail {

	/** The additionalInfo property */
	private final String additionalInfo;

	public OptionItemDetailBean(OptionItem item) {
		this(item.getValue(), item.getText(), null);
	}
	
	public OptionItemDetailBean(String value, String text, String additionalInfo) {
		super(value, text);
		this.additionalInfo = additionalInfo;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.OptionItemDetail#getAdditionalInfo()
	 */
	@Override
	public String getAdditionalInfo() {
		return additionalInfo;
	}

}
