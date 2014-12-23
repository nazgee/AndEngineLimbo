package org.andengine.limbo.physics;

import org.andengine.limbo.physics.Actions.FixtureAction;

import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public class FixtureActionRunnablePoolItem extends ActionRunnablePoolItem<Fixture, FixtureAction> {
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

	@Override
	public void run() {
		if (this.mCallback != null) {
			this.mCallback.onCallback(this.mItem);
		}

		switch (mAction) {
		case SENSORIZE:
			this.mItem.setSensor(true);
			break;
		case DESENSORIZE:
			this.mItem.setSensor(false);
			break;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}