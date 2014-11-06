package org.andengine.limbo.mesh;

import org.andengine.opengl.texture.region.ITextureRegion;


public abstract class UVMapper implements IUVMapper {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected final ITextureRegion mTextureRegion;
	protected final IXYProvider mVertexProvider;
	protected final FloatChain mU;
	protected final FloatChain mV;

	// ===========================================================
	// Constructors
	// ===========================================================
	public UVMapper(ITextureRegion pTextureRegion, IXYProvider pXYProvider, int pVerticesNumber) {
		this.mTextureRegion = pTextureRegion;
		this.mVertexProvider = pXYProvider;
		this.mU = new FloatChain(pVerticesNumber);
		this.mV = new FloatChain(pVerticesNumber);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public int getNumberOfVertices() {
		return this.mU.getSize();
	}

	@Override
	public FloatChain getV() {
		//return calculateV(mVertexProvider.getY(i));
		return this.mV;
	}

	@Override
	public FloatChain getU() {
		//return calculateU(mVertexProvider.getX(i));
		return this.mU;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}