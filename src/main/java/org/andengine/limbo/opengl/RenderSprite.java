package org.andengine.limbo.opengl;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.ZIndexSorter;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.list.SmartList;

public class RenderSprite extends Sprite {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private final ManagedRenderTexture mRenderTexture;

	// ===========================================================
	// Constructors
	// ===========================================================

	//public RenderSprite(int width, int height, int textureWidht, int textureHeight, ManagedRenderTexture mRenderTexture, VertexBufferObjectManager vbom) {
	public RenderSprite(float x, float y, float width, float height, ManagedRenderTexture renderTexture, VertexBufferObjectManager vbom) {
		super(x, y, width, height, TextureRegionFactory.extractFromTexture(renderTexture), vbom);

		setFlippedVertical(true);
		mRenderTexture = (ManagedRenderTexture) getTextureRegion().getTexture();
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void onManagedDraw(GLState pGLState, Camera pCamera) {
		pGLState.pushModelViewGLMatrix();
		{
			this.onApplyTransformations(pGLState);
			pGLState.pushModelViewGLMatrix();
			mRenderTexture.begin(pGLState);
			pCamera.onApplySceneMatrix(pGLState);
			pGLState.popModelViewGLMatrix();

			final SmartList<IEntity> children = this.mChildren;
			if ((children != null) && this.mChildrenVisible) {
				if (this.mChildrenSortPending) {
					ZIndexSorter.getInstance().sort(this.mChildren);
					this.mChildrenSortPending = false;
				}


				preDrawChildren(pGLState, pCamera, children);
				drawChildren(pGLState, pCamera, children);
				postDrawChildren(pGLState, pCamera, children);
			}
			mRenderTexture.end(pGLState);
		}

		/* Draw self. */
		drawSelf(pGLState, pCamera);

		pGLState.popModelViewGLMatrix();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	protected void preDrawChildren(GLState pGLState, Camera pCamera, SmartList<IEntity> children) {
	}

	protected void drawChildren(GLState pGLState, Camera pCamera, SmartList<IEntity> children) {
		final int childCount = children.size();
		{
			for (int i = 0; i < childCount; i++) {
				children.get(i).onDraw(pGLState, pCamera);
			}
		}
	}

	protected void postDrawChildren(GLState pGLState, Camera pCamera, SmartList<IEntity> children) {
	}

	protected void drawSelf(GLState pGLState, Camera pCamera) {
		this.preDraw(pGLState, pCamera);
		this.draw(pGLState, pCamera);
		this.postDraw(pGLState, pCamera);
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}