package org.andengine.limbo.utils;

import org.andengine.entity.IEntity;

/**
 * Created by nazgee on 30.01.15.
 */
public class EntityUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public static void copyTransform(IEntity src, IEntity dst) {
		dst.setOffsetCenterX(src.getOffsetCenterX());
		dst.setOffsetCenterY(src.getOffsetCenterY());
		dst.setPosition(src);

		dst.setRotationCenterX(src.getRotationCenterX());
		dst.setRotationCenterY(src.getRotationCenterY());
		dst.setRotation(src.getRotation());
		dst.setRotationOffset(src.getRotationOffset());

		dst.setScaleCenterX(src.getScaleCenterX());
		dst.setScaleCenterY(src.getScaleCenterY());
		dst.setScaleX(src.getScaleX());
		dst.setScaleY(src.getScaleY());

		dst.setSkewCenterX(src.getSkewCenterX());
		dst.setSkewCenterY(src.getSkewCenterY());
		dst.setSkewX(src.getSkewX());
		dst.setSkewY(src.getSkewY());
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
