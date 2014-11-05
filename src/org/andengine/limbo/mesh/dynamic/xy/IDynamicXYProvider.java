package org.andengine.limbo.mesh.dynamic.xy;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.limbo.mesh.IXYProvider;

public interface IDynamicXYProvider extends IXYProvider, IUpdateHandler{
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
	boolean isDirty();
	void setDirty(boolean pDirty);

	void setScale(float pScale);
	float getScale();

	void setOrigin(float pX, float pY);
	float getOriginX();
	float getOriginY();

	void setRotation(float pRotation);
	float getRotation();
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
