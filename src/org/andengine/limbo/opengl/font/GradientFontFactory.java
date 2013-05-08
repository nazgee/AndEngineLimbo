package org.andengine.limbo.opengl.font;

import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.ITexture;
import org.andengine.util.adt.color.Color;

import android.content.res.AssetManager;
import android.graphics.Typeface;
/**
 * Found on andengine.org/forums/
 * 
 * http://www.andengine.org/forums/gles2/gradient-to-font-t7948.html#p34667
 * 
 * @author fatal
 * @since 12:20:00 - 08.08.2012
 * 
 */
public class GradientFontFactory {

	private static String sAssetBasePath = "";

	public static void setAssetBasePath(final String pAssetBasePath) {
		if (pAssetBasePath.endsWith("/") || pAssetBasePath.length() == 0) {
			GradientFontFactory.sAssetBasePath = pAssetBasePath;
		} else {
			throw new IllegalStateException("pAssetBasePath must end with '/' or be lenght zero.");
		}
	}

	public static GradientFont createFromAsset(final FontManager pFontManager, final ITexture pTexture, final AssetManager pAssetManager,
			final String pAssetPath, final float pSize, final boolean pAntiAlias, final Color pColorFrom, final Color pColorTo) {
		return new GradientFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColorFrom.getARGBPackedInt(), pColorTo.getARGBPackedInt());
	}

	public static GradientFont createFromAsset(final FontManager pFontManager, final ITexture pTexture, final AssetManager pAssetManager,
			final String pAssetPath, final float pSize, final boolean pAntiAlias, final int pColorFrom, final int pColorTo) {
		return new GradientFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColorFrom, pColorTo);
	}

	public static GradientFont createFromAsset(final FontManager pFontManager, final ITexture pTexture, final AssetManager pAssetManager,
			final String pAssetPath, final float pSize, final boolean pAntiAlias, final int[] pColors) {
		return new GradientFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColors);
	}

	public static GradientFont createFromAsset(final FontManager pFontManager, final ITexture pTexture, final AssetManager pAssetManager,
			final String pAssetPath, final float pSize, final boolean pAntiAlias, final Color[] pColors) {
		return new GradientFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColors);
	}

	public static GradientStrokeFont createStrokeFromAsset(final FontManager pFontManager, final ITexture pTexture,
			final AssetManager pAssetManager, final String pAssetPath, final float pSize, final boolean pAntiAlias, final Color pColorFrom,
			final Color pColorTo, final float pStrokeWidth, final int pStrokeColor) {
		return new GradientStrokeFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColorFrom.getARGBPackedInt(), pColorTo.getARGBPackedInt(), pStrokeWidth, pStrokeColor);
	}

	public static GradientStrokeFont createStrokeFromAsset(final FontManager pFontManager, final ITexture pTexture,
			final AssetManager pAssetManager, final String pAssetPath, final float pSize, final boolean pAntiAlias, final Color pColorFrom,
			final Color pColorTo, final float pStrokeWidth, final Color pStrokeColor) {
		return new GradientStrokeFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColorFrom.getARGBPackedInt(), pColorTo.getARGBPackedInt(), pStrokeWidth,
				pStrokeColor.getARGBPackedInt());
	}

	public static GradientStrokeFont createStrokeFromAsset(final FontManager pFontManager, final ITexture pTexture,
			final AssetManager pAssetManager, final String pAssetPath, final float pSize, final boolean pAntiAlias, final int pColorFrom,
			final int pColorTo, final float pStrokeWidth, final int pStrokeColor) {
		return new GradientStrokeFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColorFrom, pColorTo, pStrokeWidth, pStrokeColor);
	}

	public static GradientStrokeFont createStrokeFromAsset(final FontManager pFontManager, final ITexture pTexture,
			final AssetManager pAssetManager, final String pAssetPath, final float pSize, final boolean pAntiAlias, final int pColorFrom,
			final int pColorTo, final float pStrokeWidth, final Color pStrokeColor) {
		return new GradientStrokeFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColorFrom, pColorTo, pStrokeWidth, pStrokeColor.getARGBPackedInt());
	}

	public static GradientStrokeFont createStrokeFromAsset(final FontManager pFontManager, final ITexture pTexture,
			final AssetManager pAssetManager, final String pAssetPath, final float pSize, final boolean pAntiAlias, final int[] pColors,
			final float pStrokeWidth, final int pStrokeColor) {
		return new GradientStrokeFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColors, pStrokeWidth, pStrokeColor);
	}

	public static GradientStrokeFont createStrokeFromAsset(final FontManager pFontManager, final ITexture pTexture,
			final AssetManager pAssetManager, final String pAssetPath, final float pSize, final boolean pAntiAlias, final int[] pColors,
			final float pStrokeWidth, final Color pStrokeColor) {
		return new GradientStrokeFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColors, pStrokeWidth, pStrokeColor);
	}

	public static GradientStrokeFont createStrokeFromAsset(final FontManager pFontManager, final ITexture pTexture,
			final AssetManager pAssetManager, final String pAssetPath, final float pSize, final boolean pAntiAlias, final Color[] pColors,
			final float pStrokeWidth, final int pStrokeColor) {
		return new GradientStrokeFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColors, pStrokeWidth, pStrokeColor);
	}

	public static GradientStrokeFont createStrokeFromAsset(final FontManager pFontManager, final ITexture pTexture,
			final AssetManager pAssetManager, final String pAssetPath, final float pSize, final boolean pAntiAlias, final Color[] pColors,
			final float pStrokeWidth, final Color pStrokeColor) {
		return new GradientStrokeFont(pFontManager, pTexture, Typeface.createFromAsset(pAssetManager, GradientFontFactory.sAssetBasePath
				+ pAssetPath), pSize, pAntiAlias, pColors, pStrokeWidth, pStrokeColor);
	}
}