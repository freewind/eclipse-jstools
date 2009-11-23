package org.pkg.jstools.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.pkg.jstools.JSToolsActivator;


/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class LintPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * The default constructor for the JSLint preference page.
	 */
	public LintPreferencePage() {
		super(GRID);
		setPreferenceStore(JSToolsActivator.getDefault().getPreferenceStore());
		setDescription("See http://www.jslint.com/lint.html for details");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_PASSFAIL,
				"Stop on first error", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_WHITE,
				"Strict white space", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_ONEVAR,
				"Allow one var statement per function", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_BROWSER,
				"Assume a browser", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_WIDGET,
				"Assume a Yahoo Widget", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_SIDEBAR,
				"Assume a Windows Sidebar Gadget", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_RHINO,
				"Assume Rhino", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_SAFE,
				"Safe Subset", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_ADSAFE,
				"ADsafe", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_DEBUG,
				"Tolerate debugger statements", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_EVIL,
				"Tolerate eval", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_LAXBREAK,
				"Tolerate sloppy line breaking", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_FORIN,
				"Tolerate unfiltered for in", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_SUB,
				"Tolerate inefficient subscripting", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_CSS,
				"Tolerate CSS workarounds", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_CAP,
				"Tolerate HTML case", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_ON,
				"Tolerate HTML event handlers", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_FRAGMENT,
				"Tolerate HTML fragments", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_UNDEF,
				"Disallow undefined variables", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_NOMEN,
				"Disallow dangling _ in identifiers", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_EQEQEQ,
				"Disallow == and !=", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_PLUSPLUS,
				"Disallow ++ and --", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_BITWISE,
				"Disallow bitwise operators", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_REGEXP,
				"Disallow insecure . and [^...] in /RegExp/",
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_STRICT,
				"Require \"use strict\"", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_NEWCAP,
				"Require Initial Caps for constructors", getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_IMMED,
				"Require parens around immediate invocations",
				getFieldEditorParent()));
		
		addField(new BooleanFieldEditor(PreferenceConstants.JSLINT_FNSPACE,
				"Require a space after function, catch, etc.",
				getFieldEditorParent()));
		
		addField(new StringFieldEditor(PreferenceConstants.JSLINT_INDENT,
				"Strict white space indentation", getFieldEditorParent()));
		/*addField(new StringFieldEditor(PreferenceConstants.JSLINT_LINELEN,
				"Maximum line length", getFieldEditorParent()));*/
		addField(new StringFieldEditor(PreferenceConstants.JSLINT_ERRS,
				"Maximum number of errors", getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}