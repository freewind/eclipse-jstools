package com.pkg.jstools.util;

import org.mozilla.javascript.Context;

/**
 * @author praveenkumar.g
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
