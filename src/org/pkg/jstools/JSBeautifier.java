package org.pkg.jstools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.preference.IPreferenceStore;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.pkg.jstools.preferences.PreferenceConstants;

/**
 * @author praveen.kailas@gmail.com
 *
 */
public class JSBeautifier {

	private Context context = null;
	private Scriptable scope = null;
	
	/**
	 * 
	 */
	public JSBeautifier() {
		
	}

	/**
	 * Beautifies the file in the active editor.
	 * 
	 * @param file the file to beautify.
	 */
	public void beautify(IFile file) {
		if (file == null) {
			return;
		}
		if (!file.getFileExtension().equalsIgnoreCase("js")) {
			return;
		}

		initContext();

		String contents = "";
		String options = "{}";
		try {
			contents = getFileContents(file.getContents());
			options = getPreferences().toString().replaceAll("=", ":");

			scope.put("contents", scope, contents);
			scope.put("opts", scope, options);
			
			context.evaluateString(scope, "result = js_beautify(contents, opts);",
					"JSBeautify", 1, null);
			
			Object result = scope.get("result", scope);
			if(result != null) {
				if(!file.isReadOnly() ) {
					ByteArrayInputStream newContents = new ByteArrayInputStream(result.toString().getBytes());
					file.setContents(newContents, true, true, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scope = null;
			context = null;
		}
	}

	/**
	 * 
	 */
	private void initContext() {
		try {
			context = Context.enter();
			context.setLanguageVersion(Context.VERSION_1_6);
			scope = context.initStandardObjects();
			String jsBeautifierCode = getFileContents(JSBeautifier.class
					.getResourceAsStream("beautify.js"));
			context.evaluateString(scope, jsBeautifierCode, "JSBeautify", 1, null);
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

	private Map<String, Object> getPreferences() {
		Map<String, Object> map = new HashMap<String, Object>();

		IPreferenceStore prefs = JSToolsActivator.getDefault()
				.getPreferenceStore();

		if (prefs.getInt(PreferenceConstants.JSB_INDENT) > 0)
			map.put(PreferenceConstants.JSB_INDENT, prefs
					.getInt(PreferenceConstants.JSB_INDENT));

		map.put(PreferenceConstants.JSB_PRESERVE_EMPTY, prefs
				.getBoolean(PreferenceConstants.JSB_PRESERVE_EMPTY));
		
		map.put(PreferenceConstants.JSB_DETECT_PACKERS, prefs
				.getBoolean(PreferenceConstants.JSB_DETECT_PACKERS));

		return map;
	}

}
