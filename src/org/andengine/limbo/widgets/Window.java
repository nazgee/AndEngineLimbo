package org.andengine.limbo.widgets;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.shape.IShape;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.Constants;
import org.andengine.util.adt.list.SmartList;

import android.util.SparseArray;

/**
 * (c) 2013 Michal Stawinski (nazgee)
 *
 * @author Michal Stawinski
 * @since 20:31:01 - 13.05.2013
 */
public class Window extends Entity {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final int TOUCHAREAS_CAPACITY_DEFAULT = 4;
	// ===========================================================
	// Fields
	// ===========================================================
	private IOnSceneTouchListener mOnSceneTouchListener;
	private IOnAreaTouchListener mOnAreaTouchListener;
	private boolean mOnAreaTouchTraversalBackToFront = true;
	protected boolean mTouchAreaBindingOnActionDownEnabled = false;
	protected boolean mTouchAreaBindingOnActionMoveEnabled = false;
	private final SparseArray<ITouchArea> mTouchAreaBindings = new SparseArray<ITouchArea>();
	protected boolean mOnSceneTouchListenerBindingOnActionDownEnabled = false;
	private final SparseArray<IOnSceneTouchListener> mOnSceneTouchListenerBindings = new SparseArray<IOnSceneTouchListener>();
	protected SmartList<ITouchArea> mTouchAreas = new SmartList<ITouchArea>(Window.TOUCHAREAS_CAPACITY_DEFAULT);
	private final IEntity mWindow;
	// ===========================================================
	// Constructors
	// ===========================================================
	public Window(IEntity pWindow) {
		super();
		this.mWindow = pWindow;
		setSize(pWindow.getWidth(), pWindow.getHeight());
		pWindow.setPosition(getWidth()/2, getHeight()/2);
		super.attachChild(this.mWindow);
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public IEntity getWindow() {
		return this.mWindow;
	}

	public boolean isTouchAreaBindingOnActionDownEnabled() {
		return this.mTouchAreaBindingOnActionDownEnabled;
	}

	public boolean isTouchAreaBindingOnActionMoveEnabled() {
		return this.mTouchAreaBindingOnActionMoveEnabled;
	}

	/**
	 * Enable or disable the binding of TouchAreas to PointerIDs (fingers).
	 * When enabled: TouchAreas get bound to a PointerID (finger) when returning true in
	 * {@link IShape#onAreaTouched(TouchEvent, float, float)} or
	 * {@link IOnAreaTouchListener#onAreaTouched(TouchEvent, ITouchArea, float, float)}
	 * with {@link TouchEvent#ACTION_DOWN}, they will receive all subsequent {@link TouchEvent}s
	 * that are made with the same PointerID (finger)
	 * <b>even if the {@link TouchEvent} is outside of the actual {@link ITouchArea}</b>!
	 *
	 * @param pTouchAreaBindingOnActionDownEnabled
	 */
	public void setTouchAreaBindingOnActionDownEnabled(final boolean pTouchAreaBindingOnActionDownEnabled) {
		if (this.mTouchAreaBindingOnActionDownEnabled && !pTouchAreaBindingOnActionDownEnabled) {
			this.mTouchAreaBindings.clear();
		}
		this.mTouchAreaBindingOnActionDownEnabled = pTouchAreaBindingOnActionDownEnabled;
	}

	/**
	 * Enable or disable the binding of TouchAreas to PointerIDs (fingers).
	 * When enabled: TouchAreas get bound to a PointerID (finger) when returning true in
	 * {@link IShape#onAreaTouched(TouchEvent, float, float)} or
	 * {@link IOnAreaTouchListener#onAreaTouched(TouchEvent, ITouchArea, float, float)}
	 * with {@link TouchEvent#ACTION_MOVE}, they will receive all subsequent {@link TouchEvent}s
	 * that are made with the same PointerID (finger)
	 * <b>even if the {@link TouchEvent} is outside of the actual {@link ITouchArea}</b>!
	 *
	 * @param pTouchAreaBindingOnActionMoveEnabled
	 */
	public void setTouchAreaBindingOnActionMoveEnabled(final boolean pTouchAreaBindingOnActionMoveEnabled) {
		if (this.mTouchAreaBindingOnActionMoveEnabled && !pTouchAreaBindingOnActionMoveEnabled) {
			this.mTouchAreaBindings.clear();
		}
		this.mTouchAreaBindingOnActionMoveEnabled = pTouchAreaBindingOnActionMoveEnabled;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void setWidth(float pWidth) {
		this.mWindow.setWidth(pWidth);
		super.setWidth(pWidth);
	}

	@Override
	public void setHeight(float pHeight) {
		this.mWindow.setHeight(pHeight);
		super.setHeight(pHeight);
	}

	@Override
	public void setSize(float pWidth, float pHeight) {
		this.mWindow.setSize(pWidth, pHeight);
		super.setSize(pWidth, pHeight);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void registerTouchArea(final ITouchArea pTouchArea) {
		this.mTouchAreas.add(pTouchArea);
	}

	public boolean unregisterTouchArea(final ITouchArea pTouchArea) {
		return this.mTouchAreas.remove(pTouchArea);
	}

	public boolean unregisterTouchAreas(final ITouchAreaMatcher pTouchAreaMatcher) {
		return this.mTouchAreas.removeAll(pTouchAreaMatcher);
	}

	public void clearTouchAreas() {
		this.mTouchAreas.clear();
	}

	public void setOnAreaTouchListener(final IOnAreaTouchListener pOnAreaTouchListener) {
		this.mOnAreaTouchListener = pOnAreaTouchListener;
	}

	public IOnAreaTouchListener getOnAreaTouchListener() {
		return this.mOnAreaTouchListener;
	}

	public boolean hasOnAreaTouchListener() {
		return this.mOnAreaTouchListener != null;
	}

	public void setOnSceneTouchListener(final IOnSceneTouchListener pOnSceneTouchListener) {
		this.mOnSceneTouchListener = pOnSceneTouchListener;
	}

	public IOnSceneTouchListener getOnSceneTouchListener() {
		return this.mOnSceneTouchListener;
	}

	public boolean hasOnSceneTouchListener() {
		return this.mOnSceneTouchListener != null;
	}

	public void setOnAreaTouchTraversalBackToFront() {
		this.mOnAreaTouchTraversalBackToFront = true;
	}

	public void setOnAreaTouchTraversalFrontToBack() {
		this.mOnAreaTouchTraversalBackToFront = false;
	}

	public boolean onSceneTouchEvent(final TouchEvent pSceneTouchEvent) {
		final int action = pSceneTouchEvent.getAction();
		final boolean isActionDown = pSceneTouchEvent.isActionDown();
		final boolean isActionMove = pSceneTouchEvent.isActionMove();

		if (!isActionDown) {
			if (this.mOnSceneTouchListenerBindingOnActionDownEnabled) {
				final IOnSceneTouchListener boundOnSceneTouchListener = this.mOnSceneTouchListenerBindings.get(pSceneTouchEvent.getPointerID());
				if (boundOnSceneTouchListener != null) {
					/* Check if boundTouchArea needs to be removed. */
					switch (action) {
						case TouchEvent.ACTION_UP:
						case TouchEvent.ACTION_CANCEL:
							this.mOnSceneTouchListenerBindings.remove(pSceneTouchEvent.getPointerID());
					}
					final Boolean handled = this.mOnSceneTouchListener.onSceneTouchEvent(null, pSceneTouchEvent);
					if (handled != null && handled) {
						return true;
					}
				}
			}
			if (this.mTouchAreaBindingOnActionDownEnabled) {
				final SparseArray<ITouchArea> touchAreaBindings = this.mTouchAreaBindings;
				final ITouchArea boundTouchArea = touchAreaBindings.get(pSceneTouchEvent.getPointerID());
				/* In the case a ITouchArea has been bound to this PointerID,
				 * we'll pass this this TouchEvent to the same ITouchArea. */
				if (boundTouchArea != null) {
					final float sceneTouchEventX = pSceneTouchEvent.getX();
					final float sceneTouchEventY = pSceneTouchEvent.getY();

					/* Check if boundTouchArea needs to be removed. */
					switch (action) {
						case TouchEvent.ACTION_UP:
						case TouchEvent.ACTION_CANCEL:
							touchAreaBindings.remove(pSceneTouchEvent.getPointerID());
					}
					final Boolean handled = this.onAreaTouchEvent(pSceneTouchEvent, sceneTouchEventX, sceneTouchEventY, boundTouchArea);
					if (handled != null && handled) {
						return true;
					}
				}
			}
		}

		final float sceneTouchEventX = pSceneTouchEvent.getX();
		final float sceneTouchEventY = pSceneTouchEvent.getY();

		final SmartList<ITouchArea> touchAreas = this.mTouchAreas;
		if (touchAreas != null) {
			final int touchAreaCount = touchAreas.size();
			if (touchAreaCount > 0) {
				if (this.mOnAreaTouchTraversalBackToFront) { /* Back to Front. */
					for (int i = 0; i < touchAreaCount; i++) {
						final ITouchArea touchArea = touchAreas.get(i);
						if (touchArea.contains(sceneTouchEventX, sceneTouchEventY)) {
							final Boolean handled = this.onAreaTouchEvent(pSceneTouchEvent, sceneTouchEventX, sceneTouchEventY, touchArea);
							if (handled != null && handled) {
								/* If binding of ITouchAreas is enabled and this is an ACTION_DOWN event,
								 * bind this ITouchArea to the PointerID. */
								if ((this.mTouchAreaBindingOnActionDownEnabled && isActionDown) || (this.mTouchAreaBindingOnActionMoveEnabled && isActionMove)) {
									this.mTouchAreaBindings.put(pSceneTouchEvent.getPointerID(), touchArea);
								}
								return true;
							}
						}
					}
				} else { /* Front to back. */
					for (int i = touchAreaCount - 1; i >= 0; i--) {
						final ITouchArea touchArea = touchAreas.get(i);
						if (touchArea.contains(sceneTouchEventX, sceneTouchEventY)) {
							final Boolean handled = this.onAreaTouchEvent(pSceneTouchEvent, sceneTouchEventX, sceneTouchEventY, touchArea);
							if (handled != null && handled) {
								/* If binding of ITouchAreas is enabled and this is an ACTION_DOWN event,
								 * bind this ITouchArea to the PointerID. */
								if ((this.mTouchAreaBindingOnActionDownEnabled && isActionDown) || (this.mTouchAreaBindingOnActionMoveEnabled && isActionMove)) {
									this.mTouchAreaBindings.put(pSceneTouchEvent.getPointerID(), touchArea);
								}
								return true;
							}
						}
					}
				}
			}
		}
		/* If no area was touched, the Scene itself was touched as a fallback. */
		if (this.mOnSceneTouchListener != null) {
			final Boolean handled = this.mOnSceneTouchListener.onSceneTouchEvent(null, pSceneTouchEvent);
			if (handled != null && handled) {
				/* If binding of ITouchAreas is enabled and this is an ACTION_DOWN event,
				 * bind the active OnSceneTouchListener to the PointerID. */
				if (this.mOnSceneTouchListenerBindingOnActionDownEnabled && isActionDown) {
					this.mOnSceneTouchListenerBindings.put(pSceneTouchEvent.getPointerID(), this.mOnSceneTouchListener);
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private Boolean onAreaTouchEvent(final TouchEvent pSceneTouchEvent, final float sceneTouchEventX, final float sceneTouchEventY, final ITouchArea touchArea) {
		final float[] touchAreaLocalCoordinates = touchArea.convertSceneCoordinatesToLocalCoordinates(sceneTouchEventX, sceneTouchEventY);
		final float touchAreaLocalX = touchAreaLocalCoordinates[Constants.VERTEX_INDEX_X];
		final float touchAreaLocalY = touchAreaLocalCoordinates[Constants.VERTEX_INDEX_Y];

		final boolean handledSelf = touchArea.onAreaTouched(pSceneTouchEvent, touchAreaLocalX, touchAreaLocalY);
		if (handledSelf) {
			return Boolean.TRUE;
		} else if (this.mOnAreaTouchListener != null) {
			return this.mOnAreaTouchListener.onAreaTouched(pSceneTouchEvent, touchArea, touchAreaLocalX, touchAreaLocalY);
		} else {
			return null;
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
