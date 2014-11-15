package org.andengine.limbo.mesh.dynamic.color;

import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.util.adt.color.ColorUtils;


public class DynamicColorProviderFadeout extends DynamicColorProvider {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicColorProviderFadeout(IDynamicXYProvider xyProvider) {
		super(xyProvider);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	
	@Override
	public void calculateColors() {
		final int verticesToDraw = xyProvider.getNumberOfVertices();

		mColors.clear();

		int base = Float.floatToRawIntBits(mBaseColor);

		for (int i = 0; i < verticesToDraw; i++) {
			float vertexAlpha = 1.0f - (float)i/(float)verticesToDraw;
			mColors.add(
					ColorUtils.convertRGBAToABGRPackedFloat(
							ColorUtils.extractRedFromABGRPackedInt(base),
							ColorUtils.extractGreenFromABGRPackedInt(base),
							ColorUtils.extractBlueFromABGRPackedInt(base),
							ColorUtils.extractAlphaFromABGRPackedInt(base) * vertexAlpha
					));
		}
	}
	// ===========================================================
	// Methods
	// ===========================================================


	@Override
	public void reset() {
	}

	@Override
	public int getNumberOfVerticesMax() {
		return mColors.getCapacity();
	}

	@Override
	public void setBaseColor(float pBaseColor) {
		if (pBaseColor != mBaseColor) {
			setDirty(true);
		}
		super.setBaseColor(pBaseColor);
	}

	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}