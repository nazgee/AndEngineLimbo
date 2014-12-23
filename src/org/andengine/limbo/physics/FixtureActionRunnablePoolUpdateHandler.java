package org.andengine.limbo.physics;

import org.andengine.limbo.physics.Actions.FixtureAction;
import org.andengine.util.call.Callback;

import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public class FixtureActionRunnablePoolUpdateHandler extends ActionRunnablePoolUpdateHandler<Fixture, FixtureAction, FixtureActionRunnablePoolItem> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public FixtureActionRunnablePoolUpdateHandler() {
		super();
	}

	public FixtureActionRunnablePoolUpdateHandler(final int pInitialPoolSize) {
		super(pInitialPoolSize);
	}

	public FixtureActionRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth) {
		super(pInitialPoolSize, pGrowth);
	}

	public FixtureActionRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth, final int pAvailableItemCountMaximum) {
		super(pInitialPoolSize, pGrowth, pAvailableItemCountMaximum);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected FixtureActionRunnablePoolItem onAllocatePoolItem() {
		return new FixtureActionRunnablePoolItem();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pEntity the @{link Fixture} to be detached safely.
	 * @param pCallback will be called after the @{link IEntity} actually was detached.
	 */
	public void scheduleAction(final Fixture pFixture, FixtureAction pAction, final Callback<Fixture> pCallback) {
		final FixtureActionRunnablePoolItem item = this.obtainPoolItem();
		item.setItem(pFixture);
		item.setAction(pAction);
		item.setCallback(pCallback);
		this.postPoolItem(item);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
