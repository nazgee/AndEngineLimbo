package org.andengine.limbo.mesh.dynamic.xy;

import org.andengine.entity.Entity;
import org.andengine.util.adt.color.Color;

public class DynamicXYProviderLine extends Entity {
	protected int mCapacity;
	protected int mIndex = 0;
	protected final float[] mXs;
	protected final float[] mYs;
	protected final Color[] mColors;

	public DynamicXYProviderLine(int pCapacity) {
		this.mXs = new float[pCapacity];
		this.mYs = new float[pCapacity];
		this.mColors = new Color[pCapacity];
		this.mCapacity = pCapacity;
		this.setColor(Color.GREEN);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void setDx(final float dX) {
		for (int i = this.mIndex - 1; i >= 0; i--) {
			this.mXs[i] += dX;
		}
	}

	public void setDy(final float dY) {
		for (int i = this.mIndex - 1; i >= 0; i--) {
			this.mYs[i] += dY;
		}
	}

	public void setDxDy(final float dX, final float dY) {
		for (int i = this.mIndex - 1; i >= 0; i--) {
			this.mXs[i] += dX;
			this.mYs[i] += dY;
		}
	}

	public int getIndex() {
		return mIndex;
	}

	public int getCapacity() {
		return mCapacity;
	}

	@Override
	public void setColor(Color pColor) {
		for (int i = 0; i < this.mCapacity; i++) {
			mColors[i] = new Color(pColor);
		}
		super.setColor(pColor);
	}

	public void setColor(final int pIndex, Color pColor) {
		mColors[pIndex] = new Color(pColor);
	}

	public Color getColor(final int pIndex) {
		return mColors[pIndex];
	}

	public float getX(final int pIndex) {
		this.assertCapacity(pIndex);

		return this.mXs[pIndex];
	}

	public float getY(final int pIndex) {
		this.assertCapacity(pIndex);

		return this.mYs[pIndex];
	}

	public void setX(final int pIndex, final float pX) {
		this.assertCapacity(pIndex);
		this.assertIndex(pIndex);

		this.mXs[pIndex] = pX;
	}

	public void setY(final int pIndex, final float pY) {
		this.assertCapacity(pIndex);
		this.assertIndex(pIndex);

		this.mYs[pIndex] = pY;
	}

	public void setXY(final int pIndex, final float pX, final float pY) {
		this.assertCapacity(pIndex);
		this.assertIndex(pIndex);

		this.mXs[pIndex] = pX;
		this.mYs[pIndex] = pY;
	}

	public void add(final float pX, final float pY) {
		this.assertCapacity();

		this.mXs[this.mIndex] = pX;
		this.mYs[this.mIndex] = pY;

		this.mIndex++;
	}

	public void shift() {
		final int length = this.mCapacity - 1;
		System.arraycopy(this.mXs, 0, this.mXs, 1, length);
		System.arraycopy(this.mYs, 0, this.mYs, 1, length);
	}

	private void assertIndex(final int pIndex) {
		if (pIndex >= this.mIndex - 1) {
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