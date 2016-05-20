package org.surreal.lobster.sharedcore.nosql;

public enum NoSQLKeyspace {
	COLLECTIVE("CollectiveKeyspace"),
	SPARC("SparcKeyspace"),
	PEGASUS("PegasusKeyspace");
		
	
	private String name;

	private NoSQLKeyspace(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
