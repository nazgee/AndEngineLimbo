package org.andengine.limbo.physics.raycast;

import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Ray {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	public float fraction;
	public Vector2 dir = new Vector2();
	public Fixture hitFixture;
	public Vector2 hitPoint = new Vector2();
	public Vector2 hitNormal = new Vector2();

	private boolean mIsEnabled = true;
	private Vector2 mCastTarget = new Vector2();

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
	public float cast(final PhysicsWorld pWorld, final RaycastListener pRayCastCallback, final Vector2 pCastOrigin, final float pCastRotation) {
		return cast(pWorld, pRayCastCallback, pCastOrigin, pCastRotation, 1);
	}
	
	public float cast(final PhysicsWorld pWorld, final RaycastListener pRayCastCallback, final Vector2 pCastOrigin, final float pCastRotation, final float pDirectionScale) {
		if (mIsEnabled) {
			pRayCastCallback.setRay(this);
			mCastTarget.set(dir).mul(pDirectionScale).rotate(-pCastRotation).add(pCastOrigin);
			pWorld.rayCast(pRayCastCallback, pCastOrigin, mCastTarget);
		}
		// this gets set in RayCastCallback
		return fraction;
	}
	
	public void reset() {
		fraction = 1;
	}
	
	public boolean isEnabled() {
		return mIsEnabled;
	}
	
	public void setEnabled(boolean pIsEnabled) {
		this.mIsEnabled = pIsEnabled;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}