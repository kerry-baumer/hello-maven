package org.surreal.lobster.sharedcore.nosql;

import static me.prettyprint.hector.api.factory.HFactory.createColumn;
import static me.prettyprint.hector.api.factory.HFactory.createColumnQuery;
import static me.prettyprint.hector.api.factory.HFactory.createKeyspace;
import static me.prettyprint.hector.api.factory.HFactory.createMultigetSliceQuery;
import static me.prettyprint.hector.api.factory.HFactory.createMutator;
import static me.prettyprint.hector.api.factory.HFactory.createSuperColumn;
import static me.prettyprint.hector.api.factory.HFactory.createSuperColumnQuery;
import static me.prettyprint.hector.api.factory.HFactory.getOrCreateCluster;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.prettyprint.cassandra.serializers.ObjectSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.HSuperColumn;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.beans.Rows;
import me.prettyprint.hector.api.beans.SuperSlice;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.MultigetSliceQuery;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.RangeSlicesQuery;
import me.prettyprint.hector.api.query.SuperColumnQuery;
import me.prettyprint.hector.api.query.SuperSliceQuery;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * A connection to Cassandra, the NoSQL database server.
 * @see <a href="https://github.com/rantav/hector/wiki/User-Guide">Hector User Guide</a>
 * @author jeffrey.richley
 * @author kerry.baumer
 * @author daniel.wilkin
 * @deprecated No Cassandra server instance resides on the server
 */
@Deprecated
public class CassandraConnection implements NoSQLDataSource {
	
	/** The LOGGER instance */
    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConnection.class);
    
    /** The DISABLED_CACHING_MSG constant */
    private static final String DISABLED_CACHING_MSG = "Cassandra caching is DISABLED!";
	/** The MAX_KEYS_QUERIED constant */
	private static final int MAX_KEYS_QUERIED = 100000;
	/** The COLUMN_NAME constant */
	private final static String COLUMN_NAME = "v";
	/** The CLIENT_TIMEOUT_ON_BUSY_POOL constant */
	private static final int CLIENT_TIMEOUT_ON_BUSY_POOL = 8000;
	/** The CLIENT_TIMEOUT_MILLIS constant */
	private static final int CLIENT_TIMEOUT_MILLIS = 6000;
	/** The MAX_POOLED_IDLE_CLIENTS constant */
	private static final int MAX_POOLED_IDLE_CLIENTS = 5;
	/** The MAX_POOLED_CLIENTS constant */
	private static final int MAX_POOLED_CLIENTS = 20;
	
	// Create a cluster
	private final Cluster cluster;
	// Choose a key space
	private final Keyspace keyspace;
	@SuppressWarnings("unused")
	private final String keyspaceName;
	
	// create a string extractor. I'll explain that later
	private final StringSerializer strSerializer = StringSerializer.get();
	private final ObjectSerializer objSerializer = ObjectSerializer.get();
	
	/** The isCacheEnabled property */
	private static final boolean isCacheEnabled;
	
	/** The cassandraHost property */
	private final static String cassandraHost;

	static {
		cassandraHost = getCassandraHostName();
		final StringBuilder buffer = new StringBuilder();
		buffer.append("Cassandra config ")
				.append("[host=").append(cassandraHost)
				.append(",maxpoolsize=").append(MAX_POOLED_CLIENTS)
				.append(",maxpooledidleclients=").append(MAX_POOLED_IDLE_CLIENTS)
				.append(",clienttimeout=").append(CLIENT_TIMEOUT_MILLIS)
				.append(']');
		LOGGER.trace(buffer.toString());
		if(StringUtils.isBlank(cassandraHost)) {
			isCacheEnabled = false;
		} else {
			isCacheEnabled = CassandraUrlProvider.isCassandaHostEnabled();
		}
	}
	
	private static String getCassandraHostName() {
		/*
		 * Determines which URL has an actual server running on it, primary, alternate, or neither.
		 */
		String url = null;
		URL u;
		String configuredUrl = null;
		try {
			configuredUrl = CassandraUrlProvider.getCassandraHostUrl();
			u = new URL("http://" + configuredUrl);
			TTransport tr = new TSocket(u.getHost(), u.getPort());
			try {
				url = String.format("%s:%d", u.getHost(), u.getPort());
				tr.open();
			} catch (final TTransportException e) {
				LOGGER.trace("Cassandra connection failure: ", e);
				LOGGER.info(String.format("Primary cassandra server unavailable (%s), attempting alternate.", url));
				configuredUrl = CassandraUrlProvider.getCassandraHostAlternateUrl();
				u = new URL("http://" + configuredUrl);
				try {
					tr = new TSocket(u.getHost(), u.getPort());
					url = String.format("%s:%d", u.getHost(), u.getPort());
					tr.open();
				} catch (final TTransportException e1) {
					LOGGER.trace("Cassandra alternate connection failure: ", e1);
					LOGGER.info(String.format("Alternate cassandra server unavailable (%s).", url));
					LOGGER.error("No available cassandra host!! Caching will be disabled!!");
					return null;
				}
			} finally {
				tr.close();
			}
		} catch (final MalformedURLException me) {
			LOGGER.error("The system configured URL '" + configuredUrl + "' is malformed.  Caching will be disabled!!");
			return null;
		}
		return url;
	}

	public CassandraConnection() {
		this("CollectiveKeyspace");
	}
	
	public CassandraConnection(String keyspaceName) {
		this.keyspaceName = keyspaceName;
		if (isCacheEnabled) {
				final CassandraHostConfigurator cassandraHostConfigurator = new CassandraHostConfigurator(cassandraHost);
				cassandraHostConfigurator.setMaxActive(MAX_POOLED_CLIENTS);
				cassandraHostConfigurator.setMaxIdle(MAX_POOLED_IDLE_CLIENTS);
				cassandraHostConfigurator.setCassandraThriftSocketTimeout(CLIENT_TIMEOUT_MILLIS);
				cassandraHostConfigurator.setMaxWaitTimeWhenExhausted(CLIENT_TIMEOUT_ON_BUSY_POOL);
				cluster = getOrCreateCluster("CollectiveCluster", cassandraHostConfigurator);
				keyspace = createKeyspace(keyspaceName, cluster);
		} else {
			keyspace = null;
			cluster = null;
			LOGGER.warn(DISABLED_CACHING_MSG);
		}
	}
	
	public Keyspace getKeyspace() { return keyspace; }
	
	public void insert(String topic, String key, String value) {
		if (isCacheEnabled) {
			if(topic == null) {
				throw new IllegalArgumentException("Topic must be specified!");
			}
			if(StringUtils.isBlank(key)) {
				throw new IllegalArgumentException("Key must be specified!");
			}
			createMutator(keyspace, strSerializer)
					.insert(key, topic, createColumn(COLUMN_NAME, value, strSerializer, strSerializer));
		} else {
			LOGGER.warn(DISABLED_CACHING_MSG);
		}
	}
	
	@Override
	public void insert(NoSQLTopic topic, String key, String value) {
		insert(topic.getName(), key, value);
	}
	
	// time to live is in seconds
	@Override
	public void insert(NoSQLTopic topic, String key, String value, int timeToLive) {
		if (isCacheEnabled) {
			if(topic == null) {
				throw new IllegalArgumentException("Topic must be specified!");
			}
			if(StringUtils.isBlank(key)) {
				throw new IllegalArgumentException("Key must be specified!");
			}
			createMutator(keyspace, strSerializer)
					.insert(key, topic.getName(), createColumn(COLUMN_NAME, value, timeToLive, strSerializer, strSerializer));
		} else {
			LOGGER.warn(DISABLED_CACHING_MSG);
		}
	}
	
	@Override
	public void insertMulti(NoSQLTopic topic, Map<String, String> keyValues) {
		if (isCacheEnabled) {
		    final Mutator<String> m = createMutator(keyspace, strSerializer);
		    for (final Map.Entry<String, String> keyValue: keyValues.entrySet()) {
		    	m.addInsertion(keyValue.getKey(), topic.getName(),
		    			createColumn(COLUMN_NAME, keyValue.getValue(), keyspace.createClock(), strSerializer, strSerializer));
		    }
		    m.execute();
		}
		else {
			LOGGER.warn(DISABLED_CACHING_MSG);
		}
	}
	
	@Override
	public void insertSuper(NoSQLTopic topic, String key, String column, Map<String, Object> value) {
		if (isCacheEnabled) {
			if(topic == null) {
				throw new IllegalArgumentException("Topic must be specified!");
			}
			if(StringUtils.isBlank(key)) {
				throw new IllegalArgumentException("Key must be specified!");
			}
			if(StringUtils.isBlank(column)) {
				throw new IllegalArgumentException("Column must be specified!");
			}
	        try {
	        	final List<HColumn<String, Object>> data = new ArrayList<HColumn<String, Object>>();
	        	for(final Entry<String, Object> ntry : value.entrySet()) {
	        		data.add(createColumn(ntry.getKey(), ntry.getValue(), strSerializer, ObjectSerializer.get()));
	        	}
	            final Mutator<String> mutator = createMutator(keyspace, strSerializer);
	            mutator.insert(key, topic.getName(), createSuperColumn(column, data,
	                    strSerializer, strSerializer, ObjectSerializer.get()));
	        } catch (final HectorException e) {
	            LOGGER.error("Failed to insert super data.", e);
	        }
		}
		else {
			LOGGER.warn(DISABLED_CACHING_MSG);
		}
	}
	
	public String get (String topic, String key) {
		if (isCacheEnabled) {
			final ColumnQuery<String, String, String> q = createColumnQuery(keyspace, StringSerializer.get(), strSerializer, strSerializer);
		    final QueryResult<HColumn<String, String>> r = q.setKey(key).
			        setName(COLUMN_NAME).
			        setColumnFamily(topic).
			        execute();
		    final HColumn<String, String> c = r.get();
		    return c == null ? null : c.getValue();
		}
		LOGGER.warn(DISABLED_CACHING_MSG);
		return null;
	}
	
	@Override
	public String get(NoSQLTopic topic, String key) {
		return get(topic.getName(), key);
	}
	
	@Override
	public Date getTimeStamp(NoSQLTopic topic, String key) {
		if (isCacheEnabled) {
			final ColumnQuery<String, String, String> q = createColumnQuery(keyspace, StringSerializer.get(), strSerializer, strSerializer);
		    final QueryResult<HColumn<String, String>> r = q.setKey(key).
					setName(COLUMN_NAME).
					setColumnFamily(topic.getName()).
					execute();
		    final HColumn<String, String> c = r.get();
		    return c == null ? null : new Date(c.getClock() / 1000);	// getClock is microsecond resolution
		}
		LOGGER.warn(DISABLED_CACHING_MSG);
		return null;
	}
	
	@Override
	public Map<String, String> getMulti(NoSQLTopic topic, String... keys) {
		final Map<String, String> ret = Maps.newHashMapWithExpectedSize(keys.length);
		if (isCacheEnabled) {
		    final MultigetSliceQuery<String, String,String> q = createMultigetSliceQuery(keyspace, strSerializer, strSerializer, strSerializer);
		    q.setColumnFamily(topic.getName());
		    q.setKeys(keys);
		    q.setColumnNames(COLUMN_NAME);
	
		    final QueryResult<Rows<String, String,String>> r = q.execute();
		    final Rows<String, String,String> rows = r.get();
		    for (final String k: keys) {
				final HColumn<String,String> c = rows.getByKey(k).getColumnSlice().getColumnByName(COLUMN_NAME);
				if (c != null && c.getValue() != null) {
					ret.put(k, c.getValue());
				}
		    }
		}
		else {
			LOGGER.warn(DISABLED_CACHING_MSG);
		}
		return ret;
	}

	@Override
	public Map<String, Object> getSuper(NoSQLTopic topic, String key, String column) {
		final Map<String, Object> map = Maps.newHashMap();
		if (isCacheEnabled) {
	        try {
	            final SuperColumnQuery<String, String, String, Object> superColumnQuery =
		                createSuperColumnQuery(keyspace, strSerializer, strSerializer,
		                		strSerializer, ObjectSerializer.get());
	            superColumnQuery.setColumnFamily(topic.getName()).setKey(key).setSuperName(column);
	            final QueryResult<HSuperColumn<String, String, Object>> result = superColumnQuery.execute();
	            final HSuperColumn<String, String, Object> superCol = result.get();
				LOGGER.trace("Read HSuperColumn from cassandra: " + superCol);
	            final int numCol = superCol.getSize();
	            for(int i = 0; i < numCol; i++) {
	            	final HColumn<String, Object> col = superCol.get(i);
	            	map.put(col.getName(), col.getValue());
	            }
	        } catch (final HectorException e) {
	            LOGGER.error("Failed to retrieve super data.", e);
	        }
		}
		else {
			LOGGER.warn(DISABLED_CACHING_MSG);
		}
        return map;
	}
	
	public List<Map<String, Object>> getSuperSlice(NoSQLTopic cfTopic, String key) {
		final SuperSliceQuery<String, String, String, Object> q = HFactory.createSuperSliceQuery(keyspace, strSerializer,
				strSerializer, strSerializer, objSerializer);
		q.setColumnFamily(cfTopic.getName());
		q.setRange("", "", true, 100);
		q.setKey(key);
		final List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		try {
			final QueryResult<SuperSlice<String, String, Object>> r = q.execute();
			final List<HSuperColumn<String, String, Object>> cols = r.get().getSuperColumns();
			for(final HSuperColumn<String, String, Object> col : cols) {
				map = new LinkedHashMap<String, Object>();
				final List<HColumn<String, Object>> hcols = col.getColumns();
				for(final HColumn<String, Object> hcol : hcols) {
					map.put(hcol.getName(), hcol.getValue());
				}
				result.add(map);
			}
		} catch (final HectorException he) {
			he.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public List<String> getRangeSlice(NoSQLTopic cfTopic, String minKey, String maxKey ) {	
		final List<String> result = new ArrayList<String>();
		try {
			final RangeSlicesQuery<String, String, String> q = HFactory.createRangeSlicesQuery(keyspace, strSerializer, strSerializer, strSerializer);

			q.setColumnFamily(cfTopic.getName());
			q.setRange("", "", false, 100);
			q.setKeys(minKey, maxKey);	
			final QueryResult<OrderedRows<String, String, String>> r = q.execute();
			OrderedRows<String, String, String> rows = r.get();
			List<Row<String, String, String>> rowlist = rows.getList();

			for (Row<String, String, String> row: rowlist) {
				ColumnSlice<String, String> slice = row.getColumnSlice();
				for (HColumn<String, String> column:  slice.getColumns()) {
					if(column.getName().equals(COLUMN_NAME)){
						result.add(column.getValue());
					}
				}           	                         
			}
		} catch (final HectorException he) {
			he.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void delete(NoSQLTopic topic, String... keys) {
		if (isCacheEnabled) {
			final Mutator<String> m = createMutator(keyspace, strSerializer);
			for (final String key: keys) {
				m.addDeletion(key, topic.getName(), COLUMN_NAME, strSerializer);
			}
			m.execute();
		}
		else {
			LOGGER.warn(DISABLED_CACHING_MSG);
		}
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.nosql.NoSQLDataSource#deleteSuper(org.surreal.lobster.sharedcore.nosql.NoSQLTopic, java.lang.String, java.lang.String[])
	 */
	@Override
	public void deleteSuper(NoSQLTopic topic, String column, String... keys) {
		if (isCacheEnabled) {
			// addDeletion(key, colFam) in mutator (from http://comments.gmane.org/gmane.comp.db.hector.user/2856)
/*			final Mutator<String> m = createMutator(keyspace, strSerializer);
			for (final String key : keys) {
				final SuperColumnQuery<String, String, String, Object> superColumnQuery =
		                createSuperColumnQuery(keyspace, strSerializer, strSerializer,
		                		strSerializer, ObjectSerializer.get());
	            superColumnQuery.setColumnFamily(topic.getName()).setKey(key).setSuperName(column);
	            final QueryResult<HSuperColumn<String, String, Object>> result = superColumnQuery.execute();
	            final HSuperColumn<String, String, Object> superCol = result.get();
	            for (int i = 0; i < superCol.getSize(); i++) {
	            	m.delete(key, topic.getName(), superCol.get(i), objSerializer);
//	            	m.addDeletion(key, topic.getName(), superCol.get(i), objSerializer);
//					m.superDelete(key, topic.getName(), superCol.get(i), ObjectSerializer.get());
				}
			}
*/		}
	}

	@Override
	public void purgeFamily(NoSQLTopic topic) {
		cluster.truncate(keyspace.getKeyspaceName(), topic.getName());
	}
	
	@Override
	public void truncateEntries(NoSQLTopic... noSQLTopics) {
		final int port = Integer.parseInt(cassandraHost.substring(cassandraHost.indexOf(':') + 1));
		final String host = cassandraHost.substring(0, cassandraHost.indexOf(':'));
		final CassandraThriftZombie cli = new CassandraThriftZombie(host, port);
		if (cli.connect(keyspace.getKeyspaceName())) {
			for(NoSQLTopic topic : noSQLTopics) {
				cli.truncate(topic);
				LOGGER.debug("Truncated " + topic.toString());
			}
			cli.disconnect();
		}
	}
	
	@Override
	public List<String> getKeysIn(NoSQLTopic topic) {
		if (isCacheEnabled) {
			final RangeSlicesQuery<String, String, String> rangeSlicesQuery =
					HFactory.createRangeSlicesQuery(keyspace, strSerializer, strSerializer,
				    strSerializer);
			rangeSlicesQuery.setColumnFamily(topic.getName());
			rangeSlicesQuery.setKeys("", "");
			rangeSlicesQuery.setReturnKeysOnly();
			rangeSlicesQuery.setRowCount(MAX_KEYS_QUERIED);
			final QueryResult<OrderedRows<String, String, String>> result = rangeSlicesQuery.execute();
			final List<Row<String,String,String>> rows = result.get().getList();
			final List<String> keys = Lists.newArrayListWithExpectedSize(rows.size());
			for (final Row<String, String, String> r : rows) {
				keys.add(r.getKey());
			}
			return keys;
		}
		LOGGER.warn(DISABLED_CACHING_MSG);
		return Collections.emptyList();
	}
	
	/**
     * Accessor for instance field <code>isCacheEnabled</code>.
     * @return The <code>isCacheEnabled</code>
     */
    public boolean isCacheEnabled() {
    	return isCacheEnabled;
    }

}
