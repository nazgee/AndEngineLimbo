package org.andengine.limbo.mesh;

import org.andengine.util.adt.color.Color;



public abstract class ColorProvider implements IColorProvider {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected float mBaseColor = Color.WHITE_ABGR_PACKED_FLOAT;
	protected FloatChain mColors;
	// ===========================================================
	// Constructors
	// ===========================================================
	public ColorProvider(int pVerticesCount) {
		mColors = new FloatChain(pVerticesCount);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public int getNumberOfVertices() {
		return this.mColors.getSize();
	}

	@Override
	public void setBaseColor(float pBaseColor) {
		this.mBaseColor = pBaseColor;
	}

	@Override
	public FloatChain getColors() {
		return mColors;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}