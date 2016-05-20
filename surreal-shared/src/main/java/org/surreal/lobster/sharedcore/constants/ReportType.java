package org.surreal.lobster.sharedcore.constants;

/**
 * Used in the workspace information to tell what type of report it is
 * @author Jeffrey.Richley
 */
public enum ReportType {
	AVIATION_MISHAP("Aviation Mishap", Category.AVIATION),
	AVIATION_HAZARD("Aviation Hazard", Category.AVIATION),
	AV_INITIAL_NOTIFICATION("Initial Notification (Aviation)", Category.AVIATION),
	CN_INITIAL_NOTIFICATION("Initial Notification", Category.CONSOLIDATED),
	CN_HAZARD("Consolidated Hazard", Category.CONSOLIDATED),
	MV_MISHAP("MVRS Mishap", Category.CONSOLIDATED),
	MV_HAZARD("MVRS Hazard", Category.CONSOLIDATED),
	AFLOAT_MISHAP("Afloat Mishap", Category.CONSOLIDATED),
	AFLOAT_HAZARD("Afloat Hazard", Category.CONSOLIDATED),
	ROD_MISHAP("Recreational/Off-duty Mishap", Category.CONSOLIDATED),
	ROD_HAZARD("Recreational/Off-duty Hazard", Category.CONSOLIDATED),
	SHORE_MISHAP("Shore/Ground Mishap", Category.CONSOLIDATED),
	SHORE_HAZARD("Shore/Ground Hazard", Category.CONSOLIDATED),
	OTHER("Other", Category.UNRECOGNIZED),
	UNKNOWN("Unknown", Category.UNRECOGNIZED);
	
	private final String desc;
	private final Category category;
	
	ReportType(String desc, Category category) {
		this.desc = desc;
		this.category = category;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public String getDescription() {
		return desc;
	}
	
	public static enum Category {
		CONSOLIDATED, 
		AVIATION,
		UNRECOGNIZED
	}
	
}
