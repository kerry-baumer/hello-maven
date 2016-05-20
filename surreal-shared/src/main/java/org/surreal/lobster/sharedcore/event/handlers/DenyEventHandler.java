/**
 * 
 */
package org.surreal.lobster.sharedcore.event.handlers;

import org.surreal.lobster.sharedcore.event.types.DenyEvent;

/**
 * @author kerry
 *
 */
public interface DenyEventHandler extends EventHandler {
	void onDeny(DenyEvent event);
}
