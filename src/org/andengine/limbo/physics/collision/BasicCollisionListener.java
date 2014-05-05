package org.andengine.limbo.physics.collision;

import java.util.ArrayList;

import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsConnector;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;


public class BasicCollisionListener implements ICollisionListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	Array<ICollisionListener> mListeners = new Array<ICollisionListener>(1);
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
	public void beginContact(Contact contact) {
		int listenersNumber = mListeners.size;
		for (int i = 0; i < listenersNumber; i++) {
			mListeners.get(i).beginContact(contact);
		}
	}

	@Override
	public void endContact(Contact contact) {
		int listenersNumber = mListeners.size;
		for (int i = 0; i < listenersNumber; i++) {
			mListeners.get(i).endContact(contact);
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		int listenersNumber = mListeners.size;
		for (int i = 0; i < listenersNumber; i++) {
			mListeners.get(i).preSolve(contact, oldManifold);
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		int listenersNumber = mListeners.size;
		for (int i = 0; i < listenersNumber; i++) {
			mListeners.get(i).postSolve(contact, impulse);
		}
	}

	@Override
	public void registerChild(ICollisionListener pListener) {
		mListeners.add(pListener);
	}

	@Override
	public void clearChildren() {
		mListeners.clear();
	}

	@Override
	public boolean isColliding() {
		return false;
	}
	// ===========================================================
	// Methods
	// ===========================================================


	public static boolean isInvolved(Contact pContact, Body pCollidingBody) {
		Body bodyA = pContact.getFixtureA().getBody();
		Body bodyB = pContact.getFixtureB().getBody();
		return (bodyA == pCollidingBody || bodyB == pCollidingBody);
	}

	public static boolean areInvolved(Contact pContact, Body pFirstCollidingBody, Body pSecondCollidingBody) {
		Body bodyA = pContact.getFixtureA().getBody();
		Body bodyB = pContact.getFixtureB().getBody();
		return (bodyA == pFirstCollidingBody && bodyB == pSecondCollidingBody) || (bodyA == pSecondCollidingBody && bodyB == pFirstCollidingBody);
	}

	public static Body getOther(Contact pContact, Body pBody) {
		Body bodyA = pContact.getFixtureA().getBody();
		Body bodyB = pContact.getFixtureB().getBody();
		if (bodyA == pBody) {
			return bodyB;
		} else if (bodyB == pBody) {
			return bodyA;
		} else {
			return null;
		}
	}

	public static boolean isInvolved(Contact pContact, Fixture pFixture) {
		Fixture fixtureA = pContact.getFixtureA();
		Fixture fixtureB = pContact.getFixtureB();
		return (fixtureA == pFixture || fixtureB == pFixture);
	}

	public static Fixture getOther(Contact pContact, Fixture pFixture) {
		Fixture fixtureA = pContact.getFixtureA();
		Fixture fixtureB = pContact.getFixtureB();
		if (fixtureA == pFixture) {
			return fixtureB;
		} else if (fixtureB == pFixture) {
			return fixtureA;
		} else {
			return null;
		}
	}

	public static boolean isInvolved(Contact pContact, short pCollidingFixtureCategory) {
		Fixture fixtureA = pContact.getFixtureA();
		Fixture fixtureB = pContact.getFixtureB();
		return isOnexFixtureMatchingCategory(pCollidingFixtureCategory, fixtureA, fixtureB);
	}

	public static short getOther(Contact pContact, short pCollidingFixtureCategory) {
		Fixture fixtureA = pContact.getFixtureA();
		Fixture fixtureB = pContact.getFixtureB();
		if ((fixtureA.getFilterData().categoryBits & pCollidingFixtureCategory) != 0) {
			return fixtureB.getFilterData().categoryBits;
		} else if ((fixtureB.getFilterData().categoryBits & pCollidingFixtureCategory) != 0) {
			return fixtureA.getFilterData().categoryBits;
		} else {
			return 0;
		}
	}

	public static boolean isOnexFixtureMatchingCategory(short pCollidingFixtureCategory, Fixture fixtureA, Fixture fixtureB) {
		return ((fixtureA.getFilterData().categoryBits & pCollidingFixtureCategory) != 0) 
			|| ((fixtureB.getFilterData().categoryBits & pCollidingFixtureCategory) != 0);
	}

	public static boolean areInvolved(Contact pContact, short pFirstCollidingFixtureCategory, short pSecondCollidingFixtureCategory) {
		Fixture fixtureA = pContact.getFixtureA();
		Fixture fixtureB = pContact.getFixtureB();
		return areBothFixturesMatchingBothCategories(pFirstCollidingFixtureCategory, pSecondCollidingFixtureCategory, fixtureA, fixtureB);
	}

	public static boolean areBothFixturesMatchingBothCategories(short pFirstCollidingFixtureCategory, short pSecondCollidingFixtureCategory, Fixture fixtureA,
			Fixture fixtureB) {
		return (((fixtureA.getFilterData().categoryBits & pFirstCollidingFixtureCategory) != 0) &&
				((fixtureB.getFilterData().categoryBits & pSecondCollidingFixtureCategory) != 0)) 
			|| (((fixtureB.getFilterData().categoryBits & pFirstCollidingFixtureCategory) != 0) &&
				((fixtureA.getFilterData().categoryBits & pSecondCollidingFixtureCategory) != 0));
	}

	public static boolean areInvolved(Contact pContact, Fixture pFirstCollidingFixture, short pSecondCollidingFixtureCategory) {
		Fixture fixtureA = pContact.getFixtureA();
		Fixture fixtureB = pContact.getFixtureB();
		return (fixtureA == pFirstCollidingFixture && ((fixtureB.getFilterData().categoryBits & pSecondCollidingFixtureCategory) != 0)) 
			|| (fixtureB == pFirstCollidingFixture && ((fixtureA.getFilterData().categoryBits & pSecondCollidingFixtureCategory) != 0));
	}

	public static boolean areInvolved(Contact pContact, Body pFirstCollidingBody, short pSecondCollidingFixtureCategory) {
		Fixture fixtureA = pContact.getFixtureA();
		Fixture fixtureB = pContact.getFixtureB();
		Body bodyA = fixtureA.getBody();
		Body bodyB = fixtureB.getBody();
		return (bodyA == pFirstCollidingBody && ((fixtureB.getFilterData().categoryBits & pSecondCollidingFixtureCategory) != 0)) 
			|| (bodyB == pFirstCollidingBody && ((fixtureA.getFilterData().categoryBits & pSecondCollidingFixtureCategory) != 0));
	}

	public static Fixture getFixture(Contact pContact, short pFixtureCategory) {
		Fixture fixtureA = pContact.getFixtureA();
		Fixture fixtureB = pContact.getFixtureB();
		Fixture ret = null;

		if ((fixtureA.getFilterData().categoryBits & pFixtureCategory) != 0) {
			ret = fixtureA;
		} else if ((fixtureB.getFilterData().categoryBits & pFixtureCategory) != 0) {
			ret = fixtureB;
		}
		return ret;
	}

	public static IEntity findFirstEntityByBody(ArrayList<PhysicsConnector> physicsConnectors, final Body pBody) {
		int size = physicsConnectors.size() - 1;
		for(int i = size; i >= 0; i--) {
			final PhysicsConnector physicsConnector = physicsConnectors.get(i);
			if(physicsConnector.getBody() == pBody){
				return physicsConnector.getEntity();
			}
		}
		return null;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}