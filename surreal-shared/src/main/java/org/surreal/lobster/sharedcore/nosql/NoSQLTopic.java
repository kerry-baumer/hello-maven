package org.surreal.lobster.sharedcore.nosql;

public enum NoSQLTopic {
	EVENT_JSON("EventJson"),
	USER_WORKSPACE("UserWorkspace"),
	DECODE_ITEMS("DecodeItems"),
	DECODE_CACHE("DecodeCache"),
	DECODE_MAP("DecodeMap"),
	RESOLVER_DECODE_CACHE("ResolverDecodeCache"),
	LEVEL_CODED_CACHE("LevelCodedCache"),
	MISHAP_TYPES("MishapTypeCache"),
	USER_ACCOUNTS("AccountCache"),
	TEST_NRML("TestNormal"),
	TEST_SUPER("TestSuper"),
	USERS_BY_PERMISSION("UsersByPermission"),
	USERS_BY_ROLE("UsersByRole"),
	ENDORSABLE_UICS("EndorsableUics"),
	SA_USERS("SAUsers"),
	DA_USERS("DAUsers"),
	WA_USERS("WAUsers"),
	USER_BY_EMAIL("UserByEmail"),
	SHARED_DRAFTER_NTFCN("SharedDrafterNtfcn"),
	SHARED_DRAFT_NTFCN_DIRTY("SharedDraftNtfcnDirty"),
	PDF_DATA_CACHE("PdfDataCache");
	
	private String name;

	private NoSQLTopic(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
