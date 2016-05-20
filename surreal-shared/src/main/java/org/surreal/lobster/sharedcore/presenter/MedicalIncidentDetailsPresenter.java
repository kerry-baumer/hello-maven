package org.surreal.lobster.sharedcore.presenter;

import org.surreal.lobster.sharedcore.model.draft.IDvMedicalIncidentBean;
import org.surreal.lobster.sharedcore.view.MedicalIncidentDetailsView;

public interface MedicalIncidentDetailsPresenter {

	MedicalIncidentDetailsView getView();
	
	void onCancelClicked();

	void onSubmitClicked();
	
	void load(IDvMedicalIncidentBean medicalIncident);

	void submitCompleted();
}
