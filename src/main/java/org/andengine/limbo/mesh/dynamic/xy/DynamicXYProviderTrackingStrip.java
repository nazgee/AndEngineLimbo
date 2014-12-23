package org.andengine.limbo.mesh.dynamic.xy;

import org.andengine.entity.IEntity;
import org.andengine.util.math.MathUtils;



public class DynamicXYProviderTrackingStrip extends DynamicXYProviderStrip {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	float mFadeTime;
	float mAccumulator = 0;
	final IEntity mTargetEntity;
	final float mAnchorX1, mAnchorY1, mAnchorX2, mAnchorY2;
	protected float mSegmentLastOdd = 0;
	protected float mSegmentLastEven = 0;
	protected float mSegmentFirstOdd = 0;
	protected float mSegmentFirstEven = 0;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicXYProviderTrackingStrip(int pVertexCount, IEntity pTarget, float pFadeTime,
			float pAnchorX1, float pAnchorY1, float pAnchorX2, float pAnchorY2) {
		// hub + vertices
		super(0, MathUtils.distance(pAnchorX1, pAnchorY1, pAnchorX2, pAnchorY2), pVertexCount);
		this.mTargetEntity = pTarget;
		this.mFadeTime = pFadeTime;
		this.mAnchorX1 = pAnchorX1;
		this.mAnchorY1 = pAnchorY1;
		this.mAnchorX2 = pAnchorX2;
		this.mAnchorY2 = pAnchorY2;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void calculateXY() {
		float[] loc;
		loc = mTargetEntity.convertLocalCoordinatesToParentCoordinates(mAnchorX1, mAnchorY1);
		this.mX.set(0, loc[0]);
		this.mY.set(0, loc[1]);
		loc = mTargetEntity.convertLocalCoordinatesToParentCoordinates(mAnchorX2, mAnchorY2);
		this.mX.set(1, loc[0]);
		this.mY.set(1, loc[1]);

		if (this.mX.getSize() >= DynamicXYProviderStrip.MINIMUM_VERTICES) {
			int fistEven = 0;
			int fistOdd = 1;
			mSegmentFirstEven = calculateSegmenLength(fistEven, fistEven + DynamicXYProviderStrip.VERTICES_STEP);
			mSegmentFirstOdd = calculateSegmenLength(fistOdd, fistOdd + DynamicXYProviderStrip.VERTICES_STEP);
		}
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		this.mAccumulator += pSecondsElapsed;
		if (this.mAccumulator > mFadeTime) {
			if(this.mX.isFull()) {
				int lastOdd = mX.getSize() - 1;
				int lastEven = lastOdd - 1;
				float evenLast = calculateSegmenLength(lastEven, lastEven - DynamicXYProviderStrip.VERTICES_STEP);
				float oddLast = calculateSegmenLength(lastOdd, lastOdd - DynamicXYProviderStrip.VERTICES_STEP);
				mCalculatedLengthEven -= evenLast;
				mCalculatedLengthOdd -= oddLast;
			}
	
			if (this.mX.getSize() >= DynamicXYProviderStrip.MINIMUM_VERTICES) {
				mCalculatedLengthEven += mSegmentFirstEven;
				mCalculatedLengthOdd += mSegmentFirstOdd;
			}
	
			this.mX.shift();
			this.mY.shift();
			this.mX.shift();
			this.mY.shift();
			mSegmentFirstEven = 0;
			mSegmentFirstOdd = 0;
	
			{
				this.mAccumulator -= mFadeTime;
				int lastOdd = mX.getSize() - 1;
				int lastEven = lastOdd - 1;
				mSegmentLastOdd = calculateSegmenLength(lastOdd, lastOdd - DynamicXYProviderStrip.VERTICES_STEP);
				mSegmentLastEven = calculateSegmenLength(lastEven, lastEven - DynamicXYProviderStrip.VERTICES_STEP);
			}
		}
	
		setDirty(true);
		super.onUpdate(pSecondsElapsed);
	}
	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public float getLengthEven() {
		return mCalculatedLengthEven + mSegmentFirstEven - (this.mX.isFull() ? mSegmentLastEven * mAccumulator / mFadeTime : 0);
	}

	@Override
	public float getLengthOdd() {
		return mCalculatedLengthOdd + mSegmentFirstOdd - (this.mX.isFull() ? mSegmentLastOdd * mAccumulator / mFadeTime : 0);
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}