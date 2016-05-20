/**
 * 
 */
package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.ApproveEvent;

/**
 * @author kerry
 *
 */
public interface ApproveEventHandler extends EventHandler {
	void onApprove(ApproveEvent event);
}
