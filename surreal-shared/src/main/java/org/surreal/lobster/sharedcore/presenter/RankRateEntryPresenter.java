package org.surreal.lobster.sharedcore.presenter;

import org.surreal.lobster.sharedcore.databroker.LevelCodedDecodeDataBroker;

public interface RankRateEntryPresenter {

	void onBranchOfServiceSelect(String value);

	void onServiceStatusSelect(String value);

	void onCivilianSeriesSelect(String value);

	void onMilitaryCategorySelect(String value);

	void onJobTitleSelect(String value);

	void onRankSelect(String value);

	void onEnlistedRatingSelect(String value);

	void onPrimaryMOSSelect(String value);

	void onPayGradeSelect(String value);

	void onPaybandLevelSelect(String value);

	void onParentUnitCodeSelect(String value);

	void onPersonDeployedClicked(String text);

	void onComponentCommandSelect(String value);

	void setLevelCodedDecodeDatabroker(LevelCodedDecodeDataBroker levelCodedDatabroker);

	void onMajorCommandSelect(String value);

	void onParentCommandSelect(String value);

	void onUnitSelect(String value);

	void onServiceStatusSelect(String key, boolean fireChangeEvent);
	
	/*
	 * TODO: i don't know if this is the best way to go about doing this. the
	 * rank rate presenter needs to know when the duty status on the involved
	 * person page has changed so it can update appropriately. however, adding 
	 * a call directly to the interface to let it know that an external value 
	 * has changed seems like the wrong way to go about this - it seems as if
	 * this interface is now coupled to something that can provide it a duty 
	 * status.  i have a problem doing it this way - the rank rate presenter
	 * does need to know the value of the duty status field, but that logic 
	 * should be encapsulated inside the presenter itself - it shouldn't be 
	 * exposed through its public interface. need to look into cleaning this
	 * up. (sean)
	 */
	void onDutyStatusChanged(String dutyStatus);
	
}
