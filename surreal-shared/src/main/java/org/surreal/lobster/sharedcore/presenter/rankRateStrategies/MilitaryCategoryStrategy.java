package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;


public class MilitaryCategoryStrategy extends BaseRankRateEntryStrategy {

	public MilitaryCategoryStrategy() {
		list.add(genItem(" "));
		list.add(genItem("A","Officer"));
		list.add(genItem("B","Enlisted"));
		list.add(genItem("C","MIDN"));
		list.add(genItem("D","OCS"));
		list.add(genItem("E","Recruit"));
	}
	
	@Override
	public boolean shouldShow(String... values) {
		return isMilitary(values);
	}

}
