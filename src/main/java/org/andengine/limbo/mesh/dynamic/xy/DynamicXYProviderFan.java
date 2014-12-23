package org.andengine.limbo.mesh.dynamic.xy;



public abstract class DynamicXYProviderFan extends DynamicXYProvider {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * Creates {@link DynamicXYProvider} with given number of border vertices. Additional +1 HUB vertex will be added.
	 * @param pVertexCount number of border vertices.
	 */
	public DynamicXYProviderFan(int pVertexCount) {
		// hub + vertices
		super(1 + pVertexCount + 1);
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void closeLoop() {
		int toDraw = getNumberOfVertices();
		if (toDraw >= (1+2)) {
			mX.set(toDraw, mX.get(1));
			mY.set(toDraw, mY.get(1));
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}