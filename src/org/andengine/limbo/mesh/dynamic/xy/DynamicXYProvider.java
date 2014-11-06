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

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicXYProvider(int pVertexCount) {
		super(pVertexCount);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public int getNumberOfVerticesMax() {
		return this.mX.getCapacity();
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (isDirty()) {
			calculateXY();
		}
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
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================


}