package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.model.LevelCodedOptionItem;
import org.surreal.lobster.sharedcore.model.OptionItem;
import org.surreal.lobster.sharedcore.view.OptionListItem;

public abstract class BaseRankRateEntryStrategy extends BaseEntryStrategy {

	protected static Map<String, LevelCodedOptionItem> map;
	protected static List<LevelCodedOptionItem> level1 = new ArrayList<LevelCodedOptionItem>();
	protected static List<LevelCodedOptionItem> level2 = new ArrayList<LevelCodedOptionItem>();
	protected static List<LevelCodedOptionItem> level3 = new ArrayList<LevelCodedOptionItem>();
	protected static List<LevelCodedOptionItem> level4 = new ArrayList<LevelCodedOptionItem>();
	
	public abstract boolean shouldShow(String... values);
	
	public boolean isRequired(String... values) {
		return shouldShow(values);
	}

	protected OptionListItem genItem(final String value) {
		return genItem(value,value);
	}

	protected OptionListItem genItem(final String value, final String text) {
		return wrap(new OptionItem() {
			@Override
			public String getText() {
				return text;
			}
			
			@Override
			public String getValue() {
				return value;
			}
		});
	}
	
	OptionListItem wrap(OptionItem item) {
		return new OptionListItem(item);
	}
	
	protected boolean oneOf(String v, String... strings) {
		boolean found = false;
		for (String s : strings) {
			if (s.equals(v)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	public boolean isDod(String branchOfService) {
		boolean result = false;
		if (branchOfService!= null && !branchOfService.isEmpty()) {
			result = oneOf(branchOfService, "F", "A", "G", "L", "D", "M", "N", "U");
		}
		return result;
	}
	
	public boolean isGovtOwned(String...values) {
		boolean result = false;
		if (values != null && values.length > 1) {
			result = isDod(values[0]) && !oneOf(values[1], "A", "L", "N", "K", " ");
		}
		return result;
	}
	
	public boolean isCivilian(String... values) {
		boolean result = false;
		if (values != null && values.length > 1 && !values[1].isEmpty()) {
			result = isDod(values[0]) && oneOf(values[1], "F", "G", "J");
		}
		return result;
	}
	
	public boolean isMilitary(String... values) {
		boolean result = false;
		if (values != null && values.length > 1 && !values[1].isEmpty()) {
			result = isDod(values[0]) && oneOf(values[1], "C","D","E","H");
		}
		return result;
	}
	
	protected boolean isNavy(String[] values) {
		return oneOf(values[0], "N");
	}	

	protected boolean isMarine(String[] values) {
		return oneOf(values[0], "M");
	}	

	protected boolean isArmy(String[] values) {
		return oneOf(values[0], "A");
	}	
	
	protected boolean isAirForce(String[] values) {
		return oneOf(values[0], "F");
	}

	protected boolean isOfficer(String[] values) {
		return isMilitary(values) && values.length > 2 && oneOf(values[2], "A");
	}

	protected boolean isEnlisted(String[] values) {
		return isMilitary(values) && values.length > 2 && oneOf(values[2], "B","E");
	}

	protected boolean isCoastGuard(String[] values) {
		return oneOf(values[0], "G");
	}

	public void getLevelCodedItems(DataBrokerCallback<List<LevelCodedOptionItem>> callback, String... values) {
		
	}

	public LevelCodedOptionItem genEmptyItem() {
		return new LevelCodedOptionItem() {					
			@Override public String getValue() { return " "; }
			@Override public String getText() { return " "; }
			@Override public Collection<LevelCodedOptionItem> getChildren() { return null; }
			};
	}

	public void initMap(List<LevelCodedOptionItem> result) {
		if (map == null) {
			map = new LinkedHashMap<String, LevelCodedOptionItem>();
			dig(result);
		}
	}

	private void dig(Collection<LevelCodedOptionItem> result) {
		for (LevelCodedOptionItem item : result) {
			map.put(item.getValue(), item);
			storeLevel(item);
			if (!item.getChildren().isEmpty()) {
				dig(item.getChildren());
			}
		}
	}

	private void storeLevel(LevelCodedOptionItem item) {
		switch(item.getValue().trim().length()) {
		case 1:
			level1.add(item);
			break;
		case 2:
			level2.add(item);
			break;
		case 4:
			level3.add(item);
			break;
		case 6:
			level4.add(item);
			break;
			
		}
	}	
	
}
