package org.andengine.limbo.mesh.dynamic;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.primitive.vbo.IMeshVertexBufferObject;
import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.opengl.shader.PositionColorShaderProgram;
import org.andengine.opengl.shader.PositionColorTextureCoordinatesShaderProgram;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import android.opengl.GLES20;


public class DynamicMesh extends Mesh {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	public IDynamicXYProvider xyProvider;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicMesh(final float pX, final float pY, final IDynamicXYProvider pXYProvider,
			final DrawMode pDrawMode, final VertexBufferObjectManager pVertexBufferObjectManager,
			final DrawType pDrawType) {
		this(pX, pY, pXYProvider, pDrawMode,
				new HighPerformanceDynamicMeshVertexBufferObject(pVertexBufferObjectManager,
						new float[pXYProvider.getNumberOfVerticesMax() * VERTEX_SIZE], pXYProvider.getNumberOfVerticesMax(),
						pDrawType, true, Mesh.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT)
				);
	}

	public DynamicMesh(final float pX, final float pY, final IDynamicXYProvider pXYProvider,
			final DrawMode pDrawMode, final IMeshVertexBufferObject pMeshVertexBufferObject) {
		super(pX, pY, 0, 0, pXYProvider.getNumberOfVerticesMax(), pDrawMode, pMeshVertexBufferObject);

		pXYProvider.calculateXY();
		this.xyProvider = pXYProvider;

		this.setBlendingEnabled(true);
		this.onUpdateVertices();
		this.onUpdateColor();

		this.mMeshVertexBufferObject.setDirtyOnHardware();

		this.setBlendingEnabled(true);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

		updateDynamics(pSecondsElapsed);
		xyProvider.setDirty(false);
	}

	protected void updateDynamics(float pSecondsElapsed) {
		xyProvider.onUpdate(pSecondsElapsed);

		if (xyProvider.isDirty()) {
			this.onUpdateVertices();
			this.setVertexCountToDraw(xyProvider.getNumberOfVertices());
		}
	}

	@Override
	@Deprecated
	public boolean contains(final float pX, final float pY) {
		return false;
	}
	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	protected void draw(GLState pGLState, Camera pCamera) {
		super.draw(pGLState, pCamera);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
