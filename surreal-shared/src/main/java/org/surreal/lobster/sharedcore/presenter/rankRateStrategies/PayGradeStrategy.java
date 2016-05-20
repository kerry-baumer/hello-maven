package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.view.OptionListItem;

public class PayGradeStrategy extends BaseRankRateEntryStrategy {

	@Override
	public boolean shouldShow(String... values) {
		return isCivilian(values) && values.length > 3 && !values[3].trim().isEmpty() && !oneOf(values[2], "B", "C", "F");
	}

	@Override
	public void getItems(DataBrokerCallback<List<OptionListItem>> callback, String... values) {
		List<OptionListItem> list = new ArrayList<OptionListItem>();
		if (values.length > 2) {
			list.add(genItem("      "));
			if (oneOf(values[2], "A")) {
				list.add(genItem("DA NEC"));
				list.add(genItem("DAA   "));
				list.add(genItem("DAI   "));
				list.add(genItem("DAII  "));
				list.add(genItem("DAIII "));
				list.add(genItem("DG NEC"));
				list.add(genItem("DGA   "));
				list.add(genItem("DGI   "));
				list.add(genItem("DGII  "));
				list.add(genItem("DGIII "));
				list.add(genItem("DGIV  "));
				list.add(genItem("DP NEC"));
				list.add(genItem("DPA   "));
				list.add(genItem("DPI   "));
				list.add(genItem("DPII  "));
				list.add(genItem("DPIII "));
				list.add(genItem("DPIV  "));
				list.add(genItem("DPV   "));
				list.add(genItem("DS NEC"));
				list.add(genItem("DSA   "));
				list.add(genItem("DSI   "));
				list.add(genItem("DSII  "));
				list.add(genItem("DSIII "));
				list.add(genItem("DT NEC"));
				list.add(genItem("DTA   "));
				list.add(genItem("DTI   "));
				list.add(genItem("DTII  "));
				list.add(genItem("DTIII "));
				list.add(genItem("GS NEC"));
				list.add(genItem("GS01  "));
				list.add(genItem("GS02  "));
				list.add(genItem("GS03  "));
				list.add(genItem("GS04  "));
				list.add(genItem("GS05  "));
				list.add(genItem("GS06  "));
				list.add(genItem("GS07  "));
				list.add(genItem("GS08  "));
				list.add(genItem("GS09  "));
				list.add(genItem("GS10  "));
				list.add(genItem("GS11  "));
				list.add(genItem("GS12  "));
				list.add(genItem("GS13  "));
				list.add(genItem("GS14  "));
				list.add(genItem("GS15  "));
				list.add(genItem("GS16  "));
				list.add(genItem("GS17  "));
				list.add(genItem("GS18  "));
			} else if (oneOf(values[2], "D")) {
				list.add(genItem("WD01  "));
				list.add(genItem("WD02  "));
				list.add(genItem("WD03  "));
				list.add(genItem("WD04  "));
				list.add(genItem("WD05  "));
				list.add(genItem("WD06  "));
				list.add(genItem("WD07  "));
				list.add(genItem("WD08  "));
				list.add(genItem("WD09  "));
				list.add(genItem("WD10  "));
				list.add(genItem("WD11  "));
				list.add(genItem("WD12  "));
				list.add(genItem("WD13  "));
				list.add(genItem("WD14  "));
				list.add(genItem("WD15  "));
				list.add(genItem("WG01  "));
				list.add(genItem("WG02  "));
				list.add(genItem("WG03  "));
				list.add(genItem("WG04  "));
				list.add(genItem("WG05  "));
				list.add(genItem("WG06  "));
				list.add(genItem("WG07  "));
				list.add(genItem("WG08  "));
				list.add(genItem("WG09  "));
				list.add(genItem("WG10  "));
				list.add(genItem("WG11  "));
				list.add(genItem("WG12  "));
				list.add(genItem("WG13  "));
				list.add(genItem("WG14  "));
				list.add(genItem("WG15  "));
				list.add(genItem("WG NEC"));
				list.add(genItem("WL01  "));
				list.add(genItem("WL02  "));
				list.add(genItem("WL03  "));
				list.add(genItem("WL04  "));
				list.add(genItem("WL05  "));
				list.add(genItem("WL06  "));
				list.add(genItem("WL07  "));
				list.add(genItem("WL08  "));
				list.add(genItem("WL09  "));
				list.add(genItem("WL10  "));
				list.add(genItem("WL11  "));
				list.add(genItem("WL12  "));
				list.add(genItem("WL13  "));
				list.add(genItem("WL14  "));
				list.add(genItem("WL15  "));
				list.add(genItem("WN01  "));
				list.add(genItem("WN02  "));
				list.add(genItem("WN03  "));
				list.add(genItem("WN04  "));
				list.add(genItem("WN05  "));
				list.add(genItem("WN06  "));
				list.add(genItem("WN07  "));
				list.add(genItem("WN08  "));
				list.add(genItem("WN09  "));
				list.add(genItem("WN10  "));
				list.add(genItem("WN11  "));
				list.add(genItem("WN12  "));
				list.add(genItem("WN13  "));
				list.add(genItem("WN14  "));
				list.add(genItem("WN15  "));
				list.add(genItem("WS01  "));
				list.add(genItem("WS02  "));
				list.add(genItem("WS03  "));
				list.add(genItem("WS04  "));
				list.add(genItem("WS05  "));
				list.add(genItem("WS06  "));
				list.add(genItem("WS07  "));
				list.add(genItem("WS08  "));
				list.add(genItem("WS09  "));
				list.add(genItem("WS10  "));
				list.add(genItem("WS11  "));
				list.add(genItem("WS12  "));
				list.add(genItem("WS13  "));
				list.add(genItem("WS14  "));
				list.add(genItem("WS15  "));
				list.add(genItem("WS16  "));
				list.add(genItem("WS17  "));
				list.add(genItem("WS18  "));
				list.add(genItem("WT01  "));
				list.add(genItem("WT02  "));
				list.add(genItem("WT03  "));
				list.add(genItem("WT04  "));
				list.add(genItem("WT05  "));
				list.add(genItem("WT06  "));
			} else if (oneOf(values[2], "E")) {
				list.add(genItem("GE02  "));
				list.add(genItem("GE03  "));
				list.add(genItem("GE04  "));
				list.add(genItem("NA01  "));
				list.add(genItem("NA02  "));
				list.add(genItem("NA03  "));
				list.add(genItem("NA04  "));
				list.add(genItem("NA05  "));
				list.add(genItem("NA06  "));
				list.add(genItem("NA07  "));
				list.add(genItem("NA08  "));
				list.add(genItem("NA09  "));
				list.add(genItem("NA10  "));
				list.add(genItem("NAD6  "));
				list.add(genItem("NAF NE"));
				list.add(genItem("NF01  "));
				list.add(genItem("NF02  "));
				list.add(genItem("NF03  "));
				list.add(genItem("NF04  "));
				list.add(genItem("NF0S  "));
				list.add(genItem("NFO1  "));
				list.add(genItem("NFO3  "));
				list.add(genItem("NFOI  "));
				list.add(genItem("NL01  "));
				list.add(genItem("NL02  "));
				list.add(genItem("NL06  "));
				list.add(genItem("NS01  "));
				list.add(genItem("NS03  "));
				list.add(genItem("NS05  "));
				list.add(genItem("NS08  "));
				list.add(genItem("PS02  "));
				list.add(genItem("PS03  "));
				list.add(genItem("PS05  "));
			}
		}
		callback.onSuccess(list);
	}

}
