package com.pkg.jstools;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;

/**
 * @author praveenkumar.g
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
			options = getLintPreferences().toString();
			scope.put("contents", scope, contents);
			scope.put("opts", scope, options);
			context.evaluateString(scope, "results = JSLINT(contents);", "JSLint", 1, null);
			Scriptable lint = (Scriptable) scope.get("JSLINT", scope);
			NativeArray errors = (NativeArray) lint.get("errors", null);
			for (int i = 0; i < errors.getLength(); i++) {
				NativeObject error = (NativeObject) errors.get(i, null);
				Double lineNo = (Double) error.get("line", null);
				Object reason = error.get("reason", null);
				IMarker marker = file.createMarker("org.eclipse.core.resources.problemmarker");
				marker.setAttribute(IMarker.LINE_NUMBER, lineNo.intValue());
				marker.setAttribute(IMarker.MESSAGE, reason);
				marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			int i =0;
			while ((i=stream.read(readBytes)) > 0) {
				contents.append(new String(readBytes, 0, i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents.toString();
	}
	
	private Map<String,Boolean> getLintPreferences() {
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		
		return map;
	}
}
