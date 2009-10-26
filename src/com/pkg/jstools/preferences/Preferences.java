package com.pkg.jstools.preferences;

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
		/*
		TabFolder folder = new TabFolder(parent, 0);
		folder.setLayout(new TabFolderLayout());
		folder.setLayoutData(new GridData(1808));
		
		TabItem lint = new TabItem(folder, 0);
		lint.setText("JS Lint Preferences");
		lint.setControl(createLintPreferenceControls(folder));
		
		TabItem jsb = new TabItem(folder, 0);
		jsb.setText("JS Beautifier Preferences");*/
		
		return null;
	}
/*
	private Control createLintPreferenceControls(Composite parent) {
		Composite lintControls = new Composite(parent, 0);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		lintControls.setLayout(layout);
		
		//lintControls
		
		return lintControls;
	}*/

	public void init(IWorkbench wb) {
		// TODO Auto-generated method stub

	}

}
