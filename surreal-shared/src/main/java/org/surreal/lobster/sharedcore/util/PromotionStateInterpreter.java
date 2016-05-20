package org.surreal.lobster.sharedcore.util;

import org.surreal.lobster.sharedcore.constants.PromotionState;
import org.surreal.lobster.sharedcore.databroker.GeneralInfoDataBroker;
import org.surreal.lobster.sharedcore.model.draft.IDvReportSummary;

public class PromotionStateInterpreter {

	private final GeneralInfoDataBroker db;

	public PromotionStateInterpreter(GeneralInfoDataBroker db) {
		this.db = db;
	}
	
	/**
	 * @return true if state can be determined and is true, otherwise false
	 */
	public boolean isInQa() {
		if (db.hasEvent()) {
			IDvReportSummary summary = db.getSummary();
			String currentState = summary.getCurrentState();
			return PromotionState.SUBMITTED_TO_QA.toString().equals(currentState) ||
					PromotionState.REQUEST_DELETE.toString().equals(currentState) ||
					PromotionState.RESUBMIT_TO_QA.toString().equals(currentState);
	//			return true;
		}
		return false;
	}

	/**
	 * @return true if state can be determined and is true, otherwise false
	 */
	public boolean isInDraft() {
		if (db.hasEvent()) {
			IDvReportSummary summary = db.getSummary();
			String currentState = summary.getCurrentState();
			return PromotionState.DRAFT.toString().equals(currentState);
		}
		return false;
	}

	/**
	 * @return true if state can be determined and is true, otherwise false
	 */
	public boolean isInEdit() {
		if (db.hasEvent()) {
			IDvReportSummary summary = db.getSummary();
			String currentState = summary.getCurrentState();
			return PromotionState.EDIT.toString().equals(currentState);
		}
		return false;
	}

	/**
	 * @return true if state can be determined and is true, otherwise false
	 */
	public boolean isRejectedByQa() {
		if (db.hasEvent()) {
			IDvReportSummary summary = db.getSummary();
			String currentState = summary.getCurrentState();
			return PromotionState.REJECTED_BY_QA.toString().equals(currentState);
		}
		return false;
	}
	
}
