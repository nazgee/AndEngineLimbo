package org.andengine.limbo.physics.raycast;

public class RayNode extends Ray {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	public RayNode prev;
	public RayNode next;

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
	public void reset() {
		super.reset();
		prev = null;
		next = null;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public RayNode insertLeft(RayNode pRay) {
		if (prev != null) {
			pRay.prev = prev;
			prev.next = pRay;
		} else {
			pRay.prev = null;
		}
		pRay.next = this;
		prev = pRay;
		return pRay;
	}
	
	public RayNode insertRight(RayNode pRay) {
		if (next != null) {
			pRay.next = next;
			next.prev = pRay;
		} else {
			pRay.next = null;
		}
		pRay.prev = this;
		next = pRay;
		return pRay;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}
