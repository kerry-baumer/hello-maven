package org.surreal.lobster.sharedcore.presenter;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.databroker.LevelCodedDecodeDataBroker;
import org.surreal.lobster.sharedcore.event.EventBus;
import org.surreal.lobster.sharedcore.event.types.ServiceStatusChangedEvent;
import org.surreal.lobster.sharedcore.model.LevelCodedOptionItem;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.BaseRankRateEntryStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.BranchOfServiceStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.CivilianSeriesStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.ComponentCommandStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.EnlistedRatingStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.JobTitleStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.MajorCommandStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.MilitaryCategoryStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.ParentCommandStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.ParentUicStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.PayBandStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.PayGradeStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.PersonDeployedStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.PrimaryMosStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.RankStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.ServiceStatusStrategy;
import org.surreal.lobster.sharedcore.presenter.rankRateStrategies.UnitStrategy;
import org.surreal.lobster.sharedcore.view.IsCloakableDeselectable;
import org.surreal.lobster.sharedcore.view.OptionListItem;
import org.surreal.lobster.sharedcore.view.RankRateEntry;
import org.surreal.lobster.sharedcore.view.isDeselectable;

public class RankRateEntryPresenterImpl implements RankRateEntryPresenter {

	private static final String DUTY_STATUS_ON_DUTY_CODE = "A";
	
	private final RankRateEntry view;

	private final BaseRankRateEntryStrategy serviceStatusStrategy;
	private final BaseRankRateEntryStrategy branchOfServiceStategy;
	private final BaseRankRateEntryStrategy civilianSeriesStrategy;
	private final BaseRankRateEntryStrategy militaryCategoryStrategy;
	private final BaseRankRateEntryStrategy jobTitleStrategy;
	private final BaseRankRateEntryStrategy rankStrategy;
	private final BaseRankRateEntryStrategy primaryMosStrategy;
	private final BaseRankRateEntryStrategy enlistedRatingStrategy;
	private final BaseRankRateEntryStrategy payGradeStrategy;
	private final BaseRankRateEntryStrategy payBandStrategy;
	private final BaseRankRateEntryStrategy parentUicStrategy;
	private final BaseRankRateEntryStrategy personDeployedStrategy;
	private ComponentCommandStrategy componentCommandStrategy;
	private MajorCommandStrategy majorCommandStrategy;
	private BaseRankRateEntryStrategy parentCommandStrategy;
	private UnitStrategy unitStrategy;
	
	private final List<IsCloakableDeselectable> ddls = new ArrayList<IsCloakableDeselectable>();
	private final List<IsCloakableDeselectable> ccDdls = new ArrayList<IsCloakableDeselectable>();

	private String currentBranchOfService;
	private String currentServiceStatus;
	private String currentCivilianSeries;
	private String currentMilitaryCategory;
	private String currentRank;
	private String currentJobTitle;
	
	private String currentParentUnitCode;
	private String currentPersonDeployed;
	private String currentComponentCommand;
	private String currentMajorCommand;
	private String currentParentCommand;
	
	/*
	 * for civilians, certain fields (civilian series) are only required to 
	 * show an error if the duty status is set to on duty (currently "A"). need
	 * to listen to duty status changes to determine if we should be showing
	 * the error.
	 */
	private String dutyStatus;
	private String lastErrorMessage;

	private final EventBus eventBus;

	public RankRateEntryPresenterImpl(RankRateEntry view, EventBus eventBus) {
		this.view = view;
		this.eventBus = eventBus;
		branchOfServiceStategy = new BranchOfServiceStrategy();
		serviceStatusStrategy = new ServiceStatusStrategy();
		civilianSeriesStrategy = new CivilianSeriesStrategy();
		militaryCategoryStrategy = new MilitaryCategoryStrategy();
		jobTitleStrategy = new JobTitleStrategy();
		rankStrategy = new RankStrategy();
		primaryMosStrategy = new PrimaryMosStrategy();
		enlistedRatingStrategy = new EnlistedRatingStrategy();
		payGradeStrategy = new PayGradeStrategy();
		payBandStrategy = new PayBandStrategy();
		parentUicStrategy = new ParentUicStrategy();
		personDeployedStrategy = new PersonDeployedStrategy();

		load();
	}

	public void load() {
		
		ddls.add(view.getBranchOfService()); 	// 1
		ddls.add(view.getServiceStatus()); 		// 2
		ddls.add(view.getCivilianSeries()); 	// 3
		ddls.add(view.getMilitaryCategory()); 	// 3
		ddls.add(view.getJobTitle()); 			// 4
		ddls.add(view.getRank()); 				// 4
		ddls.add(view.getPrimaryMOS()); 		// 5
		ddls.add(view.getEnlistedRating()); 	// 5
		ddls.add(view.getPayGrade()); 			// 5
		ddls.add(view.getPaybandLevel()); 		// 5
		
		ccDdls.add(view.getPersonDeployed());	// 7
		ccDdls.add(view.getComponentCommand());	// 8
		ccDdls.add(view.getMajorCommand());		// 9
		ccDdls.add(view.getParentCommand());	// 10
		ccDdls.add(view.getUnit());				// 11
		
		DataBrokerCallback<List<OptionListItem>> callback = 
			new DataBrokerCallback<List<OptionListItem>>() {
				@Override
				public void onFailure(Throwable caught) { }
				@Override
				public void onSuccess(List<OptionListItem> result) {
					view.getBranchOfService().setData(result);
				}
			};
		branchOfServiceStategy.getItems(callback, (String)null);
	}
	
	public void resetCCAfter() {
		int idx = 0;
		if (idx >= 0) {
			for (int i = idx; i < ccDdls.size(); i++) {
				if (ccDdls.get(i) == null) {
					System.out.println("can't find drop down at index: " + i);
				} else {
					ccDdls.get(i).deselect();
					ccDdls.get(i).setCloaked(true);
				}
			}
		}
	}
	
	public void resetCCAfter(isDeselectable ddl) {
		int idx = ccDdls.indexOf(ddl) + 1;
		if (idx >= 0) {
			for (int i = idx; i < ccDdls.size(); i++) {
				if (ccDdls.get(i) == null) {
					System.out.println("can't find drop down at index: " + i);
				} else {
					ccDdls.get(i).deselect();
					ccDdls.get(i).setCloaked(true);
				}
			}
		}
	}
	
	public void resetAfter(isDeselectable ddl) {
		int idx = ddls.indexOf(ddl) + 1;
		if (idx >= 0) {
			for (int i = idx; i < ddls.size(); i++) {
				if (ddls.get(i) == null) {
					System.out.println("can't find drop down at index: " + i);
				} else {
					ddls.get(i).deselect();
					ddls.get(i).setCloaked(true);
				}
			}
		}
	}

	@Override
	public void onBranchOfServiceSelect(String key) {
		resetAfter(view.getBranchOfService());
		currentBranchOfService = key;
		if (key == null || key.isEmpty()) {
			view.getBranchOfService().deselect();
		} else {
			boolean serviceStatusShouldShow = serviceStatusStrategy.shouldShow(key);
			if (serviceStatusShouldShow) {
				DataBrokerCallback<List<OptionListItem>> callback = 
						new DataBrokerCallback<List<OptionListItem>>() {
							@Override
							public void onFailure(Throwable caught) { }
							@Override
							public void onSuccess(List<OptionListItem> result) {
								view.getServiceStatus().setData(result);
							}
						};
				serviceStatusStrategy.getItems(callback, key);
			}
			view.getServiceStatus().setCloaked(!serviceStatusShouldShow);
			view.getBranchOfService().setData(getDataWithValueSelected(branchOfServiceStategy, key));

			/*
			 * for branch of service - we always want to display an error. if
			 * the branch of service is selected then service status must be
			 * selected - otherwise branch of service must still be selected
			 */
			final boolean branchOfServiceNotSelected = key.trim().isEmpty();
			final String errorMessage;
			if (branchOfServiceNotSelected) {
				errorMessage = "Please select Branch of Service";
			} else {
				errorMessage = "Please select Service Status";
			}
			setFinished(true, errorMessage);
		}
		resetCCAfter();
	}
	
	@Override
	public void onServiceStatusSelect(String key) {
		onServiceStatusSelect(key, false);
	}
	
	@Override
	public void onServiceStatusSelect(String key, boolean fireChangeEvent) {
		currentServiceStatus = key;
		if (fireChangeEvent) {
			eventBus.dispatch(new ServiceStatusChangedEvent(key));
			System.out.println("service status: " + key);
		}
		if (key == null || key.isEmpty()) {
			return;
		}
		String[] levels = new String[] { currentBranchOfService, key};
		// want to only show please select civilian series error if duty status is actually on duty
		boolean civilianSeriesShouldShow = civilianSeriesStrategy.shouldShow(levels);
		boolean militaryCategoryShouldShow = militaryCategoryStrategy.shouldShow(levels);
		boolean parentUicShouldShow = parentUicStrategy.shouldShow(levels);
		
		if (civilianSeriesShouldShow) {
			DataBrokerCallback<List<OptionListItem>> callback = 
					new DBCallback<List<OptionListItem>>() {
						@Override
						public void onSuccess(List<OptionListItem> result) {
							view.getCivilianSeries().setData(result);
						}
					};
			civilianSeriesStrategy.getItems(callback, levels);
		} else if (militaryCategoryShouldShow) {
			DataBrokerCallback<List<OptionListItem>> callback = 
					new DBCallback<List<OptionListItem>>() {
				@Override
				public void onSuccess(List<OptionListItem> result) {
					view.getMilitaryCategory().setData(result);
				}
			};
			militaryCategoryStrategy.getItems(callback);
		}
		resetAfter(view.getServiceStatus());
		resetCCAfter();
		view.getCivilianSeries().setCloaked(!civilianSeriesShouldShow);
		view.getMilitaryCategory().setCloaked(!militaryCategoryShouldShow);
		view.getServiceStatus().setData(getDataWithValueSelected(serviceStatusStrategy, levels));
		view.getParentUnit().setCloaked(!parentUicShouldShow);
		view.getPersonDeployed().setCloaked(!personDeployedStrategy.shouldShow(new String[] { currentServiceStatus, currentParentUnitCode}));
		
		final boolean serviceStatusNotSelected = key.trim().isEmpty();
		final String errorMessage;
		if (serviceStatusNotSelected) {
			errorMessage = "Please select Service Status";
		} else {
			if (civilianSeriesShouldShow) {
				errorMessage = "Please select Civilian Series";
			} else if (militaryCategoryShouldShow) {
				errorMessage = "Please select Miitary Category";
			} else {
				errorMessage = null;
			}
		}
		final boolean shouldShowError = civilianSeriesShouldShow ? DUTY_STATUS_ON_DUTY_CODE.equals(dutyStatus) : 
			(serviceStatusNotSelected || militaryCategoryShouldShow);
		setFinished(shouldShowError, errorMessage);
	}

	@Override
	public void onCivilianSeriesSelect(String key) {
		currentCivilianSeries = key;
		if (key == null || key.isEmpty()) {
			return;
		}
		final String[] levels = new String[] { currentBranchOfService, currentServiceStatus, key};
		final boolean civilianSeriesShouldShow = civilianSeriesStrategy.shouldShow(levels);
		final boolean jobTitleShouldShow = jobTitleStrategy.shouldShow(levels);
		if (jobTitleShouldShow) {
			DataBrokerCallback<List<OptionListItem>> callback = 
					new DBCallback<List<OptionListItem>>() {
				@Override
				public void onSuccess(List<OptionListItem> result) {
					view.getJobTitle().setData(result);
				}
			};
			jobTitleStrategy.getItems(callback, levels);
		}
		resetAfter(view.getMilitaryCategory());
		view.getJobTitle().setCloaked(!jobTitleShouldShow);
		view.getCivilianSeries().setData(getDataWithValueSelected(civilianSeriesStrategy, levels));
		
		final boolean civilianSeriesNotSelected = key.trim().isEmpty();
		final String errorMessage;
		if (civilianSeriesNotSelected) {
			errorMessage = "Please select Civilian Series";
		} else if (jobTitleShouldShow) {
			errorMessage = "Please select Job Title";
		} else {
			errorMessage = null;
		}
		
		final boolean shouldShowError = civilianSeriesShouldShow && (DUTY_STATUS_ON_DUTY_CODE.equals(dutyStatus) ? 
				(civilianSeriesNotSelected || jobTitleShouldShow) : false);
		if (civilianSeriesShouldShow) {
			setFinished(shouldShowError, errorMessage);
		}
	}

	@Override
	public void onMilitaryCategorySelect(String key) {
		currentMilitaryCategory = key;
		if (key == null || key.isEmpty()) {
			return;
		}
		final String[] levels = new String[] { currentBranchOfService, currentServiceStatus, key};
		final boolean militaryCategoryShouldShow = militaryCategoryStrategy.shouldShow(levels);
		final boolean rankShouldShow = rankStrategy.shouldShow(levels);
		final boolean rankIsRequired = rankStrategy.isRequired(levels);
		if (rankShouldShow) {
			DataBrokerCallback<List<OptionListItem>> callback = 
					new DBCallback<List<OptionListItem>>() {
				@Override
				public void onSuccess(List<OptionListItem> result) {
					view.getRank().setData(result);
				}
			};
			rankStrategy.getItems(callback, levels);
		}
		resetAfter(view.getMilitaryCategory());
		view.getRank().setCloaked(!rankShouldShow);
		view.getMilitaryCategory().setData(getDataWithValueSelected(militaryCategoryStrategy, levels));
		
		final boolean militaryCategoryNotSelected = key.trim().isEmpty();
		final String errorMessage;
		if (militaryCategoryNotSelected) {
			errorMessage = "Please select Military Category";
		} else if (rankIsRequired) {
			errorMessage = "Please select Rank";
		} else {
			errorMessage = null;
		}
		final boolean shouldShowError = militaryCategoryShouldShow && (militaryCategoryNotSelected || rankIsRequired);
		if (militaryCategoryShouldShow) {
			setFinished(shouldShowError, errorMessage);
		}
	}
	
	@Override
	public void onJobTitleSelect(String key) {
		currentJobTitle = key;
		if (key == null || key.isEmpty()) {
			return;
		}
		final String[] levels = new String[] { currentBranchOfService, currentServiceStatus, currentCivilianSeries, key};
		final boolean jobTitleShouldShow = jobTitleStrategy.shouldShow(levels);
		final boolean payBandShouldShow = payBandStrategy.shouldShow(levels);
		final boolean payGradeShouldShow = payGradeStrategy.shouldShow(levels);
		if (payBandShouldShow) {
			DataBrokerCallback<List<OptionListItem>> callback = 
					new DBCallback<List<OptionListItem>>() {
				@Override
				public void onSuccess(List<OptionListItem> result) {
					view.getPaybandLevel().setData(result);
				}
			};
			payBandStrategy.getItems(callback, levels);
		}
		if (payGradeShouldShow) {
			DataBrokerCallback<List<OptionListItem>> callback = 
					new DBCallback<List<OptionListItem>>() {
				@Override
				public void onSuccess(List<OptionListItem> result) {
					view.getPayGrade().setData(result);
				}
			};
			payGradeStrategy.getItems(callback, levels);
		}
		resetAfter(view.getPaybandLevel());
		view.getPaybandLevel().setCloaked(!payBandShouldShow);
		view.getPayGrade().setCloaked(!payGradeShouldShow);
		view.getJobTitle().setData(getDataWithValueSelected(jobTitleStrategy, levels));
		
		final boolean jobTitleNotSelected = key.trim().isEmpty();
		final String errorMessage;
		if (jobTitleNotSelected) {
			errorMessage = "Please select Job Title";
		} else {
			if (payBandShouldShow) {
				errorMessage = "Please select Pay Band Level";
			} else if (payGradeShouldShow) {
				errorMessage = "Please select Pay Grade";
			} else {
				errorMessage = null;
			}
		}
		
		// we only show job title for civilians - need to check duty status
		final boolean shouldShowError = jobTitleShouldShow && (DUTY_STATUS_ON_DUTY_CODE.equals(dutyStatus) ? 
				(jobTitleNotSelected || payBandShouldShow || payGradeShouldShow) : false);
		if (jobTitleShouldShow) {
			setFinished(shouldShowError, errorMessage);
		}
	}
	
	@Override
	public void onRankSelect(String key) {
		currentRank = key;
		if (key == null || key.isEmpty()) {
			return;
		}
		final String[] levels = new String[] { currentBranchOfService, currentServiceStatus, currentMilitaryCategory, key};
		final boolean rankIsRequired = rankStrategy.isRequired(levels);
		final boolean rankShouldShow = rankStrategy.shouldShow(levels); 
		final boolean primaryMosShouldShow = primaryMosStrategy.shouldShow(levels);
		final boolean enlistedRatingShouldShow = enlistedRatingStrategy.shouldShow(levels);
		if (primaryMosShouldShow) {
			DataBrokerCallback<List<OptionListItem>> callback = 
					new DBCallback<List<OptionListItem>>() {
				@Override
				public void onSuccess(List<OptionListItem> result) {
					view.getPrimaryMOS().setData(result);
				}
			};
			primaryMosStrategy.getItems(callback, levels);
		} else if (enlistedRatingShouldShow) {
			DataBrokerCallback<List<OptionListItem>> callback = 
					new DBCallback<List<OptionListItem>>() {
				@Override
				public void onSuccess(List<OptionListItem> result) {
					view.getEnlistedRating().setData(result);
				}
			};
			enlistedRatingStrategy.getItems(callback, levels);
		}
		
		resetAfter(view.getRank());
		view.getPrimaryMOS().setCloaked(!primaryMosShouldShow);
		view.getEnlistedRating().setCloaked(!enlistedRatingShouldShow);
		view.getRank().setCloaked(!rankShouldShow);
		view.getRank().setData(getDataWithValueSelected(rankStrategy, levels));
		
		final boolean rankNotSelected = key.trim().isEmpty();
		final String errorMessage;
		if (rankNotSelected) {
			errorMessage = "Please select Rank";
		} else {
			if (primaryMosShouldShow) {
				errorMessage = "Please select Primary MOS";
			} else if (enlistedRatingShouldShow) {
				errorMessage = "Please select Enlisted Rating";
			} else {
				errorMessage = null;
			}
		}
		
		final boolean shouldShowError = rankIsRequired && rankShouldShow && (rankNotSelected || primaryMosShouldShow || enlistedRatingShouldShow);
		if (rankIsRequired) {
			setFinished(shouldShowError, errorMessage);
		}
	}

	@Override
	public void onEnlistedRatingSelect(String key) {
		if (key == null || key.isEmpty()) {
			return;
		}
		final String[] levels = new String[] { currentBranchOfService, currentServiceStatus, currentMilitaryCategory, currentRank, key};
		final boolean enlistedRatingShouldShow = enlistedRatingStrategy.shouldShow(levels);
		view.getEnlistedRating().setData(getDataWithValueSelected(enlistedRatingStrategy, levels));
		view.getEnlistedRating().setCloaked(!enlistedRatingShouldShow);

		final boolean enlistedRatingNotSelected = key.trim().isEmpty();
		final String errorMessage;
		if (enlistedRatingNotSelected) {
			errorMessage = "Please select Enlisted Rating";
		} else {
			errorMessage = null;
		}
		
		final boolean shouldShowError = enlistedRatingShouldShow && (enlistedRatingNotSelected);
		if (enlistedRatingShouldShow) {
			setFinished(shouldShowError, errorMessage);
		}
	}

	@Override
	public void onPrimaryMOSSelect(String key) {
		if (key == null || key.isEmpty()) {
			return;
		}
		final String[] levels = new String[] { currentBranchOfService, currentServiceStatus, currentMilitaryCategory, currentRank, key};
		final boolean primaryMosShouldShow = primaryMosStrategy.shouldShow(levels);
		view.getPrimaryMOS().setData(getDataWithValueSelected(primaryMosStrategy, levels));
		view.getPrimaryMOS().setCloaked(!primaryMosShouldShow);
		
		final boolean primaryMosNotSelected = key.trim().isEmpty();
		final String errorMessage;
		if (primaryMosNotSelected) {
			errorMessage = "Please select Primary MOS";
		} else {
			errorMessage = null;
		}
		
		final boolean shouldShowError = primaryMosShouldShow && (primaryMosNotSelected);
		if (primaryMosShouldShow) {
			setFinished(shouldShowError, errorMessage);
		}
	}

	@Override
	public void onPayGradeSelect(String key) {
		if (key == null || key.isEmpty()) {
			return;
		}
		final String[] levels = new String[] { currentBranchOfService, currentServiceStatus, currentCivilianSeries, currentJobTitle, key};
		final boolean payGradeShouldShow = payGradeStrategy.shouldShow(levels);
		view.getPayGrade().setData(getDataWithValueSelected(payGradeStrategy, levels));
		view.getPayGrade().setCloaked(!payGradeShouldShow);
		
		final boolean payGradeNotSelected = key.trim().isEmpty();
		final String errorMessage;
		if (payGradeNotSelected) {
			errorMessage = "Please select Pay Grade";
		} else {
			errorMessage = null;
		}
		
		// pay grade is related to civilians - need to make sure they're on duty
		final boolean shouldShowError = payGradeShouldShow && (DUTY_STATUS_ON_DUTY_CODE.equals(dutyStatus) ? 
				payGradeNotSelected : false);
		if (payGradeShouldShow) {
			setFinished(shouldShowError, errorMessage);
		}
	}

	@Override
	public void onPaybandLevelSelect(String value) {
		if (value == null || value.isEmpty()) {
			return;
		}
		final String[] levels = new String[] { currentBranchOfService, currentServiceStatus, currentCivilianSeries, currentJobTitle, value};		
		final boolean payBandShouldShow = payBandStrategy.shouldShow(levels);
		view.getPaybandLevel().setData(getDataWithValueSelected(payBandStrategy, levels));
		view.getPaybandLevel().setCloaked(!payBandShouldShow);
		
		final boolean payBandNotSelected = value.trim().isEmpty();
		final String errorMessage;
		if (payBandNotSelected) {
			errorMessage = "Please select Payband Level";
		} else {
			errorMessage = null;
		}
		
		// pay band is related to civilians - need to make sure they're on duty
		final boolean shouldShowError = payBandShouldShow && (DUTY_STATUS_ON_DUTY_CODE.equals(dutyStatus) ?
				payBandNotSelected : false);
		if (payBandShouldShow) {
			setFinished(shouldShowError, errorMessage);
		}
	}

	@Override
	public void onParentUnitCodeSelect(String value) {
		currentParentUnitCode = value;
		if (value == null || value.isEmpty()) {
			view.getParentUnit().setText(value);
			return;
		} 
		
		String[] levels = new String[] {currentServiceStatus, value};		
		boolean personDeployedShouldShow = personDeployedStrategy.shouldShow(levels);
		
		resetCCAfter();
		
		view.getParentUnit().setText(value);
		
		view.getPersonDeployed().setCloaked(!personDeployedShouldShow);
	}
	
	@Override
	public void onPersonDeployedClicked(String text) {
		currentPersonDeployed = text;
		if (text == null || text.isEmpty()) {
			return;
		}
		
		String[] levels = new String[] {currentParentUnitCode};		
		boolean componentCommandShouldShow = componentCommandStrategy.shouldShow(levels);
		
		resetCCAfter(view.getComponentCommand());
		view.getPersonDeployed().setText(text);
		view.setComponentCommandCloaked(!componentCommandShouldShow);
		if (componentCommandShouldShow) {
			componentCommandStrategy.getLevelCodedItems(new DBCallback<List<LevelCodedOptionItem>>() {
				@Override
				public void onSuccess(List<LevelCodedOptionItem> result) {
					view.setComponentCommandData(wrap(result));
				}}, "");
		}
	}
	
	@Override
	public void onComponentCommandSelect(String value) {
		currentComponentCommand = value;
		if (value == null || value.isEmpty()) {
			return;
		}
		
		String[] levels = new String[] {currentParentUnitCode, currentPersonDeployed, currentComponentCommand};
		
		boolean majorCommandShouldShow = majorCommandStrategy.shouldShow(levels);
		view.setMajorCommandCloaked(!majorCommandShouldShow);
		if (majorCommandShouldShow) {
			majorCommandStrategy.getLevelCodedItems(new DBCallback<List<LevelCodedOptionItem>>() {
				@Override
				public void onSuccess(List<LevelCodedOptionItem> result) {
					view.setMajorCommandData(wrap(result));
				}
			}, levels);
		}
		resetCCAfter(view.getMajorCommand());
		
		// what is happening right here?
		DBCallback<List<OptionListItem>> cb = new DBCallback<List<OptionListItem>>() { 
			@Override public void onSuccess(List<OptionListItem> result) {
				view.setComponentCommandData(result);
			}
		};
		
		getLevelCodedDataWithValueSelected(cb, componentCommandStrategy, value);
	}
	
	@Override
	public void onMajorCommandSelect(String value) {
		currentMajorCommand = value;
		if (value == null || value.isEmpty()) {
			return;
		}
		
		String[] levels = new String[] {currentParentUnitCode, currentPersonDeployed, currentComponentCommand, currentMajorCommand};
		
		boolean parentCommandShouldShow = parentCommandStrategy.shouldShow(levels);
		view.setParentCommandCloaked(!parentCommandShouldShow);
		if (parentCommandShouldShow) {
			parentCommandStrategy.getLevelCodedItems(new DBCallback<List<LevelCodedOptionItem>>() {
				@Override
				public void onSuccess(List<LevelCodedOptionItem> result) {
					view.setParentCommandData(wrap(result));
				}
			}, levels);
		}
		resetCCAfter(view.getParentCommand());
		
		DBCallback<List<OptionListItem>> cb = new DBCallback<List<OptionListItem>>() { 
			@Override public void onSuccess(List<OptionListItem> result) {
				view.setMajorCommandData(result);
			}
		};
		getLevelCodedDataWithValueSelected(cb, majorCommandStrategy, levels);
	}

	@Override
	public void onParentCommandSelect(String value) {
		currentParentCommand = value;
		if (value == null || value.isEmpty()) {
			return;
		}
		
		String[] levels = new String[] { currentParentUnitCode, currentPersonDeployed, currentComponentCommand, currentMajorCommand, value};
		
		boolean unitShouldShow = unitStrategy.shouldShow(levels);
		view.setUnitCloaked(!unitShouldShow);
		if (unitShouldShow) {
			unitStrategy.getLevelCodedItems(new DBCallback<List<LevelCodedOptionItem>>() {
				@Override
				public void onSuccess(List<LevelCodedOptionItem> result) {
					view.setUnitData(wrap(result));
				}
			}, levels);
		}
		
		DBCallback<List<OptionListItem>> cb = new DBCallback<List<OptionListItem>>() { 
			@Override public void onSuccess(List<OptionListItem> result) {
				view.setParentCommandData(result);
			}
		};
		getLevelCodedDataWithValueSelected(cb, parentCommandStrategy, levels);
		
	}

	@Override
	public void onUnitSelect(String value) {
		if (value == null || value.isEmpty()) {
			return;
		}
		String[] levels = new String[] { currentParentUnitCode, currentPersonDeployed, currentComponentCommand, currentMajorCommand, currentParentCommand, value};
		
		DBCallback<List<OptionListItem>> cb = new DBCallback<List<OptionListItem>>() { 
			@Override public void onSuccess(List<OptionListItem> result) {
				view.setUnitData(result);
			}
		};
		getLevelCodedDataWithValueSelected(cb, unitStrategy, levels);
	}

	public List<OptionListItem> getDataWithValueSelected(BaseRankRateEntryStrategy s, final String... values) {
		final List<OptionListItem> data = new ArrayList<OptionListItem>();
		DataBrokerCallback<List<OptionListItem>> callback = 
				new DBCallback<List<OptionListItem>>() {
			@Override
			public void onSuccess(List<OptionListItem> result) {
				data.addAll(result); 
				for (OptionListItem item : data) {
					if (values[values.length-1].equals(item.getValue())) {
						item.setSelected(true);
					} else {
						item.setSelected(false);
					}
				}
			}
		};
		s.getItems(callback, values);
		return data;
	}
	public void getLevelCodedDataWithValueSelected(final DataBrokerCallback<List<OptionListItem>> cb, BaseRankRateEntryStrategy s, final String... values) {
		final List<OptionListItem> data = new ArrayList<OptionListItem>();
		DataBrokerCallback<List<LevelCodedOptionItem>> callback = new DBCallback<List<LevelCodedOptionItem>>() {
			@Override
			public void onSuccess(List<LevelCodedOptionItem> result) {
				data.addAll(wrap(result)); 
				for (OptionListItem item : data) {
					if (values[values.length-1].equals(item.getValue())) {
						item.setSelected(true);
					} else {
						item.setSelected(false);
					}
				}
				cb.onSuccess(data);
			}
		};
		s.getLevelCodedItems(callback, values);
	}

	private void setFinished(boolean showError, String errorMessage) {
		view.showError(showError, errorMessage);
		lastErrorMessage = errorMessage;
	}
	
	@Override
	public void setLevelCodedDecodeDatabroker(LevelCodedDecodeDataBroker levelCodedDatabroker) {
		componentCommandStrategy = new ComponentCommandStrategy(levelCodedDatabroker);
		majorCommandStrategy = new MajorCommandStrategy(levelCodedDatabroker);
		parentCommandStrategy = new ParentCommandStrategy(levelCodedDatabroker);
		unitStrategy = new UnitStrategy(levelCodedDatabroker);
	}

	public List<OptionListItem> wrap(List<LevelCodedOptionItem> result) {
		List<OptionListItem> data = new ArrayList<OptionListItem>();
		for (LevelCodedOptionItem item : result) {
			data.add(new OptionListItem(item));
		}
		return data;
	}
	
	abstract class DBCallback<T> implements DataBrokerCallback<T> {
		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}
	}
	
	@Override
	public void onDutyStatusChanged(String dutyStatus) {
		this.dutyStatus = dutyStatus;
		if (lastErrorMessage == null) {
			return;
		}
		if (dutyStatus == null || !dutyStatus.contains("A") && "FGJ".contains(view.getServiceStatusValue())) {
			view.showError(false, lastErrorMessage);
		} else if (dutyStatus.contains("A") && "FGJ".contains(view.getServiceStatusValue())) {
			view.showError(true, lastErrorMessage);
		}
	}
}