/**
 * Mar 2, 2012 4:07:39 PM
 */
package org.surreal.lobster.sharedcore.databroker;

/**
 * Provides GUIDs or pseudo-GUIDs (guids sufficiently unique) to be used for data
 * identities.
 * @author daniel.wilkin
 */
public interface GuidGenerator {

	/**
	 * Generates a new GUID.
	 * @return A new GUID
	 */
	public String generate();

}
