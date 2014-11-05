package org.andengine.limbo.mesh.dynamic.uv;

import org.andengine.limbo.mesh.UVMapperUtils;
import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.opengl.texture.region.ITextureRegion;


public class DynamicUVMapperCutout extends DynamicUVMapper {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected float mTextureScaleU;
	protected float mTextureScaleV;
	private final float mAnchorU;
	private final float mAnchorV;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicUVMapperCutout(ITextureRegion pTextureRegion, IDynamicXYProvider pVertexProviderFan) {
		this(pTextureRegion, pVertexProviderFan, 1, 1, 0.5f, 0.5f);
	}

	public DynamicUVMapperCutout(ITextureRegion pTextureRegion, IDynamicXYProvider pVertexProviderFan, float pTextureScaleU, float pTextureScaleV, float pAnchorU, float pAnchorV) {
		super(pTextureRegion, pVertexProviderFan);
		this.mTextureScaleU = pTextureScaleU;
		this.mTextureScaleV = pTextureScaleV;
		this.mAnchorU = pAnchorU;
		this.mAnchorV = pAnchorV;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected float calculateU(float pX) {
		float w = mTextureRegion.getWidth() * mTextureScaleU;
		float u = (pX + mAnchorU * w) / w;
		return UVMapperUtils.mapUToRegion(mTextureRegion, u);
	}

	@Override
	protected float calculateV(float pY) {
		float h = mTextureRegion.getHeight() * mTextureScaleV;
		float v = (-pY + mAnchorV * h) / h;
		return UVMapperUtils.mapVToRegion(mTextureRegion, v);
	}

	@Override
	public boolean isDirty() {
		return ((IDynamicXYProvider)mVertexProvider).isDirty();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
