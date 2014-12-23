package org.andengine.limbo.mesh.dynamic;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.primitive.vbo.IMeshVertexBufferObject;
import org.andengine.limbo.mesh.dynamic.color.IDynamicColorProvider;
import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class DynamicMesh extends Mesh {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	public IDynamicXYProvider xyProvider;
	public IDynamicColorProvider colorProvider;

	// ===========================================================
	// Constructors
	// ===========================================================
	public DynamicMesh(final float pX, final float pY, final IDynamicXYProvider pXYProvider, IDynamicColorProvider pColorProvider,
			final DrawMode pDrawMode, final VertexBufferObjectManager pVertexBufferObjectManager,
			final DrawType pDrawType) {
		this(pX, pY, pXYProvider, pColorProvider, pDrawMode,
				new HighPerformanceDynamicMeshVertexBufferObject(pVertexBufferObjectManager,
						new float[pXYProvider.getNumberOfVerticesMax() * VERTEX_SIZE], pXYProvider.getNumberOfVerticesMax(),
						pDrawType, true, Mesh.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT)
				);
	}

	public DynamicMesh(final float pX, final float pY, final IDynamicXYProvider pXYProvider, IDynamicColorProvider pColorProvider,
			final DrawMode pDrawMode, final IMeshVertexBufferObject pMeshVertexBufferObject) {
		super(pX, pY, 0, 0, pXYProvider.getNumberOfVerticesMax(), pDrawMode, pMeshVertexBufferObject);

		pXYProvider.calculateXY();
		this.xyProvider = pXYProvider;

		pColorProvider.setBaseColor(getColor().getABGRPackedFloat());
		pColorProvider.calculateColors();
		this.colorProvider = pColorProvider;

		this.setBlendingEnabled(true);
		this.onUpdateVertices();
		this.onUpdateColor();

		this.mMeshVertexBufferObject.setDirtyOnHardware();

		this.setBlendingEnabled(true);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getVertexSize() {
		return DynamicMesh.VERTEX_SIZE;
	}

	public int getVertexIndexX() {
		return DynamicMesh.VERTEX_INDEX_X;
	}

	public int getVertexIndexY() {
		return DynamicMesh.VERTEX_INDEX_Y;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onUpdateColor() {
		super.onUpdateColor();
	}

	@Override
	public void onUpdateVertices() {
		super.onUpdateVertices();
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

		updateDynamics(pSecondsElapsed);
		xyProvider.setDirty(false);
		colorProvider.setDirty(false);
	}

	protected void updateDynamics(float pSecondsElapsed) {
		colorProvider.setBaseColor(getColor().getABGRPackedFloat());

		// XXX add it to a list so it can be made more generic
		xyProvider.onUpdate(pSecondsElapsed);
		colorProvider.onUpdate(pSecondsElapsed);

		if (xyProvider.isDirty()) {
			this.onUpdateVertices();
			this.setVertexCountToDraw(xyProvider.getNumberOfVertices());
		}

		if (colorProvider.isDirty()) {
			this.onUpdateColor();
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
