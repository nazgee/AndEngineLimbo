package org.andengine.limbo.mesh.dynamic.uv;

import org.andengine.limbo.mesh.UVMapper;
import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.opengl.texture.region.ITextureRegion;

import android.util.Log;


public abstract class DynamicUVMapper extends UVMapper implements IDynamicUVMapper {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicUVMapper(ITextureRegion pTextureRegion, IDynamicXYProvider pDynamicXYProvider) {
		super(pTextureRegion, pDynamicXYProvider, pDynamicXYProvider.getNumberOfVerticesMax());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public int getNumberOfVerticesMax() {
		return this.mU.getCapacity();
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (isDirty()) {
			calculateUV();
		}
	}

	@Override
	public boolean isDirty() {
		return ((IDynamicXYProvider)mVertexProvider).isDirty();
	}

	@Override
	public void reset() {
	}

	@Override
	public void setDirty(boolean pDirty) {
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}