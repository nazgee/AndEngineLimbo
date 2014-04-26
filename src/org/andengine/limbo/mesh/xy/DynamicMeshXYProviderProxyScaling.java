package org.andengine.limbo.mesh.xy;





public class DynamicMeshXYProviderProxyScaling extends DynamicMeshXYProviderProxy {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private float mScaling = 1;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicMeshXYProviderProxyScaling(IDynamicXYProvider pDynamicXYProvider, final float pScale) {
		super(pDynamicXYProvider);
		setPixelToMeterRatio(pScale);
	}

	public float getPixelToMeterRatio() {
		return mScaling;
	}

	public void setPixelToMeterRatio(float mPixelToMeterRatio) {
		this.mScaling = mPixelToMeterRatio;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public float getX(int i) {
		return super.getX(i) * mScaling;
	}

	@Override
	public float getY(int i) {
		return super.getY(i) * mScaling;
	}

	@Override
	public void setOrigin(float pX, float pY) {
		super.setOrigin(pX / mScaling, pY / mScaling);
	}

	@Override
	public float getOriginX() {
		return super.getOriginX() * mScaling;
	}

	@Override
	public float getOriginY() {
		return super.getOriginY() * mScaling;
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}