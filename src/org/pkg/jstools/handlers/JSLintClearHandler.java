/**
 * 
 */
package org.pkg.jstools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.pkg.jstools.JSLint;

/**
 * @author praveen.kailas@gmail.com
 *
 */
public class JSLintClearHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public JSLintClearHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		new JSLint().clearMarkers(ResourcesPlugin.getWorkspace().getRoot());
		return null;
	}
}