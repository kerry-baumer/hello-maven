/**
 * Aug 28, 2012 3:13:40 PM
 */
package org.surreal.lobster.sharedcore.common;

/**
 * Defines behavior to be executed in a successful flow path and a
 * custom error message to be displayed if a previous command or
 * action failed to execute.  By intent, the caller will invoke the
 * {@link #getCustomErrorMessage()} method if the previous command failed.
 * If the previous action succeeded, the caller will then invoke the
 * {@link #execute()} method of this interface.<p>
 * 
 * This interface is most useful when the behavior of this <code>Command</code>
 * is dependent on the success of a previous action.
 * @author daniel.wilkin
 */
public interface DependentCommand extends Command {

	/**
	 * Obtains the custom error message to be displayed in a failure
	 * situation.
	 * @return The custom error message
	 */
	public String getCustomErrorMessage();
}
