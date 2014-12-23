package org.andengine.limbo.physics;

import org.andengine.limbo.physics.Actions.BodyAction;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public class BodyActionRunnablePoolItem extends ActionRunnablePoolItem<Body, BodyAction> {
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
		World world = this.mItem.getWorld();

		if (this.mCallback != null) {
			this.mCallback.onCallback(this.mItem);
		}

		switch (mAction) {
		case ACTIVATE:
			this.mItem.setActive(true);
			break;
		case DEACTIVATE:
			this.mItem.setActive(false);
			break;
		case DESTROY:
			mItem.getUserData();
			world.destroyBody(mItem);
			break;
		case AWAKE:
			this.mItem.setAwake(true);
			break;
		case SLEEP:
			this.mItem.setAwake(false);
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