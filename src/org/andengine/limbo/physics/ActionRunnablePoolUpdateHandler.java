package org.andengine.limbo.physics;

import org.andengine.util.adt.pool.RunnablePoolItem;
import org.andengine.util.adt.pool.RunnablePoolUpdateHandler;
import org.andengine.util.call.Callback;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public abstract class ActionRunnablePoolUpdateHandler<T, A, I extends RunnablePoolItem> extends RunnablePoolUpdateHandler<I> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ActionRunnablePoolUpdateHandler() {
		super();
	}

	public ActionRunnablePoolUpdateHandler(final int pInitialPoolSize) {
		super(pInitialPoolSize);
	}

	public ActionRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth) {
		super(pInitialPoolSize, pGrowth);
	}

	public ActionRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth, final int pAvailableItemCountMaximum) {
		super(pInitialPoolSize, pGrowth, pAvailableItemCountMaximum);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

//	@Override
//	protected I onAllocatePoolItem() {
//		return new BodyActionRunnablePoolItem();
//	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pBody the @{link Body} to be detached safely.
	 */
	public void scheduleAction(final T pBody, A pAction) {
		this.scheduleAction(pBody, pAction, null);
	}

	/**
	 * @param pEntity the @{link Body} to be detached safely.
	 * @param pCallback will be called after the @{link IEntity} actually was detached.
	 */
	abstract public void scheduleAction(final T pBody, A pAction, final Callback<T> pCallback);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
