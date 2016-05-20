package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.databroker.LevelCodedDecodeDataBroker;
import org.surreal.lobster.sharedcore.model.LevelCodedOptionItem;
import org.surreal.lobster.sharedcore.presenter.LevelCodedDecodeField;

public class UnitStrategy extends BaseRankRateEntryStrategy {

	private final LevelCodedDecodeDataBroker databroker;
	
	public UnitStrategy(LevelCodedDecodeDataBroker levelCodedDatabroker) {
		this.databroker = levelCodedDatabroker;
	}

	@Override
	public boolean shouldShow(String... values) {
		boolean ret = false;
		if (values!=null && values.length>4 && values[0] != null) {
			String uic = values[0];
			String parentCommand = values[4];
			if (uic.startsWith("M") && parentCommand != null && !parentCommand.trim().isEmpty()) {
				ret = true;
			}
		}
		return ret ;
	}
	
	@Override
	public void getLevelCodedItems(final DataBrokerCallback<List<LevelCodedOptionItem>> callback, final String... values) {
		if (values != null && values.length > 2) {
			databroker.getDecodeData(LevelCodedDecodeField.COMPONENT_COMMAND, new DataBrokerCallback<List<LevelCodedOptionItem>>() {
				@Override
				public void onFailure(Throwable caught) {
					callback.onFailure(caught);
				}
				@Override
				public void onSuccess(List<LevelCodedOptionItem> result) {
					initMap(result);
					String personDeployed = values[1];
					if ("Y".equals(personDeployed)) {
						// not level coded
						List<LevelCodedOptionItem> list = new ArrayList<LevelCodedOptionItem>();
						list.add(genEmptyItem());
						list.addAll(level4);
						callback.onSuccess(list);
					} else if ("N".equals(personDeployed)){
						// level coded
						String parentCommand = values[4];
						ArrayList<LevelCodedOptionItem> notDeployedList = new ArrayList<LevelCodedOptionItem>();
						LevelCodedOptionItem items = map.get(parentCommand);
						if (items != null) {
							notDeployedList.add(genEmptyItem());
							notDeployedList.addAll(items.getChildren());
						}
						callback.onSuccess(notDeployedList);
					}
				}
			});
		}
	}

}
