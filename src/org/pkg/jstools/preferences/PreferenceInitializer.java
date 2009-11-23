package org.pkg.jstools.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.pkg.jstools.JSToolsActivator;


/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = JSToolsActivator.getDefault()
				.getPreferenceStore();

		store.setDefault(PreferenceConstants.JSLINT_PASSFAIL, false);
		store.setDefault(PreferenceConstants.JSLINT_WHITE, false);
		store.setDefault(PreferenceConstants.JSLINT_ONEVAR, false);
		store.setDefault(PreferenceConstants.JSLINT_BROWSER, false);
		store.setDefault(PreferenceConstants.JSLINT_WIDGET, false);
		store.setDefault(PreferenceConstants.JSLINT_SIDEBAR, false);
		store.setDefault(PreferenceConstants.JSLINT_RHINO, false);
		store.setDefault(PreferenceConstants.JSLINT_SAFE, false);
		store.setDefault(PreferenceConstants.JSLINT_ADSAFE, false);
		store.setDefault(PreferenceConstants.JSLINT_DEBUG, false);
		store.setDefault(PreferenceConstants.JSLINT_EVIL, false);
		store.setDefault(PreferenceConstants.JSLINT_LAXBREAK, false);
		store.setDefault(PreferenceConstants.JSLINT_FORIN, false);
		store.setDefault(PreferenceConstants.JSLINT_SUB, false);
		store.setDefault(PreferenceConstants.JSLINT_CSS, false);
		store.setDefault(PreferenceConstants.JSLINT_CAP, false);
		store.setDefault(PreferenceConstants.JSLINT_ON, false);
		store.setDefault(PreferenceConstants.JSLINT_FRAGMENT, false);
		store.setDefault(PreferenceConstants.JSLINT_UNDEF, false);
		store.setDefault(PreferenceConstants.JSLINT_NOMEN, false);
		store.setDefault(PreferenceConstants.JSLINT_EQEQEQ, false);
		store.setDefault(PreferenceConstants.JSLINT_PLUSPLUS, false);
		store.setDefault(PreferenceConstants.JSLINT_BITWISE, false);
		store.setDefault(PreferenceConstants.JSLINT_REGEXP, false);
		store.setDefault(PreferenceConstants.JSLINT_STRICT, false);
		store.setDefault(PreferenceConstants.JSLINT_NEWCAP, false);
		store.setDefault(PreferenceConstants.JSLINT_IMMED, false);
		store.setDefault(PreferenceConstants.JSLINT_FNSPACE, false);

		store.setDefault(PreferenceConstants.JSLINT_INDENT, 4);
		//store.setDefault(PreferenceConstants.JSLINT_LINELEN, 0);
		store.setDefault(PreferenceConstants.JSLINT_ERRS, 50);

		store.setDefault(PreferenceConstants.JSB_INDENT, 4);
		store.setDefault(PreferenceConstants.JSB_PRESERVE_EMPTY, true);
		store.setDefault(PreferenceConstants.JSB_DETECT_PACKERS, true);
	}

}
