/**
 * Feb 14, 2012 10:07:57 AM
 */
package org.surreal.lobster.sharedcore.model.draft.navigable;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDvEventBean;
import org.surreal.lobster.sharedcore.model.draft.IDvOptionItem;

/**
 * A consolidated draft event data transfer object. 
 * @author daniel.wilkin
 */
public interface IDtEventBean extends IDvEventBean {

	IDtReportSummary getSummary();
	void setSummary(IDtReportSummary summar);
	
	IDtPocBean getPoc();
	void setPoc(IDtPocBean poc);
	
	IDtEnvironmentBean getEnvironment();
	void setEnvironment(IDtEnvironmentBean bean);
	
	IDtLocationBean getLocation();
	void setLocation(IDtLocationBean location);
	
	List<IDvOptionItem> getMishapTypes();
	void setMishapTypes(List<IDvOptionItem> mishapTypes);

	List<IDtInvlvdEntityBean> getInvolvedEntities();
	void setInvolvedEntities(List<IDtInvlvdEntityBean> involvedEntities);
	
	List<IDtInvlvdPropertyBean> getInvolvedProperties();
	void setInvolvedProperties(List<IDtInvlvdPropertyBean> involvedProperties);
	
	List<IDtInvlvdPersonBean> getInvolvedPeople();
	void setInvolvedPeople(List<IDtInvlvdPersonBean> involvedPeople);
	
	List<IDtFactorBean> getFactors();
	void setFactors(List<IDtFactorBean> factors);

	List<IDtRecommendationBean> getRecommendations();
	void setRecommendations(List<IDtRecommendationBean> recommendation);
	
}
