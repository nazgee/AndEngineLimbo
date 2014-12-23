package org.andengine.limbo.physics;

import org.andengine.limbo.physics.Actions.JointAction;

import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public class JointActionRunnablePoolItem extends ActionRunnablePoolItem<Joint, JointAction> {
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
		World world = this.mItem.getBodyA().getWorld();

		if (this.mCallback != null) {
			this.mCallback.onCallback(this.mItem);
		}

		switch (mAction) {
		case DESTROY:
			world.destroyJoint(mItem);
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