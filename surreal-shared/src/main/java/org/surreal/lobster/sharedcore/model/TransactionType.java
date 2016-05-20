/**
 * 
 */
package org.surreal.lobster.sharedcore.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The transaction type performed during an update to the database.
 * @author daniel.wilkin
 * @author kerry.baumer
 */
public enum TransactionType {
	UPDATE("U"),
	APPEND("A"),
	DELETE("D"),
	INSERT("I"),
	REPLACE("R"),
	WORKING("W"),
	VERIFIED("V"),
	QUALIFIED("Q"),
	CHANGE("C");
	private static Map<String, TransactionType> lookup = new HashMap<String, TransactionType>();

	/*
	 * The transaction type is persisted using a magic string value, provide
	 * an appropriate conversion here.
	 */
	static {
		lookup.put(UPDATE.toString(), UPDATE);
		lookup.put(APPEND.toString(), APPEND);
		lookup.put(DELETE.toString(), DELETE);
		lookup.put(REPLACE.toString(), REPLACE);
		lookup.put(WORKING.toString(), WORKING);
		lookup.put(CHANGE.toString(), CHANGE);
		lookup.put(INSERT.toString(), INSERT);
		lookup.put(VERIFIED.toString(), VERIFIED);
		lookup.put(QUALIFIED.toString(), QUALIFIED);
		
		if (TransactionType.values().length != lookup.size()) {
			throw new IllegalStateException("Static initialization for " + TransactionType.class.getName() +
					" failed to map all enumerated values.");
		}
	}
	
	/** The value property */
	private String value;
	
	private TransactionType(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {		
		return value;
	}
	
	/**
	 * Obtains the corresponding enumerated value for the specified string.
	 * @param value The string representation of the enumerated value
	 * @return The enumerated value
	 * @throws IllegalArgumentException If no matching enumerated value exists.
	 */
	public static TransactionType fromString(String value) {
		TransactionType e = lookup.get(value.trim().toUpperCase());
		if (e == null) {
			throw new IllegalArgumentException("Unrecognized transaction type: " + value);
		}
		return e;
	}

}
