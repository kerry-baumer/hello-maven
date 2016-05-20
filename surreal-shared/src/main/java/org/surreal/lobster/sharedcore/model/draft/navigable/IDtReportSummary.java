/**
 * Apr 17, 2012 8:08:13 AM
 */
package org.surreal.lobster.sharedcore.model.draft.navigable;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDvCoiBean;
import org.surreal.lobster.sharedcore.model.draft.IDvOptionItem;
import org.surreal.lobster.sharedcore.model.draft.IDvReportSummary;
import org.surreal.lobster.sharedcore.model.draft.IDvUicBean;

public interface IDtReportSummary extends IDvReportSummary {
	
	List<IDvOptionItem> getDraftParticipants();
	void setDraftParticipants(List<IDvOptionItem> draftParticipants);
	
	List<IDvCoiBean> getCois();
	void setCois(List<IDvCoiBean> cois);
	
	List<IDvUicBean> getAdditionalCommands();
	void setAdditionalCommands(List<IDvUicBean> uics);

}
