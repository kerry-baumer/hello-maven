package org.surreal.lobster.sharedcore.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.surreal.lobster.sharedcore.constants.UrlToken;
import org.surreal.lobster.sharedcore.util.StringUtil;

/**
 * @author kerry.baumer
 *
 */
/**
 * @author kerry.baumer
 *
 */
public class UrlResolverServiceImpl implements UrlResolverService {

	private static Logger LOGGER = LoggerFactory.getLogger(UrlResolverServiceImpl.class);

	private static final String CONFIG_XML_NAME="/standalone/configuration/UrlServiceProvider.xml";
	private static final String CONFIG_XML_PATH;

	private static final String URL_HOST_SEPARATOR = "://";
	private static final char URL_PORT_SEPARATOR = ':';
	private static final String URL_SEPARATOR = "/";
	private static final String WESSADMIN_EMAIL_ADDR = "wess.admin.email.address";
	private static final String DEFAULT_WESSADMIN_EMAIL_ADDR = "safe-wessadmin@navy.mil";

	private static final String KEY_WESS_PROPERTY_HOSTNAME = "wess.niprHostName";
	private static final String KEY_WESS_PROPERTY_JBOSS5_HOSTURL = "WESS_JBOSS5_HOST_URL";
	private static final String KEY_WESS_PROPERTY_HOSTPORT = "wess.niprHostPort";
	private static final String KEY_WESS_PROPERTY_SSL = "wess.isSslConfigured";
	private static final String KEY_WESS_PROPERTY_CASSANDRA = "nsc.cassandra.server";
	private static final String KEY_WESS_PROPERTY_CASSANDRA_PORT = "nsc.cassandra.port";
	private static final String KEY_WESS_PROPERTY_CASSANDRA_ALT = "nsc.cassandra.alternate";
	private static final String KEY_WESS_PROPERTY_CASSANDRA_ALT_PORT = "nsc.cassandra.altport";
	private static final String KEY_WESS_PROPERTY_CASSANDRA_ENABLE = "nsc.cassandra.enabled";
	private static final String KEY_PROPERTY_SPARC_WEBSERVICE_HOST = "nsc.sparc-ws.host";
	private static final String KEY_PROPERTY_SPARC_WEBSERVICE_PORT = "nsc.sparc-ws.port";
	private static final String KEY_PROPERTY_SPARC_WEBSERVICE_PROTOCOL = "nsc.sparc-ws.protocol";
	private static final String KEY_PROPERTY_EMAIL_SERVICE_URL = "EMAIL_SERVICE_URL";
	private static final String KEY_DJRS_DIVE_SUMMARY_URL ="DJRS_DIVE_SUMMARY_URL";
	private static final String KEY_JASPER_SERVER_URL = "JASPER_SERVER_PRO";

	private static final String DEFAULT_WESS_PROTOCOL = "http";
	private static final String DEFAULT_WESS_USES_SSL = "true";
	private static final String DEFAULT_WESS_PORT = "8443";

	private static final String WESS_APPLICATION_NAME = "/collective";

	private static final String WESS_HOST_URL = "WESS_HOST_URL";
	private static final String WESS_COLLECTIVE_URL = "WESS_COLLECTIVE_URL";
	private static final String WESS_TUTORIAL_URL = "http://www.public.navy.mil/navsafecen/Pages/wess/user_guides.aspx";
	private static final String CASSANDRA_HOST_URL = "CASSANDRA_HOST_URL";
	private static final String CASSANDRA_HOST_ALTERNATE_URL = "CASSANDRA_HOST_ALTERNATE_URL";
	private static final String CASSANDA_HOST_ENABLED = "CASSANDA_HOST_ENABLED";
	private static final String WESS_SPARC_WEBSERVICE_URL = "WESS_SPARC_WEBSERVICE_URL";
	private static final String SUBSYSTEM_PICKER_URL = "SUBSYSTEM_PICKER_URL";

	private static final String MOD_NAME_SPARC = "sparc";
	private static final String MOD_NAME_PEGASUS = "pegasus";
	private static final String MOD_NAME_PEGASUS_WEB = "pegasus-web";
	private static final String MOD_NAME_OUTPOST = "outpost-web";
	private static final String MOD_NAME_HAZARD = "emissary";
	private static final String MOD_NAME_OUTPOST_HATCH_WEB = "outpost-hatch-web";
	private static final String MOD_NAME_MVRS = "mvrs-web";
	private static final String MOD_NAME_MVRS_IN = "mvrs-hatch-web";
	//	private static final String MOD_NAME_PEGASUS_TOOLS = "pegasus-tools";

	private static final String ENTRY_POINT_SPARC = "riker";
	private static final String ENTRY_POINT_PEGASUS = "initialNotifications";
//	private static final String ENTRY_POINT_PEGASUS_WEB = "pressman";
	private static final String EVENT_ID = "eventId";
//	private static final String NTFCN_ID = "ntfcnId";
	private static final String PDF_PROVIDER_SERVLET = "pdfProviderServlet";
	private static final String INTL_NTFCN_PDF_URL = "standalonePdfProviderServlet";
//	private static final String INTL_NTFCN_PDF_POST_URL_SUFFIX = "rest/pdfprovider/viewpdf/json";
//	private static final String INTL_NTFCN_PDF_GET_URL_SUFFIX = "rest/pdfprovider/initial/";
	
	
	private static Properties props = new Properties();

	static {
		final String jbossHome = System.getProperty("jboss.home.dir").concat(CONFIG_XML_NAME);
		CONFIG_XML_PATH = System.getProperty("configXmlPath", jbossHome);
		LOGGER.debug("Configured CONFIG_XML_PATH: " + CONFIG_XML_PATH);
		loadProperties();
	}

	public UrlResolverServiceImpl() {
//		loadProperties();
	}

	//    @PostConstruct
	//    public void onPostConstruct() {
	//    	loadProperties();
	//    }
	private static void loadProperties() {
		FileInputStream fis = null;
		final Properties tempProps = new Properties();
		boolean invalidProperties = true;
		tempProps.putAll(props);			// Save current properties
		try {
			props.clear();					// remove all properties
			fis = new FileInputStream(CONFIG_XML_PATH);
			props.loadFromXML(fis);
			doPostProcessProperties();
			invalidProperties = false;
		} catch (final FileNotFoundException e) {
			LOGGER.error("FileNotFoundException loading properties file: " + e.getMessage());
		} catch (final InvalidPropertiesFormatException e) {
			LOGGER.error("InvalidPropertiesFormatException loading properties file: " + e.getMessage());
		} catch (final IOException e) {
			LOGGER.error("IOException loading properties file: " + e.getMessage());
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (final IOException ignore) {
				// nothing we can do there.
			}
			if(invalidProperties) {
				props.clear();
				props.putAll(tempProps);		// restore original values
			}
		}
	}

	private static void doPostProcessProperties() {
		StringBuilder buffer = new StringBuilder();
		final String hostname = props.getProperty(KEY_WESS_PROPERTY_HOSTNAME, 
			System.getProperty("jboss.bind.address"));
		final int hostport = Integer.parseInt(props.getProperty(KEY_WESS_PROPERTY_HOSTPORT, String.valueOf(DEFAULT_WESS_PORT)));
		final boolean isSslConfigured = Boolean.parseBoolean(System.getProperty(KEY_WESS_PROPERTY_SSL, DEFAULT_WESS_USES_SSL));
		final boolean portCheck = hostport == 80 || (hostport == 443 && isSslConfigured);
		buffer.append(DEFAULT_WESS_PROTOCOL)
		.append(isSslConfigured ? "s" : "")
		.append(URL_HOST_SEPARATOR)
		.append(hostname)
		.append(portCheck ? "" : URL_PORT_SEPARATOR)
		.append(portCheck ? "" : hostport);
		props.put(WESS_HOST_URL, buffer.toString());
		buffer.append(WESS_APPLICATION_NAME);
		props.put(WESS_COLLECTIVE_URL, buffer.toString());

		buffer = new StringBuilder();
		buffer.append(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA, "localhost"))
		.append(URL_PORT_SEPARATOR)
		.append(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA_PORT, "9160"));
		props.put(CASSANDRA_HOST_URL, buffer.toString());

		buffer = new StringBuilder();
		buffer.append(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA_ALT, "localhost"))
		.append(URL_PORT_SEPARATOR)
		.append(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA_ALT_PORT, "9160"));
		props.put(CASSANDRA_HOST_ALTERNATE_URL, buffer.toString());
		props.put(CASSANDA_HOST_ENABLED, Boolean.parseBoolean(System.getProperty(KEY_WESS_PROPERTY_CASSANDRA_ENABLE, "false")));

		buffer = new StringBuilder();
		buffer.append(System.getProperty(KEY_PROPERTY_SPARC_WEBSERVICE_PROTOCOL, "http")).append(URL_HOST_SEPARATOR)
		.append(System.getProperty(KEY_PROPERTY_SPARC_WEBSERVICE_HOST, "localhost")).append(URL_PORT_SEPARATOR)
		.append(System.getProperty(KEY_PROPERTY_SPARC_WEBSERVICE_PORT, "8080"));
		props.put(WESS_SPARC_WEBSERVICE_URL, buffer.toString());

		final boolean missingJBoss5HostUrl = StringUtil.isBlank((String)props.get(KEY_WESS_PROPERTY_JBOSS5_HOSTURL));
		if (missingJBoss5HostUrl ) {
			props.put(KEY_WESS_PROPERTY_JBOSS5_HOSTURL, "https://localhost");
		}
		final boolean missingSubsystemPickerUrl = StringUtil.isBlank((String)props.get(SUBSYSTEM_PICKER_URL));
		if (missingSubsystemPickerUrl) {
			props.put(SUBSYSTEM_PICKER_URL, "wess/model.do?model=samReturnHome&amp;newFlow=true");
		}

		//
		//	resolve macros in raw properties
		Enumeration<Object> iter = props.keys();
		String prop, value;
		try {
			while(iter.hasMoreElements()) {
				prop = (String) iter.nextElement();
				value = props.getProperty(prop);
				if(StringUtils.isNotBlank(value)) {
					LOGGER.info(String.format("\t%s: %s", prop, value));
					while(value.indexOf('$') >= 0) {
						value = resolveMacros(value);
						LOGGER.info(String.format("\t%s's new value %s", prop, value));
					}
					props.setProperty(prop, value);
				}
			}
		} catch (Throwable whatTheHellHappened) {
			whatTheHellHappened.printStackTrace();
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.info("UrlResolverService initialized: ");
			for(final Object key : props.keySet()) {
				LOGGER.info(String.format("\t%s: %s",
						key, props.getProperty((String)key)));
			}
		}
	}

	private String deriveHost(final String url) {
		return url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
	}

	public String getIntlNtfcnPdfUrl() {
		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_PEGASUS + "/" + INTL_NTFCN_PDF_URL;
	}

	/**
	 * Exposes the URL for the jasper-pro server
	 */
	public String getJasperServerURL() {
		return props.getProperty(KEY_JASPER_SERVER_URL,
				"/nsc-static-web/page-configuration-error.html");
	}

	/**
	 * Exposes the root JBoss 5 URL.  I'm implementing this here and not in the 
	 * interface because I don't know if any other projects are implementing
	 * the interface and this getter really only needs to be available for 
	 * the url resolver service in shared-core
	 * 
	 * @return the root JBoss 5 URL
	 */
	public String getJboss5url() {
		return props.getProperty(KEY_WESS_PROPERTY_JBOSS5_HOSTURL);
	}
	
	public String getLandingPageUrl() {
		return props.getProperty(WESS_HOST_URL) + "/" + props.getProperty("LANDING_PAGE");
	}

	public String getShoreGroundUrl() {
		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_OUTPOST + "/#/";
	}
	
	public String getShoreGroundInitialNotificationUrl() {
		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_OUTPOST_HATCH_WEB + "/#/";
	}
	
	public String getSparcUrl() {
		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_SPARC + "/" + ENTRY_POINT_SPARC;
	}

	public String getAfloatUrl() {
		return props.getProperty(WESS_HOST_URL) + "/afloat-web/#/";
	}

	public String getAfloatInitalNotificationUrl() {
		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_PEGASUS + "/" + ENTRY_POINT_PEGASUS;
	}

	public String getMotorVehicleUrl() {
		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_MVRS + "/#/"; 
	}

	public String getMotorVehicleInitialNotificationUrl() {
		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_MVRS_IN + "/#/"; 
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.services.UrlResolverService#getAviationUrl()
	 */
	public String getAviationUrl() {
		return props.getProperty(KEY_WESS_PROPERTY_JBOSS5_HOSTURL) + "/" + "collective?fromSparc&run=avMishap";
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.services.UrlResolverService#getAviationInitialNotificationUrl()
	 */
	public String getAviationInitialNotificationUrl() {
		return props.getProperty(KEY_WESS_PROPERTY_JBOSS5_HOSTURL) + "/" + "collective?fromSparc&run=avIntlNtfcn";
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.services.UrlResolverService#getSubsystemPickerUrl()
	 */
	public String getSubsystemPickerUrl() {
		return props.getProperty(SUBSYSTEM_PICKER_URL);
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.services.UrlResolverService#getReportsUrl()
	 */
	public String getReportsUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.services.UrlResolverService#getModuleNameFor(java.lang.String)
	 */
	public String getModuleNameFor(final String url) {
		String result = null;
		final String path = url.replace(deriveHost(url) + '/', "");
		if (path.startsWith(MOD_NAME_PEGASUS_WEB)) {
			result = MOD_NAME_PEGASUS_WEB;
		} else if (path.startsWith(MOD_NAME_PEGASUS)) {
			result = MOD_NAME_PEGASUS;
		} else if (path.startsWith(MOD_NAME_SPARC)) {
			result = MOD_NAME_SPARC;
		} else if (path.startsWith(MOD_NAME_HAZARD)) {
			result = MOD_NAME_HAZARD;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.services.UrlResolverService#getConsolidatedHazardUrl()
	 */
	public String getConsolidatedHazardUrl() {
		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_HAZARD + "/#/";
	}

	/**
	 * @return
	 */
	public String getDjrsUrl() {
		return props.getProperty(KEY_WESS_PROPERTY_JBOSS5_HOSTURL) + "/wess/wess/djrs/determineHome.do";
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.services.UrlResolverService#getDraftPdfUrl(java.lang.String)
	 */
	public String getDraftPdfUrl(String htId) {
		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_PEGASUS + "/" + PDF_PROVIDER_SERVLET + "?" + EVENT_ID + "=" + htId;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.services.UrlResolverService#getWessUrl()
	 */
	public String getWessUrl() {
		return props.getProperty(WESS_HOST_URL);
	}

	public String getWessCollectiveUrl() {
		return getJboss5url() + "/collective";
	}

	public String getWessAdminEmailAddress() {
		return props.getProperty(WESSADMIN_EMAIL_ADDR, DEFAULT_WESSADMIN_EMAIL_ADDR);
	}

	public String getWessTutorialUrl() {
		return props.getProperty(WESS_TUTORIAL_URL);
	}

	@Deprecated
	public String getCassandraHostUrl() {
		return props.getProperty(CASSANDRA_HOST_URL);
	}

	@Deprecated
	public String getCassandraHostAlternateUrl() {
		return props.getProperty(CASSANDRA_HOST_ALTERNATE_URL, props.getProperty(CASSANDRA_HOST_URL));
	}

	public boolean isCassandaHostEnabled() {
		return (Boolean) props.get(CASSANDA_HOST_ENABLED);
	}

	public String getSparcWebServiceUrl() {
		return props.getProperty(WESS_SPARC_WEBSERVICE_URL);
	}

	public String getEmailWebServiceUrl() {
		return props.getProperty(KEY_PROPERTY_EMAIL_SERVICE_URL);
		// i don't understand how these other ones are getting populated
		//		return props.getProperty(WESS_HOST_URL) + "/" + MOD_NAME_PEGASUS_TOOLS + "/rest/email/send";
	}

	private static String resolveMacros(final String format) {
		final StringBuilder sb = new StringBuilder();
		final String[] tokens = format.split(URL_SEPARATOR);
		for(int i = 0; i < tokens.length; i++) {
			final String token = tokens[i];
			if(token.contains("$")) {
				final String key = token.replaceAll("[\n|\t|$|{|}]", "");
				final String replacement = props.getProperty(key);
				sb.append((replacement == null) ? "" : replacement);
			} else {
				sb.append(token);
			}
			if((tokens.length - i) > 1) {
				sb.append(URL_SEPARATOR);
				LOGGER.debug(sb.toString());
			}
		}
		return sb.toString();
	}

	public String resolveUrl(String urlId, String... args) {
		String resolved = urlId;
		if(urlId != null && urlId.length() > 0) {
			if(urlId.startsWith("@")) {
				String format = props.getProperty(urlId.replaceAll("@", ""));
				if(StringUtils.isBlank(format)) {
					format = urlId.replaceAll("@", "");
				}
				final String resolver = resolveMacros(format);
				resolved = String.format(resolver, (Object[])args);
			} else {
				resolved = props.getProperty(urlId, resolved);
			}
		}
		return resolved;
	}

	private String getUrlForToken(final UrlToken token) {
		switch(token) {
		case AFLOAT_INITIAL_NOTIFICATION:
			return getAfloatInitalNotificationUrl();
		case AFLOAT:
			return getAfloatUrl();
		case SHORE_GROUND_INITIAL_NOTIFICATION:
			return getShoreGroundInitialNotificationUrl();
		case SHORE_GROUND:
			return getShoreGroundUrl();
		case LEGACY_REPORT:
			return getJboss5url() + "/wess/model.do?model=upfrontQuestions&newFlow=true";
		case LEGACY_INITIAL_NOTIFICATION:
			return  getJboss5url() + "/wess/model.do?model=init_SubmitNotification&newFlow=true";
		case LEGACY_ESAMS_IMPORT:
			return getJboss5url() + "/wess/model.do?model=ConsDSImport&newFlow=true";
		case WESS:
			return getWessUrl();
		case WESS_COLLECTIVE:
			return getWessCollectiveUrl();
		case DRAFT_PDF_URL:
			return getDraftPdfUrl("%s");
//		case NTFCN_PDF_URL:
//			return getNotificationPdfUrl("%s");
		case INTL_NTFCN_PDF_URL:
			return getIntlNtfcnPdfUrl();
		case LANDING_PAGE:
			return getLandingPageUrl();
		case SPARC:
			return getSparcUrl();
		case EMAIL_SERVICE_URL:
			return getEmailWebServiceUrl();
		case AVIATION:
			return getAviationUrl();
		case AVIATION_INITIAL_NOTIFICATION:
			return getAviationInitialNotificationUrl();
		case MVRS:
			return getMotorVehicleUrl();
		case MVRS_INITIAL_NOTIFICATION:
			return getMotorVehicleInitialNotificationUrl();
		case SUBSYSTEM_PICKER_URL:
			return getSubsystemPickerUrl();
		case HAZARD_WEB:
			return getConsolidatedHazardUrl();
		case DJRS:
			return getDjrsUrl();
		case DJRS_DIVE_SUMMARY_URL:
			return getDiveSummaryReportUrl();
		case WESS_JBOSS5_HOST_URL:
			return getJboss5url();
		case JASPER:
			return getJasperServerURL();
		default:
			throw new IllegalArgumentException("Undefined token");
		}
	}

	public Map<String, String> getUrlMappings() {
		final Map<String, String> answer = new HashMap<String, String>();
		for(final UrlToken token : UrlToken.values()) {
			answer.put(token.toString(), getUrlForToken(token));
		}
		return answer;
	}

	public String getDiveSummaryReportUrl() {
		return props.getProperty(KEY_DJRS_DIVE_SUMMARY_URL);
	}

	public String getDiveSummaryReportUrl(String diveLogId) {
		return String.format(getDiveSummaryReportUrl()+"?diveLogId=%s",diveLogId);
	}

}
