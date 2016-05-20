package org.surreal.lobster.sharedcore.provider;


/**
 * @author christopher.d.grimes
 *
 */
public interface UrlServiceProvider extends ServiceProvider {

	/**
	 * Provides the fully qualified URL string to the Consolidated Hazard Application 
	 * @param htId
	 * @return
	 */
	void getConsolidatedHazardUrl(ServiceCallback<String> callback);
	
	/**
	 * Provides the fully qualified URL string to the Sparc web module
	 * @param callback TODO
	 */
	void getSparcUrl(ServiceCallback<String> callback);
	/**
	 * Provides the fully qualified URL string to the Afloat web module
	 * @param callback TODO
	 */
	void getAfloatUrl(ServiceCallback<String> callback);
	/**
	 * Provides the fully qualified URL string to the Afloat Initial Notification web module
	 * @param callback TODO
	 */
	void getAfloatInitalNotificationUrl(ServiceCallback<String> callback);
	void getShoreGroundUrl(ServiceCallback<String> callback);
	void getShoreGroundInitialNotificationUrl(ServiceCallback<String> callback);
	/**
	 * Provides the fully qualified URL string to the Motor Vehicle web module
	 * @param callback TODO
	 */
	void getMotorVehicleUrl(ServiceCallback<String> callback);
	void getMotorVehicleNotificationUrl(ServiceCallback<String> callback);
	/**
	 * Provides the fully qualified URL string to the Aviation web module
	 * @param callback TODO
	 */
	void getAviationUrl(ServiceCallback<String> callback);
	void getAviationNotificationUrl(ServiceCallback<String> callback);
	/**
	 * Provides the fully qualified URL string to the Aviation web module
	 * @return reports URL string
	 */
	String getReportsUrl();
	
	void getNotificationPdfPostUrl(ServiceCallback<String> callback);
	void getNotificationPdfGetUrl(String pdfId, ServiceCallback<String> callback);
	
//	/**
//	 * Provides the fully qualified URL string to the Notification PDF Servlet 
//	 * @param htId
//	 * @return
//	 */
//	String getNotificationPdfUrl(String htId);

	/**
	 * Provides the fully qualified URL string to the Draft PDF Servlet 
	 * @param htId
	 * @return
	 */
	String getDraftPdfUrl(String htId);
	
	/**
	 * @return
	 */
	boolean isCurrentLocationSparc();
	boolean isCurrentLocationAfloat();
	boolean isCurrentLocationInitialNotification();
	void getLegacySubsystemPickerUrl(ServiceCallback<String> callback);
	void getLegacyMishapReportUrl(ServiceCallback<String> callback);
	void getJasperUrl(ServiceCallback<String> callback);
	void getLandingPageUrl(ServiceCallback<String> callback);
	void getWessCollectiveUrl(ServiceCallback<String> callback);
}
