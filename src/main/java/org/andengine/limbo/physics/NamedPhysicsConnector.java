package org.andengine.limbo.physics;

import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.Body;

public class NamedPhysicsConnector extends PhysicsConnector {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private final String mName;
	// ===========================================================
	// Constructors
	// ===========================================================
	public NamedPhysicsConnector(IEntity pEntity, Body pBody) {
		this(pEntity, pBody, "unnamed", PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
	}

	public NamedPhysicsConnector(IEntity pEntity, Body pBody, final String pName) {
		this(pEntity, pBody, pName, PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
	}

	public NamedPhysicsConnector(IEntity pEntity, Body pBody, final String pName, final float pPhysicsToMeterRatio) {
		super(pEntity, pBody, pPhysicsToMeterRatio);
		mName = pName;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public String getName() {
		return mName;
	}

	public float getPixelToMeterRatio() {
		return mPixelToMeterRatio;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onUpdate(final float pSecondsElapsed) {
		if (getBody().isAwake()) {
			super.onUpdate(pSecondsElapsed);
		}
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
