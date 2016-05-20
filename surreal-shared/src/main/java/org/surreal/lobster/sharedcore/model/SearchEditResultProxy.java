/**
 * 
 */
package org.surreal.lobster.sharedcore.model;

import java.util.List;

/**
 * NOTE: It is <b>imperative</b> that the arrays be declared as <code>List</code> instead of
 * <code>ArrayList</code>! Using <code>ArrayList</code> will result in a noxious
 * <i>&quot;java.lang.AssertionError: Incorrect size: 2&quot;</i> error. 
 * 
 * <a href=https://groups.google.com/forum/#!msg/google-web-toolkit/UoPa4ohP6sk/Q-tnuKALCgwJ>See google groups article</a>
 * @author kerry.baumer
 *
 */
public interface SearchEditResultProxy {
	List<SearchEditResultItemProxy> getItems();
	void setItems(List<SearchEditResultItemProxy> items);
}
