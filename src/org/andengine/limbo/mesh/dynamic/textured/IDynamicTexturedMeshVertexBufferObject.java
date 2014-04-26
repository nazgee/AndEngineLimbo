package org.andengine.limbo.mesh.dynamic.textured;

import org.andengine.entity.primitive.vbo.IMeshVertexBufferObject;

public interface IDynamicTexturedMeshVertexBufferObject extends IMeshVertexBufferObject {
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
	public void onUpdateColor(final DynamicTexturedMesh pMesh);
	public void onUpdateVertices(final DynamicTexturedMesh pMesh);
	public void onUpdateTextureCoordinates(final DynamicTexturedMesh pMesh);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}