package org.andengine.limbo.utils;

import java.io.IOException;
import java.util.HashMap;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.map.Library;
import org.andengine.util.texturepack.TexturePack;
import org.andengine.util.texturepack.TexturePackLoader;
import org.andengine.util.texturepack.TexturePackTextureRegionLibrary;

import android.content.Context;
import android.opengl.GLES20;

public class BaseTexturesLibrary extends Library<TexturePack> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected final HashMap<String, ITextureRegion> mRawRegionsMap = new HashMap<String, ITextureRegion>();
	// ===========================================================
	// Constructors
	// ===========================================================
	public BaseTexturesLibrary() {
		super();
	}

	public BaseTexturesLibrary(int pInitialCapacity) {
		super(pInitialCapacity);
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public ITextureRegion get(final String pSource) {
		for (int i = 0; i < mItems.size(); i++) {
			TexturePack texturePack = mItems.valueAt(i);
			ITextureRegion region;

			region = texturePack.getTexturePackTextureRegionLibrary().get(pSource);
			if (region != null) {
				return region;
			}
		}
		ITextureRegion region = mRawRegionsMap.get(pSource);
		if (region == null) {
			throw new RuntimeException("unable to find " + pSource + " in textures library");
		}
		return region;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	protected TiledTextureRegion prepareTiled(int pLibID, int pFirstTile, int pTilesNumber) {
		TextureRegion pRegions[] = new TextureRegion[pTilesNumber];
		TexturePackTextureRegionLibrary lib = get(pLibID).getTexturePackTextureRegionLibrary();
		for (int i=0; i < pTilesNumber; i++) {
			pRegions[i] = lib.get(pFirstTile + i);
		}
		return new TiledTextureRegion(get(pLibID).getTexture(), pRegions);
	}

	protected Font loadFont(final TextureManager pTextureManager, final FontManager pFontManager, final Context pContext, String pFontName, int pSize, int pAtlasW, int pAtlasH) {
		BitmapTextureAtlas mFontAtlas = new BitmapTextureAtlas(pTextureManager, pAtlasW, pAtlasH, TextureOptions.BILINEAR);
		Font font = FontFactory.createFromAsset(pFontManager, mFontAtlas, pContext.getAssets(), pFontName, pSize, true, Color.WHITE.getARGBPackedInt());
		font.load();
		return font;
	}

	protected void loadPack(final int pID, final String pAssetPath, final String pAssetBasePath,
			final TexturePackLoader pLoader) {
		try {
			TexturePack texturePack = pLoader.loadFromAsset(pAssetPath, pAssetBasePath);
			texturePack.loadTexture();
			put(pID, texturePack);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected ITextureRegion loadAsset(final String pAssetName, final TextureManager pTextureManager, final Context pContext) {
		return loadAsset(pAssetName, pTextureManager, pContext, GLES20.GL_REPEAT, GLES20.GL_REPEAT);
	}

	protected ITextureRegion loadAsset(final String pAssetName, final TextureManager pTextureManager, final Context pContext,
			int pWrapT, int pWrapS) {
		AssetBitmapTexture texture;
		try {
			texture = new AssetBitmapTexture(pTextureManager, pContext.getAssets(), BitmapTextureAtlasTextureRegionFactory.getAssetBasePath() + pAssetName, new TextureOptions(GLES20.GL_LINEAR, GLES20.GL_LINEAR, pWrapT, pWrapS, false));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		TextureRegion region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
		texture.load();
		mRawRegionsMap.put(pAssetName, region);
		return region;
	}

	protected ITextureRegion loadRaw(final String pAssetName, final int pW, final int pH,
			final TextureManager pTextureManager, final Context pContext) {
		return loadRaw(pAssetName, pW, pH, pTextureManager, pContext, GLES20.GL_REPEAT, GLES20.GL_REPEAT);
	}

	protected ITextureRegion loadRaw(final String pAssetName, final int pW, final int pH,
			final TextureManager pTextureManager, final Context pContext, int pWrapT, int pWrapS) {
		BitmapTextureAtlas atlas = new BitmapTextureAtlas(pTextureManager, pW, pH, new TextureOptions(GLES20.GL_LINEAR, GLES20.GL_LINEAR, pWrapT, pWrapS, false));
		ITextureRegion region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, pContext, pAssetName, 0, 0);
		atlas.load();
		mRawRegionsMap.put(pAssetName, region);
		return region;
	}
}