package org.andengine.limbo.physics;

import org.andengine.util.adt.pool.RunnablePoolItem;
import org.andengine.util.call.Callback;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public abstract class ActionRunnablePoolItem<T, A> extends RunnablePoolItem {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected T mItem;
	protected Callback<T> mCallback;
	protected A mAction;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setItem(final T pItem) {
		this.mItem = pItem;
	}

	/**
	 * Sets up a callback which will get called right after detaching entity
	 * @param pCallback gets called right after detaching entity; if null nothing will be called
	 */
	public void setCallback(final Callback<T> pCallback) {
		this.mCallback = pCallback;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	abstract public void run();

	public void setAction(A pAction) {
		this.mAction = pAction;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}