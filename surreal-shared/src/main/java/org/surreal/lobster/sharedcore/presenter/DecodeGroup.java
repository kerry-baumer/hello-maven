package org.surreal.lobster.sharedcore.presenter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


/**
 * A high level classification attached to a specific set of Decodes.  Decode groups are
 * established by usage, page, similarity, or other association.  Each decode group
 * provides a list of Decode fields. For example, the group <code>GEN_INFO_DIVE</code>
 * may include the <code>DiveCategory</code>, <code>DivePurpose</code>, and the
 * <code>SeaState</code> decode fields.<p>
 * 
 * Decode fields may be duplicated across groups but should be unique within any single 
 * group.
 * @author daniel.wilkin
 * @author adam.thomas
 */
public enum DecodeGroup {
	INITIAL_NOTIFICATION(DecodeField.BODY_OF_WATER),
	ENVIRONMENT(DecodeField.DISTANCE_UOM, DecodeField.SEA_DIRECTION, DecodeField.VISIBILITY_RESTRICTED_BY, 
			DecodeField.SEA_STATE, DecodeField.WIND_DIRECTION, DecodeField.FLAG_CNDN),
	GENERAL_INFORMATION(DecodeField.AREA_RESP, DecodeField.BODY_OF_WATER, DecodeField.FIRE_SOURCE, DecodeField.FIRE_CLASS),
	INVOLVED_VESSEL(DecodeField.AFLOAT_EVOLUTION, DecodeField.EXERCISE, DecodeField.FRTP_PHASE, DecodeField.INVOLVED_STATUS, DecodeField.OPERATIONAL_CONTINGENCY, DecodeField.TYPE_VESSEL, DecodeField.SMALL_CRAFT),
	INVOLVED_PERSON(DecodeField.AREA, DecodeField.DUTY_STAT, DecodeField.DUTY_STAT_TWO, DecodeField.INJ_CLASSN, DecodeField.PCN, DecodeField.INIT_MED_TRTM, DecodeField.FINAL_TEMP_DIAGNOSIS, DecodeField.NEURO_SIGNS, DecodeField.SHARP_ITEM_TYPE, DecodeField.SHARP_ITEM_BRAND, DecodeField.CA_SRC_TYPE, DecodeField.OSHA_CLASSN),
	RANK_RATE(DecodeField.BRANCH_OF_SERVICE, DecodeField.SERVICE_STATUS, DecodeField.MILITARY_CATEGORY),
	QUALS_CERTS(DecodeField.AREA_RESP, DecodeField.CERT_QUAL_LICNS),
	QA(DecodeField.PER_CATG, DecodeField.ENVIR, DecodeField.NOT_COUNT_REASON, DecodeField.MISHAP_CLASSIFICATION),
	INVOLVED_PROPERTY(DecodeField.EIC),
	FACTORS(DecodeField.HUMAN_FAC_ACT_TYPE, DecodeField.HFAC_PRECONIDTION, DecodeField.FACTOR_TYPE),
	RECOMMENDATIONS(DecodeField.RECMN_STAT);
	;

	/** The inverseMap constant */
	private static final Map<DecodeField, Set<DecodeGroup>> INVERSE_LOOKUP = new HashMap<DecodeField, Set<DecodeGroup>>();
	
	static {
		for (final DecodeGroup g : DecodeGroup.values()) {
			for (final DecodeField f : g.decodeFields) {
				if (INVERSE_LOOKUP.containsKey(f)) {
					INVERSE_LOOKUP.get(f).add(g);
				}
				else {
					final HashSet<DecodeGroup> s = new HashSet<DecodeGroup>();
					s.add(g);
					INVERSE_LOOKUP.put(f, s);
				}
			}
		}
	}
	
	/** The decodeFields property */
	private final Set<DecodeField> decodeFields;
	
	private DecodeGroup(DecodeField... decodeField) {
		this.decodeFields = new HashSet<DecodeField>(Arrays.asList(decodeField));
	}
	
	/**
	 * Obtains the decode fields associated with this group
	 * @return The decode fields that can be found within this grouping
	 */
	public Set<DecodeField> getFields() {
		return decodeFields;
	}
	
	/**
	 * Obtains the groups containing the specified field in this enumeration.
	 * @param field The field whose containing groups are to be retrieved
	 * @return The set of groups
	 */
	public static Set<DecodeGroup> findGroupsFor(DecodeField field) {
		final Set<DecodeGroup> groups = INVERSE_LOOKUP.get(field);
		if (groups == null) {
			throw new NoSuchElementException("Unmatched group for field, did you forget to associate your field in a DecodeGroup?");
		}
		return groups;
	}
}
