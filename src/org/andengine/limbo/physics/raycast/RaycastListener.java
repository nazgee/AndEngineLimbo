package org.andengine.limbo.physics.raycast;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

public class RaycastListener implements RayCastCallback {
	// ===========================================================
	// Constants
	// ===========================================================
	public static final float RAYCAST_IGNORE_AND_CONTINUE = -1;
	public static final float RAYCAST_TERMINATE = 0;
	public static final float RAYCAST_ACKNOWLEDGE_AND_CONTINUE = 1;
	
	// ===========================================================
	// Fields
	// ===========================================================
	private Ray ray;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public Ray getRay() {
		return ray;
	}
	
	public void setRay(Ray ray) {
		this.ray = ray;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	/**Called for each fixture found in the query. You control how the 
	 * ray cast proceeds by returning a float: return -1: ignore this 
	 * fixture and continue return 0: terminate the ray cast return fraction: 
	 * clip the ray to this point return 1: don't clip the ray and continue. 
	 * The Vector2 instances passed to the callback will be reused for 
	 * future calls so make a copy of them!
	 */
	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
		if (fraction > 1) {
			return RAYCAST_TERMINATE;
		}

		if (isIgnored(fixture, point, normal, fraction)) {
			return RAYCAST_IGNORE_AND_CONTINUE;
		}

		ray.fraction = Math.min(fraction, ray.fraction);
		ray.hitFixture = fixture;
		ray.hitPoint.set(point);
		ray.hitNormal.set(normal);

		return ray.fraction;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	protected boolean isIgnored(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
		return !fixture.getBody().isActive();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================



}