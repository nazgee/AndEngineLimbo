package org.andengine.limbo.mesh.dynamic.uv;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.limbo.mesh.IUVMapper;
import org.andengine.limbo.mesh.dynamic.IMutable;

public interface IDynamicUVMapper extends IUVMapper, IUpdateHandler, IMutable {

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
