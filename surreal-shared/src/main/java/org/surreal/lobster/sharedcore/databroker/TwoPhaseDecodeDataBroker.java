/**
 * Jan 26, 2012 1:24:07 PM
 */
package org.surreal.lobster.sharedcore.databroker;

import org.surreal.lobster.sharedcore.presenter.DecodeField;
import org.surreal.lobster.sharedcore.presenter.DecodeGroup;

/**
 * A data broker which retrieves data from the server in two phases.
 * @author daniel.wilkin
 */
public interface TwoPhaseDecodeDataBroker<T> extends DataBroker {

	/**
	 * Suggests a call to the server be issued to obtain the decode field data values 
	 * for the specified group.  This method supports batch data loading in the client. 
	 * Note that the call to the server may not occur, however the requested data is 
	 * guaranteed to be available via subsequent calls to {@link #getDecodeData(DecodeField)}.<p>
	 * 
	 * This method is exclusive: a call on this method with group '<i>A</i>' only guarantees 
	 * the availability of group '<i>A</i>' data, it makes no guarantees on the availability
	 * of other group data not specifically identified in <code>group</code>.
	 * @param group The group whose decode fields will be available to the <code>callback</code>
	 * @param callback The code to execute
	 * @see DecodeGroup
	 */
	public void loadDecodeData(DecodeGroup group, DataBrokerCallback<Void> callback);
	
	/**
	 * Retrieves the collection of decodes and their selectable values as <code>OptionItem</code>s 
	 * associated with the specified decode field.  Due to the possibly fluid nature of underlying
	 * implementations, the requested data may not be available and thus will return an empty
	 * list.  To avoid this, clients should invoke this method within an anonymous 
	 * callback as follows:<pre>
	 *    ...
	 *    broker.loadDecodeData(DecodeGroup.MY_GROUP, new DataBrokerCallback() {
	 *       public void onSuccess() {
	 *          setOptions(broker.getDecodeData(DecodeField.MY_DECODE_FIELD));
	 *       }
	 *       ...
	 *    });</pre>
	 * @param field The decode field whose list of decodes should be retrieved
	 * @return The decodes or an empty list
	 */
	public T getDecodeData(DecodeField e);
	
}
