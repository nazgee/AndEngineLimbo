package org.andengine.limbo.widgets.swing;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.limbo.utils.EntityAnimator;
import org.andengine.limbo.utils.EntityAnimator.IAnimatorListener;
import org.andengine.limbo.utils.positioner.PositionerImmovableRelative;
import org.andengine.limbo.widgets.ClippingWindow;
import org.andengine.limbo.widgets.ClippingWindowContainer;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * Base for widgets animating it's value updates. When value of
 * Swing is set by setValue() it hides it's container, updates it's content,
 * and then shows updated container.
 * 
 * Hiding and showing of a container is animated.
 *
 * (c) 2013 Michal Stawinski (nazgee)
 *
 * @author Michal Stawinski
 * @since 20:31:01 - 13.05.2013
 */
public abstract class Swing<T> extends ClippingWindowContainer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected T mValue;
	protected T mPendingValue;

	private final EntityAnimator mContainerAnimator;
	private final AnimationOutListener mAnimateOutListener = new AnimationOutListener();

	private final eAnimationDirection mAnimationOutDirection;
	private final eAnimationDirection mAnimationInDirection;
	private final float mAnimationTimeIn;
	private final float mAnimationTimeOut;
	private final IEaseFunction mEasingIn;
	private final IEaseFunction mEasingOut;
	// ===========================================================
	// Constructors
	// ===========================================================
	public Swing(final float pX, final float pY, final float pWidth, final float pHeight,
			eAnimationDirection pAnimationOutDirection, eAnimationDirection pAnimationInDirection, float pAnimationTimeOut, final float pAnimationTimeIn,
			final IEaseFunction pEasingOut, final IEaseFunction pEasingIn, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pWidth, pHeight);
		setPosition(pX, pY);

		this.mAnimationOutDirection = pAnimationOutDirection;
		this.mAnimationInDirection = pAnimationInDirection;
		this.mEasingOut = pEasingOut;
		this.mEasingIn = pEasingIn;
		this.mAnimationTimeIn = pAnimationTimeIn;
		this.mAnimationTimeOut = pAnimationTimeOut;
		this.mContainerAnimator = new EntityAnimator(getContainer());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * Sets the value of a {@link Swing}. Appropriate (Exit+Enter) animation will be triggered
	 * right after current (if any) animation finishes.
	 * 
	 * @param pValue
	 */
	public void setValue(T pValue) {
		this.mPendingValue = pValue;
	}

	/**
	 * Sets the value of a {@link Swing} without any animation.
	 * @param pValue
	 */
	public void setValueDirectly(T pValue) {
		updateValue(pValue);
		this.mValue = pValue;
		this.mPendingValue = null;
	}

	/**
	 * Sets the value of a {@link Swing} with Enter animation only. (Exit animation is skipped)
	 * @param pValue
	 */
	public void setValueEnterAnimationOnly(T pValue) {
		animateIn();
		setValueDirectly(pValue);
	}

	public void animateIn() {
		animateIn(this.mAnimationTimeIn, null);
	}

	/**
	 * Sets the value of a {@link Swing} with Exit animation only. (Enter animation is skipped)
	 * @param pValue
	 */
	public void setValueExitAnimationOnly(T pValue) {
		animateOut();
		setValueDirectly(pValue);
	}

	public void animateOut() {
		animateOut(this.mAnimationTimeOut, null);
	}

	/**
	 *
	 * @return value set after last Enter animation
	 */
	public T getValue() {
		return this.mValue;
	}

	/**
	 *
	 * @return value set after last setValue call
	 */
	public T getValueLatest() {
		return this.mPendingValue != null ? this.mPendingValue : this.mValue;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	protected abstract void updateValue(T pValue);

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		if (this.mPendingValue != null && !isAnimating()) {
			animateOut(this.mAnimationTimeOut, this.mAnimateOutListener);
		}
		super.onManagedUpdate(pSecondsElapsed);
	}
	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Hides the container by moving it outside of the {@link ClippingWindow} window
	 * 
	 * @param pTime
	 * @param pListener
	 */
	protected void animateOut(final float pTime, final IAnimatorListener pListener) {
		positionForAnimationOut(getContainer());
		animate(getContainer(), this.mAnimationOutDirection, pTime, this.mEasingOut, pListener);
	}

	/**
	 * Shows the container by moving it back into the {@link ClippingWindow} window
	 * 
	 * @param pTime
	 * @param pListener
	 */
	protected void animateIn(final float pTime, final IAnimatorListener pListener) {
		positionForAnimationIn(getContainer(), this.mAnimationInDirection);
		animate(getContainer(), this.mAnimationInDirection, pTime, this.mEasingIn, pListener);
	}

	public boolean isAnimating() {
		return this.mContainerAnimator.isRunning();
	}

	private void animate(IEntity pEntity, eAnimationDirection pDirection, final float pTime, IEaseFunction pEasing, IAnimatorListener pListener) {
		final float fromX = pEntity.getX();
		final float fromY = pEntity.getY();
		final float distance = calculateDistance(pDirection);

		switch (pDirection) {
		case UP:
			this.mContainerAnimator.run(new MoveModifier(pTime , fromX, fromY, fromX, fromY + distance, pEasing), pListener);
			break;
		case DOWN:
			this.mContainerAnimator.run(new MoveModifier(pTime, fromX, fromY, fromX, fromY - distance, pEasing), pListener);
			break;
		case LEFT:
			this.mContainerAnimator.run(new MoveModifier(pTime, fromX, fromY, fromX - distance, fromY, pEasing), pListener);
			break;
		case RIGHT:
			this.mContainerAnimator.run(new MoveModifier(pTime, fromX, fromY, fromX + distance, fromY, pEasing), pListener);
			break;
		}
	}

	private float calculateDistance(eAnimationDirection pAnimationDirection) {
		if (pAnimationDirection.isHorizontal) {
			return getWindow().getWidth()/2 + getContainer().getWidth()/2;
		} else {
			return getWindow().getHeight()/2 + getContainer().getHeight()/2;
		}
	}

	private void positionForAnimationOut(final IEntity container) {
		container.setPosition(getWindow().getWidth()/2, getWindow().getHeight()/2);
	}

	private void positionForAnimationIn(IEntity pEntity, eAnimationDirection pDirection) {
		switch (pDirection) {
		case UP:
			PositionerImmovableRelative.getInstance().placeBelowOfAndCenter(getWindow(), pEntity);
			break;
		case DOWN:
			PositionerImmovableRelative.getInstance().placeTopOfAndCenter(getWindow(), pEntity);
			break;
		case LEFT:
			PositionerImmovableRelative.getInstance().placeRightOfAndCenter(getWindow(), pEntity);
			break;
		case RIGHT:
			PositionerImmovableRelative.getInstance().placeLeftOfAndCenter(getWindow(), pEntity);
			break;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	/**
	 * Animation directions supported {@link Swing}
	 */
	public static enum eAnimationDirection {
		UP(false),
		DOWN(false),
		LEFT(true),
		RIGHT(true);

		public final boolean isHorizontal;
		private eAnimationDirection(boolean pHorizontal) {
			this.isHorizontal = pHorizontal;
		}
	};

	/**
	 * Helper with a sole purpose of starting "in" animation
	 * when current ("out") animation is completed.
	 */
	protected class AnimationOutListener implements IAnimatorListener {

		@Override
		public void onAnimatorFinished(EntityAnimator pAnimator) {
			if (Swing.this.mPendingValue != null) {
				setValueDirectly(Swing.this.mPendingValue);
				animateIn(Swing.this.mAnimationTimeIn, null);
			}
		}
	}
}
