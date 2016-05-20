package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.view.OptionListItem;

public class ServiceStatusStrategy extends BaseRankRateEntryStrategy {
	@Override
	public boolean shouldShow(String... values) {
		return values[0] != null && !values[0].trim().isEmpty();
	}

	@Override
	public void getItems(DataBrokerCallback<List<OptionListItem>> callback, String... values) {
		List<OptionListItem> list = new ArrayList<OptionListItem>();
		if (values[0] != null) {
			list.add(genItem(" "));
			if ("C".equals(values[0])) {
				list.add(genItem("L", "CIVILIAN AND NO GOVT AFFILIATION"));
				list.add(genItem("A", "MILITARY DEPENDENT"));
				list.add(genItem("K", "UNKNOWN"));
			} else {
				list.add(genItem("C", "ACTIVE"));
				list.add(genItem("L", "CIVILIAN AND NO GOVT AFFILIATION"));
				list.add(genItem("M", "CONTRACTOR - GOVERNMENT (AGENCY)"));
				list.add(genItem("N", "CONTRACTOR - GOVERNMENT (INDEPENDENT)"));
				list.add(genItem("F", "FEDERAL APPROPRIATED CIVILIAN"));
				list.add(genItem("G", "FEDERAL NON-APPROPRIATED CIVILIAN"));
				list.add(genItem("J", "FOREIGN CIVILIAN (ATTACHED TO U.S. MILITARY)"));
				list.add(genItem("H", "FOREIGN MILITARY (ATTACHED TO U.S. MILITARY)"));
				list.add(genItem("A", "MILITARY DEPENDENT"));
				list.add(genItem("D", "RESERVE - ACTIVE"));
				list.add(genItem("E", "RESERVE - READY"));
				list.add(genItem("K", "UNKNOWN"));
				list.add(genItem("O", "YOUTH STUDENT ASSISTANT PROGRAM"));
			}
		}
		callback.onSuccess(list);
	}
}
