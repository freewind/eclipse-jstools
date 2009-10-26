package com.pkg.jstools.preferences;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class TabFolderLayout extends Layout {
	protected Point computeSize(Composite composite, int wHint, int hHint,
			boolean flushCache) {
		if ((wHint != -1) && (hHint != -1)) {
			return new Point(wHint, hHint);
		}
		Control[] children = composite.getChildren();
		int count = children.length;
		int maxWidth = 0;
		int maxHeight = 0;
		for (int i = 0; i < count; ++i) {
			Control child = children[i];
			Point pt = child.computeSize(-1, -1, flushCache);
			maxWidth = Math.max(maxWidth, pt.x);
			maxHeight = Math.max(maxHeight, pt.y);
		}

		if (wHint != -1)
			maxWidth = wHint;
		if (hHint != -1) {
			maxHeight = hHint;
		}
		return new Point(maxWidth, maxHeight);
	}

	protected void layout(Composite composite, boolean flushCache) {
		Rectangle rect = composite.getClientArea();

		Control[] children = composite.getChildren();
		for (int i = 0; i < children.length; ++i)
			children[i].setBounds(rect);
	}
}