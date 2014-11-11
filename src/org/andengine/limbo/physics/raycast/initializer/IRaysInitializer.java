package org.andengine.limbo.physics.raycast.initializer;

import org.andengine.limbo.physics.raycast.RayNodeTagged;



public interface IRaysInitializer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	RayNodeTagged[] populateRays();
	void initializeRays(RayNodeTagged[] pRays);
	void resetRays(RayNodeTagged[] pRays);
	/**
	 * Maximum number of rays that can will be cast 
	 * @return
	 */
	int getRaysNumber();
	boolean isCircular();
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}