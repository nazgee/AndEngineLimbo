package org.andengine.limbo.opengl;

import android.util.Log;

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
		pTextureManager.loadTexture(this);
	}

	public ManagedRenderTexture(TextureManager pTextureManager, int pWidth, int pHeight, PixelFormat pPixelFormat,
			TextureOptions pTextureOptions) {
		super(pTextureManager, pWidth, pHeight, pPixelFormat, pTextureOptions);
		pTextureManager.loadTexture(this);
	}

	public ManagedRenderTexture(TextureManager pTextureManager, int pWidth, int pHeight, PixelFormat pPixelFormat) {
		super(pTextureManager, pWidth, pHeight, pPixelFormat);
		pTextureManager.loadTexture(this);
	}

	public ManagedRenderTexture(TextureManager pTextureManager, int pWidth, int pHeight, TextureOptions pTextureOptions) {
		super(pTextureManager, pWidth, pHeight, pTextureOptions);
		pTextureManager.loadTexture(this);
	}

	public ManagedRenderTexture(TextureManager pTextureManager, int pWidth, int pHeight) {
		super(pTextureManager, pWidth, pHeight);
		pTextureManager.loadTexture(this);
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
	@Override
	public void loadToHardware(GLState pGLState) throws IOException {
		if (isInitialized()) {
			destroy(pGLState);
		}
		super.loadToHardware(pGLState);
	}

	@Override
	public void begin(final GLState pGLState, final boolean pFlipX, final boolean pFlipY) {
		safeInit(pGLState);
		super.begin(pGLState, pFlipX, pFlipY);
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
			Log.v(getClass().getSimpleName(), "invoking init()");
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
