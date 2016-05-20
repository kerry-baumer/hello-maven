package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.databroker.LevelCodedDecodeDataBroker;
import org.surreal.lobster.sharedcore.model.LevelCodedOptionItem;
import org.surreal.lobster.sharedcore.presenter.LevelCodedDecodeField;

public class MajorCommandStrategy extends BaseRankRateEntryStrategy {

	private final LevelCodedDecodeDataBroker databroker;
	public MajorCommandStrategy(LevelCodedDecodeDataBroker databroker) {
		this.databroker = databroker;
	}
	
	@Override
	public boolean shouldShow(String... values) {
		boolean ret = false;
		if (values != null && values.length > 2 && values[0] != null) {
			String uic = values[0];
			String componentCommand = values[2];
			if (uic.startsWith("M") && componentCommand != null && !componentCommand.trim().isEmpty()) {
				ret = true;
			}
		}
		return ret;
	}
	
	@Override
	public void getLevelCodedItems(final DataBrokerCallback<List<LevelCodedOptionItem>> callback, final String... values) {
		if (values != null && values.length > 1) {
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
						List<LevelCodedOptionItem> list = new ArrayList<LevelCodedOptionItem>();
						list.add(genEmptyItem());
						list.addAll(level2);
						callback.onSuccess(list);
					} else if ("N".equals(personDeployed)){
						// level coded
						String componentCommand = values[2];
						LevelCodedOptionItem items = map.get(componentCommand);
						ArrayList<LevelCodedOptionItem> notDeployedList = new ArrayList<LevelCodedOptionItem>(); 
						if (items != null) {
							notDeployedList.add(genEmptyItem());
							notDeployedList.addAll(items.getChildren());
							callback.onSuccess(notDeployedList);
						}
					}
				}
			});
		}
	}

}
