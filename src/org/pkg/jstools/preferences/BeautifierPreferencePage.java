package org.pkg.jstools.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
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

public class BeautifierPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	/**
	 * Values for code indentation.
	 */
	private String[][] indentArray = { { "4 spaces", "4" },
			{ "a tab character", "1" }, { "2 spaces", "2" },
			{ "3 spaces", "3" }, { "8 spaces", "8" } };

	/**
	 * The default constructor for the beautifier preference page.
	 */
	public BeautifierPreferencePage() {
		super(GRID);
		setPreferenceStore(JSToolsActivator.getDefault().getPreferenceStore());
		setDescription("See http://jsbeautifier.org/ for details.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(new ComboFieldEditor(PreferenceConstants.JSB_INDENT,
				"Indent with", indentArray, getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.JSB_PRESERVE_EMPTY,
				"Preserve empty lines", getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.JSB_DETECT_PACKERS,
				"Detect packers", getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.JSB_IDENT_SPACE,
				"Add space after identifiers (function, catch, etc.)",
				getFieldEditorParent()));

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