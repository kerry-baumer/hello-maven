package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;



public class ParentUicStrategy extends BaseRankRateEntryStrategy {
	
	@Override
	public boolean shouldShow(String... values) {
		return isGovtOwned(values);
	}
}
