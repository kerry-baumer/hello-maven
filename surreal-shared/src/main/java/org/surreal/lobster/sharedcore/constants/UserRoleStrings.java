/**
 * 
 */
package org.surreal.lobster.sharedcore.constants;

/**
 * @author kerry.baumer
 *
 */
public enum UserRoleStrings {
	AFLOAT_DATA_ENTRY("AFLOAT_ENTRY"),
	AFLOAT_NOTIFICATION("AFLOAT_NTFCN"),
	AFLOAT_RELEASOR("AFLOAT_RELEASER"),
	AVIATION_DATA_ENTRY("AVN_ENTRY"),
	AVIATION_ENDORSER("AVN_ENDORSR"),
	AVIATION_NOTIFICATION("AV_NTFCN_OLD"),
	DEVELOPER("DEVELOPER"),
	DJSR_LOGGING("DJRS_LOGGING"),
	DRAFT_RELEASOR("DRAFT_RLSR"),
	FULL_NOTIFICATION("FULL_NTFCN"),
	HAZREP_NOTIFICATION("HAZR_NTFCN"),
	HIPAA_PROFESSIONAL("HIPAA_PRO"),
	INITIAL_NOTIFICATION("INIT_NTFCN"),
	INTERNAL_QA("INTRNL_QA"),
	MISREP_HASZREP_RESPONDER("MIZ_HAZ_RSPR"),
	PRIVILEDGED_ACCESS("PRIV_ACCESS"),
	QA_SUPERVISOR("QA_SUPV"),
	SHORE_DATA_ENTRY("SHORE_ENTRY"),
	SHORE_NOTIFICATION("SHORE_NTFCN");

	private String roleC;
	
	private UserRoleStrings(String roleC) {
		this.roleC = roleC;
	}
	public String getRoleCode() {
		return roleC;
	}
	public UserRoleStrings fromRoleCode(String roleC) {
		UserRoleStrings answer = null;
		for(UserRoleStrings urs : values()) {
			if(urs.getRoleCode().equals(roleC)) {
				answer = urs; break;
			}
		}
		return answer;
	}
}
