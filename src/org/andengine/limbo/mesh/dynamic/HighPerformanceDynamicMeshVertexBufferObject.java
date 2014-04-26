package org.andengine.limbo.mesh.dynamic;

import org.andengine.entity.primitive.vbo.HighPerformanceMeshVertexBufferObject;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;

/**
 * (c) 2012 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:46:51 - 28.03.2012
 */
public class HighPerformanceDynamicMeshVertexBufferObject extends HighPerformanceMeshVertexBufferObject {

	public HighPerformanceDynamicMeshVertexBufferObject(VertexBufferObjectManager pVertexBufferObjectManager, float[] pBufferData,
			int pVertexCount, DrawType pDrawType, boolean pAutoDispose, VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
		super(pVertexBufferObjectManager, pBufferData, pVertexCount, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
	}
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

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}