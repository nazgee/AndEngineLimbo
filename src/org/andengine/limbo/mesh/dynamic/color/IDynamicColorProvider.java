package org.andengine.limbo.mesh.dynamic.color;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.limbo.mesh.IColorProvider;
import org.andengine.limbo.mesh.IXYProvider;
import org.andengine.limbo.mesh.dynamic.IMutable;

public interface IDynamicColorProvider extends IColorProvider, IUpdateHandler, IMutable{
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
	public int getNumberOfVerticesMax();
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
