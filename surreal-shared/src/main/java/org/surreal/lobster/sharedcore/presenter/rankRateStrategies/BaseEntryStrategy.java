package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.view.OptionListItem;

public class BaseEntryStrategy {

	protected final List<OptionListItem> list = new ArrayList<OptionListItem>();

	public BaseEntryStrategy() {
		super();
	}

	public void getItems(DataBrokerCallback<List<OptionListItem>> callback, String... values) {
		callback.onSuccess(deselectAll(list));
	}

	protected List<OptionListItem> deselectAll(List<OptionListItem> items) {
		if (items == null) { items = list; }
		for (OptionListItem item : items) {
			item.setSelected(false);
		}
		return items;
	}

}