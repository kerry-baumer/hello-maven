/**
 * 
 */
package org.surreal.lobster.sharedcore.model.draft.navigable;

import java.util.List;

import org.surreal.lobster.sharedcore.model.draft.IDv72HrProfileBean;
import org.surreal.lobster.sharedcore.model.draft.IDvAlchlLocnBean;
import org.surreal.lobster.sharedcore.model.draft.IDvCmdSafetyBean;
import org.surreal.lobster.sharedcore.model.draft.IDvDrugFactorBean;
import org.surreal.lobster.sharedcore.model.draft.IDvInvlvdPersonBean;
import org.surreal.lobster.sharedcore.model.draft.IDvIpSafetyCourseBean;
import org.surreal.lobster.sharedcore.model.draft.IDvLicenseBean;
import org.surreal.lobster.sharedcore.model.draft.IDvMotorVehicleActivityBean;
import org.surreal.lobster.sharedcore.model.draft.IDvPpeBean;

public interface IDtInvlvdPersonBean extends IDvInvlvdPersonBean {

	IDvMotorVehicleActivityBean getMotorVehicleActy();
	void setMotorVehicleActy(IDvMotorVehicleActivityBean motorVehicleActy);

	IDv72HrProfileBean getSeventy2HrProfile();
	void setSeventy2HrProfile(IDv72HrProfileBean seventy2HrProfile);

	List<IDvDrugFactorBean> getDrugFactors();
	void setDrugFactors(List<IDvDrugFactorBean> drugFactors);

	List<IDvLicenseBean> getLicenses();
	void setLicenses(List<IDvLicenseBean> licenses);
		
	List<IDvIpSafetyCourseBean> getSftyCourses();
	void setSftyCourses(List<IDvIpSafetyCourseBean> sftyCourses);

	List<IDvPpeBean> getPpe();
	void setPpe(List<IDvPpeBean> ppe);
	    
	List<IDvAlchlLocnBean> getAlchlLocn();
	void setAlchlLocn(List<IDvAlchlLocnBean> alchlLocn);
	
	/** Command Safety is not collected in Afloat Release 1 or by ShoreGround Core release */
	IDvCmdSafetyBean getCommandSafety();
	void setCommandSafety(IDvCmdSafetyBean commandSafety);
	
	IDtInjuryBean getInjury();
	void setInjury(IDtInjuryBean injury);
	
}
