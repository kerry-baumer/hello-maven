package org.surreal.lobster.sharedcore.databroker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.surreal.lobster.sharedcore.model.OptionItem;

/**
 * Databroker implementation for decodes that are not retrieved from the database
 *
 * @author christopher.d.grimes
 */
public class StaticDataDecodeDataBrokerImpl implements StaticDataDecodeDataBroker {

	/**
	 * static list of factor types
	 */
	private static List<OptionItem> factorTypes = new ArrayList<OptionItem>();
	
	/** 
	 * Collection of <code>DutyStatTwoDecode</code> values that are applicable to 'On Duty' status. 
	 * 
	 * A-Regular,
	 * B-TAD Temporary Assigned Duty,
	 * C-PCS Permanent Change of Duty Station
	 */
	private static List<OptionItem> onDutyTier2Data = new ArrayList<OptionItem>();
	
	/** 
	 * Collection of <code>DutyStatTwoDecode</code> values that are applicable to 'Off Duty' status. 
	 * 
	 * C-PCS Permanent Change of Duty Station,
	 * D-Leave,
	 * E-Liberty,
	 * F-Unauthorized Absence
	 */
	private static List<OptionItem> offDutyTier2Data = new ArrayList<OptionItem>();

	/**
	 * init
	 */
	static {
		factorTypes.add(genItem("", ""));
		factorTypes.add(genItem("H", "Human Factor"));
		factorTypes.add(genItem("M", "Material Factor"));
		
		// on duty values
		onDutyTier2Data.add(genItem(" ", " "));
		onDutyTier2Data.add(genItem("A", "Regular"));
		onDutyTier2Data.add(genItem("B", "TAD - Temporary Assigned Duty"));
		onDutyTier2Data.add(genItem("C", "PCS Permanent Change of Duty Station"));
		
		// off duty values
		offDutyTier2Data.add(genItem(" ", " "));
		offDutyTier2Data.add(genItem("D", "Leave"));
		offDutyTier2Data.add(genItem("E", "Liberty"));
		offDutyTier2Data.add(genItem("C", "PCS Permanent Change of Duty Station"));
		offDutyTier2Data.add(genItem("F", "Unauthorized Absence"));
	}
	
	@Override
	public List<OptionItem> getFactorTypes() {
		return Collections.unmodifiableList(factorTypes);
	}
	
	/**
	 * Return decode data filtered by Duty Status Tier 1 for population of Duty Status Tier 2 pick list.
	 * Duty Status Tier 2 data is applicable only to Tier 1 values (On Duty) and (Off Duty).
	 * @param dutyStatC The value of Duty Status Tier 1 with which to filter Duty Status Tier 2 data
	 */
	@Override
	public List<OptionItem> getDutyStatusTier2Data(String dutyStatC) {
		if ("A".equals(dutyStatC)) {
			return Collections.unmodifiableList(onDutyTier2Data);
		} else if ("B".equals(dutyStatC)) {
			return Collections.unmodifiableList(offDutyTier2Data);
		} else {
			return new ArrayList<OptionItem>();
		}
	}

	/**
	 * Generates an option item with the given value and text
	 * @param value of the option item
	 * @param text of the option item
	 * @return
	 */
	private static OptionItem genItem(final String value, final String text) {
		OptionItem item = new OptionItem() {
			@Override public String getValue() { return value; }
			@Override public String getText() { return text; }
		};
		return item;
	}
	
}
