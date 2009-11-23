package org.pkg.jstools.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class Preferences extends PreferencePage implements
		IWorkbenchPreferencePage {

	@Override
	protected Control createContents(Composite parent) {
		Label l = new Label(parent, 1);
		l.setText("Select a child in the tree to set properties.");
		return null;
	}

	public void init(IWorkbench wb) {
		
	}

}
