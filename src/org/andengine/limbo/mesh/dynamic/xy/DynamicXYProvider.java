package org.andengine.limbo.mesh.dynamic.xy;

import org.andengine.limbo.mesh.XYProvider;



public abstract class DynamicXYProvider extends XYProvider implements IDynamicXYProvider {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected boolean mIsDirty = false;
	private float mScale = 1;
	private float mOriginX = 0;
	private float mOriginY = 0;
	private float mRotation = 0;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicXYProvider(int pVertexCount) {
		this.mVertexCount = pVertexCount;
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
	// Getter & Setter
	// ===========================================================

	@Override
	public void onUpdate(float pSecondsElapsed) {
	}

	@Override
	public void reset() {
	}

	@Override
	public boolean isDirty() {
		return mIsDirty;
	}

	@Override
	public void setDirty(boolean pDirty) {
		mIsDirty = pDirty;
	}

	@Override
	public void setScale(float pScale) {
		this.mScale = pScale;
	}

	@Override
	public float getScale() {
		return mScale;
	}

	@Override
	public void setOrigin(float pX, float pY) {
		this.mOriginX = pX;
		this.mOriginY = pY;
	}

	@Override
	public float getOriginX() {
		return mOriginX;
	}

	@Override
	public float getOriginY() {
		return mOriginY;
	}

	@Override
	public void setRotation(float pRotation) {
		this.mRotation = pRotation;
	}

	@Override
	public float getRotation() {
		return mRotation;
	}
}