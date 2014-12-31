package org.andengine.limbo.opengl;

import org.andengine.opengl.texture.ITextureStateListener;
import org.andengine.opengl.texture.PixelFormat;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.render.RenderTexture;
import org.andengine.opengl.util.GLState;

import java.io.IOException;

public class ManagedRenderTexture extends RenderTexture {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public ManagedRenderTexture(TextureManager pTextureManager, int pWidth, int pHeight, PixelFormat pPixelFormat,
			TextureOptions pTextureOptions, ITextureStateListener pTextureStateListener) {
		super(pTextureManager, pWidth, pHeight, pPixelFormat, pTextureOptions, pTextureStateListener);
	}

	public ManagedRenderTexture(TextureManager pTextureManager, int pWidth, int pHeight, PixelFormat pPixelFormat,
			TextureOptions pTextureOptions) {
		super(pTextureManager, pWidth, pHeight, pPixelFormat, pTextureOptions);
	}

	public ManagedRenderTexture(TextureManager pTextureManager, int pWidth, int pHeight, PixelFormat pPixelFormat) {
		super(pTextureManager, pWidth, pHeight, pPixelFormat);
	}

	public ManagedRenderTexture(TextureManager pTextureManager, int pWidth, int pHeight, TextureOptions pTextureOptions) {
		super(pTextureManager, pWidth, pHeight, pTextureOptions);
	}

	public ManagedRenderTexture(TextureManager pTextureManager, int pWidth, int pHeight) {
		super(pTextureManager, pWidth, pHeight);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/**
	 * This variant of {@link RenderTexture#loadToHardware(GLState)} assures that this {@link ManagedRenderTexture} 
	 * is correctly initialized when {@link ManagedRenderTexture#safeInit(GLState)} is called
	 */
	public void loadToHardware(GLState pGLState) throws IOException {
		if (isInitialized()) {
			destroy(pGLState);
		}
		super.loadToHardware(pGLState);
	}

	@Override
	public void begin(final GLState pGLState, final boolean pFlipX, final boolean pFlipY, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		safeInit(pGLState);
		super.begin(pGLState, pFlipX, pFlipY, pRed, pGreen, pBlue, pAlpha);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * calls {@link RenderTexture#init(GLState)} only if required
	 * @param pGLState
	 */
	protected void safeInit(final GLState pGLState) {
		if (!isInitialized()) {
			init(pGLState);
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
