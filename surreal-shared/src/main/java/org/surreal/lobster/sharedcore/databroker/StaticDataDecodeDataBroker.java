package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.OptionItem;

public interface StaticDataDecodeDataBroker {

	List<OptionItem> getFactorTypes();

	List<OptionItem> getDutyStatusTier2Data(String dutyStatC);

}
