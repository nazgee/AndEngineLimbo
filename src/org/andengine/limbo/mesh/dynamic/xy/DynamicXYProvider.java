package org.andengine.limbo.mesh.dynamic.xy;

import org.andengine.limbo.mesh.FloatChain;
import org.andengine.limbo.mesh.XYProvider;
import org.andengine.limbo.mesh.dynamic.xy.modifier.DynamicXYModifierList;
import org.andengine.limbo.mesh.dynamic.xy.modifier.IDynamicXYModifier;



public abstract class DynamicXYProvider extends XYProvider implements IDynamicXYProvider {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected boolean mIsDirty = false;
	protected DynamicXYModifierList mModifiers;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicXYProvider(int pVertexCount) {
		super(pVertexCount);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public int getNumberOfVerticesMax() {
		return this.mX.getCapacity();
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (isDirty()) {
			calculateXY();
		}

		if (mModifiers != null) {
			mModifiers.onUpdate(pSecondsElapsed);
		}
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
	private void allocateEntityModifiers() {
		this.mModifiers = new DynamicXYModifierList(this, 2);
	}

	public void registerModifier(final IDynamicXYModifier pModifier) {
		if (this.mModifiers == null) {
			this.allocateEntityModifiers();
		}
		this.mModifiers.add(pModifier);
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================


}