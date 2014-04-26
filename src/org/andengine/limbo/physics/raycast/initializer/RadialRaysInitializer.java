package org.andengine.limbo.physics.raycast.initializer;

import org.andengine.limbo.physics.raycast.Ray;
import org.andengine.limbo.physics.raycast.RayNodeTagged;



public class RadialRaysInitializer extends RaysInitializer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private float mRadius;
	private int mStepSize;

	// ===========================================================
	// Constructors
	// ===========================================================
	public RadialRaysInitializer(float pRadius, int pStepSize, int pRaysCount) {
		super(pRaysCount);

		this.mStepSize = pStepSize;
		this.mRadius = pRadius;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void initializeRays(RayNodeTagged[] pRays) {
		int rayCount = pRays.length;

		for (int i = 0; i < rayCount; i++) {
			float rad = ((float)i) / (float)rayCount * (float)Math.PI * 2.0f;

			Ray ray = pRays[i];
			ray.fraction = 1;
			ray.dir.set((float)Math.sin(rad), (float)Math.cos(rad));
			ray.dir.mul(mRadius);
		}
	}

	@Override
	public void resetRays(RayNodeTagged[] pRays) {
		int rayCount = pRays.length;

		for (int i = 0; i < rayCount; i++) {
			RayNodeTagged ray = pRays[i];
			ray.reset();
			ray.setEnabled((i % mStepSize) == 0);
		}
		pRays[rayCount-2].setEnabled(true);
		pRays[rayCount-1].setEnabled(true);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}