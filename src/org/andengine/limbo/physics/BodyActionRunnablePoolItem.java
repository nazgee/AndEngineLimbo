package org.andengine.limbo.physics;

import org.andengine.util.adt.pool.RunnablePoolItem;
import org.andengine.util.call.Callback;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public class BodyActionRunnablePoolItem extends RunnablePoolItem {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected Body mBody;
	protected Callback<Body> mCallback;
	private Action mAction;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setEntity(final Body pBody) {
		this.mBody = pBody;
	}

	/**
	 * Sets up a callback which will get called right after detaching entity
	 * @param pCallback gets called right after detaching entity; if null nothing will be called
	 */
	public void setCallback(final Callback<Body> pCallback) {
		this.mCallback = pCallback;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void run() {
		World world = this.mBody.getWorld();

		if (this.mCallback != null) {
			this.mCallback.onCallback(this.mBody);
		}

		switch (mAction) {
		case ACTIVATE:
			this.mBody.setActive(true);
			break;
		case DEACTIVATE:
			this.mBody.setActive(false);
			break;
		case DESTROY:
			mBody.getUserData();
			world.destroyBody(mBody);
			break;
		}
	}

	public void setAction(Action pAction) {
		this.mAction = pAction;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	public static enum Action {
		DEACTIVATE,
		ACTIVATE,
		DESTROY
	}
}