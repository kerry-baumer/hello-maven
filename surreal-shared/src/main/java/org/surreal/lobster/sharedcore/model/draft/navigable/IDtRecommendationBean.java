package org.surreal.lobster.sharedcore.model.draft.navigable;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDvRecommendationBean;
import org.surreal.lobster.sharedcore.model.draft.IDvRecommendationVersionBean;

public interface IDtRecommendationBean extends IDvRecommendationBean {

	List<IDvRecommendationVersionBean> getVersions();
	void setVersions(List<IDvRecommendationVersionBean> versions);
	
	List<String> getFactors();
	void setFactors(List<String> factors);
	
}
