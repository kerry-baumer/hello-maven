package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.databroker.LevelCodedDecodeDataBroker;
import org.surreal.lobster.sharedcore.model.LevelCodedOptionItem;
import org.surreal.lobster.sharedcore.presenter.LevelCodedDecodeField;

public class ComponentCommandStrategy extends BaseRankRateEntryStrategy {

	private final LevelCodedDecodeDataBroker databroker;

	public ComponentCommandStrategy(LevelCodedDecodeDataBroker databroker) {
		this.databroker = databroker;
	}
	
	@Override
	public boolean shouldShow(String... values) {
		boolean shouldShow = false;
		if (values != null && values.length != 0) {
			String uic = values[0]; 
			if (uic != null && uic.startsWith("M")) {
				shouldShow = true;
			}
		}
		return shouldShow;
	}
	
	@Override
	public void getLevelCodedItems(final DataBrokerCallback<List<LevelCodedOptionItem>> callback, String... values) {
		databroker.getDecodeData(LevelCodedDecodeField.COMPONENT_COMMAND, new DataBrokerCallback<List<LevelCodedOptionItem>>() {
			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);
			}
			@Override
			public void onSuccess(List<LevelCodedOptionItem> result) {
				initMap(result);
				List<LevelCodedOptionItem> list = new ArrayList<LevelCodedOptionItem>();
				list.add(genEmptyItem());
				list.addAll(level1);
				callback.onSuccess(list);
			}
		});
	}
	
}
