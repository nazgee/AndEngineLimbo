package org.andengine.limbo.mesh.dynamic.uv;

import org.andengine.limbo.mesh.FloatChain;
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
	protected float mAnchorU;
	protected float mAnchorV;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicUVMapperCutout(ITextureRegion pTextureRegion, IDynamicXYProvider pVertexProvider) {
		this(pTextureRegion, pVertexProvider, 1.0f, 1.0f, 0.5f, 0.5f);
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
	public void calculateUV() {
		final FloatChain xs = mVertexProvider.getX();
		final FloatChain ys = mVertexProvider.getY();
		final int verticesToDraw = mVertexProvider.getNumberOfVertices();

		mU.clear();
		mV.clear();

		for (int i = 0; i < verticesToDraw; i++) {
			mU.add(calculateU(xs.getScaled(i)));
			mV.add(calculateV(ys.getScaled(i)));
		}
	}
	
	protected float calculateU(float pX) {
		float w = mTextureRegion.getWidth() * mTextureScaleU;
		float u = (pX + mAnchorU * w) / w;
		return UVMapperUtils.mapUToRegion(mTextureRegion, u);
	}

	protected float calculateV(float pY) {
		float h = mTextureRegion.getHeight() * mTextureScaleV;
		float v = (-pY + mAnchorV * h) / h;
		return UVMapperUtils.mapVToRegion(mTextureRegion, v);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
