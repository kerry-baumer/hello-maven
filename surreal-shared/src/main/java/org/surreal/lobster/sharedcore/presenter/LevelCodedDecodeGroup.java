/**
 * Jan 12, 2012 11:43:40 AM
 */
package org.surreal.lobster.sharedcore.presenter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.surreal.lobster.sharedcore.presenter.DecodeGroup;

/**
 * A high level classification attached to a specific set of level coded decodes.  Decode 
 * groups are established by usage, page, similarity, or other association.  Each decode 
 * group provides a list of level coded decode fields. For example, the group 
 * <code>GEN_INFO_DIVE</code> may include the <code>DiveCategory</code>, <code>DivePurpose</code>,
 * and the <code>SeaState</code> decode fields.<p>
 * 
 * Level coded decode fields may be duplicated across groups but should be unique within any 
 * single group.
 * @see DecodeGroup
 * @author kerry.baumer
 * @author daniel.wilkin
 */
public enum LevelCodedDecodeGroup {
	INITIAL_NOTIFICATION(LevelCodedDecodeField.PORT)
	;
	
	private final Set<LevelCodedDecodeField> decodeFields;

	private LevelCodedDecodeGroup(LevelCodedDecodeField... fields) {
		this.decodeFields = new HashSet<LevelCodedDecodeField>(Arrays.asList(fields));
	}
	/**
	 * Obtains the level coded decode fields associated with this group
	 * @return The decode fields that can be found within this grouping
	 */
	public Set<LevelCodedDecodeField> getFields() {
		return decodeFields;
	}
}
