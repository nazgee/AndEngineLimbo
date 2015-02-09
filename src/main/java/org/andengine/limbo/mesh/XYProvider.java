package org.andengine.limbo.mesh;

import org.andengine.util.math.MathUtils;


public abstract class XYProvider implements IXYProvider{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected final FloatChain mX;
	protected final FloatChain mY;

	// ===========================================================
	// Constructors
	// ===========================================================
	public XYProvider(int pVertexCount) {
		this.mX = new FloatChain(pVertexCount);
		this.mY = new FloatChain(pVertexCount);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public int getNumberOfVertices() {
		return this.mX.getSize();
	}

	@Override
	public FloatChain getX() {
		return this.mX;
	}

	@Override
	public FloatChain getY() {
		return this.mY;
	}

	@Override
	public float calculateSegmenLength(int from, int to) {
		return MathUtils.distance(mX.get(from), mY.get(from), mX.get(to), mY.get(to));
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}