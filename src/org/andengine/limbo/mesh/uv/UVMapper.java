package org.andengine.limbo.mesh.uv;

import org.andengine.limbo.mesh.xy.IXYProvider;
import org.andengine.opengl.texture.region.ITextureRegion;


abstract class UVMapper implements IUVMapper {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected final ITextureRegion mTextureRegion;
	protected final IXYProvider mVertexProvider;

	// ===========================================================
	// Constructors
	// ===========================================================
	public UVMapper(ITextureRegion pTextureRegion, IXYProvider pVertexProviderFan) {
		this.mTextureRegion = pTextureRegion;
		this.mVertexProvider = pVertexProviderFan;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public int getVertexCount() {
		return mVertexProvider.getVertexCount();
	}

	@Override
	public float getV(int i) {
		return calculateV(mVertexProvider.getY(i));
	}

	@Override
	public float getU(int i) {
		return calculateU(mVertexProvider.getX(i));
	}

	// ===========================================================
	// Methods
	// ===========================================================
	abstract protected float calculateU(float pX);
	abstract protected float calculateV(float pY);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}