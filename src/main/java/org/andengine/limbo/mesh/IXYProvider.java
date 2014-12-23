package org.andengine.limbo.mesh;

import com.badlogic.gdx.math.Vector2;

public interface IXYProvider {

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
	public void calculateXY();
	public FloatChain getX();
	public FloatChain getY();
	public int getNumberOfVertices();

	public float calculateSegmenLength(int from, int to);
	public float calculateSegmenX(int from, int to);
	public float calculateSegmenY(int from, int to);
	public Vector2 calculateSegmentDir(int from, int to);
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
