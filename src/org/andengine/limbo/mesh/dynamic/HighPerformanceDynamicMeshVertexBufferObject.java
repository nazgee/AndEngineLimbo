package org.andengine.limbo.mesh.dynamic;

import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.primitive.vbo.HighPerformanceMeshVertexBufferObject;
import org.andengine.limbo.mesh.FloatChain;
import org.andengine.limbo.mesh.IXYProvider;
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

	@Override
	public void onUpdateVertices(Mesh pMesh) {
		DynamicMesh dynmesh = (DynamicMesh) pMesh;
		if (dynmesh.xyProvider == null) {
			return;
		}

		final float[] bufferData = this.mBufferData;

		final IXYProvider verticesProvider = dynmesh.xyProvider;
		final int verticesToDraw = verticesProvider.getNumberOfVertices();
		final FloatChain xs = verticesProvider.getX();
		final FloatChain ys = verticesProvider.getY();

		for (int i = 0; i < verticesToDraw; i++) {
			bufferData[i * DynamicMesh.VERTEX_SIZE + DynamicMesh.VERTEX_INDEX_X] = xs.getScaled(i);
			bufferData[i * DynamicMesh.VERTEX_SIZE + DynamicMesh.VERTEX_INDEX_Y] = ys.getScaled(i);
		}

		this.setDirtyOnHardware();
	}
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