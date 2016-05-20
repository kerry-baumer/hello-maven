package org.surreal.lobster.sharedcore.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.surreal.lobster.sharedcore.event.handlers.EventHandler;
import org.surreal.lobster.sharedcore.event.types.Event;

public class EventBus {

	private final Map<Class<?>, List<?>> map = new HashMap<Class<?>, List<?>>();

	@Inject
	EventBus() {
		// empty
	}
	
	public <T extends EventHandler> T getHandler(Class<? extends Event<T>> eventClass, int position) {
		/*
		 * Suppressing warning is safe in this case since we control all the
		 * puts into the map.
		 */
		@SuppressWarnings("unchecked")
		final List<T> handlerList = (List<T>) map.get(eventClass);
		return handlerList == null ? null : handlerList.get(position);
	}

	public <T extends EventHandler> void addHandler(Class<? extends Event<T>> eventClass, T eventHandler) {
		/*
		 * Suppressing warning is safe in this case since we control all the
		 * puts into the map.
		 */
		@SuppressWarnings("unchecked")
		List<T> handlerList = (List<T>) map.get(eventClass);
		if (handlerList == null) {
			handlerList = new ArrayList<T>();
			map.put(eventClass, handlerList);
		}
		handlerList.add(eventHandler);
	}

	public <T extends EventHandler> void removeHandler(Class<? extends Event<T>> eventClass, T eventHandler) {
		/*
		 * Suppressing warning is safe in this case since we control all the
		 * puts into the map.
		 */
		@SuppressWarnings("unchecked")
		final List<T> handlerList = (List<T>) map.get(eventClass);

		if (handlerList != null) {
			handlerList.remove(eventHandler);

			if (handlerList.isEmpty()) {
				map.remove(eventClass);
			}
		}
	}

	public <T extends EventHandler> void dispatch(Event<T> event) {
		/*
		 * Suppressing warning is safe in this case since we control all the
		 * puts into the map.
		 */
		@SuppressWarnings("unchecked")
		final List<T> handlerList = (List<T>) map.get(event.getClass());
		if (handlerList != null) {
			for (final T handler : handlerList) {
				event.dispatch(handler);
			}
		} else {
			// TODO: Client side logging may be a better solution here.
			final StringBuilder msg = new StringBuilder();
			msg.append("\n*******************************************************************");
			msg.append("\n[EventBus] Event: " + event.getClass());
			msg.append("\n           was dispatched, but there are no handlers registered"); 
			msg.append("\n           for it!! This is probably a bug!");
			msg.append("\n*******************************************************************");
			System.out.println(msg);
		}
	}
}
