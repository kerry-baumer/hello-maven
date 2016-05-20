package org.surreal.lobster.sharedcore.presenter;

import org.surreal.lobster.sharedcore.model.draft.IDvMedicalIncidentBean;
import org.surreal.lobster.sharedcore.provider.ServiceCallback;
import org.surreal.lobster.sharedcore.provider.UrlServiceProvider;
import org.surreal.lobster.sharedcore.view.IsForm;
import org.surreal.lobster.sharedcore.view.MedicalIncidentDetailsView;

public class MedicalIncidentDetailsPresenterImpl implements
		MedicalIncidentDetailsPresenter {

	private final MedicalIncidentDetailsView view;
	private final UrlServiceProvider urlServiceProvider;
	private final ServiceCallback.Default<String> navigateToSparcCommand;

//	@Inject
	public MedicalIncidentDetailsPresenterImpl(MedicalIncidentDetailsView view, UrlServiceProvider urlServiceProvider, ServiceCallback.Default<String> navigateToSparcCommand) {
		this.view = view;
		this.urlServiceProvider = urlServiceProvider;
		// see comment at bottom for explanation of this.
		this.navigateToSparcCommand = navigateToSparcCommand;
		view.setPresenter(this);
	}
	
	@Override
	public MedicalIncidentDetailsView getView() {
		return view;
	}

	@Override
	public void onCancelClicked() {
		navigateToSparc();
	}

	@Override
	public void onSubmitClicked() {
		final IsForm form = view.getReportableForm();
		form.submit();
	}

	@Override
	public void load(IDvMedicalIncidentBean medicalIncident) {
		view.getIncidentId().setText(medicalIncident.getIncidentId());
		view.getClaimant().setText(
				medicalIncident.getLastName() + ", " + medicalIncident.getFirstName());
		view.getServiceDate().setText(medicalIncident.getServiceDate());
		view.getDob().setText(medicalIncident.getDob());
		view.getDiagnosis1().setText(medicalIncident.getDiagnosis1());
		view.getDiagnosis2().setText(medicalIncident.getDiagnosis2());
		view.getDiagnosis3().setText(medicalIncident.getDiagnosis3());
		view.getDiagnosis4().setText(medicalIncident.getDiagnosis4());
		view.getDiagnosis5().setText(medicalIncident.getDiagnosisIcd9());
		view.getGender().setText(medicalIncident.getGender());
		view.getInjurySite().setText(medicalIncident.getInjurySite());
		view.getInjuryType().setText(medicalIncident.getInjuryType());

		final String type;
		if ("A".equals(medicalIncident.getTypeC())) {
			type = "ACUTE";
		} else if ("C".equals(medicalIncident.getTypeC())) {
			type = "CHRONIC";
		} else {
			type = "";
		}
		view.getType().setText(type);
		view.getSsn().setText(medicalIncident.getPatientId());
	}
	
	@Override
	public void submitCompleted() {
		navigateToSparc();
	}
	
	private void navigateToSparc() {
		urlServiceProvider.getSparcUrl(new ServiceCallback.Default<String>() {

			@Override
			public void onSuccess(String result) {
				/*
				 * INFO: (SEAN) - per Jeff Richley - since the Medical project
				 * is small we do not want to include Guice/Gin to do injection.
				 * Because of this we cannot use the EventBus (it is injection
				 * only, i was told not to change the scope of the constructor).
				 * Since we can't use the EventBus there's not really a clean way
				 * to get the AppController to handle navigating back to SPARC.
				 * in order to navigate back we are using a simple Command to tell
				 * the Controller to do this.
				 */
				navigateToSparcCommand.onSuccess(result);
			}
		});
	}
}
