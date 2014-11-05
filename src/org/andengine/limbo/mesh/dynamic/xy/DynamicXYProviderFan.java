package org.andengine.limbo.mesh.dynamic.xy;



public abstract class DynamicXYProviderFan extends DynamicXYProvider {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private final boolean mForceFanClosure;
	protected float mHubX = 0;
	protected float mHubY = 0;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicXYProviderFan(int pVertexCount) {
		this(pVertexCount, false);
	}

	public DynamicXYProviderFan(int pVertexCount, boolean pForceFanClosure) {
		super(pVertexCount);
		this.mForceFanClosure = pForceFanClosure;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	@Override
	public int getVertexCount() {
		// hub + vertices + closure
		return 1 + mVertexCount + (mForceFanClosure ? 1 : 0);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public float getX(int i) {
		if (i == 0) {
			return mHubX;
		} else {
			return getBorderVertexX(indexToVertex(i));
		}
	}

	@Override
	public float getY(int i) {
		if (i == 0) {
			return mHubY;
		} else {
			return getBorderVertexY(indexToVertex(i));
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	abstract protected float getBorderVertexX(int i);
	abstract protected float getBorderVertexY(int i);

	protected int indexToVertex(int i) {
		if (i <= 0) {
			throw new RuntimeException("bad vertex index (" + i + ")");
		}

		return ((i-1) % mVertexCount);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}