package org.surreal.lobster.sharedcore.model.draft.navigable;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDvFactorAssociationBean;
import org.surreal.lobster.sharedcore.model.draft.IDvFactorVersionBean;
import org.surreal.lobster.sharedcore.model.draft.IDvOptionItem;

public interface IDtFactorVersionBean extends IDvFactorVersionBean {

	List<IDvOptionItem> getPreconditions();
	void setPreconditions(List<IDvOptionItem> preconditions);
	
	List<IDvFactorAssociationBean> getFactorAssociations();
	void setFactorAssociations(List<IDvFactorAssociationBean> factorAssociations);
	
}
