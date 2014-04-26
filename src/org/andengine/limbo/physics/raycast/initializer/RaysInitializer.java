package org.andengine.limbo.physics.raycast.initializer;

import org.andengine.limbo.physics.raycast.RayNodeTagged;

public abstract class RaysInitializer implements IRaysInitializer{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private int mRaysCount;

	// ===========================================================
	// Constructors
	// ===========================================================
	public RaysInitializer(int pRaysCount) {
		super();
		mRaysCount = pRaysCount;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public RayNodeTagged[] populateRays() {
		RayNodeTagged[] rays = new RayNodeTagged[mRaysCount];
		
		for (int i = 0; i < (rays.length); i++) {
			rays[i] = new RayNodeTagged(i);
		}
		
		return rays;
	}

	@Override
	public int getRaysNumber() {
		return mRaysCount;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}