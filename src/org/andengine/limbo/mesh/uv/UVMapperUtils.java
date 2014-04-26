package org.andengine.limbo.mesh.uv;

import org.andengine.opengl.texture.region.ITextureRegion;

public final class UVMapperUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	private static float mapVToRegionImpl(ITextureRegion region, float v) {
		float regionV1 = region.getV();
		float regionV2 = region.getV2();
		return regionV1 + v * (regionV2 - regionV1);
	}

	private static float mapUToRegionImpl(ITextureRegion region, float u) {
		float regionU1 = region.getU();
		float regionU2 = region.getU2();
		return regionU1 + u * (regionU2 - regionU1);
	}

	public static float mapUToRegion(ITextureRegion region, float u) {
		if (region.isRotated())
			return UVMapperUtils.mapVToRegionImpl(region, u);
		else
			return UVMapperUtils.mapUToRegionImpl(region, u);
	}

	public static float mapVToRegion(ITextureRegion region, float v) {
		if (region.isRotated())
			return UVMapperUtils.mapUToRegionImpl(region, v);
		else
			return UVMapperUtils.mapVToRegionImpl(region, v);
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
