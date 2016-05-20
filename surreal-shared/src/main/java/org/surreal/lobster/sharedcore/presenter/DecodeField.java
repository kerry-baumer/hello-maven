package org.surreal.lobster.sharedcore.presenter;



/**
 * A one to one mapping between decode table/entity name and a convenience constant.
 * @author adam.thomas
 * @author daniel.wilkin
 */
public enum DecodeField {
	
	
	AFLOAT_EVOLUTION("AfloatEvolDecode"),
	AIR_SPACE("AirSpaceDecode"),
	ALCHL_LOCN("AlchlLocnDecode"),
	AREA("AreaDecode"),
	AREA_RESP("AreaRespDecode"),
	BASE_RATE_G("BaseRategDecode"),
	BLS_ACDT_TYPE("BlsAcdtTypeDecode"),
	BLS_BODY_PART("BlsBodyPartDecode"),
	BLS_INJ_TYPE("BlsInjTypeDecode"),
	BLS_OBJ_INVLVD("BlsObjInvlvdDecode"),
	BODY_OF_WATER("BodyOfWtrDecode"),
	BODY_PART("BodyPartDecode"),
	BRANCH_OF_SERVICE("SvcDecode"),
	CA_SRC_TYPE("CaSrcTypeDecode"),
	CERT_QUAL_LICNS("CertQualLicnsDecode"),
	CIN("CinDecode"),
	CIVILIAN_SERIES("CivilianSeriesDecode"),
	COMPONENT_COMMAND("CompCmdDecode"),
	COUNTRY("CntryDecode"), 
	CRS_REQD("CrsReqdDecode"),
	DAYS_PRIOR_DEP("DaysPriorDepDecode"),
	DECOMPRESSION_LOCATION("DcmpnLocnDecode"),
	DECOMPRESSION_TABLE("DcmpnTypeDecode"),
	DIRN_TRVLD("DirnTvldDecode"),
	DISTANCE_UOM("DistnUomDecode"),
	DIVE_APPARATUS("DiveAppDecode"),
	DIVE_LOCATION_TYPE("DiveLocnTypeDecode"),
	DIVE_PLATFORM("DivePlatfDecode"),
	DIVE_PURPOSE("DivePurpDecode"),
	DIVE_PURP_SHOREGROUND("DivePurpDecode","getShoreGroundDecodeList"),
	DRUG_CLASS("DrugClassDecode"),
	DRUG_NAME("DrugNameDecode"),
	DUTY_STAT("DutyStatDecode"),
	DUTY_STATUS("DutyStatusDecode"),
	DUTY_STAT_TWO("DutyStatTwoDecode"),
	EIC("CpEicDao"),
	ENVIR("EnvirDecode"),
	EVENT_CHARZN("EventCharznDecode"),
	EVOL("EvolDecode"),
	EXERCISE("ExerciseDecode"),
	EXPLSV("ExplsvDecode"),
	EXPLSV_MISHAP_TYPE("ExplsvMishapTypeDecode"),
	EXPLSV_SYS("ExplsvSysDecode"),
	FACTOR_TYPE("FactorTypeDecode"),
	FINAL_DIAGNOSIS("DiveTypeAcdtDecode"),
	FINAL_TEMP_DIAGNOSIS("FnlTempInjDgnssDecode"),
	FIRE_CLASS("FireClassDecode"),
	FIRE_SOURCE("SrcFireDecode"),
	FIRST_IMPCT_PT("FirstImpPtDecode"),
	FLAG_CNDN("FlagCndnDecode"),
	FRTP_PHASE("FrtpPhaseDecode"),
	GAS_SOURCE("SrcGasDecode"),
	HFAC_PRECONIDTION("HfacPreconditionDecode"),
	HUMAN_FAC_ACT("HfacActDecode"),
	HUMAN_FAC_ACT_TYPE("HfacActTypeDecode"),
	INITIAL_DIAGNOSIS("DiveTypeAcdtDecode"),
	INIT_MED_TRTM("InitMedTrtmDecode"),
	INJ_CLASSN("InjClassnDecode"),
	INSTALLATION("InstallationDecode"),
	INVOLVED_STATUS("InvlvdStatDecode"),
	JOB_TITLE_GS("JobTitleGsDecode"),
	LICNS("LicnsDecode"),
	LIGHTG_ADEQ("LightgAdeqDecode"),
	MAJORCLAIMANT("MajClmDecode"),
	MECHL_FAC("MechlFacDecode"),
	MILITARY_CATEGORY("MilitaryCategoryDecode"),
	MISHAP_CLASSIFICATION("MishapClassnDecode"),
	MISHAP_TYPE("MishapTypeDecode"),
	MOS("MosDecode"),
	NALC("NalcDecode"),
	NEURO_SIGNS("NeuroSignsDecode"),
	NOT_COUNT_REASON("NotCountdRsnDecode"),
	OCCPT_LOCN("OccptLocnDecode"),
	OPERATIONAL_CONTINGENCY("OpernContngcyDecode"),
	ORDINANCE_UNIT_EVOLUION("OrdnOprnEvolutionDecode"),
	OSHA_CLASSN("OshaClassnDecode"),
	PCN("PcnDecode"),
	PER_CATG("PerCatgDecode"),
	PORT("PortDecode"),
	PRE_POST_DEP("PrePostDepDecode"),
	PROP_CATG("PropCatgDecode"),
	PROT_EQ("ProtvEqpmDecode"), 
	QUALS_CERTS_LICS("CertQualLicnsDecode"),
	RATE_RATEG("RateRategDecode"),
	RECMN_STAT("RecmnStatDecode"),
	RLTN_TO_RDWY("RltnToRdwayDecode"),
	ROAD_DSGN("RoadDesignDecode"),
	RWY_SURF_CNDN("RwySurfCndnDecode"),
	RWY_SURF("RwySurfDecode"),
	SEA_DIRECTION("SeaDirnDecode"),
	SEA_STATE("SeaStateDecode"),
	SERVICE_STATUS("SvcStatDecode"),
	SHARP_ITEM_BRAND("SharpItemBrandDecode"),
	SHARP_ITEM_TYPE("SharpItemTypeDecode"),
	SHIFT("ShiftDecode"),
	SLEEP_TYPE("SleepTypeDecode"),
	SMALL_CRAFT("SmallCraftDecode"),
	STATE("StateProvDecode"),
	SURF_CNDN("SurfCndnDecode"),
	SURF_DFCT("SurfDfectDecode"),
	SURF_TYPE("SurfTypeDecode"),
	TREATED_BY("TrtdByDecode"),
	TREATEMENT_TABLE_USED("TrtmDecode"),
	TREATMENT_OUTCOME("TrtmOcmDecode"),
	TRFC_CNDN("TrafficCndnDecode"),
	TRFC_CTRL("TrfcCtrlDecode"),
	TRFC_CTRL_FUNC("TrfcCtrlFuncDecode"),
	TRFC_VOLUME("TrfcVolDecode"),
	TRIP_PURPOSE("TripPurposeDecode"),
	TYPE_DRESS("TypeDressDecode"),
	TYPE_PER("TypePerDecode"),
	TYPE_VEHICLE("TypeVehicleDecode"),
	TYPE_VESSEL("TypeVeslDecode"),
	UIC("uics"),
	UNIT_EVOLUION("EvolDecode","getUnitEvolutionDecodeList"),
	VEH_ALERT_DUTY("VehAlertDutyDecode"),
	VEH_ALERT_LOCN("VehAlertLocnDecode"),
	VEH_BODY_TYPE("VehBodyTypeDecode"),
	VEH_COLOR("VehColorDecode"),
	VEH_DMG("VehDmgDecode"),				// FIRE
	VEH_MAKE("VehMakeDecode"),
	VEH_MOB_PROBS("VehMobProbsDecode"),		// FIRE
	VEH_ORGN("VehOrgnDecode"),
	VEH_OWNED_LEASE("VehOwnedLeaseDecode"),
	VEH_PROBS_ENR("VehProbsEnrDecode"),
	VEH_STAT("VehStatDecode"),
	VEH_TYPE("VehTypeDecode"),
	VEH_USE("VehUseDecode"),
	VISIBILITY_RESTRICTED_BY("VsblyRstrdByDecode"),
	WIND_DIRECTION("WindDirnDecode"),
	WUC("WucDecode"),
    JOB_TITLE_ENLISTED("JobTitleEnlstdDecode");	

	private static final String DEFAULT_NAMED_QUERY_SUFFUX = "getDecodeList";

	/**
	 * The class name of the decode entity bean
	 */
	private final String entityName;
	private final String namedQuery;
	
	/**
	 * Creates a new enumerated value instance.
	 * @param entityName The class name of the decode entity bean such that <code>entityName.concat(".class")</code> is
	 * the name of a legitimate class file
	 */
	private DecodeField(String entityName) {
		this(entityName, DEFAULT_NAMED_QUERY_SUFFUX);
	}
	
	private DecodeField(String entityName, String namedQuerySuffix){
		this.entityName = entityName;
		this.namedQuery = entityName+"."+namedQuerySuffix;
	}
	
	/**
	 * Obtains the entity name associated with this enumerated instance. 
	 * @return The entity name
	 */
	public String getName() {
		return entityName;
	}
	
	/**
	 * Obtains the {@link javax.persistence.NamedQuery} name on the EntityBean to be used for retrieving 
	 * the decode value
	 */
	public String getNamedQuery(){
		return this.namedQuery;
	}
	
	/**
	 * Obtains the enumerated instance associated with the specified entity name.
	 * @param entityName The name of the associated entity
	 * @return The corresponding <code>DecodeField</code> enumerated instance or
	 * <code>null</code> if a matching instance cannot be identified
	 */
	public static DecodeField byName(String entityName) {
		for(DecodeField f: values()) {
			if(f.entityName.equals(entityName)) {
				return f;
			}
		}
		return null;
	}
	
}
