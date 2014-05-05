package org.andengine.limbo.physics.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;


public class CollisionListenerType extends BasicCollisionListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected final short mTypeA;
	protected final short mTypeB;
	protected final ContactsCounter mContactsCounter = new ContactsCounter();
	// ===========================================================
	// Constructors
	// ===========================================================
	public CollisionListenerType(short pType) {
		this(pType, (short)0xFFFF);
	}

	public CollisionListenerType(short pTypeA, short pTypeB) {
		mTypeA = pTypeA;
		mTypeB = pTypeB;
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
		if (areInvolved(contact, mTypeA, mTypeB)) {
			mContactsCounter.incContactsCount();
		}
	}

	@Override
	public void endContact(Contact contact) {
		if (areInvolved(contact, mTypeA, mTypeB)) {
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