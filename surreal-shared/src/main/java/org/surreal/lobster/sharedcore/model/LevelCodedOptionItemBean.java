/**
 * Jan 13, 2012 1:52:24 PM
 */
package org.surreal.lobster.sharedcore.model;

import java.util.ArrayList;
import java.util.Collection;


/**
 * A container for a tree of decode data.
 * @author daniel.wilkin
 */
public class LevelCodedOptionItemBean extends OptionItemBean implements LevelCodedOptionItem {
	
	/** The children property */
	private final Collection<LevelCodedOptionItem> children = new ArrayList<LevelCodedOptionItem>();

	public LevelCodedOptionItemBean(String value, String text, Collection<LevelCodedOptionItem> children) {
		super(value, text);
		if (children != null) {
			this.children.addAll(children);
		}
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.pegasuscore.model.LevelCodedOptionItem#getChildren()
	 */
	@Override
	public Collection<LevelCodedOptionItem> getChildren() {
		return children;
	}

}
