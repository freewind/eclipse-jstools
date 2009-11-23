package org.pkg.jstools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.jface.dialogs.MessageDialog;
import org.pkg.jstools.JSLint;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class JSLintHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public JSLintHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);

		IEditorPart activeEditor = window.getActivePage().getActiveEditor();
		IEditorInput editorInput = activeEditor.getEditorInput();
		IFile file = null;

		if (activeEditor == null || editorInput == null) {
			return null;
		}

		if (editorInput instanceof org.eclipse.ui.part.FileEditorInput) {
			file = ((FileEditorInput) editorInput).getFile();
		} else {
			MessageDialog.openWarning(window.getShell(), "JSTools", "Resource "
					+ editorInput.getName() + " is not part of the workspace!");
			return null;
		}

		if (file == null) {
			return null;
		}

		if (activeEditor.isDirty()) {
			activeEditor.doSave(null);
		}
		new JSLint().validate(file);
		
		return null;
	}
}
