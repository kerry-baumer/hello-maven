package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;


public class CivilianSeriesStrategy extends BaseRankRateEntryStrategy {

	public CivilianSeriesStrategy() {
		list.add(genItem(" "));
		list.add(genItem("C", "Demonstration Project"));
		list.add(genItem("A", "GS"));
		list.add(genItem("E", "NAF"));
		list.add(genItem("B", "SES"));
		list.add(genItem("D", "WG"));
		list.add(genItem("F", "WM"));
	}
	
	@Override
	public boolean shouldShow(String... values) {
		return isCivilian(values);
	}

}
