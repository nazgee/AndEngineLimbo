package org.andengine.limbo.mesh.dynamic.xy.modifier;

import org.andengine.limbo.mesh.FloatChain;
import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.util.modifier.BaseDurationModifier;
import org.andengine.util.modifier.IModifier;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;

public class SineDynamicXYModifier extends BaseDurationModifier<IDynamicXYProvider> implements IDynamicXYModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected float mOmega;
	protected float mFi;
	protected int mStep;
	private float mPeriods;
	private Vector2 mDirection;
	private int mOffset;

	// ===========================================================
	// Constructors
	// ===========================================================


	public SineDynamicXYModifier(float duration, float omega, float fi, float periods, Vector2 direction, int step, int offset) {
		super(duration);
		this.mOmega = omega;
		this.mFi = fi;
		this.mPeriods = periods;
		this.mDirection = direction;
		this.mStep = step;
		this.mOffset = offset;
	}

	public SineDynamicXYModifier(float duration, float omega, float fi, float periods, Vector2 direction, int step, int offset, final IDynamicXYModifierListener pEntityModifierListener) {
		super(duration, pEntityModifierListener);
		this.mOmega = omega;
		this.mFi = fi;
		this.mStep = step;
		this.mDirection = direction;
		this.mPeriods = periods;
		this.mOffset = offset;
	}

	protected SineDynamicXYModifier(SineDynamicXYModifier sineDynamicXYModifier) {
		super(sineDynamicXYModifier);
		this.mOmega = sineDynamicXYModifier.mOmega;
		this.mFi = sineDynamicXYModifier.mFi;
		this.mStep = sineDynamicXYModifier.mStep;
		this.mDirection = sineDynamicXYModifier.mDirection;
		this.mPeriods = sineDynamicXYModifier.mPeriods;
		this.mOffset = sineDynamicXYModifier.mOffset;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed, IDynamicXYProvider pItem) {
		final FloatChain xs = pItem.getX();
		final FloatChain ys = pItem.getY();
		int vertices = xs.getSize();

		final float TWO_PI = 2*(float)Math.PI;
		float t = getContinousTime();
		//Log.e("getContinousTime", "t=" + t);

		for (int i = mOffset; i < vertices; i += mStep) {

			float omega = TWO_PI * mOmega;
			float fi_vertex = TWO_PI * ((float)(i - mOffset) / (vertices - mOffset)) * mPeriods;
			float fi = TWO_PI * (mFi);

			final float sin = (float) Math.sin(omega * t + fi + fi_vertex);

			for (int j = 0; j < mStep; j++) {
				xs.set(i+j, xs.get(i+j) + mDirection.x * sin);
				ys.set(i+j, ys.get(i+j) + mDirection.y * sin);
			}
		}

		pItem.setDirty(true);
	}

	float accumulator = 0;
	protected float getContinousTime() {
		return accumulator + getSecondsElapsed();
		//return 1;
	}

	@Override
	public void reset() {
		accumulator += getSecondsElapsed();
		super.reset();
	}

	@Override
	protected void onManagedInitialize(IDynamicXYProvider pItem) {
	}

	@Override
	public IModifier<IDynamicXYProvider> deepCopy() throws org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException {
		return new SineDynamicXYModifier(this);
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
