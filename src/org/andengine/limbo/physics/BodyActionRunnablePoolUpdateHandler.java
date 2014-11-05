package org.andengine.limbo.physics;

import org.andengine.limbo.physics.BodyActionRunnablePoolItem.Action;
import org.andengine.util.adt.pool.RunnablePoolUpdateHandler;
import org.andengine.util.call.Callback;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public class BodyActionRunnablePoolUpdateHandler extends RunnablePoolUpdateHandler<BodyActionRunnablePoolItem> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public BodyActionRunnablePoolUpdateHandler() {
		super();
	}

	public BodyActionRunnablePoolUpdateHandler(final int pInitialPoolSize) {
		super(pInitialPoolSize);
	}

	public BodyActionRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth) {
		super(pInitialPoolSize, pGrowth);
	}

	public BodyActionRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth, final int pAvailableItemCountMaximum) {
		super(pInitialPoolSize, pGrowth, pAvailableItemCountMaximum);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected BodyActionRunnablePoolItem onAllocatePoolItem() {
		return new BodyActionRunnablePoolItem();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pBody the @{link Body} to be detached safely.
	 */
	public void scheduleAction(final Body pBody, Action pAction) {
		this.scheduleAction(pBody, pAction, null);
	}

	/**
	 * @param pEntity the @{link Body} to be detached safely.
	 * @param pCallback will be called after the @{link IEntity} actually was detached.
	 */
	public void scheduleAction(final Body pBody, Action pAction, final Callback<Body> pCallback) {
		final BodyActionRunnablePoolItem item = this.obtainPoolItem();
		item.setEntity(pBody);
		item.setAction(pAction);
		item.setCallback(pCallback);
		this.postPoolItem(item);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
