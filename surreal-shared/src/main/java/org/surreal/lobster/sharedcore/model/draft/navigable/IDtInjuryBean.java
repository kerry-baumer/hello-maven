/**
 * Jun 7, 2012 3:43:25 PM
 */
package org.surreal.lobster.sharedcore.model.draft.navigable;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDvBodyPartBean;
import org.surreal.lobster.sharedcore.model.draft.IDvInjuryBean;
import org.surreal.lobster.sharedcore.model.draft.IDvInvlvdChemicalBean;
import org.surreal.lobster.sharedcore.model.draft.IDvLostWorkTimeBean;
import org.surreal.lobster.sharedcore.model.draft.IDvOffsiteMedicalBean;
import org.surreal.lobster.sharedcore.model.draft.IDvSharpsBean;

public interface IDtInjuryBean extends IDvInjuryBean {

	IDvSharpsBean getSharps();
	void setSharps(IDvSharpsBean sharps);
	
	IDvOffsiteMedicalBean getOffsiteMedical();
	void setOffsiteMedical(IDvOffsiteMedicalBean offsiteMedical);
	
	List<IDvLostWorkTimeBean> getLostTimeFrames();
	void setLostTimeFrames(List<IDvLostWorkTimeBean> lostTimeFrames);

	List<IDvBodyPartBean> getBodyParts();
	void setBodyParts(List<IDvBodyPartBean> bodyParts);

	List<IDvInvlvdChemicalBean> getChemicals();
	void setChemicals(List<IDvInvlvdChemicalBean> chemicals);


}
