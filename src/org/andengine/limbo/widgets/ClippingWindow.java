package org.andengine.limbo.widgets;

import org.andengine.entity.clip.ClipEntity;

public class ClippingWindow extends Window {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	// ===========================================================
	// Constructors
	// ===========================================================
	public ClippingWindow(final float pWidth, final float pHeight) {
		super(new ClipEntity(pWidth/2, pHeight/2, pWidth, pHeight));
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
