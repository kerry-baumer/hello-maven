/**
 * 
 */
package org.surreal.lobster.sharedcore.nosql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kerry
 *
 */
public final class CassandraUrlProvider {
	/** The LOGGER instance */
    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraUrlProvider.class);
    
	private static final char URL_PORT_SEPARATOR = ':';
	
	public static final String KEY_WESS_PROPERTY_CASSANDRA = "nsc.cassandra.server";
	public static final String KEY_WESS_PROPERTY_CASSANDRA_PORT = "nsc.cassandra.port";
	public static final String KEY_WESS_PROPERTY_CASSANDRA_ALT = "nsc.cassandra.alternate";
	public static final String KEY_WESS_PROPERTY_CASSANDRA_ALT_PORT = "nsc.cassandra.altport";
	public static final String KEY_WESS_PROPERTY_CASSANDRA_ENABLE = "nsc.cassandra.enabled";
	
	/** The CASSANDRA_HOST_URL property */
	private static final String CASSANDRA_HOST_URL;
	/** The CASSANDRA_HOST_ALTERNATE_URL property */
	private static final String CASSANDRA_HOST_ALTERNATE_URL;
	/** The CASSANDA_HOST_ENABLED property */
	private static final boolean CASSANDA_HOST_ENABLED;
	

	static {
		StringBuilder buffer = new StringBuilder();
		buffer
				.append(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA, "localhost"))
				.append(URL_PORT_SEPARATOR)
				.append(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA_PORT, "9160"));
		CASSANDRA_HOST_URL = buffer.toString();
		
		buffer = new StringBuilder();
		buffer
				.append(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA_ALT, "localhost"))
				.append(URL_PORT_SEPARATOR)
				.append(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA_ALT_PORT, "9160"));
		CASSANDRA_HOST_ALTERNATE_URL = buffer.toString();
		CASSANDA_HOST_ENABLED = Boolean.parseBoolean(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA_ENABLE, "false"));
		LOGGER.info("UrlProvider initialized: " + getDefinedUrls());
	}
	
	private CassandraUrlProvider() {
		// no instantiation
	}
	
	/**
	 * @return
	 */
	public static String getCassandraHostUrl() {
		return CASSANDRA_HOST_URL;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getCassandraHostAlternateUrl() {
		return CASSANDRA_HOST_ALTERNATE_URL;
	}
	
	/**
     * Indicates whether the Cassandra server and data cache are enabled.
     * @return <code>True</code> if the server is enabled, <code>false</code> otherwise.
     */
    public static boolean isCassandaHostEnabled() {
    	return CASSANDA_HOST_ENABLED;
    }
    
	/**
	 * Emits the currently defined URLs to the server log.
	 * @return Descriptive string of currently configured URLs for this server instance
	 */
	public static String getDefinedUrls() {
	    final StringBuilder buffer = new StringBuilder();
	    buffer.append("UrlProvider loaded URLs as: [")
	    		.append("CASSANDRA_HOST_URL: ").append(CASSANDRA_HOST_URL).append(", ")
	    		.append("CASSANDRA_HOST_ALTERNATE_URL: ").append(CASSANDRA_HOST_ALTERNATE_URL).append(", ")
	    		.append("CASSANDA_HOST_ENABLED: ").append(CASSANDA_HOST_ENABLED).append(", ")
	    		.append("]");
		return buffer.toString();
	}

}
