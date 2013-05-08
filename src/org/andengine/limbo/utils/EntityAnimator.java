package org.andengine.limbo.utils;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.util.modifier.IModifier;

/**
 * This class is used to run a Modifier on a given Entity. When running
 * a modifier, it takes care of unregistering an old modifier before
 * registering a new one, which helps to avoid silly situations where simillar
 * effect is applied mutliple times on a single entity.
 * 
 * @author Michal Stawinski (nazgee)
 *
 */
public class EntityAnimator {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private IEntity mEntity = null;
	private IEntityModifier mAnimationModifier;
	private EntityListener mListener = new EntityListener();
	// ===========================================================
	// Constructors
	// ===========================================================
	public EntityAnimator(final IEntity mEntity) {
		this.mEntity = mEntity;
	}

	public EntityAnimator() {
		mEntity = null;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public IEntity getEntity() {
		return mEntity;
	}

	public synchronized void setEntity(final IEntity pNewEntity) {
		if (mEntity != null) {
			mEntity.unregisterEntityModifier(mAnimationModifier);
		}
		mEntity = pNewEntity;
	}

	public synchronized boolean isRunning() {
		return (mAnimationModifier!=null && !mAnimationModifier.isFinished());
	}

	public synchronized IEntityModifier getModifier() {
		return mAnimationModifier;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public synchronized void stop() {
		if (mAnimationModifier == null) {
			mEntity.unregisterEntityModifier(mAnimationModifier);
		}
	}

	public synchronized void run(final IEntityModifier pModifier) {
		run(pModifier, null);
	}

	public synchronized void run(final IEntityModifier pModifier, final IAnimatorListener pListener) {
		if (mEntity == null) {
			return;
		}

		pModifier.setAutoUnregisterWhenFinished(false);
		mEntity.unregisterEntityModifier(mAnimationModifier);
		mEntity.registerEntityModifier(pModifier);
		mAnimationModifier = pModifier;

		mAnimationModifier.removeModifierListener(mListener);
		mListener.listener = pListener;
		if (pListener != null) {
			mAnimationModifier.addModifierListener(mListener);
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	private class EntityListener implements IEntityModifierListener {
		public volatile IAnimatorListener listener;
		@Override
		public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		}

		@Override
		public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
			listener.onAnimatorFinished(EntityAnimator.this);
		}
	}

	public static interface IAnimatorListener {
		public void onAnimatorFinished(EntityAnimator pAnimator);
	}
}