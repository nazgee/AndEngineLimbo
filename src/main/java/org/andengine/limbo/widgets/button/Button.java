package org.andengine.limbo.widgets.button;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.NineSliceSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.limbo.utils.positioner.PositionerImmovableRelative;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;
import org.andengine.util.exception.AndEngineRuntimeException;

/**
 * Heavily based on ButtonSprite from AndEngine core
 * 
 * It's main advantage is that it allows using different entities button faces
 * not only TiledTextureRegion, which is the case for ButtonSprite.
 * 
 * (c) 2013 Michal Stawinski (nazgee)
 * 
 * @author Michal Stawinski
 * @since 20:31:01 - 13.05.2013
 */
public class Button extends Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected IEntity mEntities[] = new IEntity[State.STATES_COUNT];
	private OnClickListener mOnClickUpListener;
	private OnClickListener mOnClickDownListener;
	private boolean mEnabled = true;
	private State mState = State.UNDEFINED;
	private boolean mIsBistable = false;
	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * Creates a button using provided ITextureRegions to create NineScliceSprites for faces representing Button states
	 * 
	 * @param pWidth
	 * @param pHeight
	 * @param pFaceWidth
	 * @param pFaceHeight
	 * @param pNormal
	 * @param pPressed
	 * @param pDisabled
	 * @param pVBOM
	 */
	public Button(final float pWidth, final float pHeight, final float pFaceWidth, final float pFaceHeight, final ITextureRegion pNormal, final ITextureRegion pPressed, final ITextureRegion pDisabled,
			final float pInsetLeft, final float pInsetTop, final float pInsetRight, final float pInsetBottom, VertexBufferObjectManager pVBOM) {

		this(pWidth, pHeight,
				pNormal == null ? null : new NineSliceSprite(0, 0, pFaceWidth, pFaceHeight, pNormal, pInsetLeft, pInsetTop, pInsetRight, pInsetBottom, pVBOM),
				pPressed == null ? null : new NineSliceSprite(0, 0, pFaceWidth, pFaceHeight, pPressed, pInsetLeft, pInsetTop, pInsetRight, pInsetBottom, pVBOM),
				pDisabled == null ? null : new NineSliceSprite(0, 0, pFaceWidth, pFaceHeight, pDisabled, pInsetLeft, pInsetTop, pInsetRight, pInsetBottom, pVBOM));
	}

	public Button(final ITextureRegion pNormal, final ITextureRegion pPressed, final ITextureRegion pDisabled, VertexBufferObjectManager pVBOM) {
		this(pNormal.getWidth(), pNormal.getHeight(), pNormal.getWidth(), pNormal.getHeight(), pNormal, pPressed, pDisabled, pVBOM);
	}

	public Button(final float pWidth, final float pHeight, final ITextureRegion pNormal, final ITextureRegion pPressed, final ITextureRegion pDisabled, VertexBufferObjectManager pVBOM) {
		this(pWidth, pHeight, pNormal.getWidth(), pNormal.getHeight(), pNormal, pPressed, pDisabled, pVBOM);
	}
	/**
	 * Creates a button using provided ITextureRegions to create Sprites for faces representing Button states
	 * 
	 * @param pWidth
	 * @param pHeight
	 * @param pFaceWidth
	 * @param pFaceHeight
	 * @param pNormal
	 * @param pPressed
	 * @param pDisabled
	 * @param pVBOM
	 */
	public Button(final float pWidth, final float pHeight, final float pFaceWidth, final float pFaceHeight, final ITextureRegion pNormal, final ITextureRegion pPressed, final ITextureRegion pDisabled, VertexBufferObjectManager pVBOM) {

		this(pWidth, pHeight,
				pNormal == null ? null : new Sprite(0, 0, pFaceWidth, pFaceHeight, pNormal, pVBOM),
				pPressed == null ? null : new Sprite(0, 0, pFaceWidth, pFaceHeight, pPressed, pVBOM),
				pDisabled == null ? null : new Sprite(0, 0, pFaceWidth, pFaceHeight, pDisabled, pVBOM));
	}

	/**
	 * Creates a button using provided entities as faces representing Button states
	 * 
	 * @note Supplied entities will be moved to the center of Button
	 * @param pWidth
	 * @param pHeight
	 * @param pNormal
	 * @param pPressed
	 * @param pDisabled
	 */
	public Button(final float pWidth, final float pHeight, final IEntity pNormal, final IEntity pPressed, final IEntity pDisabled) {

		setSize(pWidth, pHeight);
		if (pNormal == null) {
			throw new AndEngineRuntimeException("pNormal button face is null");
		}

		this.mEntities[State.NORMAL.getEntityIndex()] = pNormal;
		this.mEntities[State.PRESSED.getEntityIndex()] = pPressed;
		this.mEntities[State.DISABLED.getEntityIndex()] = pDisabled;

		this.mState = State.NORMAL;
		positionEntities();
	}

	/**
	 * Creates a button using provided entities as faces representing Button states
	 *
	 * @note Supplied entities will be moved to the center of Button
	 * @param pNormal
	 * @param pPressed
	 * @param pDisabled
	 */
	public Button(final IEntity pNormal, final IEntity pPressed, final IEntity pDisabled) {

		setSize(pNormal.getWidth(), pNormal.getHeight());
		if (pNormal == null) {
			throw new AndEngineRuntimeException("pNormal button face is null");
		}

		this.mEntities[State.NORMAL.getEntityIndex()] = pNormal;
		this.mEntities[State.PRESSED.getEntityIndex()] = pPressed;
		this.mEntities[State.DISABLED.getEntityIndex()] = pDisabled;

		this.mState = State.NORMAL;
		positionEntities();
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public void setBistable(final boolean pIsBistable) {
		this.mIsBistable = pIsBistable;
	}

	public boolean isBistable() {
		return this.mIsBistable;
	}

	public void setFaceSize(final float pWidth, final float pHeight) {
		PositionerImmovableRelative positioner = PositionerImmovableRelative.getInstance();
		for (IEntity entity : this.mEntities) {
			if (entity != null) {
				entity.setSize(pWidth, pHeight);
				positioner.center(entity.getParent(), entity);
			}
		}
	}

	public IEntity getFace(State pState) {
		return this.mEntities[pState.getEntityIndex()];
	}

	public void positionEntities() {
		detachChild(mEntities[0]);
		detachChild(mEntities[1]);
		detachChild(mEntities[2]);

		attachChild(mEntities[0]);
		attachChild(mEntities[1]);
		attachChild(mEntities[2]);

		PositionerImmovableRelative positioner = PositionerImmovableRelative.getInstance();
		for (IEntity entity : this.mEntities) {
			if (entity != null) {
				positioner.center(entity.getParent(), entity);
			}
		}

		detachChild(mEntities[0]);
		detachChild(mEntities[1]);
		detachChild(mEntities[2]);

		State savedState = mState;
		mState = State.UNDEFINED;
		changeState(savedState);
	}

	public boolean isEnabled() {
		return this.mEnabled;
	}

	public void setEnabled(final boolean pEnabled) {
		this.mEnabled = pEnabled;

		if (this.mEnabled && this.mState == State.DISABLED) {
			this.changeState(State.NORMAL);
		} else if (!this.mEnabled) {
			this.changeState(State.DISABLED);
		}
	}

	public boolean isPressed() {
		return this.mState == State.PRESSED;
	}

	public State getState() {
		return this.mState;
	}

	/**
	 * Sets the listener which will be triggered when click is finished
	 * 
	 * @param pOnClickListener
	 */
	public void setOnClickUpListener(final OnClickListener pOnClickListener) {
		this.mOnClickUpListener = pOnClickListener;
	}

	/**
	 * Sets the listener which will be triggered when click is started
	 * 
	 * @param pOnClickListener
	 */
	public void setOnClickDownListener(final OnClickListener pOnClickListener) {
		this.mOnClickDownListener = pOnClickListener;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	protected void attachDefaultFace() {
		// we assume that NORMAL face HAS to be provided
		attachChild(this.mEntities[State.NORMAL.getEntityIndex()]);
	}
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (!this.isEnabled()) {
			this.changeState(State.DISABLED);
		} else if (pSceneTouchEvent.isActionDown()) {
			if (this.mState != State.PRESSED) {
				doPressedDown(pTouchAreaLocalX, pTouchAreaLocalY);
			} else {
				doUnpressed(pTouchAreaLocalX, pTouchAreaLocalY);
			}
		} else if (pSceneTouchEvent.isActionCancel()) {
			doCancelled();
		} else if (pSceneTouchEvent.isActionUp()) {
			boolean contains = this.contains(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
			if (	this.mState == State.PRESSED && !this.mIsBistable && contains) {
				doUnpressed(pTouchAreaLocalX, pTouchAreaLocalY);
			} else if (!contains) {
				doCancelled();
			}
		}

		return true;
	}

	protected void doCancelled() {
		this.changeState(State.NORMAL);
	}

	protected void doUnpressed(final float pTouchAreaLocalX,
			final float pTouchAreaLocalY) {
		this.changeState(State.NORMAL);

		if (this.mOnClickUpListener != null) {
			this.mOnClickUpListener.onClick(this, pTouchAreaLocalX, pTouchAreaLocalY);
		}
	}

	protected void doPressedDown(final float pTouchAreaLocalX,
			final float pTouchAreaLocalY) {
		this.changeState(State.PRESSED);

		if (this.mOnClickDownListener != null) {
			this.mOnClickDownListener.onClick(this, pTouchAreaLocalX, pTouchAreaLocalY);
		}
	}

	@Override
	public boolean contains(final float pX, final float pY) {
		if (!this.isEnabled() || !this.isVisible()) {
			return false;
		} else {
			return super.contains(pX, pY);
		}
	}
	// ===========================================================
	// Methods
	// ===========================================================
	public void changeState(final State pState) {
		if (pState == this.mState) {
			return;
		}

		final IEntity pNewFace = this.mEntities[pState.getEntityIndex()];
		final IEntity pOldFace = this.mEntities[this.mState.getEntityIndex()];
		this.mState = pState;

		detachChild(pOldFace);
		detachChild(pNewFace);
		if (pNewFace == null) {
			attachDefaultFace();
			Debug.w(this.getClass().getSimpleName() + " changed its " + State.class.getSimpleName() + " to " + pState.toString() + ", which doesn't have an " + IEntity.class.getSimpleName() + " supplied. Applying default " + IEntity.class.getSimpleName() + ".");
		} else {
			attachChild(pNewFace);
		}
		sortChildren(false);
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public interface OnClickListener {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		public void onClick(final Button pButton, final float pTouchAreaLocalX, final float pTouchAreaLocalY);
	}

	public static enum State {
		// ===========================================================
		// Elements
		// ===========================================================

		UNDEFINED(0),
		NORMAL(0),
		PRESSED(1),
		DISABLED(2);

		// ===========================================================
		// Constants
		// ===========================================================
		public static int STATES_COUNT = 3;
		// ===========================================================
		// Fields
		// ===========================================================

		private final int mEntityIndex;

		// ===========================================================
		// Constructors
		// ===========================================================

		private State(final int pEntityIndex) {
			this.mEntityIndex = pEntityIndex;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		public int getEntityIndex() {
			return this.mEntityIndex;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
