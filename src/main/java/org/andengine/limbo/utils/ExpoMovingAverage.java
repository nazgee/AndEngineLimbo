package org.andengine.limbo.utils;

public class ExpoMovingAverage {
	float mAccumulator;
	float mAlpha;
	float mOneMinusAlpha;

	public ExpoMovingAverage(final float pAlpha) {
		mAccumulator = 0;
		mAlpha = pAlpha;
		mOneMinusAlpha = 1.0f - mAlpha;
	}

	public ExpoMovingAverage(final float pInitialValue, final float pAlpha) {
		mAccumulator = pInitialValue;
		mAlpha = pAlpha;
		mOneMinusAlpha = 1.0f - mAlpha;
	}

	public float accumulate(final float pValue) {
		mAccumulator = (mAlpha * pValue) + mOneMinusAlpha * mAccumulator;
		return mAccumulator;
	}

	public float getValue() {
		return mAccumulator;
	}

	public void setValue(final float pValue) {
		mAccumulator = pValue;
	}
}
