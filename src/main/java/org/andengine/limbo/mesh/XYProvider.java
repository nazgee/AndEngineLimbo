package org.andengine.limbo.mesh;

import org.andengine.util.math.MathUtils;

import com.badlogic.gdx.math.Vector2;


public abstract class XYProvider implements IXYProvider{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected final FloatChain mX;
	protected final FloatChain mY;
	private final Vector2 mTmpVec2 = new Vector2();

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

	@Override
	public float calculateSegmenX(int from, int to) {
		return mX.get(to) - mX.get(from);
	}

	@Override
	public float calculateSegmenY(int from, int to) {
		return mY.get(to) - mY.get(from);
	}

	@Override
	public Vector2 calculateSegmentDir(int from, int to) {
		return mTmpVec2.set(calculateSegmenX(from, to), calculateSegmenY(from, to));
	}
	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}