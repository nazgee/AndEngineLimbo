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
	private float mPixelToMeter;
	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * In most cases using this should be enough
	 * @param pRaysInitializer
	 */
	public DynamicXYProviderFanRaycasting(IRaysInitializer pRaysInitializer, RaycastListener pRaycastListener, float pPixelToMeter) {
		this(new DefaultQualityJustifier(), pRaysInitializer, pRaycastListener, pPixelToMeter);
	}

	/**
	 * Use when you are not satisfied with default quality resolution
	 * @param pWavefrontAngleQualityThreshold
	 * @param pRaysInitializer
	 */
	public DynamicXYProviderFanRaycasting(float pWavefrontAngleQualityThreshold, IRaysInitializer pRaysInitializer, RaycastListener pRaycastListener, float pPixelToMeter) {
		this(new DefaultQualityJustifier(pWavefrontAngleQualityThreshold), pRaysInitializer, pRaycastListener, pPixelToMeter);
	}

	public DynamicXYProviderFanRaycasting(IRaycastQualityJustifier pRaycastQualityJustifier, IRaysInitializer pRaysInitializer, RaycastListener pRaycastListener, float pPixelToMeter) {
		super(pRaysInitializer.getRaysNumber(), true);

		this.mRaysInitializer = pRaysInitializer;
		this.mRaycastQualityJustifier = pRaycastQualityJustifier;
		this.mRaycastListener = pRaycastListener;

		this.mRays = this.mRaysInitializer.populateRays();
		this.mRaysInitializer.initializeRays(mRays);
		this.mPixelToMeter = pPixelToMeter;
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
	public float getX(int i) {
		return super.getX(i) * mPixelToMeter;
	}

	@Override
	public float getY(int i) {
		return super.getY(i) * mPixelToMeter;
	}

	@Override
	public void setOrigin(float pX, float pY) {
		super.setOrigin(pX / mPixelToMeter, pY / mPixelToMeter);
	}
	
	@Override
	public float getOriginX() {
		return super.getOriginX() * mPixelToMeter;
	}
	
	@Override
	public float getOriginY() {
		return super.getOriginY() * mPixelToMeter;
	}

	@Override
	protected float getBorderVertexX(int i) {
		if (mRaysToDraw.size() == 0) {
			return 0; // XXX throw an exception?
		}
		Ray ray = mRaysToDraw.get(i);
		return ray.dir.x * ray.fraction;
	}

	@Override
	protected float getBorderVertexY(int i) {
		if (mRaysToDraw.size() == 0) {
			return 0; // XXX throw an exception?
		}
		Ray ray = mRaysToDraw.get(i);
		return ray.dir.y * ray.fraction;
	}

	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (mWorld == null) {
			return;
		}

		mIsDirty = true;
		raycast(mWorld);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * Generates lightcone with as few raycasts as possible
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
		for (int i = 0; i < rayCount; i++) {
			RayNodeTagged rayCurrent = raysAll[i];

			if (rayCurrent.isEnabled()) {
				if (ray != null) {
					ray.insertRight(rayCurrent);
				}
				rayCurrent.cast(pWorld, mRaycastListener, mTmpVector.set(super.getOriginX(), super.getOriginY()), getRotation(), getScale());
				ray = rayCurrent;
			}
		}

		// enable and cast some more rays to make a smooth shape
		ray = raysAll[0];
		while (ray != null) {
			ray = (RayNodeTagged) makeSmoother(pWorld, ray);
		}

		ray = raysAll[0];
		while (ray != null) {
			mRaysToDraw.add(ray);
			ray = (RayNodeTagged) ray.next;
		}

		setVertexCount(mRaysToDraw.size());
		//Log.e("count="+mVertexCount);
	}

	/**
	 * If wavefront's shape is ugly it will get smoothed by casting more rays
	 * that were not casted yet, until we reach the limit or achieve smooth shape
	 * 
	 * @param pWorld
	 * @param pRayCurr
	 * @return ray from which we should start smoothing in next iteration
	 */
	private RayNode makeSmoother(final PhysicsWorld pWorld, RayNodeTagged pRayCurr) {

		RayNodeTagged rayPrev = (RayNodeTagged) pRayCurr.prev;
		RayNodeTagged rayNext = (RayNodeTagged) pRayCurr.next;

		if (rayNext == null) {
			return null;
		}

		if (rayPrev == null) {
			return pRayCurr.next;
		}

		int raysAvailableLeft = pRayCurr.tag - rayPrev.tag;
		int raysAvailableRight = rayNext.tag - pRayCurr.tag;

		if ((raysAvailableLeft <= 1) && (raysAvailableRight <= 1)) {
			//Log.e("out of spare siblings #" + pRayCurr.weight);

			// bail out, nothing can be done to improve smoothness anyway
			return rayNext;
		}

		/* calculate prev-curr-next angle, and see if it is acceptable */
		float angle = calculateAngle(rayPrev, pRayCurr, rayNext);

		if (!mRaycastQualityJustifier.isGoodQuality(pRayCurr, angle)) {
			// need to sharpen it
			if (raysAvailableLeft > 1) {
				// try to fix it by inserting new ray in the middle
				int indexOfNewRay = rayPrev.tag + raysAvailableLeft/2;
				RayNode rayOfHope = mRays[indexOfNewRay];
				pRayCurr.insertLeft(rayOfHope);
				rayOfHope.setEnabled(true);
				rayOfHope.cast(pWorld, mRaycastListener, mTmpVector.set(super.getOriginX(), super.getOriginY()), getRotation(), getScale());

				//Log.e("sharpening left of #" + pRayCurr.weight + " with #" + rayOfHope.weight);
				return rayPrev; //
			} else {
				// try to fix it by inserting new ray in the middle
				int indexOfNewRay = pRayCurr.tag + raysAvailableRight/2;
				RayNode rayOfHope = mRays[indexOfNewRay];
				pRayCurr.insertRight(rayOfHope);
				rayOfHope.setEnabled(true);
				rayOfHope.cast(pWorld, mRaycastListener, mTmpVector.set(super.getOriginX(), super.getOriginY()), getRotation(), getScale());

				//Log.e("sharpening right of #" + pRayCurr.weight + " with #" + rayOfHope.weight);
				return pRayCurr; //
			}
		} else {
			// we are fine!
			//Log.e("we're fine #" + pRayCurr.weight);
			return rayNext;
		}
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
		return Math.abs(prevAngle - nextAngle);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	public static interface IRaycastQualityJustifier {
		boolean isGoodQuality(RayNode pRay, float pWavefrontAngle);
	}

	public static class DefaultQualityJustifier implements IRaycastQualityJustifier {
		/**
		 * Arbitrarily chosen angle that provides reasonably smooth light cone
		 * without too many casts 
		 */
		public static float DEFAULT_WAVEFRONT_ANGLE_THRESHOLD = 25f;

		private float mWavefrontAngleQualityThreshold;

		public DefaultQualityJustifier() {
			this(DEFAULT_WAVEFRONT_ANGLE_THRESHOLD);
		}

		public DefaultQualityJustifier(float pWavefrontAngleQualityThreshold) {
			this.mWavefrontAngleQualityThreshold = pWavefrontAngleQualityThreshold;
		}

		@Override
		public boolean isGoodQuality(RayNode pRay, float pWavefrontAngle) {
			return (pWavefrontAngle < mWavefrontAngleQualityThreshold);
		}
	}
}