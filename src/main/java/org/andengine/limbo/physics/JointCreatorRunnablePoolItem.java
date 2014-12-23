package org.andengine.limbo.physics;

import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.adt.pool.RunnablePoolItem;
import org.andengine.util.call.Callback;

import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2014 Michal Stawinski
 *
 * @author Michal Stawinski
 */
public abstract class JointCreatorRunnablePoolItem<D extends JointDef> extends RunnablePoolItem {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private PhysicsWorld mWorld;
	private D mJointDef;
	protected Callback<Joint> mCallback;

	// ===========================================================
	// Constructors
	// ===========================================================
	public JointCreatorRunnablePoolItem() {
		mJointDef = createJointDef();
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public D getJointDef() {
		return mJointDef;
	}

	public void setJointDef(D pJointDef) {
		this.mJointDef = pJointDef;
	}

	public PhysicsWorld getWorld() {
		return mWorld;
	}

	public void setWorld(PhysicsWorld pWorld) {
		this.mWorld = pWorld;
	}

	/**
	 * Sets up a callback which will get called right after detaching entity
	 * @param pCallback gets called right after detaching entity; if null nothing will be called
	 */
	public void setCallback(final Callback<Joint> pCallback) {
		this.mCallback = pCallback;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void run() {
		Joint joint = mWorld.createJoint(mJointDef);

		if (this.mCallback != null) {
			this.mCallback.onCallback(joint);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	protected abstract D createJointDef();

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}