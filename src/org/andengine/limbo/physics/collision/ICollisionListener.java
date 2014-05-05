package org.andengine.limbo.physics.collision;

import com.badlogic.gdx.physics.box2d.ContactListener;


public interface ICollisionListener extends ICollisionDetector, ContactListener {
	public void registerChild(ICollisionListener pListener);
	public void clearChildren();
}
