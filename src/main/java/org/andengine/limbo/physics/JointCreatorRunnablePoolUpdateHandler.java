package org.andengine.limbo.physics;

import org.andengine.util.adt.pool.RunnablePoolUpdateHandler;
import org.andengine.util.call.Callback;

import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public abstract class JointCreatorRunnablePoolUpdateHandler<D extends JointDef> extends RunnablePoolUpdateHandler<JointCreatorRunnablePoolItem<D>> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public JointCreatorRunnablePoolUpdateHandler() {
		super();
	}

	public JointCreatorRunnablePoolUpdateHandler(final int pInitialPoolSize) {
		super(pInitialPoolSize);
	}

	public JointCreatorRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth) {
		super(pInitialPoolSize, pGrowth);
	}

	public JointCreatorRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth, final int pAvailableItemCountMaximum) {
		super(pInitialPoolSize, pGrowth, pAvailableItemCountMaximum);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected abstract JointCreatorRunnablePoolItem<D> onAllocatePoolItem();

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pEntity the @{link Body} to be detached safely.
	 * @param pCallback will be called after the @{link IEntity} actually was detached.
	 */
	public void scheduleJointCreation(final D pJointDef, final Callback<Joint> pCallback) {
		final JointCreatorRunnablePoolItem<D> item = this.obtainPoolItem();
		if (pJointDef != null) {
			item.setJointDef(pJointDef);
		}
		item.setCallback(pCallback);
		this.postPoolItem(item);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
