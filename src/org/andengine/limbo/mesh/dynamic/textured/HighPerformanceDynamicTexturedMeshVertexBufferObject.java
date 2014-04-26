package org.andengine.limbo.mesh.dynamic.textured;

import org.andengine.entity.primitive.Mesh;
import org.andengine.limbo.mesh.dynamic.HighPerformanceDynamicMeshVertexBufferObject;
import org.andengine.limbo.mesh.uv.IUVMapper;
import org.andengine.limbo.mesh.xy.IXYProvider;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;

import android.util.Log;

public class HighPerformanceDynamicTexturedMeshVertexBufferObject extends HighPerformanceDynamicMeshVertexBufferObject implements IDynamicTexturedMeshVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public HighPerformanceDynamicTexturedMeshVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final int pCapacity, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
		super(pVertexBufferObjectManager, new float[pCapacity], pCapacity/DynamicTexturedMesh.VERTEX_SIZE, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
	}

	public HighPerformanceDynamicTexturedMeshVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final float[] pBufferData, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
		super(pVertexBufferObjectManager, pBufferData, pBufferData.length/DynamicTexturedMesh.VERTEX_SIZE, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onUpdateColor(DynamicTexturedMesh pMesh) {
		if (pMesh.xyProvider == null) {
			return;
		}

		final float[] bufferData = this.mBufferData;

		final float packedColor = pMesh.getColor().getABGRPackedFloat();
		int verticesCount = pMesh.xyProvider.getVertexCount();

		for (int i = 0; i < verticesCount; i++) {
			bufferData[i * DynamicTexturedMesh.VERTEX_SIZE + DynamicTexturedMesh.COLOR_INDEX] = packedColor;
		}

		this.setDirtyOnHardware();
	}

	@Override
	public void onUpdateVertices(DynamicTexturedMesh pMesh) {
		if (pMesh.xyProvider == null) {
			return;
		}

		final float[] bufferData = this.mBufferData;

		IXYProvider verticesProvider = pMesh.xyProvider;
		int verticesCount = verticesProvider.getVertexCount();

		for (int i = 0; i < verticesCount; i++) {
			bufferData[i * DynamicTexturedMesh.VERTEX_SIZE + DynamicTexturedMesh.VERTEX_INDEX_X] = verticesProvider.getX(i);
			bufferData[i * DynamicTexturedMesh.VERTEX_SIZE + DynamicTexturedMesh.VERTEX_INDEX_Y] = verticesProvider.getY(i);
		}

		this.setDirtyOnHardware();
	}

	@Override
	public void onUpdateTextureCoordinates(DynamicTexturedMesh pMesh) {
		if (pMesh.uvMapper == null) {
			return;
		}

		final float[] bufferData = this.mBufferData;

		IUVMapper verticesMapper = pMesh.uvMapper;
		int verticesCount = verticesMapper.getVertexCount();

		for (int i = 0; i < verticesCount; i++) {
			bufferData[i * DynamicTexturedMesh.VERTEX_SIZE + DynamicTexturedMesh.TEXTURECOORDINATES_INDEX_U] = verticesMapper.getU(i);
			bufferData[i * DynamicTexturedMesh.VERTEX_SIZE + DynamicTexturedMesh.TEXTURECOORDINATES_INDEX_V] = verticesMapper.getV(i);
		}

		this.setDirtyOnHardware();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
