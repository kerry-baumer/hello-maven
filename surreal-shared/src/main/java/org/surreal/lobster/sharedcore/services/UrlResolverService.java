package org.surreal.lobster.sharedcore.services;

import java.util.Map;


public interface UrlResolverService {

	/**
	 * Provides the fully qualified URL string to the Jasper server.
	 * @return Jasper Server  URL string
	 */
	String getJasperServerURL();
	
	/**
	 * Provides the fully qualified URL string to the Landing Page
	 * @return landing page URL string
	 */
	String getLandingPageUrl();
	
	/**
	 * Provides the fully qualified URL string to the Sparc web module
	 * @return sparc URL string
	 */
	String getSparcUrl();
	
	/**
	 * Provides the fully qualified URL string to the Afloat web module
	 * @return afloat mishap reporting URL string
	 */
	String getAfloatUrl();
	
	/**
	 * Provides the fully qualified URL string to the Afloat Initial Notification web module
	 * @return afloat initial notification URL string
	 */
	String getAfloatInitalNotificationUrl();
	
	/**
	 * Provides the fully qualified URL string to the Motor Vehicle web module
	 * @return motor vehicle URL string 
	 */
	String getMotorVehicleUrl();
	String getMotorVehicleInitialNotificationUrl();
	
	/**
	 * Provides the fully qualified URL string to the Aviation web module
	 * @return aviation URL string
	 */
	String getAviationUrl();
	String getAviationInitialNotificationUrl();
	
	/**
	 * Provides the fully qualified URL string to the subsystem picker web module.
	 * @return subsystem picker URL string
	 */
	String getSubsystemPickerUrl();
	
	/**
	 * Provides the fully qualified URL string to the Aviation web module
	 * @return reports URL string
	 */
	String getReportsUrl();
	
	/**
	 * Provides the web module name for a given URL string
	 * @param moduleName
	 * @return web module name
	 */
	String getModuleNameFor(String moduleName);
	
//	/**
//	 * Provides the fully qualified URL string to the Notification PDF Servlet 
//	 * @param htId
//	 * @return
//	 */
//	String getNotificationPdfUrl(String htId);

	/**
	 * Provides the fully qualified URL string to the Consolidated Hazard Application 
	 * @param htId
	 * @return
	 */
	String getConsolidatedHazardUrl();

	/**
	 * Provides the fully qualified URL string to the Draft PDF Servlet 
	 * @param htId
	 * @return
	 */
	String getDraftPdfUrl(String htId);
	
	/**
	 * Obtains the web accessible URL of the system hosting this application
	 * without a trailing slash ('<code>/</code>').
	 * 
	 * @return The web host address
	 */
	String getWessUrl();

	/**
	 * Obtains the fully qualify URL to the Collective application without a
	 * trailing slash ('<code>/</code>').
	 * 
	 * @return The URL to the Collective application
	 */
	String getWessCollectiveUrl();

	/**
	 * Obtains the Wess Administrator's email address.
	 * 
	 * @return The Wess Administrator's email address
	 */
	String getWessAdminEmailAddress();

	/**
	 * Obtains the fully qualified URL to the Wess Tutorial application without
	 * a trailing slash ('<code>/</code>').
	 * 
	 * @return The fully qualified URL to the Wess Tutorial application
	 */
	String getWessTutorialUrl();

	/**
	 * @return
	 */
	String getCassandraHostUrl();

	/**
	 * 
	 * @return
	 */
	String getCassandraHostAlternateUrl();

	/**
	 * Indicates whether the Cassandra server and data cache are enabled.
	 * 
	 * @return <code>True</code> if the server is enabled, <code>false</code>
	 *         otherwise.
	 */
	boolean isCassandaHostEnabled();

	/**
	 * Obtains the URL to the SPARC web service.
	 * 
	 * @return The URL for the SPARC web service or the default URL if one is
	 *         not defined.
	 */
	String getSparcWebServiceUrl();
	
	/**
	 * Obtains the URL for the Email web service.
	 * 
	 * @return the URL for the Email web service
	 */
	String getEmailWebServiceUrl();
	
	/**
	 * A list of arguments to pass into the format string obtained
	 * from the configuration XML. <b>It is the callers responsibility
	 * to provide the correct number and sequence of arguments.</b>
	 * @param urlId - the Identifier of the URL to resolve.
	 * @param args - Arguments to pass into @link {@link String#format(String, Object...)}
	 * @return a resolved URL or null if the urlId cannot be found in the
	 * configuration.
	 */
	String resolveUrl(String urlId, String ...args);
	
	Map<String, String> getUrlMappings(); 
	
	/**
	 * Obtains a URL for viewing DJRS dive summary report from diveLogId.
	 * @return the URL for the dive summary report based on the diveLogId
	 */
	String getDiveSummaryReportUrl();

	String getDiveSummaryReportUrl(String diveLogId);
}
