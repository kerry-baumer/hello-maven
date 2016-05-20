package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;


public class BranchOfServiceStrategy extends BaseRankRateEntryStrategy {
	
	public BranchOfServiceStrategy() {
		list.add(genItem(" "));
		list.add(genItem("F", "AIR FORCE"));
		list.add(genItem("A", "ARMY"));
		list.add(genItem("C", "CIVILIAN - NON-GOVT"));
		list.add(genItem("G", "COAST GUARD"));
		list.add(genItem("L", "DEFENSE LOGISTICS AGENCY"));
		list.add(genItem("D", "DEFENSE MAPPING AGENCY"));
		list.add(genItem("M", "MARINE"));
		list.add(genItem("N", "NAVY"));
		list.add(genItem("U", "OTHER U.S. GOVERNMENT AGENCY"));
	}
	@Override
	public boolean shouldShow(String... values) {
		return true;
	}

}
