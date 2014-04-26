package org.andengine.limbo.mesh.xy;

import org.andengine.limbo.mesh.dynamic.DynamicMesh;



public abstract class DynamicMeshXYProvider extends DynamicXYProvider implements IDynamicMeshXYProvider {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected DynamicMesh mDynamicMesh;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicMeshXYProvider(int pVertexCount) {
		super(pVertexCount);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	@Override
	public void setMesh(DynamicMesh pDynamicMesh) {
		this.mDynamicMesh = pDynamicMesh;
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