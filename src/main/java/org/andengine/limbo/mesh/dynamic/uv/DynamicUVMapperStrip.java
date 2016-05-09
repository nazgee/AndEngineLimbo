package org.andengine.limbo.mesh.dynamic.uv;

import org.andengine.limbo.mesh.UVMapperUtils;
import org.andengine.limbo.mesh.dynamic.xy.DynamicXYProviderStrip;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.math.MathUtils;
import org.andengine.util.modifier.ease.IEaseFunction;


public class DynamicUVMapperStrip extends DynamicUVMapper {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	IEaseFunction mEasing;
	protected ISkinningPolicy mSkinningPolicy;
	private float mSkinningScale = 1.0f;
	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicUVMapperStrip(ITextureRegion pTextureRegion, DynamicXYProviderStrip pVertexProvider) {
		this(pTextureRegion, pVertexProvider, new SkinnerDistance());
	}

	public DynamicUVMapperStrip(ITextureRegion pTextureRegion, DynamicXYProviderStrip pVertexProvider, ISkinningPolicy pSkinningPolicy) {
		super(pTextureRegion, pVertexProvider);
		this.mSkinningPolicy = pSkinningPolicy;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void calculateUV() {
		final float scale = this.mSkinningScale; 
		float lenEven = 0;
		float lenOdd = 0;
		DynamicXYProviderStrip stripxy = (DynamicXYProviderStrip)mVertexProvider;

		this.mSkinningPolicy.prepareToCalculate(this, stripxy);

		for (int i = 0; i < stripxy.getNumberOfVertices(); i+=DynamicXYProviderStrip.VERTICES_STEP) {
			int even = i;
			int odd = i + 1;

			if (i >= DynamicXYProviderStrip.VERTICES_STEP) {
				lenEven += MathUtils.distance(
						stripxy.getX().get(even - DynamicXYProviderStrip.VERTICES_STEP), stripxy.getY().get(even - DynamicXYProviderStrip.VERTICES_STEP),
						stripxy.getX().get(even), stripxy.getY().get(even));
				lenOdd += MathUtils.distance(
						stripxy.getX().get(odd - DynamicXYProviderStrip.VERTICES_STEP), stripxy.getY().get(odd - DynamicXYProviderStrip.VERTICES_STEP),
						stripxy.getX().get(odd), stripxy.getY().get(odd));
			}

			if (mFlipU) {
				getU().set(even, UVMapperUtils.mapUToRegion(mTextureRegion, 1.0f - scale * this.mSkinningPolicy.calculateEvenU(i, lenEven)));
				getU().set(odd, UVMapperUtils.mapUToRegion(mTextureRegion, 1.0f - scale * this.mSkinningPolicy.calculateOddU(i, lenOdd)));
			} else {
				getU().set(even, UVMapperUtils.mapUToRegion(mTextureRegion, scale * this.mSkinningPolicy.calculateEvenU(i, lenEven)));
				getU().set(odd, UVMapperUtils.mapUToRegion(mTextureRegion, scale * this.mSkinningPolicy.calculateOddU(i, lenOdd)));
			}
			if (mFlipV) {
				getV().set(even, UVMapperUtils.mapVToRegion(mTextureRegion, 1.0f));
				getV().set(odd, UVMapperUtils.mapVToRegion(mTextureRegion, 0.0f));
			} else {
				getV().set(even, UVMapperUtils.mapVToRegion(mTextureRegion, 0.0f));
				getV().set(odd, UVMapperUtils.mapVToRegion(mTextureRegion, 1.0f));
			}
		}
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public float getSkinningScale() {
		return mSkinningScale;
	}

	public void setSkinningScale(float pSkinningScale) {
		this.mSkinningScale = pSkinningScale;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	public static interface ISkinningPolicy {
		void prepareToCalculate(DynamicUVMapperStrip stripUV, DynamicXYProviderStrip stripXY);
		float calculateEvenU(int vertex, float lengthSoFar);
		float calculateOddU(int vertex, float lengthSoFar);
	}

	public static class SkinnerDistance implements ISkinningPolicy {
		protected float mTotalLengthEven;
		protected float mTotalLengthOdd;
		protected float mMaxLength;

		public SkinnerDistance() {
			this(Float.MAX_VALUE);
		}

		public SkinnerDistance(float pMaxLength) {
			this.mMaxLength = pMaxLength;
		}

		int i = 0;
		@Override
		public void prepareToCalculate(DynamicUVMapperStrip stripUV, DynamicXYProviderStrip stripXY) {
			mTotalLengthEven = Math.min(mMaxLength, stripXY.getLengthEven());
			mTotalLengthOdd = Math.min(mMaxLength, stripXY.getLengthOdd());
		}

		public float calculateEvenU(int vertex, float lengthSoFar) {
			return lengthSoFar / mTotalLengthEven;
		}

		public float calculateOddU(int vertex, float lengthSoFar) {
			return lengthSoFar / mTotalLengthOdd;
		}
	}

	public static class SkinnerDistanceAveraged implements ISkinningPolicy {
		protected float mTotalLength;
		protected float mMaxLength;

		public SkinnerDistanceAveraged() {
			this(Float.MAX_VALUE);
		}

		public SkinnerDistanceAveraged(float pMaxLength) {
			this.mMaxLength = pMaxLength;
		}

		@Override
		public void prepareToCalculate(DynamicUVMapperStrip stripUV, DynamicXYProviderStrip stripXY) {
			mTotalLength = Math.min(mMaxLength, (stripXY.getLengthEven() + stripXY.getLengthEven()) / 2);
		}

		public float calculateEvenU(int vertex, float lengthSoFar) {
			return lengthSoFar / mTotalLength;
		}

		public float calculateOddU(int vertex, float lengthSoFar) {
			return lengthSoFar / mTotalLength;
		}
	}

	public static class SkinnerIndexed implements ISkinningPolicy {
		int mVertices;

		@Override
		public void prepareToCalculate(DynamicUVMapperStrip stripUV, DynamicXYProviderStrip stripXY) {
			mVertices = stripXY.getNumberOfVertices();
		}

		public float calculateEvenU(int vertex, float lengthSoFar) {
			return (float)vertex / (float)mVertices;
		}

		public float calculateOddU(int vertex, float lengthSoFar) {
			return (float)vertex / (float)mVertices;
		}
	}
}
