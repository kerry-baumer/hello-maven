/**
 * May 29, 2012 4:30:13 PM
 */
package org.surreal.lobster.sharedcore.model;

import org.surreal.lobster.sharedcore.model.draft.IDvOptionItem;

/**
 * A mutable decode data instance suitable for cooperating with the <b>AutoBeans</b> framework.  
 * This item class includes convenience conversions between the <code>OptionItem</code> type 
 * hierarchy and the AutoBean respective type where <code>OptionItem</code>s need to be attached.<p>
 * 
 * The pseudo-copy constructors accept an <code>OptionItem</code> instance and the 
 * {@link #toOptionItem()} method can be used to obtain the <code>OptionItem</code> equivalent
 * of this instance.<p>
 * 
 * An instance of this class can be JSON-ized and accepted as is by the AutoBeans framework as an
 * instance of {@link IDvOptionItem} and vice-versa.
 * @author daniel.wilkin
 * @see OptionItem
 */
public class ConvertableOptionItemBean implements IDvOptionItem {

	private String text;
	private String value;
	
	public ConvertableOptionItemBean() {
		// empty
	}

	/**
	 * Creates a new instance from only the <code>value</code> and <code>text</code>
	 * properties of the specified detail.
	 * @param detail The detail instance
	 */
	public ConvertableOptionItemBean(OptionItemDetail detail) {
		this(detail.getValue(), detail.getText());
	}

	public ConvertableOptionItemBean(OptionItem item) {
		this(item.getValue(), item.getText());
	}
	
	public ConvertableOptionItemBean(IDvOptionItem item) {
		this(item.getValue(), item.getText());
	}
	
	public ConvertableOptionItemBean(String code, String decode) {
		this.value = code;
		this.text = decode;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.OptionItem#getText()
	 */
	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.OptionItem#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	public OptionItem toOptionItem() {
		return new OptionItemBean(value, text);
	}
	
	@Override
	public String toString() {
		return "<" + value + ", " + text + ">";
	}

}
