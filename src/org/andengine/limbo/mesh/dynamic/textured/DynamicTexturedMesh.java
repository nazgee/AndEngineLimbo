package org.andengine.limbo.mesh.dynamic.textured;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.limbo.mesh.dynamic.DynamicMesh;
import org.andengine.limbo.mesh.dynamic.uv.IDynamicUVMapper;
import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.opengl.shader.PositionColorTextureCoordinatesShaderProgram;
import org.andengine.opengl.shader.constants.ShaderProgramConstants;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributesBuilder;
import org.andengine.util.adt.color.Color;

import android.opengl.GLES20;

public class DynamicTexturedMesh extends DynamicMesh implements ShaderProgramConstants {

	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTEX_INDEX_X = 0;
	public static final int VERTEX_INDEX_Y = DynamicTexturedMesh.VERTEX_INDEX_X + 1;
	public static final int COLOR_INDEX = DynamicTexturedMesh.VERTEX_INDEX_Y + 1;
	public static final int TEXTURECOORDINATES_INDEX_U = DynamicTexturedMesh.COLOR_INDEX + 1;
	public static final int TEXTURECOORDINATES_INDEX_V = DynamicTexturedMesh.TEXTURECOORDINATES_INDEX_U + 1;

	public static final int VERTEX_SIZE = 5;

	public static final VertexBufferObjectAttributes VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT = new VertexBufferObjectAttributesBuilder(3)
		.add(ATTRIBUTE_POSITION_LOCATION, ATTRIBUTE_POSITION, 2, GLES20.GL_FLOAT, false)
		.add(ATTRIBUTE_COLOR_LOCATION, ATTRIBUTE_COLOR, 4, GLES20.GL_UNSIGNED_BYTE, true)
		.add(ATTRIBUTE_TEXTURECOORDINATES_LOCATION, ATTRIBUTE_TEXTURECOORDINATES, 2, GLES20.GL_FLOAT, false)
		.build();

	// ===========================================================
	// Fields
	// ===========================================================
	protected ITextureRegion mTextureRegion;
	public IDynamicUVMapper uvMapper;

	// ===========================================================
	// Constructors
	// ===========================================================

	public DynamicTexturedMesh(final float pX, final float pY, final IDynamicXYProvider pXYProvider,
			final IDynamicUVMapper pUVMapper, final DrawMode pDrawMode, final ITextureRegion pTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {

		this(pX, pY, pXYProvider, pUVMapper, pDrawMode, pTextureRegion, 
				new HighPerformanceDynamicTexturedMeshVertexBufferObject(
						pVertexBufferObjectManager, pXYProvider.getNumberOfVerticesMax() * DynamicTexturedMesh.VERTEX_SIZE,
						pDrawType, true, DynamicTexturedMesh.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT));
	}

	public DynamicTexturedMesh(final float pX, final float pY, final IDynamicXYProvider pXYProvider,
			final IDynamicUVMapper pUVMapper, final DrawMode pDrawMode, final ITextureRegion pTextureRegion,
			final IDynamicTexturedMeshVertexBufferObject pMeshVertexBufferObject) {

		super(pX, pY, pXYProvider, pDrawMode, pMeshVertexBufferObject);
		setShaderProgram(PositionColorTextureCoordinatesShaderProgram.getInstance());

		pUVMapper.calculateUV();
		this.uvMapper = pUVMapper;
		this.mTextureRegion = pTextureRegion;

		if( pTextureRegion != null)
		{
			this.initBlendFunction(pTextureRegion);
			this.onUpdateTextureCoordinates();
		}

		setColor(Color.WHITE);

		this.onUpdateVertices();
		this.onUpdateTextureCoordinates();
		this.onUpdateColor();

		this.mMeshVertexBufferObject.setDirtyOnHardware();

	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public ITextureRegion getTextureRegion() {
		return this.mTextureRegion;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public IDynamicTexturedMeshVertexBufferObject getDynamicTexturedMeshVertexBufferObject() {
		return (IDynamicTexturedMeshVertexBufferObject) getVertexBufferObject();
	}

	@Override
	public void reset() {
		super.reset();
		this.initBlendFunction(this.getTextureRegion().getTexture());
	}

	@Override
	protected void preDraw(final GLState pGLState, final Camera pCamera) {
		super.preDraw(pGLState, pCamera);

		this.mTextureRegion.getTexture().bind(pGLState);
	}

	@Override
	protected void onUpdateColor() {
		getDynamicTexturedMeshVertexBufferObject().onUpdateColor(this);
	}

	@Override
	protected void onUpdateVertices() {
		getDynamicTexturedMeshVertexBufferObject().onUpdateVertices(this);
	}

	protected void onUpdateTextureCoordinates() {
		getDynamicTexturedMeshVertexBufferObject().onUpdateTextureCoordinates(this);
	}

	@Override
	public boolean isCulled(Camera pCamera) {
		return false;
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

		uvMapper.setDirty(false);
	}

	@Override
	protected void updateDynamics(float pSecondsElapsed) {
		super.updateDynamics(pSecondsElapsed);

		uvMapper.onUpdate(pSecondsElapsed);
		if (uvMapper.isDirty()) {
			onUpdateTextureCoordinates();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
