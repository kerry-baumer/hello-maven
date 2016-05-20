package org.surreal.lobster.sharedcore.model.draft.navigable;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDvFactorBean;

public interface IDtFactorBean extends IDvFactorBean {

	List<IDtFactorVersionBean> getVersions();
	void setVersions(List<IDtFactorVersionBean> versions);
	
}
