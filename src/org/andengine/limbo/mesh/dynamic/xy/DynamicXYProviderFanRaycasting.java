package org.andengine.limbo.mesh.dynamic.xy;

import java.util.ArrayList;

import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.limbo.physics.raycast.Ray;
import org.andengine.limbo.physics.raycast.RayNode;
import org.andengine.limbo.physics.raycast.RayNodeTagged;
import org.andengine.limbo.physics.raycast.RaycastListener;
import org.andengine.limbo.physics.raycast.initializer.IRaysInitializer;
import org.andengine.util.math.MathUtils;

import com.badlogic.gdx.math.Vector2;

public class DynamicXYProviderFanRaycasting extends DynamicXYProviderFan {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	RaycastListener mRaycastListener;
	ArrayList<RayNodeTagged> mRaysToDraw = new ArrayList<RayNodeTagged>();

	private IRaysInitializer mRaysInitializer;
	private final IRaycastQualityJustifier mRaycastQualityJustifier;

	private RayNodeTagged[] mRays;

	private Vector2 mTmpVector = new Vector2();
	private PhysicsWorld mWorld;
	/**
	 * Override this field to have rays scaled
	 */
	protected float mScale = 1.0f;
	private float mOriginX = 0;
	private float mOriginY = 0;
	private float mRotation = 0;
	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * 
	 * @param pRaysInitializer provides direction in which rays will be casted
	 * @param pRaycastListener provides logic to ignore collision of rays with certain fixtures
	 * @param pPixelToMeter
	 * @param pMaxRaysAngleDeviation maximum angle of polyline formed from tips of rays(forwarded to {@link RaycastQualityJustifier})
	 * @param pMaxRaysOffset maximum distance (in pixels) between tips of rays(forwarded to {@link RaycastQualityJustifier})
	 */
	public DynamicXYProviderFanRaycasting(IRaysInitializer pRaysInitializer, RaycastListener pRaycastListener, float pPixelToMeter, float pMaxRaysAngleDeviation, float pMaxRaysOffset) {
		this(pRaysInitializer, pRaycastListener, pPixelToMeter, new RaycastQualityJustifier(pMaxRaysAngleDeviation, pMaxRaysOffset, pPixelToMeter));
	}

	public DynamicXYProviderFanRaycasting(IRaysInitializer pRaysInitializer, RaycastListener pRaycastListener, float pPixelToMeter, IRaycastQualityJustifier pRaycastQualityJustifier) {
		super(pRaysInitializer.getRaysNumber());

		this.mRaysInitializer = pRaysInitializer;
		this.mRaycastQualityJustifier = pRaycastQualityJustifier;
		this.mRaycastListener = pRaycastListener;

		this.mRays = this.mRaysInitializer.populateRays();
		this.mRaysInitializer.initializeRays(mRays);

		this.mX.setScale(pPixelToMeter);
		this.mY.setScale(pPixelToMeter);
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public PhysicsWorld getPhysicsWorld() {
		return mWorld;
	}

	public void setPhysicsWorld(PhysicsWorld mWorld) {
		this.mWorld = mWorld;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (mWorld == null) {
			return;
		}

		setDirty(true);
		raycast(mWorld);

		super.onUpdate(pSecondsElapsed);
	}

	public void calculateXY() {
		if (mRaysToDraw == null) {
			// might get here when called from base class
			return;
		}

		// vertices = rays - hub
		final int numberOfVertsToDraw = mRaysToDraw.size();
		mX.clear();
		mY.clear();

		if (numberOfVertsToDraw >= 2) {
			// hub
			mX.add(0);
			mY.add(0);
	
			for (int i = 0; i < numberOfVertsToDraw; i++) {
				final Ray ray = mRaysToDraw.get(i);
				mX.add(ray.dir.x * ray.fraction);
				mY.add(ray.dir.y * ray.fraction);
			}

			makeFanLoop();
		}
	}


	// ===========================================================
	// Methods
	// ===========================================================

	public void setOffset(float pX, float pY) {
		this.mOriginX = pX;
		this.mOriginY = pY;
	}

	public float getOffsetX() {
		return mOriginX;
	}

	public float getOffsetY() {
		return mOriginY;
	}

	public void setRotation(float pRotation) {
		this.mRotation = pRotation;
	}

	public float getRotation() {
		return mRotation;
	}
	
	public void setScale(float pScale) {
		mScale = pScale;
	}

	public float getScale() {
		return mScale;
	}

	/**
	 * Generates lightcone/lightpoint/whatever with as few raycasts as possible
	 * @param pWorld
	 */
	public void raycast(final PhysicsWorld pWorld) {
		RayNodeTagged ray = null;
		RayNodeTagged[] raysAll = this.mRays;
		int rayCount = raysAll.length;

		mRaysToDraw.clear();

		// reset all rays and enable minimal subset of rays to cast
		mRaysInitializer.resetRays(raysAll);

		// initially cast only a small(for performance reasons) subset of enabled rays
		float originX = getOffsetX() / mX.getScale();
		float originY = getOffsetY() / mY.getScale();

		for (int i = 0; i < rayCount; i++) {
			RayNodeTagged rayCurrent = raysAll[i];

			if (rayCurrent.isEnabled()) {
				if (ray != null) {
					ray.insertRight(rayCurrent);
				}
				rayCurrent.cast(pWorld, mRaycastListener, mTmpVector.set(originX, originY), getRotation(), mScale);
				ray = rayCurrent;
			}
		}

		// enable and cast some more rays to make a smooth shape
		ray = raysAll[0];
		while (ray != null) {
			ray = (RayNodeTagged) makeSmoother(pWorld, ray, mRaysInitializer.isCircular());
		}

		ray = raysAll[0];
		while (ray != null) {
			mRaysToDraw.add(ray);
			ray = (RayNodeTagged) ray.next;
		}

		//Log.e("raycast", "rays to draw: " + mRaysToDraw.size());
	}

	/**
	 * If wavefront's shape is ugly it will get smoothed by casting more rays
	 * that were not casted yet, until we reach the limit or achieve smooth shape
	 * 
	 * @param pWorld
	 * @param pRayCurr
	 * @return ray from which we should start smoothing in next iteration
	 */
	private RayNode makeSmoother(final PhysicsWorld pWorld, RayNodeTagged pRayCurr, boolean circular) {

		RayNodeTagged rayOnLeft = (RayNodeTagged) pRayCurr.prev;
		RayNodeTagged rayOnRight = (RayNodeTagged) pRayCurr.next;
		int raysAvailableLeft;
		int raysAvailableRight;

		if (rayOnRight == null) {
			if (circular && (mRays.length - pRayCurr.tag) > 1) {
				// special case for radial lights (first ray is also last ray)
				//Log.e("makeSmoother", "circularizing #" + pRayCurr.tag);
				raysAvailableRight = mRays.length - pRayCurr.tag;
				rayOnRight = mRays[0];
			} else {
				return null;
			}
		} else {
			raysAvailableRight = rayOnRight.tag - pRayCurr.tag;	
		}

		if (rayOnLeft == null) {
			return rayOnRight;
		} else {
			raysAvailableLeft = pRayCurr.tag - rayOnLeft.tag;
		}

		if ((raysAvailableLeft <= 1) && (raysAvailableRight <= 1)) {
			// bail out, nothing can be done to improve smoothness anyway
			return rayOnRight;
		}

		/* calculate prev-curr-next angle, and see if it is acceptable */
		float distanceLeft = calculateDistance(pRayCurr, rayOnLeft);
		float distanceRight = calculateDistance(pRayCurr, rayOnRight);
		float angle = calculateAngle(rayOnLeft, pRayCurr, rayOnRight);

		boolean smoothingLeftPossible = raysAvailableLeft > 1;
		boolean smoothingRightPossible = raysAvailableRight > 1;
		boolean smoothingLeftWanted = smoothingLeftPossible && !mRaycastQualityJustifier.isGoodQuality(pRayCurr, angle, distanceLeft);
		boolean smoothingRightWanted = smoothingRightPossible && !mRaycastQualityJustifier.isGoodQuality(pRayCurr, angle, distanceRight);
		boolean smoothingLeftPreffered = distanceLeft > distanceRight;

		if (smoothingLeftWanted && (!smoothingRightWanted || smoothingLeftPreffered)) {
			//Log.e("makeSmoother", "L #" + pRayCurr.tag + "(L:"+raysAvailableLeft+" R:" +raysAvailableRight+") angle=" + angle + " left=" + distanceLeft + " right=" + distanceRight + " L=" + smoothingLeftWanted + " R=" + smoothingRightWanted);
			return makeSmootherLeft(pWorld, pRayCurr, raysAvailableLeft);
		} else if (smoothingRightWanted) {
			//Log.e("makeSmoother", "R #" + pRayCurr.tag + "(L:"+raysAvailableLeft+" R:" +raysAvailableRight+") angle=" + angle + " left=" + distanceLeft + " right=" + distanceRight + " L=" + smoothingLeftWanted + " R=" + smoothingRightWanted);
			return makeSmootherRight(pWorld, pRayCurr, raysAvailableRight);
		} else {
			//Log.e("makeSmoother", "X #" + pRayCurr.tag + "(L:"+raysAvailableLeft+" R:" +raysAvailableRight+") angle=" + angle + " left=" + distanceLeft + " right=" + distanceRight + " L=" + smoothingLeftWanted + " R=" + smoothingRightWanted);
			return rayOnRight;
		}
	}

	public RayNode makeSmootherRight(final PhysicsWorld pWorld, RayNodeTagged pRayCurr, int raysAvailableRight) {
		int indexOfNewRay = pRayCurr.tag + raysAvailableRight/2;
		RayNode rayOfHope = mRays[indexOfNewRay];
		pRayCurr.insertRight(rayOfHope);
		rayOfHope.setEnabled(true);
		rayOfHope.cast(pWorld, mRaycastListener, mTmpVector.set(getOffsetX() / mX.getScale(), getOffsetY() / mY.getScale()), getRotation(), mScale);

		return pRayCurr; //
	}

	public RayNode makeSmootherLeft(final PhysicsWorld pWorld, RayNodeTagged pRayCurr, int raysAvailableLeft) {
		int indexOfNewRay = pRayCurr.tag - raysAvailableLeft/2;
		RayNode rayOfHope = mRays[indexOfNewRay];
		pRayCurr.insertLeft(rayOfHope);
		rayOfHope.setEnabled(true);
		rayOfHope.cast(pWorld, mRaycastListener, mTmpVector.set(getOffsetX() / mX.getScale(), getOffsetY() / mY.getScale()), getRotation(), mScale);

		return rayOfHope; //
	}

	private static float calculateDistance(RayNode rayA, RayNode rayB) {	
		final float fractionA = rayA.fraction;
		final float ya = rayA.dir.y * fractionA;
		final float xa = rayA.dir.x * fractionA;

		final float fractionB = rayB.fraction;
		final float yb = rayB.dir.y * fractionB;
		final float xb = rayB.dir.x * fractionB;

		return MathUtils.distance(xa, ya, xb, yb);
	}

	private static float calculateAngle(RayNode pRayPrev, RayNode pRayCurr, RayNode pRayNext) {
		final float currFraction = pRayCurr.fraction;
		final float currY = pRayCurr.dir.y * currFraction;
		final float currX = pRayCurr.dir.x * currFraction;

		final float prevFraction = pRayPrev.fraction;
		final float prevY = pRayPrev.dir.y * prevFraction;
		final float prevX = pRayPrev.dir.x * prevFraction;

		final float nextFraction = pRayNext.fraction;
		final float nextY = pRayNext.dir.y * nextFraction;
		final float nextX = pRayNext.dir.x * nextFraction;

		final float prevAngle = MathUtils.radToDeg((float) Math.atan2(currY - prevY, currX - prevX));
		final float nextAngle = MathUtils.radToDeg((float) Math.atan2(nextY - currY, nextX - currX));
		
		float ret = Math.abs(prevAngle - nextAngle);

		return ret < 180 ? ret : 360 - ret;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	public static interface IRaycastQualityJustifier {
		boolean isGoodQuality(RayNode pRay, float pWavefrontAngle, float distance);
	}

	public static class RaycastQualityJustifier implements IRaycastQualityJustifier {
		private float mAngle;
		private float mDistance;
		private float mPixelToMeterRatio;

		public RaycastQualityJustifier(float pAngle, float pDistance, float pPixelToMeterRatio) {
			this.mAngle = pAngle;
			this.mDistance = pDistance;
			this.mPixelToMeterRatio = pPixelToMeterRatio;
		}

		@Override
		public boolean isGoodQuality(RayNode pRay, float angle, float distance) {
			return (distance < (mDistance/mPixelToMeterRatio)) && (angle < mAngle);
		}
	}
}