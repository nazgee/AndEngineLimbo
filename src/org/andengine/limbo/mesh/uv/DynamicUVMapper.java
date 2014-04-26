package org.andengine.limbo.mesh.uv;

import org.andengine.limbo.mesh.xy.IXYProvider;
import org.andengine.opengl.texture.region.ITextureRegion;


abstract class DynamicUVMapper extends UVMapper implements IDynamicUVMapper {
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
	public DynamicUVMapper(ITextureRegion pTextureRegion, IXYProvider pVertexProviderFan) {
		super(pTextureRegion, pVertexProviderFan);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
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

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}