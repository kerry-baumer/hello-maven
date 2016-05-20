package org.surreal.lobster.sharedcore.presenter;

import org.surreal.lobster.sharedcore.presenter.DecodeField;
import org.surreal.lobster.sharedcore.presenter.LevelCodedDecodeGroup;


/**
 * A one to one mapping between decode table/entity name and a convenience constant.
 * @see LevelCodedDecodeGroup
 * @see DecodeField
 * @author kerry.baumer
 * @author daniel.wilkin
 */
public enum LevelCodedDecodeField {
	ATC_POSITION("AtcPosnDecode"),
	BLS_ACCIDENT_TYPE("BlsAcdtTypeDecode"),
	BLS_BODY_PART("BlsBodyPartDecode"),
	BLS_INJURY_TYPE("BlsInjTypeDecode"),
	BLS_OBJECT_INVOLVED("BlsObjInvlvdDecode"),
	COMPONENT_DAMAGE_FAILURE("CmpntDmgFlrDecode"),
	COMPONENT_COMMAND("CompCmdDecode"),
	HFAC_ACT("HfacActDecode"),
	HFAC_PRECONDITION("HfacPreconditionDecode"),
	IMC("ImcDecode"),
	INCIDENT_PHASE_OF_OPERATIONS("IncdtPhaseOpsDecode"),
	INCIDENT_TYPE("IncdtTypeDecode"),
	INSTALLATION("InstallationDecode"),
	LICNS("LicnsDecode"),
	MAJOR_CLAIMANT("MajClmDecode"),
	OPERATIONAL_FORCES("OpforDecode"),
	SPECIES("SpeciesDecode"),
	WHO_FACTOR("WhoFacDecode"),
	PORT("PortDecode"),
	PPE("ProtvEqpmDecode"),
	PCN("PcnDecode"),
	AREA("AreaDecode"),
	VEH_BODY_TYPE("VehBodyTypeDecode")
	;
	
	private static final String DECODE_PKG_NAME = "org.surreal.lobster.pegasus.ejb.model.decode.";
	private final String name;
	private final String decodeClass;
	
	private LevelCodedDecodeField(String decodeName) {
		this.name = decodeName;
		this.decodeClass = DECODE_PKG_NAME.concat(decodeName);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDecodeClass() {
		return decodeClass;
	}
}
