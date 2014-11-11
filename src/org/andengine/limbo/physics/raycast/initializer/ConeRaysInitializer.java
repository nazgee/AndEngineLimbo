package org.andengine.limbo.physics.raycast.initializer;

import org.andengine.limbo.physics.raycast.Ray;
import org.andengine.limbo.physics.raycast.RayNodeTagged;



public class ConeRaysInitializer extends RaysInitializer {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final int NONWAVEFRONT_VERTS = 2*2;

	// ===========================================================
	// Fields
	// ===========================================================
	private float mRange;
	private float mHalfOfSpread;
	private int mStepSize;

	// ===========================================================
	// Constructors
	// ===========================================================
	public ConeRaysInitializer(float pRange, float pHalfOfSpread, int pStepSize, int pRaysCount) {
		super(pRaysCount + ConeRaysInitializer.NONWAVEFRONT_VERTS);

		this.mRange = pRange;
		this.mHalfOfSpread = pHalfOfSpread;
		this.mStepSize = pStepSize;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void initializeRays(RayNodeTagged[] pRays) {
		int raysCount = pRays.length;

		// non-wavefront rays
		pRays[0].dir.set(0, -mHalfOfSpread * 0.3f);
		pRays[1].dir.set(mRange * 0.9f, -mHalfOfSpread/2);
		pRays[raysCount - 2].dir.set(mRange * 0.9f, mHalfOfSpread/2);
		pRays[raysCount - 1].dir.set(0, mHalfOfSpread * 0.3f);

		// wavefront rays
		for (int i = 0; i < raysCount - NONWAVEFRONT_VERTS; i++) {
			Ray ray = pRays[i+NONWAVEFRONT_VERTS/2];
			ray.fraction = 1;

			float regularWavefrontPositionRate = (float)i/(raysCount - NONWAVEFRONT_VERTS - 1);
			float sexyWafefrontPositionRate; // -1 to 1
			sexyWafefrontPositionRate = regularWavefrontPositionRate * 2 - 1;
			ray.dir.set(mRange, mHalfOfSpread/2 * sexyWafefrontPositionRate);
		}
	}

	@Override
	public void resetRays(RayNodeTagged[] rays) {
		int rayCount = rays.length;

		for (int i = 0; i < rayCount; i++) {
			RayNodeTagged ray = rays[i];
			ray.reset();
			ray.setEnabled(	(i < NONWAVEFRONT_VERTS/2) ||
							(i > rayCount - NONWAVEFRONT_VERTS/2 - 1) ||
							(i % (NONWAVEFRONT_VERTS/2 + mStepSize)) == 0);
		}
	}

	@Override
	public boolean isCircular() {
		return false;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}