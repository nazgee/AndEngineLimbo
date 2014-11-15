package org.andengine.limbo.mesh.dynamic.xy.modifier;

import org.andengine.limbo.mesh.dynamic.xy.IDynamicXYProvider;
import org.andengine.util.modifier.LoopModifier;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich
 * @since 12:42:13 - 03.09.2010
 */
public class LoopDynamicXYModifier extends LoopModifier<IDynamicXYProvider> implements IDynamicXYModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public LoopDynamicXYModifier(final IDynamicXYModifier pEntityModifier) {
		super(pEntityModifier);
	}

	public LoopDynamicXYModifier(final IDynamicXYModifier pEntityModifier, final int pLoopCount) {
		super(pEntityModifier, pLoopCount);
	}

	public LoopDynamicXYModifier(final IDynamicXYModifier pEntityModifier, final int pLoopCount, final ILoopDynamicXYModifierListener pLoopModifierListener) {
		super(pEntityModifier, pLoopCount, pLoopModifierListener);
	}

	public LoopDynamicXYModifier(final IDynamicXYModifier pEntityModifier, final int pLoopCount, final IDynamicXYModifierListener pEntityModifierListener) {
		super(pEntityModifier, pLoopCount, pEntityModifierListener);
	}

	public LoopDynamicXYModifier(final IDynamicXYModifierListener pEntityModifierListener, final int pLoopCount, final ILoopDynamicXYModifierListener pLoopModifierListener, final IDynamicXYModifier pEntityModifier) {
		super(pEntityModifier, pLoopCount, pLoopModifierListener, pEntityModifierListener);
	}

	protected LoopDynamicXYModifier(final LoopDynamicXYModifier pLoopEntityModifier) throws DeepCopyNotSupportedException {
		super(pLoopEntityModifier);
	}

	@Override
	public LoopDynamicXYModifier deepCopy() throws DeepCopyNotSupportedException {
		return new LoopDynamicXYModifier(this);
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

	public interface ILoopDynamicXYModifierListener extends ILoopModifierListener<IDynamicXYProvider> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================
	}
}
