package org.andengine.limbo.widgets.swing;

import org.andengine.entity.sprite.NineSliceSprite;
import org.andengine.limbo.utils.positioner.PositionerSceneRelative;
import org.andengine.opengl.shader.PositionColorTextureCoordinatesShaderProgram;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * {@link Swing} that creates a sprite on top of it's window.
 * 
 * TODO: pass zindex ass constructor's parameter, so we can also have face hidden below the window, when zindex<0 is passed
 * 
 * (c) 2013 Michal Stawinski (nazgee)
 *
 * @author Michal Stawinski
 * @since 20:31:01 - 13.05.2013
 */
public abstract class SwingSprite<T> extends Swing<T> {
	private static final int ZINDEX_FACE = 1;
	// ===========================================================
	// Constants
	// ===========================================================
	protected final NineSliceSprite mFace;

	// ===========================================================
	// Fields
	// ===========================================================
//	ClipEntity mActiveArea
	// ===========================================================
	// Constructors
	// ===========================================================
	public SwingSprite(final float pX, final float pY, final float pWidth, final float pHeight, final ITextureRegion pTextureRegion, final float pInsetLeft, final float pInsetTop, final float pInsetRight, final float pInsetBottom,
			eAnimationDirection pAnimationOutDirection, eAnimationDirection pAnimationInDirection, float pAnimationTimeOut, final float pAnimationTimeIn,
			final IEaseFunction pEasingOut, final IEaseFunction pEasingIn, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pInsetLeft, pInsetTop, pInsetRight, pInsetBottom,
				pAnimationOutDirection, pAnimationInDirection, pAnimationTimeOut, pAnimationTimeIn,
				pEasingOut, pEasingIn, pVertexBufferObjectManager);
		setPosition(pX, pY);

		{
			this.mFace = new NineSliceSprite(pX, pY, pWidth, pHeight, pTextureRegion, pInsetLeft, pInsetTop, pInsetRight, pInsetBottom, pVertexBufferObjectManager, PositionColorTextureCoordinatesShaderProgram.getInstance());
			getWindow().attachChild(this.mFace);
			PositionerSceneRelative.getInstance().center(this, this.mFace);
			this.mFace.setZIndex(ZINDEX_FACE);
		}

		getWindow().sortChildren(false);
	}

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
