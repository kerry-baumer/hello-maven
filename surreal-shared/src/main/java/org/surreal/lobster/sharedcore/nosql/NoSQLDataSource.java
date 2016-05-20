package org.surreal.lobster.sharedcore.nosql;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface NoSQLDataSource {
	
	void insert(NoSQLTopic topic, String key, String value);
	void insert(NoSQLTopic topic, String key, String value, int timeToLive);
	void insertMulti(NoSQLTopic topic, Map<String, String> keyValues);
	void insertSuper(NoSQLTopic topic, String key, String column, Map<String, Object> value);
	String get(NoSQLTopic topic, String key);
	Map<String, String> getMulti(NoSQLTopic topic, String... keys);
	Map<String, Object> getSuper(NoSQLTopic topic, String key, String column);
	void delete(NoSQLTopic topic, String... keys);
	void deleteSuper(NoSQLTopic topic, String column, String... keys);
	Date getTimeStamp(NoSQLTopic topic, String key);
	List<String> getRangeSlice(NoSQLTopic topic, String minKey, String maxKey);
	/**
	 * Clears the data from a column family.
	 * @param topic The column family name
	 */
	void truncateEntries(NoSQLTopic... topic);
	/**
	 * Drops all columns from a column family.  <b>WARNING:</b> this operation
	 * cannot be undone and destroys all keys found in the column family.
	 * @param topic The topic whose column family should be purged
	 */
	void purgeFamily(NoSQLTopic topic);
	/**
	 * Obtains the keys found in the cache.  This method is processing intensive for the cache; as such,
	 * avoid invocations to it.  However, it has the benefit of not deserializing the column data associated
	 * with each key.  <i>Note:</i> this method will report tombstones found in the cache.
	 * @param topic The topic whose keys are to be obtained
	 * @return The list of keys in the specified topic
	 */
	List<String> getKeysIn(NoSQLTopic topic);
}
