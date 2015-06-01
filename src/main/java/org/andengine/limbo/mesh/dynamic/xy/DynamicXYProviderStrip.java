package org.andengine.limbo.mesh.dynamic.xy;


public class DynamicXYProviderStrip extends DynamicXYProvider {

	// ===========================================================
	// Constants
	// ===========================================================
	public static final int VERTICES_STEP = 2;
	public static final int MINIMUM_VERTICES = 4;

	// ===========================================================
	// Fields
	// ===========================================================
	protected float mLength;
	protected float mHeight;

	protected float mCalculatedLengthEven;
	protected float mCalculatedLengthOdd;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicXYProviderStrip(final float length, final float height, int pVertexCount) {
		super(pVertexCount);
		this.mLength = length;
		this.mHeight = height;

		if (pVertexCount %2 != 0) {
			throw new RuntimeException("Strip must have even number of vertices!");
		}
		if (pVertexCount < MINIMUM_VERTICES) {
			throw new RuntimeException("Strip must have at least " + MINIMUM_VERTICES + " vertices (" + pVertexCount + " were given)");
		}


		calculateXY();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public float getLength() {
		return mLength;
	}

	public float getHeight() {
		return mHeight;
	}

	public void setHeight(float height) {
		mHeight = height;
		setDirty(true);
	}

	public void setLength(float length) {
		mLength = length;
		setDirty(true);
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onUpdate(float pSecondsElapsed) {
		//Log.e("onUpdate", "elapsed=" + pSecondsElapsed);
		if (isDirty() || (mModifiers != null && !mModifiers.isEmpty())) {
			calculateXY();
		}

		if (mModifiers != null) {
			mModifiers.onUpdate(pSecondsElapsed);
		}
	}

	@Override
	public void calculateXY() {
		buildStrip(mLength, mHeight, getNumberOfVerticesMax());
		calculateLengths();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void calculateLengths() {
		int segments = getNumberOfVertices()/2 - 1;
		mCalculatedLengthEven = 0;
		mCalculatedLengthOdd = 0;
		for (int i = 0; i < segments; i++) {
			int even = i*VERTICES_STEP + 0;
			int odd = even + 1;
			mCalculatedLengthEven += calculateSegmenLength(even, even + VERTICES_STEP);
			mCalculatedLengthOdd += calculateSegmenLength(odd, odd + VERTICES_STEP);
		}
	}

	protected void buildStrip(float length, float height, int vertices) {
		if (length == 0 || height == 0)
			return;

		int segments = vertices/2 - 1;
		float segmentLength = length / (float)segments;
		float x = -length/2;
		float yEven = height/2;
		float yOdd = -yEven;

		mX.clear();
		mY.clear();

		mX.set(0, x);
		mY.set(0, yEven);
		mX.set(1, x);
		mY.set(1, yOdd);
		
		for (int i = 0; i < segments; i++) {
			x = x + segmentLength;
			int even = (i+1)*VERTICES_STEP;
			int odd = even + 1;

			mX.set(even, x);
			mY.set(even, yEven);
			mX.set(odd, x);
			mY.set(odd, yOdd);
		}
	}

	public float getLengthEven() {
		return mCalculatedLengthEven;
	}

	public float getLengthOdd() {
		return mCalculatedLengthOdd;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}