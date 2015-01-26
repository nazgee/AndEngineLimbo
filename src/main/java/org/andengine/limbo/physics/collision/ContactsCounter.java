package org.andengine.limbo.physics.collision;


public class ContactsCounter implements ICollisionDetector {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private int mContactsCount;
	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getContactsCount() {
		return mContactsCount;
	}

	@Override
	public boolean isColliding() {
		return mContactsCount > 0;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public void setContactsCount(int mContactsCount) {
		this.mContactsCount = mContactsCount;
	}

	public int incContactsCount() {
		return mContactsCount++;
	}

	public int decContactsCount() {
		if (mContactsCount > 0) {
			mContactsCount--;
		}
		return mContactsCount;
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}