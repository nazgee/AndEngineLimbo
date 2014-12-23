package org.andengine.limbo.mesh.dynamic.color;

import org.andengine.limbo.mesh.ColorProvider;
import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;


public class DynamicColorProvider extends ColorProvider implements IDynamicColorProvider {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected boolean mIsDirty = false;
	protected IDynamicXYProvider xyProvider;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicColorProvider(IDynamicXYProvider xyProvider) {
		super(xyProvider.getNumberOfVerticesMax());
		this.xyProvider = xyProvider;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void calculateColors() {
		final int verticesToDraw = xyProvider.getNumberOfVertices();

		mColors.clear();

		for (int i = 0; i < verticesToDraw; i++) {
			mColors.add(mBaseColor);
		}
	}
	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public void onUpdate(float pSecondsElapsed) {
		boolean verticesNumberChanged = xyProvider.getNumberOfVertices() != mColors.getSize();
		if (isDirty() || verticesNumberChanged) {
			calculateColors();
			mIsDirty = true;
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

	@Override
	public int getNumberOfVerticesMax() {
		return mColors.getCapacity();
	}

	@Override
	public void setBaseColor(float pBaseColor) {
		if (pBaseColor != mBaseColor) {
			setDirty(true);
		}
		super.setBaseColor(pBaseColor);
	}

	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}