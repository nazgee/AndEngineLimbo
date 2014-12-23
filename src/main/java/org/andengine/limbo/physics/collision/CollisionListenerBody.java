package org.andengine.limbo.physics.collision;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;


public class CollisionListenerBody extends BasicCollisionListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected final Body mBody;
	protected final ContactsCounter mContactsCounter = new ContactsCounter();
	// ===========================================================
	// Constructors
	// ===========================================================
	public CollisionListenerBody(Body pBody) {
		mBody = pBody;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void registerChild(ICollisionListener pListener) {
		throw new RuntimeException(getClass().getSimpleName() + " is not accepting children");
	}

	@Override
	public void beginContact(Contact contact) {
		if (isInvolved(contact, mBody)) {
			mContactsCounter.incContactsCount();
		}
	}

	@Override
	public void endContact(Contact contact) {
		if (isInvolved(contact, mBody)) {
			mContactsCounter.decContactsCount();
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

	@Override
	public boolean isColliding() {
		return mContactsCounter.isColliding();
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}