/**
 *
 */
package org.surreal.lobster.sharedcore.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.surreal.lobster.sharedcore.nosql.CassandraConnection;
import org.surreal.lobster.sharedcore.nosql.NoSQLTopic;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @author kerry.baumer
 * @author daniel.wilkin
 * @deprecated
 */
@Deprecated
public class UserAccountCacheEntryLocator implements IUserAccountCacheEntryLocator {
	/** The LOGGER instance for this class */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountCacheEntryLocator.class);

	private static final int CONNECTION_RESPONSE_NO_CONTENT = 204;
	private static final int CONNECTION_RESPONSE_SUCCESS = 200;
	private static final String URL_HOST_SEPARATOR = "://";
	private static final char URL_PORT_SEPARATOR = ':';
	public static final String KEYSPACE_NAME_USER_MANAGEMENT = "UserManagement";
	private static final String KEY_PROPERTY_SPARC_WEBSERVICE_PROTOCOL = "nsc.sparc-ws.protocol";
	private static final String KEY_PROPERTY_SPARC_WEBSERVICE_HOST = "nsc.sparc-ws.host";
	private static final String KEY_PROPERTY_SPARC_WEBSERVICE_PORT = "nsc.sparc-ws.port";


	public UserAccountCacheEntryLocator() {
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.user.IUserAccountCacheEntryLocator#findUser(java.lang.String)
	 */
	@Override
	public UserAccount findUser(final String ediPi) {
		UserAccount ua = null;
		if ((ua = accountLookup(ediPi)) == null) {
			// push data into cache
			final String buffer = new StringBuilder()
			.append(System.getProperty(KEY_PROPERTY_SPARC_WEBSERVICE_PROTOCOL, "http")).append(URL_HOST_SEPARATOR)
			.append(System.getProperty(KEY_PROPERTY_SPARC_WEBSERVICE_HOST, "localhost")).append(URL_PORT_SEPARATOR)
			.append(System.getProperty(KEY_PROPERTY_SPARC_WEBSERVICE_PORT, "8080")).toString();
			final String url = String.format("%s/pegasus-web/rest/update/user/%s", buffer, ediPi);
			final HttpGet getter = new HttpGet(url);
			try {
				final HttpResponse resp = new DefaultHttpClient().execute(getter);
				final int code = resp.getStatusLine().getStatusCode();
				final String reason = resp.getStatusLine().getReasonPhrase();
				EntityUtils.consume(resp.getEntity());
				if (code == CONNECTION_RESPONSE_SUCCESS || code == CONNECTION_RESPONSE_NO_CONTENT) {
					LOGGER.debug("Notifying UserAccountCache succeeded.");
				}
				else {
					LOGGER.error(String.format("Failed to contact remote UserAccountCache update service: [%s {%d}]", reason, code));
				}
//			} catch (MalformedURLException e) {
//				LOGGER.error(String.format("Failed to make connection to %s: [%s]", url, e.getMessage()));
			} catch (final IOException e) {
				LOGGER.error(String.format("Request/response failed to %s: [%s]", url, e.getMessage()));
			} catch (final RuntimeException e) {
				LOGGER.error(String.format("Unknown error occurred attempting to contact %s: [%s]", url, e.getMessage()));
			}
			finally {
				getter.releaseConnection();
			}

			// lookup account again
			if ((ua = accountLookup(ediPi)) == null) {
				throw new IllegalArgumentException(String.format("Unknown user id (ediPi:%s)", ediPi));
			}
		}
		return ua;
	}

	@Override
	public UserAccount findUserNullOk(final String ediPi) {
		return accountLookup(ediPi);
	}

	private UserAccount accountLookup(final String ediPi) {
		UserAccountClientBean clone = null;
		final CassandraConnection cc = new CassandraConnection(KEYSPACE_NAME_USER_MANAGEMENT);
		final String json = cc.get(NoSQLTopic.USER_ACCOUNTS, ediPi);
		if(!StringUtils.isBlank(json)) {
			clone = new Gson().fromJson(json, UserAccountClientBean.class);
		} else {
			LOGGER.info(String.format("[user-cache-miss] eidPi:%s", ediPi));
			return null;
		}
		LOGGER.info(String.format("[user-cache-hit] %s", clone.toString()));
		return clone;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.user.IUserAccountCacheEntryLocator#findUserFromEmail(java.lang.String)
	 */
	@Override
	public UserAccount findUserFromEmail(final String email) {
		UserAccountClientBean clone = null;
		final CassandraConnection cc = new CassandraConnection(KEYSPACE_NAME_USER_MANAGEMENT);
		final String json = cc.get(NoSQLTopic.USER_BY_EMAIL, email);
		if(!StringUtils.isBlank(json)) {
			final Gson gson = new Gson();
			clone = gson.fromJson(json, UserAccountClientBean.class);
		} else {
			LOGGER.error(String.format("[user-cache-miss] No known user with email address '%s'", email));
		}
		return clone;
	}

	@Override
	public List<UserAccount> findSafetyAuthorities(final String uic) {
		final CassandraConnection cassandraConnection = new CassandraConnection(KEYSPACE_NAME_USER_MANAGEMENT);
		final List<Map<String, Object>> superSlice = cassandraConnection.getSuperSlice(NoSQLTopic.SA_USERS, uic);
		final List<UserAccount> sas = new ArrayList<UserAccount>();
		for (final Map<String, Object> row : superSlice) {
			final String ediPi = (String) row.get("ediPi");
			final String email = (String) row.get("email");
			/*
			 * TODO:(SEAN) - for now, in order to not have to put sam-api.jar in
			 * anything that uses this method - we have only pulled back the uic,
			 * edipi, and email address for these user accounts - it would be
			 * better to return the entire user account instead of just these fields
			 */
			final UserAccount userAccount = new UserAccountClientBean();
			userAccount.setUic(uic);
			userAccount.setEdiPi(ediPi);
			userAccount.setEmail(email);
			sas.add(userAccount);
			LOGGER.info("[user-cache-hit] found sa user: " + userAccount.getEdiPi() + ", " + userAccount.getEmail());
			//			sas.add(new Gson().fromJson((String) row.get("accountInfo"), UserAccountClientBean.class));
		}
		LOGGER.info("[user-cache-hit] found " + sas.size() + " total sa users for uic " + uic);
		return sas;
	}

	@Override
	public List<UserAccount> getUserAccountsInPermissionAndUic(final String permission, final String uic) {
		final List<UserAccount> userAccounts = new ArrayList<UserAccount>();
		final List<Map<String, Object>> rows = getNotifiableUsersByPermission(permission);
		for (final Map<String, Object> row : rows) {
			if (uic.equals(row.get("uic"))) {
				/*
				 * TODO:(SEAN) - for now, in order to not have sto put sam-api.jar in
				 * anything that uses this method - we have only pulled back the uic,
				 * edipi, and email address for these user accounts - it would be
				 * better to return the entire user account instead of just these field
				 */
				final UserAccount userAccount = new UserAccountClientBean();
				userAccount.setUic(uic);
				userAccount.setEdiPi((String) row.get("ediPi"));
				userAccount.setEmail((String) row.get("email"));
				userAccounts.add(userAccount);
			}
		}
		LOGGER.info("[user-cache-hit] found " + userAccounts.size() + " users with permission " + permission + " for uic " + uic);
		return userAccounts;
	}

		@Override
		public List<String> getNotifiableEdiPiInRole(final String role) {
			final List<String> result = new ArrayList<String>();
			final List<Map<String, Object>> rows = getNotifiableUsers(role);
			for(final Map<String, Object> row : rows) {
				result.add((String) row.get("ediPi"));
			}
			return result;
		}

		@Override
		public List<String> getNotifiableEmailInRole(final String role) {
			final List<String> result = new ArrayList<String>();
			final List<Map<String, Object>> rows = getNotifiableUsers(role);
			for(final Map<String, Object> row : rows) {
				result.add((String) row.get("email"));
			}
			return result;
		}

		@Override
		public List<String> getNotifiableEdiPiInRoleAndUic(final String role, final String uic) {
			final List<String> result = new ArrayList<String>();
			final List<Map<String, Object>> rows = getNotifiableUsers(role);
			for(final Map<String, Object> row : rows) {
				if(uic.equals(row.get("uic"))) {
					result.add((String) row.get("ediPi"));
				}
			}
			return result;
		}

		@Override
		public List<String> getNotifiableEmailInRoleAndUic(final String role, final String uic) {
			final List<String> result = new ArrayList<String>();
			final List<Map<String, Object>> rows = getNotifiableUsers(role);
			for(final Map<String, Object> row : rows) {
				if(uic.equals(row.get("uic"))) {
					result.add((String) row.get("email"));
				}
			}
			return result;
		}

	@Override
	public List<String> getNotifiableEmailInPermission(final String permission) {
		throw new UnsupportedOperationException("NEW METHOD IMPLEMENTED IN LANTARU ONLY");
	}

	@Override
	public List<String> getNotifiableEdiPiInPermissionAndUic(final String permission, final String uic) {
		throw new UnsupportedOperationException("NEW METHOD IMPLEMENTED IN LANTARU ONLY");
	}
	
	@Override
	public List<String> getNotifiableEdiPiInPermission(String permission) {
		throw new UnsupportedOperationException("NEW METHOD IMPLEMENTED IN LANTARU ONLY");
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.user.IUserAccountCacheEntryLocator#getNotifiableEmailInPermissionAndUic(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getNotifiableEmailInPermissionAndUic(final String permission,
			final String uic) {
		final List<String> result = new ArrayList<String>();
		final List<Map<String, Object>> rows = getNotifiableUsersByPermission(permission);
		for(final Map<String, Object> row : rows) {
			if(uic.equals(row.get("uic"))) {
				result.add((String) row.get("email"));
			}
		}
		return result;
	}

	private List<Map<String, Object>> getNotifiableUsers(final String role) {
		final CassandraConnection cc = new CassandraConnection(KEYSPACE_NAME_USER_MANAGEMENT);
		return cc.getSuperSlice(NoSQLTopic.USERS_BY_ROLE, role.toUpperCase());
	}

	private List<Map<String, Object>> getNotifiableUsersByPermission(final String permission) {
		final CassandraConnection cc = new CassandraConnection(KEYSPACE_NAME_USER_MANAGEMENT);
		return cc.getSuperSlice(NoSQLTopic.USERS_BY_PERMISSION, permission.toUpperCase());
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.user.IUserAccountCacheEntryLocator#getUserPermissions(java.lang.String)
	 */
	@Override
	public Set<String> getUserPermissions(final String ediPi) {
		final Set<String> result = new HashSet<String>();
		final CassandraConnection cc = new CassandraConnection(KEYSPACE_NAME_USER_MANAGEMENT);
		final String json = cc.get(NoSQLTopic.USER_ACCOUNTS, ediPi);
		if(!StringUtils.isBlank(json)) {
			try {
				final JSONObject jso = new JSONObject(json);
				final JSONArray perms = jso.getJSONArray("permissions");
				for (int i = 0; i < perms.length(); i++) {
					result.add(perms.get(i).toString());
				}
			} catch (final JSONException e) {
				LOGGER.error(String.format("Failed to extract permissions from json user account (ediPi:%s)", ediPi));
			}
		}
		return result;
	}

	@Override
	public String getUserAccountType(String ediPi) {
		return null;
	}

}
