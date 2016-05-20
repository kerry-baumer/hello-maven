package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;


public class PayBandStrategy extends BaseRankRateEntryStrategy {

	public PayBandStrategy() {
		list.add(genItem(""));
		list.add(genItem("I"));
		list.add(genItem("II"));
		list.add(genItem("III"));
		list.add(genItem("IV"));
		list.add(genItem("V"));
		list.add(genItem("VI"));
		list.add(genItem("VII"));
		list.add(genItem("VIII"));
		list.add(genItem("IX"));
		list.add(genItem("X"));
		list.add(genItem("XI"));
		list.add(genItem("XII"));
	}
	
	@Override
	public boolean shouldShow(String... values) {
		return isCivilian(values) && values.length > 3 && !values[3].trim().isEmpty() && oneOf(values[2], "C");
	}
	
}
