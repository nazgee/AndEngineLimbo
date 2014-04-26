package org.andengine.limbo.mesh.xy;

import org.andengine.limbo.mesh.dynamic.DynamicMesh;




public class DynamicMeshXYProviderProxy implements IDynamicMeshXYProvider {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private IDynamicXYProvider mDynamicXYProvider;
	private DynamicMesh mDynamicMesh;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicMeshXYProviderProxy(IDynamicXYProvider pDynamicXYProvider) {
		mDynamicXYProvider = pDynamicXYProvider;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public IDynamicXYProvider getProxy() {
		return mDynamicXYProvider;
	}

	public void setProxy(IDynamicXYProvider pDynamicXYProvider) {
		this.mDynamicXYProvider = pDynamicXYProvider;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onUpdate(float pSecondsElapsed) {
		setScale(mDynamicMesh.getScaleX());
		setOrigin(mDynamicMesh.getX(), mDynamicMesh.getY());
		setRotation(mDynamicMesh.getRotation());

		mDynamicXYProvider.onUpdate(pSecondsElapsed);
	}
	
	@Override
	public void reset() {
		mDynamicXYProvider.reset();
	}

	@Override
	public int getVertexCount() {
		return mDynamicXYProvider.getVertexCount();
	}

	@Override
	public boolean isDirty() {
		return mDynamicXYProvider.isDirty();
	}

	@Override
	public void setDirty(boolean pDirty) {
		mDynamicXYProvider.setDirty(pDirty);
	}

	@Override
	public float getX(int i) {
		return mDynamicXYProvider.getX(i);
	}

	@Override
	public float getY(int i) {
		return mDynamicXYProvider.getY(i);
	}

	@Override
	public void setScale(float pScale) {
		this.mDynamicXYProvider.setScale(pScale);
	}
	
	@Override
	public float getScale() {
		return this.mDynamicXYProvider.getScale();
	}

	@Override
	public void setMesh(DynamicMesh pDynamicMesh) {
		this.mDynamicMesh = pDynamicMesh;
	}

	@Override
	public void setOrigin(float pX, float pY) {
		this.mDynamicXYProvider.setOrigin(pX, pY);
	}

	@Override
	public float getOriginX() {
		return this.mDynamicXYProvider.getOriginX();
	}

	@Override
	public float getOriginY() {
		return this.mDynamicXYProvider.getOriginY();
	}

	@Override
	public void setRotation(float pRotation) {
		this.mDynamicXYProvider.setRotation(pRotation);
	}

	@Override
	public float getRotation() {
		return this.mDynamicXYProvider.getRotation();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}