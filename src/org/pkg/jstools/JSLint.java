package org.pkg.jstools;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.pkg.jstools.preferences.PreferenceConstants;

/**
 * @author praveen.kailas@gmail.com
 * 
 */
public class JSLint {

	private Context context = null;
	private Scriptable scope = null;

	/**
	 * 
	 */
	public JSLint() {

	}

	/**
	 * Invokes the JSLint validator on the file.
	 * 
	 * @param file
	 *            the file to validate.
	 */
	public void validate(IFile file) {
		if (file == null) {
			return;
		}

		initContext();

		String contents = "";
		String options = "/*jslint maxerr: 50 */";
		try {
			contents = getFileContents(file.getContents());
			options = getLintPreferences().toString().replaceAll("=", ":")
					.replaceFirst("\\{", "/*jslint ").replaceAll("\\}", " */");
			contents = options + "\n" + contents;
			scope.put("contents", scope, contents);
			context.evaluateString(scope, "results = JSLINT(contents, null);",
					"JSLint", 1, null);
			Scriptable lint = (Scriptable) scope.get("JSLINT", scope);
			NativeArray errors = (NativeArray) lint.get("errors", null);
			clearMarkers(file);
			for (int i = 0; i < errors.getLength(); i++) {
				NativeObject error = (NativeObject) errors.get(i, null);
				Double lineNo = (Double) error.get("line", null);
				Object reason = error.get("reason", null);
				IMarker marker = file
						.createMarker("org.eclipse.core.resources.problemmarker");
				marker.setAttribute(IMarker.LINE_NUMBER, lineNo.intValue());
				marker.setAttribute(IMarker.MESSAGE, reason);
				marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				// marker.setAttribute(IMarker.USER_EDITABLE, true);
				// Needed to delete own markers!
				marker.setAttribute("org.pkg.jstools.marker", true);
			}
			if (errors.getLength() == 0) {
				MessageDialog.openInformation(null, "JSLint",
						"HOORAY! No errors found. \n" + options);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context = null;
		}
	}

	private void clearMarkers(IFile file) {
		IMarker[] markers = null;
		try {
			markers = file.findMarkers(
					"org.eclipse.core.resources.problemmarker", true,
					IResource.DEPTH_INFINITE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (IMarker marker : markers) {
			try {
				if (marker.getAttribute("org.pkg.jstools.marker").toString() == "true") {
					marker.delete();
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return
	 */
	private void initContext() {
		try {
			context = Context.enter();
			context.setLanguageVersion(Context.VERSION_1_6);
			scope = context.initStandardObjects();
			String jsLintCode = getFileContents(JSLint.class
					.getResourceAsStream("fulljslint.js"));
			context.evaluateString(scope, jsLintCode, "JSLint", 1, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFileContents(InputStream stream) {
		StringBuffer contents = new StringBuffer("");
		try {
			byte[] readBytes = new byte[1024];
			int i = 0;
			while ((i = stream.read(readBytes)) > 0) {
				contents.append(new String(readBytes, 0, i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents.toString();
	}

	private Map<String, Object> getLintPreferences() {
		Map<String, Object> map = new HashMap<String, Object>();

		IPreferenceStore prefs = JSToolsActivator.getDefault()
				.getPreferenceStore();

		map.put(PreferenceConstants.JSLINT_PASSFAIL, prefs
				.getBoolean(PreferenceConstants.JSLINT_PASSFAIL));
		map.put(PreferenceConstants.JSLINT_WHITE, prefs
				.getBoolean(PreferenceConstants.JSLINT_WHITE));
		map.put(PreferenceConstants.JSLINT_ONEVAR, prefs
				.getBoolean(PreferenceConstants.JSLINT_ONEVAR));
		map.put(PreferenceConstants.JSLINT_BROWSER, prefs
				.getBoolean(PreferenceConstants.JSLINT_BROWSER));
		map.put(PreferenceConstants.JSLINT_WIDGET, prefs
				.getBoolean(PreferenceConstants.JSLINT_WIDGET));
		map.put(PreferenceConstants.JSLINT_SIDEBAR, prefs
				.getBoolean(PreferenceConstants.JSLINT_SIDEBAR));
		map.put(PreferenceConstants.JSLINT_RHINO, prefs
				.getBoolean(PreferenceConstants.JSLINT_RHINO));
		map.put(PreferenceConstants.JSLINT_SAFE, prefs
				.getBoolean(PreferenceConstants.JSLINT_SAFE));
		map.put(PreferenceConstants.JSLINT_ADSAFE, prefs
				.getBoolean(PreferenceConstants.JSLINT_ADSAFE));
		map.put(PreferenceConstants.JSLINT_DEBUG, prefs
				.getBoolean(PreferenceConstants.JSLINT_DEBUG));
		map.put(PreferenceConstants.JSLINT_EVIL, prefs
				.getBoolean(PreferenceConstants.JSLINT_EVIL));
		map.put(PreferenceConstants.JSLINT_LAXBREAK, prefs
				.getBoolean(PreferenceConstants.JSLINT_LAXBREAK));
		map.put(PreferenceConstants.JSLINT_FORIN, prefs
				.getBoolean(PreferenceConstants.JSLINT_FORIN));
		map.put(PreferenceConstants.JSLINT_SUB, prefs
				.getBoolean(PreferenceConstants.JSLINT_SUB));
		map.put(PreferenceConstants.JSLINT_CSS, prefs
				.getBoolean(PreferenceConstants.JSLINT_CSS));
		map.put(PreferenceConstants.JSLINT_CAP, prefs
				.getBoolean(PreferenceConstants.JSLINT_CAP));
		map.put(PreferenceConstants.JSLINT_ON, prefs
				.getBoolean(PreferenceConstants.JSLINT_ON));
		map.put(PreferenceConstants.JSLINT_FRAGMENT, prefs
				.getBoolean(PreferenceConstants.JSLINT_FRAGMENT));
		map.put(PreferenceConstants.JSLINT_UNDEF, prefs
				.getBoolean(PreferenceConstants.JSLINT_UNDEF));
		map.put(PreferenceConstants.JSLINT_NOMEN, prefs
				.getBoolean(PreferenceConstants.JSLINT_NOMEN));
		map.put(PreferenceConstants.JSLINT_EQEQEQ, prefs
				.getBoolean(PreferenceConstants.JSLINT_EQEQEQ));
		map.put(PreferenceConstants.JSLINT_PLUSPLUS, prefs
				.getBoolean(PreferenceConstants.JSLINT_PLUSPLUS));
		map.put(PreferenceConstants.JSLINT_BITWISE, prefs
				.getBoolean(PreferenceConstants.JSLINT_BITWISE));
		map.put(PreferenceConstants.JSLINT_REGEXP, prefs
				.getBoolean(PreferenceConstants.JSLINT_REGEXP));
		map.put(PreferenceConstants.JSLINT_STRICT, prefs
				.getBoolean(PreferenceConstants.JSLINT_STRICT));
		map.put(PreferenceConstants.JSLINT_NEWCAP, prefs
				.getBoolean(PreferenceConstants.JSLINT_NEWCAP));
		map.put(PreferenceConstants.JSLINT_IMMED, prefs
				.getBoolean(PreferenceConstants.JSLINT_IMMED));

		if (prefs.getInt(PreferenceConstants.JSLINT_INDENT) > 0)
			map.put(PreferenceConstants.JSLINT_INDENT, prefs
					.getInt(PreferenceConstants.JSLINT_INDENT));
		if (prefs.getInt(PreferenceConstants.JSLINT_LINELEN) > 0)
			map.put(PreferenceConstants.JSLINT_LINELEN, prefs
					.getInt(PreferenceConstants.JSLINT_LINELEN));
		if (prefs.getInt(PreferenceConstants.JSLINT_ERRS) > 0)
			map.put(PreferenceConstants.JSLINT_ERRS, prefs
					.getInt(PreferenceConstants.JSLINT_ERRS));

		return map;
	}
}
