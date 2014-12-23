package org.andengine.limbo.mesh.dynamic.uv;

import org.andengine.limbo.mesh.FloatChain;
import org.andengine.limbo.mesh.UVMapperUtils;
import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.math.MathUtils;


public class DynamicUVMapperFan extends DynamicUVMapper {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================


	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicUVMapperFan(ITextureRegion pTextureRegion, IDynamicXYProvider pVertexProvider) {
		this(pTextureRegion, pVertexProvider, 1.0f, 1.0f, 0.5f, 0.5f);
	}

	public DynamicUVMapperFan(ITextureRegion pTextureRegion, IDynamicXYProvider pVertexProviderFan, float pTextureScaleU, float pTextureScaleV, float pAnchorU, float pAnchorV) {
		super(pTextureRegion, pVertexProviderFan);
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
		if (verticesToDraw == 0) {
			return;
		}
	
		mU.clear();
		mV.clear();
	
		float x0 = xs.getScaled(0);
		float y0 = ys.getScaled(0);
		mU.add(UVMapperUtils.mapUToRegion(mTextureRegion, 0.5f));
		mV.add(UVMapperUtils.mapVToRegion(mTextureRegion, 0.5f));
	
		float dx = 0;
		float dy = 0;
		float len = 0;
		for (int i = 1; i < verticesToDraw; i++) {
			dx = xs.getScaled(i) - x0;
			dy = ys.getScaled(i) - y0;
			len = MathUtils.length(dx, dy);
	
			mU.add(UVMapperUtils.mapUToRegion(mTextureRegion, 0.5f + dx/len*0.5f));
			mV.add(UVMapperUtils.mapVToRegion(mTextureRegion, 0.5f + dy/len*0.5f));
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
