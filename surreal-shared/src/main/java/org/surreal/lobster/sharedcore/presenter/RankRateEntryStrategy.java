package org.surreal.lobster.sharedcore.presenter;

import java.util.List;

import org.surreal.lobster.sharedcore.view.OptionListItem;

public interface RankRateEntryStrategy {
	public boolean shouldShow(String... values);
	public List<OptionListItem> getItems(String... values);
}
