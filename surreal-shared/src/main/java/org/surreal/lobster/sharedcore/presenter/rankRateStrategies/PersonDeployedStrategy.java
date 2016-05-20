package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;

public class PersonDeployedStrategy extends BaseRankRateEntryStrategy {

	@Override
	public boolean shouldShow(String... values) {
		boolean result = false;
		if (values != null && values.length > 1 && values[0] != null && values[1] != null) {
			String svcStat = values[0];
			String parentUic = values[1];
			if (oneOf(svcStat, "C","D","E","F","G","M") && parentUic.startsWith("M")) {
				result = true;
			}
		}
		return result;
	}

}
