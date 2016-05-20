package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.view.OptionListItem;

public class RankStrategy extends BaseRankRateEntryStrategy {

	/*
	 * apparently we want to show rank if the service status is foreign military
	 * but we do not want to show a page level validation error.
	 */
	private static final String FOREIGN_MILITARY_SERVICE_STATUS_CODE = "H";
	
	@Override
	public boolean shouldShow(String... values) {
		return isMilitary(values) && values.length > 2 && oneOf(values[2], "A","B","E");
	}
	
	@Override
	public boolean isRequired(String... values) {
		return shouldShow(values) && !FOREIGN_MILITARY_SERVICE_STATUS_CODE.equals(values[1]);
	}
	
	@Override
	public void getItems(DataBrokerCallback<List<OptionListItem>> callback, String... values) {
		ArrayList<OptionListItem> list = new ArrayList<OptionListItem>();
		list.add(genItem("      "));
		if (isNavy(values)) {
			if (isEnlisted(values)) {
				list.add(genItem("AA    "));
				list.add(genItem("AN    "));
				list.add(genItem("AR    "));
				list.add(genItem("CA    "));
				list.add(genItem("CN    "));
				list.add(genItem("CPO   "));
				list.add(genItem("CR    "));
				list.add(genItem("DA    "));
				list.add(genItem("DN    "));
				list.add(genItem("DR    "));
				list.add(genItem("FA    "));
				list.add(genItem("FN    "));
				list.add(genItem("FR    "));
				list.add(genItem("HA    "));
				list.add(genItem("HN    "));
				list.add(genItem("HR    "));
				list.add(genItem("MCPO  "));
				list.add(genItem("MCPON "));
				list.add(genItem("PO1   "));
				list.add(genItem("PO2   "));
				list.add(genItem("PO3   "));
				list.add(genItem("SA    "));
				list.add(genItem("SCPO  "));
				list.add(genItem("SN    "));
				list.add(genItem("SR    "));
			} else if (isOfficer(values)) {
				list.add(genItem("ADM   "));
				list.add(genItem("CAPT  "));
				list.add(genItem("CDR   "));
				list.add(genItem("CWO2  "));
				list.add(genItem("CWO3  "));
				list.add(genItem("CWO4  "));
				list.add(genItem("CWO5  "));
				list.add(genItem("ENS   "));
				list.add(genItem("FADM  "));
				list.add(genItem("LCDR  "));
				list.add(genItem("LT    "));
				list.add(genItem("LTJG  "));
				list.add(genItem("RADML "));
				list.add(genItem("RADMU "));
				list.add(genItem("VADM  "));
				list.add(genItem("WO1   "));
			}
		} else if (isMarine(values)) {
			if (isEnlisted(values)) {
				list.add(genItem("1STSGT"));
				list.add(genItem("CPL   "));
				list.add(genItem("GYSGT "));
				list.add(genItem("LCPL  "));
				list.add(genItem("MGYSGT"));
				list.add(genItem("MSGT  "));
				list.add(genItem("PFC   "));
				list.add(genItem("PVT   "));
				list.add(genItem("RECRT "));
				list.add(genItem("SGT   "));
				list.add(genItem("SGTMAJ"));
				list.add(genItem("SMAJM "));
				list.add(genItem("SSGT  "));
			} else if (isOfficer(values)) {
				list.add(genItem("1STLT "));
				list.add(genItem("2NDLT "));
				list.add(genItem("BGEN  "));
				list.add(genItem("CAPT  "));
				list.add(genItem("COL   "));
				list.add(genItem("CWO2  "));
				list.add(genItem("CWO3  "));
				list.add(genItem("CWO4  "));
				list.add(genItem("CWO5  "));
				list.add(genItem("GEN   "));
				list.add(genItem("LCOL  "));
				list.add(genItem("LGEN  "));
				list.add(genItem("MAJ   "));
				list.add(genItem("MGEN  "));
				list.add(genItem("WO1   "));
			}
		} else if (isAirForce(values)) {
			if (isEnlisted(values)) {
				list.add(genItem("AB    "));
				list.add(genItem("AN    "));
				list.add(genItem("ANFC  "));
				list.add(genItem("ANS   "));
				list.add(genItem("CMSGT "));
				list.add(genItem("CMSGTF"));
				list.add(genItem("MSGT  "));
				list.add(genItem("SGT   "));
				list.add(genItem("SMSGT "));
				list.add(genItem("SSGT  "));
				list.add(genItem("TSGT  "));
			} else if (isOfficer(values)) {
				list.add(genItem("1STLT "));
				list.add(genItem("2NDLT "));
				list.add(genItem("BGEN  "));
				list.add(genItem("CAPT  "));
				list.add(genItem("COL   "));
				list.add(genItem("CWO2  "));
				list.add(genItem("CWO3  "));
				list.add(genItem("CWO4  "));
				list.add(genItem("GEN   "));
				list.add(genItem("GENF  "));
				list.add(genItem("LCOL  "));
				list.add(genItem("LGEN  "));
				list.add(genItem("MAJ   "));
				list.add(genItem("MGEN  "));
				list.add(genItem("WO1   "));
			}
		} else if (isArmy(values)) {
			if (isEnlisted(values)) {
				list.add(genItem("1STSGT"));
				list.add(genItem("COSMAJ"));
				list.add(genItem("CPL   "));
				list.add(genItem("MSGT  "));
				list.add(genItem("PFC   "));
				list.add(genItem("PVT   "));
				list.add(genItem("PVT2  "));
				list.add(genItem("SGT   "));
				list.add(genItem("SGTFC "));
				list.add(genItem("SGTMAJ"));
				list.add(genItem("SMAJA "));
				list.add(genItem("SPEC4 "));
				list.add(genItem("SPEC5 "));
				list.add(genItem("SPEC6 "));
				list.add(genItem("SSGT  "));
			} else if (isOfficer(values)) {
				list.add(genItem("1STLT "));
				list.add(genItem("2NDLT "));
				list.add(genItem("BGEN  "));
				list.add(genItem("CAPT  "));
				list.add(genItem("COL   "));
				list.add(genItem("CWO2  "));
				list.add(genItem("CWO3  "));
				list.add(genItem("CWO4  "));
				list.add(genItem("GEN   "));
				list.add(genItem("GENA  "));
				list.add(genItem("LCOL  "));
				list.add(genItem("LGEN  "));
				list.add(genItem("MAJ   "));
				list.add(genItem("MGEN  "));
				list.add(genItem("WO1   "));
			}
		} else if (isCoastGuard(values)) {
			if (isEnlisted(values)) {
				list.add(genItem("CPO   "));
				list.add(genItem("MCPO  "));
				list.add(genItem("MCPOG "));
				list.add(genItem("PO1   "));
				list.add(genItem("PO2   "));
				list.add(genItem("PO3   "));
				list.add(genItem("SA    "));
				list.add(genItem("SCPO  "));
				list.add(genItem("SN    "));
				list.add(genItem("SR    "));
			} else if (isOfficer(values)) {
				list.add(genItem("ADM   "));
				list.add(genItem("CAPT  "));
				list.add(genItem("CDR   "));
				list.add(genItem("CWO2  "));
				list.add(genItem("CWO3  "));
				list.add(genItem("CWO4  "));
				list.add(genItem("ENS   "));
				list.add(genItem("LCDR  "));
				list.add(genItem("LT    "));
				list.add(genItem("LTJG  "));
				list.add(genItem("RADML "));
				list.add(genItem("RADMU "));
				list.add(genItem("VADM  "));
				list.add(genItem("WO1   "));
			}
		}
		callback.onSuccess(list);
	}

}
