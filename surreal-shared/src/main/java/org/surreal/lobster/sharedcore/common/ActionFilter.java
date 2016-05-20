package org.surreal.lobster.sharedcore.common;

/**
 * A type that is capable of accepting a single value object, evaluating it, and
 * then determining whether the action should be allowed to proceed. Useful for
 * strategy implementations when the value object is required to be evaluated
 * externally.
 * 
 * @author adam.thomas
 * 
 * @param <T>
 *            the type of the value object that will be evaluated in order to
 *            determine if the action will be allowed to proceed
 */
public interface ActionFilter<T> {

	/**
	 * Evaluates the value object that it receives and determines if the action
	 * will be allowed to proceed.
	 * 
	 * @param value
	 *            the value object used to determine if the action will be
	 *            allowed to proceed
	 * @return a boolean value indicating whether the action will be allowed to
	 *         proceed
	 */
	boolean eval(T value);

}
