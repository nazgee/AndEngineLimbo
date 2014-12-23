package org.andengine.limbo.physics;

import org.andengine.limbo.physics.Actions.JointAction;
import org.andengine.util.call.Callback;

import com.badlogic.gdx.physics.box2d.Joint;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public class JointActionRunnablePoolUpdateHandler extends ActionRunnablePoolUpdateHandler<Joint, JointAction, JointActionRunnablePoolItem> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public JointActionRunnablePoolUpdateHandler() {
		super();
	}

	public JointActionRunnablePoolUpdateHandler(final int pInitialPoolSize) {
		super(pInitialPoolSize);
	}

	public JointActionRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth) {
		super(pInitialPoolSize, pGrowth);
	}

	public JointActionRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth, final int pAvailableItemCountMaximum) {
		super(pInitialPoolSize, pGrowth, pAvailableItemCountMaximum);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected JointActionRunnablePoolItem onAllocatePoolItem() {
		return new JointActionRunnablePoolItem();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pEntity the @{link Joint} to be detached safely.
	 * @param pCallback will be called after the @{link IEntity} actually was detached.
	 */
	public void scheduleAction(final Joint pJoint, JointAction pAction, final Callback<Joint> pCallback) {
		final JointActionRunnablePoolItem item = this.obtainPoolItem();
		item.setItem(pJoint);
		item.setAction(pAction);
		item.setCallback(pCallback);
		this.postPoolItem(item);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
