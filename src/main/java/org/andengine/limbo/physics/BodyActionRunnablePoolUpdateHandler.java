package org.andengine.limbo.physics;

import org.andengine.limbo.physics.Actions.BodyAction;
import org.andengine.util.call.Callback;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public class BodyActionRunnablePoolUpdateHandler extends ActionRunnablePoolUpdateHandler<Body, BodyAction, BodyActionRunnablePoolItem> {
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
	 * @param pEntity the @{link Body} to be detached safely.
	 * @param pCallback will be called after the @{link IEntity} actually was detached.
	 */
	public void scheduleAction(final Body pBody, BodyAction pAction, final Callback<Body> pCallback) {
		final BodyActionRunnablePoolItem item = this.obtainPoolItem();
		item.setItem(pBody);
		item.setAction(pAction);
		item.setCallback(pCallback);
		this.postPoolItem(item);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
