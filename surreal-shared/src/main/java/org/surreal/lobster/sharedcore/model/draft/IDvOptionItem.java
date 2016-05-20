/**
 * May 29, 2012 3:26:16 PM
 */
package org.surreal.lobster.sharedcore.model.draft;

/**
 * An <code>OptionItem</code> compatible with AutoBeans.  This interface
 * is strictly <b>not</b> associated with the <code>OptionItem</code> class
 * hierarchy.
 * @author daniel.wilkin
 */
public interface IDvOptionItem {
	
	String getValue();
	void setValue(String value);
	
	String getText();
	void setText(String text);
	
}
