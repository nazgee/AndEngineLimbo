package org.andengine.limbo.mesh.dynamic.xy.modifier;

import org.andengine.entity.IEntity;
import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.util.modifier.ModifierList;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich
 * @since 14:19:18 - 24.12.2010
 */
public class DynamicXYModifierList extends ModifierList<IDynamicXYProvider> {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 161652765736770082L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public DynamicXYModifierList(final IDynamicXYProvider pTarget) {
		super(pTarget);
	}

	public DynamicXYModifierList(final IDynamicXYProvider pTarget, final int pCapacity) {
		super(pTarget, pCapacity);
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
