package org.andengine.limbo.mesh.dynamic.textured;

import org.andengine.entity.primitive.Mesh;
import org.andengine.limbo.mesh.FloatChain;
import org.andengine.limbo.mesh.IUVMapper;
import org.andengine.limbo.mesh.IXYProvider;
import org.andengine.limbo.mesh.dynamic.HighPerformanceDynamicMeshVertexBufferObject;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;

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
	public HighPerformanceDynamicTexturedMeshVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final int pBufferSize, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
		super(pVertexBufferObjectManager, new float[pBufferSize], pBufferSize/DynamicTexturedMesh.VERTEX_SIZE, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
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
	public void onUpdateColor(final Mesh pMesh) {
		DynamicTexturedMesh dynmesh = (DynamicTexturedMesh) pMesh;
		final float[] bufferData = this.mBufferData;
		
		int verticesToDraw = dynmesh.colorProvider.getNumberOfVertices();
		//Log.e("onUpdateColor", "verticesToDraw=" + verticesToDraw );

		for (int i = 0; i < verticesToDraw; i++) {
			bufferData[(i * DynamicTexturedMesh.VERTEX_SIZE) + DynamicTexturedMesh.COLOR_INDEX] = dynmesh.colorProvider.getColors().get(i);
		}

		this.setDirtyOnHardware();
	}

	@Override
	public void onUpdateVertices(DynamicTexturedMesh pMesh) {
		if (pMesh.xyProvider == null) {
			return;
		}

		final float[] bufferData = this.mBufferData;

		final IXYProvider verticesProvider = pMesh.xyProvider;
		final int verticesToDraw = verticesProvider.getNumberOfVertices();
		final FloatChain xs = verticesProvider.getX();
		final FloatChain ys = verticesProvider.getY();

//		pMesh.detachChildren();
		for (int i = 0; i < verticesToDraw; i++) {
			bufferData[i * DynamicTexturedMesh.VERTEX_SIZE + DynamicTexturedMesh.VERTEX_INDEX_X] = xs.getScaled(i);
			bufferData[i * DynamicTexturedMesh.VERTEX_SIZE + DynamicTexturedMesh.VERTEX_INDEX_Y] = ys.getScaled(i);
//			Rectangle rect = new Rectangle(xs.getScaled(i), ys.getScaled(i), 5, 5, getVertexBufferObjectManager());
//			if (i == 0) {
//				rect.setColor(Color.GREEN);
//			} else if (i <= 3) {
//				rect.setColor(Color.RED);
//				rect.setScale(4 - i);
//			}
//			pMesh.attachChild(rect);
		}

		this.setDirtyOnHardware();
	}

	@Override
	public void onUpdateTextureCoordinates(DynamicTexturedMesh pMesh) {
		if (pMesh.uvMapper == null) {
			return;
		}

		final float[] bufferData = this.mBufferData;

		final IUVMapper verticesMapper = pMesh.uvMapper;
		final int verticesCount = verticesMapper.getNumberOfVertices();
		final FloatChain us = verticesMapper.getU();
		final FloatChain vs = verticesMapper.getV();

		for (int i = 0; i < verticesCount; i++) {
			bufferData[i * DynamicTexturedMesh.VERTEX_SIZE + DynamicTexturedMesh.TEXTURECOORDINATES_INDEX_U] = us.getScaled(i);
			bufferData[i * DynamicTexturedMesh.VERTEX_SIZE + DynamicTexturedMesh.TEXTURECOORDINATES_INDEX_V] = vs.getScaled(i);
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
