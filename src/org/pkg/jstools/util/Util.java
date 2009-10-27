package org.pkg.jstools.util;

import org.mozilla.javascript.Context;

/**
 * @author praveen.kailas@gmail.com
 *
 */
public final class Util {

	private Util() {
		//
	}
	
	public static Context getJSContext() {
		return Context.enter();
	}
	
}
