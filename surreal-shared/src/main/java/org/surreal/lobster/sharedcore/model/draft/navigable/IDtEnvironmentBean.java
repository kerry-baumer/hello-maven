package org.surreal.lobster.sharedcore.model.draft.navigable;

import java.util.List;

import org.surreal.lobster.sharedcore.model.OptionItemDetail;
import org.surreal.lobster.sharedcore.model.draft.IDvEnvironmentBean;
import org.surreal.lobster.sharedcore.model.draft.IDvOptionItem;

public interface IDtEnvironmentBean extends IDvEnvironmentBean {

	List<IDvOptionItem> getVisibilityRstrdBy();
	void setVisibilityRstrdBy(List<IDvOptionItem> data);
	
	List<IDtTrafficControlBean> getTrafficControl();
	void setTrafficControl(List<IDtTrafficControlBean> trafficControls);
	
	List<OptionItemDetail> getTrafficCondition();
	void setTrafficCondition(List<OptionItemDetail> text);
	
	List<OptionItemDetail> getSurfaceCondition();
	void setSurfaceCondition(List<OptionItemDetail> text);
}
