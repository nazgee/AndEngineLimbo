package org.andengine.limbo.physics.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;


public class CollisionListenerFixture extends BasicCollisionListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected final Fixture mFixture;
	protected final ContactsCounter mContactsCounter = new ContactsCounter();
	// ===========================================================
	// Constructors
	// ===========================================================
	public CollisionListenerFixture(Fixture pType) {
		mFixture = pType;
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
		if (isInvolved(contact, mFixture)) {
			mContactsCounter.incContactsCount();
		}
	}

	@Override
	public void endContact(Contact contact) {
		if (isInvolved(contact, mFixture)) {
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