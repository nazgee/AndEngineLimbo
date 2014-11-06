package org.andengine.limbo.mesh;

public class FloatChain {
	protected int mCapacity;
	/**
	 * Holds number of valid entries- it incidentally is also the index of where the next entry will be addeds 
	 */
	protected int mIndex;
	protected final float[] mValues;
	private float mScale;

	public FloatChain(int pCapacity) {
		this.mValues = new float[pCapacity];
		this.mCapacity = pCapacity;
		this.mIndex = 0 ;
		this.mScale = 1.0f;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public float getScale() {
		return mScale;
	}

	public void setScale(float pScale) {
		this.mScale = pScale;
	}

	public float getScaled(final int pIndex) {
		return get(pIndex) * this.mScale;
	}

	public void offset(final float pOffset) {
		for (int i = this.mIndex - 1; i >= 0; i--) {
			this.mValues[i] += pOffset;
		}
	}

	public int getIndex() {
		return mIndex;
	}

	/**
	 * 
	 * @return Maximum number of entries this {@link FloatChain} can hold
	 */
	public int getCapacity() {
		return mCapacity;
	}

	/**
	 * 
	 * @return Number of valid entries this {@link FloatChain}
	 */
	public int getSize() {
		return mIndex;
	}

	/**
	 * Invalidate/delete all entries in this {@link FloatChain}
	 */
	public void clear() {
		mIndex = 0;
	}

	public float get(final int pIndex) {
		this.assertCapacity(pIndex);
		this.assertIndex(pIndex);

		return this.mValues[pIndex];
	}



	public void set(final int pIndex, final float pValue) {
		this.assertCapacity(pIndex);

		mIndex = Math.max(mIndex, pIndex+1);
		this.mValues[pIndex] = pValue;
	}

	public void add(final float pValue) {
		this.assertCapacity();

		this.mValues[this.mIndex] = pValue;
		this.mIndex++;
	}

	public void shift() {
		final int length = this.mCapacity - 1;
		System.arraycopy(this.mValues, 0, this.mValues, 1, length);
	}

	private void assertIndex(final int pIndex) {
		if (pIndex >= this.mIndex) {
			throw new IllegalStateException("This supplied pIndex: '" + pIndex + "' is exceeding the current index: '" + this.mIndex + "' of this " + this.getClass().getSimpleName() + "!");
		}
	}

	private void assertCapacity(final int pIndex) {
		if (pIndex >= this.mCapacity) {
			throw new IllegalStateException("This supplied pIndex: '" + pIndex + "' is exceeding the capacity: '" + this.mCapacity + "' of this " + this.getClass().getSimpleName() + "!");
		}
	}

	private void assertCapacity() {
		if (this.mIndex == this.mCapacity) {
			throw new IllegalStateException("This " + this.getClass().getSimpleName() + " has already reached its capacity (" + this.mCapacity + ") !");
		}
	}
}